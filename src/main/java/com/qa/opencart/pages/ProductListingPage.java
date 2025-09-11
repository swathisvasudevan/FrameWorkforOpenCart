package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductListingPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	
	public ProductListingPage(WebDriver driver)
	{
		this.driver=driver;
		eleUtil = new ElementUtil(driver);
	}
	
	private final By header = By.tagName("h1");
	private final By searchResults = By.xpath("//div[@class='product-thumb']");
	
	
	public int getResultsCount()
	{
		int ResultsOnSearchPage = eleUtil.waitForElementsPresence(searchResults, AppConstants.DEFAULT_SHORT_WAIT).size();
		System.out.println(ResultsOnSearchPage);
		return ResultsOnSearchPage;
	}
	
	
	public String getHeaderValue()
	{
		String pageheader=eleUtil.doElementGetText(header);
		System.out.println(pageheader);
		return pageheader;
	}
	
	
	public ProductInfoPage selectProduct(String productname)
	{
		System.out.println("Product Name selected" + productname);
		By product = By.linkText(productname);
		eleUtil.doClick(product);
		return new ProductInfoPage(driver);
		
	}
	

}
