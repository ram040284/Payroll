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
import com.payroll.hrms.payroll.dataobjects.PensionPaybillDetails;
import com.payroll.hrms.payroll.dataobjects.PensionReportDetails;
import com.payroll.pdf.business.PdfBuilder;
import com.payroll.pdf.business.PdfUtils;

public class PensionPayBillReport extends PdfBuilder{
	int srNo = 0;
	Font boldFont = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.BOLD);
	Font headHdFont = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD);
	byte pensionFlag;
	double totalBasicPension;
	double totalResidualPension;
	double totalDR;
	double totalMedicalAlloances; 
	double totalPensionDeductions; 
	double totalNetPension; 
	double totalCommutationAmount; 
	double totArrears;
	
	public void pensionBillreport(Document doc, PensionPaybillDetails pensionPaybill, String imgPath){
		try {
			
			String watermarkImg = imgPath+"//CBK_Logo_min.png";//request.getSession().getServletContext().getRealPath("/resources/images/CBK_Logo.png");
	        String logoImg = imgPath+"//logo_new_min.jpg";
	        doc.add(PdfUtils.getMainHeader(logoImg));
	        
	        DottedLineSeparator dottedline = new DottedLineSeparator();
	        dottedline.setOffset(-2);
	        dottedline.setGap(2f);
	        doc.add(dottedline);
	        
	        for (Iterator<PensionReportDetails> iterator = pensionPaybill.getPayrollList().iterator(); iterator.hasNext();) {
	        	PensionReportDetails pensionReportDetails = (PensionReportDetails) iterator.next();
	        	pensionFlag = pensionReportDetails.getFamilyPensionFlag();
	        }
	        
	        Font frtHdFont = FontFactory.getFont(FontFactory.HELVETICA);
	        frtHdFont.setSize(7);
	        StringBuffer titleText;
	        if (pensionFlag==1) {
	        	titleText = new StringBuffer("Pension Paybill ");
			}else {
				titleText = new StringBuffer("Family Pension Paybill ");
			}
	        
	        titleText.append("for the Month of ");
	        titleText.append(Utils.getMonthYear(pensionPaybill.getMonth()));
	        PdfPTable paybillTab = null;
	        float paybillTabW[] = {1.5f, 4.5f, 1.0f};
	        paybillTab = createPdfPTable(3, 5, paybillTabW);
	        paybillTab.setHorizontalAlignment(Element.ALIGN_CENTER);
	        
	        paybillTab.addCell(addToCell("Office", headHdFont));
	        paybillTab.addCell(addToCell(titleText.toString(), headHdFont));
	        paybillTab.addCell(addToCell(Utils.getSimpleDate(new Date()), headHdFont, true));
	        doc.add(paybillTab);
	        doc.add(dottedline);
	        
	        PdfPTable paybillTabDetails = null;
	        PdfPTable payBillHeaders = null;
	        PdfPTable payBillTotal = null;
	        float tabWidths[] = {1.9f,4.5f,1.9f,1.9f,1.9f,1.9f,1.9f,1.9f,1.9f,1.9f,1.9f};
	        float tabPayBillHeaderWidths[] = {1.9f,4.5f,1.9f,1.9f,1.9f,1.9f,1.9f,1.9f,1.9f,1.9f,1.9f};
	        float totalWidths[] = {1.9f,4.5f,1.9f,1.9f,1.9f,1.9f,1.9f,1.9f,1.9f,1.9f,1.9f};
	        
	        payBillHeaders = createPdfPTable(11, 2, tabPayBillHeaderWidths);
	        addHeaders(payBillHeaders, frtHdFont);
	        doc.add(payBillHeaders);
	        doc.add(dottedline);
	        for (Iterator<PensionReportDetails> iterator = pensionPaybill.getPayrollList().iterator(); iterator.hasNext();) {
	        	
	        	doc.add(PdfUtils.getWaterMarkImg(watermarkImg));
	        	PensionReportDetails pensionReportDetails = (PensionReportDetails) iterator.next();
	        	srNo++;
	        	
	        	paybillTabDetails = createPdfPTable(11, 2, tabWidths);
	        	addDetails(paybillTabDetails, pensionReportDetails, frtHdFont);
				doc.add(paybillTabDetails);
		        doc.add(dottedline);
	        }
	        
	        payBillTotal = createPdfPTable(11, 2, totalWidths);
	        addTotal(payBillTotal, frtHdFont);
	        doc.add(payBillTotal);
	        doc.add(dottedline);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void addDetails(PdfPTable table, PensionReportDetails pensionReportDetails, Font font) {
		table.addCell(addToCell(srNo+"", font));
		if (pensionReportDetails.getFamilyPensionFlag()==1) {
			table.addCell(addToCell(pensionReportDetails.getEmployeeId() + "\n" + pensionReportDetails.getFullName() 
			+ "\nRetd. as " + pensionReportDetails.getDesignation() + "\nRetd. on " + pensionReportDetails.getRetirementDate() , font));
		}else {
			table.addCell(addToCell(pensionReportDetails.getEmployeeId() + "\n" + pensionReportDetails.getFamilyPensionName() + "\nW/O " + pensionReportDetails.getFullName() 
			+ "\nRetd. as " + pensionReportDetails.getDesignation() + "\nRetd. on " + pensionReportDetails.getRetirementDate() , font));
		}
		
		table.addCell(addToCell(Utils.getDecimalFormat(pensionReportDetails.getBasicPension()), font));
		table.addCell(addToCell(Utils.getDecimalFormat(pensionReportDetails.getResidualPension()), font));
		table.addCell(addToCell(Utils.getDecimalFormat(pensionReportDetails.getDearnessRelief()), font));
		table.addCell(addToCell(Utils.getDecimalFormat(pensionReportDetails.getArrears()), font));
		table.addCell(addToCell(Utils.getDecimalFormat(pensionReportDetails.getMedicalAllowance()), font));
		table.addCell(addToCell(Utils.getDecimalFormat(pensionReportDetails.getTotalPensionDeduction()), font));
		table.addCell(addToCell(Utils.getDecimalFormat(pensionReportDetails.getNetPension()), font));
		table.addCell(addToCell(pensionReportDetails.getPensionRemark(), font));
		table.addCell(addToCell(Utils.getDecimalFormat(pensionReportDetails.getCommutationAmount()), font));
		
		totalBasicPension +=pensionReportDetails.getBasicPension();
		totalResidualPension+=pensionReportDetails.getResidualPension();
		totalDR+=pensionReportDetails.getDearnessRelief();
		totArrears+=pensionReportDetails.getArrears();
		totalMedicalAlloances+=pensionReportDetails.getMedicalAllowance();
		totalPensionDeductions+=pensionReportDetails.getTotalPensionDeduction();
		totalNetPension+=pensionReportDetails.getNetPension();
		totalCommutationAmount+=pensionReportDetails.getCommutationAmount();
	}
	
	private void addHeaders(PdfPTable table, Font font) {
		table.addCell(addToCell(PdfUtils.SNO, font));
		table.addCell(addToCell(PdfUtils.PENSION_EMP_HEAD, font));
		table.addCell(addToCell(PdfUtils.BASIC_PENION, font));
		table.addCell(addToCell(PdfUtils.RESY_PENSION, font));
		table.addCell(addToCell(PdfUtils.DR, font));
		table.addCell(addToCell(PdfUtils.ARREARS, font));
		table.addCell(addToCell(PdfUtils.MEDICAL_ALLOWANCES, font));
		table.addCell(addToCell(PdfUtils.TOTAL_PENSION_DEDUCTIONS, font));
		table.addCell(addToCell(PdfUtils.NET_PENSION, font));
		table.addCell(addToCell(PdfUtils.REMARK, font));
		table.addCell(addToCell(PdfUtils.COMMUTATION_AMT, font));
	}
	
	private void addTotal(PdfPTable table, Font font) {
		table.addCell(addToCell(PdfUtils.CF_TOTAL, font));
		table.addCell(addToCell("", font));
		table.addCell(addToCell(Utils.getDecimalFormat(totalBasicPension), font));
		table.addCell(addToCell(Utils.getDecimalFormat(totalResidualPension), font));
		table.addCell(addToCell(Utils.getDecimalFormat(totalDR), font));
		table.addCell(addToCell(Utils.getDecimalFormat(totArrears), font));
		table.addCell(addToCell(Utils.getDecimalFormat(totalMedicalAlloances), font));
		table.addCell(addToCell(Utils.getDecimalFormat(totalPensionDeductions), font));
		table.addCell(addToCell(Utils.getDecimalFormat(totalNetPension), font));
		table.addCell(addToCell("", font));
		table.addCell(addToCell(Utils.getDecimalFormat(totalCommutationAmount), font));
	}
}
