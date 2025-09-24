package com.qa.opencart.factory;

import java.io.FileInputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.errors.AppError;
import com.qa.opencart.exceptions.FrameworkException;

public class DriverFactory {
	
	
	public WebDriver driver;
	public Properties prop;
	public static String highlightEle;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	
	private static final Logger log = LogManager.getLogger(DriverFactory.class); 
	public OptionsManager optionsManager;
	
	
	
	/**
	 * initializing the driver with browser
	 * @param browsername
	 * @return 	driver
	 */
	public WebDriver initDriver(Properties prop) {

		String browserName = prop.getProperty("browser");
		// System.out.println("browser name : " + browserName);
		log.info("browser name : " + browserName);

		highlightEle = prop.getProperty("highlight");
		optionsManager = new OptionsManager(prop);

		boolean remoteExeution = Boolean.parseBoolean(prop.getProperty("remote"));

		switch (browserName.trim().toLowerCase()) {
		case "chrome":
			if (remoteExeution) {
				// run tcs on remote - grid
				init_remoteDriver("chrome");
			} else {
				// run tcs in local
				tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			}
			break;
		case "firefox":
			if (remoteExeution) {
				// run tcs on remote - grid
				init_remoteDriver("firefox");
			} else {
				// run tcs in local
				tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			}
			break;
			

		
		default:
			log.error(AppError.INVALID_BROWSER_MESG + " : " + browserName);
			FrameworkException fe = new FrameworkException(AppError.INVALID_BROWSER_MESG + " : " + browserName);
			log.error("Exception occurred while initializing driver: ", fe);
			throw new FrameworkException("=====INVALID BROWSER====");

		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));

		return getDriver();

	}
	
	public static WebDriver getDriver() {
		return tlDriver.get();
	}

	
	
	/**
	 * Initializing the properties.config file 
	 * @return
	 */
	
	
	
	private void init_remoteDriver(String browserName) {
		log.info("Running tests on selenoum grid --"+ browserName);

		try {
			switch (browserName) {
			case "chrome":
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getChromeOptions()));
				break;
				
			case "firefox":
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getFirefoxOptions()));
				break;
				
		
				
			default:
				log.error("Plz supply the right browser name for selenium grid....");
				FrameworkException fe = new FrameworkException(AppError.INVALID_BROWSER_MESG + " : " + browserName);
				log.error("Exception occurred while initializing driver: ", fe);
				throw new FrameworkException("=====INVALID BROWSER====");
			}
		} 
		catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}
	public Properties initProp() {
		prop = new Properties();
		FileInputStream ip =null;
		String  envName = System.getProperty("env");
		log.info("Env Name" + envName);
		try
		{
		if(envName ==null)
		{
			log.info("No env passed, Running on QA environment by default");
			ip = new FileInputStream("./scr/test/resources/config/config.qa.properties");
		}
		
		else
		{
			switch (envName.trim().toLowerCase()) {
			case "qa": 
				ip = new FileInputStream("./scr/test/resources/config/config.qa.properties");
				break;
			case "prod":
				ip = new FileInputStream("./scr/test/resources/config/config.prod.properties");
			
			default:
				log.error("Env value is invalid.. pls pass the right env");
				throw new FrameworkException("====INVALID ENV====");
			}
		}
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		try {
			prop.load(ip);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return prop;
	}
	

}
