package hu.pazsitz.pacuse.pageObjects.widgets;

import org.openqa.selenium.WebDriver;

import hu.pazsitz.pacuse.pageObjects.AbstractWidgetObject;
import hu.pazsitz.pacuse.pages.widgets.AboutWidget;

public class AboutWidgetObject extends AbstractWidgetObject<AboutWidget> {

	public AboutWidgetObject(AboutWidget widget, WebDriver webDriver) {
		super(widget, webDriver);
	}

}
