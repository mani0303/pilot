package com.test.core;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openqa.selenium.WebDriver;
import com.test.apitest.ApiTestRunner;
import com.test.report.Logger;
import com.test.support.Configurations;
import com.test.support.UIDriver;

/** File name   :RunnerHelper.java
 * Description  : 
 * Date created :13 Sep 2016
 * Author 		:Veera
 */
public class RunnerHelper{

	public HashMap<String, String> testParameters;
	public Logger log;
	public WebDriver driver;
	public HashMap<String, String> testData;
	Configurations conf = Configurations.getInstance();
	/**
	 * 
	 * Method name  : setTestParametersAndTestData
	 * Return types : void
	 * Description  :
	 */
	public void setTestParametersAndTestData(HashMap<String, String> testParameters,HashMap<String, String> testData){
		this.testParameters=testParameters;
		this.testData=testData;
	}

	/**
	 * 
	 * Method name  : startTestLogger
	 * Return types : void
	 * Description  :
	 */
	public void startTestLogger(long startTime,WebDriver driver){
		if(log==null){
			this.log = new Logger(testParameters,driver);
		}
		log.getResetedStatistic();
		log.getInitializedReportData(startTime);
	}
	/**
	 * 
	 * Method name  : startTestLogger
	 * Return types : void
	 * Description  :
	 */
	public void startTestLogger(long startTime){
		if(log==null){
			this.log = new Logger(testParameters);
		}
		log.getResetedStatistic();
		log.getInitializedReportData(startTime);
	}
	/**
	 * 
	 * Method name  : runTestMethod
	 * Return types : boolean
	 * Description  :
	 */
	public boolean runTestMethod() {
		boolean testStatus = false;
		String description = "";
		String testDescription=testParameters.get("Test_Description").trim();
		String testType=testParameters.get("Test_Type").trim();
		Pattern pattern;
		Matcher matcher;

		try {
			switch(testType){
			case "Functional" :
				String functionaltests = "bin."+conf.getProperty("functionaltests");
				File functionalDir = new File(System.getProperty("user.dir")+System.getProperty("file.separator")+functionaltests.replace(".", "\\"));
				final String[]  testFiles = functionalDir.list();
				String FunctionalTestClass;
				for (final String testFile : testFiles) {
					if (testFile.endsWith(".class") && !testFile.contains("$")) {
						FunctionalTestClass = testFile.substring(0, testFile.length() - 6);						
						try {
							Class<?> testClass = Class.forName("com.test.functional."+FunctionalTestClass);
							Constructor<?> testConstructor = testClass.getConstructor(RunnerHelper.class);
							Object testRunner = testConstructor.newInstance(this);
							Method[] methods = testRunner.getClass().getMethods();
							if(testDescription.contains(";")){
								System.out.println(testDescription);
								String[] testSubDescriptions = testDescription.split(";");
								for(String testSubDescription:testSubDescriptions){
									for(Method test:methods){
										if(test.getAnnotations().length>0){
											pattern = Pattern.compile(".*description=(.+?)\\)");
											matcher = pattern.matcher(test.getAnnotations()[0].toString());
											while (matcher.find()) {
												description=matcher.group(1).trim();
											}
											if(description.contains(testSubDescription.trim())){
												System.out.println(description);
												test.invoke(testRunner);
											}
										}
									}
								}
							}else{
								for(Method test:methods){
									if(test.getAnnotations().length>0){
										pattern = Pattern.compile(".*description=(.+?)\\)");
										matcher = pattern.matcher(test.getAnnotations()[0].toString());
										while (matcher.find()) {
											description=matcher.group(1).trim();
										}
										if(description.contains(testDescription)){
											System.out.println(description);
											test.invoke(testRunner);
										}
									}
								}
							}
						} catch (final Exception e) {
							e.printStackTrace();
							// do nothing. this class hasn't been found by the
							// loader, and we don't care.
						}
					}
				}
				return testStatus;

			case "API_Test" :
				ApiTestRunner apiTestRunner = new ApiTestRunner(this);
				Method[] apiMethods = apiTestRunner.getClass().getMethods();
				for(Method test:apiMethods){
					if(test.getAnnotations().length>0){
						pattern = Pattern.compile(".*description=(.+?)\\)");
						matcher = pattern.matcher(test.getAnnotations()[0].toString());
						while (matcher.find()) {
							description=matcher.group(1).trim();
						}
						if(description.toLowerCase().contains(testDescription.toLowerCase())){
							System.out.println(description);
							test.invoke(apiTestRunner);
						}
					}
				}
				return testStatus;
			}
			return false;
		} catch (Exception e) {
			return false;
		} 
	}
	/**
	 * 
	 * Method name  : startTests
	 * Return types : WebDriver
	 * Description  :
	 */
	public WebDriver startTests(){
		String testType=testParameters.get("Test_Type").trim();
		if(driver==null&&testType.equals("Functional")){
			String browser = testParameters.get("Browser_Name");
			UIDriver uiDriver = new UIDriver();
			this.driver=uiDriver.getDriver(browser);
		}
		return driver;
	}

	/**
	 * Method name  : finishTestLogger
	 * Return types : void
	 * Description  :
	 */

	public void finishTestLogger(long startTime){
		if(log==null){
			this.log = new Logger(testParameters,driver);
		}
		log.finishTestReport(startTime);
	}

	/**
	 * Method name  : finishTests
	 * Return types : void
	 * Description  :
	 */

	public void finishTests() {
		if(driver!=null){
			driver.quit();
		}
	}
}