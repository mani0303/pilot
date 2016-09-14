
package com.test.apitest;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Set;
import org.apache.commons.io.IOUtils;
import com.jayway.restassured.response.Response;
import com.test.report.Logger;
import com.test.support.Settings;

/**
 * File name   :ApiTestHelper.java
 * Description  : 
 * Date created :13 Sep 2016
 * Author 		:Veera
 */

public class ApiTestHelper{
	private Logger log;
	private HashMap<String, String> data;
	public static  String ACCESS_TOKEN;
	Settings settings = Settings.getInstance();
	HashMap<String, String> parameters = new HashMap<String, String>();
	HashMap<String, String> headers = new HashMap<String, String>();
	public ApiTestHelper(Logger log, HashMap<String, String> data) {
		this.log = log;
		this.data = data;
	}

	/**
	 * 
	 * Method name  : addHeaders
	 * Return types : HashMap<String,String> :
	 * Description  :
	 */
	public HashMap<String, String> addHeaders(){
		Set<String> columnNames = data.keySet();
		for(String columnName:columnNames){
			if(columnName.toUpperCase().trim().contains("HEADER")){
				headers.put(columnName.split(":")[1], data.get(columnName));	
			}
		}
		return headers;
	}

	/**
	 * 
	 * Method name  : addHeaders
	 * Return types : HashMap<String,String>
	 * Description  :
	 */
	public HashMap<String, String> addHeaders(String headerName,String headerValue){
		Set<String> columnNames = data.keySet();
		for(String columnName:columnNames){
			if(columnName.toUpperCase().trim().contains("HEADER")){
				headers.put(columnName.split(":")[1], data.get(columnName));	
			}
		}
		headers.put(headerName, headerValue);
		return headers;
	}
	/**
	 * 
	 * Method name  : addParameters
	 * Return types : HashMap<String,String>
	 * Description  :
	 */
	public HashMap<String, String> addParameters(){
		Set<String> columnNames = data.keySet();
		for(String columnName:columnNames){
			if(columnName.toUpperCase().trim().contains("PARAMETER")){
				parameters.put(columnName.split(":")[1], data.get(columnName));	
			}
		}
		return parameters;
	}

	/**
	 * 
	 * Method name  : addParameters
	 * Return types : HashMap<String,String>
	 * Description  :
	 */
	public HashMap<String, String> addParameters(String paramName,String paramValue){
		Set<String> columnNames = data.keySet();
		for(String columnName:columnNames){
			if(columnName.toUpperCase().trim().contains("PARAMETER")){
				parameters.put(columnName.split(":")[1], data.get(columnName));	
			}
		}
		parameters.put(paramName, data.get(paramValue));
		return parameters;
	}
	/**
	 * 
	 * Method name  : setAccessToken
	 * Return types : void
	 * Description  :
	 */
	public void setAccessToken(Response accessTokenResponse){
		ACCESS_TOKEN=accessTokenResponse.body().jsonPath().getString("access_token");
	}

	/**
	 * 
	 * Method name  : getJsonFileContent
	 * Return types : String
	 * Description  :
	 */
	public String getJsonFileContent(String fileName){
		String jsonFilesPath = settings.getJsonFilesDir();
		String jsonFileAbsPath=jsonFilesPath+fileName+".json";
		String jsonFileContent = null;
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(jsonFileAbsPath);
			jsonFileContent = IOUtils.toString(inputStream);
			inputStream.close();
		} catch(Exception e){
			e.printStackTrace();
		}
		return jsonFileContent;
	}
	/**
	 * Method name  : getURLParam
	 * Return types : String
	 * Description  :
	 */
	public String getURLParam(){
		Set<String> columnNames = data.keySet();
		String finalURLParam="";
		for(String columnName:columnNames){
			if(columnName.toUpperCase().trim().contains("URLPARAM")){
				finalURLParam=finalURLParam+"/"+data.get(columnName);
			}
		}
		return finalURLParam;
	}
}
