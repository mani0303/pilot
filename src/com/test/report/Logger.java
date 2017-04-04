package com.test.report;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import com.google.gson.Gson;
import com.jayway.restassured.response.Response;
import com.test.support.Settings;
/**
 * File name    :Logger.java
 * Description  : 
 * Date created :13 Sep 2016
 * Author 		:Veera
 */
public class Logger {
	Settings settings = Settings.getInstance();
	public static List<Object> reportList = new ArrayList<Object>();
	public static HashMap<String, Object> tests = new  HashMap<String,Object>();
	public static Gson gson = new Gson();
	private HashMap<String, Object> statistic;
	private HashMap<String, String> testParameters;
	private HashMap<String, Object> reportData;
	private WebDriver driver;
	private String modelHTML="";
	private String stepRowHTML="";
	private String fileFormat="html";
	public Response response;
	String reportDetailsDir=settings.getReportDetailsDir();
	HashMap<String, Object> stepInfo = new HashMap<String, Object>();
	public Logger(HashMap<String, String> testParameters,WebDriver driver){
		this.testParameters=testParameters;
		this.driver=driver;
	}

	public Logger(HashMap<String, String> testParameters){
		this.testParameters=testParameters;
	}

	/**
	 * Method name  : log
	 * Return types : void
	 * Description  :
	 */
	public void log(String step,String status){
		String testType=testParameters.get("Test_Type").trim();
		String testId = testParameters.get("Test_ID");
		String browser = testParameters.get("Browser_Name");
		int pass=(int) statistic.get("passed");
		int error=(int) statistic.get("errors");
		int warn=(int) statistic.get("warnings");
		int total=(int) statistic.get("total");
		++total;
		stepInfo.put("StepId", total);
		stepInfo.put("StepDesc", step);
		stepInfo.put("StepStatus", status);
		statistic.put("total",total);
		System.out.println("Id : "+testId+" Step: "+step+" Status: "+status);
		switch(status){
		case "PASS" :
			++pass;			
			statistic.put("passed",pass);
			break;
		case "FAIL" :
			++error;
			statistic.put("errors",error);
			break;
		case "INFO":
			++warn;
			statistic.put("warnings",warn);
			break;
		}
		if(driver!=null&&testType.equals("Functional")&&!browser.equalsIgnoreCase("headless")){
			captureScreen(total);
		}else if(testType.equals("API_Test")){
			captureResponse(total);
		}

		modelHTML=modelHTML+buildHTMLModel(total);
		stepRowHTML=stepRowHTML+buildHTMLRow(stepInfo);
	}

	/**
	 * Method name  : getResetedStatistic
	 * Return types : void
	 * Description  :
	 */
	public void getResetedStatistic(){
		HashMap<String, Object> statistic = new HashMap<String, Object>();
		statistic.put("passed", 0);
		statistic.put("errors", 0);
		statistic.put("warnings", 0);
		statistic.put("total", 0);
		this.statistic=statistic;
	}
	
