package com.payroll.hrms.payroll.service;

import java.util.Iterator;
import java.util.List;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;
import com.payroll.hrms.payroll.dataobjects.PaybillDetails;
import com.payroll.hrms.payroll.dataobjects.EmployeePayroll;

public class GeneratePayslip {
	
	public void generatePayslip(int deptId){
		/*EmployeePayrollService payrollService =  new EmployeePayrollService();
		PaybillDetails payrollTotals = payrollService.retreiveEmpsForPayroll(deptId);
		for (Iterator iterator = payrollTotals.getPayrollList().iterator(); iterator.hasNext();) {
			EmployeePayroll employeePayroll = (EmployeePayroll) iterator.next();
			payslipPDF(employeePayroll);
		}*/
		payslipPDF(null);
	}
	
	private void payslipPDF(EmployeePayroll payroll){
		String dest = "C:/RAM/Payroll/payslip/Dec17_"+payroll.getEmployeeId()+".pdf";   
		try{
	      PdfWriter writer = new PdfWriter(dest);       
	         
	      // Creating a PdfDocument object      
	      PdfDocument pdf = new PdfDocument(writer);                  
	      
	      // Creating a Document object       
	      Document doc = new Document(pdf);                       
	         
	      // Creating a table       
	      float [] pointColumnWidths = {150F, 150F, 150F};   
	      Table table = new Table(pointColumnWidths); 
	      table.addCell(new Cell().add("Name"));       
	      table.addCell(new Cell().add("Raju"));       
	      table.addCell(new Cell().add("Id"));       
	      table.addCell(new Cell().add("1001"));       
	      table.addCell(new Cell().add("Designation"));       
	      table.addCell(new Cell().add("Programmer"));                 
	      
	      doc.add(table);                  
	         
	      // Closing the document       
	      doc.close();
	      System.out.println("Table created successfully..");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
