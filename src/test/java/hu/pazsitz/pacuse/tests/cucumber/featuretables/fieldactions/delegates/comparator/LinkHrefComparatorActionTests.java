package hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions.delegates.comparator;

import static org.testng.AssertJUnit.assertTrue;
import hu.pazsitz.pacuse.tests.cucumber.featuretables.AnnotatedWebElement;
import hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions.delegates.DelegatedActionException;

import org.mockito.Mockito;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LinkHrefComparatorActionTests {
	private static final String UNIQUE_LINK_VALUE = "http://uniqueLinkValue.com";
	private LinkHrefComparatorAction comparator = new LinkHrefComparatorAction();
	private AnnotatedWebElement element;
	
	@BeforeMethod
	public void init() {
		element = Mockito.mock(AnnotatedWebElement.class, Mockito.RETURNS_SMART_NULLS);
		Mockito.when(element.getAttribute("href")).thenReturn(UNIQUE_LINK_VALUE);
	}
	
	@Test
	public void testInputCommonComparatorActionMatch() throws Exception {
		assertTrue(comparator.doAction(element, UNIQUE_LINK_VALUE));
	}
	
	@Test
	public void testInputCommonComparatorActionMatchEmpty() throws Exception {
		Mockito.when(element.getAttribute("href")).thenReturn("");
		assertTrue(comparator.doAction(element, ""));
	}
	
	@Test(expectedExceptions=DelegatedActionException.class)
	public void testInputCommonComparatorActionMissMatch() throws Exception {
		try {
			comparator.doAction(element, "http://missmatchedlink");
		} catch (DelegatedActionException e) {
			assertTrue(e.getMessage().contains("[" + UNIQUE_LINK_VALUE + "]"));
			assertTrue(e.getMessage().contains("[http://missmatchedlink]"));
			
			throw e;
		}
	}
	
	@Test(expectedExceptions=NullPointerException.class)
	public void testCommonElementcomparatorActionNull() throws Exception {
		comparator.doAction(element, null);
	}
	
	@Test(expectedExceptions=NullPointerException.class)
	public void testCommonElementcomparatorActionNullElement() throws Exception {
		comparator.doAction(null, UNIQUE_LINK_VALUE);
	}
}
