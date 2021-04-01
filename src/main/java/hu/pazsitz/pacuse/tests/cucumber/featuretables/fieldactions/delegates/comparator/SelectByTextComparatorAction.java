package hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions.delegates.comparator;

import hu.pazsitz.pacuse.tests.cucumber.featuretables.AnnotatedWebElement;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.google.common.base.Function;

/**
 * SelectByTextComparatorAction.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
public class SelectByTextComparatorAction extends AbstractDelegatedComparatorAction<String, String> {
	private Function<WebElement, String> selectByTextFunction = new Function<WebElement, String>() {
		@Override
		public String apply(WebElement element) { return element.getText(); }
	};

	public boolean doAction(AnnotatedWebElement element, String value) throws Exception {
		Select selector = new Select(element);
		
		if (element.getFieldAnnotation().allowMultiSelect() && selector.isMultiple()) {
			return isMultiSelectContainsValue(selector, selectByTextFunction, value);
		} else {
			return compareAction(value, selector.getFirstSelectedOption().getText());
		}
		
	}

	

}
