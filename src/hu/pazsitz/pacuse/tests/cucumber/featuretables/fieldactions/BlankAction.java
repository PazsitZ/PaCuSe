package hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions;

import org.openqa.selenium.WebElement;

/**
 * ComparatorAction.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
public class BlankAction implements IFieldAction {

	/**
	 * Blank Action - no action evaluates, for mapping purpose
	 */
	@Override
	public boolean doAction(WebElement element, String value) {
		return true;
	}

}
