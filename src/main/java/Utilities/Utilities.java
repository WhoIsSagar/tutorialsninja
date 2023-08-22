package Utilities;


import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;


public class Utilities {
	public static String email;
	public static Date date;
	public static final int IMPLICIT_WAIT = 100;
	public static final int PAGELOAD_TIME= 100;
	public static final int SCRIPTLOAD_TIME = 100;
	
	public static String getEmail() {
		
	
		date = new Date();
		
		String email = date.toString().replaceAll(" ","_").replaceAll(":","_");	
		
		return email;
		
	}
public static String captureScreenShotCode(WebDriver driver, String testName) {
		
		File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destinationPath = "/Users/simran/eclipse-workspace/Tutorials_Ninja_Full_Project/test-output/Screenshots" + testName + ".png";
		try {
			FileHandler.copy(source, new File(destinationPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return destinationPath;
	}
}
