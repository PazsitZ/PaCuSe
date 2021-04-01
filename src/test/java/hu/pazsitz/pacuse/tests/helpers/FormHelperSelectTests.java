package hu.pazsitz.pacuse.tests.helpers;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * FormHelperSelectTests.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
@Test(groups = "unit")
public class FormHelperSelectTests {
	@Mock(answer=Answers.RETURNS_DEFAULTS)
	private WebElement element;
	@Mock(answer=Answers.RETURNS_DEFAULTS)
	private WebElement option;
	
	@BeforeMethod
	public void init() {
		MockitoAnnotations.initMocks(this);
		
		when(element.getTagName()).thenReturn("select");
		when(element.getAttribute("multiple")).thenReturn(null);
		
		List<WebElement> options = new ArrayList<>();
		options.add(option);
		when(element.findElements(Mockito.any(By.class))).thenReturn(options);
	}
	
	/**
	 * Select.selectByValue concats and dymanically looking after the element
	 * so the test is asserts only the click by the overrided element.findElements call
	 */
	@Test
	public void testSelectByValueDummyTest() {
		FormHelper.selectByValue(element, "asd");
		verify(option, atLeast(1)).isSelected();
		verify(option, times(1)).click();
		
	}
	
	@Test
	public void testSelectByValueWithNull() {
		FormHelper.selectByValue(element, null);
		verify(option, times(0)).isSelected();
		verify(option, times(0)).click();
		
	}
	
	@Test
	public void testSelectByIndex() {
		when(option.getAttribute("index")).thenReturn("19");
		
		FormHelper.selectByIndex(element, 19);
		verify(option, atLeast(1)).isSelected();
		verify(option, times(1)).click();
	}
	
	@Test(expectedExceptions = NoSuchElementException.class)
	public void testSelectByIndexWithException() {
		when(option.getAttribute("index")).thenReturn("19");
		
		FormHelper.selectByIndex(element, 1);
	}
	
	/**
	 * Select.selectByValue concats and dymanically looking after the element
	 * so the test is asserts only the click by the overrided element.findElements call
	 */
	@Test
	public void testSelectByTextDummyTest() {
		FormHelper.selectByText(element, "selectable element");
		verify(option, atLeast(1)).isSelected();
		verify(option, times(1)).click();
	}
	
	@Test
	public void testSelectByTextWithNull() {
		FormHelper.selectByText(element, null);
		verify(option, times(0)).isSelected();
		verify(option, times(0)).click();
	}
	
}
