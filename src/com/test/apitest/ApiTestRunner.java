package com.test.apitest;

import static com.jayway.restassured.RestAssured.*;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.jayway.restassured.response.Response;
import com.test.core.RunnerHelper;
import com.test.report.Logger;
import com.test.support.Status;

/** File name   :ApiTestRunner.java
 * Description  : 
 * Date created :13 Sep 2016
 * Author 		:Veera
 */
public class ApiTestRunner {
	Logger log;
	public static String eTag;
	JsonParser parser = new JsonParser();
	public HashMap<String, String> data;
	ApiTestHelper ath;
	@Retention(RetentionPolicy.RUNTIME)
	@interface Test{
		String description();
	}

	public ApiTestRunner(RunnerHelper helper){
		log = helper.log;
		data=helper.testData;
		ath = new ApiTestHelper(log,data);
	}

	/**
	 * Method name  : postRequestWithParameters
	 * Return types : void
	 * Description  :
	 */
	@Test(description= "Do Post request with parameters")
	public void postRequestWithParameters(){
		String endPoint = data.get("Endpoint");
		String resource = data.get("Resource");
		String urlParameters = ath.getURLParam();
		HashMap<String, String> parameters = ath.addParameters();
		System.out.println(endPoint+resource+urlParameters);
		Response response =with().parameters(parameters).post(endPoint+resource);
		log.setResponseInLogger(response);
		ath.setAccessToken(response);
		log.log("My step", "FAIL");
		log.log("My step", "PASS");
	}

	/**
	 * Method name  : getRequestWithHeaders
	 * Return types : void
	 * Description  :
	 */
	@Test(description= "Do Get request with headers")
	public void getRequestWithHeaders(){
		String endPoint = data.get("Endpoint");
		String resource = data.get("Resource");
		HashMap<String, String> headers = ath.addHeaders("Authorization","Bearer "+ApiTestHelper.ACCESS_TOKEN);
		Response response =with().headers(headers).get(endPoint+resource);
		log.setResponseInLogger(response);
		int statusCode = response.getStatusCode();
		System.out.println(statusCode);
		System.out.println(response.asString());
		log.log("My step", "FAIL");
		log.log("My step", "PASS");
	}
	/**
	 * Method name  : getRequest
	 * Return types : void
	 * Description  :
	 */
	@Test(description= "Do Get request without headers and parameters")
	public void getRequest(){
		String endPoint = data.get("Endpoint");
		String resource = data.get("Resource");
		System.out.println(endPoint+resource);
		Response response =get(endPoint+resource);
		int statusCode = response.getStatusCode();
		log.setResponseInLogger(response);
		System.out.println(statusCode);
		System.out.println(response.asString());
		log.log("My step", "FAIL");
		log.log("My step", "PASS");
	}

	/**
	 * Method name  : verifyPostJsonResponse
	 * Return types : void
	 * Description  :
	 */
	@Test(description= "PUT Salvador-JSON-Ephemeral")
	public void verifyPostJsonResponse(){
		try{
			String jsonFileName=data.get("Json_File_Name");
			String endPoint = data.get("Endpoint");
			String resource = data.get("Resource");
			String urlParameters = ath.getURLParam();
			String putBody=ath.getJsonFileContent(jsonFileName);
			Response response =given().contentType("application/json").with().body(putBody).put(endPoint+resource+urlParameters);
			log.setResponseInLogger(response);
			log.log("Salvador PUT Endpoint:  "+endPoint+resource+urlParameters, Status.INFO);
			if(response.statusCode()==200){
				log.log("Status code validation: "+200, Status.PASS);
			}else{
				log.log("Status code validation: "+response.statusCode(), Status.FAIL);
			}
			log.log("Salvador PUT Headers:  "+response.getHeaders().toString(), Status.INFO);
			log.log("Salvador PUT Status Line:  "+response.statusLine(), Status.INFO);
			log.log("Salvador PUT Time duration:  "+response.getTimeIn(TimeUnit.MILLISECONDS), Status.INFO);
			JsonElement actualResponse = parser.parse(response.asString());
			JsonElement expectedResponse = parser.parse(ath.getJsonFileContent(jsonFileName));
			if(actualResponse.equals(expectedResponse)){
				log.log("Salvador PUT request body matches with reponse body", Status.PASS);
			}else{
				log.log("Salvador PUT request body doesn't match with reponse body", Status.FAIL);
			}
		}catch(Exception e){
			log.log("Exception occurred in the test "+ e.getMessage(), Status.FAIL);
		}
	}
	
