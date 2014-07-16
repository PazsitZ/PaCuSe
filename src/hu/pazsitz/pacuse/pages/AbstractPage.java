package hu.pazsitz.pacuse.pages;

import org.openqa.selenium.WebDriver;

/**
 * AbstractPage.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
public abstract class AbstractPage {

    protected WebDriver webDriver;

    protected String pageUrl = "/";

    public AbstractPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public String getTitle() {
        return webDriver.getTitle();
    }

    abstract public String getUrl();
    
}
