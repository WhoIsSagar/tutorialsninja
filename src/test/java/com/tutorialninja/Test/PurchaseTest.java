package com.tutorialninja.Test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.tutorialninja.page.AccountPage;
import com.tutorialninja.page.CartPage;
import com.tutorialninja.page.CheckoutPage;
import com.tutorialninja.page.HomePage;
import com.tutorialninja.page.LaptopPage;
import com.tutorialninja.page.LoginPage;
import com.tutorialninja.testBase.TestBase;

public class PurchaseTest extends TestBase {
	public static WebDriver driver;
	public static SoftAssert softassert;
	public static TestBase base;
	public static HomePage homepage;
	public static LoginPage loginpage;
	public static LaptopPage laptoppage;
	public static AccountPage accountpage;
	public static CartPage cartpage;
	public static CheckoutPage checkoutpage;
	public PurchaseTest() {
		super();
	}
	
	@BeforeMethod
	public void setup() {
		base = new TestBase();
		driver = base.initializeBrowserAndOpenApplication("chrome");
		softassert = new SoftAssert();
		homepage = new HomePage(driver);
		loginpage = homepage.navigateToLoginPage();
		accountpage = loginpage.navigateToAccountPage(prop.getProperty("validEmail"), prop.getProperty("validPassword"));
		laptoppage = accountpage.searchProduct("HP");
		boolean isLaptopPresent = laptoppage.isHpLp3065Present();
		softassert.assertTrue(isLaptopPresent);
		laptoppage.clickOnHpLp3065();
		laptoppage.clickOnCalendar();
		base.datePicker("10", "January", "2015");
		laptoppage.addQuantity("2");
		laptoppage.clickOnAddToCartButton();
		String addtoCartMessage = laptoppage.successAddToCartMessage();
		softassert.assertEquals(addtoCartMessage, "Success: You have added "+laptoppage.HpLp3065Name()+" to your shopping cart");
		cartpage = laptoppage.clickOnShowCartButton();
		checkoutpage = cartpage.clickOnCheckOutButton();
		checkoutpage.clickOnNewAddressButton();
		
	}
	
	@Test
	public void purchasePositive() {
		
	}
}
