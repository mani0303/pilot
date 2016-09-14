package com.test.functional;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.test.core.RunnerHelper;
import com.test.report.Logger;
import com.test.support.Status;
/**
 * File name   :TestRunner.java
 * Description  : 
 * Date created :13 Sep 2016
 * Author 		:Veera
 */
public class TestRunner{
	Logger log;
	WebDriver driver;
	public HashMap<String, String> data;
	@Retention(RetentionPolicy.RUNTIME)
	@interface Test{
		String description();
	}
	public TestRunner(RunnerHelper helper){
		log = helper.log;
		driver=helper.driver;
		data=helper.testData;
	}

	/**
	 * 
	 * Method name  : openGooglePage
	 * Return types : void
	 * Description  :
	 */
	@Test(description= "When I open Google website")
	public void openGooglePage(){
		try{
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.get(data.get("Parameter_1"));
			log.log("Google page is opened", Status.PASS);			
		}catch(Exception e){
			log.log("When I open Google website "+e.getMessage(),Status.FAIL);
		}
	}

	/**
	 * 
	 * Method name  : enterSomeText
	 * Return types : void
	 * Description  :
	 */
	@Test(description= "And I enter some text")
	public void enterSomeText(){
		try{
			driver.findElement(By.name("q")).sendKeys(data.get("Parameter_2"));
			driver.findElement(By.name("btnG")).click();
			Thread.sleep(2000);
			log.log("The text "+data.get("Parameter_2")+" is entered", Status.PASS);
		}catch(Exception e){
			log.log("And I enter some text "+e.getMessage(),Status.FAIL);
		}
	}
	/**
	 * 
	 * Method name  : verifyResultPage
	 * Return types : void
	 * Description  :
	 */
	@Test(description= "Then I should see that text in the result page")
	public void verifyResultPage(){
		try{
			if(driver.getPageSource().contains(data.get("Parameter_2"))){
				log.log(data.get("Parameter_2")+" is present in the result page", Status.PASS);
			}else{
				log.log(data.get("Parameter_2")+" is not present in the result page", Status.FAIL);
			}
		}catch(Exception e){
			log.log("Then I should see that text in the result page "+e.getMessage(),Status.FAIL);
		}
	}
	/**
	 * 
	 * Method name  : searchInBing
	 * Return types : void
	 * Description  :
	 */
	@Test(description= "Search a text 'Firefox' in Bing")
	public void searchInBing(){
		try{
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.get(data.get("Parameter_1"));
			log.log("Bing page is opened", Status.PASS);	
			driver.findElement(By.name("q")).sendKeys(data.get("Parameter_2"));
			driver.findElement(By.name("go")).click();
			log.log("The text "+data.get("Parameter_2")+" is entered", Status.PASS);
		}catch(Exception e){
			log.log("Search a text 'Firefox' in Bing "+e.getMessage(),Status.FAIL);
		}
	}
}
