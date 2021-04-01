package hu.pazsitz.pacuse.pages;

import hu.pazsitz.pacuse.tests.helpers.ElementHelper;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
    
    public abstract WebElement getContainer();

    @Override
    public String getUrl() {
        return null;
    }
    
    public boolean exists() {
    	return ElementHelper.isExists(getContainer());
    }
    
    public boolean visible() throws NoSuchElementException {
    	return ElementHelper.isVisible(getContainer());
    }
}
