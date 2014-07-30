package hu.pazsitz.pacuse.tests.helpers;

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

    public static void waitForTagNameDisplayed(String inputTag, int seconds) {
        final String tag = inputTag;
        new WebDriverWait(webDriver, seconds).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver d) {
                return d.findElement(By.tagName(tag)).isDisplayed();
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
    
    public static void waitForIdDisplayed(String idName, int seconds) {
        final String name = idName;
        new WebDriverWait(webDriver, seconds).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver d) {
                return d.findElement(By.id(name)).isDisplayed();
            }
        });
    }

    public static void waitForClassDisplayed(String className, int seconds) {
        final String name = className;
        new WebDriverWait(webDriver, seconds).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver d) {
                return d.findElement(By.className(name)).isDisplayed();
            }
        });
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
