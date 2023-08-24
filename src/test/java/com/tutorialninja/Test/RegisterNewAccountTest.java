package com.tutorialninja.Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.tutorialninja.page.AccountSuccesCreatedPage;
import com.tutorialninja.page.HomePage;
import com.tutorialninja.page.RegisterAccountPage;
import com.tutorialninja.testBase.TestBase;
import com.tutorialninja.testData.FetchExcelData;

import Utilities.Utilities;
public class RegisterNewAccountTest extends TestBase {

	public static WebDriver driver; 
	public static Select select;
	public static SoftAssert asserts;
	public static Actions action; 
	public static HomePage homepage;
	public static RegisterAccountPage registerpage;
	public static AccountSuccesCreatedPage accountsuccesspage;
	
	public RegisterNewAccountTest() {
		super();
	}
	@BeforeMethod
	public static void basic(){
		
		TestBase base = new TestBase();
		driver = base.initializeBrowserAndOpenApplication("Chrome");
		HomePage homepage = new HomePage(driver);
		registerpage = homepage.navigateToRegisterPage();
		
		
	}
	
	@Test(priority = 1, dataProvider = "RegisterData", dataProviderClass = FetchExcelData.class )
	public static void registerTestWithAllMendatoryFields(String firstName, String lastName,String email, String telephoneNumber ,String password, String confirmPassword) throws InterruptedException {

		asserts = new SoftAssert();
		action = new Actions(driver);	
		registerpage = new RegisterAccountPage(driver);
		registerpage.enterAllDetails(firstName,lastName,("sagar"+Utilities.getEmail()+"@gmail.com"), telephoneNumber,password,confirmPassword);
		registerpage.checkPolicyAgreeButton();
		accountsuccesspage = registerpage.clickOnRegisterAccountButton();	
		String actualAccountCreationMessagedriver = accountsuccesspage.getaccounSuccesCreatedMessage();
		String expectedAccountCreationMessage = "actualAccountCreationMessage";
		asserts.assertTrue(actualAccountCreationMessagedriver.equals(expectedAccountCreationMessage));
			
	}
	
	
	@Test(priority = 2 )
	public static void verifyRegisterWithBlankSubmission() {
		asserts = new SoftAssert();	
		registerpage = new RegisterAccountPage(driver);
		registerpage.clickOnRegisterAccountButton();
		String actualMustAgreePrivacyPolicyMessage = registerpage.getMustAgreePrivacyPolicyMessage();
		String expectedMustAgreePrivacyPolicyMessage = "Warning: You must agree to the Privacy Policy!";
		asserts.assertTrue(actualMustAgreePrivacyPolicyMessage.contains(expectedMustAgreePrivacyPolicyMessage));
		
		
	}
	
	
	@AfterMethod
	public static void tearDown() {
		driver.quit();
	}
	
	
	
	
}
