package hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions.delegates.comparator;

import hu.pazsitz.pacuse.tests.cucumber.featuretables.AnnotatedWebElement;

import org.openqa.selenium.support.ui.Select;

/**
 * SelectByValueComparatorAction.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
public class SelectByValueComparatorAction extends AbstractDelegatedComparatorAction<String, String> {

	@Override
	public boolean doAction(AnnotatedWebElement element, String value) throws Exception {
		Select selector = new Select(element);
		
		return compareAction(value, selector.getFirstSelectedOption().getAttribute("value"));
	}

}
