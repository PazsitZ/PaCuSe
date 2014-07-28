package hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions.delegates.populator;

import hu.pazsitz.pacuse.tests.cucumber.featuretables.AnnotatedWebElement;
import hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions.delegates.IDelegatedAction;
import hu.pazsitz.pacuse.tests.helpers.FormHelper;

/**
 * SelectByIndexPopulatorAction.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
public class SelectByIndexPopulatorAction implements IDelegatedAction {
	@Override
	public boolean doAction(AnnotatedWebElement element, String value) throws Exception {
		// TODO Log4j
		System.out.println("[DEBUG - " + this.getClass().getSimpleName() + "] tag: " + element.getTagName() + " value: " + value);
		
		int[] values;
		if (element.getFieldAnnotation().allowMultiSelect() && value.contains(",")) {
			values = spitIntValues(value);
		} else {
			values = new int[]{ Integer.parseInt(value) };
		}
		FormHelper.selectByIndex(element, values);
		
		return true;
	}

	private int[] spitIntValues(String value) {
		String[] stringValues = value.split(",");
		int i = 0;
		int[] values = new int[stringValues.length];
		for (String numericalValue : stringValues) { 
			values[i++] = Integer.parseInt(numericalValue); 
		}
		return values;
	}

}