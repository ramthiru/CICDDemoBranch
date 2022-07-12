package com.wow.rpc.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.wow.rpc.Reports.WowReport;
import com.wow.rpc.ReusableMethods.GenericMethods;
import com.wow.rpc.ReusableMethods.TestBase;

public class CreateAccountPage extends TestBase {

	
	GenericMethods objGenericMethods = new GenericMethods();
	WebDriver webdriver;
	String pageTitle ;
	WowReport reportThis; 
	
	@FindBy(xpath="//div[@class='header_user_info']/a")    
	private WebElement signIn;
	
	@FindBy(id="email_create")
	private WebElement createEmail;
	
	@FindBy(id="SubmitCreate")
	private WebElement CreateAnAccountbtn;
	
	@FindBy(id="uniform-id_gender1")
	private WebElement mr;
	
	@FindBy(id="uniform-id_gender2")
	private WebElement ms;
	
	@FindBy(id="customer_firstname")
	private WebElement custFirstName;
	
	@FindBy(id="customer_lastname")
	private WebElement custLastName;
	
	@FindBy(id="email")
	private WebElement custEmail;
	
	@FindBy(id="passwd")
	private WebElement passWd;
	
	@FindBy(id="days")
	private WebElement uniDay;
	
	@FindBy(id="months")
	private WebElement uniMonths;
	
	@FindBy(id="years")
	private WebElement uniYears;
	
	
	@FindBy(id="newsletter")
	private WebElement newLetter;
	
	@FindBy(id="uniform-optin")
	private WebElement specialOffer;
	
	@FindBy(id="firstname")
	private WebElement AdfirstName;
	
	
	@FindBy(id="lastname")
	private WebElement AdlastName;
	
	
	@FindBy(id="company")
	private WebElement AdcompanyName;
	
	@FindBy(id="address1")
	private WebElement AdFirstaddress1;
	
	@FindBy(id="address2")
	private WebElement AdFirstaddress2;
	
	@FindBy(id="city")
	private WebElement AdCity;
	
	@FindBy(id="id_state")
	private WebElement AdState;
	
	@FindBy(id="postcode")
	private WebElement Adpostcode;
	
	@FindBy(id="id_country")
	private WebElement AdCountry;

	@FindBy(id="other")
	private WebElement AdditinoalInfo;
	
	@FindBy(id="phone")
	private WebElement Adphone1;
	
	@FindBy(id="phone_mobile")
	private WebElement Adphone_mobile;
	
	@FindBy(id="alias")
	private WebElement Adalias;
	
	@FindBy(id="submitAccount")
	private WebElement AdregisterAcc;
	
	public CreateAccountPage(WebDriver driverPassed , String pageTitlePassed, WowReport reportThisPassed) {
		
		this.webdriver = driverPassed;
		this.pageTitle = pageTitlePassed;
		this.reportThis= reportThisPassed;
		PageFactory.initElements(webdriver,this);
	}
	
