package hu.pazsitz.pacuse.tests.cucumber.featuretables.mapperactions;

import hu.pazsitz.pacuse.tests.cucumber.featuretables.mapperactions.delegates.populator.PopulatorActionDelegator;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

/**
 * PopulatorAction.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
public class PopulatorAction implements IMapperAction {
	private PopulatorActionDelegator delegator = new PopulatorActionDelegator();
	
	@Override
	public boolean doAction(WebElement element, String value) {
		System.out.println("[DEBUG - PopulatorAction]  element.text: " + element.getText() + " value: " + value);
		
		try {
			return delegator.populate(element, value);
		} catch (NullPointerException | NoSuchElementException e) {
			return false;
		}
	}

}
