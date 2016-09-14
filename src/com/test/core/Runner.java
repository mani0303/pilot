package com.test.core;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
/** File name   :Runner.java
 * Description  : 
 * Date created :13 Sep 2016
 * Author 		:Veera
 */
public class Runner implements Runnable{
	private HashMap<String, String> testParameters;
	private HashMap<String, String> testData;
	long startTime = System.currentTimeMillis();
	public Runner(HashMap<String, String> testParameters, HashMap<String, String> testData){
		this.testParameters=testParameters;
		this.testData=testData;
	}
	
	@Override
	public void run() {
		String testType=testParameters.get("Test_Type").trim();
		WebDriver driver;
		RunnerHelper runnerHelper = new RunnerHelper();
		runnerHelper.setTestParametersAndTestData(testParameters,testData);
		driver=runnerHelper.startTests();
		if(driver!=null&&testType.equals("Functional")){
			runnerHelper.startTestLogger(startTime,driver);
		}else{
			runnerHelper.startTestLogger(startTime);
		}
		runnerHelper.runTestMethod();
		runnerHelper.finishTestLogger(startTime);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		runnerHelper.finishTests();
	}
}
