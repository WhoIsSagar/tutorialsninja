package com.tutorialninja.Test;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.tutorialninja.testData.FetchExcelData;
import com.tutorialsninja.testBase.TestBase;

import Utilities.Utilities;
public class RegisterNewAccountTest extends TestBase {

	public static WebDriver driver; 
	public static Select select;
	public static SoftAssert asserts;
	public static Actions action; 
	
	@BeforeMethod
	public static void basic(){
		
		TestBase base = new TestBase();
		driver = base.initializeBrowserAndOpenApplication("Chrome");
		
	}
	
	@Test(priority = 1, dataProvider = "RegisterData", dataProviderClass = FetchExcelData.class )
	public static void registerTestWithAllMendatoryFields(String firstName, String lastName,String email, String telephoneNumber ,String password, String confirmPassword) throws InterruptedException {

		asserts = new SoftAssert();
		action = new Actions(driver);	
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Register")).click();
		driver.findElement(By.id("input-firstname")).sendKeys(firstName);
		driver.findElement(By.id("input-lastname")).sendKeys(lastName);
		driver.findElement(By.id("input-email")).sendKeys("sagar"+Utilities.getEmail()+"@gmail.com");
		driver.findElement(By.id("input-telephone")).sendKeys(telephoneNumber);
		driver.findElement(By.id("input-password")).sendKeys(password);
		driver.findElement(By.id("input-confirm")).sendKeys(confirmPassword);
		driver.findElement(By.xpath("//input[@name ='agree' and @value = '1']")).click();
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		String actualAccountCreationMessagedriver = driver.findElement(By.xpath("//div[@id = 'content']/child::p[1]")).getText();
		String expectedAccountCreationMessage = "actualAccountCreationMessage";
		AssertJUnit.assertTrue(actualAccountCreationMessagedriver.equals(expectedAccountCreationMessage));
			
	}
	
	
	@Test(priority = 2 )
	public static void verifyRegisterWithBlankSubmission() {
		asserts = new SoftAssert();			
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Register")).click();
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		String actualAccountCreationMessagedriver = driver.findElement(By.xpath("//div[@id = 'content']/child::p[1]")).getText();
		String expectedAccountCreationMessage = "actualAccountCreationMessage";
		AssertJUnit.assertTrue(actualAccountCreationMessagedriver.equals(expectedAccountCreationMessage));
		String actualMustAgreePrivacyPolicyMessage = driver.findElement(By.cssSelector("div.alert.alert-danger.alert-dismissible>i")).getText();
		String expectedMustAgreePrivacyPolicyMessage = "Warning: You must agree to the Privacy Policy!";
		AssertJUnit.assertTrue(actualMustAgreePrivacyPolicyMessage.contains(expectedMustAgreePrivacyPolicyMessage));
		
		
	}
	
	
	@AfterMethod
	public static void tearDown() {
		driver.quit();
	}
	
	
	
	
}
