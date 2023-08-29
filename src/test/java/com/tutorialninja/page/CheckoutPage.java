package com.tutorialninja.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {
	public static WebDriver driver;
	
	@FindBy(xpath="//input[@type ='radio' and @value='new']")
	private WebElement newAddressButton;
	
	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	public void clickOnNewAddressButton() {
		newAddressButton.click();
	}
	
}
