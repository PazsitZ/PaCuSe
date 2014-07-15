package hu.pazsitz.pacuse.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import hu.pazsitz.pacuse.pages.AbstractWidget;

public class AbstractWidgetObject<W extends AbstractWidget> {
    protected WebDriver webDriver;
    protected W widget;

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
