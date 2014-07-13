package hu.pazsit.seleniumtestframework.pageObjects;

import hu.pazsit.seleniumtestframework.pages.GeneralPageImpl;
import org.openqa.selenium.WebDriver;


public class GeneralPageImplObject extends AbstractPageObject<GeneralPageImpl> {

    public GeneralPageImplObject(WebDriver webDriver) {
        super(GeneralPageImpl.class, webDriver);
    }

}
