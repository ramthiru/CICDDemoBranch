package com.wow.rpc.ReusableMethods;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.log4testng.Logger;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.wow.rpc.Reports.WowReport;
import com.wow.rpc.Utils.DataUtilities;

public class GenericMethods {

	Logger logger = Logger.getLogger(GenericMethods.class);
	String locVal, locObj, locType;
	String[] parts;

	public void waitForPageLoad(WebDriver driver) throws Exception {
		driver.manage().timeouts().pageLoadTimeout(300, TimeUnit.SECONDS);

	}

	/* Assertion */

	public void checkAssertText(WebDriver driver, WowReport reportThis,
			String actualText, String expectedText, String pageTitle,
			String statement) throws ElementNotFoundException {

		if (actualText.trim().equalsIgnoreCase(expectedText.trim())) {

			reportThis.CustReport(driver, pageTitle, statement,
					"Pass_ScreenShot");
		} else {
			reportThis.CustReport(driver, pageTitle, statement, "Fail");
			System.out.println("expectedText====" + expectedText
					+ "actualText====" + actualText);
		}

	}

	/*public void inputForSendKeysAndClick(WebDriver driver, WowReport reportThis,
			String locator, String senkeysVal, String cat) {

		WebElement loc = null;

		try {

			parts = locator.split("\\|");

			locObj = parts[0].trim();
			locType = parts[1].trim();
			locType = locType.toUpperCase().trim();

			switch (locType) {

				case "ID" :

					loc = driver.findElement(By.id(locObj));
					break;

				case "NAME" :

					loc = driver.findElement(By.name(locObj));
					break;

				case "CSSSELECTOR" :

					loc = driver.findElement(By.cssSelector(locObj));
					break;

				case "XPATH" :

					loc = driver.findElement(By.xpath(locObj));
					break;

				case "LINKTEXT" :

					loc = driver.findElement(By.linkText(locObj));
					break;

				case "PARTIALLINKTEXT" :

					loc = driver.findElement(By.partialLinkText(locObj));
					break;

				case "TAGNAME" :

					loc = driver.findElement(By.tagName(locObj));
					break;

				case "CLASSNAME" :

					loc = driver.findElement(By.className(locObj));
					break;
			}

			if (cat.equalsIgnoreCase("input")) {

				loc.clear();
				loc.sendKeys(senkeysVal);

			} else if (cat.equalsIgnoreCase("click")) {

				loc.click();
			} else {

				logger.info(
						"WebDriver is not able to recognise the  category you have been specified..!!!!");
			}

		}

		catch (ElementNotVisibleException e) {
			e.printStackTrace();
			reportThis.CustReport(driver, cat,
					"WebDriver is not able to recognise the  Locator", "Fail");

		}
	}*/

	public int getRandonmNuber(int min, int max) {

		Random r = new Random();
		int rno = r.nextInt(max - min) + min;
		return rno;

	}
	public int genRan() {
		Random r = new Random(System.currentTimeMillis());
		return ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
	}

	

	public String generateScheduleName(String scheduleName) {

		int num = genRan();
		String fileName = scheduleName + num;
		return fileName;
	}

	public void captureInputs(String objName) throws IOException {

		File myFile = new File(
				System.getProperty("user.dir")
						+ "\\src\\main\\resources\\retailExecutionModule\\",
				"input.txt");

		myFile.createNewFile();
		BufferedWriter output = new BufferedWriter(new FileWriter(myFile));
		output.write(objName);
		output.close();

	}

