package hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions.delegates.comparator;

import hu.pazsitz.pacuse.tests.cucumber.featuretables.AnnotatedWebElement;

/**
 * LinkHrefComparatorAction.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
public class LinkHrefComparatorAction extends AbstractDelegatedComparatorAction<String, String> {

	@Override
	public boolean doAction(AnnotatedWebElement element, String value) throws Exception {
		return compareAction(value.trim(), element.getAttribute("href").trim());
	}

}
