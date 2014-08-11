package hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions.delegates.populator;

import org.apache.log4j.Logger;

import hu.pazsitz.pacuse.tests.cucumber.featuretables.AnnotatedWebElement;
import hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions.delegates.IDelegatedAction;
import hu.pazsitz.pacuse.tests.helpers.FormHelper;

/**
 * CustomAttributePopulatorAction.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
public class CustomAttributePopulatorAction implements IDelegatedAction {

	@Override
	public boolean doAction(AnnotatedWebElement element, String value) throws Exception {
		Logger.getLogger(this.getClass()).info("tag: " + element.getTagName() + " value: " + value);
	
		String attribute = element.getFieldAnnotation().attribute();
		FormHelper.setElementAttribute(element, attribute, value);
		return true;
	}

}
