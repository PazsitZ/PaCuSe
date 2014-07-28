package hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions.delegates;

import hu.pazsitz.pacuse.tests.cucumber.featuretables.AnnotatedWebElement;

public interface IDelegatedAction {
	public boolean doAction(AnnotatedWebElement element, String value) throws Exception;
}
