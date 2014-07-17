package hu.pazsitz.pacuse.pageObjects;

import org.openqa.selenium.WebDriver;

import hu.pazsitz.pacuse.pages.GeneralWidgetImpl;

/**
 * GeneralWidgetImplObject.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
public class GeneralWidgetImplObject extends AbstractWidgetObject<GeneralWidgetImpl>{

	public GeneralWidgetImplObject(WebDriver webDriver) {
		super(GeneralWidgetImpl.class, webDriver);
	}

}