	public void navigateToSignInScreen() {
		
		try {
			objGenericMethods.inputForSendKeysAndClick(webdriver, reportThis,signIn, "", "click");
			reportThis.CustReport(webdriver, pageTitle,"Clicked on SignIn link", "Pass_ScreenShot");	
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void initiateCreateAccountByEmail(String userEmailVal) throws InterruptedException { 
		
		objGenericMethods.sendKeysAfterClear(createEmail, userEmailVal, getDriver());
		reportThis.CustReport(webdriver, pageTitle,"Entered the user email value", "Pass");
		
	}
	
		public void clickOnCreateAnAccountBtn() {
			
			try {
				objGenericMethods.inputForSendKeysAndClick(webdriver, reportThis,CreateAnAccountbtn, "", "click");
				reportThis.CustReport(webdriver, pageTitle,"Clicked on Create an account button", "Pass_ScreenShot");	
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	
		public void selectDayinDOB(String displayTypeValue) {
			//objGenericMethods.waitUntillClickableMethod(uniDay, webdriver);
			Select dropDown = new Select(uniDay);
			dropDown.selectByValue(displayTypeValue);
			reportThis.CustReport(webdriver, pageTitle,"Selected the date Value", "Pass");
		}
		
		public void selectMonthInDOB(String displayTypeValue) {
			//objGenericMethods.waitUntillClickableMethod(uniMonths,webdriver);
			Select dropDown = new Select(uniMonths);
			dropDown.selectByValue(displayTypeValue);
			reportThis.CustReport(webdriver, pageTitle,"Selected the month Value", "Pass");
		}
		
		public void selectyearInDOB(String displayTypeValue) {
			//objGenericMethods.waitUntillClickableMethod(uniYears,webdriver);
			Select dropDown = new Select(uniYears);
			dropDown.selectByValue(displayTypeValue);
			reportThis.CustReport(webdriver, pageTitle,"Selected the year Value", "Pass_ScreenShot");
		}
		
		public void selectState(String displayTypeValue) {
			//objGenericMethods.waitUntillClickableMethod(uniYears,webdriver);
			Select dropDown = new Select(AdState);
			dropDown.selectByVisibleText(displayTypeValue);
			reportThis.CustReport(webdriver, pageTitle,"Selected the State Value", "Pass_ScreenShot");
		}
		
		
		
	public void createNewAccount(String title,String firstname,String lastname,String userEmail,String password,String dateOfBirth,String adFristName,String adLastName,String adCompanyName,String adAddress1,String adAddress2,String adCity,String adState,String adZip, String adCountry,String adAdditionalInfo,String adHomePhone,String adMobilePhone,String adAddressAlias) { 
		
	
		
		WebElement titleName;
	try {
			
		if(title.equalsIgnoreCase("mr")){
			
			titleName = mr;
			
		} else { 
			
			titleName = ms;
		}
		 
		objGenericMethods.inputForSendKeysAndClick(webdriver, reportThis, titleName, "", "click");
		reportThis.CustReport(webdriver, pageTitle,"Title got selected", "Pass");
		
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	try { 
		
		objGenericMethods.sendKeysAfterClear(custFirstName, firstname, getDriver());
		reportThis.CustReport(webdriver, pageTitle,"Entered the firstname", "Pass");
		
		objGenericMethods.sendKeysAfterClear(custLastName, lastname, getDriver());
		reportThis.CustReport(webdriver, pageTitle,"Entered the lastname", "Pass");
		
		objGenericMethods.sendKeysAfterClear(custEmail, userEmail, getDriver());
		reportThis.CustReport(webdriver, pageTitle,"Entered the email id", "Pass");
		
		String emailId = objGenericMethods.getText(getDriver(), reportThis, custEmail);
		
		if(userEmail.equalsIgnoreCase(emailId)){
			
			reportThis.CustReport(webdriver, pageTitle,"Verified the email is correct", "Pass");
			
		}
		
		objGenericMethods.sendKeysAfterClear(passWd, password, getDriver());
		reportThis.CustReport(webdriver, pageTitle,"Entered the password", "Pass");
		
		System.out.println("Dob==" + dateOfBirth);
		String dob[] = dateOfBirth.split("-");
		
		System.out.println("Date==" + dob[0] + "Month== " +dob[1] +" year== " + dob[2] );
		selectDayinDOB(dob[0]);
		selectMonthInDOB(dob[1]);
		selectyearInDOB(dob[2]);
		
		objGenericMethods.inputForSendKeysAndClick(webdriver, reportThis, newLetter, "", "click");
		reportThis.CustReport(webdriver, pageTitle,"selected the newsletter", "Pass");
		
		objGenericMethods.inputForSendKeysAndClick(webdriver, reportThis, specialOffer, "", "click");
		reportThis.CustReport(webdriver, pageTitle,"Special offers got selected", "Pass");
		
		//	
		
		
		objGenericMethods.sendKeysAfterClear(AdfirstName, adFristName, getDriver());
		reportThis.CustReport(webdriver, pageTitle,"Entered the Firstname for Address", "Pass");
		
		objGenericMethods.sendKeysAfterClear(AdlastName, adLastName, getDriver());
		reportThis.CustReport(webdriver, pageTitle,"Entered the Lastname for Address", "Pass");
		
		objGenericMethods.sendKeysAfterClear(AdcompanyName, adCompanyName, getDriver());
		reportThis.CustReport(webdriver, pageTitle,"Entered the companyname", "Pass");
		
		objGenericMethods.sendKeysAfterClear(AdFirstaddress1, adAddress1, getDriver());
		reportThis.CustReport(webdriver, pageTitle,"Entered the Address1", "Pass");
		
		objGenericMethods.sendKeysAfterClear(AdFirstaddress2, adAddress2, getDriver());
		reportThis.CustReport(webdriver, pageTitle,"Entered the Address2", "Pass");
		
		objGenericMethods.sendKeysAfterClear(AdCity,adCity , getDriver());
		reportThis.CustReport(webdriver, pageTitle,"Entered the city", "Pass");
		
		
		selectState(adState);
		reportThis.CustReport(webdriver, pageTitle,"Entered the state", "Pass");
		
		objGenericMethods.sendKeysAfterClear(Adpostcode,adZip, getDriver());
		reportThis.CustReport(webdriver, pageTitle,"Entered the Zipcode ", "Pass");
		
		/*objGenericMethods.sendKeysAfterClear(AdCountry,adCountry, getDriver());
		reportThis.CustReport(webdriver, pageTitle,"Entered the Country", "Pass");*/
		
		objGenericMethods.sendKeysAfterClear(AdditinoalInfo,adAdditionalInfo, getDriver());
		reportThis.CustReport(webdriver, pageTitle,"Entered the AdditionalInformation", "Pass");
		
		objGenericMethods.sendKeysAfterClear(Adphone1,adHomePhone, getDriver());
		reportThis.CustReport(webdriver, pageTitle,"Entered the HomePhone", "Pass");
		
		objGenericMethods.sendKeysAfterClear(Adphone_mobile,adMobilePhone, getDriver());
		reportThis.CustReport(webdriver, pageTitle,"Entered the MobilePhone", "Pass");
		
		objGenericMethods.sendKeysAfterClear(Adalias, adAddressAlias, getDriver());
		reportThis.CustReport(webdriver, pageTitle,"Entered the Address alias for future reference", "Pass");
		
	
		
		objGenericMethods.inputForSendKeysAndClick(webdriver, reportThis, AdregisterAcc, "", "click");
		reportThis.CustReport(webdriver, pageTitle,"Clicked on Register button", "Pass");
		
		
		
	}catch(Exception e) {
		
		e.printStackTrace();
	}
		
		
	}
	
}
