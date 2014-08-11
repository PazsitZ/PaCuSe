package hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions.delegates.comparator;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.google.common.base.Function;
import com.google.common.base.Joiner;

import hu.pazsitz.pacuse.tests.cucumber.featuretables.AnnotatedWebElement;
import hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions.delegates.DelegatedActionException;
import hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions.delegates.IDelegatedAction;

/**
 * AbstractDelegatedComparatorAction.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
abstract public class AbstractDelegatedComparatorAction<E, V> implements IDelegatedAction {

	/**
	 * Handles the common part of the ComparatorActions
	 */
	@Override
	abstract public boolean doAction(AnnotatedWebElement element, String value) throws Exception;
	
	/**
	 * Compares values
	 * @param expected
	 * @param actual
	 * @return boolean
	 * @throws DelegatedActionException
	 */
	protected boolean compareAction(E expected, V actual) throws DelegatedActionException {
		boolean result = expected.equals(actual);
		// TODO Log4j
		Logger.getLogger(this.getClass()).error("expected: " + expected + " value: " + actual);
//		System.out.println("[DEBUG - " + this.getClass().getSimpleName() + "] expected: " + expected + " value: " + actual);

		if (!result) {
			throw new DelegatedActionException(expected.toString(), actual.toString());
		}
		
		return result;
	}
	
	/**
	 * Search single value in multi select list
	 * @param selector
	 * @param selectTypeMethod
	 * @param value
	 * @return boolean
	 * @throws DelegatedActionException 
	 */
	protected boolean isMultiSelectContainsValue(Select selector, Function<WebElement, String> selectTypeMethod, String value) throws DelegatedActionException {
		List<String> selectedList = new ArrayList<>();
		for (WebElement selectedElement : selector.getAllSelectedOptions()) {
		    selectedList.add(selectTypeMethod.apply(selectedElement));
		}
		String multiSelectValues = Joiner.on(",").join(selectedList);
		
		boolean result =  ("," + multiSelectValues + ",").contains("," + value + ",");
		
		if (!result) {
			throw new DelegatedActionException(value, multiSelectValues);
		}
		
		return result;
	}

}
