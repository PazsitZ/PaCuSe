package hu.pazsitz.pacuse.pages;

import hu.pazsitz.pacuse.tests.cucumber.featuretables.PageFieldTableMapper;

import org.testng.annotations.Test;

/**
 * PageFieldComparatorTests.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
@Test(groups="unit")
public class PageFieldComparatorTests {
	private PageFieldTableMapper comparator;
	
	public PageFieldComparatorTests() {
		comparator = new PageFieldTableMapper();
	}
	
	@Test
	public void testMapFields() {
//		Assert.assertNull(comparator.mapFields(null, null, null));
	}
}
