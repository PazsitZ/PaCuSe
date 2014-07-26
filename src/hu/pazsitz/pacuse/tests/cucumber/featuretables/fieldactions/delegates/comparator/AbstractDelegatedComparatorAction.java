package hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions.delegates.comparator;

import hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions.delegates.DelegatedActionException;
import hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions.delegates.IDelegatedAction;

import org.openqa.selenium.WebElement;

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
	abstract public boolean doAction(WebElement element, String value) throws Exception;
	
	protected boolean compareAction(E expected, V actual) throws DelegatedActionException {
		boolean result = expected.equals(actual);

		if (!result) {
			throw new DelegatedActionException(expected.toString(), actual.toString());
		}
		
		return result;
	}

}
