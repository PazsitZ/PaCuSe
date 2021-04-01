package hu.pazsitz.pacuse.tests.helpers;

import hu.pazsitz.pacuse.pages.AbstractPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;


/*
 * Base class for all the test classes

 * TestBase.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
public class TestBaseForGradle {

    protected WebDriver webDriver;

    protected String server;

    protected String websiteUrl;

    /**
     * @BeforeClass is removed, to handle the gradle TestNg bug
     * You must invoke it from the actual test @BeforeClass method
     */
    public void init() {
        server = "";
        webDriver = new SharedDriver();
        
        Waiters.setWebDriver(webDriver);
    }

    @BeforeMethod
    public void initMethod() {
        webDriver.manage().deleteAllCookies();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
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