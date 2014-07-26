package hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions.delegates.populator;

import hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions.delegates.IActionDelegator;
import hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions.delegates.IDelegatedAction;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

/**
 * PopulatorActionDelegator.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
public class PopulatorActionDelegator implements IActionDelegator {

	@Override
	public boolean doDelegate(WebElement element, String value) throws Exception {
		boolean result = false;
		Exception ex = null;
		List<IDelegatedAction> actions = new ArrayList<>();
		
		if ("select".equals(element.getTagName())) {
			actions.add(new SelectByValuePopulatorAction());
			actions.add(new SelectByTextPopulatorAction());
		} else {
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
