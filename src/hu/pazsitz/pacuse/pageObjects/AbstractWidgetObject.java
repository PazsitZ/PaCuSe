package hu.pazsitz.pacuse.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import hu.pazsitz.pacuse.pages.AbstractWidget;

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
    
    /**
     * Implicit Instantiation of the Widget
     * @param widgetClass
     * @param webDriver
     */
    public AbstractWidgetObject(Class<W> widgetClass, WebDriver webDriver) {
        this.webDriver = webDriver;
        instantiatePage(widgetClass, webDriver);
    }

    private void instantiatePage(Class<W> widget, WebDriver webDriver) {
        try {
            this.widget = widget.getDeclaredConstructor(WebDriver.class).newInstance(webDriver);
        } catch (Exception e) {
            e.printStackTrace();
        }
        PageFactory.initElements(webDriver, this.widget);
    }

    public W getWidget() {
        return widget;
    }
}
