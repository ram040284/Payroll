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
	
	public void pensionBillreport(Document doc, PensionPaybillDetails pensionPaybill, String imgPath){
		try {
			
			String watermarkImg = imgPath+"//CBK_Logo_min.png";//request.getSession().getServletContext().getRealPath("/resources/images/CBK_Logo.png");
	        String logoImg = imgPath+"//logo_new_min.jpg";
	        doc.add(PdfUtils.getMainHeader(logoImg));
	        
	        DottedLineSeparator dottedline = new DottedLineSeparator();
	        dottedline.setOffset(-2);
	        dottedline.setGap(2f);
	        doc.add(dottedline);
	        
	        Font frtHdFont = FontFactory.getFont(FontFactory.HELVETICA);
	        frtHdFont.setSize(7);
	        String deptName = Utils.safeTrim(pensionPaybill.getDeptName());
	        StringBuffer titleText = new StringBuffer("Pension Paybill ");
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
	        
	        PdfPTable edTab = null;
	        PdfPTable paybillTabDetails = null;
	        PdfPTable payBillHeaders = null;
	        float edTabWidths[] = {1.5f, 2.0f};
	        float tabWidths[] = {1.9f,4.5f,1.9f,1.9f,1.9f,1.9f,1.9f,1.9f,1.9f,1.9f};
	        float tabPayBillHeaderWidths[] = {1.9f,4.5f,1.9f,1.9f,1.9f,1.9f,1.9f,1.9f,1.9f,1.9f};
	        
	        payBillHeaders = createPdfPTable(10, 2, tabPayBillHeaderWidths);
	        addHeaders(payBillHeaders, frtHdFont);
	        doc.add(payBillHeaders);
	        doc.add(dottedline);
	        for (Iterator iterator = pensionPaybill.getPayrollList().iterator(); iterator.hasNext();) {
	        	
	        	doc.add(PdfUtils.getWaterMarkImg(watermarkImg));
	        	PensionReportDetails pensionReportDetails = (PensionReportDetails) iterator.next();
	        	srNo++;
	        	
	        	paybillTabDetails = createPdfPTable(10, 2, tabWidths);
	        	addDetails(paybillTabDetails, pensionReportDetails, frtHdFont);
				doc.add(paybillTabDetails);
		        doc.add(dottedline);
	        }
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void addDetails(PdfPTable table, PensionReportDetails pensionReportDetails, Font font) {
		table.addCell(addToCell(srNo+"", font));
		table.addCell(addToCell(pensionReportDetails.getEmployeeId() + "\n" + pensionReportDetails.getFullName() + "\nW/O " + pensionReportDetails.getFamilyPensionName() 
		+ "\nRetd. as " + pensionReportDetails.getDesignation() + "\nRetd. on " + pensionReportDetails.getRetirementDate() , font));
		table.addCell(addToCell(Utils.getDecimalFormat(pensionReportDetails.getBasicPension()), font));
		table.addCell(addToCell(Utils.getDecimalFormat(pensionReportDetails.getResidualPension()), font));
		table.addCell(addToCell(Utils.getDecimalFormat(pensionReportDetails.getDearnessReliefArrears()), font));
		table.addCell(addToCell(Utils.getDecimalFormat(pensionReportDetails.getMedicalAllowance()), font));
		table.addCell(addToCell(Utils.getDecimalFormat(pensionReportDetails.getTotalPensionDeduction()), font));
		table.addCell(addToCell(Utils.getDecimalFormat(pensionReportDetails.getNetPension()), font));
		table.addCell(addToCell(pensionReportDetails.getPensionRemark(), font));
		table.addCell(addToCell(Utils.getDecimalFormat(pensionReportDetails.getCommutationAmount()), font));
	}
	
	private void addHeaders(PdfPTable table, Font font) {
		table.addCell(addToCell(PdfUtils.SNO, font));
		table.addCell(addToCell(PdfUtils.PENSION_EMP_HEAD, font));
		table.addCell(addToCell(PdfUtils.BASIC_PENION, font));
		table.addCell(addToCell(PdfUtils.RESY_PENSION, font));
		table.addCell(addToCell(PdfUtils.D_R_ARREARS, font));
		table.addCell(addToCell(PdfUtils.MEDICAL_ALLOWANCES, font));
		table.addCell(addToCell(PdfUtils.TOTAL_PENSION_DEDUCTIONS, font));
		table.addCell(addToCell(PdfUtils.NET_PENSION, font));
		table.addCell(addToCell(PdfUtils.REMARK, font));
		table.addCell(addToCell(PdfUtils.COMMUTATION_AMT, font));
	}
}
