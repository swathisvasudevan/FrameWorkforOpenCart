package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountPage {
	
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	public AccountPage(WebDriver driver)
	{
		this.driver=driver;
		eleUtil = new ElementUtil(driver);	
	}
	
	
	private final By myOrders = By.xpath("//h2[contains(text(),'Orders')]");
	private final By rightPaneLinks = By.xpath("//div[@class='list-group']/a");
	private final By myAccountText = By.xpath("//h2[contains(text(),'My Account')]");
	private final By Searchbox = By.xpath("//input[@name='search']");
	private final By searchicon = By.cssSelector("div#search button");
	
	
	
	public String AccPageUrl()
	{
		String AccUrl = driver.getCurrentUrl();
		return AccUrl;
	}
	
	
	public boolean MyOrderExists()
	{
		return driver.findElement(myOrders).isDisplayed();
	}
	
	public boolean MyAccountExists()
	{
		return driver.findElement(myAccountText).isDisplayed();
	}
	
	public List<String> getRightPanelLinks()
	{
		List<WebElement> rightPane = driver.findElements(rightPaneLinks);
		System.out.println("Number of Links on Right Pane:" +rightPane.size());
		
		List<String> PaneValue = new ArrayList<String>();
		
		for(WebElement e :rightPane)
		{
			String text = e.getText();	
			PaneValue.add(text);
		}
		return PaneValue;
	}
	
	public ProductListingPage doSearch(String Searchtxt)
	{
	System.out.println("The search key -->"+Searchtxt);
	WebElement SearchFiled = eleUtil.waitForElementPresence(Searchbox, AppConstants.DEFAULT_MEDIUM_WAIT);
	SearchFiled.clear();
	SearchFiled.sendKeys(Searchtxt);
	eleUtil.doClick(searchicon);
	return new ProductListingPage(driver);
	}
}
