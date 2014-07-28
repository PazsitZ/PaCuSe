package hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions;

import hu.pazsitz.pacuse.tests.cucumber.featuretables.AnnotatedWebElement;

/**
 * ComparatorAction.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 * 
 * @deprecated since the mapping has no action it's useless
 */
public class BlankAction implements IFieldAction {

	/**
	 * Blank Action - no action evaluates, for mapping purpose
	 */
	@Override
	public boolean doAction(AnnotatedWebElement element, String value) {
		return true;
	}

}
