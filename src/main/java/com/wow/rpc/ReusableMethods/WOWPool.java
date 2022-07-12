package com.wow.rpc.ReusableMethods;



import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.wow.rpc.Reports.WowReport;
import com.wow.rpc.Utils.DataUtilities;
import com.wow.rpc.Utils.GlobalVariables;


public class WOWPool extends GlobalVariables {
	
	
	protected static Logger log = Logger.getLogger(WOWPool.class);
	boolean mstatus = false;
	


    public void autLogin(WebDriver webDriver, String environment, WowReport reportThis,DataUtilities envDataload,String userName,String passWord) throws Exception {

        try {
            
        	System.out.println("driver==" +webDriver + " environment==" +environment);
        	
                if (environment.equalsIgnoreCase("SIT")) {
                	
                   webDriver.get(envDataload.getTestdata("AUTURL"));
                    reportThis.CustReport(webDriver,"Login Page", "Navigated to Url : " + envDataload.getTestdata("AUTURL"), "Pass");
                    webDriver.manage().window().maximize();
                    Thread.sleep(9000);
                    
                  /*  WebElement username = webDriver.findElement(By.id(envDataload.getObject("uname")));
                    username.clear();
                    Thread.sleep(5000);
                    System.out.println("Username after clean=="+userName);
                    username.sendKeys(userName);
                    reportThis.CustReport(webDriver, "Login", "Entered UserName", "Pass");
                    
                    
                    webDriver.findElement(By.cssSelector(envDataload.getObject("pass"))).sendKeys(passWord);
                    reportThis.CustReport(webDriver,"Login", "Entered Password", "Pass");
                    Thread.sleep(2000);*/
       			                     
              } else  if (environment.equalsIgnoreCase("UAT")) {
            	
                webDriver.get(envDataload.getTestdata("UATURL"));
            	reportThis.CustReport(webDriver,"Login Page", "Navigated to Url : " + envDataload.getTestdata("UATURL"), "Pass");
                
                webDriver.manage().window().maximize();
                WebElement username = webDriver.findElement(By.id(envDataload.getObject("uname")));
                username.clear();
                username.sendKeys(envDataload.getTestdata("UserName"));
                reportThis.CustReport(webDriver, "System Login", "Entered UserName", "Pass");
                 WebElement password = webDriver.findElement(By.id(envDataload.getObject("pass")));
                
               // String decodedpwd = getDecodedpassword(envDataload.getTestdata("PassWord"));
                 password.sendKeys(envDataload.getTestdata("PassWord"));
                reportThis.CustReport(webDriver," System Login", "Entered Password", "Pass");
             } 
            /* Thread.sleep(2000);
			 webDriver.findElement(By.id(envDataload.getObject("loginBtn"))).click();
            Thread.sleep(2000);*/

        }

        catch (Exception e) {
        	e.printStackTrace();
            reportThis.CustReport(webDriver,"AUT Login ", "Username/Password is incorrect.Login failed", "Fail");
        }

       
    }

 
     
 }
