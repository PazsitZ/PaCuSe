package hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions.delegates.comparator;

import hu.pazsitz.pacuse.tests.annotations.DataTableAttributes;
import hu.pazsitz.pacuse.tests.cucumber.featuretables.AnnotatedWebElement;
import hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions.delegates.DelegatedActionException;

import java.util.LinkedList;
import java.util.List;

import org.mockito.Mockito;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SelectByValueComparatorActionTests {
private AnnotatedWebElement element;
	
	private WebElement searchElement;
	private WebElement element1;
	private WebElement element2;
	
	@BeforeMethod
	public void init() {
		DataTableAttributes annotation = Mockito.mock(DataTableAttributes.class, Mockito.RETURNS_MOCKS);
		Mockito.when(annotation.allowMultiSelect()).thenReturn(true);
		element = Mockito.mock(AnnotatedWebElement.class, Mockito.RETURNS_MOCKS);
		Mockito.when(element.getTagName()).thenReturn("select");
		Mockito.when(element.getAttribute("multiple")).thenReturn("true");
		Mockito.when(element.getFieldAnnotation()).thenReturn(annotation);
		
		searchElement = Mockito.mock(WebElement.class, Mockito.RETURNS_MOCKS);
		Mockito.when(searchElement.getTagName()).thenReturn("option");
		Mockito.when(searchElement.getAttribute("value")).thenReturn("found");
		Mockito.when(searchElement.isSelected()).thenReturn(true);
		element1 = Mockito.mock(WebElement.class, Mockito.RETURNS_MOCKS);
		Mockito.when(element1.getTagName()).thenReturn("option");
		Mockito.when(element1.getAttribute("value")).thenReturn("asd");
		Mockito.when(element1.isSelected()).thenReturn(true);
		element2 = Mockito.mock(WebElement.class, Mockito.RETURNS_MOCKS);
		Mockito.when(element2.getTagName()).thenReturn("option");
		Mockito.when(element2.getAttribute("value")).thenReturn("dfg");
		Mockito.when(element2.isSelected()).thenReturn(true);
	}
	
	@Test
	public void testComparatorInTheMiddle() throws Exception {
		SelectByValueComparatorAction action = new SelectByValueComparatorAction();
		
		List<WebElement> elements = new LinkedList<>();
		elements.add(element1);
		elements.add(searchElement);
		elements.add(element2);
		Mockito.when(element.findElements(Mockito.any(By.class))).thenReturn(elements);
		String value = "found";
		Assert.assertTrue(action.doAction(element, value));
	}
	
	@Test
	public void testComparatorInTheBeginning() throws Exception {
		SelectByValueComparatorAction action = new SelectByValueComparatorAction();
		
		List<WebElement> elements = new LinkedList<>();
		elements.add(searchElement);
		elements.add(element1);
		elements.add(element2);
		Mockito.when(element.findElements(Mockito.any(By.class))).thenReturn(elements);
		String value = "found";
		Assert.assertTrue(action.doAction(element, value));
	}
	
	@Test
	public void testComparatorInTheEnd() throws Exception {
		SelectByValueComparatorAction action = new SelectByValueComparatorAction();
		
		List<WebElement> elements = new LinkedList<>();
		elements.add(element1);
		elements.add(element2);
		elements.add(searchElement);
		Mockito.when(element.findElements(Mockito.any(By.class))).thenReturn(elements);
		String value = "found";
		Assert.assertTrue(action.doAction(element, value));
	}
	
	@Test
	public void testComparatorMatchSingleValue() throws Exception {
		SelectByValueComparatorAction action = new SelectByValueComparatorAction();
		
		List<WebElement> elements = new LinkedList<>();
		elements.add(searchElement);
		Mockito.when(element.findElements(Mockito.any(By.class))).thenReturn(elements);
		String value = "found";
		Assert.assertTrue(action.doAction(element, value));
	}
	
	@Test(expectedExceptions=DelegatedActionException.class)
	public void testComparatorNoMatchSingleValue() throws Exception {
		SelectByValueComparatorAction action = new SelectByValueComparatorAction();
		
		List<WebElement> elements = new LinkedList<>();
		elements.add(element1);
		Mockito.when(element.findElements(Mockito.any(By.class))).thenReturn(elements);
		String value = "found";
		Assert.assertFalse(action.doAction(element, value));
	}
	
	@Test(expectedExceptions=DelegatedActionException.class)
	public void testComparatorNoMatchMultiValue() throws Exception {
		SelectByValueComparatorAction action = new SelectByValueComparatorAction();
		
		List<WebElement> elements = new LinkedList<>();
		elements.add(element1);
		elements.add(element2);
		Mockito.when(element.findElements(Mockito.any(By.class))).thenReturn(elements);
		String value = "found";
		Assert.assertFalse(action.doAction(element, value));
	}
	
	@Test(expectedExceptions=DelegatedActionException.class)
	public void testComparatorNonSelected() throws Exception {
		SelectByValueComparatorAction action = new SelectByValueComparatorAction();
		
		Mockito.when(element.findElements(Mockito.any(By.class))).thenReturn(new LinkedList<WebElement>());
		String value = "found";
		Assert.assertFalse(action.doAction(element, value));
	}
}
