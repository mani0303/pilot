package com.test.core;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.test.support.TestRunnerInfo;
import com.test.support.Configurations;
import com.test.support.Identifier;
import com.test.support.Settings;

/** File name   :Test_Controller.java
 * Description  : 
 * Date created :13 Sep 2016
 * Author 		:Veera
 */
public class Test_Controller {
	Identifier identifier = Identifier.getInstance();
	Settings setting = Settings.getInstance();
	Configurations conf = Configurations.getInstance();
	int parallelLimit = Integer.parseInt(conf.getProperty("ThreadsLimit"));
	public static void main(String args[]){
		Test_Controller tc= new Test_Controller();
		tc.controller();
	}

	/**
	 * Method name  : controller
	 * Return types : void
	 * Description  :
	 */
	public void controller(){
		TestRunnerInfo testInfo = TestRunnerInfo.getInstance();
		String testType;
		String runFlag;
		HashMap<String, String> testLineItem;
		ExecutorService executor = Executors.newFixedThreadPool(parallelLimit);
		try {
			HashMap<String, HashMap<String, String>> testParameter = testInfo.readTestRunner("TestRunner",setting.getTestRunner(),"Test_ID");
			System.out.println(testParameter.keySet());
			for (Entry<String, HashMap<String, String>> entry : testParameter.entrySet()) {
				runFlag=entry.getValue().get("Run");
				if(runFlag.equalsIgnoreCase("Yes")){
					testType=entry.getValue().get("Test_Type");
					HashMap<String, HashMap<String, String>> testData = testInfo.readTestRunner(testType,setting.getTestRunner(),"Test_ID");
					testLineItem = testData.get(entry.getKey());
					Runnable runner = new Runner(entry.getValue(),testLineItem);
					executor.execute(runner);
				}
			}
			executor.shutdown();
			while (!executor.isTerminated()) {
			}
			System.out.println("Finished running all tests!!!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
