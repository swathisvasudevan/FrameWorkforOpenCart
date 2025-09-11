package com.qa.opencart.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.ProductListingPage;

public class ProductListingPageTest extends BaseTest {
	
	@BeforeClass
	public void ProductListingPageTestSetUp()
	{
		accntPage = loginpage.doLogin("Swa@gmail.com", "SwatPwd");	
	}
	
	@Test
	public void searchTest()
	{
		
		
	}

}
