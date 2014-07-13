package hu.pazsit.seleniumtestframework.pages;

import org.openqa.selenium.WebDriver;

/**
 * GeneralPageImpl.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
public class GeneralPageImpl extends AbstractPage {

    public GeneralPageImpl(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public String getUrl() {
        return pageUrl;
    }

}
