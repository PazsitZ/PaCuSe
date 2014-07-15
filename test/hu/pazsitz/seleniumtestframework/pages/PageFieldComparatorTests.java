package hu.pazsitz.seleniumtestframework.pages;

import hu.pazsitz.seleniumtestframework.pages.PageFieldComparator;

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
