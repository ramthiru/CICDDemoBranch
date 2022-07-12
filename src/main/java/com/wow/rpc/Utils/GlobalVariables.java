package com.wow.rpc.Utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
//import com.wow.rpc.Reports.WowReport;

public class GlobalVariables extends Constants {
	public static DataUtilities envDataload = new DataUtilities();

	
	

	//public static WowReport reportThis = new WowReport();
	
	public static ExtentReports extentReport = new ExtentReports(extentReportPath, true);

	public static ExtentTest addReportstep;

	//public static Properties prop = new Properties();

	
	
protected String testName, testSetName;
	/*protected String browserName;
	protected  String environment;
	protected String pageTitle;*/

	protected long startTime;

	protected long endTime;

	protected long differenceTime;

	protected long differenceTimeSec;
	
	
    protected Properties objectProperties = new Properties();
	protected long differenceTimeMin;
	protected static String testCaseExecution;
	protected String duration;
	
	public static String getTestdata;
	public static String getORObj;
	protected  XMLReader testcases;

	protected TestCaseData testcase;

	protected BufferedReader br = null;

	protected  BufferedWriter createHeader = null;

	protected  BufferedWriter bf_OverAllReport = null;

	protected  Date currentDt = null;

	protected  DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

	protected  DateFormat date = new SimpleDateFormat("MM/dd/yyyy");

	protected  Date currrentDate = null;

	protected  int stepNumber = 1;

	protected  int passedCount = 0;

	protected  int failedCount = 0;

	protected  int infoCount = 0;

	protected  int warningCount = 0;

	protected  int addressCount = 0;

	protected int TestDataNumeric;

	protected int noOfRows = 0, noOfCols = 0, tdColId = 0, tdRowId = 0, tempRowId = 0, tempColId = 0;

	protected int arrayRowIndex, arrayColIndex;

	protected  String currentLine;

	protected Properties prop = new Properties();
	
	protected Properties prop1 = new Properties();
	
	protected  String htmlName;

	protected  String snapshotFolder;

	protected  String timeStamp;

	protected  String timeFormat;

	protected  String captureScreenshot;

	protected  String StrOverAllStatus = null;

	protected  String imagePath = " ";

	protected  String imageName = " ";

	protected  String fileName;

	protected  String reportPath;

	protected  String resultPath;

	protected  String snapshotPath;

	protected  String snapshotURL;

	protected  String snapshotFolderName;

	protected  String reportName;

	protected  String overAllReportPath;

	protected  String actualText;

	protected  String msaPageTitle;

	protected  String mkt = null;

	protected  String lng = null;

	protected  String headerText = null;

	
	protected String locator = null;

	protected String url;

	protected String TestData;

	protected String data;

	



	
	protected  boolean matchFound;

	protected List<WebElement> linkElements, allLinkElements;
	
	protected ResultSet rset =  null ;
    protected Statement stmt = null;
    protected  int PassTc = 0;
    protected  int FailTc = 0;
    

    
    protected  String[] splitMsge = null;
    protected  String[] querySplit = null;
   
    protected  String TransportCode;
    protected  String CarrierCode;
    
    protected  boolean flag = true;
    protected  boolean statusFlag = true;
    
    protected  String actualErrMsge=null;
    protected  String actualData=null;
    
    protected int latestRec = 0;
    protected String Reportname;
    
    protected  String inputData;
    
   
}
