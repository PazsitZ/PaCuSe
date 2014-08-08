package hu.pazsitz.pacuse.pageObjects;

import hu.pazsitz.pacuse.pages.AbstractPage;
import hu.pazsitz.pacuse.pages.AbstractWidget;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.google.common.collect.Iterables;

/**
 * AbstractPageObject.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
public abstract class AbstractPageObject<P extends AbstractPage> {
    protected WebDriver webDriver;
    protected P page;
    private String prevPageHandle;

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

	/**
	 * Loads the actual page by the defined page url
	 */
    protected void loadPage() {
        String pageUrl = page.getUrl();
        page.getWebDriver().get(pageUrl);
    }
    
    /**
     * Loads the actual page by the defined page url
     * and appends the given parameters
     * @param additionalParams
     */
    protected void loadPage(String additionalParams) {
    	String pageUrl = page.getUrl() + additionalParams;
    	page.getWebDriver().get(pageUrl);
    }
    
    /**
     * Loads the actual page by the given page url
     * and appends the given parameters if any given
     * @param additionalParams
     */
    protected void loadPage(String url, String additionalParams) {
    	if (additionalParams == null) additionalParams = "";
    	
    	String pageUrl = url + additionalParams;
    	page.getWebDriver().get(pageUrl);
    }
    
    /**
     * Opens and loads the given content in a new window
     * to navigate back use the this.goToPreviousPage()
     * @param url
     * @return newly opened page handle Identifier String
     */
    public String loadPageInNewWindow(String url) {
        prevPageHandle = webDriver.getWindowHandle();
        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
        jsExecutor.executeScript("window.open(\"" + url + "\")");
        String connectorPageHandle = Iterables.getLast(webDriver.getWindowHandles());
       
        webDriver.switchTo().window(connectorPageHandle);
       
        return connectorPageHandle;
    }
    
    /**
     * Navigate back after page a page is loaded into new window
     * optionally closes the actual window
     * @param closeActual
     * @return boolean
     */
    public boolean goToPreviousPage(boolean closeActual) {
    	if (!prevPageHandle.isEmpty() && webDriver.getWindowHandles().contains(prevPageHandle)) {
    		if (closeActual && !webDriver.getWindowHandle().equals(prevPageHandle)) {
    			webDriver.close();
    		}
    		webDriver.switchTo().window(prevPageHandle);
    		return true;
    	}
    	
    	return false;
    }

    public P getPage() {
        return page;
    }
}
