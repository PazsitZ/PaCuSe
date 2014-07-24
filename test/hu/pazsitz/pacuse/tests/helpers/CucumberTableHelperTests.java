package hu.pazsitz.pacuse.tests.helpers;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test(groups = "unit")
public class CucumberTableHelperTests {
	@Mock(answer=Answers.RETURNS_DEFAULTS)
	private WebElement element;
	
	@BeforeMethod
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	
	@Test
	public void testCheckboxTickerNotSelectedElementWithYes() {
		when(element.isSelected()).thenReturn(false);
		CucumberTableHelper.checkboxTicker(element, "yes");
		verify(element, atLeast(1)).isSelected();
		verify(element, times(1)).click();
	}
	@Test
	public void testCheckboxTickerNotSelectedElementWithTrue() {	
		when(element.isSelected()).thenReturn(false);
		CucumberTableHelper.checkboxTicker(element, "true");
		verify(element, atLeast(1)).isSelected();
		verify(element, times(1)).click();
	}
	@Test
	public void testCheckboxTickerNotSelectedElementWithNo() {		
		when(element.isSelected()).thenReturn(false);
		CucumberTableHelper.checkboxTicker(element, "no");
		verify(element, atLeast(1)).isSelected();
		verify(element, times(0)).click();
	}
	@Test
	public void testCheckboxTickerNotSelectedElementWithDummy() {
		when(element.isSelected()).thenReturn(false);
		CucumberTableHelper.checkboxTicker(element, "-");
		verify(element, atLeast(1)).isSelected();
		verify(element, times(0)).click();
	}
	@Test
	public void testCheckboxTickerNotSelectedElementWithNull() {
		when(element.isSelected()).thenReturn(false);
		CucumberTableHelper.checkboxTicker(element, null);
		verify(element, atLeast(1)).isSelected();
		verify(element, times(0)).click();
	}
	
	@Test
	public void testCheckboxTickerSelectedElementWith() {
		when(element.isSelected()).thenReturn(true);
		CucumberTableHelper.checkboxTicker(element, "yes");
		verify(element, atLeast(1)).isSelected();
		verify(element, times(0)).click();
	}
	@Test
	public void testCheckboxTickerSelectedElementWithTrue() {
		when(element.isSelected()).thenReturn(true);
		CucumberTableHelper.checkboxTicker(element, "true");
		verify(element, atLeast(1)).isSelected();
		verify(element, times(0)).click();
	}
	@Test
	public void testCheckboxTickerSelectedElementWithFalse() {
		when(element.isSelected()).thenReturn(true);
		CucumberTableHelper.checkboxTicker(element, "false");
		verify(element, atLeast(1)).isSelected();
		verify(element, times(1)).click();
	}
	@Test
	public void testCheckboxTickerSelectedElementWithDummy() {
		when(element.isSelected()).thenReturn(true);
		CucumberTableHelper.checkboxTicker(element, "asd");
		verify(element, atLeast(1)).isSelected();
		verify(element, times(1)).click();
	}
	@Test
	public void testCheckboxTickerSelectedElementWithNull() {
		when(element.isSelected()).thenReturn(true);
		CucumberTableHelper.checkboxTicker(element, null);
		verify(element, atLeast(1)).isSelected();
		verify(element, times(1)).click();
	}
	
	@Test
	public void testInputFillOverWrite() {
		String assertValue = "input String";
		
		CucumberTableHelper.inputFillOverWrite(element, assertValue);
		verify(element, times(1)).clear();
		verify(element, times(1)).sendKeys(assertValue);
	}
	
	@Test
	public void testInputFillOverWriteWithNull() {
		String assertValue = null;
		
		CucumberTableHelper.inputFillOverWrite(element, assertValue);
		verify(element, times(0)).clear();
		verify(element, times(0)).sendKeys(assertValue);
	}
	
	@Test
	public void testGetCheckboxBoolValue() {
		Assert.assertTrue(CucumberTableHelper.getCheckboxBoolValue("yes"));
		Assert.assertTrue(CucumberTableHelper.getCheckboxBoolValue("true"));
		Assert.assertFalse(CucumberTableHelper.getCheckboxBoolValue("no"));
		Assert.assertFalse(CucumberTableHelper.getCheckboxBoolValue("false"));
		Assert.assertFalse(CucumberTableHelper.getCheckboxBoolValue(""));
		Assert.assertFalse(CucumberTableHelper.getCheckboxBoolValue(null));
	}
	
	
	
}
