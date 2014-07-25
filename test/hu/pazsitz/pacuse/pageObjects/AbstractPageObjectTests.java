package hu.pazsitz.pacuse.pageObjects;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import hu.pazsitz.pacuse.pages.AbstractPage;
import hu.pazsitz.pacuse.pages.GeneralPageImpl;

import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * AbstractPageObjectTests.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
@Test(groups = "unit")
public class AbstractPageObjectTests {
	@Mock(answer=Answers.RETURNS_DEFAULTS)
	private WebDriver driver;
	private AbstractPage page;
	private AbstractPageObject<AbstractPage> pageObject;
	
	@BeforeMethod
	public void init() {
		MockitoAnnotations.initMocks(this);
		
		page = new GeneralPageImpl(driver);
		pageObject = spy(new AbstractPageObject(page.getClass(), driver) {});
	}
	
	@Test
	public void testInstantiatePage() {
		pageObject.loadPage();
		
		verify(driver, times(1)).get(page.getUrl());
	}
	
	@Test
	public void testGetPage() {
		AbstractPage pageObjectPage = pageObject.getPage();
		
		Assert.assertEquals(pageObjectPage.getClass(), GeneralPageImpl.class);
		Assert.assertNotEquals(pageObjectPage, page);
	}
	
	@Test
	public void testPageObjectWebDriverInstance() {
		AbstractPage pageObjectPage = pageObject.getPage();
		
		Assert.assertEquals(pageObjectPage.getWebDriver(), page.getWebDriver());
	}
}
