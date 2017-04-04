package com.test.pageobjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.test.core.RunnerHelper;
import com.test.support.Status;

public class LandingPage extends PageSupporter{

	public LandingPage(RunnerHelper helper) {
		super(helper);
	}

	public boolean enterURLforTest(){
		try{
			driver.get(data.get("BaseURL"));
			log.log("Launch base url in the browser", Status.INFO);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			return true;
		}catch(Exception e){
			log.log("Launch base url in the browser", Status.FAIL);
			return false;
		}	
	}

	public boolean updateZipAndUtility(){
		try{
			WebElement Zip = driver.findElement(By.id(identifier.getIdentifier("Zip")));
			Zip.clear();
			Zip.sendKeys(data.get("Zip"));
			Thread.sleep(5000);
			WebElement UpdateZip = driver.findElement(By.cssSelector(identifier.getIdentifier("UpdateZip")));
			UpdateZip.click();
			log.log("Zip is updated and utilities are selected", Status.INFO);
			return true;
		}catch(Exception e){
			log.log("Zip update and utilities selection", Status.FAIL);
			return false;
		}		
	}
}