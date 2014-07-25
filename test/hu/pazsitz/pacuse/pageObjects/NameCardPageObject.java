package hu.pazsitz.pacuse.pageObjects;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import hu.pazsitz.pacuse.pageObjects.AbstractPageObject;
import hu.pazsitz.pacuse.pages.NameCardPage;
import hu.pazsitz.pacuse.tests.cucumber.featuretables.ComparatorDataTable;
import hu.pazsitz.pacuse.tests.cucumber.featuretables.FieldActionResult;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

public class NameCardPageObject extends AbstractPageObject<NameCardPage> {

	public NameCardPageObject(WebDriver webDriver) {
		super(NameCardPage.class, webDriver);
	}

	public void showAboutCard() {
		page.getAboutCardPanel().click();
        webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}

	public void showContactCard() {
		page.getContactCardPanel().click();
        webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}

	public void validateAboutCard(List<Map<String, String>> table) {
		assertEquals(page.getName().getText(), table.get(0).get("name"));
		assertEquals(page.getProfession().getText(), table.get(0).get("profession"));
		
		ComparatorDataTable comparatorTable = new ComparatorDataTable(table);
		FieldActionResult result = comparatorTable.compareToPageModel(page);
		assertTrue(result.fullSuccess(), result.getFailedFields().toString());
	}

	public void validateContactCard() {
		final String mail1 = "contact@pazsitz.hu";
		final String mail2 = "pazsitz@gmail.com";
		final String webPageUrl = "http://pazsitz.hu/";
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
}
