package hu.pazsitz.pacuse.tests.cucumber.featuretables;

import org.openqa.selenium.WebElement;

public interface IMapperAction {

	public boolean doAction(WebElement element, String value);
}
