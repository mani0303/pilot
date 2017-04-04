package com.test.core;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.io.FileUtils;

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
	String launchSummary = conf.getProperty("LaunchSummary");
	public static void main(String args[]){
		Test_Controller tc= new Test_Controller();
		tc.takeExecutionHistory();
		tc.controller();		
		tc.launchTestSummary();
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

	public void takeExecutionHistory(){
		String reportPath=setting.getProjectPath();		
		String history = System.getProperty("user.dir")+System.getProperty("file.separator")+"History"+System.getProperty("file.separator");
		new File("history").mkdirs();
		File historyDir = new File(history);
		FilenameFilter filter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.contains("Execution_");
			}
		};
		String[] resultsDir = historyDir.list(filter);
		int executionDirIndex = resultsDir.length+1;
		String executionDir = history+System.getProperty("file.separator")+"Execution_"+executionDirIndex;
		new File(executionDir).mkdirs();
		try {
			FileUtils.copyFileToDirectory(new File(reportPath+System.getProperty("file.separator")+"TestReport.html"), new File(executionDir),false);
			FileUtils.copyDirectoryToDirectory(new File(reportPath+System.getProperty("file.separator")+"report-resources"+System.getProperty("file.separator")),  new File(executionDir));
			FileUtils.cleanDirectory(new File(setting.getReportDetailsDir()));
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	public void launchTestSummary(){
		String reportPath=setting.getProjectPath();
		try {
			if(launchSummary.equalsIgnoreCase("yes")){				
				Runtime.getRuntime().exec(new String[]{"cmd", "/c","start chrome file:///"+reportPath+System.getProperty("file.separator")+"TestReport.html"});
			}
		} catch (Exception e) {
			try {
				Runtime.getRuntime().exec(new String[]{"cmd", "/c","start iexplore file:///"+reportPath+System.getProperty("file.separator")+"TestReport.html"});
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		}
	}
}
