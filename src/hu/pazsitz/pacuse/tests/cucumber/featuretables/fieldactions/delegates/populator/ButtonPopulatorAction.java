package hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions.delegates.populator;

import org.openqa.selenium.WebElement;

import hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions.delegates.IDelegatedAction;

/**
 * ButtonPopulatorAction.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
public class ButtonPopulatorAction implements IDelegatedAction {

	@Override
	public boolean doAction(WebElement element, String value) throws Exception {
		element.click();
		return true;
	}

}
