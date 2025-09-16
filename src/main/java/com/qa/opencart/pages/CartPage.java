package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;



public class CartPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	public CartPage(WebDriver driver)
	{
		this.driver= driver;
	}

	private final By emptycart = By.xpath("Your shopping cart is empty!");
	
	
	public boolean CartStatus()
	{
		String cartText = eleUtil.doElementGetText(emptycart);
		if (cartText.contains("empty"))
		{
			return true;
		}
		else return false;
	}
	
	

}
