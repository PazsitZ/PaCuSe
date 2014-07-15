package hu.pazsitz.seleniumtestframework.tests.helpers;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * CucumberTableHelper.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
public class CucumberTableHelper {

    /**
     * Handles "yes" and "true" String values as checked
     *
     * @param element
     * @param value
     * @return WebElement
     */
    public static WebElement checkboxTicker(WebElement element, String value) {
        final String TICK_YES = "yes";
        final String TICK_TRUE = "true";

        value = value.trim().toLowerCase();
        boolean boolValue = value.equals(TICK_TRUE) || value.equals(TICK_YES);

        if (!element.isSelected() && boolValue) {
            element.click();
        } else if (element.isSelected() && !boolValue) {
            element.click();
        }

        return element;
    }

    /**
     * fills the element only on non-null value
     *
     * @param element
     * @param value
     * @return WebElement
     */
    public static WebElement selectByValue(WebElement element, String value) {
        if (value == null) return element;
        Select selector = new Select(element);
        selector.selectByValue(value);

        return element;
    }

    public static WebElement selectByIndex(WebElement element, int value) {
        Select selector = new Select(element);
        selector.selectByIndex(value);

        return element;
    }

    /**
     * fills the element only on non-null value
     *
     * @param element
     * @param value
     * @return WebElement
     */
    public static WebElement selectByText(WebElement element, String value) {
        if (value == null) return element;
        Select selector = new Select(element);
        selector.selectByVisibleText(value);

        return element;
    }

    /**
     * fills the element only on non-null value
     *
     * @param element
     * @param value
     * @return WebElement
     */
    public static WebElement inputFillOverWrite(WebElement element, String value) {
        if (value == null) return element;
        element.clear();
        element.sendKeys(value);

        return element;
    }
}