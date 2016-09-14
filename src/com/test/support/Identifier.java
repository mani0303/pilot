package com.test.support;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.util.Properties;
/**
 * File name   :Identifier.java
 * Description  : 
 * Date created :13 Sep 2016
 * Author 		:Veera
 */
public class Identifier {
	private static Identifier Identifier;
	private Properties properties;
	private Settings settings = Settings.getInstance();
	private Identifier(){};
	public static Identifier getInstance(){
		if(Identifier==null){
			Identifier= new Identifier();
		}
		return Identifier;
	}
	/**
	 * Method name  : setIdentificationProperties
	 * Return types : void
	 * Description  :
	 */
	private void setIdentificationProperties(){
		String elementIdentifiersFileName;
		String identifiersDir = settings.getElementPropertiesDir();
		File elementIdentifiersDir = new File(identifiersDir);
		properties = new Properties();
		FilenameFilter filter = new FilenameFilter() {
	        public boolean accept(File dir, String name) {
	            return name.endsWith(".properties");
	        }
	    };
		String[] elementIdentifiersFileNames = elementIdentifiersDir.list(filter);
		for(int fileIndex=0;fileIndex<elementIdentifiersFileNames.length;fileIndex++){
			elementIdentifiersFileName=elementIdentifiersFileNames[fileIndex];
			try {
				InputStream inStream = new FileInputStream(identifiersDir+elementIdentifiersFileName);
				properties.load(inStream);
			} catch (Exception e) {
				new Exception("Error in reading configuration file!!!");
			}
		}
	}
	/**
	 * Method name  : getIdentifier
	 * Return types : String
	 * Description  :
	 */
	public String getIdentifier(String property){
		if(properties==null){
			setIdentificationProperties();
		}
		if(properties.getProperty(property)!=null){
			return properties.getProperty(property);
		}else{
			return "No such property";
		}
	}
}
