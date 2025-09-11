package com.qa.opencart.factory;

import java.io.FileInputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.errors.AppError;
import com.qa.opencart.exceptions.FrameworkException;

public class DriverFactory {
	
	
	public WebDriver driver;
	public Properties prop;
	public static String highlightEle;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	
	private static final Logger log = LogManager.getLogger(DriverFactory.class); 
	public OptionsManager Optionsmanager;
	
	
	
	/**
	 * initializing the driver with browser
	 * @param browsername
	 * @return 	driver
	 */
	public WebDriver initDriver(Properties prop)
	{
		String browername = prop.getProperty("browser");
		Optionsmanager = new OptionsManager(prop);
		
		System.out.println("Browser" + browername);
		switch (browername.trim().toLowerCase()) {
		case "chrome": 
		driver = new ChromeDriver();
		break;
		
		case "firefox": 
		driver = new FirefoxDriver();
		break;
		
		case "edge": 
		driver = new EdgeDriver();
		break;
		
		case "safari": 
		driver = new SafariDriver();
		break;
		
		default:
			System.out.println(AppError.INVALID_BROWSER + ":" + browername);
			throw new FrameworkException("=====INVALID BROWSER=====");
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().window().fullscreen();
		driver.get(prop.getProperty("url"));
		
		return driver;
		
		
	}
	
	public static WebDriver getDriver() {
		return tlDriver.get();
	}

	
	
	/**
	 * Initializing the properties.config file 
	 * @return
	 */
	
	public Properties initProp() {
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream("./scr/test/resources/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	

}
