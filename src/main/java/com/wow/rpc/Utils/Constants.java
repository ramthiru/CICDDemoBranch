package com.wow.rpc.Utils;

import java.io.FileInputStream;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import jxl.Sheet;

public class Constants {
	public static String testDataFilePath = null,objectRepositoriesFilePath = null, envPropFilePath = null, testCaseToRunPath = null, extentReportPath=null, objectRepoFilePath=null, extentReportName =null, log4jPropertiesFilePath=null;
//	public static String propsname;
	public static XSSFSheet ExcelWSheet;
	public static XSSFWorkbook ExcelWBook;
	public static XSSFCell dataCell;
	public static XSSFRow row;
	public static String testCaseExecution;
	public static Sheet TSSheet;
	protected FileInputStream excelFile = null;
	//public static WebDriver webdriver = null;
/*	public static String browserName;
	public static String environment;
	public static String testSetName;
	public static String testName;*/
	protected static Logger log = Logger.getLogger(Constants.class);
	
	
	
	public Constants() {
	//	propsname 		 = System.getProperty("user.dir")+"\\src\\test\\resources\\com.wow.rpc.TestExecutionSupporter\\RetailObjectRepository";
		objectRepositoriesFilePath 	= (new StringBuilder(String.valueOf(System.getProperty("user.dir")))).append("\\src\\test\\resources\\com.wow.rpc.TestExecutionSupporter\\RetailObjectRepository").toString();
		testDataFilePath = (new StringBuilder(String.valueOf(System.getProperty("user.dir")))).append("\\src\\test\\resources\\com.wow.rpc.TestExecutionSupporter\\Aut_Test_Data.xls").toString();
		envPropFilePath = (new StringBuilder(String.valueOf(System.getProperty("user.dir")))).append("\\src\\test\\resources\\com.wow.rpc.TestExecutionSupporter\\RetailEnv").toString();
		testCaseToRunPath = (new StringBuilder(String.valueOf(System.getProperty("user.dir")))).append("\\src\\main\\resources\\TestCasesToRun").toString();
		extentReportPath = (new StringBuilder(String.valueOf(System.getProperty("user.dir")))).append("\\ExtentReports\\TestResults.html").toString();
		log4jPropertiesFilePath = (new StringBuilder(String.valueOf(System.getProperty("user.dir")))).append("\\src\\test\\resources\\log4j.properties").toString();
		System.setProperty("logfilepath", (new StringBuilder(String.valueOf(System.getProperty("user.dir")))).append("\\src\\test\\resources").toString());
		System.setProperty("filename", "WOWLogs");
		PropertyConfigurator.configure(log4jPropertiesFilePath);
		System.out.println("extentReportPath===" + extentReportPath);
	}
	
}
