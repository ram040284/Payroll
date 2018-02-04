package com.payroll.pdf.business;

import java.util.Date;
import java.util.List;
import java.util.Map;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.layout.Style;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.payroll.Utils;
import com.payroll.hrms.payroll.dataobjects.EmployeePayroll;
import com.payroll.hrms.payroll.dataobjects.PaybillDetails;
import com.payroll.paybill.vo.PaybillBean;
import com.payroll.pdf.report.BankwiseReport;
import com.payroll.pdf.report.HeadwiseReport;
import com.payroll.pdf.report.MonthlyPdfRep;
import com.payroll.pdf.report.PaybillPdfRep;
import com.payroll.pdf.report.PayslipReport;
 
/**
 * This view class generates a PDF document 'on the fly' based on the data
 * contained in the model.
 * @author www.codejava.net
 *
 */
public class PdfBuilder extends AbstractITextPdfView {
 
    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document doc,
            PdfWriter writer, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // get data model which is passed by the Spring container
    	
    	String imgPath = request.getSession().getServletContext().getRealPath("/resources/images/");
        if(model.get("monthlyDetails") !=null){
        	List<PaybillDetails> monthlyDetails = (List<PaybillDetails>) model.get("monthlyDetails");
        	if(monthlyDetails != null && !monthlyDetails.isEmpty())
        		new MonthlyPdfRep().mthlyCompReport(doc, monthlyDetails, imgPath);
        }
        if(model.get("paybillDetails") !=null){
        	PaybillDetails paybillDetails = (PaybillDetails) model.get("paybillDetails");
        	if(paybillDetails.getPayrollList() != null && !paybillDetails.getPayrollList().isEmpty())
        		new PaybillPdfRep().paybillReport(doc, paybillDetails, imgPath);
        }
        
        if(model.get("headwiseDetails") !=null){
        	List<PaybillDetails> headwiseDetails = (List<PaybillDetails>) model.get("headwiseDetails");
        	if(headwiseDetails != null && !headwiseDetails.isEmpty())
        		new HeadwiseReport().headwireReport(doc, headwiseDetails, imgPath);
        }
        
        if(model.get("bankwiseDetails") !=null){
        	List<PaybillDetails> bankwiseDetails = (List<PaybillDetails>) model.get("bankwiseDetails");
        	if(bankwiseDetails != null && !bankwiseDetails.isEmpty())
        		new BankwiseReport().bankwireReport(doc, bankwiseDetails, imgPath);
        }
        
        if(model.get("payslip") !=null){
        	PaybillDetails payslip = (PaybillDetails) model.get("payslip");
        	if(payslip != null)
        		new PayslipReport().getPayslip(doc, payslip, imgPath);
        }
        
    }
    
    protected PdfPCell addToCell(String value, Font font){
    	return addToCell(value, font, false);
    }
    protected PdfPCell addToCell(String value, Font font, boolean alignRight){
    	PdfPCell cell1 = new PdfPCell();
        cell1.setBorder(0);
        cell1.setPadding(5);
        if(alignRight)
        	cell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell1.setPhrase(new Phrase(value, font));
        return cell1;
    }
    
    protected PdfPTable createPdfPTable(int noOfCols, int spacing, float[] widths){
    	PdfPTable firstLine = null;
    	try{
	    	firstLine = new PdfPTable(noOfCols);
	        firstLine.setWidthPercentage(100.0f);
	        firstLine.setWidths(widths);
	        firstLine.setSpacingBefore(spacing);
	        firstLine.getDefaultCell().setBorder(0);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return firstLine;
    }
 
}