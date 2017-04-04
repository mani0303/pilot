package com.test.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.test.core.RunnerHelper;
import com.test.support.Status;

public class CompanyAndLocationDetailsPage extends PageSupporter{
	JavascriptExecutor executor = (JavascriptExecutor) driver;
	public CompanyAndLocationDetailsPage(RunnerHelper helper) {
		super(helper);
	}

	public boolean fillInCompanyInfo(){
		try{
			WebElement CompanyName = driver.findElement(By.id(identifier.getIdentifier("CompanyName")));
			CompanyName.clear();
			CompanyName.sendKeys(data.get("CompanyName"));
			WebElement FirstName = driver.findElement(By.id(identifier.getIdentifier("FirstName")));
			FirstName.clear();
			FirstName.sendKeys(data.get("FirstName"));
			WebElement LastName = driver.findElement(By.id(identifier.getIdentifier("LastName")));
			LastName.clear();
			LastName.sendKeys(data.get("LastName"));
			WebElement Email = driver.findElement(By.id(identifier.getIdentifier("Email")));
			Email.clear();
			Email.sendKeys(data.get("Email"));
			WebElement PhoneNumber = driver.findElement(By.id(identifier.getIdentifier("PhoneNumber")));
			PhoneNumber.clear();
			PhoneNumber.sendKeys(data.get("PhoneNumber"));
			log.log("Fill in all the company related info", Status.PASS);
			return true;
		}catch(Exception e){
			log.log("Fill in all the company related info", Status.FAIL);
			return false;
		}		
	}

	public boolean fillInSecurityQuestions(){
		try{
			WebElement SecurityQuestion = driver.findElement(By.id(identifier.getIdentifier("SecurityQuestion")));
			Select Security = new Select(SecurityQuestion);
			Security.selectByIndex(0);
			WebElement SecurityAnswer = driver.findElement(By.id(identifier.getIdentifier("SecurityAnswer")));
			SecurityAnswer.clear();
			SecurityAnswer.sendKeys("Dallas");			
			return true;
		}catch(Exception e){
			return false;
		}		
	}


	public boolean fillInServiceDetails(){
		try{
			WebElement Street = driver.findElement(By.id(identifier.getIdentifier("Street")));
			Street.clear();
			Street.sendKeys(data.get("Street"));
			WebElement Unit = driver.findElement(By.id(identifier.getIdentifier("Unit")));
			Unit.clear();
			Unit.sendKeys(data.get("Unit"));
			WebElement City = driver.findElement(By.id(identifier.getIdentifier("City")));
			City.clear();
			City.sendKeys(data.get("City"));
			WebElement Electric = driver.findElement(By.id(identifier.getIdentifier("Electric")));
			Electric.clear();
			Electric.sendKeys("33333"+System.currentTimeMillis());//24353-56755-55555-555
			List<WebElement> Gas = driver.findElements(By.id(identifier.getIdentifier("Gas")));
			if(Gas.size()>0){
				Gas.get(0).clear();
				Gas.get(0).click();
				Gas.get(0).sendKeys("44444"+System.currentTimeMillis());	
			}
			WebElement BillingAddressCheckBox = driver.findElement(By.id(identifier.getIdentifier("BillingAddressCheckBox")));
			BillingAddressCheckBox.click();
			log.log("Fill in all the service related info", Status.PASS);
			return true;
		}catch(Exception e){
			log.log("Fill in all the service related info", Status.FAIL);
			return false;
		}		
	}

	public boolean fillInBillingAddress(){
		try{
			WebElement BillingStreet = driver.findElement(By.id(identifier.getIdentifier("BillingStreet")));
			BillingStreet.clear();
			BillingStreet.sendKeys("Street Name");
			WebElement BillingUnit = driver.findElement(By.id(identifier.getIdentifier("BillingUnit")));
			BillingUnit.clear();
			BillingUnit.sendKeys("0003");
			WebElement BillingCity = driver.findElement(By.id(identifier.getIdentifier("BillingCity")));
			BillingCity.clear();
			BillingCity.sendKeys("Dallas");	
			WebElement BillingState = driver.findElement(By.id(identifier.getIdentifier("BillingState")));
			Select StateDropdown = new Select(BillingState);
			StateDropdown.selectByVisibleText("TX");
			WebElement BillingZip = driver.findElement(By.id(identifier.getIdentifier("BillingCity")));
			BillingZip.sendKeys(Keys.RETURN);
			BillingZip.sendKeys("9");
			executor.executeScript("arguments[0].setAttribute('value', '" + 07001 +"')", BillingZip);
			return true;
		}catch(Exception e){
			return false;
		}		
	}

	public boolean submitReviewAndConfirm(){
		try{
			WebElement ReviewAndConfirm = driver.findElement(By.name(identifier.getIdentifier("ReviewAndConfirm")));
			ReviewAndConfirm.click();
			log.log("Review and confirm the order", Status.PASS);
			return true;
		}catch(Exception e){
			log.log("Review and confirm the order", Status.FAIL);
			return false;
		}		
	}
}
