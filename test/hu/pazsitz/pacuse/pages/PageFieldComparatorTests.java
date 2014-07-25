package hu.pazsitz.pacuse.pages;

import hu.pazsitz.pacuse.tests.cucumber.featuretables.PageFieldTableMapper;
import hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions.ComparatorAction;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test(groups="unit")
public class PageFieldComparatorTests {
	private PageFieldTableMapper comparator;
	
	public PageFieldComparatorTests() {
		comparator = new PageFieldTableMapper(new ComparatorAction());
	}
	
	@Test
	public void testMapFields() {
//		Assert.assertNull(comparator.mapFields(null, null, null));
	}
}
