package com.qa.opencart.tests;

import org.testng.Assert;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductInfoPageTest extends BaseTest{
	
	@BeforeClass
	public void ProductInfoPageSetup()
	{
		accntPage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	
	@DataProvider
	public Object[][] getproducts()
	{
		return new 	Object[][]
		           	         {
			{"Macbook","MacBook Pro"},
			{"samsung","Samsung SyncMaster 941BW"},
			{"canon","Canon EOS 5D"}
		           	         };
	}
	
		
	@Test(dataProvider = "getproducts")
	public void getHeaderList(String searchkey , String productname)
	{
		prodListPage =accntPage.doSearch(searchkey);
		ProdInfoPage =prodListPage.selectProduct(productname);
		String HeaderVal = prodListPage.getHeaderValue();
		Assert.assertEquals(HeaderVal, productname);
		
	}
	
	
	
	public void productMetadataTest()
	{
		
	}
	
	
	
	
	
	

}
