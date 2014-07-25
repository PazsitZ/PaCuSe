package hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions.delegates.comparator;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions.delegates.IActionDelegator;
import hu.pazsitz.pacuse.tests.helpers.FormHelper;

/**
 * ComparatorActionDelegator.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
public class ComparatorActionDelegator implements IActionDelegator {

	@Override
	public boolean populate(WebElement element, String value) {
		if ("select".equals(element.getTagName())) {
			Select selector = new Select(element);
			return selector.getFirstSelectedOption().getText().equals(value) 
				|| selector.getFirstSelectedOption().getAttribute("value").equals(value);
		} else if ("input".equals(element.getTagName())) {
			switch (element.getAttribute("type")) {
				case "radio" :
				case "checkbox" :
					return element.isSelected() == FormHelper.getCheckboxBoolValue(value);
				default:
					return element.getAttribute("value").equals(value) || element.getAttribute("value").trim().equals(value.trim());
			}
		} else {
			return element.getText().equals(value) || element.getText().trim().equals(value.trim());
		}
	}

}
