package hu.pazsitz.pacuse.pages.widgets;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import hu.pazsitz.pacuse.pages.AbstractWidget;
import hu.pazsitz.pacuse.tests.annotations.DataTableAttributes;

public class AboutWidget extends AbstractWidget {

	public AboutWidget(WebDriver webDriver) {
		super(webDriver);
	}
	
	@FindBy(how=How.XPATH, using="//div[contains(concat(' ',@class,' '), ' about ')]")
    private WebElement container;
	
	@DataTableAttributes(name="name")
    @FindBy(how=How.XPATH, using="//div[contains(concat(' ',@class,' '), ' about ')]/div/span/h2")
    private WebElement name;

    @DataTableAttributes(name="profession")
    @FindBy(how=How.XPATH, using="//div[contains(concat(' ',@class,' '), ' about ')]//span[@class='profession']")
    private WebElement profession;
    
    
    public WebElement getName() {
		return name;
	}

	public WebElement getProfession() {
		return profession;
	}

	@Override
	public WebElement getContainer() {
		return container;
	}

}
