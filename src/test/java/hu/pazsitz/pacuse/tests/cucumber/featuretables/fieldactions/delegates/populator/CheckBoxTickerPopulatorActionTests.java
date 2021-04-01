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
public class CheckBoxTickerPopulatorActionTests {
	private CheckBoxTickerPopulatorAction populator = new CheckBoxTickerPopulatorAction();
	private AnnotatedWebElement element;
	
	@BeforeMethod
	public void init() {
		element = Mockito.mock(AnnotatedWebElement.class, Mockito.RETURNS_SMART_NULLS);
	}
	
	@Test
	public void testCheckBoxtickerPopulatorActionClickIn() throws Exception {
		Mockito.when(element.isSelected()).thenReturn(false);
		populator.doAction(element, "true");
		Mockito.verify(element, Mockito.times(1)).click();
	}
	
	@Test
	public void testCheckBoxtickerPopulatorActionClickIn2() throws Exception {
		Mockito.when(element.isSelected()).thenReturn(false);
		populator.doAction(element, "yes");
		Mockito.verify(element, Mockito.times(1)).click();
	}

	@Test
	public void testCheckBoxtickerPopulatorActionClickOut() throws Exception {
		Mockito.when(element.isSelected()).thenReturn(true);
		populator.doAction(element, "false");
		Mockito.verify(element, Mockito.times(1)).click();
	}
	
	@Test
	public void testCheckBoxtickerPopulatorActionClickOut2() throws Exception {
		Mockito.when(element.isSelected()).thenReturn(true);
		populator.doAction(element, "asd");
		Mockito.verify(element, Mockito.times(1)).click();
	}
	
	@Test
	public void testCheckBoxtickerPopulatorActionClickInACheckedOne() throws Exception {
		Mockito.when(element.isSelected()).thenReturn(true);
		populator.doAction(element, "yes");
		Mockito.verify(element, Mockito.times(0)).click();
	}
	
	@Test
	public void testCheckBoxtickerPopulatorActionClickOutANonCheckedOne() throws Exception {
		Mockito.when(element.isSelected()).thenReturn(false);
		populator.doAction(element, "");
		Mockito.verify(element, Mockito.times(0)).click();
	}
}
