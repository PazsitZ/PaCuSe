package hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions;


import hu.pazsitz.pacuse.tests.cucumber.featuretables.AnnotatedWebElement;
import hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions.delegates.comparator.ComparatorActionDelegator;

/**
 * ComparatorAction.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
public class ComparatorAction implements IFieldAction {
	private ComparatorActionDelegator delegator = new ComparatorActionDelegator();
	
	@Override
	public boolean doAction(AnnotatedWebElement element, String value) throws Exception {
		return delegator.doDelegate(element, value);			
	}

}
