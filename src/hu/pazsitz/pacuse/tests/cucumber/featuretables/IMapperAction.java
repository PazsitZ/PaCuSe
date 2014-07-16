package hu.pazsitz.pacuse.tests.cucumber.featuretables;

import org.openqa.selenium.WebElement;

/**
 * IMapperAction.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
public interface IMapperAction {

	public boolean doAction(WebElement element, String value);
}
