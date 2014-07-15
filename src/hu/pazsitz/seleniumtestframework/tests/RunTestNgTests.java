package hu.pazsitz.seleniumtestframework.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

/**
 * RunTestNgTests.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
@Test(groups={"cucumber"})
@CucumberOptions(
    format = {"pretty", "html:reports/cucumber-html-report", "json:reports/cucumber-report.json"},
    features="src/main/java/hu/pazsit/seleniumtestframework/tests/cucumber/features",
    tags = { "@test" } //what tags to include(@)/exclude(@~)
)
// Default TestNG Runner, runs all of the features are available
public class RunTestNgTests extends AbstractTestNGCucumberTests {
    /**
     * Right now the Cucumber ReportGenerator doesn't support embed image only the HtmlReportGenerator
     */
    private final boolean DO_EMBED_SCREENSHOT = false;

    @BeforeClass
    public void init() {
        System.setProperty("cucumber.report.embed_scrennshot", Boolean.toString(DO_EMBED_SCREENSHOT));
    }

    @AfterClass
    public void TestSuiteTearDown() {
        if (DO_EMBED_SCREENSHOT == false) {
            ReportGenerator.main(null);
        }
    }
}