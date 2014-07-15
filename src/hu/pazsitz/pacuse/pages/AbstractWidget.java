package hu.pazsitz.pacuse.pages;

import org.openqa.selenium.WebDriver;

/**
 * AbstractWidget.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
public abstract class AbstractWidget extends AbstractPage {

    public AbstractWidget(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public String getUrl() {
        return null;
    }
}
