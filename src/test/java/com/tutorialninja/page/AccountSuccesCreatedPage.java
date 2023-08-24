package com.tutorialninja.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountSuccesCreatedPage {

	public static WebDriver driver;
	@FindBy(xpath = "//div[@id = 'content']/child::p[1]")
	private WebElement accounSuccesCreatedMessage;
	
	public AccountSuccesCreatedPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	public String getaccounSuccesCreatedMessage() {
		String message = accounSuccesCreatedMessage.getText();
		return message;
		
	}
	
	
}
