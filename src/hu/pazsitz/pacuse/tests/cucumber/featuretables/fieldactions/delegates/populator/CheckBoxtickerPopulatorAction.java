package hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions.delegates.populator;

import org.openqa.selenium.WebElement;

import hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions.delegates.IDelegatedAction;
import hu.pazsitz.pacuse.tests.helpers.FormHelper;

/**
 * CheckBoxtickerPopulatorAction.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
public class CheckBoxtickerPopulatorAction implements IDelegatedAction {

	@Override
	public boolean doAction(WebElement element, String value) throws Exception {
		FormHelper.checkboxTicker(element, value);
		return true;
	}

}
