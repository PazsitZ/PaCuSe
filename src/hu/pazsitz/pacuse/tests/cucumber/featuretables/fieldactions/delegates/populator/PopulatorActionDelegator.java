package hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions.delegates.populator;

import hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions.delegates.IActionDelegator;
import hu.pazsitz.pacuse.tests.helpers.FormHelper;

import org.openqa.selenium.WebElement;

/**
 * PopulatorActionDelegator.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
public class PopulatorActionDelegator implements IActionDelegator {

	@Override
	public boolean populate(WebElement element, String value) {
		try {
		if ("select".equals(element.getTagName())) {
			FormHelper.selectByValue(element, value);
		} else {
			switch (element.getAttribute("type")) {
				case "radio" :
				case "checkbox" :
					FormHelper.checkboxTicker(element, value);
				break;
				case "button" :
					element.click();
				break;
				default:
					FormHelper.inputFillOverWrite(element, value);
				break;
			}
			
		}
		} catch (Exception e){
			return false;
		}
		return true;
	}
	

}
