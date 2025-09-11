package com.qa.opencart.pages;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	private Map<String,String> productMap;
	
	
	public ProductInfoPage(WebDriver driver)
	{
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	private final By header = By.tagName("h1");
	private final By productImages = By.xpath("//li[@class='image-additional']");
	private final By productAvail = By.xpath("//li[contains(text(),'Availability')]");
	private final By productMetadata =By.xpath("//div[@id='content']//ul[@class='list-unstyled'][1]");
	private final By productPricedata = By.xpath("//div[@id='content']//ul[@class='list-unstyled'][2]");
	

	public String getProductHeader()
	{
		String headerVal = eleUtil.waitForElementVisible(header, AppConstants.DEFAULT_SHORT_WAIT).getText();
		System.out.println("Product Header:" + headerVal);
		return headerVal;
	}
	public int getProductImages()
	{
		int NumberOfImages =  eleUtil.waitForElementsVisible(productImages, AppConstants.DEFAULT_SHORT_WAIT).size();
		System.out.println("Total Number of Images= " +NumberOfImages);
		return NumberOfImages;
	}
	
	public boolean productAvailabilty()
	{
		String StockInfo = eleUtil.doElementGetText(productAvail);
		if (StockInfo.contains("In Stock"))
		{
			return true;
		}
		else
		{
			System.out.println("Product Is Currently No Available");
			return false;
		}
	}
	
	public Map<String,String> productData()
	{
		
		productMap = new TreeMap<String, String>();
		
		productMap.put("Product Name", getProductHeader());
		productMap.put("Product Images",String.valueOf(getProductImages()));
		getMetadataInfo();
		getMetapriceInfo();
		System.out.println("=====Product Data===== " +productMap );
		return productMap;
	}
	
	public void getMetadataInfo() {
		
	
		List<WebElement> MetadataEle = eleUtil.waitForElementsVisible(productMetadata, AppConstants.DEFAULT_SHORT_WAIT);
		System.out.println("total size" + MetadataEle.size());
		
		for (WebElement e :MetadataEle)
		{
			String[] metainfo = e.getText().split(":");
			
			String MetaKey = metainfo[0].trim();
			String MetaValue = metainfo[1].trim();
			productMap.put(MetaKey,MetaValue);
			
		}
	}
	
	public void getMetapriceInfo() {
		
		productMap = new TreeMap<String, String>();
		List<WebElement> Metapricelist = driver.findElements(productPricedata);
		String priceInfo = Metapricelist.get(0).getText();
		String eTaxvalue = Metapricelist.get(1).getText().split(":")[1].trim();
		
		
		productMap.put("Product price", priceInfo);
		productMap.put("etax Vlaue", eTaxvalue);
		
			
		}
	}
	
	
	
