package com.tutorialninja.Test;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.tutorialsninja.testBase.TestBase;
import com.tutorialninja.testData.FetchExcelData;




public class EndToEndaPurchasePositiveTest extends TestBase {

	public static WebDriver driver; 
	public static Select select;
	public static SoftAssert asserts;
	public static Actions action;
	
	
	

	@BeforeMethod
	public static void basic(){
			TestBase base = new TestBase();
		    driver = base.initializeBrowserAndOpenApplication(base.prop.getProperty("browser"));

		
	}
	
	@Test(priority = 1)
	public static void endToEnd() throws InterruptedException {
		

		asserts = new SoftAssert();
		action = new Actions(driver);		
		TestBase base = new TestBase();
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("input-email")).sendKeys(base.prop.getProperty("validEmail"));
		driver.findElement(By.id("input-password")).sendKeys(base.prop.getProperty("validPassword"));
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		String expectedEditYourAccountMessage = "Edit your account information";
		String actualEditYourAccountMessage = driver.findElement(By.linkText("Edit your account information")).getText();		
		asserts.assertTrue(actualEditYourAccountMessage.contains(expectedEditYourAccountMessage),"Firsst");
		driver.findElement(By.cssSelector("input.form-control.input-lg")).sendKeys("HP");
		driver.findElement(By.cssSelector("i.fa.fa-search")).click();
		boolean isProductPresent = driver.findElement(By.linkText("HP LP3065")).isDisplayed();
		asserts.assertTrue(isProductPresent,"third Last");
		driver.findElement(By.linkText("HP LP3065")).click();
		driver.findElement(By.cssSelector("i.fa.fa-calendar")).click();
		String calendarMonthYear = driver.findElement(By.xpath("//div[@class = 'datepicker']/child::div[@class = 'datepicker-days']/child::table/child::thead/child::tr/child::th[@class = 'picker-switch']")).getText();
		System.out.println(calendarMonthYear);
		String calendarMonth = calendarMonthYear.split(" ")[0];
		String calendarYear = calendarMonthYear.split(" ")[1];
		while(! (calendarMonth.equals("January") && calendarYear.equals("2015"))) {
			driver.findElement(By.xpath("//div[@class = 'datepicker']/child::div[@class ='datepicker-days']/child::table/child::thead/child::tr[1]/th[3]")).click();
			 calendarMonthYear = driver.findElement(By.xpath("//div[@class = 'datepicker']/child::div[@class = 'datepicker-days']/child::table/child::thead/child::tr/child::th[@class = 'picker-switch']")).getText();
			calendarMonth = calendarMonthYear.split(" ")[0];
			 calendarYear = calendarMonthYear.split(" ")[1];	
		}
		int date = 20;
		String todayDate = Integer.toString(date);
		driver.findElement(By.xpath("//div[@class = 'datepicker']/child::div[@class = 'datepicker-days']/child::table/child::tbody/child::tr/child::td[text() = '" + todayDate + "'and @class = 'day']")).click();
		String qty = "20"; 
		action.moveToElement(driver.findElement(By.id("input-quantity"))).click().keyDown(Keys.META).sendKeys("a").keyUp(Keys.META).sendKeys(qty).build().perform();
		driver.findElement(By.id("button-cart")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[text() = 'Shopping Cart']")).click();
		driver.findElement(By.cssSelector("a.btn.btn-primary")).click();
		driver.findElement(By.xpath("//input[@type = 'radio' and @value = 'new']")).click();
		driver.findElement(By.id("input-payment-firstname")).sendKeys("Singh");
		driver.findElement(By.id("input-payment-lastname")).sendKeys("Singh");
		driver.findElement(By.id("input-payment-address-1")).sendKeys("261 Jonga Jonga ");
		driver.findElement(By.id("input-payment-city")).sendKeys("Brampton");
		driver.findElement(By.id("input-payment-postcode")).sendKeys("L6X 5M4");
		select = new Select(driver.findElement(By.id("input-payment-country")));
		select.selectByVisibleText("Canada");
		select = new Select(driver.findElement(By.id("input-payment-zone")));
		select.selectByVisibleText("Ontario");
		driver.findElement(By.xpath("//input[@class = 'btn btn-primary']")).click();
		driver.findElement(By.id("button-payment-address")).click();
		driver.findElement(By.id("button-shipping-address")).click();
		driver.findElement(By.id("button-shipping-method")).click();
		driver.findElement(By.xpath("//input [@name ='agree' and @value = '1']")).click();
		driver.findElement(By.id("button-payment-method")).click();
		driver.findElement(By.id("button-confirm")).click();
		
		String actualConfirmedOrderMessage = driver.findElement(By.xpath("//div[@id ='content']/child::p[1]")).getText();
		String expectedConfirmedOrderMessage = "Your order has been successfully processed!";	
		asserts.assertTrue(actualConfirmedOrderMessage.equals(expectedConfirmedOrderMessage),"Last Assertion");


		
		
		
	}
	
	
	@AfterMethod
	public static void tearDown()
	{

		asserts.assertAll();
		
	}
	
}
