package com.tutorialninja.Test;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.tutorialninja.page.AccountPage;
import com.tutorialninja.page.HomePage;
import com.tutorialninja.page.LoginPage;
import com.tutorialninja.testBase.TestBase;

public class LoginTest extends TestBase {

	public static WebDriver driver;
	public static SoftAssert asserts;
	public static LoginPage loginpage;
	public static AccountPage accountpage;
	public static HomePage homepage; 
	
	public LoginTest(){
		super();
	}
	@BeforeMethod
	public static void buildUp() {
		TestBase base = new TestBase();
		driver = base.initializeBrowserAndOpenApplication("Chrome");
		homepage = new HomePage(driver);
		loginpage = homepage.navigateToLoginPage();
		
		
	}
	
	@Test
	public static void verifyLoginWithCorrectCridentials() {

		asserts = new SoftAssert();
		accountpage = loginpage.navigateToAccountPage(prop.getProperty("validEmail"), prop.getProperty("validPassword"));
		String expectedLoginVerifyMessage = "Edit your account information";
		asserts.assertTrue((accountpage.getLoginSuccessMessage()).contains(expectedLoginVerifyMessage));
	
	}
	
	
	@AfterMethod
	public static void tearDown() {
		driver.quit();
	}
	
}
