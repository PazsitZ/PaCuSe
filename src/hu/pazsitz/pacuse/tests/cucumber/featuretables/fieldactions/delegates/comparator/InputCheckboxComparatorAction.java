package hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions.delegates.comparator;

import hu.pazsitz.pacuse.tests.helpers.FormHelper;

import org.openqa.selenium.WebElement;

/**
 * InputCheckboxComparatorAction.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
public class InputCheckboxComparatorAction extends AbstractDelegatedComparatorAction<Boolean, Boolean> {

	@Override
	public boolean doAction(WebElement element, String value) throws Exception {
		return compareAction((Boolean) element.isSelected(), (Boolean) FormHelper.getCheckboxBoolValue(value));
	}

}
