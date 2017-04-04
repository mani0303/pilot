package com.test.support;

import java.io.File;

public class Settings {
	private static Settings settings;
	private String projectPath;
	private String configurationProp;
	private String testRunner;
	private String elementPropertiesDir;
	private String driverEXEDir;
	private String reportDetailsDir;
	private String JsonFilesDir;
	private String XMLFilesDir;
	private String PhantomJSPath;
	private Settings(){};

	/**
	 * 
	 * Method name  : getInstance
	 * Return types : Settings
	 * Description  :
	 */
	public static Settings getInstance(){
		if(settings==null){
			settings= new Settings();
		}
		return settings;
	}
	/**
	 * Method name  : setProjectPath
	 * Return types : void
	 * Description  :
	 */
	public void setProjectPath(){
		String projectPath = new File(System.getProperty("user.dir")).getAbsolutePath();
		this.projectPath=projectPath;
	}

	/**
	 * 
	 * Method name  : getProjectPath
	 * Return types : String
	 * Description  :
	 */
	public String getProjectPath() {
		if(projectPath==null){
			setProjectPath();
		}
		return projectPath;
	}
	/**
	 * Method name  : getConfigurationProp
	 * Return types : String
	 * Description  :
	 */
	public String getConfigurationProp() {
		if(configurationProp==null){
			setConfigurationProp();
		}
		return configurationProp;
	}

	/**
	 * Method name  : setConfigurationProp
	 * Return types : void
	 * Description  :
	 */
	public void setConfigurationProp() {
		this.configurationProp = getProjectPath()+System.getProperty("file.separator")+"resources"+System.getProperty("file.separator")+"configurations.properties";
	}
	/**
	 * 
	 * Method name  : getTestRunner
	 * Return types : String
	 * Description  :
	 */
	public String getTestRunner() {
		if(testRunner==null){
			setTestRunner();
		}
		return testRunner;
	}
	/**
	 * 
	 * Method name  : setTestRunner
	 * Return types : void
	 * Description  :
	 */
	public void setTestRunner() {
		this.testRunner = getProjectPath()+System.getProperty("file.separator")+"resources"+System.getProperty("file.separator")+"Test_Runner.xlsx";
	}

	/**
	 * 
	 * Method name  : getElementPropertiesDir
	 * Return types : String
	 * Description  :
	 */
	public String getElementPropertiesDir() {
		if(elementPropertiesDir==null){
			setElementPropertiesDir();
		}
		return elementPropertiesDir;
	}
	/**
	 * 
	 * Method name  : setElementPropertiesDir
	 * Return types : void
	 * Description  :
	 */
	public void setElementPropertiesDir() {
		this.elementPropertiesDir = getProjectPath()+System.getProperty("file.separator")+"resources"+System.getProperty("file.separator")+"element-properties"+System.getProperty("file.separator");
	}

	/**
	 * 
	 * Method name  : getDriverEXEDir
	 * Return types : String
	 * Description  :
	 */
	public String getDriverEXEDir() {
		if(driverEXEDir==null){
			setDriverEXEDir();
		}
		return driverEXEDir;
	}

	/**
	 * 
	 * Method name  : setDriverEXEDir
	 * Return types : void
	 * Description  :
	 */
	public void setDriverEXEDir() {
		this.driverEXEDir = getProjectPath()+System.getProperty("file.separator")+"resources"+System.getProperty("file.separator")+"driver-exe"+System.getProperty("file.separator");
	}

	/**
	 * 
	 * Method name  : getReportDetailsDir
	 * Return types : String
	 * Description  :
	 */
	public String getReportDetailsDir() {
		if(reportDetailsDir==null){
			setReportDetailsDir();
		}
		return reportDetailsDir;
	}
	/**
	 * 
	 * Method name  : setReportDetailsDir
	 * Return types : void
	 * Description  :
	 */
	public void setReportDetailsDir() {
		this.reportDetailsDir = getProjectPath()+System.getProperty("file.separator")+"report-resources"+System.getProperty("file.separator")+"report-details"+System.getProperty("file.separator");
	}
	/**
	 * 
	 * Method name  : getJsonFilesDir
	 * Return types : String
	 * Description  :
	 */
	public String getJsonFilesDir() {
		if(JsonFilesDir==null){
			setJsonFilesDir();
		}
		return JsonFilesDir;
	}

	/**
	 * 
	 * Method name  : getXMLFilesDir
	 * Return types : String
	 * Description  :
	 */
	public String getXMLFilesDir() {
		if(XMLFilesDir==null){
			setXMLFilesDir();
		}
		return XMLFilesDir;
	}

	/**
	 * 
	 * Method name  : setJsonFilesDir
	 * Return types : void
	 * Description  :
	 */
	public void setJsonFilesDir() {
		this.JsonFilesDir = getProjectPath()+System.getProperty("file.separator")+"resources"+System.getProperty("file.separator")+"api-tests-resources"+System.getProperty("file.separator")+"json-files"+System.getProperty("file.separator");
	}

	/**
	 * 
	 * Method name  : setJsonFilesDir
	 * Return types : void
	 * Description  :
	 */
	public void setXMLFilesDir() {
		this.XMLFilesDir = getProjectPath()+System.getProperty("file.separator")+"resources"+System.getProperty("file.separator")+"api-tests-resources"+System.getProperty("file.separator")+"xml-files"+System.getProperty("file.separator");
	}

	/**
	 * 
	 * Method name  : setPhantomJSPath
	 * Return types : void
	 * Description  :
	 */
	public void setPhantomJSPath() {
		this.PhantomJSPath = getProjectPath()+System.getProperty("file.separator")+"resources"+System.getProperty("file.separator")+"phantomjs"+System.getProperty("file.separator");
	}

	/**
	 * 
	 * Method name  : getPhantomJSPath
	 * Return types : String
	 * Description  :
	 */
	public String getPhantomJSPath() {
		if(PhantomJSPath==null){
			setPhantomJSPath();
		}
		return PhantomJSPath;
	}
}
