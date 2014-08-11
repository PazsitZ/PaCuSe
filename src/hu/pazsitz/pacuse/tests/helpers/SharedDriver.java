package hu.pazsitz.pacuse.tests.helpers;


import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

/**
* <p>
* Example of a WebDriver implementation that has delegates all methods to a static instance (REAL_DRIVER) that is only
* created once for the duration of the JVM. The REAL_DRIVER is automatically closed when the JVM exits. This makes
* scenarios a lot faster since opening and closing a browser for each scenario is pretty slow.
* To prevent browser state from leaking between scenarios, cookies are automatically deleted before every scenario.
* </p>
* <p>
* A new instance of SharedDriver is created for each Scenario and passed to yor Stepdef classes via Dependency Injection
* </p>
* <p>
* As a bonus, screenshots are embedded into the report for each scenario. (This only works
* if you're also using the HTML formatter).
* </p>
* <p>
* A new instance of the SharedDriver is created for each Scenario and then passed to the Step Definition classes'
* constructor. They all receive a reference to the same instance. However, the REAL_DRIVER is the same instance throughout
* the life of the JVM.
* </p>
*/
public class SharedDriver extends EventFiringWebDriver {
    private static boolean doEmbedScreenshot = false;
    private static final WebDriver REAL_DRIVER = WebDriverFactory.getInstance(
		WebDriverFactory.BrowserName.getBrowser(
			System.getProperty("PaCuSe.browser")
		)
	);

    public SharedDriver() {
        super(REAL_DRIVER);
        doEmbedScreenshot = Boolean.parseBoolean(System.getProperty("cucumber.report.embed_screenshot", "false"));
    }

    @Override
    public void close() {
        if (REAL_DRIVER != null) {
            super.close();
        }
    }

    @Before
    public void deleteAllCookies() {
        manage().deleteAllCookies();
    }

    @After
    public void embedScreenshot(Scenario scenario) {
        if (scenario.isFailed()) {
            String scenarioName = scenario.getName().replace(" ", "_").replaceAll("[^\\w_]", "");
            byte[] screenshot = makeScreenshot(scenarioName);

            if (doEmbedScreenshot && screenshot.length > 0) {
                scenario.embed(screenshot, "image/png");
            }
        }
    }

    public boolean makeScreenshot() {
        String name = Thread.currentThread().getStackTrace()[2].getMethodName();
        return makeScreenshot(name).length > 0;
    }

    protected byte[] makeScreenshot(String name) {
        final String SCREENSHOT_FOLDER = System.getProperty("PaCuSe.WebDriver.screenshot.path", "reports/cucumber-html-report/images/");
        final String SCREENSHOT_FORMAT = "png";
        byte[] screenshot = new byte[0];

        try {
            screenshot = getScreenshotAs(OutputType.BYTES);
            ByteArrayInputStream bais = new ByteArrayInputStream(screenshot);
            BufferedImage image = ImageIO.read(bais);
            ImageIO.write(image, SCREENSHOT_FORMAT, new File(SCREENSHOT_FOLDER + name + "." + SCREENSHOT_FORMAT));
        } catch (WebDriverException e) {
        	Logger.getLogger(this.getClass()).error(e.getMessage());
        } catch (IOException e) {
        	Logger.getLogger(this.getClass()).error(e.getMessage());
        }

        return screenshot;
    }
}