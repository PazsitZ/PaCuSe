package hu.pazsitz.pacuse.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * GeneralWidgetImpl.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
public class GeneralWidgetImpl extends AbstractWidget {

	private WebElement container;
	
	public GeneralWidgetImpl(WebDriver webDriver, WebElement element) {
		super(webDriver);
		this.container = element;
	}
	
	public GeneralWidgetImpl(WebDriver webDriver, By by) {
		super(webDriver);
		this.container = getWebDriver().findElement(by);
	}

	@Override
	public WebElement getContainer() {
		return container;
	}

}
