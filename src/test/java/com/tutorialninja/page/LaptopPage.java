package com.tutorialninja.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LaptopPage {

	public static WebDriver driver;
	@FindBy(linkText = "HP LP3065")
	private WebElement hpLp3065;
	@FindBy(xpath = "//i[@class='fa fa-calendar']")
	private WebElement calendar;
	@FindBy(id = "button-cart")
	private WebElement addToCartButton;
	@FindBy(id = "input-quantity")
	private WebElement quantityTextBox;
	@FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
	private WebElement succesfullAddtoCartMessage;
	@FindBy(xpath = "//span[text()='Shopping Cart']")
	private WebElement cartButton;
	
	public LaptopPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	


	public boolean isHpLp3065Present() {
		return hpLp3065.isDisplayed();
		
	}
	public String HpLp3065Name() {
		return hpLp3065.getText();
	}
	public void clickOnHpLp3065() {
		hpLp3065.click();
	}

	public void clickOnCalendar() {
		calendar.click();
	}

	public void clickOnAddToCartButton() {
		addToCartButton.click();
	}
	
	public void addQuantity(String qty) {
		quantityTextBox.click();
		quantityTextBox.clear();
		quantityTextBox.sendKeys(qty);
	}
	
	public String successAddToCartMessage() {
		return succesfullAddtoCartMessage.getText();
	}
	public CartPage clickOnShowCartButton() {
		cartButton.click();
		return new CartPage(driver);
		
	}
}
