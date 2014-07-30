package hu.pazsitz.pacuse.pageObjects;

import hu.pazsitz.pacuse.pages.GeneralWidgetImpl;

import org.openqa.selenium.WebDriver;

/**
 * GeneralWidgetImplObject.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
public class GeneralWidgetImplObject extends AbstractWidgetObject<GeneralWidgetImpl>{

	public GeneralWidgetImplObject(GeneralWidgetImpl widget, WebDriver webDriver) {
		super(widget, webDriver);
	}
	
}
