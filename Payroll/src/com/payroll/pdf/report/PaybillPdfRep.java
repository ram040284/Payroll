package com.payroll.pdf.report;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import com.payroll.Utils;
import com.payroll.hrms.payroll.dataobjects.EmployeePayroll;
import com.payroll.hrms.payroll.dataobjects.PaybillDetails;
import com.payroll.hrms.payroll.dataobjects.ReportDetails;
import com.payroll.pdf.business.PdfBuilder;
import com.payroll.pdf.business.PdfUtils;

public class PaybillPdfRep extends PdfBuilder{
	int srNo = 0;
	Font boldFont = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.BOLD);
	Font headHdFont = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD);
    
	public void paybillReport(Document doc, PaybillDetails paybillDetails, String imgPath){
		try{
			String watermarkImg = imgPath+"\\CBK_Logo.png";//request.getSession().getServletContext().getRealPath("/resources/images/CBK_Logo.png");
	        doc.add(PdfUtils.getWaterMarkImg(watermarkImg));
	        String logoImg = imgPath+"\\logo_new.jpg";
	        doc.add(PdfUtils.getMainHeader(logoImg));
	        
	        DottedLineSeparator dottedline = new DottedLineSeparator();
	        dottedline.setOffset(-2);
	        dottedline.setGap(2f);
	        doc.add(dottedline);
	        
	        Font frtHdFont = FontFactory.getFont(FontFactory.HELVETICA);
	        frtHdFont.setSize(7);
	        //Date date = new Date();
	        String deptName = Utils.safeTrim(paybillDetails.getDeptName());//"Civil Cons.";
	        StringBuffer titleText = new StringBuffer("Paybill of Permanent/Temporary Establishment ");
	        titleText.append("for the Month of ");
	        titleText.append(Utils.getMonthYear(paybillDetails.getMonth()));
	        PdfPTable paybillTab = null;
	        float paybillTabW[] = {1.5f, 4.5f, 1.0f};
	        paybillTab = createPdfPTable(3, 5, paybillTabW);
	        paybillTab.setHorizontalAlignment(Element.ALIGN_CENTER);
	        
	        paybillTab.addCell(addToCell(deptName.toUpperCase(), headHdFont));
	        paybillTab.addCell(addToCell(titleText.toString(), headHdFont));
	        paybillTab.addCell(addToCell(Utils.getSimpleDate(new Date()), headHdFont));
	        doc.add(paybillTab);
	        doc.add(dottedline);
	        
	        PdfPTable edTab = null;
	        PdfPTable grossTab = null;
	        PdfPTable deductTab1 = null;
	        PdfPTable deductTab2 = null;
	        float edTabWidths[] = {1.5f, 2.0f};
	        float grossTabWidths[] = {1f, 1f};
	        float deductTab1Widths[] = {1.4f, 0.9f};
	        float deductTab2Widths[] = {1.5f, 0.9f};
	        float tabWidths[] = {2.8f, 2.2f, 1.9f, 2.2f};
	        PdfPTable paybillTab2 = null; 
	        for (Iterator iterator = paybillDetails.getPayrollList().iterator(); iterator.hasNext();) {
				ReportDetails employeePayroll = (ReportDetails) iterator.next();
				srNo++;
				edTab = createPdfPTable(2, 5, edTabWidths);
				grossTab = createPdfPTable(2, 5, grossTabWidths);
				deductTab1 = createPdfPTable(2, 5, deductTab1Widths);
				deductTab2 = createPdfPTable(2, 5, deductTab2Widths);
				addDetails(edTab, employeePayroll, frtHdFont);
				addGrossDetails(grossTab, employeePayroll, frtHdFont);
				addDeduct1Details(deductTab1, employeePayroll, frtHdFont);
				addDeduct2Details(deductTab2, employeePayroll, frtHdFont);
				paybillTab2 = createPdfPTable(4, 5, tabWidths);
				paybillTab2.addCell(edTab);
				paybillTab2.addCell(grossTab);
				paybillTab2.addCell(deductTab1);
				paybillTab2.addCell(deductTab2);
				doc.add(paybillTab2);
		        doc.add(dottedline);
		        doc.add(addTotals(employeePayroll.getGrossPay()+"", employeePayroll.getTotalDeductions()+"",
		        		frtHdFont, tabWidths));
		        doc.add(addNetPay(employeePayroll.getNetPay()+"", frtHdFont, tabWidths));
		        doc.add(dottedline);
	        }
	        //Adding totals per Department
	        PdfPTable pbDtlsTab = null;
	        float pbDtlsTabW[] = {7f};
	        pbDtlsTab = createPdfPTable(1, 3, pbDtlsTabW);
	        pbDtlsTab.setHorizontalAlignment(Element.ALIGN_LEFT);
	        pbDtlsTab.addCell(addToCell(PdfUtils.INCOME_SIDE, headHdFont));
	        doc.add(pbDtlsTab);
	        
	        float pbDtls1TabW[] = {0.6f, 0.7f, 0.8f, 0.7f, 0.8f, 0.7f, 0.8f, 0.6f, 0.5f, 0.6f, 0.7f, 0.6f, 0.6f, 0.6f};
	        pbDtlsTab = createPdfPTable(14, 3, pbDtls1TabW);
	        pbDtlsTab.setHorizontalAlignment(Element.ALIGN_LEFT);
	        addTotalDetails(pbDtlsTab, paybillDetails, frtHdFont);
	        doc.add(pbDtlsTab);
	        doc.add(dottedline);
	        pbDtlsTab = createPdfPTable(1, 3, pbDtlsTabW);
	        pbDtlsTab.setHorizontalAlignment(Element.ALIGN_LEFT);
	        pbDtlsTab.addCell(addToCell(PdfUtils.DEDUCT_SIDE, headHdFont));
	        doc.add(pbDtlsTab);
	      
	        pbDtlsTab = createPdfPTable(14, 3, pbDtls1TabW);
	        pbDtlsTab.setHorizontalAlignment(Element.ALIGN_LEFT);
	        addDeductionTotals(pbDtlsTab, paybillDetails, frtHdFont);
	        doc.add(pbDtlsTab);
	        doc.add(dottedline);
	        
	        float pbTotDtlsTabW[] = {2.5f, 2.5f, 2.5f};
	        pbDtlsTab = createPdfPTable(3, 5, pbTotDtlsTabW);
	        pbDtlsTab.setHorizontalAlignment(Element.ALIGN_CENTER);
	        pbDtlsTab.addCell(addToCell(PdfUtils.GROSS+" "+paybillDetails.getTotalGrossPay(), headHdFont));
	        pbDtlsTab.addCell(addToCell(PdfUtils.TOTDEDUCTION+" "+paybillDetails.getTotalDeductions(), headHdFont));
	        pbDtlsTab.addCell(addToCell(PdfUtils.NETPAY+" "+paybillDetails.getNetPay(), headHdFont));
	        doc.add(pbDtlsTab);
	        doc.add(dottedline);
	      
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	private PdfPTable addNetPay(String netPay, Font font, float[] tabWidths){
		Font localFont = font;
		PdfPTable table = null;
		table = createPdfPTable(4, 5, tabWidths);
		table.setSpacingBefore(2);
		table.addCell("");
		table.addCell("");
		table.addCell("");
		//localFont.setStyle(Font.BOLD);
		float netpayWidths[] = {1.5f, 1f};
		PdfPTable netpayTab = createPdfPTable(2, 5, netpayWidths);
		netpayTab.addCell(addToCell(PdfUtils.NETPAY, boldFont));
		netpayTab.addCell(addToCell(netPay+"", boldFont));
		table.addCell(netpayTab);
		return table;
	}
	private PdfPTable addTotals(String grossPay, String totDeductions, Font font, float[] tabWidths){
		PdfPTable table = null;
		table = createPdfPTable(4, 5, tabWidths);
		table.addCell("");
		float totWidths[] = {1.5f, 1f};
		PdfPTable totTab = createPdfPTable(2, 5, totWidths);
		totTab.addCell(addToCell(PdfUtils.GROSS, boldFont));
		totTab.addCell(addToCell(grossPay+"", boldFont));
		table.addCell(totTab);
		table.addCell("");
		//float totDeductWidths[] = {1.5f, 1f};
		totTab = createPdfPTable(2, 5, totWidths);
		totTab.addCell(addToCell(PdfUtils.TOTDEDUCTION, boldFont));
		totTab.addCell(addToCell(totDeductions+"", boldFont));
		table.addCell(totTab);
		return table;
	}
	
	private void addDeduct1Details(PdfPTable table, ReportDetails payroll, Font font){
		table.addCell(addToCell(PdfUtils.L_FEES, font));
		table.addCell(addToCell(payroll.getLfee()+"", font));
		table.addCell(addToCell(PdfUtils.AFKRENT, font));
		table.addCell(addToCell(payroll.getAfkRent()+"", font));
		
		table.addCell(addToCell(PdfUtils.ABSDED, font));
		table.addCell(addToCell(payroll.getAbsentAmount()+"", font));
		table.addCell(addToCell(PdfUtils.FAREC, font));
		table.addCell(addToCell(payroll.getFestAdvRecovery()+"", font));
		
		table.addCell(addToCell(PdfUtils.PTAX, font));
		table.addCell(addToCell(payroll.getProfTax()+"", font));
		table.addCell(addToCell(PdfUtils.LIC, font));
		table.addCell(addToCell(payroll.getLic()+"", font));
		
		table.addCell(addToCell(PdfUtils.SOCIETY, font));
		table.addCell(addToCell(payroll.getSocietyInstallment()+"", font));
		table.addCell(addToCell(PdfUtils.GIS, font));
		table.addCell(addToCell(payroll.getGrpInsurance()+"", font));
		
		table.addCell(addToCell(PdfUtils.BANKLOANREC, font));
		table.addCell(addToCell(payroll.getBankLoanRcry()+"", font));
		table.addCell(addToCell(PdfUtils.VEHLOANREC, font));
		table.addCell(addToCell(payroll.getVehclLoanRcry()+"", font));
		
	}
	
	private void addDeduct2Details(PdfPTable table, ReportDetails payroll, Font font){
		table.addCell(addToCell(PdfUtils.PFSCPFS, font));
		table.addCell(addToCell(payroll.getProvidentFund()+"", font));
		table.addCell(addToCell(PdfUtils.APFACPF, font));
		table.addCell(addToCell(payroll.getApfacpf()+"", font));
		
		table.addCell(addToCell(PdfUtils.PFLOANREC, font));
		table.addCell(addToCell(payroll.getPfLoanRecovery()+"", font));
		table.addCell(addToCell(PdfUtils.CPFREC, font));
		table.addCell(addToCell(payroll.getCpfRecovery()+"", font));
		
		table.addCell(addToCell(PdfUtils.INCTAX, font));
		table.addCell(addToCell(payroll.getIncomeTax()+"", font));
		table.addCell(addToCell(PdfUtils.UNIONFEE, font));
		table.addCell(addToCell(payroll.getUnionFee()+"", font));
		
		table.addCell(addToCell(PdfUtils.ELECREC, font));
		table.addCell(addToCell(payroll.getElectricityRecovery()+"", font));
		table.addCell(addToCell(PdfUtils.COURTREC, font));
		table.addCell(addToCell(payroll.getCourtRecovery()+"", font));
		
		table.addCell(addToCell(PdfUtils.OTHERDED, font));
		table.addCell(addToCell(payroll.getOtherDeductions()+"", font));
		table.addCell(addToCell(PdfUtils.MISC, font));
		table.addCell(addToCell("0.00", font));
		
		table.addCell(addToCell(PdfUtils.UNFEE_KSS, font));
		table.addCell(addToCell(payroll.getUnionFeeKss()+"", font)); // Kss fee yet to add
		table.addCell(addToCell(PdfUtils.PFINST, font));
		//table.addCell(addToCell(payroll.getPfInstment()+"", font));
		table.addCell(addToCell("0.00", font));
		
		//table.addCell(addToCell(PdfUtils.TOTDEDUCTION, font));
		//table.addCell(addToCell(payroll.getTotalDeductions()+"", font));
		
	}

	
	private void addGrossDetails(PdfPTable table, ReportDetails payroll, Font font){
		table.addCell(addToCell(PdfUtils.BASICS, font));
		table.addCell(addToCell(payroll.getBasic()+"", font));
		table.addCell(addToCell(PdfUtils.GRADEPAY, font));
		table.addCell(addToCell(payroll.getGradePay()+"", font));
		
		table.addCell(addToCell(PdfUtils.DA, font));
		table.addCell(addToCell(payroll.getDearnessAllowance()+"", font));
		table.addCell(addToCell(PdfUtils.HRA, font));
		table.addCell(addToCell(payroll.getHouseRentAllowance()+"", font));
		
		table.addCell(addToCell(PdfUtils.CCA, font));
		table.addCell(addToCell(payroll.getCca()+"", font));
		table.addCell(addToCell(PdfUtils.TA, font));
		table.addCell(addToCell(payroll.getTravelAllowance()+"", font));
		
		table.addCell(addToCell(PdfUtils.NPA, font));
		table.addCell(addToCell(payroll.getNonPracticingAllowance()+"", font));
		table.addCell(addToCell(PdfUtils.WA, font));
		table.addCell(addToCell(payroll.getWashingAllowance()+"", font));
		
		table.addCell(addToCell(PdfUtils.CA, font));
		table.addCell(addToCell(payroll.getConveyanceAllowance()+"", font));
		table.addCell(addToCell(PdfUtils.UNIFORMALW, font));
		table.addCell(addToCell(payroll.getUniformAllowance()+"", font));
		
		table.addCell(addToCell(PdfUtils.FLYPLGALW, font));
		table.addCell(addToCell(payroll.getFamilyPlanningAllowance()+"", font));
		table.addCell(addToCell(PdfUtils.TALW, font));
		table.addCell(addToCell(payroll.getTotalAllowance()+"", font));
		
		table.addCell(addToCell(PdfUtils.OTAMT, font));
		table.addCell(addToCell(payroll.getOverTimeAmount()+"", font));
		table.addCell(addToCell(PdfUtils.OTHERS, font));
		table.addCell(addToCell("0.00", font));
		
		//table.addCell(addToCell(PdfUtils.GROSS, font));
		//table.addCell(addToCell(payroll.getGrossPay()+"", font));
		
	}
	
	protected void addTotalDetails(PdfPTable table, PaybillDetails payroll, Font font){
		table.addCell(addToCell(PdfUtils.BASICS, font));
		table.addCell(addToCell(payroll.getBasicPay()+"", font));
		table.addCell(addToCell(PdfUtils.GRADEPAY, font));
		table.addCell(addToCell(payroll.getGradePay()+"", font));
		
		table.addCell(addToCell(PdfUtils.DA, font));
		table.addCell(addToCell(payroll.getDa()+"", font));
		table.addCell(addToCell(PdfUtils.HRA, font));
		table.addCell(addToCell(payroll.getHra()+"", font));
		
		table.addCell(addToCell(PdfUtils.CCA, font));
		table.addCell(addToCell(payroll.getCca()+"", font));
		table.addCell(addToCell(PdfUtils.TA, font));
		table.addCell(addToCell(payroll.getTa()+"", font));
		
		table.addCell(addToCell(PdfUtils.NPA, font));
		table.addCell(addToCell(payroll.getNpa()+"", font));
		table.addCell(addToCell(PdfUtils.WA, font));
		table.addCell(addToCell(payroll.getWa()+"", font));
		
		table.addCell(addToCell(PdfUtils.CA, font));
		table.addCell(addToCell(payroll.getCa()+"", font));
		table.addCell(addToCell(PdfUtils.UNIFORMALW, font));
		table.addCell(addToCell(payroll.getUniformAlw()+"", font));
		
		table.addCell(addToCell(PdfUtils.FLYPLGALW, font));
		table.addCell(addToCell(payroll.getFamilyPlaningAlw()+"", font));
		table.addCell(addToCell(PdfUtils.TALW, font));
		table.addCell(addToCell(payroll.getTotallw()+"", font));
		
		table.addCell(addToCell(PdfUtils.OTAMT, font));
		table.addCell(addToCell(payroll.getOtAmt()+"", font));
		table.addCell(addToCell(PdfUtils.OTHERS, font));
		table.addCell(addToCell("0.00", font));
		
	}
	
	protected void addDeductionTotals(PdfPTable table, PaybillDetails payroll, Font font){
		table.addCell(addToCell(PdfUtils.RENT, font));
		table.addCell(addToCell(payroll.getRent()+"", font));
		table.addCell(addToCell(PdfUtils.AFKRENT, font));
		table.addCell(addToCell(payroll.getAfkRent()+"", font));
		
		table.addCell(addToCell(PdfUtils.ABSDED, font));
		table.addCell(addToCell(payroll.getAbsentDed()+"", font));
		table.addCell(addToCell(PdfUtils.FAREC, font));
		table.addCell(addToCell(payroll.getFestAdvRcry()+"", font));
		
		table.addCell(addToCell(PdfUtils.LIC, font));
		table.addCell(addToCell(payroll.getLic()+"", font));
		table.addCell(addToCell(PdfUtils.SOCIETY, font));
		table.addCell(addToCell(payroll.getSocity()+"", font));
		
		table.addCell(addToCell(PdfUtils.GIS, font));
		table.addCell(addToCell(payroll.getGis()+"", font));
		table.addCell(addToCell(PdfUtils.BANKLOANREC, font));
		table.addCell(addToCell(payroll.getBankLoanRcry()+"", font));
		
		table.addCell(addToCell(PdfUtils.VEHLOANREC, font));
		table.addCell(addToCell(payroll.getVlr()+"", font));
		table.addCell(addToCell(PdfUtils.PFSCPFS, font));
		table.addCell(addToCell(payroll.getPfsCpf()+"", font));
		
		table.addCell(addToCell(PdfUtils.APFACPF, font));
		table.addCell(addToCell(payroll.getApfAcf()+"", font));
		table.addCell(addToCell(PdfUtils.PFLOANREC, font));
		table.addCell(addToCell(payroll.getPfLoanRcry()+"", font));
		
		table.addCell(addToCell(PdfUtils.INCTAX, font));
		table.addCell(addToCell(payroll.getIncomTax()+"", font));
		table.addCell(addToCell(PdfUtils.OTHERDED, font));
		table.addCell(addToCell(payroll.getOtherDeducs()+"", font));
		
		table.addCell(addToCell(PdfUtils.MISC, font));
		table.addCell(addToCell(payroll.getMisc()+"", font));
		table.addCell(addToCell(PdfUtils.UNIONFEE, font));
		table.addCell(addToCell(payroll.getUnionFee()+"", font));
		
	}
	
	private void addDetails(PdfPTable table, ReportDetails payroll, Font font){
		table.addCell(addToCell(PdfUtils.SNO, font));
		table.addCell(addToCell(srNo+"", font));
		table.addCell(addToCell(PdfUtils.HEAD, font));
		table.addCell(addToCell(payroll.getDeptCostHead(), font));
		
		table.addCell(addToCell(PdfUtils.CODE_NAME, font));
		table.addCell(addToCell(payroll.getEmployeeName(), font));
		
		
		table.addCell(addToCell(PdfUtils.DESIGNATION, font));
		table.addCell(addToCell(payroll.getDesignation(), font));
		table.addCell(addToCell(PdfUtils.BANK_NAME, font));
		table.addCell(addToCell(payroll.getBankName(), font));
		
		table.addCell(addToCell(PdfUtils.BANK_ACCTNO, font));
		table.addCell(addToCell(payroll.getBankAcctNo(), font));
		table.addCell(addToCell(PdfUtils.BIRTH_DATE, font));
		table.addCell(addToCell(payroll.getDob(), font));
		
		table.addCell(addToCell(PdfUtils.APPOINTMENT_DATE, font));
		table.addCell(addToCell(payroll.getJoiningDate(), font));
		table.addCell(addToCell(PdfUtils.RETIRE_DATE, font));
		table.addCell(addToCell(payroll.getRetirementDate(), font));
		
		table.addCell(addToCell(PdfUtils.SCALE, font));
		table.addCell(addToCell(payroll.getScale(), font));
		table.addCell(addToCell(PdfUtils.INC_MONTH, font));
		table.addCell(addToCell(payroll.getIncrementDate(), font));
		
		table.addCell(addToCell(PdfUtils.INC_AMT, font));
		table.addCell(addToCell(String.valueOf(payroll.getIncrementAmt()), font));
		table.addCell(addToCell(PdfUtils.PAN, font));
		table.addCell(addToCell(payroll.getPanNo(), font));
		
		table.addCell(addToCell(PdfUtils.DA_RATE, font));
		table.addCell(addToCell(String.valueOf(PdfUtils.DA_PERCENT+"%"), font));
		table.addCell(addToCell(PdfUtils.HRA_RATE, font));
		table.addCell(addToCell(PdfUtils.HRA_PERCENT+"%", font));
	}
}