	public String readInputs() throws IOException {

		String str;
		FileReader fr = new FileReader(System.getProperty("user.dir")
				+ "\\src\\main\\resources\\retailExecutionModule\\input.txt");
		BufferedReader br = new BufferedReader(fr);
		while ((str = br.readLine()) != null) {
			return str;
		}
		br.close();
		return str;
	}
	public void verifyUIPresense(WebDriver driver, WowReport reportThis,
			String locator, String name, String pageTitle) {

		WebElement loc = null;
		try {
			parts = locator.split("\\|");
			locObj = parts[0];
			locType = parts[1];
			locType = locType.toUpperCase();

			switch (locType) {

				case "ID" :

					loc = driver.findElement(By.id(locObj));
					break;

				case "NAME" :

					loc = driver.findElement(By.name(locObj));
					break;

				case "CSSSELECTOR" :

					loc = driver.findElement(By.cssSelector(locObj));
					break;

				case "XPATH" :

					loc = driver.findElement(By.xpath(locObj));
					break;

				case "LINKTEXT" :

					loc = driver.findElement(By.linkText(locObj));
					break;

				case "PARTIALLINKTEXT" :

					loc = driver.findElement(By.partialLinkText(locObj));
					break;

				case "TAGNAME" :

					loc = driver.findElement(By.tagName(locObj));
					break;

				case "CLASSNAME" :

					loc = driver.findElement(By.className(locObj));
					break;

			}

			if (loc.isDisplayed()) {

				reportThis.CustReport(driver, pageTitle,
						"Verified the UI presence for " + name,
						"Pass_ScreenShot");
			} else {

				reportThis.CustReport(driver, pageTitle,
						"The given UI " + name + " is not getting displayed ",
						"Pass");
			}

		}

		catch (ElementNotVisibleException e) {

			reportThis.CustReport(driver, locObj,
					"WebDriver is not able to recognise the  Locator", "Fail");

		}

	}

	public String trimText(String txt) {

		return txt.trim();
	}

	public void selectDropDownText(WebDriver driver, WowReport reportThis,
			String locator, String inputToBeSelected, String category) {

		try {

			parts = locator.split("\\|");
			locObj = parts[0].trim();
			locVal = parts[1].trim();
			locVal = locVal.toUpperCase();
			WebElement Options = null;

			switch (locVal) {

				case "ID" :

					Options = driver.findElement(By.id(locObj));
					break;

				case "CSSSELECTOR" :

					Options = driver.findElement(By.cssSelector(locObj));
					break;

				case "XPATH" :

					Options = driver.findElement(By.xpath(locObj));
					break;

				case "LINKTEXT" :

					Options = driver.findElement(By.linkText(locObj));
					break;

				case "PARTIALLINKTEXT" :

					Options = driver.findElement(By.partialLinkText(locObj));
					break;

				case "TAGNAME" :

					Options = driver.findElement(By.tagName(locObj));
					break;

				case "CLASSNAME" :

					Options = driver.findElement(By.className(locObj));
					break;

				case "NAME" :

					Options = driver.findElement(By.name(locObj));
					break;
			}

			Select selObj = new Select(Options);
			if (category.equalsIgnoreCase("byText")) {

				selObj.selectByVisibleText(inputToBeSelected);

			} else if (category.equalsIgnoreCase("byValue")) {

				selObj.selectByValue(inputToBeSelected);

			} else if (category.equalsIgnoreCase("byIndex")) {

				selObj.deselectByIndex(Integer.parseInt(inputToBeSelected));

			} else if (category.equalsIgnoreCase("ByVisibleText")) {

				selObj.selectByVisibleText(inputToBeSelected);

			} else {

				System.out.println("Category is mismatching.. !!");

			}
		} catch (Exception e) {
			e.printStackTrace();
			reportThis.CustReport(driver, locVal,
					"WebDriver is not able to recognise the  Locator", "Fail");

		}

	}

	

	public void viewToELement1(WebDriver driver, WowReport reportThis,WebElement locator) {

		//WebElement listofrows = null;

		try {
		

			((JavascriptExecutor) driver).executeScript(
					"arguments[0].scrollIntoView(true);", locator);

		}

		catch (ElementNotVisibleException e) {

			reportThis.CustReport(driver, locObj,
					"WebDriver is not able to recognise the  Locator", "Fail");

		}

	}

	
	
	/*


	 * =================================Verify the Auto suggestedValues===================================
	 */

