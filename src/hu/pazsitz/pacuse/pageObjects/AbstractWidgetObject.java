package hu.pazsitz.pacuse.pageObjects;

import hu.pazsitz.pacuse.pages.AbstractWidget;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

/**
 * AbstractWidgetObject.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
public class AbstractWidgetObject<W extends AbstractWidget> {
    protected WebDriver webDriver;
    protected W widget;

    /**
     * Widget is provided (ideally from the Page Model object page.getXYWidget())
     * @param widget
     * @param webDriver
     */
    public AbstractWidgetObject(W widget, WebDriver webDriver) {
    	if (widget == null) {
            throw new IllegalArgumentException("Widget should not be null!");
        }
    	this.widget = widget;
    	this.webDriver = webDriver;
    }
    
    public boolean exists() {
    	return getWidget().exists();
    }
    
    public boolean visible() throws NoSuchElementException {
    	return getWidget().visible();
    }

    public W getWidget() {
        return widget;
    }
}
