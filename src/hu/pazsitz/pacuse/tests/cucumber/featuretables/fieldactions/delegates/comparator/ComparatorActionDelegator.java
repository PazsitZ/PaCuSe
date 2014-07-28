package hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions.delegates.comparator;

import hu.pazsitz.pacuse.tests.annotations.DTAInputHandling;
import hu.pazsitz.pacuse.tests.cucumber.featuretables.AnnotatedWebElement;
import hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions.delegates.IActionDelegator;
import hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions.delegates.IDelegatedAction;

import java.util.ArrayList;
import java.util.List;

/**
 * ComparatorActionDelegator.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
public class ComparatorActionDelegator implements IActionDelegator {

	/**
	 * 
	 */
	@Override
	public boolean doDelegate(AnnotatedWebElement element, String value) throws Exception {
		boolean result = false;
		Exception ex = null;
		List<IDelegatedAction> actions = new ArrayList<>();
		
		DTAInputHandling inputHandling = element.getFieldAnnotation().inputHandling();
		switch (element.getTagName()) {
			case "select":
				if (inputHandling == DTAInputHandling.AUTO || inputHandling == DTAInputHandling.SELECT_BY_TEXT)
					actions.add(new SelectByTextComparatorAction());
				if (inputHandling == DTAInputHandling.AUTO || inputHandling == DTAInputHandling.SELECT_BY_VALUE)
					actions.add(new SelectByValueComparatorAction());
				// TODO SelectByIndexComparatorAction
			break;
			case "input":
				if (element.getAttribute("type").equals("radio") || element.getAttribute("type").equals("checkbox")) {
				actions.add(new InputCheckboxComparatorAction());
				} else {
					actions.add(new InputCommonComparatorAction());
				}
			break;
			case "a":
				if (inputHandling == DTAInputHandling.AUTO || inputHandling == DTAInputHandling.LINK_BY_SRC)
					actions.add(new LinkSrcComparatorAction());
				if (inputHandling == DTAInputHandling.AUTO || inputHandling == DTAInputHandling.LINK_BY_TEXT)
					actions.add(new LinkTextComparatorAction());
			break;
			default:
				actions.add(new CommonElementComparatorAction());
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
