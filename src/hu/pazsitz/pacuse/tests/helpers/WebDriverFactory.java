package hu.pazsitz.pacuse.tests.helpers;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * WebDriverFactory.java
 */
public class WebDriverFactory {

    /* Browsers constants */
    enum BrowserName {
        CHROME("chrome"),
        FIREFOX("firefox"),
        INTERNET_EXPLORER("ie"),
        SAFARI("safari"),
        HTML_UNIT("htmlunit");

        private final String name;

        BrowserName(String name) {
            this.name = name;
        }

    }

    private WebDriverFactory() {
    }

    /*
     * Factory method to return a RemoteWebDriver instance given the url of the
     * Grid hub and a Browser instance.
     *
     * @param gridHubUrl : grid hub URI
     *
     * @param browser : Browser object containing info around the browser to hit
     *
     * @param username : username for BASIC authentication on the page to test
     *
     * @param password : password for BASIC authentication on the page to test
     *
     * @return RemoteWebDriver
     */
    public static WebDriver getInstance(String gridHubUrl, BrowserName browserName, String username, String password) {

        WebDriver webDriver = null;

        DesiredCapabilities capability = new DesiredCapabilities();
        capability.setJavascriptEnabled(true);

        // In case there is no Hub
        if (gridHubUrl == null || gridHubUrl.length() == 0) {
            return getInstance(browserName, username, password);
        }

        if (BrowserName.CHROME.equals(browserName)) {
            capability = DesiredCapabilities.chrome();
        }
        else if (BrowserName.FIREFOX.equals(browserName)) {
            capability = DesiredCapabilities.firefox();

            FirefoxProfile ffProfile = new FirefoxProfile();

            // Authenication Hack for Firefox
            if (username != null && password != null) {
                ffProfile.setPreference("network.http.phishy-userpass-length", 255);
                capability.setCapability(FirefoxDriver.PROFILE, ffProfile);
            }

            capability.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
//        } else if (BrowserName.INTERNET_EXPLORER.equals(browserName)) {
//            capability = DesiredCapabilities.internetExplorer();
//            capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
//        } else if (BrowserName.SAFARI.equals(browserName)) {
//            capability = DesiredCapabilities.safari();
        } else {
            capability = DesiredCapabilities.htmlUnit();
            // HTMLunit Check
            webDriver = new HtmlUnitDriver(true);

            return webDriver;
        }

        capability.setPlatform(Platform.WINDOWS);

        // Create Remote WebDriver
        try {
            webDriver = new RemoteWebDriver(new URL(gridHubUrl), capability);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return webDriver;
    }

    public static WebDriver getInstance(BrowserName browser) {
        return getInstance(browser, null, null);
    }

    /*
     * Factory method to return a WebDriver instance given the browser to hit
     *
     * @param browser : String representing the local browser to hit
     *
     * @param username : username for BASIC authentication on the page to test
     *
     * @param password : password for BASIC authentication on the page to test
     *
     * @return WebDriver instance
     */
    public static WebDriver getInstance(BrowserName browser, String username, String password) {

        WebDriver webDriver;

        if (BrowserName.CHROME.equals(browser)) {
            setChromeDriver();

            webDriver = new ChromeDriver();
        }
        else if (BrowserName.FIREFOX.equals(browser)) {

            FirefoxProfile ffProfile = new FirefoxProfile();

            // Authenication Hack for Firefox
            if (username != null && password != null) {
                ffProfile.setPreference("network.http.phishy-userpass-length", 255);
            }

            webDriver = new FirefoxDriver(ffProfile);

//        } else if (BrowserName.INTERNET_EXPLORER.equals(browser)) {
//            isSupportedPlatform(browser);
//            setIEDriver();
//            webDriver = new InternetExplorerDriver();
//
//        } else if (BrowserName.SAFARI.equals(browser)) {
//            isSupportedPlatform(browser);
//            webDriver = new SafariDriver();

        } else {

            // HTMLunit Check
            webDriver = new HtmlUnitDriver(true);
        }

        return webDriver;
    }

    /*
     * Helper method to set ChromeDriver location into the right system property
     */
    private static void setChromeDriver() {
        String chromeDriverPath = new File("").getAbsolutePath() + "/lib/chromedriver/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
    }

    private static void setIEDriver() {
        String IEPath = System.getenv("ProgramFiles(x86)") + "/Internet Explorer/iexplore.exe";
        System.setProperty("webdriver.ie.driver", IEPath);
    }

    private static void isSupportedPlatform(BrowserName browser) {
        boolean is_supported = true;
        Platform current = Platform.getCurrent();
        if (BrowserName.INTERNET_EXPLORER.equals(browser)) {
            is_supported = Platform.WINDOWS.is(current);
        } else if (BrowserName.SAFARI.equals(browser)) {
            is_supported = Platform.MAC.is(current) || Platform.WINDOWS.is(current);
        }
        assert is_supported : "Platform is not supported by " + browser.name + " browser";
    }
}