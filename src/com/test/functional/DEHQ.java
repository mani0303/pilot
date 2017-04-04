/*package com.test.functional;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DEHQ {

	public static void main(String[] args) {
		try {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\450405\\workspace\\Pilot\\resources\\driver-exe\\chromedriver.exe");
			WebDriver driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.get("https://debdehqtest.directenergy.com/REG1/DEHQ/Account/Index");
			driver.findElement(By.id("UserName")).sendKeys("businessadmin@test.com");
			driver.findElement(By.id("Password")).sendKeys("Testtest1");
			driver.findElement(By.id("main-form_submit")).click();
			Thread.sleep(3000);
			if(driver.findElements(By.id("main-form_submit")).size()>0){
				driver.findElement(By.id("main-form_submit")).click();
			}
			
			
			int Plus = driver.findElements(By.cssSelector("#MatrixPricingTitle span[class*='plus']")).size();
			if(Plus>0){
				driver.findElements(By.cssSelector("#MatrixPricingTitle span[class*='plus']")).get(0).click();
			}
			driver.findElement(By.cssSelector("span[aria-owns*='MatrixFilter_State_listbox']")).click();
			Thread.sleep(3000);
			 driver.findElements(By.cssSelector("#MatrixFilter_State_listbox li")).get(3).click();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
*/