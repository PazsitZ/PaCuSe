package hu.pazsitz.pacuse.tests.cucumber.featuretables;

import hu.pazsitz.pacuse.pages.AbstractPage;
import hu.pazsitz.pacuse.tests.annotations.DataTableAttributes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mockito.Mockito;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * ComparatorDataTableTests.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
@Test(groups = "unit")
public class ComparatorDataTableTests {

	private ComparatorDataTable table;
	private TestPage page;
	private TestPage2 page2;
	
	@BeforeMethod
	public void init() {
		Map<String, String> map = new HashMap<>();
		map.put("name", "testName");
		map.put("email", "email@email.com");
		map.put("non existent field", "asdasd");
		List<Map<String, String>> dataTable = new ArrayList<>();
		dataTable.add(map);
		table = new ComparatorDataTable(dataTable);
		
		buildPageObject();
	}

	private void buildPageObject() {
		page = new TestPage(null);
		WebElement element = Mockito.mock(WebElement.class, Mockito.RETURNS_MOCKS);
		Mockito.when(element.getTagName()).thenReturn("input");
		Mockito.when(element.getAttribute("value")).thenReturn("testName");
		page.name = element;
		
		WebElement element2 = Mockito.mock(WebElement.class, Mockito.RETURNS_MOCKS);
		Mockito.when(element2.getTagName()).thenReturn("input");
		Mockito.when(element2.getAttribute("value")).thenReturn("email@email.com");
		page.email = element2;
		
		WebElement element3 = Mockito.mock(WebElement.class, Mockito.RETURNS_MOCKS);
		Mockito.when(element3.getTagName()).thenReturn("input");
		Mockito.when(element3.getAttribute("value")).thenReturn("addressValue 123");
		page.address = element3;
		
		page2 = new TestPage2(null);
		WebElement element4 = Mockito.mock(WebElement.class, Mockito.RETURNS_MOCKS);
		Mockito.when(element4.getTagName()).thenReturn("input");
		Mockito.when(element4.getAttribute("value")).thenReturn("asdasd");
		page2.field = element4;
	}
	
	@Test
	public void testCompareToPageModel() {
		FieldActionResult result = table.compareToPageModel(page);
		Assert.assertFalse(result.hasFailed(), result.getFailedFields().toString());
		Assert.assertFalse(result.fullSuccess(), result.getFailedFields().toString());
		Assert.assertTrue(result.success(), result.getFailedFields().toString());
		Assert.assertTrue(result.hasNonDeterined(), result.getNonDeterminedFields().toString());
		
		FieldActionResult result2 = table.compareToPageModel(page2);
		Assert.assertTrue(result2.fullSuccess(),result2.getFailedFields().toString());
		Assert.assertTrue(result2.success(), result2.getFailedFields().toString());
	}
	
	private class TestPage extends AbstractPage {
		@DataTableAttributes(name="name") public WebElement name;
		@DataTableAttributes(name="email") public WebElement email;
		@DataTableAttributes(name="address") public WebElement address;
		public TestPage(WebDriver webDriver) { super(webDriver); }
		@Override public String getUrl() { return pageUrl; }
	}
	
	private class TestPage2 extends AbstractPage {
		@DataTableAttributes(name="non existent field") public WebElement field;
		public TestPage2(WebDriver webDriver) { super(webDriver); }
		@Override public String getUrl() { return pageUrl; }
	}
}
