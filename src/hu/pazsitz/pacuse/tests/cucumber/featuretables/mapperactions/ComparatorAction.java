package hu.pazsitz.pacuse.tests.cucumber.featuretables.mapperactions;


import hu.pazsitz.pacuse.tests.cucumber.featuretables.mapperactions.delegates.comparator.ComparatorActionDelegator;

import org.openqa.selenium.WebElement;

/**
 * ComparatorAction.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
public class ComparatorAction implements IMapperAction {
	private ComparatorActionDelegator delegator = new ComparatorActionDelegator();
	
	@Override
	public boolean doAction(WebElement element, String value) {
		try {
			System.out.println("[DEBUG - ComparatorAction]  element.text: " + element.getText() + " value: " + value);
			
			return delegator.populate(element, value);			
		} catch (NullPointerException e) {
			return false;
		}
	}

}
