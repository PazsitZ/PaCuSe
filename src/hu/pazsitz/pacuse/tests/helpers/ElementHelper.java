package hu.pazsitz.pacuse.tests.helpers;

import hu.pazsitz.pacuse.pages.AbstractPage;
import hu.pazsitz.pacuse.pages.AbstractWidget;
import hu.pazsitz.pacuse.tests.annotations.Widget;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ByIdOrName;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * ElementHelper.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
public class ElementHelper {
	private static Logger logger = Logger.getLogger(ElementHelper.class);
	
	/**
	 * Determines if element is visible (handles null element and NoSuchElementException)
	 * @param element
	 * @return boolean
	 */
	public static boolean isVisible(WebElement element) {
		try {
			return element.isDisplayed();
		} catch (NoSuchElementException e) {
			logger.debug("ElementHelper.isVisible: " + e.getMessage());
		} catch (NullPointerException e) {
			logger.debug("isVisible(WebElement) - element was null");
		}
		
		return false;
	}
	
	/**
	 * Determines if element is visible and also handles NoSuchElementException
	 * @param element
	 * @return boolean
	 */
	public static boolean isVisible(By by) {
		WebElement element = null;
		WebDriver driver = StepDefBase.getInstance().getWebDriver();
		try {
			element = driver.findElement(by);
		} catch (NoSuchElementException e) {
			logger.debug("ElementHelper.isVisible: " + e.getMessage());
			return false;
		}
		
		return isVisible(element);
	}
	
	/**
	 * Determines and handles if element doesn't exists
	 * @param element
	 * @return boolean
	 */
	public static boolean isExists(By by) {
		WebDriver driver = StepDefBase.getInstance().getWebDriver();
		
		return !driver.findElements(by).isEmpty();
		
	}
	
	/**
	 * Simply check element existence (handles null element and NoSuchElementException)
	 * @param element
	 * @return boolean
	 */
	public static boolean isExists(WebElement element) {
		try{
			element.isDisplayed();
			return true;
		} catch (NoSuchElementException e) {
			logger.debug("ElementHelper.isExists: " + e.getMessage());
		} catch (NullPointerException e) {
			logger.debug("isExists(WebElement) - element was null");
		}
		
		return false;
	}
	
	/**
	 * Gets the defined field By selector from the page model
	 * @param element
	 * @param page
	 * @return By or null if not found
	 */
	public static By getByFromElement(AbstractPage page, WebElement element) {
		if (page == null || !isExists(element)) {
			return null;
		}
		
		FindBy findBy = null;
		List<AbstractWidget> widgets = new ArrayList<>();
		for(Field field : page.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			WebElement reflectionElement;
			try {
				if (field.isAnnotationPresent(Widget.class)) {
					widgets.add((AbstractWidget)field.get(page));
					continue;
				}
				reflectionElement = (WebElement) field.get(page);
				if (reflectionElement.equals(element)) {
					findBy = field.getAnnotation(FindBy.class);
					break;
				}
			} catch (Exception e) {
				logger.info(e.getMessage());
			}
		}
		
		if (findBy != null) {
			return buildByFromLongFindBy(findBy);
		}
		
		return getByFromWidgetElement(widgets, element);
	}

	private static By getByFromWidgetElement(List<AbstractWidget> widgets, WebElement element) {
		for (AbstractWidget widget : widgets) {
			By by = getByFromElement(widget, element);
			if (by != null) {
				return by;
			}
		}
		
		return null;
	}
	
	private static By buildByFromLongFindBy(FindBy findBy) {
	    How how = findBy.how();
	    String using = findBy.using();

	    switch (how) {
	      case CLASS_NAME:
	        return By.className(using);

	      case CSS:
	        return By.cssSelector(using);

	      case ID:
	        return By.id(using);

	      case ID_OR_NAME:
	        return new ByIdOrName(using);

	      case LINK_TEXT:
	        return By.linkText(using);

	      case NAME:
	        return By.name(using);

	      case PARTIAL_LINK_TEXT:
	        return By.partialLinkText(using);

	      case TAG_NAME:
	        return By.tagName(using);

	      case XPATH:
	        return By.xpath(using);

	      default:
	        return null;
	    }
	}
	
	/**
	 * Determine a By Class for the given element
	 * @param element
	 * @return By
	 */
	public static By getByFromElement(WebElement element) {
		if (!element.getAttribute("id").isEmpty()) {
			return By.id(element.getAttribute("id"));
		}
		if (hasUniqueClass(element)) {
			return By.className(element.getAttribute("class"));
		}
		
		return By.xpath(xPathGenerate(element));
	}

	private static boolean hasUniqueClass(WebElement element) {
		String attribute = element.getAttribute("class");
		if (!attribute.isEmpty()) {
			WebDriver driver = StepDefBase.getInstance().getWebDriver();
			
			return (driver.findElements(By.className(attribute)).size() == 1);
		}
		return false;
	}

	private static String xPathGenerate(WebElement element) {
		String absoluteXPathJS = "function absoluteXPath(element) {"+
			"var comp, comps = [];"+
			"var parent = null;"+
			"var xpath = '';"+
			"var getPos = function(element) {"+
				"var position = 1, curNode;"+
				"if (element.nodeType == Node.ATTRIBUTE_NODE) {"+
					"return null;"+
				"}"+
				"for (curNode = element.previousSibling; curNode; curNode = curNode.previousSibling) {"+
					"if (curNode.nodeName == element.nodeName) {"+
						"++position;"+
					"}"+
				"}"+
				"return position;"+
			"};"+

		    "if (element instanceof Document) {"+
		    	"return '/';"+
		    "}"+

		    "for (; element && !(element instanceof Document); element = element.nodeType == Node.ATTRIBUTE_NODE ? element.ownerElement : element.parentNode) {"+
			    "comp = comps[comps.length] = {};"+
			    "switch (element.nodeType) {"+
				    "case Node.TEXT_NODE:"+
				    "comp.name = 'text()';"+
				    "break;"+
				    "case Node.ATTRIBUTE_NODE:"+
				    "comp.name = '@' + element.nodeName;"+
				    "break;"+
				    "case Node.PROCESSING_INSTRUCTION_NODE:"+
				    "comp.name = 'processing-instruction()';"+
				    "break;"+
				    "case Node.COMMENT_NODE:"+
				    "comp.name = 'comment()';"+
				    "break;"+
				    "case Node.ELEMENT_NODE:"+
				    "comp.name = element.nodeName;"+
				    "break;"+
			    "}"+
			    "comp.position = getPos(element);"+
		    "}"+
		
		    "for (var i = comps.length - 1; i >= 0; i--) {"+
			    "comp = comps[i];"+
			    "xpath += '/' + comp.name.toLowerCase();"+
			    "if (comp.position !== null) {"+
			    	"xpath += '[' + comp.position + ']';"+
			    "}"+
		    "}"+
		    "return xpath;"+
		"} return absoluteXPath(arguments[0]);";
		
		WebDriver driver = StepDefBase.getInstance().getWebDriver();
		return (String) ((JavascriptExecutor) driver).executeScript(absoluteXPathJS, element);
	}
	
}
