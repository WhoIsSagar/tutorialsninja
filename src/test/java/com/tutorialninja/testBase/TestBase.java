package com.tutorialninja.testBase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.openqa.selenium.By;
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
	public static int day;
	public static int year;
	
	public TestBase() {
	    prop = new Properties();
	    try {
	        String filePath = "/Users/simran/eclipse-workspace/Tutorials_Ninja_Full_Project/src/test/java/com/tutorialninja/config/config.properties";
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

	public void datePicker(String day, String month, String year) {
		 this.day = Integer.parseInt(day);
		 this.year = Integer.parseInt(year);
		 
		 String monthYear = driver.findElement(By.xpath("//div[@class= 'datepicker']/div[@class='datepicker-days']/table/thead/tr/th[@class='picker-switch']")).getText();
		System.out.println(monthYear);
		 String initialMonth = monthYear.split(" ")[0];
		 int initialYear = Integer.parseInt((monthYear).split(" ")[1]);
		 
		 while(!(initialMonth.equals(month) && this.year == initialYear)) {
			 driver.findElement(By.xpath("//div[@class= 'datepicker']/div[@class='datepicker-days']/table/thead/tr/th[@class='picker-switch']/following-sibling::th")).click();
			  monthYear = driver.findElement(By.xpath("//div[@class= 'datepicker']/div[@class='datepicker-days']/table/thead/tr/th[@class='picker-switch']")).getText();
			  initialMonth = monthYear.split(" ")[0];
			  initialYear = Integer.parseInt((monthYear).split(" ")[1]);
			
			  
		 }
		 driver.findElement(By.xpath("//div[@class = 'datepicker-days']//td[@class= 'day' and text()='"+this.day+"']")).click();
		 
		 
	
		
		 
	}
}
