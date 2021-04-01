package hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions.delegates.populator;

import hu.pazsitz.pacuse.tests.cucumber.featuretables.AnnotatedWebElement;

import org.mockito.Mockito;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * CheckBoxTickerPopulatorActionTests.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
@Test(groups = "unit")
public class InputPopulatorActionTests {
	private static final String INPUT_STRING = "input String";
	private InputPopulatorAction populator = new InputPopulatorAction();
	private AnnotatedWebElement element;
	
	@BeforeMethod
	public void init() {
		element = Mockito.mock(AnnotatedWebElement.class, Mockito.RETURNS_SMART_NULLS);
	}
	
	@Test
	public void testInputPopulatorActionPopulate() throws Exception {
		populator.doAction(element, INPUT_STRING);
		Mockito.verify(element, Mockito.times(1)).sendKeys(INPUT_STRING);
		Mockito.verify(element, Mockito.times(1)).clear();
	}
	
	@Test
	public void testInputPopulatorActionPopulateEmpty() throws Exception {
		populator.doAction(element, "");
		Mockito.verify(element, Mockito.times(1)).sendKeys("");
		Mockito.verify(element, Mockito.times(1)).clear();
	}
	
	public void testInputPopulatorActionPopulateNull() throws Exception {
		populator.doAction(element, null);
		Mockito.verify(element, Mockito.times(0)).sendKeys("");
		Mockito.verify(element, Mockito.times(0)).clear();
	}
	
	@Test(expectedExceptions = NullPointerException.class)
	public void testInputPopulatorActionPopulateToNull() throws Exception {
		populator.doAction(null, INPUT_STRING);
		Mockito.verify(element, Mockito.times(0)).sendKeys(INPUT_STRING);
	}
}
