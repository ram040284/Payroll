package com.payroll;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument; 
import com.itextpdf.kernel.pdf.PdfWriter; 

import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;
import com.payroll.hrms.payroll.service.GeneratePayslip;  

public class ITextTest {      
	
	public static Image getWaterMarkImg(String imagePath){
		Image wmarkimge = null;
		try{
		 String waterMarkImg = imagePath+"CBK_Logo.png";       
	      ImageData waterImgdata = ImageDataFactory.create(waterMarkImg);              
	      wmarkimge = new Image(waterImgdata);    
	      wmarkimge.scaleToFit(400, 500);
	      wmarkimge.setFixedPosition(80,250);
		}catch(Exception e){
			e.printStackTrace();
		}
		return wmarkimge;
	}
	public static Table getMainHeader(String imagePath){
		Table head1_tbl = null;
		try{
			float [] mainHeaderColWidth = {80F,20F, 400F};
		      head1_tbl = new Table(mainHeaderColWidth);
		      float[] headTextWidth = {350F};
		      Table headTextTab = new Table(headTextWidth);
		      Cell headTxtCell = new Cell();                        
		      headTxtCell.add("Khadki Cantonment Board");
		      headTxtCell.setFontSize(15);   
		      headTxtCell.setBorder(Border.NO_BORDER);   
		      headTxtCell.setTextAlignment(TextAlignment.LEFT);
		      Cell address1 = new Cell();                  
		      address1.add("Golibar Maidan, Pune - Solapur Road,");
		      address1.setFontSize(10); 
		      address1.setBorder(Border.NO_BORDER);   
		      address1.setTextAlignment(TextAlignment.LEFT);
		      Cell address2 = new Cell();                  
		      address2.add("Pune, Maharashtra 411001");
		      address2.setFontSize(10); 
		      address2.setBorder(Border.NO_BORDER);
		      address2.setTextAlignment(TextAlignment.LEFT);
		      
		      //c1.setBackgroundColor(Color.DARK_GRAY);
		      headTextTab.addCell( new Cell().setBorder(Border.NO_BORDER));
		      headTextTab.addCell(headTxtCell);
		      headTextTab.addCell(address1);
		      headTextTab.addCell(address2);
		      Cell headCell = new Cell();
		      headCell.setBorder(Border.NO_BORDER);              
		      headCell.setTextAlignment(TextAlignment.CENTER);
		      headCell.add(headTextTab);
		      Cell leftLogo = new Cell();   
		      leftLogo.setBorder(Border.NO_BORDER);              // Setting border
		      leftLogo.setTextAlignment(TextAlignment.CENTER);   // Setting text alignment      
		      String imageFile = imagePath+"logo_new.jpg";       
		      ImageData data = ImageDataFactory.create(imageFile);        
		      Image img = new Image(data);   
		      img.scaleToFit(80, 65);
		      leftLogo.add(img.setAutoScale(true));
		      Cell rightLogo = new Cell();              
		      rightLogo.setBorder(Border.NO_BORDER);              // Setting border
		      rightLogo.setTextAlignment(TextAlignment.CENTER);   // Setting text alignment      
		      String rightImage = imagePath+"Emblem.png";       
		      ImageData rightImgData = ImageDataFactory.create(rightImage);        
		      Image rightImg = new Image(rightImgData); 
		      rightImg.scaleToFit(70, 55);
		      rightLogo.add(rightImg.setAutoScale(true));
		      
		      head1_tbl.addCell(leftLogo);
		      head1_tbl.addCell( new Cell().setBorder(Border.NO_BORDER));
		      head1_tbl.addCell(headCell);
		      
		}catch(Exception e){
			e.printStackTrace();
		}
		return head1_tbl;
	}
   public static void main(String args[]) throws Exception {           
      // Creating a PdfDocument object
	   String imagePath="C:/RAM/Mis/";
      String dest = "C:/RAM/Mis/addingTable.pdf";   
      PdfWriter writer = new PdfWriter(dest);       
         
      // Creating a PdfDocument object      
      PdfDocument pdf = new PdfDocument(writer);                  
      
      // Creating a Document object       
      Document doc = new Document(pdf);          
     /* String waterMarkImg = imagePath+"CBK_Logo.png";       
      ImageData waterImgdata = ImageDataFactory.create(waterMarkImg);              
      Image wmarkimge = new Image(waterImgdata);    
      wmarkimge.scaleToFit(400, 500);
      wmarkimge.setFixedPosition(80,250);*/
      
      doc.add(getWaterMarkImg(imagePath+"CBK_Logo.png")); 
      //float [] mainHeaderColWidth = {100F, 350F, 100F};
     /* float [] mainHeaderColWidth = {80F,20F, 400F};
      Table head1_tbl = new Table(mainHeaderColWidth);*/
      Style commonStyle = new Style();
      commonStyle.setFontSize(10);
      commonStyle.setBold();
      Style empInfoStyle = new Style();
      empInfoStyle.setFontSize(10);
      
     
      // Populating row 1 and adding it to the table
      /*float[] headTextWidth = {350F};
      Table headTextTab = new Table(headTextWidth);
      Cell headTxtCell = new Cell();                        
      headTxtCell.add("Khadki Cantonment Board");
      headTxtCell.setFontSize(15);   
      headTxtCell.setBorder(Border.NO_BORDER);   
      headTxtCell.setTextAlignment(TextAlignment.LEFT);
      Cell address1 = new Cell();                  
      address1.add("Golibar Maidan, Pune - Solapur Road,");
      address1.setFontSize(10); 
      address1.setBorder(Border.NO_BORDER);   
      address1.setTextAlignment(TextAlignment.LEFT);
      Cell address2 = new Cell();                  
      address2.add("Pune, Maharashtra 411001");
      address2.setFontSize(10); 
      address2.setBorder(Border.NO_BORDER);
      address2.setTextAlignment(TextAlignment.LEFT);
      
      //c1.setBackgroundColor(Color.DARK_GRAY);
      headTextTab.addCell( new Cell().setBorder(Border.NO_BORDER));
      headTextTab.addCell(headTxtCell);
      headTextTab.addCell(address1);
      headTextTab.addCell(address2);
      
      Cell headCell = new Cell();
      headCell.setBorder(Border.NO_BORDER);              
      headCell.setTextAlignment(TextAlignment.CENTER);
      headCell.add(headTextTab);
      
      Cell leftLogo = new Cell();   
      leftLogo.setBorder(Border.NO_BORDER);              // Setting border
      leftLogo.setTextAlignment(TextAlignment.CENTER);   // Setting text alignment      
      String imageFile = imagePath+"logo_new.jpg";       
      ImageData data = ImageDataFactory.create(imageFile);        
      Image img = new Image(data);   
      img.scaleToFit(80, 65);
      leftLogo.add(img.setAutoScale(true));
      
      Cell rightLogo = new Cell();              
      rightLogo.setBorder(Border.NO_BORDER);              // Setting border
      rightLogo.setTextAlignment(TextAlignment.CENTER);   // Setting text alignment      
      String rightImage = imagePath+"Emblem.png";       
      ImageData rightImgData = ImageDataFactory.create(rightImage);        
      Image rightImg = new Image(rightImgData); 
      rightImg.scaleToFit(70, 55);
      rightLogo.add(rightImg.setAutoScale(true));
      
      head1_tbl.addCell(leftLogo);
      head1_tbl.addCell( new Cell().setBorder(Border.NO_BORDER));
      head1_tbl.addCell(headCell);*/
      //head1_tbl.addCell(rightLogo);
      
      float[] monthTextWidth = {600F};
      Table monthTextTab = new Table(monthTextWidth);
      Cell monthTxtCell = new Cell();    
      monthTxtCell.setHeight(40);
      monthTxtCell.setBold().add("PAYSLIP FOR THE MONTH OF "+Utils.getMonthYear(new Date()).toUpperCase());
      monthTxtCell.setFontSize(12);   
      monthTxtCell.setBorder(Border.NO_BORDER);   
      monthTxtCell.setTextAlignment(TextAlignment.CENTER);
      monthTextTab.addCell(monthTxtCell);
      
      float [] pointColumnWidths = {150F, 200F, 150F, 250F};   
      Table table = new Table(pointColumnWidths);    
      // Adding cells to the table       
      table.addCell(new Cell().add("Emp Code").addStyle(empInfoStyle));       
      table.addCell(new Cell().add("1001").addStyle(commonStyle));       
      table.addCell(new Cell().add("Emp Name").addStyle(empInfoStyle));
      table.addCell(new Cell().add("Raju").addStyle(commonStyle));
      table.addCell(new Cell().add("Department").addStyle(empInfoStyle));
      table.addCell(new Cell().add("Business").addStyle(commonStyle));
      table.addCell(new Cell().add("Designation").addStyle(empInfoStyle));       
      table.addCell(new Cell().add("Programmer").addStyle(commonStyle));                 
      
      table.addCell(new Cell().add("Date of Birth").addStyle(empInfoStyle));       
      table.addCell(new Cell().add("04/02/1984").addStyle(commonStyle));       
      table.addCell(new Cell().add("Jaoining Date").addStyle(empInfoStyle));
      table.addCell(new Cell().add("12/12/2015").addStyle(commonStyle));
      table.addCell(new Cell().add("Gender").addStyle(empInfoStyle));
      table.addCell(new Cell().add("Male").addStyle(commonStyle));
      table.addCell(new Cell().add("Bank a/c").addStyle(empInfoStyle));       
      table.addCell(new Cell().add("89796786786").addStyle(commonStyle));                 
      table.addCell(new Cell().add("Pan No.").addStyle(empInfoStyle));
      table.addCell(new Cell().add("6778675677567").addStyle(commonStyle));
      table.addCell(new Cell().add("PF No.").addStyle(empInfoStyle));       
      table.addCell(new Cell().add("89796786786").addStyle(commonStyle));                 
      
      float [] emptyTabColumn = {750f};   
      Table emptyTab = new Table(emptyTabColumn);    
      emptyTab.addCell(new Cell().setHeight(20).setBorder(Border.NO_BORDER));
      
      Style earHeadstyle = new Style();
      earHeadstyle.setBold();
      earHeadstyle.setBorder(Border.NO_BORDER);
      earHeadstyle.setFontSize(10);
      Style earStyle = new Style();
      earStyle.setBorder(Border.NO_BORDER);
      //earStyle.setBorderTop(Border.NO_BORDER);
      earStyle.setFontSize(10);
      
      float [] earDeductColWidths = {250F, 100F};   
      Table earHeadTab = new Table(earDeductColWidths);    
      //earHeadTab.addStyle(earHeadstyle);
      Cell amtHeadCell = new Cell().add("Amount").addStyle(earHeadstyle);
      earHeadTab.addCell(new Cell().add("Earnings").addStyle(earHeadstyle));
      earHeadTab.addCell(amtHeadCell);
      Table deductHeadTab = new Table(earDeductColWidths);    
      deductHeadTab.addStyle(earHeadstyle);
      
      deductHeadTab.addCell(new Cell().add("Deductions").addStyle(earHeadstyle));
      deductHeadTab.addCell(amtHeadCell);
      
      Table earTab = new Table(earDeductColWidths);
      earTab.addStyle(earStyle);
      earTab.addCell(new Cell().add("Basic").addStyle(earStyle));
      earTab.addCell(new Cell().add("1000").addStyle(earStyle));
      earTab.addCell(new Cell().add("House Rent Allowance").addStyle(earStyle));
      earTab.addCell(new Cell().add("5000").addStyle(earStyle));
      Table totEarTab = new Table(earDeductColWidths);
      totEarTab.addCell(new Cell().add("Total Earnings").addStyle(earHeadstyle));
      totEarTab.addCell(new Cell().add("6000").addStyle(earHeadstyle));
      
      Table deductTab = new Table(earDeductColWidths);
      deductTab.addStyle(earStyle);
      deductTab.addCell(new Cell().add("Provident Fund").addStyle(earStyle));
      deductTab.addCell(new Cell().add("800").addStyle(earStyle));
      deductTab.addCell(new Cell().add("Professional Tax").addStyle(earStyle));
      deductTab.addCell(new Cell().add("200").addStyle(earStyle));
      Table totDeductTab = new Table(earDeductColWidths);
      totDeductTab.addCell(new Cell().add("Total Deductions").addStyle(earHeadstyle));
      totDeductTab.addCell(new Cell().add("1000").addStyle(earHeadstyle));
      
      
      
      Style earDeductStyle = new Style();
      earDeductStyle.setBorder(Border.NO_BORDER);
      float [] earDeductTabCols = {350F, 350F};
      Table earDeductTab = new Table(earDeductTabCols);
      //earDeductTab.addStyle(earDeductStyle);
      earDeductTab.addCell(earHeadTab).addStyle(earDeductStyle);
      earDeductTab.addCell(deductHeadTab).addStyle(earDeductStyle);
      earDeductTab.addCell(earTab).addStyle(earDeductStyle);
      earDeductTab.addCell(deductTab).addStyle(earDeductStyle);
      earDeductTab.addCell(totEarTab);
      earDeductTab.addCell(totDeductTab);
      Table totAmtTab = new Table(emptyTabColumn);  
      double netPay = 10143.00;
      totAmtTab.addCell(new Cell().add("Net Pay     "+netPay).addStyle(commonStyle));
      totAmtTab.addCell(new Cell().add("In Words    Rupees "+InWords.getInWords(netPay)).addStyle(commonStyle));
      totAmtTab.addCell(new Cell().addStyle(commonStyle).setHeight(17));
      
      float [] workingDaysTabCols = {140F, 140F, 140F, 140F, 140F};
      Table workingDaysTab = new Table(workingDaysTabCols); 
      workingDaysTab.addCell(new Cell().add("Days in Month (A)").addStyle(commonStyle));
      workingDaysTab.addCell(new Cell().add("Arrear Days (B)").addStyle(commonStyle));
      workingDaysTab.addCell(new Cell().add("LOPR Days (C)").addStyle(commonStyle));
      workingDaysTab.addCell(new Cell().add("LOP Days (D)").addStyle(commonStyle));
      workingDaysTab.addCell(new Cell().add("Net Days Worked (A+B+C-D)").addStyle(commonStyle));
      String daysInMonth = Utils.daysInMonth();//""+30;
      workingDaysTab.addCell(new Cell().add(daysInMonth).addStyle(empInfoStyle));
      workingDaysTab.addCell(new Cell().add("0.0").addStyle(empInfoStyle));
      workingDaysTab.addCell(new Cell().add("0.0").addStyle(empInfoStyle));
      workingDaysTab.addCell(new Cell().add("0.0").addStyle(empInfoStyle));
      workingDaysTab.addCell(new Cell().add(daysInMonth).addStyle(empInfoStyle));
      
      doc.add(getMainHeader(imagePath+"logo_new.jpg"));
      doc.add(monthTextTab);
      doc.add(table);                  
      doc.add(emptyTab);   
      doc.add(earDeductTab);
      doc.add(emptyTab);   
      doc.add(totAmtTab);
      doc.add(emptyTab);
      doc.add(workingDaysTab);
      // Closing the document       
      doc.close();
	  /* GeneratePayslip payslip = new GeneratePayslip();
	   payslip.generatePayslip(1);*/
      
      System.out.println("Table created successfully..");   
   }
   
}