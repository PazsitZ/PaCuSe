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
 * SelectByIndexPopulatorActionTests.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
@Test(groups = "unit")
public class SelectByIndexPopulatorActionTests {
	private SelectByIndexPopulatorAction populator = new SelectByIndexPopulatorAction();
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
		Mockito.when(searchElement.getAttribute("index")).thenReturn("1");
		Mockito.when(searchElement.isSelected()).thenReturn(true);
		
	}
	
	@Test
	public void testSelectByIndexPopulatorActionValid() throws Exception {
		List<WebElement> elements = new LinkedList<>();
		elements.add(searchElement);
		Mockito.when(element.findElements(Mockito.any(By.class))).thenReturn(elements);
		
		populator.doAction(element, "1");
	}
	
	@Test
	public void testSelectByIndexPopulatorActionValidMultiple() throws Exception {
		WebElement searchElement2 = Mockito.mock(WebElement.class, Mockito.RETURNS_MOCKS);
		Mockito.when(searchElement2.getTagName()).thenReturn("option");
		Mockito.when(searchElement2.getAttribute("index")).thenReturn("3");
		Mockito.when(searchElement2.isSelected()).thenReturn(true);
		WebElement searchElement3 = Mockito.mock(WebElement.class, Mockito.RETURNS_MOCKS);
		Mockito.when(searchElement3.getTagName()).thenReturn("option");
		Mockito.when(searchElement3.getAttribute("index")).thenReturn("66");
		Mockito.when(searchElement3.isSelected()).thenReturn(true);
		List<WebElement> elements = new LinkedList<>();
		elements.add(searchElement);
		elements.add(searchElement2);
		elements.add(searchElement3);
		Mockito.when(element.findElements(Mockito.any(By.class))).thenReturn(elements);
		
		populator.doAction(element, "1,3, 66");
	}
	
	@Test(expectedExceptions = NoSuchElementException.class)
	public void testSelectByIndexPopulatorActionInValidIndex() throws Exception {
		populator.doAction(element, "5");
	}
	
	@Test(expectedExceptions = NumberFormatException.class)
	public void testSelectByIndexPopulatorActionNonNumeric() throws Exception {
		populator.doAction(element, "asd");
	}
}
