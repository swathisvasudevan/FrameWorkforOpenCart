package com.qa.opencart.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotSame;
import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class CartPageTest extends BaseTest {
	
	
	public void CartPageTestSetup()
	{
		accntPage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void checkCartStatus()
	{
		Assert.assertTrue(cartPage.CartStatus());;
	}
	

}
