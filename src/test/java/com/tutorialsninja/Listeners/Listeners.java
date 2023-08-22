package com.tutorialsninja.Listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsninja.extenreports.ExtentReportsss;

import Utilities.Utilities;

public class Listeners extends TestListenerAdapter {

    public ExtentReports extentReport;
    public ExtentTest extentTest;
    public WebDriver driver;

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Project Execution Started");
        try {
            extentReport = ExtentReportsss.generatingExtentReport();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        extentTest = extentReport.createTest(result.getName()); // Initialize extentTest
        extentTest.log(Status.INFO, result.getName() + " started executing");
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        extentTest.log(Status.PASS, tr.getName() + " passed");
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        try {
            driver = (WebDriver) tr.getTestClass().getRealClass().getDeclaredField("driver").get(tr.getInstance());
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e1) {
            e1.printStackTrace();
        }
        String destinationPath = Utilities.captureScreenShotCode(driver, tr.getName());
        extentTest.addScreenCaptureFromPath(destinationPath);
        extentTest.log(Status.FAIL, tr.getName() + " -> failed");
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        extentTest.log(Status.SKIP, tr.getName() + " -> skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Project Execution Finished");
        extentReport.flush();
        String pathofExtentReport = System.getProperty("/Users/simran/eclipse-workspace/Tutorials_Ninja_Full_Project/test-output/ExtentReport/ExtentReport.html");
        File extentReportpath = new File(pathofExtentReport);
        try {
            Desktop.getDesktop().browse(extentReportpath.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
