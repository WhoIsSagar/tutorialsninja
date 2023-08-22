package com.tutorialsninja.testBase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import Utilities.Utilities;

public class TestBase {
	
	public WebDriver driver;
	public ChromeOptions options;
	public static Properties prop;
	public FileInputStream ip;
	
	public TestBase() {
	    prop = new Properties();
	    try {
	        String filePath = "/Users/simran/eclipse-workspace/Tutorials_Ninja_Full_Project/src/test/java/com/tutorialsninja/config/config.properties";
	        ip = new FileInputStream(filePath);
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
	    try {
	        prop.load(ip);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	
	public WebDriver initializeBrowserAndOpenApplication(String browserName) {
		if(browserName.equalsIgnoreCase("chrome")) {
			options = new ChromeOptions();
			options.addArguments("--start-maximized");
			options.addArguments("--incognito");
			options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation", "disable-infobars"));
			driver = new ChromeDriver(options);
		}else if(browserName.equals("firefox")) {
			driver = new FirefoxDriver();
		}else if(browserName.equals("Edge")) {
			driver = new EdgeDriver();
		}
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGELOAD_TIME));
		driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(Utilities.SCRIPTLOAD_TIME));
		driver.get(prop.getProperty("url"));
		
		return driver;
	}

}
