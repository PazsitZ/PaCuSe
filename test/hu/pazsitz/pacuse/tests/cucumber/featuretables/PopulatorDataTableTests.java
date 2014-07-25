package hu.pazsitz.pacuse.tests.cucumber.featuretables;

import hu.pazsitz.pacuse.pages.AbstractPage;
import hu.pazsitz.pacuse.tests.annotations.TableName;

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

@Test(groups = "unit")
public class PopulatorDataTableTests {

	private PopulatorDataTable table;
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
		table = new PopulatorDataTable(dataTable);
		
		buildPageObject();
	}

	private void buildPageObject() {
		page = new TestPage(null);
		WebElement element = Mockito.mock(WebElement.class, Mockito.RETURNS_MOCKS);
		Mockito.when(element.getTagName()).thenReturn("input");
		page.name = element;
		
		WebElement element2 = Mockito.mock(WebElement.class, Mockito.RETURNS_MOCKS);
		Mockito.when(element2.getTagName()).thenReturn("input");
		page.email = element2;
		
		WebElement element3 = Mockito.mock(WebElement.class, Mockito.RETURNS_MOCKS);
		Mockito.when(element3.getTagName()).thenReturn("input");
		page.address = element3;
		
		page2 = new TestPage2(null);
		WebElement element4 = Mockito.mock(WebElement.class, Mockito.RETURNS_MOCKS);
		Mockito.when(element4.getTagName()).thenReturn("input");
		page2.field = element4;
	}
	
	@Test
	public void testPopulateToPageModel() {
		FieldActionResult result = table.populateToPageModel(page);
		Assert.assertFalse(result.hasFailed(), result.getFailedFields().toString());
		Assert.assertFalse(result.fullSuccess(), result.getFailedFields().toString());
		Assert.assertTrue(result.success(), result.getFailedFields().toString());
		Assert.assertTrue(result.hasNonDeterined(), result.getNonDeterminedFields().toString());
		Mockito.verify(page.name, Mockito.times(1)).clear();
		Mockito.verify(page.name, Mockito.times(1)).sendKeys("testName");
		Mockito.verify(page.email, Mockito.times(1)).clear();
		Mockito.verify(page.email, Mockito.times(1)).sendKeys("email@email.com");
		Mockito.verify(page.address, Mockito.times(0)).clear();
		Mockito.verify(page.address, Mockito.times(0)).sendKeys("addressValue 123");
		
		FieldActionResult result2 = table.populateToPageModel(page2);
		Assert.assertTrue(result2.fullSuccess(),result2.getFailedFields().toString());
		Assert.assertTrue(result2.success(), result2.getFailedFields().toString());
		Mockito.verify(page2.field, Mockito.times(1)).clear();
		Mockito.verify(page2.field, Mockito.times(1)).sendKeys("asdasd");
	}
	
	private class TestPage extends AbstractPage {
		@TableName(name="name") public WebElement name;
		@TableName(name="email") public WebElement email;
		@TableName(name="address") public WebElement address;
		public TestPage(WebDriver webDriver) { super(webDriver); }
		@Override public String getUrl() { return pageUrl; }
	}
	
	private class TestPage2 extends AbstractPage {
		@TableName(name="non existent field") public WebElement field;
		public TestPage2(WebDriver webDriver) { super(webDriver); }
		@Override public String getUrl() { return pageUrl; }
	}
}
