package com.test.core;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import org.openqa.selenium.WebDriver;
import com.test.core.RunnerHelper;
import com.test.pageobjects.CompanyAndLocationDetailsPage;
import com.test.pageobjects.EnergyRatesPage;
import com.test.pageobjects.LandingPage;
import com.test.pageobjects.ReviewAndConfirmSignUp;
import com.test.report.Logger;
import com.test.support.Identifier;
/**
 * File name   :TestRunner.java
 * Description  : 
 * Date created :13 Sep 2016
 * Author 		:Veera
 */
public class TestRunner{
	protected Logger log;
	protected WebDriver driver;
	protected HashMap<String, String> data;
	Identifier identifier = Identifier.getInstance();
	protected LandingPage lp;
	protected CompanyAndLocationDetailsPage cldp;
	protected EnergyRatesPage erp;
	protected ReviewAndConfirmSignUp rcs;
	@Retention(RetentionPolicy.RUNTIME)
	public
	@interface Test{
		String description();
	}
	public TestRunner(RunnerHelper helper){
		log = helper.log;
		driver=helper.driver;
		data=helper.testData;
		this.lp = new LandingPage(helper);
		this.cldp = new CompanyAndLocationDetailsPage(helper);
		this.erp = new EnergyRatesPage(helper);
		this.rcs = new ReviewAndConfirmSignUp(helper);
	}		
}
