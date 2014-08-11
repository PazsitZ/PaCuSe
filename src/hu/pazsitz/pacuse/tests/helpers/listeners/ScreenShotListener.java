package hu.pazsitz.pacuse.tests.helpers.listeners;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import hu.pazsitz.pacuse.tests.helpers.StepDefBase;

/**
 * ScreenShotListener.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
public class ScreenShotListener extends TestListenerAdapter {
    private static final String SCREENSHOT_FOLDER = "build/cucumber-html-report/images/";
    private static final String SCREENSHOT_FORMAT = ".png";

    @Override
    public void onTestFailure(ITestResult tr) {
        File screenshot = new File(SCREENSHOT_FOLDER + tr.getName().replace(" ", "_") + SCREENSHOT_FORMAT);
        if (!screenshot.exists()) {
            new File(screenshot.getParent()).mkdirs();
            try {
                screenshot.createNewFile();
            } catch (IOException e) {
            	Logger.getLogger(this.getClass()).error(e.getMessage());
            }
        }
        try {
            new FileOutputStream(screenshot).write(((TakesScreenshot) StepDefBase.getInstance().getWebDriver())
                    .getScreenshotAs(OutputType.BYTES));
        } catch (FileNotFoundException e) {
        	Logger.getLogger(this.getClass()).error(e.getMessage());
        } catch (IOException e) {
        	Logger.getLogger(this.getClass()).error(e.getMessage());
        }
        Logger.getLogger(this.getClass()).info("Written screenshot to " + screenshot.getAbsolutePath());
    }
}