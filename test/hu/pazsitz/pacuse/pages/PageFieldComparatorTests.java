package hu.pazsitz.pacuse.pages;

import hu.pazsitz.pacuse.pages.PageFieldComparator;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test(groups="unit")
public class PageFieldComparatorTests {
	private PageFieldComparator comparator;
	
	public PageFieldComparatorTests() {
		comparator = new PageFieldComparator() {
		};
	}
	
	@Test
	public void compareFields() {
		Assert.assertTrue(comparator.compareFields());
	}
}
