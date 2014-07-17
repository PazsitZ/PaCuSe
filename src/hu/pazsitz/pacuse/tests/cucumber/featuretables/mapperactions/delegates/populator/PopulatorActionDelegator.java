package hu.pazsitz.pacuse.tests.cucumber.featuretables.mapperactions.delegates.populator;

import hu.pazsitz.pacuse.tests.cucumber.featuretables.mapperactions.delegates.IActionDelegator;
import hu.pazsitz.pacuse.tests.helpers.CucumberTableHelper;

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
			CucumberTableHelper.selectByValue(element, value);
		} else {
			switch (element.getAttribute("type")) {
				case "radio" :
				case "checkbox" :
					CucumberTableHelper.checkboxTicker(element, value);
				break;
				case "button" :
					element.click();
				break;
				default:
					CucumberTableHelper.inputFillOverWrite(element, value);
				break;
			}
			
		}
		} catch (Exception e){
			return false;
		}
		return true;
	}
	

}
