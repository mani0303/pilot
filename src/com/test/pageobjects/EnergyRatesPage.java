package com.test.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.test.core.RunnerHelper;
import com.test.support.Status;

public class EnergyRatesPage extends PageSupporter {
	public EnergyRatesPage(RunnerHelper helper) {
		super(helper);
	}

	public boolean selectEnergyPlanAndClickSignUp(){
		try{
			List<WebElement> AddToCart = driver.findElements(By.cssSelector(identifier.getIdentifier("AddToCart")));
			AddToCart.get(0).click();
			WebElement SignUp = driver.findElement(By.partialLinkText(identifier.getIdentifier("SignUp")));
			SignUp.click();
			log.log("Select Energy plan and proceed for sign up", Status.INFO);
			Thread.sleep(5000);
			int agreeCount = driver.findElements(By.cssSelector(identifier.getIdentifier("Agreement"))).size();
			if(agreeCount>0){
				WebElement Agreement = driver.findElement(By.cssSelector(identifier.getIdentifier("Agreement")));
				Agreement.click();
			}
			return true;
		}catch(Exception e){
			log.log("Select Energy plan and proceed for sign up", Status.FAIL);
			return false;
		}		
	}

}
