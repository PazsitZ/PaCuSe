package hu.pazsitz.pacuse.tests.cucumber.featuretables;

import hu.pazsitz.pacuse.tests.annotations.DataTableAttributes;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

/**
 * AnnotatedWebElement.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
public class AnnotatedWebElement implements WebElement {
	private WebElement baseElement;
	private DataTableAttributes fieldAnnotation;
	
	/**
	 * wraps WebElement and the associated DataTableAttributes annotation
	 * @param element
	 * @param fieldAnnotation
	 */
	public AnnotatedWebElement(WebElement element, DataTableAttributes annotation) {
		this.baseElement = element;
		this.fieldAnnotation = annotation;
	}
	
	/**
	 * Gets the real WebElement
	 * @return WebElement
	 */
	public WebElement getBaseElement() {
		return baseElement;
	}

	/**
	 * Gets the WebElement - Field annotation
	 * @return DataTableAttributes
	 */
	public DataTableAttributes getFieldAnnotation() {
		return fieldAnnotation;
	}

	@Override
	public void click() {
		baseElement.click();
	}

	@Override
	public void submit() {
		baseElement.submit();
	}

	@Override
	public void sendKeys(CharSequence... keysToSend) {
		baseElement.sendKeys(keysToSend);
	}

	@Override
	public void clear() {
		baseElement.clear();
	}

	@Override
	public String getTagName() {
		return baseElement.getTagName();
	}

	@Override
	public String getAttribute(String name) {
		return baseElement.getAttribute(name);
	}

	@Override
	public boolean isSelected() {
		return baseElement.isSelected();
	}

	@Override
	public boolean isEnabled() {
		return baseElement.isEnabled();
	}

	@Override
	public String getText() {
		return baseElement.getText();
	}

	@Override
	public List<WebElement> findElements(By findElements) {
		return baseElement.findElements(findElements);
	}

	@Override
	public WebElement findElement(By by) {
		return baseElement.findElement(by);
	}

	@Override
	public boolean isDisplayed() {
		return baseElement.isDisplayed();
	}

	@Override
	public Point getLocation() {
		return baseElement.getLocation();
	}

	@Override
	public Dimension getSize() {
		return baseElement.getSize();
	}

	@Override
	public String getCssValue(String propertyName) {
		return baseElement.getCssValue(propertyName);
	}

}