	public void verifyAutoSuggestion(WebDriver driver, WowReport reportThis,WebElement element, String expValue, String pageTitle, String UiName) {

		WebElement loc = element;
		try {
		
			

			List<WebElement> allOptions = loc
					.findElements(By.tagName("option"));

			for (int i = 0; i < allOptions.size(); i++) {

				String optionValue = allOptions.get(i).getText().trim();

				if (!optionValue.trim().contains(expValue)) {

					reportThis.CustReport(driver, pageTitle,
							"Auto Suggested is Invalid", "Pass");

				}
				{

					reportThis
							.CustReport(driver,
									pageTitle, "Auto Suggestion Value :"
											+ optionValue + " is Verified",
									"Pass");
				}
			}

			reportThis.CustReport(driver, pageTitle,
					"Verified the auto suggestion for the given input "
							+ UiName,
					"Pass");

		}

		catch (ElementNotVisibleException e) {

			reportThis.CustReport(driver, locObj,
					"WebDriver is not able to recognise the  Locator", "Fail");

		}

	}

	

	public void waitForElementLoad(WebDriver webDriver, WowReport reportThis,WebElement locator) {

		try {
			

			WebDriverWait wait = new WebDriverWait(webDriver, 120);
			wait.until(ExpectedConditions.elementToBeClickable(locator));
					}

		catch (ElementNotVisibleException e) {

			reportThis.CustReport(webDriver, "WaitForElement","WebDriver is not able to recognise the  Locator", "Fail");

		}
	}

	public void verifyUiVisibilityStatus(WebDriver driver, WowReport reportThis,
			String locator, String name, String pageTitle) {

		WebElement loc = null;
		try {
			parts = locator.split("\\|");
			locObj = parts[0];
			locType = parts[1];
			locType = locType.toUpperCase();

			switch (locType) {

				case "ID" :

					loc = driver.findElement(By.id(locObj));
					break;

				case "NAME" :

					loc = driver.findElement(By.name(locObj));
					break;

				case "CSSSELECTOR" :

					loc = driver.findElement(By.cssSelector(locObj));
					break;

				case "XPATH" :

					loc = driver.findElement(By.xpath(locObj));
					break;

				case "LINKTEXT" :

					loc = driver.findElement(By.linkText(locObj));
					break;

				case "PARTIALLINKTEXT" :

					loc = driver.findElement(By.partialLinkText(locObj));
					break;

				case "TAGNAME" :

					loc = driver.findElement(By.tagName(locObj));
					break;

				case "CLASSNAME" :

					loc = driver.findElement(By.className(locObj));
					break;

			}

			if (loc.isEnabled()) {

				reportThis.CustReport(driver, pageTitle,
						"Verified the UI " + name + " is Enabled",
						"Pass_ScreenShot");
			} else {

				reportThis.CustReport(driver, pageTitle,
						"Verified the UI " + name + " is Disabled",
						"Pass_ScreenShot");

			}

		}

		catch (ElementNotVisibleException e) {

			reportThis.CustReport(driver, locObj,
					"WebDriver is not able to recognise the  Locator", "Fail");

		}

	}

	public boolean getUIPresenseFlag(WebDriver driver, String locator,
			WowReport reportThis, String pageTitle) {

		List<WebElement> loc = null;
		boolean flag = false;
		try {
			parts = locator.split("\\|");
			locObj = parts[0];
			locType = parts[1];
			locType = locType.toUpperCase();

			switch (locType) {

				case "ID" :

					loc = driver.findElements(By.id(locObj));
					break;

				case "NAME" :

					loc = driver.findElements(By.name(locObj));
					break;

				case "CSSSELECTOR" :

					loc = driver.findElements(By.cssSelector(locObj));
					break;

				case "XPATH" :

					loc = driver.findElements(By.xpath(locObj));
					break;

				case "LINKTEXT" :

					loc = driver.findElements(By.linkText(locObj));
					break;

				case "PARTIALLINKTEXT" :

					loc = driver.findElements(By.partialLinkText(locObj));
					break;

				case "TAGNAME" :

					loc = driver.findElements(By.tagName(locObj));
					break;

				case "CLASSNAME" :

					loc = driver.findElements(By.className(locObj));
					break;

			}

			if (loc.size() != 0) {

				flag = true;

			} else {

				flag = false;

			}

		}

		catch (ElementNotVisibleException e) {

			reportThis.CustReport(driver, locObj,
					"WebDriver is not able to recognise the  Locator", "Fail");

		}
		return flag;
	}

