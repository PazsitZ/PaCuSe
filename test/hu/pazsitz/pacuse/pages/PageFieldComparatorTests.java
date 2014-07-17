package hu.pazsitz.pacuse.pages;

import hu.pazsitz.pacuse.tests.cucumber.featuretables.PageFieldTableMapper;
import hu.pazsitz.pacuse.tests.cucumber.featuretables.mapperactions.ComparatorAction;

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
		Assert.assertTrue(comparator.mapFields(null, null, null));
	}
}
