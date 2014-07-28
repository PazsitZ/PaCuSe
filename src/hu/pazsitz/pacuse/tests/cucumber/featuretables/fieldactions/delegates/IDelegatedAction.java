package hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions.delegates;

import hu.pazsitz.pacuse.tests.cucumber.featuretables.AnnotatedWebElement;

/**
 * IDelegatedAction.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
public interface IDelegatedAction {
	public boolean doAction(AnnotatedWebElement element, String value) throws Exception;
}
