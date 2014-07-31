package hu.pazsitz.pacuse.tests.helpers;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Waiters.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
public class Waiters {
    private static WebDriver webDriver;

    public static void waitForUrl(String inputUrlPart, int seconds) {
        final String ulrPart = inputUrlPart;
        new WebDriverWait(webDriver, seconds).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver d) {
                return d.getCurrentUrl().contains(ulrPart);
            }
        });
    }

    public static void waitForMultipleUrl(int seconds, String... inputUrlParts) {
        final String[] urlParts = inputUrlParts;
        new WebDriverWait(webDriver, seconds).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver d) {
                for(String urlPart : urlParts) {
                    if( d.getCurrentUrl().contains(urlPart) ) {
                        return true;
                    }
                }
                return false;
            }
        });
    }

    public static void waitForMultipleUrlOnPayments(int seconds, String... inputUrlParts) {
        final String[] urlParts = inputUrlParts;
        final String startUrl = webDriver.getCurrentUrl();
        new WebDriverWait(webDriver, seconds).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver d) {
                for(String urlPart : urlParts) {
                    String url = d.getCurrentUrl();
                    try{
                        if(
                            url.contains(urlPart) && !startUrl.equals(url)
                            ||
                            startUrl.equals(url) && d.findElement(By.id("page-level-error")).isDisplayed()
                        ) {
                            return true;
                        }
                    } catch(Exception e) { return false; }
                }
                return false;
            }
        });
    }

    public static void waitForDisplayed(WebElement webElement, int seconds) {
    	final WebElement element = webElement;
    	new WebDriverWait(webDriver, seconds).until(new ExpectedCondition<Boolean>() {
    		@Override
    		public Boolean apply(WebDriver d) {
    			return element.isDisplayed();
    		}
    	});
    }
    
    public static void waitForElementDisappears(By by, int seconds) {
    	waitForElementDisappears(by, seconds, 0);
    }
    
    public static void waitForElementDisappears(By by, int seconds, int startDelay) {
    	webDriver.manage().timeouts().implicitlyWait(startDelay, TimeUnit.SECONDS);
    	final By byStatement = by;
    	new WebDriverWait(webDriver, seconds).until(new ExpectedCondition<Boolean>() {
    		@Override
    		public Boolean apply(WebDriver d) {
    			return d.findElements(byStatement).isEmpty();
    		}
    	});
    }
    
    public static void waitForElementDisplayed(By by, int seconds) {
    	final By byStatement = by;
    	new WebDriverWait(webDriver, seconds).until(new ExpectedCondition<Boolean>() {
    		@Override
    		public Boolean apply(WebDriver d) {
    			return d.findElement(byStatement).isDisplayed();
    		}
    	});
    }
    
    public static void waitForTagNameDisplayed(String inputTag, int seconds) {
    	waitForElementDisplayed(By.tagName(inputTag), seconds);
    }
    
    public static void waitForIdDisplayed(String idName, int seconds) {
    	waitForElementDisplayed(By.id(idName), seconds);
    }

    public static void waitForClassDisplayed(String className, int seconds) {
    	waitForElementDisplayed(By.className(className), seconds);
    }

    public static void waitForDocumentReady(int seconds) {
        new WebDriverWait(webDriver, seconds).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor)webDriver).executeScript("return document.readyState").equals("complete");
            }
        });
    }

    public static void waitForCondition(ExpectedCondition<Boolean> condition, int seconds) {
        new WebDriverWait(webDriver, seconds).until(condition);
    }

    public static void setWebDriver(WebDriver webDriver) {
        Waiters.webDriver = webDriver;
    }
}
