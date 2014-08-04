package hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions.delegates.comparator;

import static org.testng.AssertJUnit.assertTrue;
import hu.pazsitz.pacuse.tests.cucumber.featuretables.AnnotatedWebElement;
import hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions.delegates.DelegatedActionException;

import org.mockito.Mockito;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class InputCommonComparatorActionTests {
	private static final String UNIQUE_TEXT_VALUE = "uniqueTextValue";
	private InputCommonComparatorAction comparator = new InputCommonComparatorAction();
	private AnnotatedWebElement element;
	
	@BeforeMethod
	public void init() {
		element = Mockito.mock(AnnotatedWebElement.class, Mockito.RETURNS_SMART_NULLS);
		Mockito.when(element.getAttribute("value")).thenReturn(UNIQUE_TEXT_VALUE);
	}
	
	@Test
	public void testInputCommonComparatorActionMatch() throws Exception {
		assertTrue(comparator.doAction(element, UNIQUE_TEXT_VALUE));
	}
	
	@Test
	public void testInputCommonComparatorActionMatchEmpty() throws Exception {
		Mockito.when(element.getAttribute("value")).thenReturn("");
		assertTrue(comparator.doAction(element, ""));
	}
	
	@Test(expectedExceptions=DelegatedActionException.class)
	public void testInputCommonComparatorActionMissMatch() throws Exception {
		try {
			comparator.doAction(element, "missmatched text");
		} catch (DelegatedActionException e) {
			assertTrue(e.getMessage().contains("[" + UNIQUE_TEXT_VALUE + "]"));
			assertTrue(e.getMessage().contains("[missmatched text]"));
			
			throw e;
		}
	}
	
	@Test(expectedExceptions=NullPointerException.class)
	public void testCommonElementcomparatorActionNull() throws Exception {
		comparator.doAction(element, null);
	}
	
	@Test(expectedExceptions=NullPointerException.class)
	public void testCommonElementcomparatorActionNullElement() throws Exception {
		comparator.doAction(null, UNIQUE_TEXT_VALUE);
	}
	
}
