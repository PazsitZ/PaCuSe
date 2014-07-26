package hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions.delegates;

import org.openqa.selenium.WebElement;

public interface IDelegatedAction {
	public boolean doAction(WebElement element, String value) throws Exception;
}