	/**
	 * Method name  : verifyGetJsonResponse
	 * Return types : void
	 * Description  :
	 */
	@Test(description= "GET Salvador-JSON-Ephemeral")
	public void verifyGetJsonResponse(){
		try{
			String jsonFileName=data.get("Json_File_Name");
			String endPoint = data.get("Endpoint");
			String resource = data.get("Resource");
			String urlParameters = ath.getURLParam();
			Response response =get(endPoint+resource+urlParameters);
			log.setResponseInLogger(response);
			log.log("Salvador GET Endpoint:  "+endPoint+resource+urlParameters, Status.INFO);
			if(response.statusCode()==200){
				eTag=response.getHeader("ETag").replaceAll("\"", "");
				log.log("Status code validation: "+200, Status.PASS);
			}else{
				log.log("Status code validation: "+response.statusCode(), Status.FAIL);
			}
			log.log("Salvador GET Headers:  "+response.getHeaders().toString(), Status.INFO);
			log.log("Salvador GET Status Line:  "+response.statusLine(), Status.INFO);
			log.log("Salvador GET Time duration:  "+response.getTimeIn(TimeUnit.MILLISECONDS), Status.INFO);
			JsonElement actualResponse = parser.parse(response.asString());
			JsonElement expectedResponse = parser.parse(ath.getJsonFileContent(jsonFileName));
			if(actualResponse.equals(expectedResponse)){
				log.log("Salvador GET request body matches with reponse body", Status.PASS);
			}else{
				log.log("Salvador GET request body doesn't match with reponse body", Status.FAIL);
			}
		}catch(Exception e){
			log.log("Exception occurred in the test "+ e.getMessage(), Status.FAIL);
		}
	}

	/**
	 * Method name  : verifyUpdatePostJsonResponse
	 * Return types : void
	 * Description  :
	 */
	@Test(description= "UPDATE Salvador-JSON-Ephemeral")
	public void verifyUpdatePostJsonResponse(){
		try{
			String jsonFileName=data.get("Json_File_Name");
			String endPoint = data.get("Endpoint");
			String resource = data.get("Resource");
			String urlParameters = ath.getURLParam();
			String putBody=ath.getJsonFileContent(jsonFileName).replace("REPLACE_VERSION", eTag);
			System.out.println(putBody);
			Response response =given().contentType("application/json").with().body(putBody).put(endPoint+resource+urlParameters);
			log.setResponseInLogger(response);
			log.log("Salvador PUT Endpoint:  "+endPoint+resource+urlParameters, Status.INFO);
			if(response.statusCode()==200){
				//eTag=response.getHeader("ETag").replaceAll("\"", "");
				log.log("Status code validation: "+200, Status.PASS);
			}else{
				log.log("Status code validation: "+response.statusCode(), Status.FAIL);
			}
			log.log("Salvador PUT Headers:  "+response.getHeaders().toString(), Status.INFO);
			log.log("Salvador PUT Status Line:  "+response.statusLine(), Status.INFO);
			log.log("Salvador PUT Time duration:  "+response.getTimeIn(TimeUnit.MILLISECONDS), Status.INFO);
			JsonElement actualResponse = parser.parse(response.asString());
			JsonElement expectedResponse = parser.parse(ath.getJsonFileContent(jsonFileName).replace("\"version\" : \"REPLACE_VERSION\",", ""));
			if(actualResponse.equals(expectedResponse)){
				log.log("Salvador PUT request body matches with reponse body", Status.PASS);
			}else{
				log.log("Salvador PUT request body doesn't match with reponse body", Status.FAIL);
			}
		}catch(Exception e){
			log.log("Exception occurred in the test "+ e.getMessage(), Status.FAIL);
		}
	}

