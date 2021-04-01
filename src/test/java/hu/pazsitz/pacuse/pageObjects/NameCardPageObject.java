package hu.pazsitz.pacuse.pageObjects;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import hu.pazsitz.pacuse.pageObjects.widgets.AboutWidgetObject;
import hu.pazsitz.pacuse.pages.NameCardPage;
import hu.pazsitz.pacuse.tests.cucumber.featuretables.ComparatorDataTable;
import hu.pazsitz.pacuse.tests.cucumber.featuretables.FieldActionResult;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

/**
 * NameCardPageObject.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
public class NameCardPageObject extends AbstractPageObject<NameCardPage> {
	private AboutWidgetObject aboutWidgetObject;
	
	public NameCardPageObject(WebDriver webDriver) {
		super(NameCardPage.class, webDriver);
		aboutWidgetObject = registerWidgetObject(AboutWidgetObject.class, page.getAboutWidget());
	}
	
	public AboutWidgetObject getAboutWidgetObject() {
		return aboutWidgetObject;
	}

	public void showAboutCard() {
		page.getAboutCardPanel().click();
        webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}

	public void showContactCard() {
		page.getContactCardPanel().click();
        webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}

	public void validateAboutCard() {
		String name = "Zoltán Pázsit";
		String profession = "Software Enginer - Web Developer";
		assertEquals(page.getAboutWidget().getName().getText(), name);
		assertEquals(page.getAboutWidget().getProfession().getText(), profession);
	}
	
	public void validateAboutCard(List<Map<String, String>> table) {
		ComparatorDataTable comparatorTable = new ComparatorDataTable(table);
		FieldActionResult result = comparatorTable.compareToPageModel(page);
		Assert.assertTrue(page.getAboutWidget().exists(), "aboutWidget not exists");
		Assert.assertTrue(page.getAboutWidget().visible(), "aboutWidget not visible");
		assertTrue(result.success(2), result.getNonSucceedFields().toString());
		assertTrue(result.success("name", "profession"), result.getNonSucceedFields().toString());
		assertTrue(result.fullSuccess(), result.getNonSucceedFields().toString());
		assertEquals(result.getNonDeterminedFields().size(), 0);
	}

	public void validateContactCard() {
		final String mail1 = "contact@pazsitz.hu";
		final String mail2 = "pazsitz@gmail.com";
		final String webPageUrl = "http://pazsitz.hu";
		final String FBUrl = "http://facebook.com/pazsitz";
		final String LIUrl = "http://hu.linkedin.com/in/PazsitZ";

		assertEquals(page.getContactEmail1().getText(), mail1);
		assertEquals(page.getContactEmail2().getText(), mail2);
		assertEquals(page.getContactEmail1().getAttribute("href"), "mailto:" + mail1);
		assertEquals(page.getContactEmail2().getAttribute("href"), "mailto:" + mail2);
		assertEquals(page.getWebPageUrlLink().getAttribute("href"), webPageUrl);
		assertEquals(page.getFBUrlLink().getAttribute("href"), FBUrl);
		assertEquals(page.getLinkedInUrlLink().getAttribute("href"), LIUrl);
	}

	public void validateContactCard(List<Map<String, String>> table) {
		ComparatorDataTable comparatorTable = new ComparatorDataTable(table);
		FieldActionResult result = comparatorTable.compareToPageModel(page);
		assertTrue(result.fullSuccess(), result.getNonSucceedFields().toString());
		assertEquals(result.getNonDeterminedFields().size(), 0);
	}
}
