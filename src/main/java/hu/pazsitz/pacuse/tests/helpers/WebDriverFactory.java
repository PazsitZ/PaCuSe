package hu.pazsitz.pacuse.tests.helpers;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * WebDriverFactory.java
 */
public class WebDriverFactory {
    private static Platform platform = Platform.WINDOWS;

    /* Browsers constants */
    public enum BrowserName {
        CHROME("chrome"),
        FIREFOX("firefox"),
        INTERNET_EXPLORER("ie"),
        HTML_UNIT("htmlunit");

        private final String name;

        BrowserName(String name) {
            this.name = name;
        }

        public static BrowserName getBrowser(String name) {
        	for (BrowserName browserName : BrowserName.values()){
        		if (browserName.name().equals(name))
        			return browserName;
        	}

        	return BrowserName.CHROME;
        }

    }

    private WebDriverFactory() {
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
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
            return getInstance(browserName);
        }

        if (BrowserName.CHROME.equals(browserName)) {
            setChromeDriver();
            capability = DesiredCapabilities.chrome();
            capability.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
            capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        }
        else if (BrowserName.FIREFOX.equals(browserName)) {
            capability = DesiredCapabilities.firefox();

            FirefoxProfile ffProfile = new FirefoxProfile();
            ffProfile.setAcceptUntrustedCertificates(true);

            // Authenication Hack for Firefox
            if (username != null && password != null) {
                ffProfile.setPreference("network.http.phishy-userpass-length", 255);
                capability.setCapability(FirefoxDriver.PROFILE, ffProfile);
            }

            capability.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
        } else if (BrowserName.INTERNET_EXPLORER.equals(browserName)) {
            capability = DesiredCapabilities.internetExplorer();
            capability.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
            capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
            capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        } else {
            capability = DesiredCapabilities.htmlUnit();
            // HTMLunit Check
            webDriver = new InternetExplorerDriver();

            return webDriver;
        }

        capability.setPlatform(Platform.WINDOWS);

        // Create Remote WebDriver
        try {
            webDriver = new RemoteWebDriver(new URL(gridHubUrl), capability);
        } catch (MalformedURLException e) {
        	Logger.getLogger(WebDriverFactory.class).error(e.getMessage());
        }

        return webDriver;
    }

    /*
     * Factory method to return a WebDriver instance given the browser to hit
     *
     * @param browser : String representing the local browser to hit
     *
     * @return WebDriver instance
     */
    public static WebDriver getInstance(BrowserName browser) {

        WebDriver webDriver = null;

        DesiredCapabilities capabilities;

        if (BrowserName.CHROME.equals(browser)) {
            setChromeDriver();
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
            chromeOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            webDriver = new ChromeDriver(chromeOptions);
        } else if (BrowserName.FIREFOX.equals(browser)) {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.setAcceptInsecureCerts(true);
            webDriver = new FirefoxDriver(firefoxOptions);
        } else if (BrowserName.INTERNET_EXPLORER.equals(browser)) {
            isSupportedPlatform(browser);
            setIEDriver();
            capabilities = DesiredCapabilities.internetExplorer();
            capabilities.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
            capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
            webDriver = new InternetExplorerDriver(capabilities);
        } else {
        	capabilities = DesiredCapabilities.internetExplorer();
            InternetExplorerOptions options = new InternetExplorerOptions(capabilities);
            // HTMLunit Check
            webDriver = new InternetExplorerDriver(capabilities);
        }

        return webDriver;
    }

    /*
     * Helper method to set ChromeDriver location into the right system property
     */
    private static void setChromeDriver() {
        String fileName = (platform == Platform.WINDOWS) ? "chromedriver.exe" : "chromedriver";
        String chromeDriverPath = new File("").getAbsolutePath() + "/lib/chromedriver/" + fileName ;
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
            is_supported = Platform.WINDOWS.is(current) || Platform.XP.is(current) || Platform.VISTA.is(current) || Platform.WIN8.is(current);
        }
        assert is_supported : "Platform is not supported by " + browser.name + " browser";
    }
}