package com.wow.rpc.TestCases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;



public class DataSheet {
	
	public static void main(String args[]) {
	try {
		
		String path = "D:\\File\\221.xls";
		
		FileInputStream file = new FileInputStream(new File(path));

		@SuppressWarnings("resource")
		HSSFWorkbook workbook = new HSSFWorkbook(file);
		HSSFSheet sheet = workbook.getSheetAt(0);
		Cell cell = null;

		//Update the value of cell
		cell = sheet.getRow(1).getCell(5);
		cell.setCellValue("225");
		
		
		file.close();
		
		FileOutputStream outFile =new FileOutputStream(new File(path));
		workbook.write(outFile);
		outFile.close();
		
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
}
}
