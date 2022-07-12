package com.wow.rpc.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.EmptyStackException;

import org.apache.log4j.Logger;

//import com.wow.rpc.ReusableMethods.TestBase;

import jxl.Workbook;

public class DataUtilities extends GlobalVariables {

	private static Logger log = Logger.getLogger(DataUtilities.class);
	String[] sheetName;
	

	

	public void AddRunTimeData(String StrProperty, String StrPropertyValue) throws Exception {
		try {
			prop.setProperty(StrProperty, StrPropertyValue);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
	
	public String getTestdata(String PropName) throws Exception {
		try {
			getTestdata = prop.getProperty(PropName);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return getTestdata;
	}
	public String getObject(String ORName) throws Exception {
		try {
			getORObj = prop1.getProperty(ORName);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return getORObj;
	}

	// TO delete the TestCasesToRun file
	public void deleteFiles() {
		try {

			File TestCasesToRun = new File("src/main/resources/TestCasesToRun.txt");
			if (TestCasesToRun.exists())
				TestCasesToRun.delete();
			else
				log.info("File does not exist");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
		public void loadEnvProperties() throws Exception {
		InputStream inputEnv;
		
		System.out.println((new StringBuilder("Loading Data from Env.properties from the path:"))
				.append(envPropFilePath).toString());
		inputEnv = null;
		try {
			System.out.println("envPropFilePath from the load method==" + envPropFilePath);
			inputEnv = new FileInputStream(envPropFilePath);
			System.out.println("inputEnv===" + inputEnv);
			prop.load(inputEnv);

		} catch (IOException ex) {
			System.out.println((new StringBuilder("File is missing in the path:")).append(envPropFilePath).toString());
			ex.printStackTrace();
		} finally {
			if (inputEnv != null)
				try {
					inputEnv.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}

	}
	
		
		/*Upload the Object Repositories*/
		
		public void loadObjRepositories() throws Exception {
			InputStream objectEnv;
			
			System.out.println((new StringBuilder("Loading Data from OR from the path:"))
					.append(objectRepositoriesFilePath).toString());
			objectEnv = null;
			try {
				System.out.println("OR from the load method==" + objectRepositoriesFilePath);
				objectEnv = new FileInputStream(objectRepositoriesFilePath);
				System.out.println("inputEnv===" + objectEnv);
				prop1.load(objectEnv);

			} catch (IOException ex) {
				System.out.println((new StringBuilder("File is missing in the path:")).append(objectRepositoriesFilePath).toString());
				ex.printStackTrace();
			} finally {
				if (objectEnv != null)
					try {
						objectEnv.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
			}

		}
		
		
		
	public void loadTestScripts(String Testcase,String env) throws Exception {
		prop.setProperty("ContinueFlag", "Y");
		InputStream inputTestScr = null;
		try {
			System.out.println("Testcase Name from the XML===" + Testcase);
			sheetName = Testcase.split("_"); 
			
			
			System.out.println((new StringBuilder("Loading Data from TestData.xls from the path:")).append(testDataFilePath).toString());
			inputTestScr = new FileInputStream(testDataFilePath);
			Workbook TSworkbook = Workbook.getWorkbook(inputTestScr);
			boolean MatchFound = false;
			TSSheet = TSworkbook.getSheet(sheetName[0].toUpperCase());
			int TSColId = 0;
			int TSRowId = 0;
			String Temp_TestcaseName;
			for (int RowMaxVal = 0; RowMaxVal < 200; RowMaxVal++) {
				TSRowId++;
				 Temp_TestcaseName = TSSheet.getCell(0, TSRowId).getContents();
				Temp_TestcaseName.trim();
				Testcase.trim();
				if (Testcase.equals(Temp_TestcaseName)) {
					System.out.println("Matching Rows is ::" +TSRowId);
					MatchFound = true;
					RowMaxVal = 201;
				}
			}

			if (MatchFound) {
				System.out.println("Match Found in the DataSheet. Loading Data...");
			} else {
				System.out.println((new StringBuilder("Match not Found in the DataSheet for the Test case: "))
						.append(Testcase).toString());
				throw new EmptyStackException();
			}
			String TempPropName = TSSheet.getCell(TSColId, 0).getContents();
			String TempPropValue = TSSheet.getCell(TSColId, TSRowId).getContents();
			System.out.println("Columns count==" + TSSheet.getColumns());
			//while (TempPropName != "") {
			for(int k=1;k<=TSSheet.getColumns();k++){
				
				TempPropName = TSSheet.getCell(TSColId, 0).getContents();
				TempPropValue = TSSheet.getCell(TSColId, TSRowId).getContents();
				
				System.out.println(
						(new StringBuilder(String.valueOf(TempPropName))).append(":").append(TempPropValue).toString());
				prop.setProperty(TempPropName, TempPropValue);
				TSColId++;
			}
			TSworkbook.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (inputTestScr != null)
				try {
					inputTestScr.close();
				} catch (IOException e) {
					System.out.println((new StringBuilder(
							"EnvTestParams.xls is not loadded. Please make sure you have it in the path : "))
									.append(testDataFilePath).toString());
					e.printStackTrace();
				}
		}

	}

}
