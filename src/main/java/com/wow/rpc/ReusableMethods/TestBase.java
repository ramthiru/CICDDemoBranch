package com.wow.rpc.ReusableMethods;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.UnexpectedException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.log4testng.Logger;

//import com.wow.rpc.Reports.Mail;
import com.wow.rpc.Reports.WowReport;

import io.github.bonigarcia.wdm.WebDriverManager;


public class TestBase {


	public static ThreadLocal<RemoteWebDriver> webDriver = new ThreadLocal<RemoteWebDriver>();
	protected Logger log = org.testng.log4testng.Logger.getLogger(TestBase.class);
    private String browserName;
    private String environment;
    private String testSetName;
  
    public WebDriver getDriver() {
        return webDriver.get();
    }

    @BeforeSuite
   	public void beforeSuite() {
   		log.info("Inside @BeforeSuite");
   		deleteFiles();
   		
   	}
 
   // @Parameters({ "browser", "ENV" })
    @Parameters({"ENV" })
    @BeforeTest
	public void beforeTest(String ENV, ITestContext testContext) {
		log.info("Inside @BeforeTest");
		//this.browserName = browser;
		this.environment = ENV;
		this.testSetName = testContext.getName();

    }
    @Parameters("Browser")
    @BeforeMethod
    protected void createDriver(String Browser)
            throws MalformedURLException, UnexpectedException {
    	this.browserName = Browser;
        if(browserName.equalsIgnoreCase("GRID")) { 
        	
        
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
		capabilities.setJavascriptEnabled(true);
		capabilities.setCapability("platform", "LINUX");
	
		//webDriver.set(new RemoteWebDriver(new URL("http://21.01.12.989:78787/wd/hub"), capabilities));
        } else if(browserName.equalsIgnoreCase("chrome")) { 
        	
        	System.out.println("Dirver path==" +System.getProperty("user.dir"));
    	String chDriverPath = System.getProperty("user.dir") + "\\src\\test\\resources\\retailsDrivers\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chDriverPath);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("useAutomationExtension", false);
		options.setExperimentalOption("excludeSwitches",Collections.singletonList("enable-automation"));    
		
		options.addArguments("--start-maximized");
		options.addArguments("--disable-web-security");
		options.addArguments("--no-proxy-server");

		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);

		options.setExperimentalOption("prefs", prefs);
		
		webDriver.set(new ChromeDriver(options));
        
        } else if(browserName.equalsIgnoreCase("firefox")) {
        	
        WebDriverManager.firefoxdriver().setup();
        webDriver.set(new FirefoxDriver());
       
        } else {
        	
        	System.out.println("Browser object not instantiated ..!!");
        }

    }

   
    @AfterMethod
    public void tearDown() throws Exception {
    	
    	try {
	
    	//getDriver().quit();

		} catch (Exception e) {
			e.printStackTrace();
		}
        
    }

    @AfterSuite
    
    public void publishReport() throws Exception {
        	WowReport reportThis =  new WowReport();	
        	try {
    	int passTC,failTC;
    	passTC = reportThis.getPassCount();
    	failTC = reportThis.getFailCount();
    	System.out.println("passTC==" +passTC + "failTC " + failTC);
        		 /*Mail ma =  new Mail();
        		 
                 ma.MailScheduler(passTC,failTC);*/

    		} catch (Exception e) {
    			e.printStackTrace();
    		}
            
        }


	public String getBrowserName() {
		return browserName;
	}
	
	public String getEnvironment(){
		return environment;
	}
	
	public String getTestSetName(){
		return testSetName;
	}
	public void deleteFiles(){
		
		 try {
	        	        
	         File fl = new File((new StringBuilder(String.valueOf(System.getProperty("user.dir")))).append("\\CustReport").toString());
	         if(!fl.exists()) // checking if directory exists
	         {
	            System.out.println("Sorry!! directory doesn't exist.");
	         }
	         else
	         {
	           deleteDirectory(fl);
	         }
	         
	       

	         //file1.delete();
	 } catch (Exception exception) {
	         exception.printStackTrace();
	 }
		
		
		
	}
	public static void deleteDirectory(File file) throws IOException
	{
	   if(file.isDirectory())
	   {
	      if(file.list().length == 0)
	      { 
	         deleteEmptyDirectory(file); 
	      }
	      else
	      {
	         File fe[] = file.listFiles();
	         for(File deleteFile : fe)
	         {
	            deleteDirectory(deleteFile); 
	         }
	         if(file.list().length == 0)
	         {
	            deleteEmptyDirectory(file);
	         }
	      }
	   }
	   else
	   {
	      file.delete();
	  
	   }
	}

	private static void deleteEmptyDirectory(File fi)
	{
	   fi.delete();
	 
	}
	         


    
}