	public void waitUntillVisibleMethod(WebElement elementToCheck,
			WebDriver webdriver) {
		WebDriverWait wait = new WebDriverWait(webdriver, 120);
		wait.until(ExpectedConditions.visibilityOf(elementToCheck));
	}

	public void waitUntillClickableMethod(WebElement elementToCheck,WebDriver webdriver) {
		WebDriverWait wait = new WebDriverWait(webdriver, 120);
		wait.until(ExpectedConditions.elementToBeClickable(elementToCheck));
	}

	public void moveToWebElement(WebElement elementToSearch,
			WebDriver webdriver) {
		((JavascriptExecutor) webdriver).executeScript(
				"arguments[0].scrollIntoView(true);", elementToSearch);
	}

	public boolean verifyListTextWithOneTextValue(String ValueToMatch,
			List<WebElement> searchedResltsDcElementList, String pageTitle,
			WowReport reportThis, WebDriver driver) {
		boolean listVerifyFlag = false;
		reportThis.CustReport(driver, pageTitle,
				"Number of list elements to be verified : "
						+ searchedResltsDcElementList.size(),
				"Pass");
		for (WebElement elementToVerify : searchedResltsDcElementList) {
			System.out.println(elementToVerify.getText());
			if (elementToVerify.getText().trim()
					.equalsIgnoreCase(ValueToMatch)) {
				listVerifyFlag = true;
			} else {
				listVerifyFlag = false;
			}
		}
		if (listVerifyFlag) {
			reportThis.CustReport(driver, pageTitle,
					"All list webelements text - matched - with the value passed to verify",
					"Pass");
		} else
			reportThis.CustReport(driver, pageTitle,
					"All list webelements text - not matched - with the value passed to verify",
					"Fail");
		return listVerifyFlag;
	}

	
	
	
	public boolean verifyListOfDropdownTextWithOneTextValue(String ValueToMatch,
			List<WebElement> searchedResltsDcElementList, WowReport reportThis,
			String pageTitle, WebDriver driver) {
		boolean listVerifyFlag = false;
		reportThis.CustReport(driver, pageTitle,
				"Number of list elements to be verified : "
						+ searchedResltsDcElementList.size(),
				"Pass");
		for (WebElement elementToVerify : searchedResltsDcElementList) {
			Select dropDown = new Select(elementToVerify);
			WebElement option = dropDown.getFirstSelectedOption();
			if (option.getText().trim().equalsIgnoreCase(ValueToMatch)) {
				listVerifyFlag = true;
			} else {
				listVerifyFlag = false;
			}
		}
		if (listVerifyFlag) {
			reportThis.CustReport(driver, pageTitle,
					"All list webelements text - matched - with the value passed to verify",
					"Pass");
		} else
			reportThis.CustReport(driver, pageTitle,
					"All list webelements text - not matched - with the value passed to verify",
					"FAil");
		return listVerifyFlag;
	}

