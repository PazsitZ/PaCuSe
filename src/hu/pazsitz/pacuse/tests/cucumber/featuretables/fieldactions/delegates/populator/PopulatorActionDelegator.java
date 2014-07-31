package hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions.delegates.populator;

import hu.pazsitz.pacuse.tests.annotations.DTAInputHandling;
import hu.pazsitz.pacuse.tests.cucumber.featuretables.AnnotatedWebElement;
import hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions.delegates.IActionDelegator;
import hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions.delegates.IDelegatedAction;

import java.util.ArrayList;
import java.util.List;

/**
 * PopulatorActionDelegator.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
public class PopulatorActionDelegator implements IActionDelegator {

	@Override
	public boolean doDelegate(AnnotatedWebElement element, String value) throws Exception {
		boolean result = false;
		Exception ex = null;
		List<IDelegatedAction> actions = new ArrayList<>();
		DTAInputHandling inputHandling = element.getFieldAnnotation().inputHandling();
		
		// TODO refactor
		if ((inputHandling == DTAInputHandling.AUTO || inputHandling == DTAInputHandling.CUSTOM_ATTRIBUTE) 
				&& !element.getFieldAnnotation().attribute().isEmpty()) {
			actions.add(new CustomAttributePopulatorAction());
		}
		
		if ("select".equals(element.getTagName())) {
			if (inputHandling == DTAInputHandling.AUTO || inputHandling == DTAInputHandling.SELECT_BY_VALUE)
				actions.add(new SelectByValuePopulatorAction());
			if (inputHandling == DTAInputHandling.AUTO || inputHandling == DTAInputHandling.SELECT_BY_TEXT)
				actions.add(new SelectByTextPopulatorAction());
			if (inputHandling == DTAInputHandling.AUTO || inputHandling == DTAInputHandling.SELECT_BY_INDEX)
				actions.add(new SelectByIndexPopulatorAction());
		} else {
			if (inputHandling == DTAInputHandling.AUTO){
				switch (element.getAttribute("type")) {
					case "radio" :
					case "checkbox" :
						actions.add(new CheckBoxtickerPopulatorAction());
					break;
					case "button" :
						actions.add(new ButtonPopulatorAction());
					break;
					default:
						actions.add(new InputPopulatorAction());
					break;
				}
			}
			
		}
		
		for (IDelegatedAction action : actions) {
			try {
				result = action.doAction(element, value);
			} catch (Exception e) {
				ex = e;
			}
			if (result) break;
		}
		
		if (!result && ex != null) throw ex;
		
		return result;
	}
	

}
