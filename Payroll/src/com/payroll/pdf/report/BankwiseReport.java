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
import com.payroll.hrms.payroll.dataobjects.PaybillDetails;
import com.payroll.pdf.business.PdfUtils;

public class BankwiseReport extends PaybillPdfRep{
	
	public void bankwireReport(Document doc, List<PaybillDetails> paybillDetails, String imgPath){
		PaybillDetails totPBDetails = new PaybillDetails();
		try{
			String watermarkImg = imgPath+"\\CBK_Logo.png";
	        doc.add(PdfUtils.getWaterMarkImg(watermarkImg));
	        String logoImg = imgPath+"\\logo_new.jpg";
	        doc.add(PdfUtils.getMainHeader(logoImg));
	        
	        DottedLineSeparator dottedline = new DottedLineSeparator();
	        dottedline.setOffset(-2);
	        dottedline.setGap(2f);
	        doc.add(dottedline);
	        
	        Font frtHdFont = FontFactory.getFont(FontFactory.HELVETICA);
	        frtHdFont.setSize(7);
	        StringBuffer titleText = new StringBuffer("Paybill of Permanent/Temporary Establishment ");
	        titleText.append("for the Month of ");
	        Date month = (paybillDetails!=null && !paybillDetails.isEmpty())? paybillDetails.get(0).getMonth() : new Date();
	        titleText.append(Utils.getMonthYear(month));
	        PdfPTable paybillTab = null;
	        float paybillTabW[] = {5f, 2f};
	        paybillTab = createPdfPTable(2, 5, paybillTabW);
	        paybillTab.setHorizontalAlignment(Element.ALIGN_RIGHT);
	        
	        paybillTab.addCell(addToCell(titleText.toString(), headHdFont, true));
	        paybillTab.addCell(addToCell(Utils.getSimpleDate(new Date()), headHdFont, true));
	        doc.add(paybillTab);
	        doc.add(dottedline);
	        PdfPTable pbDtlsTab = null;
	        float pbDtlsTabW[] = {3f};
	        float pbDtls1TabW[] = {0.8f, 0.7f, 0.8f, 0.7f, 0.9f, 0.6f, 0.8f, 0.6f, 0.7f, 0.5f, 0.5f, 0.5f, 0.6f, 0.5f};
	        for (Iterator iterator = paybillDetails.iterator(); iterator.hasNext();) {
	        	PaybillDetails pbDetails = (PaybillDetails)iterator.next();
	        	pbDtlsTab = createPdfPTable(1, 3, pbDtlsTabW);
		        pbDtlsTab.setHorizontalAlignment(Element.ALIGN_LEFT);
		        pbDtlsTab.addCell(addToCell(PdfUtils.BANK_NAME + pbDetails.getBankName(), headHdFont));
		        pbDtlsTab.addCell(addToCell(PdfUtils.EARNINGS, headHdFont));
		        doc.add(pbDtlsTab);
		        pbDtlsTab = createPdfPTable(14, 3, pbDtls1TabW);
		        addTotalDetails(pbDtlsTab, pbDetails, frtHdFont);
		        doc.add(pbDtlsTab);
		        pbDtlsTab = createPdfPTable(1, 3, pbDtlsTabW);
		        pbDtlsTab.setHorizontalAlignment(Element.ALIGN_LEFT);
		        pbDtlsTab.addCell(addToCell(PdfUtils.DEDUCTIONS, headHdFont));
		        doc.add(pbDtlsTab);
		        pbDtlsTab = createPdfPTable(14, 3, pbDtls1TabW);
		        addDeductionTotals(pbDtlsTab, pbDetails, frtHdFont);
		        doc.add(pbDtlsTab);
		        doc.add(dottedline);
		        totPBDetails.addTotals(pbDetails);
/*		        float pbTotDtlsTabW[] = {2.5f, 2.5f, 2.5f};
		        pbDtlsTab = createPdfPTable(3, 5, pbTotDtlsTabW);
		        pbDtlsTab.addCell(addToCell(PdfUtils.GROSS+" "+pbDetails.getTotalGrossPay(), headHdFont, true));
		        pbDtlsTab.addCell(addToCell(PdfUtils.TOTDEDUCTION+" "+pbDetails.getTotalDeductions(), headHdFont, true));
		        pbDtlsTab.addCell(addToCell(PdfUtils.NETPAY+" "+pbDetails.getNetPay(), headHdFont, true));
		        doc.add(pbDtlsTab);
		        doc.add(dottedline);*/
		    }
	        pbDtlsTab = createPdfPTable(1, 3, pbDtlsTabW);
	        pbDtlsTab.setHorizontalAlignment(Element.ALIGN_LEFT);
	        pbDtlsTab.addCell(addToCell(PdfUtils.EARNINGSTOT, headHdFont));
	        doc.add(pbDtlsTab);
	        pbDtlsTab = createPdfPTable(14, 3, pbDtls1TabW);
	        addTotalDetails(pbDtlsTab, totPBDetails, frtHdFont);
	        doc.add(pbDtlsTab);
	        pbDtlsTab = createPdfPTable(1, 3, pbDtlsTabW);
	        pbDtlsTab.setHorizontalAlignment(Element.ALIGN_LEFT);
	        pbDtlsTab.addCell(addToCell(PdfUtils.DEDUCTIONSTOT, headHdFont));
	        doc.add(pbDtlsTab);
	        pbDtlsTab = createPdfPTable(14, 3, pbDtls1TabW);
	        addDeductionTotals(pbDtlsTab, totPBDetails, frtHdFont);
	        doc.add(pbDtlsTab);
	        doc.add(dottedline);
	   }catch(Exception e){
			e.printStackTrace();
		}
	}
}
