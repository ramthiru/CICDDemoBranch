package com.wow.rpc.TestCases;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.wow.rpc.Reports.WowReport;
import com.wow.rpc.ReusableMethods.GenericMethods;
import com.wow.rpc.ReusableMethods.TestBase;
import com.wow.rpc.ReusableMethods.WOWPool;
import com.wow.rpc.Utils.DataUtilities;
import com.wow.rpc.pages.CreateAccountPage;

public class CreateAccountTest extends TestBase{
	
	Logger log = org.testng.log4testng.Logger.getLogger(CreateAccountTest.class);

	
	@Test
	public void shop_createUserAccount() throws Exception {
		String pageTitle = "Create new user account";
		 			
		 long startTime;

		 long endTime;

		 long differenceTime;

		 long differenceTimeSecs;
		
		 long differenceTimeMin;
		
		 String duration;
		
				startTime = System.currentTimeMillis();
				WOWPool wowObj =  new WOWPool();
				DataUtilities envDataload = new DataUtilities();
				WowReport reportThis =  new WowReport();
				GenericMethods reObject  =  new GenericMethods();
				String curExeMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
				String environment = getEnvironment();
				String browserName = getBrowserName();
				String testSetname = getTestSetName();
				
			try {
				
				reportThis.createReport(curExeMethodName, browserName, testSetname);
			    envDataload.loadEnvProperties();
			    envDataload.loadObjRepositories();
			    envDataload.loadTestScripts(curExeMethodName,environment);
			    wowObj.autLogin(getDriver(),environment,reportThis,envDataload,envDataload.getTestdata("UserName"),envDataload.getTestdata("PassWord"));
			    reObject.waitForPageLoad(getDriver());
			    System.out.println("test");
			    


			    CreateAccountPage creAccObj = new CreateAccountPage(getDriver(), pageTitle, reportThis);
			    creAccObj.navigateToSignInScreen();
			    creAccObj.initiateCreateAccountByEmail(envDataload.getTestdata("Email"));
			    creAccObj.clickOnCreateAnAccountBtn();
			    creAccObj.createNewAccount(envDataload.getTestdata("Ptitle"),envDataload.getTestdata("PFirstName"),envDataload.getTestdata("PLastName"),envDataload.getTestdata("Email"),envDataload.getTestdata("Ppwd"),envDataload.getTestdata("DOB"),envDataload.getTestdata("AdFirstName"),envDataload.getTestdata("AdLastName"),envDataload.getTestdata("AdCompanyName"),envDataload.getTestdata("AdAddress1"),envDataload.getTestdata("AdAddress2"),envDataload.getTestdata("AdCity"),envDataload.getTestdata("AdState"),envDataload.getTestdata("AdZip"),envDataload.getTestdata("AdCountry"),envDataload.getTestdata("AdAdditionalInfo"),envDataload.getTestdata("AdHomePhone"),envDataload.getTestdata("AdMobilePhone"),envDataload.getTestdata("AdaddressAlias"));
			    

		}
		catch(Exception e) { 
			
			e.printStackTrace();
			reportThis.CustReport(getDriver(),pageTitle, pageTitle + " Execution interrupted by Some issues", "Fail");
	        
		}
		finally {
			
			reportThis.CustReportclose(false, "");
			reportThis.overAllFooter();
			endTime = System.currentTimeMillis();
			differenceTime = endTime - startTime;
			differenceTimeSecs = TimeUnit.MILLISECONDS.toSeconds(differenceTime);
			differenceTimeMin = differenceTimeSecs / 60;
			log.info("Diff of time in min" + differenceTimeMin);
			duration = String.valueOf(differenceTimeMin);
			String exeStatus =  reportThis.getOvellAllReportStatus();
			String testName = envDataload.getTestdata("TestCaseDesc");
			reportThis.overallStatusReport(testName, duration,exeStatus , browserName, environment);
		}
	}

	
}
