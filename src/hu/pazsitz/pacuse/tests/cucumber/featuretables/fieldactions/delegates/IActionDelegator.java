package hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions.delegates;

import hu.pazsitz.pacuse.tests.cucumber.featuretables.AnnotatedWebElement;

/**
 * IActionDelegator.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
public interface IActionDelegator {
	public boolean doDelegate(AnnotatedWebElement element, String value) throws Exception;
}
