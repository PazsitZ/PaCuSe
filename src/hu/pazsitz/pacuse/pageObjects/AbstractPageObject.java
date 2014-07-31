package hu.pazsitz.pacuse.pageObjects;

import hu.pazsitz.pacuse.pages.AbstractPage;
import hu.pazsitz.pacuse.pages.AbstractWidget;

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
    
    /**
     * Creates the widgetObject
     * @param clazz Class< ? extends {@link AbstractWidgetObject<?>} >
     * @param widget ideally comes from the Page Model object page.getXYWidget()
     * @return < ? extends {@link AbstractWidgetObject} >
     */
	protected <W extends AbstractWidgetObject<? extends AbstractWidget>> W registerWidgetObject(Class<W> clazz, AbstractWidget widget) {
		W widgetObject = null;
		
		try {
			widgetObject = clazz.getDeclaredConstructor(
					widget.getClass(), WebDriver.class).newInstance(
						widget, webDriver);
		} catch (Exception e) {
			// TODO log4j
			e.printStackTrace();
		}

		return widgetObject; 
	}

    protected void loadPage() {
        String pageUrl = page.getUrl();
        page.getWebDriver().get(pageUrl);
    }

    public P getPage() {
        return page;
    }
}
