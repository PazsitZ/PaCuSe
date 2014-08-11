package hu.pazsitz.pacuse.tests;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import net.masterthought.cucumber.ReportBuilder;

/**
 * ReportGenerator.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
public class ReportGenerator {
	private static String outputReportPath = System.getProperty("PaCuSe.ReportGenerator.outputReport.path", "./reports/");
	private static String jsonReportPath = System.getProperty("PaCuSe.ReportGenerator.jsonReport.path", outputReportPath);

    public static void main(String[] args) {
    	
        File reportOutputDirectory = new File(outputReportPath + "/cucumber-pretty-html-report");
        System.out.println(reportOutputDirectory.getAbsolutePath());
        Logger.getLogger(ReportGenerator.class).info("fancy report path:" + reportOutputDirectory.getAbsolutePath());
        List<String> jsonReportFiles = new ArrayList<String>();
        jsonReportFiles.add(jsonReportPath + "/cucumber-report.json");
        String pluginUrlPath = "";

        String buildNumber = "1";
        String buildProjectName = "PaCuSe FrameWork";
        boolean skippedFails = false;
        boolean undefinedFails = false;
        boolean flashCharts = true;
        boolean runWithJenkins = false;
        boolean artifactsEnabled = false;
        String artifactConfig = "";
        boolean highCharts = true;

        ReportBuilder reportBuilder;
        try {
            reportBuilder = new ReportBuilder(
                jsonReportFiles,
                reportOutputDirectory,
                pluginUrlPath,
                buildNumber,
                buildProjectName,
                skippedFails,
                undefinedFails,
                flashCharts,
                runWithJenkins,
                artifactsEnabled,
                artifactConfig,
                highCharts
            );

            reportBuilder.generateReports();

            System.out.println("Fancy report generated successful");
        } catch (Exception e) {
        	Logger.getLogger(ReportGenerator.class).error(e.getMessage());
        }

    }
}
