package com.payroll.pdf.report;

import java.util.Date;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import com.payroll.InWords;
import com.payroll.Utils;
import com.payroll.hrms.payroll.dataobjects.Paybill;
import com.payroll.hrms.payroll.dataobjects.PaybillDetails;
import com.payroll.hrms.payroll.dataobjects.ReportDetails;
import com.payroll.pdf.business.Payslip;
import com.payroll.pdf.business.PdfUtils;

public class PayslipReport extends PaybillPdfRep{
	
	public void getPayslip(Document doc, PaybillDetails payslip, String imgPath){
		try{
			String watermarkImg = imgPath+"\\CBK_Logo.png";
	        doc.add(PdfUtils.getWaterMarkImg(watermarkImg));
	        String logoImg = imgPath+"\\logo_new.jpg";
	        doc.add(PdfUtils.getMainHeader(logoImg));
	        
	        DottedLineSeparator dottedline = new DottedLineSeparator();
	        dottedline.setOffset(-2);
	        dottedline.setGap(2f);
	        
	        Font frtHdFont = FontFactory.getFont(FontFactory.HELVETICA);
	        frtHdFont.setSize(8);
	        StringBuffer titleText = new StringBuffer("Paybill of Permanent/Temporary Establishment ");
	        titleText.append("for the Month of ");
	        Date month = (payslip.getMonth()!= null) ? payslip.getMonth() : new Date();
	        titleText.append(Utils.getMonthYear(month));
	        PdfPTable paybillTab = null;
	        float paybillTabW[] = {7f};
	        paybillTab = createPdfPTable(1, 0, paybillTabW);
	        paybillTab.setHorizontalAlignment(Element.ALIGN_CENTER);
	        paybillTab.addCell(addToCell(titleText.toString(), headHdFont, false));
	        doc.add(dottedline);
	        doc.add(paybillTab);
	        doc.add(dottedline);
	        
	        float psDetTabW[] = {2f, 4f};
	        PdfPTable psDetTab = createPdfPTable(2, 0, psDetTabW);
	        
	        
	        float payslipTabW[] = {0.8f, 1.2f};
	        PdfPTable part1Tab = createPdfPTable(2, 0, payslipTabW);
	        
	        ReportDetails repDetails = payslip.getPayrollList().get(0);
	        part1Tab.addCell(addToCell(PdfUtils.EMPNO, headHdFont, false));
	        part1Tab.addCell(addToCell(repDetails.getEmployeeNumber(), frtHdFont, false));
	        part1Tab.addCell(addToCell(PdfUtils.DEPARTMENT, headHdFont, false));
	        part1Tab.addCell(addToCell(payslip.getDeptName(), frtHdFont, false));
	        part1Tab.addCell(addToCell(PdfUtils.BIRTH_DATE, headHdFont, false));
	        part1Tab.addCell(addToCell(repDetails.getDob(), frtHdFont, false));
	        part1Tab.addCell(addToCell(PdfUtils.APPOINTMENT_DATE, headHdFont, false));
	        part1Tab.addCell(addToCell(repDetails.getJoiningDate(), frtHdFont, false));
	        psDetTab.addCell(part1Tab);
	        float psPart2_1TabW[] = {0.8f, 3.2f};
	       PdfPTable empNameTab = createPdfPTable(2, 0, psPart2_1TabW);
	       //empNameTab = createPdfPTable(2, 5, payslipTabW);
	       //empNameTab.getDefaultCell().setBorder(1);
	       empNameTab.addCell(addToCell(PdfUtils.EMPNAME, headHdFont, false));
	       empNameTab.addCell(addToCell(repDetails.getEmployeeName(), frtHdFont, false));
	        //psDetTab.addCell(part1Tab);
	        float psPart2TabW[] = {2f, 2f};
	        PdfPTable part2Tab = createPdfPTable(2, 0, psPart2TabW);
	        
	        float psPart2_2TabW[] = {0.8f, 1.2f};
	        PdfPTable part2_1Tab = createPdfPTable(2, 0, psPart2_2TabW);
	        
	        part2_1Tab.addCell(addToCell(PdfUtils.HEAD, headHdFont, false));
	        part2_1Tab.addCell(addToCell(repDetails.getDeptCostHead(), frtHdFont, false));
	        part2_1Tab.addCell(addToCell(PdfUtils.DESIGNATION, headHdFont, false));
	        part2_1Tab.addCell(addToCell(repDetails.getDesignation(), frtHdFont, false));
	        part2_1Tab.addCell(addToCell(PdfUtils.GENDER, headHdFont, false));
	        String gender = !Utils.isEmpty(repDetails.getGender()) ? repDetails.getGender().toUpperCase() : repDetails.getGender();
	        part2_1Tab.addCell(addToCell(gender , frtHdFont, false));
	        
	        PdfPTable part2_2Tab = createPdfPTable(2, 0, psPart2_2TabW);
	        part2_2Tab.addCell(addToCell(PdfUtils.BANK_ACCTNO, headHdFont, false));
	        part2_2Tab.addCell(addToCell(repDetails.getBankAcctNo(), frtHdFont, false));
	        part2_2Tab.addCell(addToCell(PdfUtils.PAN, headHdFont, false));
	        part2_2Tab.addCell(addToCell(repDetails.getPanNo(), frtHdFont, false));
	        part2_2Tab.addCell(addToCell(PdfUtils.PF_NO, headHdFont, false));
	        part2_2Tab.addCell(addToCell("", frtHdFont, false));
	        //part2Tab.addCell(part2_1Tab);
	        part2Tab.addCell(part2_1Tab);
	        part2Tab.addCell(part2_2Tab);
	        
	        float psPartTabW[] = {4f};
	        PdfPTable partTab2 = createPdfPTable(1, 0, psPartTabW);
	        partTab2.addCell(empNameTab);
	        partTab2.addCell(part2Tab);
	        psDetTab.addCell(partTab2);
	        doc.add(psDetTab);
	        doc.add(dottedline);
	        float psPayDetTabW[] = {3f, 3f};
	        PdfPTable psPayDetTab = createPdfPTable(2, 0, psPayDetTabW);
	        psPayDetTab.addCell(addToCell(PdfUtils.EARNINGS, headHdFont, false));
	        psPayDetTab.addCell(addToCell(PdfUtils.DEDUCTIONS, headHdFont, false));
	        doc.add(psPayDetTab);
	        doc.add(dottedline);
	        psPayDetTab = createPdfPTable(2, 0, psPayDetTabW);
	        float psEarTabW[] = {1.5f, 1.5f};
	        PdfPTable psEarTab = createPdfPTable(2, 0, psEarTabW);
	        addTotalDetails(psEarTab, payslip, frtHdFont);
	        PdfPTable psDeductionTab = createPdfPTable(2, 0, psEarTabW);
	        addDeductionTotals(psDeductionTab, payslip, frtHdFont);
	        psPayDetTab.addCell(psEarTab);
	        psPayDetTab.addCell(psDeductionTab);
	        doc.add(psPayDetTab);
	        doc.add(dottedline);
	        psPayDetTab = createPdfPTable(2, 0, psPayDetTabW);
	        psEarTab = createPdfPTable(2, 1, psEarTabW);
	        psEarTab.addCell(addToCell(PdfUtils.EARNINGSTOT, headHdFont, false));
	        psEarTab.addCell(addToCell(payslip.getTotalGrossPay()+"", headHdFont, false));
	        psPayDetTab.addCell(psEarTab);
	        psEarTab = createPdfPTable(2, 1, psEarTabW);
	        psEarTab.addCell(addToCell(PdfUtils.DEDUCTIONSTOT, headHdFont, false));
	        psEarTab.addCell(addToCell(payslip.getTotalDeductions()+"", headHdFont, false));
	        psPayDetTab.addCell(psEarTab);
	        doc.add(psPayDetTab);
	        doc.add(dottedline);
	        float psInWordsTxtTabW[] = {1f, 5f};
	        psPayDetTab = createPdfPTable(2, 0, psInWordsTxtTabW);
	        psPayDetTab.addCell(addToCell(PdfUtils.NETPAY+" "+payslip.getNetPay(), headHdFont, false));
	        StringBuffer inWordsTxt = new StringBuffer(PdfUtils.IN_WORDS);
	        inWordsTxt.append(" ");
	        inWordsTxt.append(InWords.getInWords(payslip.getNetPay()));
	        psPayDetTab.addCell(addToCell(inWordsTxt.toString(), headHdFont, false));
	        doc.add(psPayDetTab);
	        doc.add(dottedline);
	        
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	protected PdfPCell addToCell(String value, Font font, boolean alignRight){
    	PdfPCell cell1 = new PdfPCell();
        cell1.setBorder(PdfPCell.NO_BORDER);
        cell1.setPaddingBottom(5);
        cell1.setPaddingTop(5);
        
        if(alignRight)
        	cell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
       /* else 
        	cell1.setHorizontalAlignment(Element.ALIGN_CENTER);*/
        cell1.setPhrase(new Phrase(value, font));
        return cell1;
    }
	
	protected PdfPTable createPdfPTable(int noOfCols, int border, float[] widths){
    	PdfPTable firstLine = null;
    	try{
	    	firstLine = new PdfPTable(noOfCols);
	        firstLine.setWidthPercentage(100.0f);
	        firstLine.setWidths(widths);
	        firstLine.setSpacingBefore(5);
	        firstLine.getDefaultCell().setBorder(border);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return firstLine;
    }

}
