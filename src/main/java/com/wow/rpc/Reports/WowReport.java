package com.wow.rpc.Reports;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EmptyStackException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import com.relevantcodes.extentreports.LogStatus;
import com.wow.rpc.Utils.GlobalVariables;



public class WowReport extends GlobalVariables {
	private static Logger reportlogger;
        public  int Int_step_number = 1;
        public  int Int_Passed_count = 0;
        public  int Int_Failed_count = 0;
        public  int Int_info_count = 0;
        public  int Int_warning_count = 0;
        private  int Int_testcase_count = 1;
        public  BufferedWriter CreateHeader = null;
        public  BufferedWriter bf_OverAllReport = null;
        
        public  String htmlname;
        public  String snapshotfolder;
         Date cur_dt = null;
         String str_time_stamp;
         String str_time_reqFormat;
         String CaptureScreenshot;
        public  String ImagePath = "";
        public  String ImageName = " ";
        public  String filename;
        public  String reportPath;
        public  String reportPath1;
        public  String resultPath;
        public  String navigaPath;
        public  String snapshotPath;
        public  String snapshotURL;
        public  String snapshotFolderName;
        public  String reportName;
        public  String individualReportPath;
        public  String overAllReportPath;
        private  Logger reportLogger= Logger.getLogger(WowReport.class);
       
        public WowReport() {
                
                if (System.getenv("testsetID") != null) {
                        System.setProperty("logfilepath", System.getenv("LOGFILEPATH"));
                        System.setProperty("filename", System.getenv("JOBNAME"));
                        
                } else {
                        System.setProperty("logfilepath", System.getProperty("user.dir")+ "\\src\\test\\resource");
                        System.setProperty("filename", "RetailLogs");
                        
                }
                reportlogger = Logger.getLogger(WowReport.class);
        }

        public void createReport(String testName, String browserName, String testSetName) {
    		reportLogger.info("In CreateReport method - Test Name is : " + testName);
    		CreateHeader(testName, browserName);
    		addReportstep = extentReport.startTest(testName);
    		addReportstep.assignCategory(testSetName);
    		reportLogger.info("Report Setup done");
    	}
        