	/**
	 * 
	 * Method name  : getInitializedReportData
	 * Return types : void
	 * Description  :
	 */
	public void getInitializedReportData(long startTime){
		String testId = testParameters.get("Test_ID");
		HashMap<String, Object> reportData = new HashMap<String, Object>();
		reportData.put("testId", testId);
		reportData.put("statistic", statistic);
		reportData.put("name", testParameters.get("Test_Description"));
		reportData.put("startedAt", startTime);
		reportData.put("failed", false);
		this.reportData=reportData;
	}
	/**
	 * 
	 * Method name  : finishTestReport
	 * Return types : void
	 * Description  :
	 */
	public synchronized void finishTestReport(long startTime){
		String testId = testParameters.get("Test_ID");
		long endTime = System.currentTimeMillis();
		long duration = endTime-startTime;
		reportData.put("endedAt", endTime);
		reportData.put("duration", duration);
		String reportPath=settings.getProjectPath();
		try {
			File htmlStepsTemplateFile = new File(settings.getReportDetailsDir()+testId+".html");
			String htmlStepDetailsString = TestReportTemplate.getStepsDetailTemplate();
			String modifiedStepDetailsReport = htmlStepDetailsString.replace("REPLACE THIS WITH MODEL CONTENT", modelHTML).replace("REPLACE THIS WITH ROWS CONTENT", stepRowHTML);
			FileUtils.writeStringToFile(htmlStepsTemplateFile, modifiedStepDetailsReport);
			reportList.add(this.reportData);
			tests.put("tests", reportList);
			String json = gson.toJson(tests);
			File htmlTemplateFile = new File(reportPath+"/TestReport.html");
			String htmlString = ReportTemplate.getReportTemplate();
			String modified = htmlString.replace("REPLACE THIS WITH TEST RESULTS", json);
			FileUtils.writeStringToFile(htmlTemplateFile, modified);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * Method name  : captureScreen
	 * Return types : void
	 * Description  :
	 */
	private void captureScreen(int stepId) {
		String testId = testParameters.get("Test_ID");
		fileFormat="png";
		try {
			//WebDriver augmentedDriver = new Augmenter().augment(driver); 
			File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(source, new File(reportDetailsDir+testId+"_"+stepId+".png"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * Method name  : setResponseInLogger
	 * Return types : void
	 * Description  :
	 */
	public void setResponseInLogger(Response response){
		this.response=response;
	}
	/**
	 * 
	 * Method name  : captureResponse
	 * Return types : void
	 * Description  :
	 */
	private void captureResponse(int stepId) {
		String testId = testParameters.get("Test_ID");
		String testDescription = testParameters.get("Test_Description");
		fileFormat="html";
		String responseHTML=TestReportTemplate.getResponseHTMLTemplate();
		File htmlResponseFile = null;
		try {
			htmlResponseFile = new File(reportDetailsDir+testId+"_"+stepId+".html");
			String modified = responseHTML.replace("REPLACE THIS WITH RESPONSE",response.asString()).replace("REPLACE THIS WITH DESCRIPTION", testDescription);
			FileUtils.writeStringToFile(htmlResponseFile, modified);
		} catch (Exception e) {
			String modified = responseHTML.replace("REPLACE THIS WITH RESPONSE","Logger to set response has not done");
			try {
				FileUtils.writeStringToFile(htmlResponseFile, modified);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	/**
	 * 
	 * Method name  : buildHTMLModel
	 * Return types : String
	 * Description  :
	 */
	private String buildHTMLModel(int stepId){
		String testId = testParameters.get("Test_ID");
		String modelDiv="<div id="+testId+"_"+stepId+" class=\"modal modal-wide fade\">\r\n" + 
				"  <div class=\"modal-dialog\">\r\n" + 
				"    <div class=\"modal-content\">\r\n" + 
				"      <div class=\"modal-header\">\r\n" + 
				"        <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\">&times;</button>\r\n" + 
				"        <h4 class=\"modal-title\">Modal title</h4>\r\n" + 
				"      </div>\r\n" + 
				"      <div class=\"modal-body\" style=\"width: 1200px; height: 500px;\">\r\n" + 
				"		  <div><img src=\"file:///"+reportDetailsDir+testId+"_"+stepId+"."+fileFormat+"\" style=\"width: 550px; height: 500px;\"></div>\r\n" + 
				"      </div>\r\n" + 
				"      <div class=\"modal-footer\">\r\n" + 
				"        <button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">Close</button>\r\n" + 
				"      </div>\r\n" + 
				"    </div>\r\n" + 
				"  </div>\r\n" + 
				"</div>";
		return modelDiv;
	}
	/**
	 * 
	 * Method name  : buildHTMLRow
	 * Return types : String
	 * Description  :
	 */
	public String buildHTMLRow(HashMap<String, Object> stepInfo){
		String testId = testParameters.get("Test_ID");
		String htmlRow="";
		if(stepInfo.get("StepStatus").equals("PASS")){
			htmlRow="<tr class=\"table-success\">\r\n" + 
					"      <th scope=\"row\">"+stepInfo.get("StepId")+"</th>\r\n" + 
					"      <td>"+stepInfo.get("StepDesc")+"</td>\r\n" + 
					"		<td><a href=\".\\"+testId+"_"+stepInfo.get("StepId")+"."+fileFormat+"\">"+stepInfo.get("StepStatus")+"</a></td>\r\n" +
					"    </tr>";

		}else if(stepInfo.get("StepStatus").equals("FAIL")){
			htmlRow="<tr class=\"table-danger\">\r\n" + 
					"      <th scope=\"row\">"+stepInfo.get("StepId")+"</th>\r\n" + 
					"      <td>"+stepInfo.get("StepDesc")+"</td>\r\n" + 
					"		<td><a href=\".\\"+testId+"_"+stepInfo.get("StepId")+"."+fileFormat+"\">"+stepInfo.get("StepStatus")+"</a></td>\r\n" + 
					"    </tr>";

		}else{
			htmlRow="<tr class=\"table-info\">\r\n" + 
					"      <th scope=\"row\">"+stepInfo.get("StepId")+"</th>\r\n" + 
					"      <td>"+stepInfo.get("StepDesc")+"</td>\r\n" + 
					//					"		<td><a href=\"#"+testId+"_"+stepInfo.get("StepId")+"\" data-toggle=\"modal\">"+stepInfo.get("StepStatus")+"</a></td>\r\n" + 
					"		<td><a href=\".\\"+testId+"_"+stepInfo.get("StepId")+"."+fileFormat+"\">"+stepInfo.get("StepStatus")+"</a></td>\r\n" + 
					"    </tr>";
		}
		return htmlRow;
	}
}
