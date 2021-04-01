package hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions.delegates.comparator;

import static org.testng.AssertJUnit.assertTrue;
import hu.pazsitz.pacuse.tests.cucumber.featuretables.AnnotatedWebElement;
import hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions.delegates.DelegatedActionException;

import org.mockito.Mockito;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class InputCheckboxComparatorActionTests {
	private InputCheckboxComparatorAction comparator = new InputCheckboxComparatorAction();
	private AnnotatedWebElement element;
	
	@BeforeMethod
	public void init() {
		element = Mockito.mock(AnnotatedWebElement.class, Mockito.RETURNS_SMART_NULLS);
		Mockito.when(element.isSelected()).thenReturn(true);
	}
	
	@Test
	public void testInputCheckboxComparatorActionMatch() throws Exception {
		assertTrue(comparator.doAction(element, "true"));
	}
	
	@Test(expectedExceptions=DelegatedActionException.class)
	public void testInputCheckboxComparatorActionMissMatch() throws Exception {
		try {
			comparator.doAction(element, "false");
		} catch (DelegatedActionException e) {
			assertTrue(e.getMessage().contains("[true]"));
			assertTrue(e.getMessage().contains("[false]"));
			
			throw e;
		}
	}
	
	@Test(expectedExceptions=DelegatedActionException.class)
	public void testInputCheckboxComparatorActionMissMatch2() throws Exception {
		try {
			comparator.doAction(element, "asdasd");
		} catch (DelegatedActionException e) {
			assertTrue(e.getMessage().contains("[true]"));
			assertTrue(e.getMessage().contains("[false]"));
			
			throw e;
		}
	}
	
	@Test(expectedExceptions=DelegatedActionException.class)
	public void testInputCheckboxComparatorActionMissMatch3() throws Exception {
		try {
			comparator.doAction(element, "");
		} catch (DelegatedActionException e) {
			assertTrue(e.getMessage().contains("[true]"));
			assertTrue(e.getMessage().contains("[false]"));
			
			throw e;
		}
	}
	
	@Test(expectedExceptions=DelegatedActionException.class)
	public void testCommonElementcomparatorActionMissMatch() throws Exception {
		try {
			comparator.doAction(element, "missmatched text");
		} catch (DelegatedActionException e) {
			assertTrue(e.getMessage().contains("[true]"));
			assertTrue(e.getMessage().contains("[false]"));
			
			throw e;
		}
	}
}
