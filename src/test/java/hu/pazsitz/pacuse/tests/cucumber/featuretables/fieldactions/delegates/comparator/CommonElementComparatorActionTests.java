package hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions.delegates.comparator;

import static org.testng.AssertJUnit.assertTrue;
import hu.pazsitz.pacuse.tests.cucumber.featuretables.AnnotatedWebElement;
import hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions.delegates.DelegatedActionException;

import org.mockito.Mockito;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test(groups="unit")
public class CommonElementComparatorActionTests {
	private static final String UNIQUE_TEXT_VALUE = "uniqueTextValue";
	private CommonElementComparatorAction comparator = new CommonElementComparatorAction();
	private AnnotatedWebElement element;
	
	@BeforeMethod
	public void init() {
		element = Mockito.mock(AnnotatedWebElement.class, Mockito.RETURNS_SMART_NULLS);
		Mockito.when(element.getText()).thenReturn(UNIQUE_TEXT_VALUE);
	}
	
	@Test
	public void testCommonElementcomparatorActionMatch() throws Exception {
		assertTrue(comparator.doAction(element, UNIQUE_TEXT_VALUE));
	}
	
	@Test
	public void testCommonElementcomparatorActionMatchWithWhitespaces() throws Exception {
		Mockito.when(element.getText()).thenReturn("        "+ UNIQUE_TEXT_VALUE);
		assertTrue(comparator.doAction(element, UNIQUE_TEXT_VALUE + "   "));
	}
	
	@Test
	public void testCommonElementcomparatorActionEmptyMatch() throws Exception {
		Mockito.when(element.getText()).thenReturn("");
		assertTrue(comparator.doAction(element, ""));
	}
	
	@Test(expectedExceptions=DelegatedActionException.class)
	public void testCommonElementcomparatorActionMissMatch() throws Exception {
		try {
			comparator.doAction(element, "missmatched text");
		} catch (DelegatedActionException e) {
			assertTrue(e.getMessage().contains("[" + UNIQUE_TEXT_VALUE + "]"));
			assertTrue(e.getMessage().contains("[missmatched text]"));
			
			throw e;
		}
	}
	
	@Test(expectedExceptions=DelegatedActionException.class)
	public void testCommonElementcomparatorActionEmpty() throws Exception {
		try {
			comparator.doAction(element, "");
		} catch (DelegatedActionException e) {
			assertTrue(e.getMessage().contains("[" + UNIQUE_TEXT_VALUE + "]"));
			assertTrue(e.getMessage().contains("[]"));
			
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
	
	@Test(expectedExceptions=NullPointerException.class)
	public void testCommonElementcomparatorActionNullToNullElement() throws Exception {
		comparator.doAction(null, null);
	}
	
}
