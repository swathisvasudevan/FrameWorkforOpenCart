package com.qa.opencart.tests;

import org.testng.Assert;

import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.pages.AccountPage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;

@Epic("001 - OpenCart Login Page test")
@Feature("OpenCart Feature")
@Story("Testing login page core features ")


public class LoginPageTest extends BaseTest {
	
	
	@Description("Teting the Login Feature")
	@Owner("Swathi Vasudevan")
	@Test
	public void LoginTest() {
		accntPage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		accntPage.MyAccountExists();
	}
	
	@Description("Teting the Header")
	@Test
	public void ForgotPwdExists()
	{
		Assert.assertTrue(loginpage.isHeaderExists());
	}
	
	@Description("Teting the URL Feature")
	@Test
	public void loginPageUrlTest()
	{
		String accntURL = loginpage.getURL();
		Assert.assertTrue(accntURL.contains("/index.php?route=account/account"));
	}
	
	
	@Description("Teting the Title")
	@Test
	public void loginpageTitleTest()
	{
	  String accnttitle = loginpage.getPageTitle();
	  Assert.assertEquals(accnttitle, "My Account");
	}
	
	
	
	@Test
	public void SearchBoxExists()
	{
		Assert.assertTrue(loginpage.SearchFiled());	
	}
	
	@Test
	public void LogoExistsCheck()
	{
		Assert.assertTrue(loginpage.LoginpageLogo());
	}

}
