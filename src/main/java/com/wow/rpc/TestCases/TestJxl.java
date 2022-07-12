package com.wow.rpc.TestCases;

import java.io.File;
import java.io.IOException;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
public class TestJxl {
	
	public static void main(String args[])  throws BiffException, IOException, RowsExceededException, WriteException {
		
		Workbook wb = Workbook.getWorkbook(new File("D:\\File\\221.xls"));
		
		Sheet shtObj = wb.getSheet(0);
		 
		 Cell getcl = shtObj.getCell(5,1);

		 String s = getcl.getContents();
		System.out.println("Text=="+s);
		// String S="225";
			/*
			 * Label label = new Label(0, 0, "Test Count"); excelSheet.addCell(label);
			 */
		 
		wb.close();
		
	}

}
