package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;



public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	int i=10;
	
	
	private final By emailID = By.id("input-email");
	private final By password = By.id("input-password");
	private final By lgnbtn = By.xpath("//input[@value='Login']");
	private final By header = By.tagName("h2");
	private final By forgotpwdlink =By.linkText("Forgotten Password");
	private final By searchBox = By.xpath("//input[@name='search']");
	private final By logo = By.xpath("//img[@class='img-responsive']");
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public boolean forgotPasswordExists()
	{
		return driver.findElement(forgotpwdlink).isDisplayed();
	}
	
	public String getPageTitle() {
		String title = eleUtil.waitForTitleContains("Account Login", 5);
		//String pageTitle =driver.getTitle();
		System.out.println("Title of the page :" + title);
		return title;
	}
	
	public String getURL()
	{
		String pageUrl = driver.getCurrentUrl();
		System.out.println("URL of the page:" + pageUrl);
		return pageUrl;
	}
	public boolean isHeaderExists()
	{
		return driver.findElement(header).isDisplayed();
		
	}
	
	@Step("Login with Username {0} and password {1}")
	
	public AccountPage doLogin(String username,String pwd)
	{
		System.out.println("Application Credentials :" + username +":" +password);
		
		eleUtil.waitForElementVisible(emailID, 10).sendKeys(username);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(lgnbtn); 
		return new AccountPage(driver);
	
	}
	
	public boolean SearchFiled()
	{
		return driver.findElement(searchBox).isDisplayed();
	}
	
	public boolean LoginpageLogo()
	{
		return driver.findElement(logo).isDisplayed();
	}
	
	
	
	
	
}
