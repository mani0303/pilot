package com.test.support;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
/**
 * File name   :UIDriver.java
 * Description  : 
 * Date created :13 Sep 2016
 * Author 		:Veera
 */
public class UIDriver {
	WebDriver wDriver;
	/**
	 * 
	 * Method name  : getDriver
	 * Return types : WebDriver
	 * Description  :
	 */
	public WebDriver getDriver(String driver){
		
		
		switch(driver){
		case "Firefox" :
			wDriver = new FirefoxDriver();
			return wDriver;
		case "Google Chrome" :
			String chromeDriver=Settings.getInstance().getDriverEXEDir()+"chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", chromeDriver);
			wDriver = new ChromeDriver();
			return wDriver;
		case "IE":
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			capabilities.setCapability("requireWindowFocus", true);
			String ieDriver=Settings.getInstance().getDriverEXEDir()+"IEDriverServer.exe";
			System.setProperty("webdriver.ie.driver", ieDriver);
			wDriver = new InternetExplorerDriver(capabilities);
			return wDriver;
		case "Edge":
			return wDriver;
		case "Safari":
			return wDriver;
		default :
			wDriver = new FirefoxDriver();
			return wDriver;
		}
	}
	/**
	 * 
	 * Method name  : quitDriver
	 * Return types : boolean
	 * Description  :
	 */
	public boolean quitDriver(WebDriver wDriver){
		try{
			wDriver.quit();
			return true;
		}catch(Exception e){
			return false;
		}

	}
}
