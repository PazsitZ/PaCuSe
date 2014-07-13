package hu.pazsit.seleniumtestframework.pages;

import org.openqa.selenium.By;
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

    public String getPageName() {
        return webDriver.findElement(By.id("pageId")).getAttribute("value").toString();
    }

    public String getPageId() {
        return webDriver.findElement(By.tagName("body")).getAttribute("data-pageid");
    }

    abstract public String getUrl();
}
