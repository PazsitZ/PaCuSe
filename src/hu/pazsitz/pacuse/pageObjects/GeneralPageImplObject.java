package hu.pazsitz.pacuse.pageObjects;

import hu.pazsitz.pacuse.pages.GeneralPageImpl;

import org.openqa.selenium.WebDriver;


public class GeneralPageImplObject extends AbstractPageObject<GeneralPageImpl> {

    public GeneralPageImplObject(WebDriver webDriver) {
        super(GeneralPageImpl.class, webDriver);
    }

}
