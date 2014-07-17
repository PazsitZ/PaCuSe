package hu.pazsitz.pacuse.pageObjects;

import hu.pazsitz.pacuse.pages.GeneralPageImpl;

import org.openqa.selenium.WebDriver;

/**
 * GeneralPageImplObject.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
public class GeneralPageImplObject extends AbstractPageObject<GeneralPageImpl> {

    public GeneralPageImplObject(WebDriver webDriver) {
        super(GeneralPageImpl.class, webDriver);
    }

}
