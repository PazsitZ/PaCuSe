package hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions;

import hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions.delegates.populator.PopulatorActionDelegator;

import org.openqa.selenium.WebElement;

/**
 * PopulatorAction.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
public class PopulatorAction implements IFieldAction {
	private PopulatorActionDelegator delegator = new PopulatorActionDelegator();
	
	@Override
	public boolean doAction(WebElement element, String value) throws Exception {
		// TODO Log4j
		System.out.println("[DEBUG - PopulatorAction]  element.text: " + element.getText() + "(" +element.getAttribute("name") + ") value: " + value);
			
		return delegator.doDelegate(element, value);
	}

}