	public void scrollToWebElement(WebDriver driver,
			WebElement webelementPassed) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);",
				webelementPassed);
	}

	public boolean searchTextValueInDropDown(WebDriver driver,
			WebElement webElementPassed, String valueToSearchInDropDwn,
			WowReport reportThis, String pageTitle) {
		boolean dropDwnValueVerifyFlag = false;
		Select dropDown = new Select(webElementPassed);
		for (WebElement elementToVerify : dropDown.getOptions()) {
			System.out.println(
					"Text of Elemnt to verify : " + elementToVerify.getText());
			if (elementToVerify.getText().trim()
					.equalsIgnoreCase(valueToSearchInDropDwn)) {
				dropDwnValueVerifyFlag = true;
			} else {
				dropDwnValueVerifyFlag = false;
			}
		}
		if (dropDwnValueVerifyFlag) {
			reportThis.CustReport(driver,
					pageTitle, "Searched dropDown value : "
							+ valueToSearchInDropDwn + " present in DropDown",
					"Pass");
		}
		return dropDwnValueVerifyFlag;
	}

	public void rcLogOut(WebDriver driver, String pageTitle,
			WowReport reportThis) throws InterruptedException {
		Thread.sleep(2000);
		reportThis.CustReport(driver, pageTitle, " Logging out of application",
				"Pass");
		WebElement logoutLink = driver.findElement(By.linkText("Logout"));
		scrollToWebElement(driver, logoutLink);
		waitUntillClickableMethod(logoutLink, driver);
		logoutLink.click();
		Thread.sleep(1000);
		reportThis.CustReport(driver, pageTitle,
				" Logout Button clicked successfully", "Pass_ScreenShot");
		Thread.sleep(1500);
	}

	public void sendKeysWithTabPress(WebElement webElementPassed,
			String sendKeysValue, WebDriver driver)
					throws InterruptedException, AWTException {
		waitUntillClickableMethod(webElementPassed, driver);
		webElementPassed.clear();
		webElementPassed.sendKeys(sendKeysValue);
		Thread.sleep(1000);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_TAB);
	}

	public void selectDropDownByVisibleText(WebElement dropDownWebElement,
			String ValueToSelect, WebDriver driver) {
		waitUntillClickableMethod(dropDownWebElement, driver);
		Select dropDown = new Select(dropDownWebElement);
		dropDown.selectByVisibleText(ValueToSelect);
	}

	public void scrollToWebElmentAndClick(WebDriver driver,
			WebElement webELement) throws InterruptedException {
		scrollToWebElement(driver, webELement);
		Thread.sleep(1000);
		webELement.click();
	}

	public void sendKeysAfterClear(WebElement webElementPassed,
			String sendKeysValue, WebDriver driver)
					throws InterruptedException {
		waitUntillClickableMethod(webElementPassed, driver);
		webElementPassed.clear();
		webElementPassed.sendKeys(sendKeysValue);
		Thread.sleep(1000);

	}

	public String getCurrentDatePlusNumOfDays(int numOfDaysAfterCurrentDate) {
		Date dt = new Date();
		System.out.println(dt);
		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		c.add(Calendar.DATE, numOfDaysAfterCurrentDate);
		dt = c.getTime();
		System.out.println(dt);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String formattedDate = formatter.format(dt);
		System.out.println("Formated date:  " + formattedDate);
		return formattedDate;
	}

	public void tabKeyPress() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
	}
	public boolean checkAssertTextValue(WebDriver driver, String actualText,
			String expectedText, String pageTitle, String passStatement,
			String failStatement, WowReport reportThis)
					throws ElementNotFoundException {
		reportThis.CustReport(
				driver, pageTitle, "For verification - expected value: "
						+ expectedText + " & actual value: " + actualText,
				"Pass");

		if (actualText.trim().equalsIgnoreCase(expectedText.trim())) {
			reportThis.CustReport(driver, pageTitle,
					"Actual And expected values for verification are matching",
					"Pass_ScreenShot");
			reportThis.CustReport(driver, pageTitle, passStatement, "Pass");
			return true;

		} else {
			reportThis.CustReport(driver, pageTitle, failStatement, "Fail");
			System.out.println(failStatement + " expectedText==== "
					+ expectedText + " actualText==== " + actualText);
			return false;
		}

	}


	
	
	
	
	 public void verifyList(WebDriver driver,WowReport reportThis, String output, String locator, String dropdownname) {
			String[] val = dropdownname.split("\\|");
			try {

				String[] expected = output.split("\\|");

				WebElement loc = null;

				parts = locator.split("\\|");
				locObj = parts[0];
				locType = parts[1];
				locType = locType.toUpperCase();

				switch (locType) {

				case "ID":

					loc = driver.findElement(By.id(locObj));
					break;

				case "NAME":

					loc = driver.findElement(By.name(locObj));
					break;

				case "CSSSELECTOR":

					loc = driver.findElement(By.cssSelector(locObj));
					break;

				case "XPATH":

					loc = driver.findElement(By.xpath(locObj));
					break;

				case "LINKTEXT":

					loc = driver.findElement(By.linkText(locObj));
					break;

				case "PARTIALLINKTEXT":

					loc = driver.findElement(By.partialLinkText(locObj));
					break;

				case "TAGNAME":

					loc = driver.findElement(By.tagName(locObj));
					break;

				case "CLASSNAME":

					loc = driver.findElement(By.className(locObj));
					break;

				}

				List<WebElement> allOptions = loc.findElements(By.tagName("option"));
				System.out.println("all Options==" + allOptions.size());

				if (expected.length == allOptions.size()) {

					reportThis.CustReport(driver,val[1] + " Configurator", "Size of " + val[0] + " dropdown is verified", "Pass");
				}

				for (int i = 0; i < expected.length; i++) {

					String optionValue = allOptions.get(i).getText().trim();

					if (optionValue.trim().equalsIgnoreCase(expected[i].trim())) {

						reportThis.CustReport(driver,val[1] + " Configurator",
								"The Option " + optionValue + " in the " + val[0] + " dropdown has been verified", "Pass");

					} else {

						System.out.println("failed on: " + optionValue);
						System.out.println(optionValue);
						System.out.println("expected== " + expected[i].trim());
					}
				}

			} catch (Exception e) {

				reportThis.CustReport(driver,val[0], "not able to list the options in the dropdown", "Fail");
			}

		}

	   
