package com.payroll.pdf.report;

import java.util.Date;
import java.util.Iterator;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import com.payroll.Utils;
import com.payroll.hrms.payroll.dataobjects.PaybillDetails;
import com.payroll.hrms.payroll.dataobjects.ReportDetails;
import com.payroll.pdf.business.PdfBuilder;
import com.payroll.pdf.business.PdfUtils;

public class PaybillPdfRep extends PdfBuilder{
	int srNo = 0;
	Font boldFont = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.BOLD);
	Font headHdFont = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD);
	int empType;
	double bankOfIndia, canaraBank, indianBank, vijayaBank, totbankCash;
    
	public void paybillReport(Document doc, PaybillDetails paybillDetails, String imgPath){
		try{
			
			
			String watermarkImg = imgPath+"//CBK_Logo_min.png";//request.getSession().getServletContext().getRealPath("/resources/images/CBK_Logo.png");
	        String logoImg = imgPath+"//logo_new_min.jpg";
	        doc.add(PdfUtils.getMainHeader(logoImg));
	        
	        DottedLineSeparator dottedline = new DottedLineSeparator();
	        dottedline.setOffset(-2);
	        dottedline.setGap(2f);
	        doc.add(dottedline);
	        
	        Font frtHdFont = FontFactory.getFont(FontFactory.HELVETICA);
	        frtHdFont.setSize(7);
	        //Date date = new Date();
	        empType = paybillDetails.getEmployeeType();
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

		        doc.add(PdfUtils.getWaterMarkImg(watermarkImg));
		        
				ReportDetails employeePayroll = (ReportDetails) iterator.next();
				srNo++;
				edTab = createPdfPTable(2, 5, edTabWidths);
				grossTab = createPdfPTable(2, 5, grossTabWidths);
				deductTab1 = createPdfPTable(2, 5, deductTab1Widths);
				deductTab2 = createPdfPTable(2, 5, deductTab2Widths);
				if (paybillDetails.getEmployeeType() == 1) {
					addDetails(edTab, employeePayroll, frtHdFont);
					addGrossDetails(grossTab, employeePayroll, frtHdFont);
					addDeduct1Details(deductTab1, employeePayroll, frtHdFont);
					addDeduct2Details(deductTab2, employeePayroll, frtHdFont);
				} else if (paybillDetails.getEmployeeType() == 2) {
					addContractualDetails(edTab, employeePayroll, frtHdFont);
					addContractualTwo(deductTab2, employeePayroll, frtHdFont);
					
				} else if (paybillDetails.getEmployeeType() == 3) {
					addHonoraryDetails(edTab, employeePayroll, frtHdFont);
					addHonoraryDetailsTwo(deductTab2, employeePayroll, frtHdFont);
				}
				
				paybillTab2 = createPdfPTable(4, 5, tabWidths);
				paybillTab2.addCell(edTab);
				paybillTab2.addCell(grossTab);
				paybillTab2.addCell(deductTab1);
				paybillTab2.addCell(deductTab2);
				doc.add(paybillTab2);
		        doc.add(dottedline);
		        if (paybillDetails.getEmployeeType() == 1) {
		        	if (employeePayroll.getBankName().equals("BANK OF INDIA")) {
		        		bankOfIndia += employeePayroll.getNetPay();
					}
		        	if (employeePayroll.getBankName().equals("CANARA BANK")) {
		        		canaraBank += employeePayroll.getNetPay();
					}
		        	if (employeePayroll.getBankName().equals("INDIAN BANK")) {
		        		indianBank += employeePayroll.getNetPay();
					}
		        	if (employeePayroll.getBankName().equals("VIJAYA BANK")) {
		        		vijayaBank += employeePayroll.getNetPay();
					}
		        	totbankCash = bankOfIndia + canaraBank + indianBank + vijayaBank;
		        	doc.add(addTotals(Utils.getDecimalFormat(employeePayroll.getGrossPay()), Utils.getDecimalFormat(employeePayroll.getTotalDeductions()),
			        		frtHdFont, tabWidths));
			        doc.add(addNetPay(Utils.getDecimalFormat(employeePayroll.getNetPay()), frtHdFont, tabWidths));
				} 
		        
		        doc.add(dottedline);
		        if (paybillDetails.getEmployeeType() == 1) {
		        	if (srNo % 3 == 0) {
			        	doc.newPage();
			        	doc.add(PdfUtils.getMainHeader(logoImg));
				        dottedline.setOffset(-2);
				        dottedline.setGap(2f);
				        doc.add(dottedline);
			        	doc.add(paybillTab);
			        	doc.add(dottedline);
			        }
				} else if (paybillDetails.getEmployeeType() == 2) {
					
				} else if (paybillDetails.getEmployeeType() == 3) {
					
				}
		        
	        }
	        
	        if (paybillDetails.getEmployeeType() == 1) {
	        	
	        	//Adding totals per Department
		        PdfPTable pbDtlsTab = null;
		        float pbDtlsTabW[] = {7f};
		        pbDtlsTab = createPdfPTable(1, 3, pbDtlsTabW);
		        pbDtlsTab.setHorizontalAlignment(Element.ALIGN_LEFT);
		        pbDtlsTab.addCell(addToCell(PdfUtils.INCOME_SIDE, headHdFont));
		        doc.add(pbDtlsTab);
		        
		        float pbDtls1TabW[] = {0.6f, 0.7f, 0.8f, 0.7f, 0.8f, 0.7f, 0.8f, 0.6f, 0.5f, 0.6f, 0.7f, 0.6f, 0.6f, 0.6f};
		        float lastPGTab[] = {0.6f, 0.7f, 0.8f, 0.7f};
		        float certificateNTTab[] = {0.6f, 0.7f};
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
		        pbDtlsTab.addCell(addToCell(PdfUtils.GROSS+" "+Utils.getDecimalFormat(paybillDetails.getTotalGrossPay()), headHdFont));
			    pbDtlsTab.addCell(addToCell(PdfUtils.TOTDEDUCTION+" "+Utils.getDecimalFormat(paybillDetails.getTotalDeductions()), headHdFont));
			    pbDtlsTab.addCell(addToCell(PdfUtils.NETPAY+" "+Utils.getDecimalFormat(paybillDetails.getNetPay()), headHdFont));
			    
		        doc.add(pbDtlsTab);
		        doc.add(dottedline);
		        
		        StringBuffer lastPageTitle = new StringBuffer("PAYBILL SUMARY FOR THE MONTH OF : ");
		        lastPageTitle.append(Utils.getMonthYear(paybillDetails.getMonth()));
		        pbDtlsTab = createPdfPTable(1, 3, pbDtlsTabW);
		        pbDtlsTab.setHorizontalAlignment(Element.ALIGN_LEFT);
		        pbDtlsTab.addCell(addToCell(lastPageTitle.toString(), headHdFont));
		        doc.add(pbDtlsTab);
		        doc.add(dottedline);
		        
		        pbDtlsTab = createPdfPTable(4, 3, lastPGTab);
		        pbDtlsTab.setHorizontalAlignment(Element.ALIGN_LEFT);
		        addLastPageSummary(pbDtlsTab, paybillDetails, frtHdFont);
		        doc.add(pbDtlsTab);
		        doc.add(dottedline);
		        
		        pbDtlsTab = createPdfPTable(1, 3, pbDtlsTabW);
		        pbDtlsTab.setHorizontalAlignment(Element.ALIGN_CENTER);
		        pbDtlsTab.addCell(addToCell("", headHdFont));
		        pbDtlsTab.addCell(addToCell("", headHdFont));
		        pbDtlsTab.addCell(addToCellCenter(PdfUtils.CERTIFICATE, headHdFont ,true));
		        pbDtlsTab.addCell(addToCell("", headHdFont));
		        doc.add(pbDtlsTab);
		        doc.add(dottedline);
		        
		        pbDtlsTab = createPdfPTable(1, 3, pbDtlsTabW);
		        pbDtlsTab.setHorizontalAlignment(Element.ALIGN_CENTER);
		        pbDtlsTab.addCell(addToCellCenter(PdfUtils.CERTIFICATE_NOTE, headHdFont ,true));
		        doc.add(pbDtlsTab);
		        doc.add(dottedline);
		        
		        pbDtlsTab = createPdfPTable(1, 3, pbDtlsTabW);
		        pbDtlsTab.setHorizontalAlignment(Element.ALIGN_LEFT);
		        pbDtlsTab.addCell(addToCell("", headHdFont));
		        pbDtlsTab.addCell(addToCell("", headHdFont));
		        pbDtlsTab.addCell(addToCell("", headHdFont));
		        pbDtlsTab.addCell(addToCell("", headHdFont));
		        pbDtlsTab.addCell(addToCell("", headHdFont));
		        pbDtlsTab.addCell(addToCell("", headHdFont));
		        pbDtlsTab.addCell(addToCell(PdfUtils.CHIEF_EXECUTIVE, headHdFont));
		        doc.add(pbDtlsTab);
		        
		        pbDtlsTab = createPdfPTable(1, 3, pbDtlsTabW);
		        pbDtlsTab.setHorizontalAlignment(Element.ALIGN_LEFT);
		        pbDtlsTab.addCell(addToCell("", headHdFont));
		        pbDtlsTab.addCell(addToCell("", headHdFont));
		        pbDtlsTab.addCell(addToCell(PdfUtils.POERED_BY, headHdFont));
		        doc.add(pbDtlsTab);
		        
		        pbDtlsTab = createPdfPTable(1, 3, pbDtlsTabW);
		        pbDtlsTab.setHorizontalAlignment(Element.ALIGN_CENTER);
		        pbDtlsTab.addCell(addToCell("", headHdFont));
		        pbDtlsTab.addCell(addToCell("", headHdFont));
		        pbDtlsTab.addCell(addToCellCenter(PdfUtils.CERTIFICATE, headHdFont ,true));
		        pbDtlsTab.addCell(addToCell("", headHdFont));
		        doc.add(pbDtlsTab);
		        doc.add(dottedline);
		        
		        pbDtlsTab = createPdfPTable(1, 3, pbDtlsTabW);
		        pbDtlsTab.setHorizontalAlignment(Element.ALIGN_CENTER);
		        pbDtlsTab.addCell(addToCellCenter(PdfUtils.CERTIFICATE_NOTE_TWO, headHdFont ,true));
		        doc.add(pbDtlsTab);
		        doc.add(dottedline);
		        
		        pbDtlsTab = createPdfPTable(2, 3, certificateNTTab);
		        pbDtlsTab.setHorizontalAlignment(Element.ALIGN_LEFT);
		        addDocumentsChecked(pbDtlsTab, paybillDetails, frtHdFont);
		        doc.add(pbDtlsTab);
		        doc.add(dottedline);
		        
		        pbDtlsTab = createPdfPTable(1, 3, pbDtlsTabW);
		        pbDtlsTab.setHorizontalAlignment(Element.ALIGN_RIGHT);
		        pbDtlsTab.addCell(addToCell("", headHdFont));
		        pbDtlsTab.addCell(addToCell("", headHdFont));
		        pbDtlsTab.addCell(addToCell("", headHdFont));
		        pbDtlsTab.addCell(addToCell("", headHdFont));
		        pbDtlsTab.addCell(addToCell(PdfUtils.PAY_CLEARK, frtHdFont ,true));
		        doc.add(pbDtlsTab);
		        
		        pbDtlsTab = createPdfPTable(1, 3, pbDtlsTabW);
		        pbDtlsTab.setHorizontalAlignment(Element.ALIGN_LEFT);
		        pbDtlsTab.addCell(addToCell(PdfUtils.CHECKED_BY, headHdFont));
		        doc.add(pbDtlsTab);
		        
		        StringBuffer lastNote = new StringBuffer("Total GIS Demand for ");
		        lastNote.append(Utils.getMonthYear(paybillDetails.getMonth()));
		        pbDtlsTab = createPdfPTable(1, 3, pbDtlsTabW);
		        pbDtlsTab.setHorizontalAlignment(Element.ALIGN_LEFT);
		        pbDtlsTab.addCell(addToCell(lastNote.toString(), frtHdFont));
		        doc.add(pbDtlsTab);
		        
		        doc.add(PdfUtils.getWaterMarkImg(watermarkImg));
			} else if (paybillDetails.getEmployeeType() == 2) {
				
				//Adding totals per Department
		        PdfPTable pbDtlsTab = null;
		        float pbDtlsTabW[] = {7f};
		        pbDtlsTab = createPdfPTable(1, 3, pbDtlsTabW);
		        pbDtlsTab.setHorizontalAlignment(Element.ALIGN_LEFT);
		        doc.add(pbDtlsTab);
		        
		        float pbTotDtlsTabW[] = {2.5f, 2.5f, 2.5f};
		        pbDtlsTab = createPdfPTable(3, 5, pbTotDtlsTabW);
		        pbDtlsTab.setHorizontalAlignment(Element.ALIGN_CENTER);
		        pbDtlsTab.addCell(addToCell(PdfUtils.NET_PAY+ " "+Utils.getDecimalFormat(paybillDetails.getTotal()), headHdFont));
		        pbDtlsTab.addCell(addToCell(PdfUtils.TOT_TDS+" "+Utils.getDecimalFormat(paybillDetails.getTDS()), headHdFont));
		        pbDtlsTab.addCell(addToCell(PdfUtils.ABS_DED_TOTAL_AMT+" "+Utils.getDecimalFormat(paybillDetails.getAbsentDeduction()), headHdFont));
		        
		        doc.add(pbDtlsTab);
		        doc.add(dottedline);
		        doc.add(PdfUtils.getWaterMarkImg(watermarkImg));
			} else if (paybillDetails.getEmployeeType() == 3) {
				
				//Adding totals per Department
		        PdfPTable pbDtlsTab = null;
		        float pbDtlsTabW[] = {7f};
		        pbDtlsTab = createPdfPTable(1, 3, pbDtlsTabW);
		        pbDtlsTab.setHorizontalAlignment(Element.ALIGN_LEFT);
		        doc.add(pbDtlsTab);
		        
		        float pbTotDtlsTabW[] = {2.5f, 2.5f, 2.5f};
		        pbDtlsTab = createPdfPTable(3, 5, pbTotDtlsTabW);
		        pbDtlsTab.setHorizontalAlignment(Element.ALIGN_CENTER);
		        pbDtlsTab.addCell(addToCell(PdfUtils.NET_PAY+ " "+Utils.getDecimalFormat(paybillDetails.getTotal()), headHdFont));
		        pbDtlsTab.addCell(addToCell(""+" ", headHdFont));
		        pbDtlsTab.addCell(addToCell(""+" ", headHdFont));
		        
		        doc.add(pbDtlsTab);
		        doc.add(dottedline);
		        doc.add(PdfUtils.getWaterMarkImg(watermarkImg));
			}
	        
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
		netpayTab.addCell(addToCell(netPay, boldFont));
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
		totTab.addCell(addToCell(grossPay, boldFont));
		table.addCell(totTab);
		table.addCell("");
		//float totDeductWidths[] = {1.5f, 1f};
		totTab = createPdfPTable(2, 5, totWidths);
		totTab.addCell(addToCell(PdfUtils.TOTDEDUCTION, boldFont));
		totTab.addCell(addToCell(totDeductions, boldFont));
		table.addCell(totTab);
		return table;
	}
	
	private void addDeduct1Details(PdfPTable table, ReportDetails payroll, Font font){
		
		table.addCell(addToCell(PdfUtils.L_FEES, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getLfee()), font, true));
		table.addCell(addToCell(PdfUtils.AFKRENT, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getAfkRent()), font, true));
		
		table.addCell(addToCell(PdfUtils.ABSDED, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getAbsentAmount()), font,true));
		table.addCell(addToCell(PdfUtils.FAREC, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getFestAdvRecovery()), font,true));
		
		table.addCell(addToCell(PdfUtils.PTAX, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getProfTax()), font,true));
		table.addCell(addToCell(PdfUtils.LIC, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getLic()), font,true));
		
		table.addCell(addToCell(PdfUtils.SOCIETY, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getSocietyInstallment()), font,true));
		table.addCell(addToCell(PdfUtils.GIS, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getGrpInsurance()), font, true));
		
		table.addCell(addToCell(PdfUtils.BANKLOANREC, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getBankLoanRcry()), font ,true));
		table.addCell(addToCell(PdfUtils.VEHLOANREC, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getVehclLoanRcry()), font,true));
	}
	
	private void addDeduct2Details(PdfPTable table, ReportDetails payroll, Font font){
		// add employee pf recovery from emp fixed ded table and emp var ded
		
		table.addCell(addToCell(PdfUtils.APFACPF, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getApfacpf()), font ,true));
		
		table.addCell(addToCell(PdfUtils.PFLOANREC, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getPfLoanRecovery()), font,true));
		
		table.addCell(addToCell(PdfUtils.PFSCPFS, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getProvidentFund()), font,true));
		/*table.addCell(addToCell(PdfUtils.APFACPF, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getApfacpf()), font));*/
		
		/*table.addCell(addToCell(PdfUtils.PFLOANREC, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getPfLoanRecovery()), font));
		table.addCell(addToCell(PdfUtils.CPFREC, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getCpfRecovery()), font));*/ 
		
		
		
		table.addCell(addToCell(PdfUtils.INCTAX, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getIncomeTax()), font, true));
		table.addCell(addToCell(PdfUtils.UNIONFEE, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getUnionFee()), font, true));
		
		table.addCell(addToCell(PdfUtils.ELECREC, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getElectricityRecovery()), font, true));
		table.addCell(addToCell(PdfUtils.COURTREC, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getCourtRecovery()), font ,true));
		
		table.addCell(addToCell(PdfUtils.OTHERDED, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getOtherDeductions()), font ,true));
		table.addCell(addToCell(PdfUtils.MISC, font));
		table.addCell(addToCell("0.00", font,true));
		
		table.addCell(addToCell(PdfUtils.UNFEE_KSS, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getUnionFeeKss()), font ,true)); // Kss fee yet to add
		table.addCell(addToCell(PdfUtils.PFINST, font));
		//table.addCell(addToCell(payroll.getPfInstment()+"", font));
		table.addCell(addToCell("0.00", font, true));
		
		//table.addCell(addToCell(PdfUtils.TOTDEDUCTION, font));
		//table.addCell(addToCell(payroll.getTotalDeductions()+"", font));
		
	}

	
	private void addGrossDetails(PdfPTable table, ReportDetails payroll, Font font){
		
		table.addCell(addToCell(PdfUtils.BASICS, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getBasic()), font,true));
		table.addCell(addToCell(PdfUtils.GRADEPAY, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getGradePay()), font,true));
		
		table.addCell(addToCell(PdfUtils.DA, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getDearnessAllowance()), font,true));
		table.addCell(addToCell(PdfUtils.HRA, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getHouseRentAllowance()), font,true));
		
		table.addCell(addToCell(PdfUtils.CCA, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getCca()), font,true));
		table.addCell(addToCell(PdfUtils.TA, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getTravelAllowance()), font,true));
		
		table.addCell(addToCell(PdfUtils.NPA, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getNonPracticingAllowance()), font, true));
		table.addCell(addToCell(PdfUtils.WA, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getWashingAllowance()), font ,true));
		
