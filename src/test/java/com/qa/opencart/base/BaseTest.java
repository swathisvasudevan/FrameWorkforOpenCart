package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.ProductListingPage;

import io.qameta.allure.Description;

public class BaseTest {
	
	public WebDriver driver;
	protected Properties prop;
	DriverFactory df;
	protected LoginPage loginpage;
	protected AccountPage accntPage;
	protected ProductListingPage prodListPage;
	protected ProductInfoPage ProdInfoPage;
	
	
	@Description("Driver Initalization")
	@BeforeTest
	public void setup(@Optional("Chrome")String browsername)
	{
		df=new DriverFactory();
		prop = df.initProp();
		
		
		driver = df.initDriver(prop);
		loginpage = new LoginPage(driver);	
	}
	@Description("Quitting Driver")
	@AfterTest
	
	public void tearDown()
	{
		driver.quit();
	}
	
}