public List<String> getAllOptions(WebElement dropdown) {
    List<String> options = new ArrayList<String>();
    for (WebElement option : new Select(dropdown).getOptions()) {
      //  String txt = option.getText() + "\n";
        if (option.getAttribute("value") != "") options.add(option.getText() + " \n ");
    }
    return options;
} 


public boolean verifyDropDownContainsListOfValues(WebDriver driver, WebElement dropDown, List<String> listOfValuesToSearch , WowReport reportThis , String pageTitle) {
	
	String dropDownValues = dropDown.getText();
	reportThis.CustReport(driver,pageTitle, "Verifying Dropdown values present in dropdown displayed as expected ", "Pass");
	boolean allValuesPresent = false;
	
	reportThis.CustReport(driver,pageTitle, "Drop Down values to verify :  " + listOfValuesToSearch , "Pass");
	
	for (String valueToVerify : listOfValuesToSearch) {
		if (dropDownValues.contains(valueToVerify)) {
			reportThis.CustReport(driver,pageTitle, "Value : " + valueToVerify + " is present in dropdown", "Pass");
			allValuesPresent = true;
		} else {
			reportThis.CustReport(driver,pageTitle, "Value : " + valueToVerify + " is not present in dropdown", "Fail");
			allValuesPresent = false;
		}
	}
	reportThis.CustReport(driver,pageTitle, "All Drop Down values displayed as:  " + dropDownValues , "Pass");
	return allValuesPresent;
}

	@SuppressWarnings("unchecked")
	public static List<String> splitAndReturnCommaSeparatedStringToListOfString(String stringToSplit) {

		@SuppressWarnings("rawtypes")
		List<String> aList = new ArrayList(Arrays.asList(stringToSplit.split(",")));
		for (int i = 0; i < aList.size(); i++) {
			System.out.println(" -->" + aList.get(i));
		}
		for (String stringtoVerify : aList) {
			System.out.println(" list  --> " + stringtoVerify);
		}
		return aList;
	}


public boolean verifyListTextWithOneTextValue(WebDriver webDriver, WowReport reportThis, DataUtilities envDataload, String ValueToMatch, List<WebElement> searchedResltsDcElementList, String pageTitle) {
	boolean listVerifyFlag = false;
reportThis.CustReport(webDriver,pageTitle,
			"Number of list elements to be verified : " + searchedResltsDcElementList.size(), "Pass");
	for (WebElement elementToVerify : searchedResltsDcElementList) {
		System.out.println(elementToVerify.getText());
		if (elementToVerify.getText().trim().equalsIgnoreCase(ValueToMatch)) {
			listVerifyFlag = true;
		} else {
			listVerifyFlag = false;
		}
	}
	if (listVerifyFlag) {
		 reportThis.CustReport(webDriver, pageTitle,"All list webelements text - matched - with the value passed to verify",
				"Pass");
	} else
		 reportThis.CustReport(webDriver, pageTitle,
				"All list webelements text - not matched - with the value passed to verify", "Fail");
	return listVerifyFlag;
}




//////////////

