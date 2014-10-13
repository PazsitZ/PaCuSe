package hu.pazsitz.pacuse.tests.cucumber.stepdefs;

import hu.pazsitz.pacuse.pageObjects.NameCardPageObject;
import hu.pazsitz.pacuse.tests.cucumber.helpers.BoolValue;
import hu.pazsitz.pacuse.tests.cucumber.helpers.BoolValueConverter;
import hu.pazsitz.pacuse.tests.cucumber.helpers.BoolValueEnum;
import hu.pazsitz.pacuse.tests.helpers.StepDefBase;

import java.util.List;
import java.util.Map;

import cucumber.api.Transform;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.java.StepDefAnnotation;

/**
 * NameCardStepDef.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
@StepDefAnnotation
public class NameCardStepDef {
    private final StepDefBase base;
    private final NameCardPageObject nameCard;

    public NameCardStepDef(StepDefBase base) {
        this.base = base;
        nameCard = new NameCardPageObject(base.getWebDriver());
    }


	@Given("^the NameCard Webpage$")
	public void the_NameCard_Webpage() throws Throwable {
        base.loadPage(nameCard.getPage());
	}

	@When("^select the (about|contact) card$")
	public void select_the_about_card(String type) throws Throwable {
	    if (type.equals("about")) {
	    	nameCard.showAboutCard();
	    } else {
	    	nameCard.showContactCard();
	    }
	}

	@Then("^validate the (about|contact) page content$")
	public void validate_the_about_page_content(String type) throws Throwable {
		validate_the_about_page_contentWithTable(type, null);
	}
	
	@Then("^validate the (about|contact) page content:$")
	public void validate_the_about_page_contentWithTable(String type, List<Map<String, String>> table) throws Throwable {
		if (type.equals("about")) {
	    	nameCard.validateAboutCard(table);
	    } else {
	    	nameCard.validateContactCard();
	    }
	}
	
	@Then("^validate the contact page content from table:$")
	public void validate_the_contact_page_contentWithTable(List<Map<String, String>> table) throws Throwable {
    	nameCard.validateContactCard(table);
	}
	
	@Then("^the email (.*) visible$")
	public void theEmailVisible(BoolValue visible) {
		System.out.println("[" + visible.getClass().getName() + "]" + visible.name() + " " + visible.getBoolValue());
		if (visible.getBoolValue()) {
			System.out.println(visible.getValue());
		}
		else {
			System.out.println(visible.getValue());
		}
	}
	
	@Then("^the email3 (.*) present$")
	public void theEmailPresent(@Transform(BoolValueConverter.class) BoolValueEnum present) {
		System.out.println("[" + present.getClass().getName() + "]" + present.name() + " " + present.getBoolValue());
		if (present.getBoolValue()) {
			System.out.println(present.getValue());
		}
		else {
			System.out.println(present.getValue());
		}
	}
}
