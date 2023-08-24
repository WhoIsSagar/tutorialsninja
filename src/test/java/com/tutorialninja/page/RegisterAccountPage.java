package com.tutorialninja.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterAccountPage {

	public static WebDriver driver;
	
	@FindBy(id = "input-firstname")
	private WebElement inputFirstName;
	
	@FindBy(id = "input-lastname")
	private WebElement inputLastName;
	
	@FindBy(id = "input-email")
	private WebElement inputEmail;
	
	@FindBy(id = "input-telephone")
	private WebElement inputTelephone;
	
	@FindBy(id ="input-password")
	private WebElement inputPassword;
	
	@FindBy(id = "input-confirm")
	private WebElement inputConfirmPassword;
	
	@FindBy(xpath = "//input[@name ='agree' and @value = '1']")
	private WebElement policyAgreeButton;
	
	@FindBy(css ="input.btn.btn-primary")
	private WebElement registerAccountButton;
	
	@FindBy(css ="div.alert.alert-danger.alert-dismissible>i")
	private WebElement mustAgreePrivacyPolicyErrorMessage;
	
	public RegisterAccountPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	public void inputFirstName(String firstName) {
		inputFirstName.sendKeys(firstName);
	}
	
	public void inputLastName(String lastName) {
	inputLastName.sendKeys(lastName);
	
	}
	
	public void inputEmail(String email) {
		inputEmail.sendKeys(email);
	}
	
	public void inputTelephone(String telephone) {
		inputTelephone.sendKeys(telephone);
		
	}
	
	public void inputPassword(String password) {
		inputPassword.sendKeys(password);
	}
	
	public void inputConfirmPassword(String confirmPassword) {
		inputConfirmPassword.sendKeys(confirmPassword);
	}
	
	public void checkPolicyAgreeButton() {
		policyAgreeButton.click();
	}
	
	
	public AccountSuccesCreatedPage clickOnRegisterAccountButton() {
		registerAccountButton.click();
		return new AccountSuccesCreatedPage(driver);
	}
	
	public void enterAllDetails(String firstName, String lastName, String email, String telephone, String password, String confirmPassword) {
		inputFirstName.sendKeys(firstName);
		inputLastName.sendKeys(lastName);
		inputEmail.sendKeys(email);
		inputTelephone.sendKeys(telephone);
		inputPassword.sendKeys(password);
		inputConfirmPassword.sendKeys(confirmPassword);
	}
	
	public String getMustAgreePrivacyPolicyMessage() {
		return mustAgreePrivacyPolicyErrorMessage.getText();
	}
}
