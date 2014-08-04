package hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions.delegates.comparator;

import static org.testng.AssertJUnit.assertTrue;
import hu.pazsitz.pacuse.tests.annotations.DataTableAttributes;
import hu.pazsitz.pacuse.tests.cucumber.featuretables.AnnotatedWebElement;
import hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions.delegates.DelegatedActionException;

import org.mockito.Mockito;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test(groups="unit")
public class CustomAttributeComparatorActionTests {
	private static final String DATA_ATTRIBUTE = "data-uniqueData";
	private static final String UNIQUE_VALUE = "uniqueTextValue";
	private CustomAttributeComparatorAction comparator = new CustomAttributeComparatorAction();
	private AnnotatedWebElement element;
	private DataTableAttributes attribute;
	
	@BeforeMethod
	public void init() {
		element = Mockito.mock(AnnotatedWebElement.class, Mockito.RETURNS_SMART_NULLS);
		Mockito.when(element.getAttribute(DATA_ATTRIBUTE)).thenReturn(UNIQUE_VALUE);
		
		attribute = Mockito.mock(DataTableAttributes.class, Mockito.RETURNS_MOCKS);
		Mockito.when(attribute.attribute()).thenReturn(DATA_ATTRIBUTE);
		Mockito.when(element.getFieldAnnotation()).thenReturn(attribute);
	}
	
	@Test
	public void testCustomAttributeComparatorActionMatch() throws Exception {
		Mockito.when(element.getAttribute(DATA_ATTRIBUTE)).thenReturn(UNIQUE_VALUE);
		assertTrue(comparator.doAction(element, UNIQUE_VALUE));
	}
	
	@Test
	public void testCustomAttributeComparatorActionMatchEmpty() throws Exception {
		Mockito.when(element.getAttribute(DATA_ATTRIBUTE)).thenReturn("");
		assertTrue(comparator.doAction(element, ""));
	}
	
	@Test(expectedExceptions=DelegatedActionException.class)
	public void testCustomAttributeComparatorActionMissMatch() throws Exception {
		Mockito.when(element.getAttribute(DATA_ATTRIBUTE)).thenReturn("different value");
		
		try {
			comparator.doAction(element, UNIQUE_VALUE);
		} catch (DelegatedActionException e) {
			assertTrue(e.getMessage().contains("[" + UNIQUE_VALUE + "]"));
			assertTrue(e.getMessage().contains("[different value]"));
			
			throw e;
		}
	}
	
	@Test(expectedExceptions=NullPointerException.class)
	public void testCustomAttributeComparatorActionNull() throws Exception {
		comparator.doAction(element, null);
	}
	
	@Test(expectedExceptions=NullPointerException.class)
	public void testCustomAttributeComparatorActionNullElement() throws Exception {
		comparator.doAction(null, UNIQUE_VALUE);
	}
	
	@Test(expectedExceptions=NullPointerException.class)
	public void testCustomAttributeComparatorActionNullToNullElement() throws Exception {
		comparator.doAction(null, null);
	}
}
