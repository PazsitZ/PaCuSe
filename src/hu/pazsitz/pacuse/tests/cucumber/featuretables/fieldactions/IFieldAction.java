package hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions;

import hu.pazsitz.pacuse.tests.cucumber.featuretables.AnnotatedWebElement;

/**
 * IFieldAction.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
public interface IFieldAction {

	public boolean doAction(AnnotatedWebElement element, String value) throws Exception;
}
