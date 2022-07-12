package com.wow.rpc.Reports;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Mail {
	List<String> filesListInDir = new ArrayList<String>();
public void MailScheduler(int PassTc,int FailTc) throws IOException {



    final String username = "tramachandran@gmail.com";
   final String password = "aiyhjtbkcupkhvyh";

    Properties props = new Properties();
    props.put("mail.smtp.host", "smtp.gmail.com");
	props.put("mail.smtp.socketFactory.port", "465");
	props.put("mail.smtp.socketFactory.class",
			"javax.net.ssl.SSLSocketFactory");
	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.port", "465");
	props.put("mail.smtp.socketFactory.fallback", "true");
    
    Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

    try {

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("tramachandran@gmail.com"));
        
        String status="PASSED";
        if(FailTc >0){
        	status="FAILED";
        }
         
        
        
      /*  String mailIDs;    
	     
		 FileReader fr=new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\retailExecutionSupporter\\MailFile");
	     @SuppressWarnings("resource")
		BufferedReader br=new BufferedReader(fr);
	    mailIDs=br.readLine();
	    System.out.println("mailIDs==" + mailIDs);
		*/
        
        message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("tramachandran@gmail.com"));
      
        message.setSubject("AUT - Regression Automation Execution Status:-" + status + ", # of TCs Executed : "+(PassTc+FailTc )+", Passed TCs : " + PassTc + " Failed TCs : " + FailTc);
        MimeBodyPart messageBodyPart2 = new MimeBodyPart();  
		  
        File dir = new File(System.getProperty("user.dir")+ "\\CustReport");
		//String zipDirName = "D:\\Rc_AutomationDevelopment\\RcAutomation\\CustReport\\ExecutionReport.zip";
		String zipDirName = System.getProperty("user.dir")+ "\\CustReport\\ExecutionReport.zip";
	    zipDirectory(dir, zipDirName);
		 
	    String filename1 = System.getProperty("user.dir")+ "\\CustReport\\ExecutionReport.zip";
        
        
		 
		ArrayList<String> str=new ArrayList<String>();
	    str.add(filename1);
	    Multipart multipart = new MimeMultipart(); 
	    DataSource source = null;
	    for(String s:str)
	    {
	    	source = new FileDataSource(s); 
	    	messageBodyPart2.setDataHandler(new DataHandler(source));  
		    messageBodyPart2.setFileName(s);
		    multipart.addBodyPart(messageBodyPart2);
	    }
        
        message.setContent(multipart);

        System.out.println("Sending");

        Transport.send(message);

        System.out.println("Done");

    } catch (MessagingException e) {
        e.printStackTrace();
    }
  }
private void zipDirectory(File dir, String zipDirName) {
    try {
        populateFilesList(dir);
        //now zip files one by one
        //create ZipOutputStream to write to the zip file
        FileOutputStream fos = new FileOutputStream(zipDirName);
        ZipOutputStream zos = new ZipOutputStream(fos);
        for(String filePath : filesListInDir){
            System.out.println("Zipping "+filePath);
            //for ZipEntry we need to keep only relative file path, so we used substring on absolute path
            ZipEntry ze = new ZipEntry(filePath.substring(dir.getAbsolutePath().length()+1, filePath.length()));
            zos.putNextEntry(ze);
            //read the file and write to ZipOutputStream
            FileInputStream fis = new FileInputStream(filePath);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) > 0) {
                zos.write(buffer, 0, len);
            }
            zos.closeEntry();
            fis.close();
        }
        zos.close();
        fos.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

private void populateFilesList(File dir) throws IOException {
    File[] files = dir.listFiles();
    for(File file : files){
        if(file.isFile()) filesListInDir.add(file.getAbsolutePath());
        else populateFilesList(file);
    }
}



}
