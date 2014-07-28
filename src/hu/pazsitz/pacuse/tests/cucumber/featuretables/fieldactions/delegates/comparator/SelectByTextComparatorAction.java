package hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions.delegates.comparator;

import hu.pazsitz.pacuse.tests.cucumber.featuretables.AnnotatedWebElement;

import org.openqa.selenium.support.ui.Select;

/**
 * SelectByTextComparatorAction.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
public class SelectByTextComparatorAction extends AbstractDelegatedComparatorAction<String, String> {

	public boolean doAction(AnnotatedWebElement element, String value) throws Exception {
		Select selector = new Select(element);
		
		return compareAction(value, selector.getFirstSelectedOption().getText());
	}

}