//		table.addCell(addToCell(PdfUtils.CA, font));
//		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getConveyanceAllowance()), font));
		table.addCell(addToCell(PdfUtils.CA, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getCa()), font ,true));
		table.addCell(addToCell(PdfUtils.UNIFORMALW, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getUniformAllowance()), font ,true));
		
		table.addCell(addToCell(PdfUtils.FLYPLGALW, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getFamilyPlanningAllowance()), font ,true));
		table.addCell(addToCell(PdfUtils.TALW, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.gettAllowance()), font ,true)); //TODO - Prasad
		
		table.addCell(addToCell(PdfUtils.OTAMT, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getOverTimeAmount()), font ,true));
		table.addCell(addToCell(PdfUtils.OTHERS, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getOtherPayAmount()), font ,true));
		
		//table.addCell(addToCell(PdfUtils.GROSS, font));
		//table.addCell(addToCell(payroll.getGrossPay()+"", font));
		
	}
	
	protected void addTotalDetails(PdfPTable table, PaybillDetails payroll, Font font){
		
		table.addCell(addToCell(PdfUtils.BASICS, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getBasicPay()), font,true));
		table.addCell(addToCell(PdfUtils.GRADEPAY, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getGradePay()), font,true));
		
		table.addCell(addToCell(PdfUtils.DA, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getDa()), font,true));
		table.addCell(addToCell(PdfUtils.HRA, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getHra()), font,true));
		
		table.addCell(addToCell(PdfUtils.CCA, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getCca()), font,true));
		table.addCell(addToCell(PdfUtils.TA, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getTa()), font,true));
		
		table.addCell(addToCell(PdfUtils.NPA, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getNpa()), font,true));
		table.addCell(addToCell(PdfUtils.WA, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getWa()), font,true));
		
		table.addCell(addToCell(PdfUtils.CA, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getCa()), font,true));
		table.addCell(addToCell(PdfUtils.UNIFORMALW, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getUniformAlw()), font,true));
		
		table.addCell(addToCell(PdfUtils.FLYPLGALW, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getFamilyPlaningAlw()), font,true));
		table.addCell(addToCell(PdfUtils.TALW, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.gettAllowance()), font,true));
		
		table.addCell(addToCell(PdfUtils.OTAMT, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getOtAmt()), font,true));
		table.addCell(addToCell(PdfUtils.OTHERS, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getOthers()), font,true));
		
	}
	
	protected void addDeductionTotals(PdfPTable table, PaybillDetails payroll, Font font){
		
		table.addCell(addToCell(PdfUtils.RENT, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getRent()), font,true));
		table.addCell(addToCell(PdfUtils.AFKRENT, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getAfkRent()), font,true));
		
		table.addCell(addToCell(PdfUtils.ABSDED, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getAbsentDed()), font,true));
		table.addCell(addToCell(PdfUtils.FAREC, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getFestAdvRcry()), font,true));
		
		table.addCell(addToCell(PdfUtils.LIC, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getLic()), font,true));
		table.addCell(addToCell(PdfUtils.SOCIETY, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getSocity()), font,true));
		
		table.addCell(addToCell(PdfUtils.PFSCPFS, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getProvidentFund()), font,true));
		
		table.addCell(addToCell(PdfUtils.GIS, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getGis()), font,true));
		table.addCell(addToCell(PdfUtils.BANKLOANREC, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getBankLoanRcry()), font,true));
		
		/*table.addCell(addToCell(PdfUtils.VEHLOANREC, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getVlr()), font));
		table.addCell(addToCell(PdfUtils.PFSCPFS, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getPfsCpf()), font));
		
		table.addCell(addToCell(PdfUtils.APFACPF, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getApfAcf()), font));
		table.addCell(addToCell(PdfUtils.PFLOANREC, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getPfLoanRcry()), font));*/
		
		table.addCell(addToCell(PdfUtils.INCTAX, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getIncomeTax()), font,true));
		table.addCell(addToCell(PdfUtils.OTHERDED, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getOtherDeducs()), font,true));
		
		table.addCell(addToCell(PdfUtils.MISC, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getMisc()), font,true));
		table.addCell(addToCell(PdfUtils.UNIONFEE, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getUnionFee()), font,true));
		table.addCell(addToCell(PdfUtils.UNFEE_KSS, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getUnionFeeKss()), font,true));
		
	}
	
	private void addDetails(PdfPTable table, ReportDetails payroll, Font font){
		
		table.addCell(addToCell(PdfUtils.SNO, font));
		table.addCell(addToCell(srNo+"", font));
		table.addCell(addToCell(PdfUtils.HEAD, font));
		table.addCell(addToCell(payroll.getDeptCostHead(), font));
		
		table.addCell(addToCell(PdfUtils.EMPLOYEE_ID, font));
		table.addCell(addToCell(payroll.getEmployeeNumber(), font));
		
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
		table.addCell(addToCell(payroll.getScalePay() + ": " + payroll.getScale(), font));
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
	
	private void addContractualDetails(PdfPTable table, ReportDetails payroll, Font font) {
		table.addCell(addToCell(PdfUtils.SNO, font));
		table.addCell(addToCell(srNo+"", font));
		
		table.addCell(addToCell(PdfUtils.HEAD, font));
		table.addCell(addToCell(payroll.getDeptCostHead(), font));
		
		table.addCell(addToCell(PdfUtils.EMPLOYEE_ID, font));
		table.addCell(addToCell(String.valueOf(payroll.getEmployeeId()), font));
		
		table.addCell(addToCell(PdfUtils.BANK_ACCTNO, font));
		table.addCell(addToCell(payroll.getContBankAcNumber(), font));
		
		table.addCell(addToCell(PdfUtils.BASICS, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getBasic()), font));
		
		table.addCell(addToCell(PdfUtils.OTHERDED, font));
		table.addCell(addToCell("0", font));
		
	}
	
	private void addContractualTwo(PdfPTable table, ReportDetails payroll, Font font) {
	
		table.addCell(addToCell(PdfUtils.NO_OF_DAYS, font));
		table.addCell(addToCell("31", font));
		
		table.addCell(addToCell(PdfUtils.ABS_DAYS, font));
		table.addCell(addToCell(String.valueOf(payroll.getEmpAbsentDays()), font));
		
		table.addCell(addToCell(PdfUtils.PRESENT_DAY, font));
		table.addCell(addToCell(String.valueOf(payroll.getEmpPresentDays()), font));
		
		table.addCell(addToCell(PdfUtils.ABS_DED_AMT, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getContAbsDedAmt()), font));
		
		table.addCell(addToCell(PdfUtils.TDS, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getContTDS()), font));
	}
	
	private void addHonoraryDetails(PdfPTable table, ReportDetails payroll, Font font) {
		table.addCell(addToCell(PdfUtils.SNO, font));
		table.addCell(addToCell(srNo+"", font));
		
		table.addCell(addToCell(PdfUtils.HEAD, font));
		table.addCell(addToCell(payroll.getDeptCostHead(), font));
		
		table.addCell(addToCell(PdfUtils.EMPLOYEE_ID, font));
		table.addCell(addToCell(String.valueOf(payroll.getEmployeeId()), font));
		
		table.addCell(addToCell(PdfUtils.BANK_ACCTNO, font));
		table.addCell(addToCell(payroll.getContBankAcNumber(), font));
		
		table.addCell(addToCell(PdfUtils.BASICS, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getBasic()), font));
		
		table.addCell(addToCell(PdfUtils.OTHERDED, font));
		table.addCell(addToCell("0", font));
		
		table.addCell(addToCell(PdfUtils.DP, font));
		table.addCell(addToCell("0", font));
		
		table.addCell(addToCell(PdfUtils.DA, font));
		table.addCell(addToCell("0", font));
		
		table.addCell(addToCell(PdfUtils.GROSSPAY, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getBasic()), font));
		
		table.addCell(addToCell(PdfUtils.ABS_DAYS, font));
		table.addCell(addToCell(String.valueOf(payroll.getEmpAbsentDays()), font));
	} 
	
	private void addHonoraryDetailsTwo(PdfPTable table, ReportDetails payroll, Font font) {
		
		table.addCell(addToCell(PdfUtils.ABS_DED_AMT, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getContAbsDedAmt()), font));
		
		table.addCell(addToCell(PdfUtils.MISC, font));
		table.addCell(addToCell("0", font,true));
		
		table.addCell(addToCell(PdfUtils.PT, font));
		table.addCell(addToCell("0", font));
		
		table.addCell(addToCell(PdfUtils.DA_FLAG, font));
		table.addCell(addToCell("FLASE", font));
		
		table.addCell(addToCell(PdfUtils.SUSPENSE, font));
		table.addCell(addToCell("FLASE", font));
		
		table.addCell(addToCell(PdfUtils.PT_FLAG, font));
		table.addCell(addToCell("FLASE", font,true));
		
		table.addCell(addToCell(PdfUtils.DP_FLAG, font));
		table.addCell(addToCell("FALSE", font));
		
		table.addCell(addToCell(PdfUtils.DED_REMARK, font));
		table.addCell(addToCell("", font));
		
		table.addCell(addToCell(PdfUtils.NO_OF_DAYS, font));
		table.addCell(addToCell(String.valueOf(payroll.getEmpPresentDays()), font));
	}
	
	
	protected void addLastPageSummary(PdfPTable table, PaybillDetails payroll, Font font){
		
		table.addCell(addToCell(PdfUtils.PT, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getTotalPT()), font,true));
		table.addCell(addToCell(PdfUtils.CASH_PAYMENT, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getCashPayment()), font,true));
		
		table.addCell(addToCell(PdfUtils.LIC, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getTotalLIC()), font,true));
		table.addCell(addToCell(PdfUtils.BANK_OF_INDIA, font));
		table.addCell(addToCell(Utils.getDecimalFormat(bankOfIndia), font,true));
		
		table.addCell(addToCell(PdfUtils.BANK_LOAN, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getTotalbankLoan()), font,true));
		table.addCell(addToCell(PdfUtils.CANARA_BANK, font));
		table.addCell(addToCell(Utils.getDecimalFormat(canaraBank), font,true));
		
		table.addCell(addToCell(PdfUtils.GIS, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getTotalGIS()), font,true));
		table.addCell(addToCell(PdfUtils.INDIAN_BANK, font));
		table.addCell(addToCell(Utils.getDecimalFormat(indianBank), font,true));
		
		table.addCell(addToCell(PdfUtils.SOCIETY, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getTotalSociety()), font,true));
		table.addCell(addToCell(PdfUtils.VIJAYA_BANK, font));
		table.addCell(addToCell(Utils.getDecimalFormat(vijayaBank), font,true));
		
		table.addCell(addToCell(PdfUtils.PFS_CPF, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getTotalPFS()), font,true));
		table.addCell(addToCell(PdfUtils.TOTAl_BANK_CASH, font));
		table.addCell(addToCell(Utils.getDecimalFormat(totbankCash), font,true));
		
		table.addCell(addToCell(PdfUtils.PF_LOAN_CPF_LOAN, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getPfLoanRcry()), font,true));
		table.addCell(addToCell(PdfUtils.RECOVERIES, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getSubTotal()), font,true));
		
		table.addCell(addToCell(PdfUtils.APFACPF, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getTotalPfsAcpf()), font,true));
		table.addCell(addToCell(PdfUtils.TOTAL, font));
		table.addCell(addToCell(Utils.getDecimalFormat(totbankCash+payroll.getSubTotal()), font,true));// need to add recoveries total ass well
		
		table.addCell(addToCell(PdfUtils.INCOME_TAX, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getTotalIncomeTax()), font,true));
		table.addCell(addToCell(PdfUtils.PENSION_CONTR_CF, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getPensionContrCF()), font,true));
		
		table.addCell(addToCell(PdfUtils.UNION_FEES, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getTotalUnionFees()), font,true));
		table.addCell(addToCell(PdfUtils.TOTAL, font));
		table.addCell(addToCell(Utils.getDecimalFormat(totbankCash+payroll.getSubTotal()+payroll.getPensionContrCF()), font,true));
		
		table.addCell(addToCell(PdfUtils.UNIOS_FEES_KSS, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getTotalUnionFeesKSS()), font,true));
		table.addCell(addToCell("", font));
		table.addCell(addToCell("", font,true));
		
		table.addCell(addToCell(PdfUtils.AFK_RECOVERY, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getTotalAFKRecovery()), font,true));
		table.addCell(addToCell("", font));
		table.addCell(addToCell("", font,true));
		
		table.addCell(addToCell(PdfUtils.COURTREC, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getTotalCourtRecovery()), font,true));
		table.addCell(addToCell("", font));
		table.addCell(addToCell("", font,true));
		
		table.addCell(addToCell(PdfUtils.SUB_TOTAL, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getSubTotal()), font,true));
		table.addCell(addToCell("", font));
		table.addCell(addToCell("", font,true));
		
		table.addCell(addToCell(PdfUtils.PF_PENSION, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getPFPension()), font,true));
		table.addCell(addToCell("", font));
		table.addCell(addToCell("", font,true));
		
		table.addCell(addToCell(PdfUtils.TOTAL, font));
		table.addCell(addToCell(Utils.getDecimalFormat(payroll.getAllTotal()), font,true));
		table.addCell(addToCell("", font));
		table.addCell(addToCell("", font,true));
		
	}
	
	protected void addDocumentsChecked(PdfPTable table, PaybillDetails payroll, Font font){
		
		table.addCell(addToCell(PdfUtils.MASTER_ROLLS, font));
		table.addCell(addToCell(PdfUtils.OVERTIME_ORDERS, font));
		
		table.addCell(addToCell(PdfUtils.EARNED_LEAVE_APPLI, font));
		table.addCell(addToCell(PdfUtils.LAST_MONTH_SAL_DRAWN, font));
		
		table.addCell(addToCell(PdfUtils.NO_EXCESS, font));
		table.addCell(addToCell(PdfUtils.NO_OVERPAT, font));
		
	}
}
