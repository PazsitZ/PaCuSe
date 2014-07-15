package hu.pazsitz.pacuse.tests.helpers;

import hu.pazsitz.pacuse.pages.AbstractPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;


/*
 * Base class for all the test classes

 * TestBase.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
public class TestBase {

    protected WebDriver webDriver;

    protected String server;

    protected String websiteUrl;

    @BeforeClass
    public void init() {
        server = "";
        webDriver = WebDriverFactory.getInstance(WebDriverFactory.BrowserName.FIREFOX);
        Waiters.setWebDriver(webDriver);
    }

    @BeforeMethod
    public void initMethod() {
        webDriver.manage().deleteAllCookies();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    @AfterMethod
    public void setScreenshot(ITestResult result) {
//        if (!result.isSuccess()) {
//            ATUReports.add("Test Failed", LogAs.FAILED, null);

//            try {
//                WebDriver returned = new Augmenter().augment(webDriver);
//                if (returned != null) {
//                    File f = ((TakesScreenshot) returned).getScreenshotAs(OutputType.FILE);
//                    try {
//                        FileUtils.copyFile(f, new File(SCREENSHOT_FOLDER + result.getName() + SCREENSHOT_FORMAT));
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            } catch (ScreenshotException se) {
//                se.printStackTrace();
//            }
//        }
    }


    protected void loadPage(AbstractPage pageImpl) {
        String pageUrl = server + pageImpl.getUrl();
        pageImpl.getWebDriver().get(pageUrl);
        PageFactory.initElements(webDriver, pageImpl);
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }


}