public boolean checkAssertTextWithScreenshot(WebDriver webDriver, WowReport reportThis, String actualText, String expectedText,
		String pageTitle, String statement) throws ElementNotFoundException {

	if (actualText.trim().equalsIgnoreCase(expectedText.trim())) {
		reportThis.CustReport(webDriver,pageTitle, statement, "Pass_ScreenShot");
		return true;

	} else {
		reportThis.CustReport(webDriver,pageTitle, statement, "Fail");
		System.out.println(" expectedText==== " + expectedText + " actualText==== " + actualText);
		return false;
	}

}

public void inputForSendKeysAndClick(WebDriver driver, WowReport reportThis, WebElement mainMenu,String senkeysVal, String cat) throws InterruptedException   {
	
    
    try {
    	
    	Thread.sleep(3000);
    	
        if (cat.equalsIgnoreCase("input")) {
        	mainMenu.clear();
        	mainMenu.sendKeys(senkeysVal);
        	 
        } else if (cat.equalsIgnoreCase("click")) {
        //	moveToWebElement(mainMenu, driver);
        	mainMenu.click();
        } else {
            logger.info("WebDriver is not able to recognise the locator given specified..!!!!");
        }
    }

    catch (ElementNotVisibleException e) {
    	e.printStackTrace();
        reportThis.CustReport(driver,cat, "WebDriver is not able to recognise the  Locator", "Fail");   
    }
}

public void viewToELement(WebDriver driver,WowReport reportThis,WebElement mainMenu)
{ 
	  
	   
    try {
    
  	  ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", mainMenu); 
        
    }

    catch (ElementNotVisibleException e) {
        reportThis.CustReport(driver,locObj, "WebDriver is not able to recognise the  Locator", "Fail");
    }
}

public void selectDropDownText(WebDriver driver, WowReport reportThis, WebElement  locator, String inputToBeSelected,String category){
	  
	  try {
		         
       	Select selObj = new Select(locator);
		  if(category.equalsIgnoreCase("byText")){
			 
			  selObj.selectByVisibleText(inputToBeSelected);
			  
		  } else if(category.equalsIgnoreCase("byValue")){
			 
			  selObj.selectByValue(inputToBeSelected);
			  
		  } else if(category.equalsIgnoreCase("byIndex")) {
			  
			  selObj.selectByIndex(Integer.parseInt(inputToBeSelected));
			  
		  }  else {
			  
			  System.out.println("Category is mismatching.. !!");
			  
		  }
	  }
	  catch(Exception e) {
		e.printStackTrace();
		  reportThis.CustReport(driver,locVal, "WebDriver is not able to recognise the  Locator", "Fail" );   
        
		  
	  }
	    
}
 
  

public String getFirstSelectedOptionText(WebDriver driver, WowReport reportThis, WebElement locator) throws Exception {

    String getText = null;
    try { 
    	
  
   	 getText = new Select(locator).getFirstSelectedOption().getText();
   	 
   	      
    }
    catch(Exception e) { 
      	
      	 reportThis.CustReport(driver, "getFirstSelectedOption", "Unable to find out the given UI element in this page", "Fail");   
           
      }
    return getText.trim();
}

public String getText(WebDriver driver,WowReport reportThis, WebElement createHeaderName) throws Exception{

    String text = null;
    try { 
    	
    	text = createHeaderName.getText().trim();
    
    }
    catch(Exception e) { 
      	
    	 reportThis.CustReport(driver, "getText", "Unable to find out the given UI element in this page", "Fail");   
         
    }
   
    return text;
}

public String getAttribute(WebDriver driver, WowReport reportThis,WebElement locator,String attVal) throws Exception {

	String text = null;
	
	try {

	text = locator.getAttribute(attVal);
		

	} catch (Exception e) {

		reportThis.CustReport(driver, "getAttribute","Unable to find out the given UI element in this page", "Fail");

	}

	return text.trim();
}


public void moveTo(WebDriver driver,WebElement locator,WowReport reportThis) throws Exception {

	
	
	try {

		Coordinates coordinate = ((Locatable)locator).getCoordinates(); 
		coordinate.onPage(); 
		coordinate.inViewPort();
		

	} catch (Exception e) {

		reportThis.CustReport(driver, "MoveTo","Unable to find out the given UI element in this page", "Fail");

	}

	
	
}


}
