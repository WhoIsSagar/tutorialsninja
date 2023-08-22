package com.tutorialninja.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	public WebDriver driver;
	@FindBy(id = "input-email")
	private WebElement inputEmailTextField;
	
	@FindBy(id = "input-password")
	private WebElement inputPasswordTextField;
	
	@FindBy(css = "input.btn.btn-primary")
	private WebElement loginButton;
	
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public void inputEmail(String email) {
		inputEmailTextField.sendKeys(email);
	}
	
	public void inputPassword(String password) {
		inputPasswordTextField.sendKeys(password);
	}
	
	public AccountPage clickOnLoginButton() {
		loginButton.click();
		return new AccountPage(driver);
	}
	
	public AccountPage navigateToAccountPage(String email, String password) {
		inputEmailTextField.sendKeys(email);
		inputPasswordTextField.sendKeys(password);
		loginButton.click();
		return new AccountPage(driver);
		
	}
	
}
