package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.pages.AccountPage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


@Epic("002 - OpenCart Account  Page test")
@Feature("OpenCart Feature")
@Story("Testing Login Page features ")

public class AccountPageTest extends BaseTest {
	
	@Description("Account page URL Test")
	@Severity(SeverityLevel.CRITICAL)
	@BeforeClass
	public void AccntPagesetup()
	{
		accntPage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));		
	}
	
	@Description("Account page URL Test")
	@Test
	public void AccPageUrlTest()
	{
		String accUrl = accntPage.AccPageUrl();
		Assert.assertTrue(accUrl.contains("index.php?route=account/account"));
	}
	
	@Test
	public void verifyRightPaneLinksExists()
	{
		List<String> rightLinks =  accntPage.getRightPanelLinks();
		Assert.assertEquals(rightLinks.size(), AppConstants.ACC_PAGE_RIGHTPANE_COUNT);
	}

	@Test
	
	public void searchTest()
	{
		accntPage.doSearch("ipad");
	}
	

}
