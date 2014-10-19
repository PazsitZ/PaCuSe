package hu.pazsitz.pacuse.tests.helpers;

import hu.pazsitz.pacuse.pages.AbstractPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
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
        String weDriverGridUrl = System.getProperty("PaCuSe.WebDriver.gridUrl", "");
        if (weDriverGridUrl.isEmpty()) {
            webDriver = WebDriverFactory.getInstance(
        		WebDriverFactory.BrowserName.getBrowser(
        			System.getProperty("PaCuSe.browser")
        		)
    		);
        } else {
            webDriver = WebDriverFactory.getInstance(
                weDriverGridUrl, 
                WebDriverFactory.BrowserName.getBrowser(
                    System.getProperty("PaCuSe.browser")
                ), 
                "", ""
            );
        }
        
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

    protected void loadPage(AbstractPage pageImpl) {
        String pageUrl = server + pageImpl.getUrl();
        pageImpl.getWebDriver().get(pageUrl);
        PageFactory.initElements(webDriver, pageImpl);
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }


}