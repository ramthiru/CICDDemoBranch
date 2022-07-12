package com.wow.rpc.Utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Excel
{
	//to get values from excel
    public static String getCellValue(String xl, String Sheet, int r, int c)
      {
          try
                {
                             FileInputStream fis = new FileInputStream(xl);
                             POIFSFileSystem fs = new POIFSFileSystem(fis);
                             Workbook wb = WorkbookFactory.create(fs);
                             Cell cell = wb.getSheet(Sheet).getRow(r).getCell(c);
                             fis.close();
                              return cell.getStringCellValue();
                                }
                                catch (Exception e)
                                {
                                                return "";
                                }
                }
                public static int getRowCount (String xl, String Sheet)
                {
                    try
                         {
                             FileInputStream fis = new FileInputStream(xl);
                             POIFSFileSystem fs = new POIFSFileSystem(fis);
                             Workbook wb = WorkbookFactory.create(fs);
                             fis.close();
                             return wb.getSheet(Sheet).getLastRowNum();
                          }
                                catch (Exception e)
                               {
                                  return 0;
                                }
                }
                //To write data into excel
                public static void writeExcel(String xl, String Sheet, String cno, int row, int col) throws IOException
                {
                	FileInputStream fis = new FileInputStream(xl);
                	POIFSFileSystem fs = new POIFSFileSystem(fis);
                    Workbook wb = WorkbookFactory.create(fs);
                	org.apache.poi.ss.usermodel.Sheet sh=wb.getSheet(Sheet);
                	// int rowCount = sh.getLastRowNum()-sh.getFirstRowNum();
                	
                	 Row newRow = sh.getRow(row);

                	        //Fill data in row
                	        Cell cell = newRow.createCell(col);
                	        cell.setCellValue(cno);
                	   
                	 fis.close();
                	 FileOutputStream os = new FileOutputStream(xl);
                	 wb.write(os);
                	 os.close();
                	
                }
}