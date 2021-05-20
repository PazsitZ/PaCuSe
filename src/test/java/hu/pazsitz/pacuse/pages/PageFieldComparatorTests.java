package hu.pazsitz.pacuse.pages;

import hu.pazsitz.pacuse.tests.annotations.DataTableAttributes;
import hu.pazsitz.pacuse.tests.cucumber.featuretables.AnnotatedWebElement;
import hu.pazsitz.pacuse.tests.cucumber.featuretables.PageFieldTableMapper;

import hu.pazsitz.pacuse.tests.cucumber.featuretables.PopulatorDataTableTests;
import org.mockito.Mockito;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Map;

/**
 * PageFieldComparatorTests.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
@Test(groups="unit")
public class PageFieldComparatorTests {
	private PageFieldTableMapper comparator;
	private PageFieldComparatorTests.TestPage page;

	public PageFieldComparatorTests() {
		comparator = new PageFieldTableMapper();
	}


	@Test
	public void testMapFields() {
		buildPageObject();
		Map<String, AnnotatedWebElement> result = comparator.mapFields(page, Arrays.asList("name", "email", "address"));
		Assert.assertEquals(result.get("name").getBaseElement(), page.name);
		Assert.assertEquals(result.get("email").getBaseElement(), page.email);
		Assert.assertEquals(result.get("address").getBaseElement(), page.address);
	}

	@Test
	public void testMapFieldsNonExisting() {
		buildPageObject();
		Map<String, AnnotatedWebElement> result = comparator.mapFields(page, Arrays.asList("nonExistingfield"));
		Assert.assertNull(result.get("nonExistingfield"));
	}

	private void buildPageObject() {
		page = new PageFieldComparatorTests.TestPage(null);
		WebElement element = Mockito.mock(WebElement.class, Mockito.RETURNS_MOCKS);
		Mockito.when(element.getTagName()).thenReturn("input");
		page.name = element;

		WebElement element2 = Mockito.mock(WebElement.class, Mockito.RETURNS_MOCKS);
		Mockito.when(element2.getTagName()).thenReturn("input");
		page.email = element2;

		WebElement element3 = Mockito.mock(WebElement.class, Mockito.RETURNS_MOCKS);
		Mockito.when(element3.getTagName()).thenReturn("input");
		page.address = element3;
	}

	private class TestPage extends AbstractPage {
		@DataTableAttributes(name="name") public WebElement name;
		@DataTableAttributes(name="email") public WebElement email;
		@DataTableAttributes(name="address") public WebElement address;
		public TestPage(WebDriver webDriver) { super(webDriver); }
		@Override public String getUrl() { return pageUrl; }
	}

}
