package hu.pazsitz.pacuse.tests;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.IHookCallBack;
import org.testng.ITestResult;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import cucumber.api.Scenario;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import cucumber.api.testng.TestNGCucumberRunner;
import cucumber.runtime.Runtime;

/**
 * AbstractTestNGCucumberWithReporterTests.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
public class AbstractTestNGCucumberWithReporterTests extends AbstractTestNGCucumberTests {
    private static TestNGCucumberRunner runner;
    private static String filePathOfStepDefinitions = "tests/cucumber/stepdefs/";
    
    @BeforeClass
    public void initLogging() {
    	String configPath = System.getProperty("PaCuSe.log4j.config", "");
        if (configPath.isEmpty()) {
        	PropertyConfigurator.configure(getLog4jProperties());
        } else {
        	PropertyConfigurator.configure(configPath);
        }
    }

	private Properties getLog4jProperties() {
		Properties prop = new Properties();
		//    	# Define the root logger with appender file
    	prop.setProperty("log", "./log4j");
    	prop.setProperty("log4j.rootLogger", "DEBUG, FILE");
    	//    	# Define the file appender
    	prop.setProperty("log4j.appender.FILE", "org.apache.log4j.FileAppender");
		prop.setProperty("log4j.appender.FILE.File", "${log}/log.out");
		//    	# Define the layout for file appender
    	prop.setProperty("log4j.appender.FILE.layout", "org.apache.log4j.PatternLayout");
		prop.setProperty("log4j.appender.FILE.layout.conversionPattern", "[%-5p] %d{yyyy MMM dd HH:mm:ss} %c{1} - %m%n");
		//    	#log4j.appender.FILE.layout.conversionPattern=%C{1}.%M %m %n
		prop.setProperty("log4j.logger.org.apache.http", "ERROR");
		prop.setProperty("log4j.logger.hu.pazsitz", "DEBUG");
		return prop;
	}
    
    @Override
    @Test(groups = "cucumber", description = "Runs Cucumber Features")
    public void run_cukes() throws IOException {
        runner = new TestNGCucumberRunner(getClass());
        runner.runCukes();
    }

    @Override
    public void run(IHookCallBack iHookCallBack, ITestResult iTestResult) {
        iHookCallBack.runTestMethod(iTestResult);
    }
    
    public TestNGCucumberRunner getRunner() {
        return runner;
    }
    
    public void setFilePathOfStepDefinitions(String filePath) {
    	AbstractTestNGCucumberWithReporterTests.filePathOfStepDefinitions = filePath;
    }
    
    public void afterReporting(Scenario scenario) {
    	cucumber.runtime.Runtime runtime = null;
        Field field;
        try {
            field = runner.getClass().getDeclaredField("runtime");
            field.setAccessible(true);
            
            runtime = (Runtime) field.get(runner);
        } catch (NoSuchFieldException | SecurityException | IllegalAccessException e) {
        	Logger.getLogger(this.getClass()).error(e.getMessage());
        }

        if (scenario.getStatus().equals("undefined")) {
        	Logger.getLogger(this.getClass()).info(scenario.getName());
        	String fileName = StringUtils.capitalize(scenario.getName()).replaceAll("/[^A-Za-z0-9]/", "").replace(" ", "");
        	generateMissingSteps(fileName, runtime.getSnippets());
        }
    }
    
	private void generateMissingSteps(String className, List<String> snippets) {
		final String br = System.getProperty("line.separator");
		String fileStringStart = "package " + this.getClass().getPackage().getName() + ".cucumber.stepdefs;" + br + br
			+ "import cucumber.api.DataTable;" + br 
			+ "import cucumber.api.PendingException;" + br 
			+ "import cucumber.api.java.en.When;" + br 
			+ "import cucumber.api.java.en.Given;" + br 
			+ "import cucumber.api.java.en.Then;" + br 
			+ br + br
			+ "public class " + className + " { " + br;
		final String fileStringEnd = "} ";
		 
		try {
			File file = new File(filePathOfStepDefinitions + className + ".java");
	        BufferedWriter bufferWritter = new BufferedWriter(new FileWriter(file));
	        bufferWritter.write(fileStringStart);
	        for (String snippet : snippets) {
	        	snippet.replace(br, br +"\\t");
	        	bufferWritter.write(snippet);
	        }
	        bufferWritter.write(fileStringEnd);
	        
	        bufferWritter.close();
		} catch (IOException e) {
			Logger.getLogger(this.getClass()).error(e.getMessage());
		}
		
	}
    
}
