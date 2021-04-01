package hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions;

import hu.pazsitz.pacuse.tests.cucumber.featuretables.AnnotatedWebElement;
import hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions.delegates.populator.PopulatorActionDelegator;

/**
 * PopulatorAction.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
public class PopulatorAction implements IFieldAction {
	private PopulatorActionDelegator delegator = new PopulatorActionDelegator();
	
	@Override
	public boolean doAction(AnnotatedWebElement element, String value) throws Exception {
		return delegator.doDelegate(element, value);
	}

}