        public Object CreateHeader(String TcName, String BrowserName) {

                try {
                	
                        Int_step_number = 1;
                        Int_Passed_count = 0;
                        Int_Failed_count = 0;
                        Int_info_count = 0;
                        Int_warning_count = 0;
                        StrOverAllStatus = "<B><FONT FACE=VERDANA SIZE=2 COLOR=GREEN>PASSED</FONT></B>";
                        str_time_stamp = getTimeStamp();
                        str_time_reqFormat =  getTimeStampFormat();
                        resultPath = System.getenv("RESULTPATH");
                        snapshotFolderName = (new StringBuilder(String.valueOf(TcName))).append("_").append(str_time_stamp).toString();
                        snapshotFolderName = snapshotFolderName.replace(" ", "_");
                        
                        reportlogger.info((new StringBuilder()).append("RESULTPATH........").append(resultPath).toString());
                        filename = TcName;
                        if (resultPath == null || resultPath == "") {
                                reportPath1 = (new StringBuilder(String.valueOf(System.getProperty("user.dir")))).append("\\CustReport").toString();
                                
                                reportPath = (new StringBuilder(String.valueOf(System.getProperty("user.dir")))).append("\\CustReport\\").append(snapshotFolderName).append("\\").toString();
                              snapshotfolder = (new StringBuilder(String.valueOf(System.getProperty("user.dir")))).append("\\CustReport\\").append(snapshotFolderName).append("\\").toString();
                              navigaPath = (new StringBuilder(String.valueOf(snapshotFolderName))).toString(); 
                              
                        } else {
                        	
                        	
                        	reportPath1 = (new StringBuilder(String.valueOf(System.getProperty("user.dir")))).append("\\CustReport").toString();
                                reportPath = (new StringBuilder(String.valueOf(resultPath))).append("\\CustReport").toString();
                                snapshotfolder = (new StringBuilder(String.valueOf(resultPath))).append("\\CustReport\\").append(snapshotFolderName).append("\\").toString();
                              
                        }
                        snapshotPath = System.getenv("SNAPSHOTPATH");
                        
                        
                        
                        if (snapshotPath == null || snapshotPath == " ")
                                snapshotURL = snapshotfolder;
                        else
                                snapshotURL = (new StringBuilder(String.valueOf(snapshotPath))).append(snapshotFolderName).append("/").toString();
                        
                        File file = new File(snapshotfolder);
                        if (!file.exists())
                                file.mkdirs();
                        File file1 = new File(reportPath);
                        if (!file1.exists())
                                file1.mkdirs();
                        reportlogger.info(reportPath);
                        
                        htmlname = (new StringBuilder(String.valueOf(TcName))).append("_").append(BrowserName).append("_").append(str_time_stamp)
                                        .append(".html").toString();
                        
                        Reportname = htmlname;
                        reportName = (new StringBuilder(String.valueOf(reportPath))).append("\\").append(htmlname).toString();
                        individualReportPath = (new StringBuilder(String.valueOf(navigaPath))).append("\\").append(htmlname).toString();
                        File file2 = new File(reportName);
                        file2.createNewFile();
                        
                        
                        CreateHeader = new BufferedWriter(new FileWriter(reportName));
                        CreateHeader.write("<HTML><BODY><TABLE BORDER=0 CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
                        CreateHeader.write("<TABLE BORDER=0 BGCOLOR=BLACK CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
                        CreateHeader.write(
                                        "<TR>"
                                        + "<TD BGCOLOR=#66699 WIDTH=70%><FONT FACE=VERDANA COLOR=WHITE SIZE=2><B>Test Case Name: ");
                        CreateHeader.write((new StringBuilder(String.valueOf(TcName)))
                                        .append("</B></FONT></TD>"
                                                + "<TD COLSPAN=6 BGCOLOR=#66699><FONT FACE=VERDANA COLOR=WHITE SIZE=2><B>")
                                        .append("Browser: ").append(BrowserName).append("</B></FONT></TD>"
                                                + "</TR>").toString());
                        CreateHeader.write("<HTML><BODY><TABLE BORDER=1 CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
                        CreateHeader.write(
                                        "<TR COLS=6>"
                                        + "<TD ALIGN=CENTER BGCOLOR=#FFCC99 WIDTH=3%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>S.NO</B></FONT></TD>"
                                        + "<TD ALIGN=CENTER BGCOLOR=#FFCC99 WIDTH=15%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>TestCase ID</B></FONT></TD>"
                                        + "<TD ALIGN=CENTER BGCOLOR=#FFCC99 WIDTH=35%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>TestStep Description</B></FONT></TD>"
                                        + "<TD ALIGN=CENTER BGCOLOR=#FFCC99 WIDTH=25%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Execution Time</B></FONT></TD>"
                                        + "<TD ALIGN=CENTER BGCOLOR=#FFCC99 WIDTH=25%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Action</B></FONT></TD>"
                                        + "</TR>");
                        
                        Reporter.log((new StringBuilder("*****")).append(TcName).append("*******").toString());
                        reportlogger.info((new StringBuilder("Created Report Header for Test case: ")).append(TcName).toString());
                } catch (Exception exception) {
                        reportlogger.info("Error while Creating Report Header");
                        exception.printStackTrace();
                }
                
                return CreateHeader;
        }

        public Object CustReport(WebDriver webDriver, String titleMsge, String Message, String Result) {
                try {
                        cur_dt = new Date();
                        SimpleDateFormat simpledateformat = new SimpleDateFormat("MM-dd-yyyy 'at' HH:mm:ss z");
                        String s3 = simpledateformat.format(cur_dt);
                        String s4 = "";
                        String s5;
                        
                        switch ((s5 = Result).hashCode()) {
                        default:
                                break;

                        case -1505867908:
                                if (s5.equals("Warning")) {
                                        s4 = "<B><FONT FACE=VERDANA SIZE=2 COLOR=#FF8C00>WARNING</FONT></B>";
                                        Int_warning_count++;
                                }
                                break;

                        case -658498292:
                                if (s5.equals("Information")) {
                                        s4 = "<B><FONT FACE=VERDANA SIZE=2 COLOR=BLACK>INFORMATION</FONT></B>";
                                        Int_info_count++;
                                        addReportstep.log(LogStatus.INFO, Message);
                                }
                                break;

                        case -502072716:
                                if (s5.equals("Pass_ScreenShot")) {
                                        Int_Passed_count++;
                                        String s6 = (new StringBuilder(String.valueOf(snapshotfolder))).append(CaptureScreenshot(webDriver,titleMsge)).toString();
                                       // String s6 = CaptureScreenshot(titleMsge);
                                        
                                        reportlogger.info((new StringBuilder("Screenshot taken for the Passed Test step: ")).append(titleMsge)
                                                        .append(" Path: ").append(s6).toString());
                                        s4 = (new StringBuilder("<A HREF = ")).append(ImageName)
                                                        .append(" style=text-decoration:none TARGET= _blank>")
                                                        .append("<B><FONT FACE=VERDANA SIZE=2 COLOR=BLUE>PASSED</FONT></B>").append("</A>")
                                                        .toString();
                                        StrOverAllStatus = "<B><FONT FACE=VERDANA SIZE=2 COLOR=GREEN>PASSED</FONT></B>";
                                        addReportstep.log(LogStatus.PASS, Message+ "" + addReportstep.addScreenCapture(s6));
                                }
                                break;

                        case 2174270:
                                if (s5.equals("Exit")) {
                                        StrOverAllStatus = s4 = "<B><FONT FACE=VERDANA SIZE=2 COLOR=RED>FAILED</FONT></B>";
                                       // envDataload.stopExecution();
                                        stopExecution();
                                        throw new EmptyStackException();
                                }
                                break;

                        case 2181950:
                                if (s5.equals("Fail")) {
                                        Int_Failed_count++;
                                        
                                      String s7 = (new StringBuilder(String.valueOf(snapshotURL))).append(CaptureScreenshot(webDriver, titleMsge)).toString();
                                      // String s7 = CaptureScreenshot(titleMsge);
                                        reportlogger.info((new StringBuilder("Screenshot taken for the Failed Test step: ")).append(titleMsge)
                                                        .append(" Path: ").append(s7).toString());
                                        String s6 = (new StringBuilder(String.valueOf(snapshotfolder))).append(CaptureScreenshot(webDriver, titleMsge)).toString();	
                                        s4 = (new StringBuilder("<A HREF= ")).append(ImageName).append(" TARGET= _blank>")
                                                        .append("<B><FONT FACE=VERDANA SIZE=2 COLOR=RED>FAILED</FONT></B>").append("</A>")
                                                        .toString();
                                        StrOverAllStatus = "<B><FONT FACE=VERDANA SIZE=2 COLOR=RED>FAILED</FONT></B>";
                                        addReportstep.log(LogStatus.FAIL, Message+ "" + addReportstep.addScreenCapture(s6));
                                                           }
                                break;

                        case 2480177:
                                if (s5.equals("Pass")) {
                                        s4 = "<B><FONT FACE=VERDANA SIZE=2 COLOR=GREEN>PASSED</FONT></B>";
                                        Int_Passed_count++;
                                        addReportstep.log(LogStatus.PASS, Message);
                                }
                                break;
                        }
                   CreateHeader.write((new StringBuilder(
                             "<TR COLS=6><TD ALIGN=CENTER BGCOLOR=#FFFFFF WIDTH=3%><FONT FACE=VERDANA COLOR=BLACK SIZE=2>"))
                              .append(String.valueOf(Int_step_number))
                              .append("</FONT></TD><TD ALIGN=CENTER BGCOLOR=#FFFFFF WIDTH=15%><FONT FACE=VERDANA COLOR=BLACK SIZE=2>")
                              .append(titleMsge)
                              .append("</FONT></TD><TD ALIGN=CENTER BGCOLOR=#FFFFFF WIDTH=35%><FONT FACE=VERDANA COLOR=BLACK SIZE=2>")
                              .append(Message)
                              .append("</FONT></TD><TD ALIGN=CENTER BGCOLOR=#FFFFFF WIDTH=25%><FONT FACE=VERDANA COLOR=BLACK SIZE=2>")
                              .append(s3).append("</FONT></TD><TD ALIGN=CENTER BGCOLOR=#FFFFFF WIDTH=25%>").append(s4)
                              .append("</TD></TR>").toString());
                        Reporter.log((new StringBuilder(String.valueOf(String.valueOf(Int_step_number)))).append(";").append(titleMsge)
                                        .append(";").append(Message).append(";").append(Result).toString());
                        Reporter.log(" ");
                        reportlogger.info((new StringBuilder(String.valueOf(String.valueOf(Int_step_number)))).append(";")
                                        .append(titleMsge).append(";").append(Message).append(";").append(Result).toString());
                        Int_step_number++;
                } catch (Exception exception) {
                	exception.printStackTrace();
                        reportlogger.info("error");
                        CustReportclose(Boolean.valueOf(true),
                                        (new StringBuilder(String.valueOf(titleMsge))).append(" ").append(Message).toString());
                        exception.printStackTrace();
                }
                return CreateHeader;
        }

        public void CustReportclose(Boolean boolean1, String s) {
                try {
                	reportlogger.info("*** Execution Ends ***");
                	 extentReport.endTest(addReportstep);
                	 extentReport.flush();
                        if (boolean1.booleanValue()) {
                        	reportlogger.info("Sorry!! There is a error reported while executing your Testcase");
                                CreateHeader.write("<br><br><br>");
                                CreateHeader
                                                .write((new StringBuilder("<B><FONT FACE=VERDANA SIZE=2 COLOR=RED>**********Error**********"))
                                                                .append(s).append("</FONT><br></B>").toString());
                        }
                        CreateHeader.write(
                                        "</BODY></HTML><TABLE BORDER=0 BGCOLOR=BLACK CELLPADDING=3 CELLSPACING=1 WIDTH=100%><BODY><HTML>");
                        CreateHeader.write((new StringBuilder(
                                        "<TR><TD BGCOLOR=#FFFFE0 WIDTH= 25%><B><I><FONT FACE=VERDANA SIZE=2 COLOR=GREEN>Total Passed:"))
                                                        .append(Int_Passed_count).append("</FONT></I></B>").toString());
                        reportlogger.info((new StringBuilder("Total Passed Steps:")).append(Int_Passed_count).toString());
                        CreateHeader.write((new StringBuilder(
                                        "</TD><TD BGCOLOR=#FFFFE0 WIDTH= 25%><B><I><FONT FACE=VERDANA SIZE=2 COLOR=RED>Total Failed:"))
                                                        .append(Int_Failed_count).append("</FONT></I></B>").toString());
                        reportlogger.info((new StringBuilder("Total Failed Steps:")).append(Int_Failed_count).toString());
                        CreateHeader.write((new StringBuilder(
                                        "</TD><TD BGCOLOR=#FFFFE0 WIDTH= 25%><B><I><FONT FACE=VERDANA SIZE=2 COLOR=BLACK>Total Informations:"))
                                                        .append(Int_info_count).append("</FONT></I></B>").toString());
                        reportlogger.info((new StringBuilder("Total Informations:")).append(Int_info_count).toString());
                        CreateHeader.write((new StringBuilder(
                                        "</TD><TD BGCOLOR=#FFFFE0 WIDTH= 25%><B><I><FONT FACE=VERDANA SIZE=2 COLOR=#FF8C00>Total Warnings:"))
                                                        .append(Int_warning_count).append("</FONT></I></B></TD></TR></BODY></HTML>").toString());
                        reportlogger.info((new StringBuilder("Total Warnings:")).append(Int_warning_count).toString());
                        CreateHeader.close();
                       // copy_files(reportName);
                        reportlogger.info((new StringBuilder("Please find the Test report for the Test case in the Path: "))
                                        .append(htmlname.replace("\\", "/")).toString());
                } catch (Exception exception) {
                        reportlogger.info("error");
                        exception.printStackTrace();
                }
        }

        public static String getTimeStamp() {
                String s = "";
                try {
                        Date date = null;
                        date = new Date();
                        SimpleDateFormat simpledateformat = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
                      //  SimpleDateFormat ft = new SimpleDateFormat ("dd - MMM -yyyy '&' hh:mm:ss a ");
                        s = simpledateformat.format(date);
                } catch (Exception exception) {
                        reportlogger.info("error");
                        exception.printStackTrace();
                }
                return s;
        }
        
        public static String getTimeStampFormat() {
            String s = "";
            try {
                    Date date = null;
                    date = new Date();
                    
                    SimpleDateFormat ft = new SimpleDateFormat ("dd - MMM -yyyy '&' hh:mm a ");
                    s = ft.format(date);
            } catch (Exception exception) {
                    reportlogger.info("error");
                    exception.printStackTrace();
            }
            return s;
    }

        public String CaptureScreenshot(WebDriver webDriver, String imgTitle) {
                try {
                        str_time_stamp = getTimeStamp();
                        ImageName = (new StringBuilder(String.valueOf(imgTitle))).append("_").append(str_time_stamp).append(".png")
                                        .toString();
                        ImageName = ImageName.replace(" ", "_");
                        
                        ImagePath = (new StringBuilder(String.valueOf(snapshotfolder))).append("\\").append(ImageName).toString();
                        
						
						File f = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
						FileUtils.copyFile(f, new File(ImagePath));  

                       /* java.awt.image.BufferedImage bufferedimage = (new Robot())
                                .createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
                        ImageIO.write(bufferedimage, "png", new File(ImagePath));*/
                        Thread.sleep(1000L);
                } catch (Exception exception) {
                        exception.printStackTrace();
                        reportlogger.info("Screenshot error");
                }
                
                return ImageName;
        }

        public void OverAllReport(String s, String s1, String s2) {
                try {
                        reportlogger.info("Called OverAllReport Report");
                        File file = new File((new StringBuilder(String.valueOf(System.getProperty("user.dir"))))
                                        .append("\\CustReport\\").toString());
                        file.isDirectory();
                        String s3 = (new StringBuilder(String.valueOf(System.getProperty("user.dir")))).append("\\CustReport\\")
                                        .append("Over_All_Status.html").toString();
                        File file1 = new File(s3);
                        if (file1.exists()) {
                                bf_OverAllReport = new BufferedWriter(new FileWriter(s3, true));
                                bf_OverAllReport.write((new StringBuilder(
                                                "<TR COLS=4><TD ALIGN=CENTER BGCOLOR=#FFCC99 WIDTH=50%><FONT FACE=VERDANA COLOR=BLACK SIZE=2>"))
                                                                .append(s)
                                                                .append("</FONT></TD><TD ALIGN=CENTER BGCOLOR=#FFCC99 WIDTH=7%><FONT FACE=VERDANA COLOR=BLACK SIZE=2>")
                                                                .append(s1)
                                                                .append("</FONT></TD><TD ALIGN=CENTER BGCOLOR=#FFCC99 WIDTH=7%><FONT FACE=VERDANA COLOR=BLACK SIZE=2>")
                                                                .append(s2).append("</FONT></TD><TD ALIGN=CENTER BGCOLOR=#FFCC99 WIDTH=15%>")
                                                                .append(StrOverAllStatus).append("</TD></TR>").toString());
                                bf_OverAllReport.close();
                        } else {
                                bf_OverAllReport = new BufferedWriter(new FileWriter(s3));
                                bf_OverAllReport.write("<HTML><BODY><TABLE BORDER=0 CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
                                bf_OverAllReport.write("<TABLE BORDER=0 BGCOLOR=BLACK CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
                                bf_OverAllReport.write(
                                                "<TR><TD ALIGN=CENTER BGCOLOR=#66699 WIDTH=100%><FONT FACE=VERDANA COLOR=WHITE SIZE=4><B>OVER ALL RESULTS</B></FONT></TD><TR>");
                                bf_OverAllReport.write("<HTML><BODY><TABLE BORDER=1 CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
                                bf_OverAllReport.write(
                                                "<TR COLS=4><TD ALIGN=CENTER BGCOLOR=#FFCC99 WIDTH=50%><FONT FACE=VERDANA COLOR=BLACK SIZE=2 ALIGN=CENTER><B>TEST CASE NAME</B></FONT></TD><TD ALIGN=CENTER BGCOLOR=#FFCC99 WIDTH=7%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>BROWSER</B></FONT></TD><TD ALIGN=CENTER BGCOLOR=#FFCC99 WIDTH=7%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>ENVIRONMENT</B></FONT></TD><TD ALIGN=CENTER BGCOLOR=#FFCC99 WIDTH=15%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>OVER ALL STATUS</B></FONT></TD></TR>");
                                bf_OverAllReport.write((new StringBuilder(
                                                "<TR COLS=4><TD ALIGN=CENTER BGCOLOR=#FFCC99 WIDTH=50%><FONT FACE=VERDANA COLOR=BLACK SIZE=2>"))
                                                                .append(s)
                                                                .append("</FONT></TD><TD ALIGN=CENTER BGCOLOR=#FFCC99 WIDTH=7%><FONT FACE=VERDANA COLOR=BLACK SIZE=2>")
                                                                .append(s1)
                                                                .append("</FONT></TD><TD ALIGN=CENTER BGCOLOR=#FFCC99 WIDTH=7%><FONT FACE=VERDANA COLOR=BLACK SIZE=2>")
                                                                .append(s2).append("</FONT></TD><TD ALIGN=CENTER BGCOLOR=#FFCC99 WIDTH=15%>")
                                                                .append(StrOverAllStatus).append("</TD></TR>").toString());
                                bf_OverAllReport.close();
                        }
                        reportlogger.info((new StringBuilder("Please find your overall results in the path: "))
                                        .append(s3.replace("\\", "/")).toString());
                } catch (Exception exception) {
                        reportlogger.info("error");
                        exception.printStackTrace();
                }
        }

        public void OverAllReport_Custom(String s, String s1, String s2) {
                try {
                        String s3 = (new StringBuilder(String.valueOf(System.getProperty("user.dir")))).append("\\CustReport\\")
                                        .append("Over_All_Status.html").toString();
                        File file = new File(s3);
                        if (file.exists()) {
                                bf_OverAllReport = new BufferedWriter(new FileWriter(s3, true));
                                String as[] = s2.split("\\|");
                                int i = as.length + 1;
                                bf_OverAllReport
                                                .write((new StringBuilder("<TR COLS=")).append(String.valueOf(i)).append(">").toString());
                                for (int k = 0; k < as.length; k++) {
                                        String s4 = as[k];
                                        if (as[k].equals("Passed"))
                                                s4 = (new StringBuilder("<B><FONT FACE=VERDANA COLOR=GREEN SIZE=2>")).append(s4)
                                                                .append("</FONT></B>").toString();
                                        if (as[k].equals("Failed"))
                                                s4 = (new StringBuilder("<B><FONT FACE=VERDANA COLOR=RED SIZE=2>")).append(s4)
                                                                .append("</FONT></B>").toString();
                                        bf_OverAllReport.write((new StringBuilder(
                                                        "<TD ALIGN=CENTER BGCOLOR=#FFFFE0 WIDTH=AUTO><FONT FACE=VERDANA COLOR=BLACK SIZE=2>"))
                                                                        .append(s4).append("</FONT></TD>").toString());
                                }

                                bf_OverAllReport.write("</TR>");
                                bf_OverAllReport.close();
                        } else {
                                bf_OverAllReport = new BufferedWriter(new FileWriter(s3));
                                bf_OverAllReport.write("<HTML><BODY><TABLE BORDER=0 CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
                                bf_OverAllReport.write("<TABLE BORDER=0 BGCOLOR=BLACK CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
                                bf_OverAllReport.write((new StringBuilder(
                                                "<TR><TD ALIGN=CENTER BGCOLOR=#66699 WIDTH=100%><FONT FACE=VERDANA COLOR=WHITE SIZE=4><B>"))
                                                                .append(s).append("-OVER ALL RESULTS</B></FONT></TD><TR>").toString());
                                bf_OverAllReport.write("<HTML><BODY><TABLE BORDER=1 CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
                                String as1[] = s1.split("\\|");
                                int j = as1.length + 1;
                                bf_OverAllReport
                                                .write((new StringBuilder("<TR COLS=")).append(String.valueOf(j)).append(">").toString());
                                for (int l = 0; l < as1.length; l++)
                                        bf_OverAllReport.write((new StringBuilder(
                                                        "<TD ALIGN=CENTER BGCOLOR=#FFCC99 WIDTH=AUTO><FONT FACE=VERDANA COLOR=BLACK SIZE=2 ALIGN=CENTER><B>"))
                                                                        .append(as1[l]).append("</B></FONT></TD>").toString());

                                bf_OverAllReport.write("</TR>");
                                String as2[] = s2.split("\\|");
                                reportlogger.info(as2.length);
                                int i1 = as2.length + 1;
                                bf_OverAllReport
                                                .write((new StringBuilder("<TR COLS=")).append(String.valueOf(i1)).append(">").toString());
                                for (int j1 = 0; j1 < as2.length; j1++) {
                                        String s5 = as2[j1];
                                        if (as2[j1].equals("Passed"))
                                                s5 = (new StringBuilder("<B><FONT FACE=VERDANA COLOR=GREEN SIZE=2>")).append(s5)
                                                                .append("</FONT></B>").toString();
                                        if (as2[j1].equals("Failed"))
                                                s5 = (new StringBuilder("<B><FONT FACE=VERDANA COLOR=RED SIZE=2>")).append(s5)
                                                                .append("</FONT></B>").toString();
                                        bf_OverAllReport.write((new StringBuilder(
                                                        "<TD ALIGN=CENTER BGCOLOR=#FFFFE0 WIDTH=AUTO><FONT FACE=VERDANA COLOR=BLACK SIZE=2>"))
                                                                        .append(s5).append("</FONT></TD>").toString());
                                }

                                bf_OverAllReport.write("</TR>");
                                bf_OverAllReport.close();
                        }
                        reportlogger.info((new StringBuilder("Please find your overall results in the path: "))
                                        .append(s3.replace("\\", "/")).toString());
                } catch (Exception exception) {
                        reportlogger.info("error");
                        exception.printStackTrace();
                }
        }

              public void overallStatusReport(String testName, String duration, String Status,String bname,String env) {
                try {
                       
                	
                	reportlogger.info("Called overallStatusReport ");
                        	
                      
                        overAllReportPath = System.getenv("REPORTPATH");
                        
                        if (overAllReportPath == null || overAllReportPath == " ")
                                htmlname = (new StringBuilder(String.valueOf(reportPath1))).append("\\").append(htmlname).toString();
                        else
                                htmlname = (new StringBuilder(String.valueOf(overAllReportPath))).append(htmlname).toString();
                        
                        File file = new File((new StringBuilder(String.valueOf(reportPath1))).toString());
                        file.isDirectory();
                        
                        String s3 = (new StringBuilder(String.valueOf(reportPath1))).append("\\").append("overallStatusReport.html")
                                        .toString();
                        
                        
                        File file1 = new File(s3);
                        
                        
                       
                        if (file1.exists()) {
                        	
                        
                        	 Int_testcase_count++; System.out.println("Int_testcase_count++; ==" + Int_testcase_count++);                                               
                        	   bf_OverAllReport = new BufferedWriter(new FileWriter(s3, true));
                        	   
                        	   if(Status.equals("<B><FONT FACE=VERDANA SIZE=2 COLOR=RED>FAILED</FONT></B>") ) {
                        		   
                        		  FailTc++;
                              bf_OverAllReport.write((new StringBuilder(
                              "<TR COLS=3><script>document.getElementById('SID').innerHTML='<font face=VERDANA color=black size=2><b>Build Status:<b><font face=VERDANA size=2 color=RED>FAIL</font></b></b></font>'</script>"
                                               // + "<TD ALIGN=CENTER BGCOLOR=WHITE WIDTH=10%><FONT FACE=VERDANA COLOR=BLACK SIZE=2>")).append(Int_testcase_count).append("</FONT></TD>"
                                                		+ "<TD ALIGN=CENTER BGCOLOR=WHITE WIDTH=60%><FONT FACE=VERDANA COLOR=BLACK SIZE=2>")).append(testName).append("</FONT></TD>"
                                                + "<TD ALIGN=CENTER BGCOLOR=WHITE WIDTH=20%><FONT FACE=VERDANA COLOR=BLACK SIZE=2>").append(Status).append("</FONT></TD>"
                                                + "<TD ALIGN=CENTER BGCOLOR=WHITE WIDTH=20%><A HREF = ").append(individualReportPath).append("><FONT FACE=VERDANA COLOR=BLACK SIZE=2>").append("Result Log")
                                                    .append("</A> </TD></TR> ").toString());
                              
                        	   } else {
                        		   
                        		 PassTc++;  
                        		   bf_OverAllReport.write((new StringBuilder(
                                           "<TR COLS=3>"
                                                            // + "<TD ALIGN=CENTER BGCOLOR=WHITE WIDTH=10%><FONT FACE=VERDANA COLOR=BLACK SIZE=2>")).append(Int_testcase_count).append("</FONT></TD>"
                                                             		+ "<TD ALIGN=CENTER BGCOLOR=WHITE WIDTH=60%><FONT FACE=VERDANA COLOR=BLACK SIZE=2>")).append(testName).append("</FONT></TD>"
                                                             + "<TD ALIGN=CENTER BGCOLOR=WHITE WIDTH=20%><FONT FACE=VERDANA COLOR=BLACK SIZE=2>").append(Status).append("</FONT></TD>"
                                                             + "<TD ALIGN=CENTER BGCOLOR=WHITE WIDTH=20%><A HREF = ").append(individualReportPath).append("><FONT FACE=VERDANA COLOR=BLACK SIZE=2>").append("Result Log")
                                                                 .append("</A> </TD></TR> ").toString());
                        		   
                        		   
                        	   }
                                bf_OverAllReport.close();
                        } else {   
                        	
                        	if(Status.equals("<B><FONT FACE=VERDANA SIZE=2 COLOR=RED>FAILED</FONT></B>") ) {
                        		
                        		 FailTc++;
                        		 
                        	}else {
                        		
                        		PassTc++;
                        	}
                        	String Status1="";
                        	if(Status.equals("<B><FONT FACE=VERDANA SIZE=2 COLOR=GREEN>PASSED</FONT></B>")) {  Status1 = "<B><FONT FACE=VERDANA SIZE=2 COLOR=GREEN>PASS</FONT></B>"; }
                                bf_OverAllReport = new BufferedWriter(new FileWriter(s3));
                                bf_OverAllReport.write("<HTML><BODY><TABLE BORDER=0 CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
                                bf_OverAllReport.write("<TABLE BORDER=0 BGCOLOR=BLACK CELLPADDING=3 CELLSPACING=1 WIDTH=100% id=tableId>");
                                bf_OverAllReport.write(new StringBuilder(
                                                "<TR><TD ALIGN=CENTER BGCOLOR=#9ACD32 WIDTH=100%><FONT FACE=VERDANA COLOR=BLACK SIZE=4><B>Automation Build Regression Report</B></FONT></TD><TR> "
                                                + "<TR><TD ALIGN=CENTER BGCOLOR=#1E90FF WIDTH=100%><FONT FACE=VERDANA COLOR=WHITE SIZE=4><B>Project - AUT </B></FONT></TD></TR> "
                                                + "<TR><TD ALIGN=CENTER BGCOLOR=#9ACD32 WIDTH=100%><FONT FACE=VERDANA COLOR=WHITE SIZE=4><B></B></FONT></TD></TR>"
                                                + "<TR><TD ALIGN=CENTER BGCOLOR=WHITE WIDTH=100% id=SID><FONT FACE=VERDANA COLOR=black SIZE=2><B>Build Status :").append(Status1).append("</B></FONT></TD></TR>"
                                                + "<TR><TD ALIGN=CENTER BGCOLOR=WHITE WIDTH=100%><FONT FACE=VERDANA COLOR=black SIZE=2><B>Module:AUT-Module-1 </B></FONT></TD></TR>"
                                                + " <TR><TD ALIGN=CENTER BGCOLOR=WHITE WIDTH=100%><FONT FACE=VERDANA COLOR=black SIZE=2><B>Date of build:").append(str_time_reqFormat).append("</B></FONT></TD></TR> "
                                                + " <TR><TD ALIGN=CENTER BGCOLOR=WHITE WIDTH=100%><FONT FACE=VERDANA COLOR=black SIZE=2><B>Build description:").append(env).append(" Regression - ").append("Chrome").append("</B></FONT></TD></TR> "
                                                + "<TR><TD ALIGN=CENTER BGCOLOR=#1E90FF WIDTH=100%><FONT FACE=VERDANA COLOR=BLACK SIZE=4><B>Test Execution Summary </B></FONT></TD></TR> ").toString());
                                bf_OverAllReport.write("<HTML><BODY><TABLE BORDER=1 CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
                                bf_OverAllReport.write(
                                                "<TR COLS=3>"
//                                                + "<TD ALIGN=CENTER BGCOLOR=WHITE WIDTH=10%><FONT FACE=VERDANA COLOR=BLACK SIZE=2 ALIGN=CENTER><B>SNo</B></FONT></TD>"
                                                + "<TD ALIGN=CENTER BGCOLOR=WHITE WIDTH=60%><FONT FACE=VERDANA COLOR=BLACK SIZE=2 ALIGN=CENTER><B>Functionality</B></FONT></TD>"
                                                + "<TD ALIGN=CENTER BGCOLOR=WHITE WIDTH=20%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Status</B></FONT></TD>"
                                                + "<TD ALIGN=CENTER BGCOLOR=WHITE WIDTH=20%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Log</B></FONT></TD> </TR>");
                                bf_OverAllReport.write((new StringBuilder(
                                                "<TR COLS=3>"
                                                //+ "<TD ALIGN=CENTER BGCOLOR=WHITE WIDTH=10%><FONT FACE=VERDANA COLOR=BLACK SIZE=2>")).append(Int_testcase_count).append("</FONT></TD>"
                                                + "<TD ALIGN=CENTER BGCOLOR=WHITE WIDTH=60%><FONT FACE=VERDANA COLOR=BLACK SIZE=2>")).append(testName).append("</FONT></TD>"
                                                + "<TD ALIGN=CENTER BGCOLOR=WHITE WIDTH=20%><FONT FACE=VERDANA COLOR=BLACK SIZE=2>").append(Status).append("</FONT></TD>"
                                                + "<TD ALIGN=CENTER BGCOLOR=WHITE WIDTH=20%><A HREF = ").append(individualReportPath).append("><FONT FACE=VERDANA COLOR=BLACK SIZE=2>").append("Result Log")
                                                    .append("</A> </TD>"
                                                    + "</TR> ").toString());
                                bf_OverAllReport.close();
                        } 
                        reportlogger.info((new StringBuilder("Please find your overall results in the path: "))
                                        .append(s3.replace("\\", "/")).toString());
                } catch (Exception exception) {
                        reportlogger.info("error");
                        exception.printStackTrace();
                }
        }

        public void delete_files() {
                try {
                        /*File file = new File((new StringBuilder(String.valueOf(System.getProperty("user.dir"))))
                                        .append("\\CustReport\\Jenkins").toString());*/
                        File file1 = new File((new StringBuilder(String.valueOf(System.getProperty("user.dir"))))
                                        .append("\\CustReport").toString());
                        if (!file1.mkdir()) {
                                File afile[];
                                int i = (afile = file1.listFiles()).length;
                                
                                for (int j = 0; j < i; j++) {
                                        File file2 = afile[j];
                                        file2.delete();
                                }
                        }

                        //file1.delete();
                } catch (Exception exception) {
                        exception.printStackTrace();
                }
        }

        public void copy_overallStatusReport() {
                try {
                        File file = new File((new StringBuilder(String.valueOf(System.getProperty("user.dir"))))
                                        .append("\\CustReport\\overallStatusReport.html").toString());
                        if (file.mkdir()) {
                                reportlogger.info("File Not  exists");
                        } else {
                               /* File file1 = new File((new StringBuilder(String.valueOf(System.getProperty("user.dir"))))
                                                .append("\\CustReport\\Jenkins\\").append(file.getName()).toString());
                                Files.copy(file.toPath(), file1.toPath(), new CopyOption[0]);*/
                        }
                } catch (Exception exception) {
                        exception.printStackTrace();
                }
        }
        
public void overAllFooter() { 
            
            String s3 = (new StringBuilder(String.valueOf(reportPath))).append("\\").append("overallStatusReport.html").toString();

          try
          {
              bf_OverAllReport = new BufferedWriter(new FileWriter(s3, true));
              
              //bf_OverAllReport.write((new StringBuilder("<TR COLS=4><TD colspan=4 ALIGN=CENTER BGCOLOR=#1E90FF WIDTH=100%><FONT FACE=VERDANA COLOR=BLACK SIZE=4><B>Replenishment Automation Framework</B></FONT></TD><TR>").toString()));
              bf_OverAllReport.write((new StringBuilder("<TR COLS=4><TD colspan=4 ALIGN=CENTER BGCOLOR=#1E90FF WIDTH=100%><FONT FACE=VERDANA COLOR=BLACK SIZE=4><B></B></FONT></TD><TR>").toString()));
              bf_OverAllReport.close();
              
          }catch(Exception e)
          {
              System.out.println(e.getMessage());
              
          }
            
            
            
        }

public void deleteFiles(){
	
	 try {
         /*File file = new File((new StringBuilder(String.valueOf(System.getProperty("user.dir"))))
                         .append("\\CustReport\\Jenkins").toString());*/
        
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
     // System.out.println("File deleted : " + file.getAbsolutePath());
   }
}

private static void deleteEmptyDirectory(File fi)
{
   fi.delete();
  // System.out.println("Directory deleted : " + fi.getAbsolutePath());
}
public void stopExecution() throws Exception {
	try {
		prop.setProperty("ContinueFlag", "N");
		//log.info("In stopExecution()");

	} catch (Exception ex) {
		ex.printStackTrace();
	}

}      
public String getOvellAllReportStatus(){
	
	return StrOverAllStatus;
}
public int getPassCount(){
	System.out.println("PassTc==" + PassTc);
	return PassTc;
	
}
public int getFailCount(){
	System.out.println("FailTc==" + FailTc);
	return FailTc;
	
}

public Object CreateHeader_Table(String TcName, String BrowserName) {

    try {
    	
            Int_step_number = 1;
            Int_Passed_count = 0;
            Int_Failed_count = 0;
            Int_info_count = 0;
            Int_warning_count = 0;
            StrOverAllStatus = "<B><FONT FACE=VERDANA SIZE=2 COLOR=GREEN>PASSED</FONT></B>";
            str_time_stamp = getTimeStamp();
            str_time_reqFormat =  getTimeStampFormat();
            resultPath = System.getenv("RESULTPATH");
            snapshotFolderName = (new StringBuilder(String.valueOf(TcName))).append("_").append(str_time_stamp).toString();
            snapshotFolderName = snapshotFolderName.replace(" ", "_");
            
            reportlogger.info((new StringBuilder()).append("RESULTPATH........").append(resultPath).toString());
            filename = TcName;
            if (resultPath == null || resultPath == "") {
                    reportPath1 = (new StringBuilder(String.valueOf(System.getProperty("user.dir")))).append("\\CustReport").toString();
                    
                    reportPath = (new StringBuilder(String.valueOf(System.getProperty("user.dir")))).append("\\CustReport\\").append(snapshotFolderName).append("\\").toString();
                  snapshotfolder = (new StringBuilder(String.valueOf(System.getProperty("user.dir")))).append("\\CustReport\\").append(snapshotFolderName).append("\\").toString();
                  navigaPath = (new StringBuilder(String.valueOf(snapshotFolderName))).toString(); 
                  
            } else {
            	
            	
            	reportPath1 = (new StringBuilder(String.valueOf(System.getProperty("user.dir")))).append("\\CustReport").toString();
                    reportPath = (new StringBuilder(String.valueOf(resultPath))).append("\\CustReport").toString();
                    snapshotfolder = (new StringBuilder(String.valueOf(resultPath))).append("\\CustReport\\").append(snapshotFolderName).append("\\").toString();
                  
            }
            snapshotPath = System.getenv("SNAPSHOTPATH");
            
            
            
            if (snapshotPath == null || snapshotPath == " ")
                    snapshotURL = snapshotfolder;
            else
                    snapshotURL = (new StringBuilder(String.valueOf(snapshotPath))).append(snapshotFolderName).append("/").toString();
            
            File file = new File(snapshotfolder);
            if (!file.exists())
                    file.mkdirs();
            File file1 = new File(reportPath);
            if (!file1.exists())
                    file1.mkdirs();
            reportlogger.info(reportPath);
            
            htmlname = (new StringBuilder(String.valueOf(TcName))).append("_").append(BrowserName).append("_").append(str_time_stamp)
                            .append(".html").toString();
            
            Reportname = htmlname;
            reportName = (new StringBuilder(String.valueOf(reportPath))).append("\\").append(htmlname).toString();
            individualReportPath = (new StringBuilder(String.valueOf(navigaPath))).append("\\").append(htmlname).toString();
            File file2 = new File(reportName);
            file2.createNewFile();
            
            
            CreateHeader = new BufferedWriter(new FileWriter(reportName));
            CreateHeader.write("<HTML><BODY><TABLE BORDER=0 CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
            CreateHeader.write("<TABLE BORDER=0 BGCOLOR=BLACK CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
            CreateHeader.write(
                            "<TR>"
                            + "<TD BGCOLOR=#66699 WIDTH=70%><FONT FACE=VERDANA COLOR=WHITE SIZE=2><B>Test Case Name: ");
            CreateHeader.write((new StringBuilder(String.valueOf(TcName)))
                            .append("</B></FONT></TD>"
                                    + "<TD COLSPAN=6 BGCOLOR=#66699><FONT FACE=VERDANA COLOR=WHITE SIZE=2><B>")
                            .append("Browser: ").append(BrowserName).append("</B></FONT></TD>"
                                    + "</TR>").toString());
            CreateHeader.write("<HTML><BODY><TABLE BORDER=1 CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
            CreateHeader.write(
                            "<TR COLS=6>"
                            + "<TD ALIGN=CENTER BGCOLOR=#FFCC99 WIDTH=3%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>S.NO</B></FONT></TD>"
                            + "<TD ALIGN=CENTER BGCOLOR=#FFCC99 WIDTH=15%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>TestCase ID</B></FONT></TD>"
                            + "<TD ALIGN=CENTER BGCOLOR=#FFCC99 WIDTH=35%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>TestStep Description</B></FONT></TD>"
                            + "<TD ALIGN=CENTER BGCOLOR=#FFCC99 WIDTH=35%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Count</B></FONT></TD>"
                            + "<TD ALIGN=CENTER BGCOLOR=#FFCC99 WIDTH=25%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Execution Time</B></FONT></TD>"
                            + "<TD ALIGN=CENTER BGCOLOR=#FFCC99 WIDTH=25%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Action</B></FONT></TD>"
                            + "</TR>");
            
            Reporter.log((new StringBuilder("*****")).append(TcName).append("*******").toString());
            reportlogger.info((new StringBuilder("Created Report Header for Test case: ")).append(TcName).toString());
    } catch (Exception exception) {
            reportlogger.info("Error while Creating Report Header");
            exception.printStackTrace();
    }
    
    return CreateHeader;
}
public void createReport1(String testName, String browserName, String testSetName) {
	reportLogger.info("In CreateReport method - Test Name is : " + testName);
	CreateHeader_Table(testName, browserName);
	addReportstep = extentReport.startTest(testName);
	addReportstep.assignCategory(testSetName);
	reportLogger.info("Report Setup done");
}
public Object CustReportNew(WebDriver webDriver, String titleMsge, String Message,String Count, String Result) {
    try {
            cur_dt = new Date();
            SimpleDateFormat simpledateformat = new SimpleDateFormat("MM-dd-yyyy 'at' HH:mm:ss z");
            String s3 = simpledateformat.format(cur_dt);
            String s4 = "";
            String s5;
            
            switch ((s5 = Result).hashCode()) {
            default:
                    break;

            case -1505867908:
                    if (s5.equals("Warning")) {
                            s4 = "<B><FONT FACE=VERDANA SIZE=2 COLOR=#FF8C00>WARNING</FONT></B>";
                            Int_warning_count++;
                    }
                    break;

            case -658498292:
                    if (s5.equals("Information")) {
                            s4 = "<B><FONT FACE=VERDANA SIZE=2 COLOR=BLACK>INFORMATION</FONT></B>";
                            Int_info_count++;
                            addReportstep.log(LogStatus.INFO, Message);
                    }
                    break;

            case -502072716:
                    if (s5.equals("Pass_ScreenShot")) {
                            Int_Passed_count++;
                            String s6 = (new StringBuilder(String.valueOf(snapshotfolder))).append(CaptureScreenshot(webDriver,titleMsge)).toString();
                           // String s6 = CaptureScreenshot(titleMsge);
                            
                            reportlogger.info((new StringBuilder("Screenshot taken for the Passed Test step: ")).append(titleMsge)
                                            .append(" Path: ").append(s6).toString());
                            s4 = (new StringBuilder("<A HREF = ")).append(ImageName)
                                            .append(" style=text-decoration:none TARGET= _blank>")
                                            .append("<B><FONT FACE=VERDANA SIZE=2 COLOR=BLUE>PASSED</FONT></B>").append("</A>")
                                            .toString();
                            StrOverAllStatus = "<B><FONT FACE=VERDANA SIZE=2 COLOR=GREEN>PASSED</FONT></B>";
                            addReportstep.log(LogStatus.PASS, Message+ "" + addReportstep.addScreenCapture(s6));
                    }
                    break;

            case 2174270:
                    if (s5.equals("Exit")) {
                            StrOverAllStatus = s4 = "<B><FONT FACE=VERDANA SIZE=2 COLOR=RED>FAILED</FONT></B>";
                           // envDataload.stopExecution();
                            stopExecution();
                            throw new EmptyStackException();
                    }
                    break;

            case 2181950:
                    if (s5.equals("Fail")) {
                            Int_Failed_count++;
                            
                          String s7 = (new StringBuilder(String.valueOf(snapshotURL))).append(CaptureScreenshot(webDriver, titleMsge)).toString();
                          // String s7 = CaptureScreenshot(titleMsge);
                            reportlogger.info((new StringBuilder("Screenshot taken for the Failed Test step: ")).append(titleMsge)
                                            .append(" Path: ").append(s7).toString());
                            String s6 = (new StringBuilder(String.valueOf(snapshotfolder))).append(CaptureScreenshot(webDriver, titleMsge)).toString();	
                            s4 = (new StringBuilder("<A HREF= ")).append(ImageName).append(" TARGET= _blank>")
                                            .append("<B><FONT FACE=VERDANA SIZE=2 COLOR=RED>FAILED</FONT></B>").append("</A>")
                                            .toString();
                            StrOverAllStatus = "<B><FONT FACE=VERDANA SIZE=2 COLOR=RED>FAILED</FONT></B>";
                            addReportstep.log(LogStatus.FAIL, Message+ "" + addReportstep.addScreenCapture(s6));
                                               }
                    break;

            case 2480177:
                    if (s5.equals("Pass")) {
                            s4 = "<B><FONT FACE=VERDANA SIZE=2 COLOR=GREEN>PASSED</FONT></B>";
                            Int_Passed_count++;
                            addReportstep.log(LogStatus.PASS, Message);
                    }
                    break;
            }
       CreateHeader.write((new StringBuilder(
                 "<TR COLS=6><TD ALIGN=CENTER BGCOLOR=#FFFFFF WIDTH=3%><FONT FACE=VERDANA COLOR=BLACK SIZE=2>"))
                  .append(String.valueOf(Int_step_number))
                  .append("</FONT></TD><TD ALIGN=CENTER BGCOLOR=#FFFFFF WIDTH=15%><FONT FACE=VERDANA COLOR=BLACK SIZE=2>")
                  .append(titleMsge)
                  .append("</FONT></TD><TD ALIGN=CENTER BGCOLOR=#FFFFFF WIDTH=35%><FONT FACE=VERDANA COLOR=BLACK SIZE=2>")
                  .append(Message)
                  .append("</FONT></TD><TD ALIGN=CENTER BGCOLOR=#FFFFFF WIDTH=25%><FONT FACE=VERDANA COLOR=BLACK SIZE=2>")
                  .append(Count).append("</FONT></TD><TD ALIGN=CENTER BGCOLOR=#FFFFFF WIDTH=25%><FONT FACE=VERDANA COLOR=BLACK SIZE=2>")
                  .append(s3).append("</FONT></TD><TD ALIGN=CENTER BGCOLOR=#FFFFFF WIDTH=25%>").append(s4)
                  .append("</TD></TR>").toString());
            Reporter.log((new StringBuilder(String.valueOf(String.valueOf(Int_step_number)))).append(";").append(titleMsge)
                            .append(";").append(Message).append(";").append(Result).toString());
            Reporter.log(" ");
            reportlogger.info((new StringBuilder(String.valueOf(String.valueOf(Int_step_number)))).append(";")
                            .append(titleMsge).append(";").append(Message).append(";").append(Result).toString());
            Int_step_number++;
    } catch (Exception exception) {
    	exception.printStackTrace();
            reportlogger.info("error");
            CustReportclose(Boolean.valueOf(true),
                            (new StringBuilder(String.valueOf(titleMsge))).append(" ").append(Message).toString());
            exception.printStackTrace();
    }
    return CreateHeader;
}

}