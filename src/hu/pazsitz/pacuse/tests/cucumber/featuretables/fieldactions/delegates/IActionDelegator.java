package hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions.delegates;

import org.openqa.selenium.WebElement;

/**
 * IActionDelegator.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
public interface IActionDelegator {
	public boolean populate(WebElement element, String value);
}
