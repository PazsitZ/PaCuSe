package hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions.delegates.populator;

import java.util.LinkedList;
import java.util.List;

import hu.pazsitz.pacuse.tests.annotations.DataTableAttributes;
import hu.pazsitz.pacuse.tests.cucumber.featuretables.AnnotatedWebElement;

import org.mockito.Mockito;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * SelectByTextPopulatorActionTests.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
@Test(groups = "unit")
public class SelectByTextPopulatorActionTests {
	private static final String UNIQUE_TEXT_VALUE = "uniqueTextValue";
	private SelectByTextPopulatorAction populator = new SelectByTextPopulatorAction();
	private AnnotatedWebElement element;
	private WebElement searchElement;
	
	@BeforeMethod
	public void init() {
		element = Mockito.mock(AnnotatedWebElement.class, Mockito.RETURNS_SMART_NULLS);
		Mockito.when(element.getTagName()).thenReturn("select");
		Mockito.when(element.getAttribute("multiple")).thenReturn("true");
		DataTableAttributes annotation = Mockito.mock(DataTableAttributes.class, Mockito.RETURNS_MOCKS);
		Mockito.when(annotation.allowMultiSelect()).thenReturn(true);
		Mockito.when(element.getFieldAnnotation()).thenReturn(annotation);
		
		searchElement = Mockito.mock(WebElement.class, Mockito.RETURNS_MOCKS);
		Mockito.when(searchElement.getTagName()).thenReturn("option");
		Mockito.when(searchElement.getText()).thenReturn(UNIQUE_TEXT_VALUE);
		Mockito.when(searchElement.isSelected()).thenReturn(true);
		
	}
	
	@Test
	public void testSelectByTextPopulatorActionValid() throws Exception {
		List<WebElement> elements = new LinkedList<>();
		elements.add(searchElement);
		Mockito.when(element.findElements(Mockito.any(By.class))).thenReturn(elements);
		
		populator.doAction(element, UNIQUE_TEXT_VALUE);
	}
	
	@Test
	public void testSelectByTextPopulatorActionValidMultiple() throws Exception {
		WebElement searchElement2 = Mockito.mock(WebElement.class, Mockito.RETURNS_MOCKS);
		Mockito.when(searchElement2.getTagName()).thenReturn("option");
		Mockito.when(searchElement2.getText()).thenReturn("asdasd");
		Mockito.when(searchElement2.isSelected()).thenReturn(true);
		WebElement searchElement3 = Mockito.mock(WebElement.class, Mockito.RETURNS_MOCKS);
		Mockito.when(searchElement3.getTagName()).thenReturn("option");
		Mockito.when(searchElement3.getText()).thenReturn("xdc");
		Mockito.when(searchElement3.isSelected()).thenReturn(true);
		List<WebElement> elements = new LinkedList<>();
		elements.add(searchElement);
		elements.add(searchElement2);
		elements.add(searchElement3);
		Mockito.when(element.findElements(Mockito.any(By.class))).thenReturn(elements);
		
		populator.doAction(element, "asdasd, " + UNIQUE_TEXT_VALUE);
	}
	
	@Test(expectedExceptions = NoSuchElementException.class)
	public void testSelectByTextPopulatorActionInValidIndex() throws Exception {
		populator.doAction(element, "qwe");
	}
	
}
