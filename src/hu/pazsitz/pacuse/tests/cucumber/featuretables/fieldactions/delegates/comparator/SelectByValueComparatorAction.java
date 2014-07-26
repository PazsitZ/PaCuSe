package hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions.delegates.comparator;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * SelectByValueComparatorAction.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
public class SelectByValueComparatorAction extends AbstractDelegatedComparatorAction<String, String> {

	@Override
	public boolean doAction(WebElement element, String value) throws Exception {
		Select selector = new Select(element);
		
		return compareAction(selector.getFirstSelectedOption().getAttribute("value"), value);
	}

}
