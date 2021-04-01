package hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions.delegates.populator;

import hu.pazsitz.pacuse.tests.cucumber.featuretables.AnnotatedWebElement;

import org.mockito.Mockito;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test(groups = "unit")
public class ButtonPopulatorActionTests {
	private ButtonPopulatorAction populator = new ButtonPopulatorAction();
	private AnnotatedWebElement element;
	
	@BeforeMethod
	public void init() {
		element = Mockito.mock(AnnotatedWebElement.class, Mockito.RETURNS_SMART_NULLS);
	}
	
	@Test
	public void testButtonPopulatorAction() throws Exception {
		populator.doAction(element, "true");
		Mockito.verify(element, Mockito.times(1)).click();
	}
	
	@Test
	public void testButtonPopulatorActionOnEmpty() throws Exception {
		populator.doAction(element, "");
		Mockito.verify(element, Mockito.times(1)).click();
	}
	
	@Test
	public void testButtonPopulatorActionOnFalse() throws Exception {
		populator.doAction(element, "false");
		Mockito.verify(element, Mockito.times(1)).click();
	}
	
}