	/**
	 * Method name  : verifyDeleteJsonResponse
	 * Return types : void
	 * Description  :
	 */
	@Test(description= "DELETE Salvador-JSON-Ephemeral")
	public void verifyDeleteJsonResponse(){
		try{
			String endPoint = data.get("Endpoint");
			String resource = data.get("Resource");
			String urlParameters = ath.getURLParam();
			Response response =delete(endPoint+resource+urlParameters+"/"+eTag);
			log.setResponseInLogger(response);
			log.log("Salvador DELETE Endpoint:  "+endPoint+resource+urlParameters+"/"+eTag, Status.INFO);
			if(response.statusCode()==200){
				log.log("Status code validation: "+200, Status.PASS);
			}else{
				log.log("Status code validation: "+response.statusCode(), Status.FAIL);
			}
			log.log("Salvador DELETE Headers:  "+response.getHeaders().toString(), Status.INFO);
			log.log("Salvador DELETE Status Line:  "+response.statusLine(), Status.INFO);
			log.log("Salvador DELETE Time duration:  "+response.getTimeIn(TimeUnit.MILLISECONDS), Status.INFO);
		}catch(Exception e){
			log.log("Exception occurred in the test "+ e.getMessage(), Status.FAIL);
		}
	}



/**
 * Method name  : verifyPostJsonResponse
 * Return types : void
 * Description  :
 */
@Test(description= "PUT Salvador-XML-Permanent")
public void verifyPostXMLResponse1(){
	try{
		String xmlFileName=data.get("Json_File_Name");
		String endPoint = data.get("Endpoint");
		String resource = data.get("Resource");
		String urlParameters = ath.getURLParam();
		String putBody=ath.getXMLFileContent(xmlFileName);
		Response response =given().contentType("application/xml").with().body(putBody).put(endPoint+resource+urlParameters);
		log.setResponseInLogger(response);
		log.log("Salvador PUT Endpoint:  "+endPoint+resource+urlParameters, Status.INFO);
		if(response.statusCode()==200){
			log.log("Status code validation: "+200, Status.PASS);
		}else{
			log.log("Status code validation: "+response.statusCode(), Status.FAIL);
		}
		log.log("Salvador PUT Headers:  "+response.getHeaders().toString(), Status.INFO);
		log.log("Salvador PUT Status Line:  "+response.statusLine(), Status.INFO);
		log.log("Salvador PUT Time duration:  "+response.getTimeIn(TimeUnit.MILLISECONDS), Status.INFO);
		String actualResponse = response.asString();
		String expectedResponse = ath.getXMLFileContent(xmlFileName);
		if(expectedResponse.contains(actualResponse)){
			log.log("Salvador PUT request body matches with reponse body", Status.PASS);
		}else{
			log.log("Salvador PUT request body doesn't match with reponse body", Status.FAIL);
		}
	}catch(Exception e){
		log.log("Exception occurred in the test "+ e.getMessage(), Status.FAIL);
	}
}

/**
 * Method name  : verifyGetJsonResponse
 * Return types : void
 * Description  :
 */
@Test(description= "GET Salvador-XML-Ephemeral")
public void verifyGetXMLResponse(){
	try{
		String xmlFileName=data.get("Json_File_Name");
		String endPoint = data.get("Endpoint");
		String resource = data.get("Resource");
		String urlParameters = ath.getURLParam();
		Response response =get(endPoint+resource+urlParameters);
		log.setResponseInLogger(response);
		log.log("Salvador GET Endpoint:  "+endPoint+resource+urlParameters, Status.INFO);
		if(response.statusCode()==200){
			//eTag=response.getHeader("ETag").replaceAll("\"", "");
			log.log("Status code validation: "+200, Status.PASS);
		}else{
			log.log("Status code validation: "+response.statusCode(), Status.FAIL);
		}
		log.log("Salvador GET Headers:  "+response.getHeaders().toString(), Status.INFO);
		log.log("Salvador GET Status Line:  "+response.statusLine(), Status.INFO);
		log.log("Salvador GET Time duration:  "+response.getTimeIn(TimeUnit.MILLISECONDS), Status.INFO);
		String actualResponse = response.asString();
		String expectedResponse = ath.getXMLFileContent(xmlFileName);
		System.out.println("actualResponse :"+actualResponse);
		System.out.println("expectedResponse :"+expectedResponse);
		if(expectedResponse.contains(actualResponse)){
			log.log("Salvador GET request body matches with reponse body", Status.PASS);
		}else{
			log.log("Salvador GET request body doesn't match with reponse body", Status.FAIL);
		}
	}catch(Exception e){
		log.log("Exception occurred in the test "+ e.getMessage(), Status.FAIL);
	}
}

/**
 * Method name  : verifyUpdatePostJsonResponse
 * Return types : void
 * Description  :
 */
@Test(description= "UPDATE Salvador-XML-Ephemeral")
public void verifyUpdatePostXMLResponse(){
	try{
		String xmlFileName=data.get("Json_File_Name");
		String endPoint = data.get("Endpoint");
		String resource = data.get("Resource");
		String urlParameters = ath.getURLParam();
		String putBody=ath.getXMLFileContent(xmlFileName);
		System.out.println(putBody);
		Response response =given().contentType("application/xml").with().body(putBody).put(endPoint+resource+urlParameters);
		log.setResponseInLogger(response);
		log.log("Salvador PUT Endpoint:  "+endPoint+resource+urlParameters, Status.INFO);
		if(response.statusCode()==200){
			//eTag=response.getHeader("ETag").replaceAll("\"", "");
			log.log("Status code validation: "+200, Status.PASS);
		}else{
			log.log("Status code validation: "+response.statusCode(), Status.FAIL);
		}
		log.log("Salvador PUT Headers:  "+response.getHeaders().toString(), Status.INFO);
		log.log("Salvador PUT Status Line:  "+response.statusLine(), Status.INFO);
		log.log("Salvador PUT Time duration:  "+response.getTimeIn(TimeUnit.MILLISECONDS), Status.INFO);
		String actualResponse = response.asString();
		String expectedResponse = ath.getXMLFileContent(xmlFileName);
		if(expectedResponse.contains(actualResponse)){
			log.log("Salvador PUT request body matches with reponse body", Status.PASS);
		}else{
			log.log("Salvador PUT request body doesn't match with reponse body", Status.FAIL);
		}
	}catch(Exception e){
		log.log("Exception occurred in the test "+ e.getMessage(), Status.FAIL);
	}
}

/**
 * Method name  : verifyDeleteJsonResponse
 * Return types : void
 * Description  :
 */
@Test(description= "DELETE Salvador-XML-Ephemeral")
public void verifyDeleteXMLResponse(){
	try{
		String endPoint = data.get("Endpoint");
		String resource = data.get("Resource");
		String urlParameters = ath.getURLParam();
		Response response =delete(endPoint+resource+urlParameters);
		log.setResponseInLogger(response);
		log.log("Salvador DELETE Endpoint:  "+endPoint+resource+urlParameters, Status.INFO);
		if(response.statusCode()==200){
			log.log("Status code validation: "+200, Status.PASS);
		}else{
			log.log("Status code validation: "+response.statusCode(), Status.FAIL);
		}
		log.log("Salvador DELETE Headers:  "+response.getHeaders().toString(), Status.INFO);
		log.log("Salvador DELETE Status Line:  "+response.statusLine(), Status.INFO);
		log.log("Salvador DELETE Time duration:  "+response.getTimeIn(TimeUnit.MILLISECONDS), Status.INFO);
	}catch(Exception e){
		log.log("Exception occurred in the test "+ e.getMessage(), Status.FAIL);
	}
}
/**
 * Method name  : verifyPostJsonResponse
 * Return types : void
 * Description  :
 */
@Test(description= "PUT Salvador-JSON-Permanent")
public void verifyPostJsonResponse1(){
	try{
		String jsonFileName=data.get("Json_File_Name");
		String endPoint = data.get("Endpoint");
		String resource = data.get("Resource");
		String urlParameters = ath.getURLParam();
		String putBody=ath.getJsonFileContent(jsonFileName);
		Response response =given().contentType("application/json").with().body(putBody).put(endPoint+resource+urlParameters);
		log.setResponseInLogger(response);
		log.log("Salvador PUT Endpoint:  "+endPoint+resource+urlParameters, Status.INFO);
		if(response.statusCode()==200){
			log.log("Status code validation: "+200, Status.PASS);
		}else{
			log.log("Status code validation: "+response.statusCode(), Status.FAIL);
		}
		log.log("Salvador PUT Headers:  "+response.getHeaders().toString(), Status.INFO);
		log.log("Salvador PUT Status Line:  "+response.statusLine(), Status.INFO);
		log.log("Salvador PUT Time duration:  "+response.getTimeIn(TimeUnit.MILLISECONDS), Status.INFO);
		JsonElement actualResponse = parser.parse(response.asString());
		JsonElement expectedResponse = parser.parse(ath.getJsonFileContent(jsonFileName));
		if(actualResponse.equals(expectedResponse)){
			log.log("Salvador PUT request body matches with reponse body", Status.PASS);
		}else{
			log.log("Salvador PUT request body doesn't match with reponse body", Status.FAIL);
		}
	}catch(Exception e){
		log.log("Exception occurred in the test "+ e.getMessage(), Status.FAIL);
	}
}

/**
 * Method name  : verifyGetJsonResponse
 * Return types : void
 * Description  :
 */
@Test(description= "GET Salvador-JSON-Permanent")
public void verifyGetJsonResponse1(){
	try{
		String jsonFileName=data.get("Json_File_Name");
		String endPoint = data.get("Endpoint");
		String resource = data.get("Resource");
		String urlParameters = ath.getURLParam();
		Response response =get(endPoint+resource+urlParameters);
		log.setResponseInLogger(response);
		log.log("Salvador GET Endpoint:  "+endPoint+resource+urlParameters, Status.INFO);
		if(response.statusCode()==200){
			eTag=response.getHeader("ETag").replaceAll("\"", "");
			log.log("Status code validation: "+200, Status.PASS);
		}else{
			log.log("Status code validation: "+response.statusCode(), Status.FAIL);
		}
		log.log("Salvador GET Headers:  "+response.getHeaders().toString(), Status.INFO);
		log.log("Salvador GET Status Line:  "+response.statusLine(), Status.INFO);
		log.log("Salvador GET Time duration:  "+response.getTimeIn(TimeUnit.MILLISECONDS), Status.INFO);
		JsonElement actualResponse = parser.parse(response.asString());
		JsonElement expectedResponse = parser.parse(ath.getJsonFileContent(jsonFileName));
		if(actualResponse.equals(expectedResponse)){
			log.log("Salvador GET request body matches with reponse body", Status.PASS);
		}else{
			log.log("Salvador GET request body doesn't match with reponse body", Status.FAIL);
		}
	}catch(Exception e){
		log.log("Exception occurred in the test "+ e.getMessage(), Status.FAIL);
	}
}

/**
 * Method name  : verifyUpdatePostJsonResponse
 * Return types : void
 * Description  :
 */
@Test(description= "UPDATE Salvador-JSON-Permanent")
public void verifyUpdatePostJsonResponse1(){
	try{
		String jsonFileName=data.get("Json_File_Name");
		String endPoint = data.get("Endpoint");
		String resource = data.get("Resource");
		String urlParameters = ath.getURLParam();
		String putBody=ath.getJsonFileContent(jsonFileName).replace("REPLACE_VERSION", eTag);
		System.out.println(putBody);
		Response response =given().contentType("application/json").with().body(putBody).put(endPoint+resource+urlParameters);
		log.setResponseInLogger(response);
		log.log("Salvador PUT Endpoint:  "+endPoint+resource+urlParameters, Status.INFO);
		if(response.statusCode()==200){
			//eTag=response.getHeader("ETag").replaceAll("\"", "");
			log.log("Status code validation: "+200, Status.PASS);
		}else{
			log.log("Status code validation: "+response.statusCode(), Status.FAIL);
		}
		log.log("Salvador PUT Headers:  "+response.getHeaders().toString(), Status.INFO);
		log.log("Salvador PUT Status Line:  "+response.statusLine(), Status.INFO);
		log.log("Salvador PUT Time duration:  "+response.getTimeIn(TimeUnit.MILLISECONDS), Status.INFO);
		JsonElement actualResponse = parser.parse(response.asString());
		JsonElement expectedResponse = parser.parse(ath.getJsonFileContent(jsonFileName).replace("\"version\" : \"REPLACE_VERSION\",", ""));
		if(actualResponse.equals(expectedResponse)){
			log.log("Salvador PUT request body matches with reponse body", Status.PASS);
		}else{
			log.log("Salvador PUT request body doesn't match with reponse body", Status.FAIL);
		}
	}catch(Exception e){
		log.log("Exception occurred in the test "+ e.getMessage(), Status.FAIL);
	}
}

/**
 * Method name  : verifyDeleteJsonResponse
 * Return types : void
 * Description  :
 */
@Test(description= "DELETE Salvador-JSON-Permanent")
public void verifyDeleteJsonResponse1(){
	try{
		String endPoint = data.get("Endpoint");
		String resource = data.get("Resource");
		String urlParameters = ath.getURLParam();
		Response response =delete(endPoint+resource+urlParameters+"/"+eTag);
		log.setResponseInLogger(response);
		log.log("Salvador DELETE Endpoint:  "+endPoint+resource+urlParameters+"/"+eTag, Status.INFO);
		if(response.statusCode()==200){
			log.log("Status code validation: "+200, Status.PASS);
		}else{
			log.log("Status code validation: "+response.statusCode(), Status.FAIL);
		}
		log.log("Salvador DELETE Headers:  "+response.getHeaders().toString(), Status.INFO);
		log.log("Salvador DELETE Status Line:  "+response.statusLine(), Status.INFO);
		log.log("Salvador DELETE Time duration:  "+response.getTimeIn(TimeUnit.MILLISECONDS), Status.INFO);
	}catch(Exception e){
		log.log("Exception occurred in the test "+ e.getMessage(), Status.FAIL);
	}
}


/**
 * Method name  : verifyPostJsonResponse
 * Return types : void
 * Description  :
 */
@Test(description= "PUT Salvador-XML-Ephemeral")
public void verifyPostXMLResponse(){
	try{
		String xmlFileName=data.get("Json_File_Name");
		String endPoint = data.get("Endpoint");
		String resource = data.get("Resource");
		String urlParameters = ath.getURLParam();
		String putBody=ath.getXMLFileContent(xmlFileName);
		Response response =given().contentType("application/xml").with().body(putBody).put(endPoint+resource+urlParameters);
		log.setResponseInLogger(response);
		log.log("Salvador PUT Endpoint:  "+endPoint+resource+urlParameters, Status.INFO);
		if(response.statusCode()==200){
			log.log("Status code validation: "+200, Status.PASS);
		}else{
			log.log("Status code validation: "+response.statusCode(), Status.FAIL);
		}
		log.log("Salvador PUT Headers:  "+response.getHeaders().toString(), Status.INFO);
		log.log("Salvador PUT Status Line:  "+response.statusLine(), Status.INFO);
		log.log("Salvador PUT Time duration:  "+response.getTimeIn(TimeUnit.MILLISECONDS), Status.INFO);
		String actualResponse = response.asString();
		String expectedResponse = ath.getXMLFileContent(xmlFileName);
		if(expectedResponse.contains(actualResponse)){
			log.log("Salvador PUT request body matches with reponse body", Status.PASS);
		}else{
			log.log("Salvador PUT request body doesn't match with reponse body", Status.FAIL);
		}
	}catch(Exception e){
		log.log("Exception occurred in the test "+ e.getMessage(), Status.FAIL);
	}
}

/**
 * Method name  : verifyGetJsonResponse
 * Return types : void
 * Description  :
 */
@Test(description= "GET Salvador-XML-Permanent")
public void verifyGetXMLResponse1(){
	try{
		String xmlFileName=data.get("Json_File_Name");
		String endPoint = data.get("Endpoint");
		String resource = data.get("Resource");
		String urlParameters = ath.getURLParam();
		Response response =get(endPoint+resource+urlParameters);
		log.setResponseInLogger(response);
		log.log("Salvador GET Endpoint:  "+endPoint+resource+urlParameters, Status.INFO);
		if(response.statusCode()==200){
			//eTag=response.getHeader("ETag").replaceAll("\"", "");
			log.log("Status code validation: "+200, Status.PASS);
		}else{
			log.log("Status code validation: "+response.statusCode(), Status.FAIL);
		}
		log.log("Salvador GET Headers:  "+response.getHeaders().toString(), Status.INFO);
		log.log("Salvador GET Status Line:  "+response.statusLine(), Status.INFO);
		log.log("Salvador GET Time duration:  "+response.getTimeIn(TimeUnit.MILLISECONDS), Status.INFO);
		String actualResponse = response.asString();
		String expectedResponse = ath.getXMLFileContent(xmlFileName);
		System.out.println("actualResponse :"+actualResponse);
		System.out.println("expectedResponse :"+expectedResponse);
		if(expectedResponse.contains(actualResponse)){
			log.log("Salvador GET request body matches with reponse body", Status.PASS);
		}else{
			log.log("Salvador GET request body doesn't match with reponse body", Status.FAIL);
		}
	}catch(Exception e){
		log.log("Exception occurred in the test "+ e.getMessage(), Status.FAIL);
	}
}

/**
 * Method name  : verifyUpdatePostJsonResponse
 * Return types : void
 * Description  :
 */
@Test(description= "UPDATE Salvador-XML-Permanent")
public void verifyUpdatePostXMLResponse1(){
	try{
		String xmlFileName=data.get("Json_File_Name");
		String endPoint = data.get("Endpoint");
		String resource = data.get("Resource");
		String urlParameters = ath.getURLParam();
		String putBody=ath.getXMLFileContent(xmlFileName);
		System.out.println(putBody);
		Response response =given().contentType("application/xml").with().body(putBody).put(endPoint+resource+urlParameters);
		log.setResponseInLogger(response);
		log.log("Salvador PUT Endpoint:  "+endPoint+resource+urlParameters, Status.INFO);
		if(response.statusCode()==200){
			//eTag=response.getHeader("ETag").replaceAll("\"", "");
			log.log("Status code validation: "+200, Status.PASS);
		}else{
			log.log("Status code validation: "+response.statusCode(), Status.FAIL);
		}
		log.log("Salvador PUT Headers:  "+response.getHeaders().toString(), Status.INFO);
		log.log("Salvador PUT Status Line:  "+response.statusLine(), Status.INFO);
		log.log("Salvador PUT Time duration:  "+response.getTimeIn(TimeUnit.MILLISECONDS), Status.INFO);
		String actualResponse = response.asString();
		String expectedResponse = ath.getXMLFileContent(xmlFileName);
		if(expectedResponse.contains(actualResponse)){
			log.log("Salvador PUT request body matches with reponse body", Status.PASS);
		}else{
			log.log("Salvador PUT request body doesn't match with reponse body", Status.FAIL);
		}
	}catch(Exception e){
		log.log("Exception occurred in the test "+ e.getMessage(), Status.FAIL);
	}
}

/**
 * Method name  : verifyDeleteJsonResponse
 * Return types : void
 * Description  :
 */
@Test(description= "DELETE Salvador-XML-Permanent")
public void verifyDeleteXMLResponse1(){
	try{
		String endPoint = data.get("Endpoint");
		String resource = data.get("Resource");
		String urlParameters = ath.getURLParam();
		Response response =delete(endPoint+resource+urlParameters);
		log.setResponseInLogger(response);
		log.log("Salvador DELETE Endpoint:  "+endPoint+resource+urlParameters, Status.INFO);
		if(response.statusCode()==200){
			log.log("Status code validation: "+200, Status.PASS);
		}else{
			log.log("Status code validation: "+response.statusCode(), Status.FAIL);
		}
		log.log("Salvador DELETE Headers:  "+response.getHeaders().toString(), Status.INFO);
		log.log("Salvador DELETE Status Line:  "+response.statusLine(), Status.INFO);
		log.log("Salvador DELETE Time duration:  "+response.getTimeIn(TimeUnit.MILLISECONDS), Status.INFO);
	}catch(Exception e){
		log.log("Exception occurred in the test "+ e.getMessage(), Status.FAIL);
	}
}
}

