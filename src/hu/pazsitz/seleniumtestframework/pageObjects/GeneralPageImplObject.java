package hu.pazsitz.seleniumtestframework.pageObjects;

import hu.pazsitz.seleniumtestframework.pages.GeneralPageImpl;

import org.openqa.selenium.WebDriver;


public class GeneralPageImplObject extends AbstractPageObject<GeneralPageImpl> {

    public GeneralPageImplObject(WebDriver webDriver) {
        super(GeneralPageImpl.class, webDriver);
    }

}
