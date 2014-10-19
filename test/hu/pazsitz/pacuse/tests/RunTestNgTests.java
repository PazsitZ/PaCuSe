package hu.pazsitz.pacuse.tests;

import hu.pazsitz.pacuse.tests.helpers.StepDefBase;
import hu.pazsitz.pacuse.tests.helpers.WebDriverFactory;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;

/**
 * RunTestNgTests.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
@Test(groups={"cucumber"})
@CucumberOptions(
    format = {"pretty", "html:reports/cucumber-html-report", "json:reports/cucumber-report.json"},
    features="test/hu/pazsitz/pacuse/tests/cucumber/features",
    tags = { "@test" }, //what tags to include(@)/exclude(@~)
    snippets = SnippetType.CAMELCASE
)
// Default TestNG Runner, runs all of the features are available
public class RunTestNgTests extends AbstractTestNGCucumberWithReporterTests {

    @BeforeClass
    public void init() {
    	setFilePathOfStepDefinitions("test/main/java/hu/pazsitz/pacuse/tests/cucumber/stepdefs/");
        System.setProperty("cucumber.report.embed_screenshot", Boolean.toString(true));
        System.setProperty("PaCuSe.browser", WebDriverFactory.BrowserName.CHROME.name());
        System.setProperty("PaCuSe.WebDriver.screenshot.path", "reports/");
//        System.setProperty("PaCuSe.WebDriver.gridUrl", "http://127.0.0.1:5555/wd/hub");
        System.setProperty("PaCuSe.ReportGenerator.outputReport.path", "reports/");
        System.setProperty("PaCuSe.ReportGenerator.jsonReport.path", "reports/");
        System.setProperty("PaCuSe.log4j.config", "");
    }

    @AfterClass
    public void TestSuiteTearDown() {
    	StepDefBase.getInstance().tearDown();
        ReportGenerator.main(null);
    }
}