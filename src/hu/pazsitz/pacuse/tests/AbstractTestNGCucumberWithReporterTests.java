package hu.pazsitz.pacuse.tests;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.testng.IHookCallBack;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import cucumber.api.Scenario;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import cucumber.api.testng.TestNGCucumberRunner;
import cucumber.runtime.Runtime;

public class AbstractTestNGCucumberWithReporterTests extends AbstractTestNGCucumberTests {
    private static TestNGCucumberRunner runner;
    private String filePath = "tests/cucumber/stepdefs/";
    
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
    
    public void setFilePath(String filePath) {
    	this.filePath = filePath;
    }
    
    public void afterReporting(Scenario scenario) {
    	cucumber.runtime.Runtime runtime = null;
        Field field;
        try {
            field = runner.getClass().getDeclaredField("runtime");
            field.setAccessible(true);
            
            runtime = (Runtime) field.get(runner);
        } catch (NoSuchFieldException | SecurityException | IllegalAccessException e) {
            e.printStackTrace();
        }

        if (scenario.getStatus().equals("undefined")) {
        	System.out.println(scenario.getName());
        	String fileName = StringUtils.capitalize(scenario.getName()).replaceAll("/[^A-Za-z0-9]/", "").replace(" ", "");
        	generateMissingSteps(fileName, runtime.getSnippets());
        }
    }
    
	private void generateMissingSteps(String className, List<String> snippets) {
		final String br = System.getProperty("line.separator");
		String fileStringStart = "package com.expedia.www.checkout.ui.tests.cucumber.stepdefs;" + br + br
			+ "import cucumber.api.PendingException;" + br + "import cucumber.api.java.en.Then;" + br + br
			+ "public class " + className + " { " + br;
		final String fileStringEnd = "} ";
		 
		try {
			File file = new File(filePath + className + ".java");
	        BufferedWriter bufferWritter = new BufferedWriter(new FileWriter(file));
	        bufferWritter.write(fileStringStart);
	        for (String snippet : snippets) {
	        	snippet.replace(br, br +"\\t");
	        	bufferWritter.write(snippet);
	        }
	        bufferWritter.write(fileStringEnd);
	        
	        bufferWritter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
    
}
