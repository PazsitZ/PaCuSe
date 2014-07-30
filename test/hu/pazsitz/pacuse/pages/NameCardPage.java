package hu.pazsitz.pacuse.pages;

import hu.pazsitz.pacuse.pages.AbstractPage;
import hu.pazsitz.pacuse.pages.widgets.AboutWidget;
import hu.pazsitz.pacuse.tests.annotations.DataTableAttributes;
import hu.pazsitz.pacuse.tests.annotations.Widget;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * NameCardPage.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
public class NameCardPage extends AbstractPage {
    private final String pageUrl = "http://pazsitz.hu";
    @Widget
    protected AboutWidget aboutWidget;

    public NameCardPage(WebDriver webDriver) {
        super(webDriver);
        aboutWidget = registerWidget(AboutWidget.class);
    }

	@Override
    public String getUrl() {
        return pageUrl;
    }

    @FindBy(how=How.XPATH, using="//div[contains(concat(' ',@class,' '), ' about ')]")
    private WebElement aboutCardPanel;

    @FindBy(how=How.XPATH, using="//div[contains(concat(' ',@class,' '), ' contact ')]")
    private WebElement contactCardPanel;

    @DataTableAttributes(name="email1", priority = 1)
    @FindBy(how=How.XPATH, using="//div[contains(concat(' ',@class,' '), ' contact ')]//span[@class='email'][1]/a")
    private WebElement contactEmail1;

    @DataTableAttributes(name="email2", priority = 1)
    @FindBy(how=How.XPATH, using="//div[contains(concat(' ',@class,' '), ' contact ')]//span[@class='email'][2]/a")
    private WebElement contactEmail2;

    @DataTableAttributes(name={"webPage", "web Page Url"}, priority = 2)
    @FindBy(how=How.XPATH, using="//div[contains(concat(' ',@class,' '), ' contact ')]//span[@class='list']/span[1]/a")
    private WebElement webPageUrlLink;

    @DataTableAttributes(name="Facebook", priority = 2)
    @FindBy(how=How.XPATH, using="//div[contains(concat(' ',@class,' '), ' contact ')]//span[@class='list']/span[2]/a")
    private WebElement FBUrlLink;

    @DataTableAttributes(name="LinkedIn", priority = 2)
    @FindBy(how=How.XPATH, using="//div[contains(concat(' ',@class,' '), ' contact ')]//span[@class='list']/span[3]/a")
    private WebElement LinkedInUrlLink;

	public WebElement getAboutCardPanel() {
		return aboutCardPanel;
	}

	public WebElement getContactCardPanel() {
		return contactCardPanel;
	}

	public WebElement getContactEmail1() {
		return contactEmail1;
	}

	public WebElement getContactEmail2() {
		return contactEmail2;
	}

	public WebElement getWebPageUrlLink() {
		return webPageUrlLink;
	}

	public WebElement getFBUrlLink() {
		return FBUrlLink;
	}

	public WebElement getLinkedInUrlLink() {
		return LinkedInUrlLink;
	}
	
	public AboutWidget getAboutWidget() {
		return aboutWidget;
	}


}
