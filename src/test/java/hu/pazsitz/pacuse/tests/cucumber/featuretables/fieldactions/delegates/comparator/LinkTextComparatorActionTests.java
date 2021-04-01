package hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions.delegates.comparator;

import static org.testng.AssertJUnit.assertTrue;
import hu.pazsitz.pacuse.tests.cucumber.featuretables.AnnotatedWebElement;
import hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions.delegates.DelegatedActionException;

import org.mockito.Mockito;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LinkTextComparatorActionTests {
	private static final String UNIQUE_LINK_TEXT_VALUE = "uniqueLinkTextValue";
	private LinkTextComparatorAction comparator = new LinkTextComparatorAction();
	private AnnotatedWebElement element;
	
	@BeforeMethod
	public void init() {
		element = Mockito.mock(AnnotatedWebElement.class, Mockito.RETURNS_SMART_NULLS);
		Mockito.when(element.getText()).thenReturn(UNIQUE_LINK_TEXT_VALUE);
	}
	
	@Test
	public void testLinkTextComparatorActionMatch() throws Exception {
		assertTrue(comparator.doAction(element, UNIQUE_LINK_TEXT_VALUE));
	}
	
	@Test
	public void testLinkTextComparatorActionMatchWithWhitespaces() throws Exception {
		Mockito.when(element.getText()).thenReturn("        "+ UNIQUE_LINK_TEXT_VALUE);
		assertTrue(comparator.doAction(element, UNIQUE_LINK_TEXT_VALUE + "   "));
	}
	
	@Test
	public void testLinkTextComparatorActionEmptyMatch() throws Exception {
		Mockito.when(element.getText()).thenReturn("");
		assertTrue(comparator.doAction(element, ""));
	}
	
	@Test(expectedExceptions=DelegatedActionException.class)
	public void testLinkTextComparatorActionMissMatch() throws Exception {
		try {
			comparator.doAction(element, "missmatched text");
		} catch (DelegatedActionException e) {
			assertTrue(e.getMessage().contains("[" + UNIQUE_LINK_TEXT_VALUE + "]"));
			assertTrue(e.getMessage().contains("[missmatched text]"));
			
			throw e;
		}
	}
	
	@Test(expectedExceptions=DelegatedActionException.class)
	public void testLinkTextComparatorActionEmpty() throws Exception {
		try {
			comparator.doAction(element, "");
		} catch (DelegatedActionException e) {
			assertTrue(e.getMessage().contains("[" + UNIQUE_LINK_TEXT_VALUE + "]"));
			assertTrue(e.getMessage().contains("[]"));
			
			throw e;
		}
	}
	
	@Test(expectedExceptions=NullPointerException.class)
	public void testLinkTextComparatorActionNull() throws Exception {
		comparator.doAction(element, null);
	}
	
	@Test(expectedExceptions=NullPointerException.class)
	public void testLinkTextComparatorActionNullElement() throws Exception {
		comparator.doAction(null, UNIQUE_LINK_TEXT_VALUE);
	}
	
	@Test(expectedExceptions=NullPointerException.class)
	public void testLinkTextComparatorActionNullToNullElement() throws Exception {
		comparator.doAction(null, null);
	}
}
