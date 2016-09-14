package com.test.support;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 * File name   :TestRunnerInfo.java
 * Description  : 
 * Date created :13 Sep 2016
 * Author 		:Veera
 */
public class TestRunnerInfo {
	private static TestRunnerInfo testRunnerInfo;
	private TestRunnerInfo(){};
	public static TestRunnerInfo getInstance(){
		if(testRunnerInfo==null){
			testRunnerInfo= new TestRunnerInfo();
		}
		return testRunnerInfo;
	}
	/**
	 * 
	 * Method name  : readTestRunner
	 * Return types : LinkedHashMap<String,HashMap<String,String>>
	 * Description  :
	 */
	public LinkedHashMap<String, HashMap<String, String>> readTestRunner(String workSheet,String workBook,String referenceColumn){
		LinkedHashMap<String,HashMap<String,String>> sheetInfo = new LinkedHashMap<String,HashMap<String,String>>();
		HashMap<String,String> rowInfo = null;
		String testID = null;
		DataFormatter formatter = new DataFormatter();
		XSSFWorkbook wb;
		try {
			wb = new XSSFWorkbook(new File(workBook));
			XSSFSheet sheet = wb.getSheet(workSheet);
			XSSFRow header = sheet.getRow(0);
			String headerStr;			
			for (Iterator<Row> iterator = sheet.iterator(); iterator.hasNext();) {
				XSSFRow row = (XSSFRow) iterator.next();
				rowInfo = new HashMap<String,String>();
				if(row.getRowNum()!=0){
					for (int i = 0; i < row.getLastCellNum(); i++) {
						XSSFCell cell = row.getCell(i);
						headerStr = formatter.formatCellValue(header.getCell(i));
						if(headerStr.equals(referenceColumn)){
							testID=formatter.formatCellValue(cell);
						}
						rowInfo.put(headerStr, formatter.formatCellValue(cell));
					}
					sheetInfo.put(testID, rowInfo);
				}
			}
			wb.close();
			return sheetInfo;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sheetInfo;
	}
}
