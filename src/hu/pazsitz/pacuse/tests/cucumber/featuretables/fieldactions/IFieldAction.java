package hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions;

import org.openqa.selenium.WebElement;

/**
 * IFieldAction.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
public interface IFieldAction {

	public boolean doAction(WebElement element, String value);
}
