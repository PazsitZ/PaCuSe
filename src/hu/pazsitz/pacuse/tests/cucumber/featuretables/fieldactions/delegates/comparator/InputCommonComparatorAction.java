package hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions.delegates.comparator;

import org.openqa.selenium.WebElement;

/**
 * InputCommonComparatorAction.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
public class InputCommonComparatorAction extends AbstractDelegatedComparatorAction<String, String> {

	@Override
	public boolean doAction(WebElement element, String value) throws Exception {
		return compareAction(element.getAttribute("value").trim(), value.trim());
	}

}
