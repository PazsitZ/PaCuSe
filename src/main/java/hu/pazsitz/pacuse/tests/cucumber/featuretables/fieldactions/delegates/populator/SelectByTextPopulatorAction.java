package hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions.delegates.populator;

import org.apache.log4j.Logger;

import hu.pazsitz.pacuse.tests.cucumber.featuretables.AnnotatedWebElement;
import hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions.delegates.IDelegatedAction;
import hu.pazsitz.pacuse.tests.helpers.FormHelper;

/**
 * SelectByTextPopulatorAction.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
public class SelectByTextPopulatorAction implements IDelegatedAction {
	@Override
	public boolean doAction(AnnotatedWebElement element, String value) throws Exception {
		Logger.getLogger(this.getClass()).info(" tag: " + element.getTagName() + " value: " + value);

		FormHelper.selectByText(element, value, element.getFieldAnnotation().allowMultiSelect());
		return true;
	}

}
