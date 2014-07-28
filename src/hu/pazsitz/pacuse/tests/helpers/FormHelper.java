package hu.pazsitz.pacuse.tests.helpers;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * FormHelper.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
public class FormHelper {

	private static final String MULTISELECT_VALUE_SPLIT_CHAR = ",";

	/**
	 * Handles checkbox setting
	 * @param element
	 * @param boolValue
	 * @return WebElement
	 */
	 public static WebElement checkboxTicker(WebElement element, boolean boolValue) {
		 if (!element.isSelected() && boolValue) {
	            element.click();
	        } else if (element.isSelected() && !boolValue) {
	            element.click();
	        }

	        return element;
	 }
	 
    /**
     * Handles "yes" and "true" String values as checked
     *
     * @param element
     * @param value
     * @return WebElement
     */
    public static WebElement checkboxTicker(WebElement element, String value) {
        boolean boolValue = getCheckboxBoolValue(value);

        return checkboxTicker(element, boolValue);
    }

    /**
     * Converts "yes" and "true" strings (from gherkin table) to boolean
     * @param value
     * @return boolean
     */
    public static boolean getCheckboxBoolValue(String value) {
		final String TICK_YES = "yes";
        final String TICK_TRUE = "true";

        if (value == null) return false;
        
        value = value.trim().toLowerCase();
        boolean boolValue = value.equals(TICK_TRUE) || value.equals(TICK_YES);
		return boolValue;
	}

    /**
     * Fills the element only on non-null value (multiple select handles "," as separator chars)
     * @param element
     * @param value
     * @return WebElement
     */
    public static WebElement selectByValue(WebElement element, String value) {
    	return selectByValue(element, value, true);
    }
    
    /**
     * 
     * @param element
     * @param value
     * @param allowHandleMultiple if multiple select allowed it considers "," as separator chars
     * @return
     */
	public static WebElement selectByValue(WebElement element, String value, boolean allowHandleMultiple) {
		if (value == null) return element;
		
		Select selector = new Select(element);
		if (!selector.isMultiple()) allowHandleMultiple = false;
		String[] values = splitMultipleSelectValue(value, allowHandleMultiple);
        
        for (String singleValue : values) {
	        selector.selectByValue(singleValue);
        }

        return element;
    }

    /**
     * Selects option by numerical index
     * @param element
     * @param value
     * @return WebElement
     */
	public static WebElement selectByIndex(WebElement element, int value) {
		return selectByIndex(element, new int[]{value});
	}
	
	/**
	 * Selects option by numerical index
	 * @param element
	 * @param value
	 * @return WebElement
	 */
    public static WebElement selectByIndex(WebElement element, int[] values) {
    	for (int singleValue : values) {
	        Select selector = new Select(element);
	        selector.selectByIndex(singleValue);
    	}

        return element;
    }

    /**
     * Fills the element only on non-null value (multiple select handles "," as separator chars)
     * @param element
     * @param value
     * @return WebElement
     */
    public static WebElement selectByText(WebElement element, String value) {
    	return selectByText(element, value, true);
    }
    
    /**
     * Fills the element only on non-null value
     * @param element
     * @param value
     * @param allowHandleMultiple if multiple select allowed it considers "," as separator chars
     * @return WebElement
     */
    public static WebElement selectByText(WebElement element, String value, boolean allowHandleMultiple) {
		if (value == null) return element;
		
		Select selector = new Select(element);
		if (!selector.isMultiple()) allowHandleMultiple = false;
		String[] values = splitMultipleSelectValue(value, allowHandleMultiple);
        
        for (String singleValue : values) {
	        selector.selectByVisibleText(singleValue);
        }
        
        return element;
    }

	private static String[] splitMultipleSelectValue(String value, boolean allowHandleMultiple) {
		String[] values;
		if (allowHandleMultiple) {
        	values = value.split(MULTISELECT_VALUE_SPLIT_CHAR);
        } else {
        	values = new String[]{value};
        }
		return values;
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