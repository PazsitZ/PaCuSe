package hu.pazsitz.pacuse.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

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
    
    /**
     * Creates the Widget Model
     * @param clazz
     * @return ? extends {@link AbstractWidget}
     */
    protected <W> W registerWidget(Class<W> clazz) {
    	W widget = null;
		try {
			widget = clazz.getDeclaredConstructor(WebDriver.class).newInstance(webDriver);
		} catch (Exception e) {
			// TODO log4j
			e.printStackTrace();
		}
		
    	PageFactory.initElements(webDriver, widget);
    	
    	return widget;
	}
}
