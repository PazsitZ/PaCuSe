package hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions;


import hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions.delegates.comparator.ComparatorActionDelegator;

import org.openqa.selenium.WebElement;

/**
 * ComparatorAction.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
public class ComparatorAction implements IFieldAction {
	private ComparatorActionDelegator delegator = new ComparatorActionDelegator();
	
	@Override
	public boolean doAction(WebElement element, String value) throws Exception {
		// TODO Log4j
		System.out.println("[DEBUG - ComparatorAction]  element.text: " + element.getText() + " value: " + value);
		
		return delegator.doDelegate(element, value);			
	}

}
