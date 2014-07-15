package hu.pazsitz.seleniumtestframework.pageObjects;

import hu.pazsitz.seleniumtestframework.pages.AbstractPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractPageObject<P extends AbstractPage> {
    protected WebDriver webDriver;
    protected P page;

    public AbstractPageObject(Class<P> pageClass, WebDriver webDriver) {
        this.webDriver = webDriver;
        instantiatePage(pageClass, webDriver);
    }

    public AbstractPageObject(Class<P> pageClass, WebDriver webDriver, boolean implicitLoadPage) {
        this.webDriver = webDriver;
        instantiatePage(pageClass, webDriver);
        if (implicitLoadPage) {
            loadPage();
        }
    }

    private void instantiatePage(Class<P> page, WebDriver webDriver) {
        try {
            this.page = page.getDeclaredConstructor(WebDriver.class).newInstance(webDriver);
        } catch (Exception e) {
            e.printStackTrace();
        }
        PageFactory.initElements(webDriver, this.page);
    }

    protected void loadPage() {
        String pageUrl = page.getUrl();
        page.getWebDriver().get(pageUrl);
    }

    public P getPage() {
        return page;
    }
}
