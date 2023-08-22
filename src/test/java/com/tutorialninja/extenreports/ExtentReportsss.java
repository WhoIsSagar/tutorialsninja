package com.tutorialninja.extenreports;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportsss {

    private static ExtentReports extentreport; // Make it private and static
    private static Properties prop;
    private static FileInputStream ip;

    public static ExtentReports generatingExtentReport() throws IOException {
        if (extentreport == null) { // Create only if not already created
            extentreport = new ExtentReports();
            File file = new File("/Users/simran/eclipse-workspace/Tutorials_Ninja_Full_Project/test-output/ExtentReport/ExtentReport.html");
            ExtentSparkReporter reporter = new ExtentSparkReporter(file);

            reporter.config().setTheme(Theme.DARK);
            reporter.config().setReportName("TutorialsNinja Automation");
            reporter.config().setDocumentTitle("TutorialsNinja | Automation | Report");
            reporter.config().setTimeStampFormat("MM/dd/yyyy hh:mm:ss");
            extentreport.attachReporter(reporter);
            
            prop = new Properties();
            ip = new FileInputStream("/Users/simran/eclipse-workspace/Tutorials_Ninja_Full_Project/src/test/java/com/tutorialsninja/config/config.properties");
            prop.load(ip);
            extentreport.setSystemInfo("application url", prop.getProperty("url"));
            extentreport.setSystemInfo("browser name", prop.getProperty("browser"));
            extentreport.setSystemInfo("username", prop.getProperty("validEmail"));
            extentreport.setSystemInfo("password", prop.getProperty("validPassword"));
            extentreport.setSystemInfo("operating sytem", System.getProperty("os.name"));
            extentreport.setSystemInfo("operating sytemversion", System.getProperty("os.version"));
            extentreport.setSystemInfo("SDET Name", System.getProperty("user.name"));
            extentreport.setSystemInfo("Java Runtime Version", System.getProperty("java.runtime.version"));
        }
        return extentreport;
    }
}