package com.test.support;


import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
/**
 * File name   :UIDriver.java
 * Description  : 
 * Date created :13 Sep 2016
 * Author 		:Veera
 */
public class UIDriver {
	Configurations configurations = Configurations.getInstance();
	WebDriver wDriver;
	/**
	 * 
	 * Method name  : getDriver
	 * Return types : WebDriver
	 * Description  :
	 */
	public WebDriver getDriver(String driver){
		String executionMode = configurations.getProperty("execution");
		String gridURL = configurations.getProperty("gridURL");
		DesiredCapabilities dc = new DesiredCapabilities();
		try{
			switch(driver){

			case "Firefox" :
				if(executionMode.equalsIgnoreCase("remote")){
					dc.setBrowserName("firefox");
					wDriver = new RemoteWebDriver(new URL(gridURL),dc);
					return wDriver;
				}
				wDriver = new FirefoxDriver();
				return wDriver;
			case "Google Chrome" :
				if(executionMode.equalsIgnoreCase("remote")){
					dc.setCapability("browserName", BrowserType.CHROME);
					wDriver = new RemoteWebDriver(new URL(gridURL),dc);
					return wDriver;
				}
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
			case "Headless":
				dc.setJavascriptEnabled(true);
				dc.setCapability("takesScreenshot", false);
				dc.setCapability("phantomjs.binary.path", Settings.getInstance().getPhantomJSPath()+"phantomjs.exe");
				wDriver = new PhantomJSDriver(dc);
				return wDriver;
			default :
				wDriver = new FirefoxDriver();
				return wDriver;
			}
		}catch(Exception e){
			e.printStackTrace();
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
	
	public void enableBrowserMobProxy(){
		
	}
	
}
