package com.tutorialninja.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage {

	public WebDriver driver;
	
	@FindBy(xpath = "//a[text() = 'Edit your account information']")
	private WebElement editAccounInformationLink;
	
	@FindBy(css = "input.form-control.input-lg")
	private WebElement searchTab;
	
	@FindBy(css = "i.fa.fa-search")
	private WebElement searchProductButton;
	
	public AccountPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public String getLoginSuccessMessage() {
		String message = editAccounInformationLink.getText();
		return message;
	}
	
	public void typeSearchProduct(String product) {
		searchTab.sendKeys(product);
	}
	
	public void clickOnSearchButton() {
		searchProductButton.click();
	}
	
	public void searchProduct(String product) {
		searchTab.sendKeys(product);
		searchProductButton.click();
	}
	
	
}
