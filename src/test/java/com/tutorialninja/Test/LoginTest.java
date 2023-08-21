package com.tutorialninja.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.tutorialsninja.page.AccountPage;
import com.tutorialsninja.page.HomePage;
import com.tutorialsninja.page.LoginPage;
import com.tutorialsninja.testBase.TestBase;

public class LoginTest {

	public static WebDriver driver;
	public static SoftAssert asserts; 
	
	@BeforeMethod
	public static void buildUp() {
		TestBase base = new TestBase();
		driver = base.initializeBrowserAndOpenApplication("Chrome");
		HomePage homepage = new HomePage(driver);
		homepage.clickOnMyAccountLink();
		homepage.clickOnLoginButton();
		
		
	}
	
	@Test
	public static void verifyLoginWithCorrectCridentials() {
		TestBase base = new TestBase();
		LoginPage loginPage = new LoginPage(driver);
		AccountPage accountpage = new AccountPage(driver);
		asserts = new SoftAssert();
		loginPage.inputEmail(base.prop.getProperty("validEmail"));
		loginPage.inputPassword(base.prop.getProperty("validPassword"));
		loginPage.clickOnLoginButton();
		String expectedLoginVerifyMessage = "Edit your account information";
		asserts.assertTrue((accountpage.getLoginSuccessMessage()).contains(expectedLoginVerifyMessage));
		
	}
	
	
	@AfterMethod
	public static void tearDown() {
		driver.quit();
	}
	
}
