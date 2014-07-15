package hu.pazsitz.seleniumtestframework.tests.helpers;

import hu.pazsitz.seleniumtestframework.pages.AbstractPage;

import org.openqa.selenium.support.PageFactory;

/**
 * StepDefBase.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
public class StepDefBase {

    private static StepDefBase stepDefBase = null;

    /**
     * public for cucumber AutoWire
     */
    public StepDefBase() {
        stepDefBase = this;
        init();
    }

    public static StepDefBase getInstance() {
        if (null == stepDefBase) {
            stepDefBase = new StepDefBase();
        }

        return stepDefBase;
    }


    protected SharedDriver webDriver;

    protected String server;

    protected String websiteUrl;

    public void init() {
        webDriver = new SharedDriver();
        Waiters.setWebDriver(webDriver);
    }

    public void deleteCookies() {
        webDriver.manage().deleteAllCookies();
    }

    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    public void loadPage(AbstractPage pageImpl) {
        String pageUrl = pageImpl.getUrl();
        pageImpl.getWebDriver().get(pageUrl);
        PageFactory.initElements(webDriver, pageImpl);
    }

    public SharedDriver getWebDriver() {
        return webDriver;
    }
}
