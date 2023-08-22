package com.tutorialninja.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	public WebDriver driver;
	//declaration
	@FindBy(linkText = "My Account")
	private WebElement myAccountButton;
	
	@FindBy(linkText = "Login")
	private WebElement loginButton;
	
	
	//initialization
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnMyAccountLink() {
		myAccountButton.click();	
	}
	
	public LoginPage clickOnLoginButton() {
		loginButton.click();
		return new LoginPage(driver);
		
	}
	
	public LoginPage navigateToLoginPage() {
		myAccountButton.click();	
		loginButton.click();
		return new LoginPage(driver);
	}
	
	
	
}
