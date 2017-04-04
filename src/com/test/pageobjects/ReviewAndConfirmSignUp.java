package com.test.pageobjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.test.core.RunnerHelper;
import com.test.support.Status;

public class ReviewAndConfirmSignUp extends PageSupporter{

	public ReviewAndConfirmSignUp(RunnerHelper helper) {
		super(helper);
	}
	
	public boolean reviewAndConfirmSignUp(){
		try{
			WebElement TermsAndConditions = driver.findElement(By.cssSelector(identifier.getIdentifier("TermsAndConditions")));
			TermsAndConditions.click();
			Thread.sleep(5000);
			WebElement ConfirmAndSignUp = driver.findElement(By.id(identifier.getIdentifier("ConfirmAndSignUp")));
			ConfirmAndSignUp.click();
			log.log("Order is reviewed and submitted for processing", Status.PASS);
			return true;
		}catch(Exception e){
			log.log("Order is reviewed and submitted for processing", Status.FAIL);
			return false;
		}	
	}
	
	public boolean validateSignUpConfirmation(){
		try{
			String OrderNo;
			List<WebElement> SuccessMessage = driver.findElements(By.cssSelector(identifier.getIdentifier("SuccessMessage")));			
			WebElement OrderNumber = driver.findElement(By.cssSelector(identifier.getIdentifier("OrderNumber")));
			if(SuccessMessage.size()>0){
				OrderNo = OrderNumber.getText();
				log.log("The order has been placed successfully and the order # is "+OrderNo, Status.INFO);
				return true;
			}else{
				log.log("Error placing order", Status.FAIL);
				return false;
			}
		}catch(Exception e){
			log.log("Error placing order", Status.FAIL);
			return false;
		}	
	}
}
