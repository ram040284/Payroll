package com.payroll.pdf.report;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.PdfPRow;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import com.payroll.Utils;
import com.payroll.hrms.payroll.dataobjects.PaybillDetails;
import com.payroll.paybill.vo.PaybillBean;
import com.payroll.pdf.business.PdfBuilder;
import com.payroll.pdf.business.PdfUtils;

public class MonthlyPdfRep extends PaybillPdfRep{
	
	public void mthlyCompReport(Document doc, List<PaybillDetails> paybillDetails, String imgPath){
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
	        frtHdFont.setSize(8);
	        float tabWidths[] = {1.0f, 2.5f, 2.5f, 2.5f, 1.0f};
	        PdfPTable firstLine = null;
	        firstLine = createPdfPTable(5, 5, tabWidths);
	        firstLine.addCell(addToCell(PdfUtils.MTHLY_RPT_HEAD[0], headHdFont));
	        firstLine.addCell(addToCell(PdfUtils.MTHLY_RPT_HEAD[1], headHdFont));
	        firstLine.addCell(addToCell(PdfUtils.MTHLY_RPT_HEAD[2]+Utils.getMonthYear(new Date()), headHdFont, true));
	        firstLine.addCell(addToCell(PdfUtils.MTHLY_RPT_HEAD[3]+Utils.getLastMonthYear(new Date()), headHdFont, true));
	        firstLine.addCell(addToCell(PdfUtils.MTHLY_RPT_HEAD[4], headHdFont, true));
	        doc.add(firstLine);
	        doc.add(dottedline);
	        
	        PaybillDetails cmDetails = (paybillDetails.get(0) != null) ? paybillDetails.get(0) : null;
	        PaybillDetails pmDetails = (paybillDetails.get(1) != null) ? paybillDetails.get(1) : null;
	        int cmEmps = cmDetails.getNoOfEmployees();
	        int pmEmps = pmDetails.getNoOfEmployees();
	       	        
	        firstLine = createPdfPTable(5, 15, tabWidths);
	        firstLine.addCell(addToCell("", frtHdFont));
	        firstLine.addCell(addToCell(PdfUtils.NOOFEMPS, headHdFont));
	        firstLine.addCell(addToCell(cmEmps+".00", headHdFont, true));
	        firstLine.addCell(addToCell(pmEmps+".00", headHdFont, true));
	        firstLine.addCell(addToCell((cmEmps-pmEmps)+".00", headHdFont, true));
	        doc.add(firstLine);
	        doc.add(dottedline);
	        
	        int srNo = 1;
	        firstLine = createPdfPTable(5, 3, tabWidths);
	        srNo = addTotalDetails(srNo, firstLine, cmDetails, pmDetails, frtHdFont);
	        doc.add(firstLine);
	        doc.add(dottedline);
	        firstLine = createPdfPTable(5, 8, tabWidths);
	        firstLine.addCell(addToCell(srNo++ +"", frtHdFont));
	        firstLine.addCell(addToCell(PdfUtils.GROSS, headHdFont));
	        firstLine.addCell(addToCell(cmDetails.getTotalGrossPay()+"", headHdFont, true));
	        firstLine.addCell(addToCell(pmDetails.getTotalGrossPay()+"", headHdFont, true));
	        firstLine.addCell(addToCell((cmDetails.getTotalGrossPay() - pmDetails.getTotalGrossPay())+"", headHdFont, true));
	        doc.add(firstLine);
	        doc.add(dottedline);
	        firstLine = createPdfPTable(5, 3, tabWidths);
	        srNo = addDeductionTotals(srNo, firstLine, cmDetails, pmDetails, frtHdFont);
	        doc.add(firstLine);
	        doc.add(dottedline);
	        firstLine = createPdfPTable(5, 8, tabWidths);
	        firstLine.addCell(addToCell(srNo++ +"", frtHdFont));
	        firstLine.addCell(addToCell(PdfUtils.TOTDEDUCTION, headHdFont));
	        firstLine.addCell(addToCell(cmDetails.getTotalDeductions()+"", headHdFont, true));
	        firstLine.addCell(addToCell(pmDetails.getTotalDeductions()+"", headHdFont, true));
	        firstLine.addCell(addToCell((cmDetails.getTotalDeductions() - pmDetails.getTotalDeductions())+"", headHdFont, true));
	        doc.add(firstLine);
	        doc.add(dottedline);
	        firstLine = createPdfPTable(5, 8, tabWidths);
	        firstLine.addCell(addToCell(srNo++ +"", frtHdFont));
	        firstLine.addCell(addToCell(PdfUtils.NETPAY, headHdFont));
	        firstLine.addCell(addToCell(cmDetails.getNetPay()+"", headHdFont, true));
	        firstLine.addCell(addToCell(pmDetails.getNetPay()+"", headHdFont, true));
	        firstLine.addCell(addToCell((cmDetails.getNetPay() - pmDetails.getNetPay())+"", headHdFont, true));
	        doc.add(firstLine);
	        doc.add(dottedline);
	    }catch(Exception e){
			e.printStackTrace();
		}
	}
	private int addTotalDetails(int srNo, PdfPTable table, PaybillDetails payroll, PaybillDetails lmDetails, Font font){
		table.addCell(addToCell(srNo++ +"", font));
		table.addCell(addToCell(PdfUtils.BASICS, font));
		table.addCell(addToCell(payroll.getBasicPay()+"", font, true));
		table.addCell(addToCell(lmDetails.getBasicPay()+"", font, true));
		table.addCell(addToCell((payroll.getBasicPay() - lmDetails.getBasicPay())+"", font, true));
		
		table.addCell(addToCell(srNo++ +"", font));
		table.addCell(addToCell(PdfUtils.GRADEPAY, font));
		table.addCell(addToCell(payroll.getGradePay()+"", font, true));
		table.addCell(addToCell(lmDetails.getGradePay()+"", font, true));
		table.addCell(addToCell((payroll.getGradePay() - lmDetails.getGradePay())+"", font, true));
		
		table.addCell(addToCell(srNo++ +"", font));
		table.addCell(addToCell(PdfUtils.DA, font));
		table.addCell(addToCell(payroll.getDa()+"", font, true));
		table.addCell(addToCell(lmDetails.getDa()+"", font, true));
		table.addCell(addToCell(payroll.getDa() - lmDetails.getDa()+"", font, true));
		
		table.addCell(addToCell(srNo++ +"", font));
		table.addCell(addToCell(PdfUtils.HRA, font));
		table.addCell(addToCell(payroll.getHra()+"", font, true));
		table.addCell(addToCell(lmDetails.getHra()+"", font, true));
		table.addCell(addToCell(payroll.getHra() - lmDetails.getHra()+"", font, true));
		
		table.addCell(addToCell(srNo++ +"", font));
		table.addCell(addToCell(PdfUtils.CCA, font));
		table.addCell(addToCell(payroll.getCca()+"", font, true));
		table.addCell(addToCell(lmDetails.getCca()+"", font, true));
		table.addCell(addToCell(payroll.getCca() - lmDetails.getCca()+"", font, true));
		
		table.addCell(addToCell(srNo++ +"", font));
		table.addCell(addToCell(PdfUtils.TA, font));
		table.addCell(addToCell(payroll.getTa()+"", font, true));
		table.addCell(addToCell(lmDetails.getTa()+"", font, true));
		table.addCell(addToCell(payroll.getTa() - lmDetails.getTa()+"", font, true));
		
		table.addCell(addToCell(srNo++ +"", font));
		table.addCell(addToCell(PdfUtils.NPA, font));
		table.addCell(addToCell(payroll.getNpa()+"", font, true));
		table.addCell(addToCell(lmDetails.getNpa()+"", font, true));
		table.addCell(addToCell(payroll.getNpa() - lmDetails.getNpa()+"", font, true));
		
		table.addCell(addToCell(srNo++ +"", font));
		table.addCell(addToCell(PdfUtils.WA, font));
		table.addCell(addToCell(payroll.getWa()+"", font, true));
		table.addCell(addToCell(lmDetails.getWa()+"", font, true));
		table.addCell(addToCell(payroll.getWa() - lmDetails.getWa()+"", font, true));
		
		table.addCell(addToCell(srNo++ +"", font));
		table.addCell(addToCell(PdfUtils.CA, font));
		table.addCell(addToCell(payroll.getCa()+"", font, true));
		table.addCell(addToCell(lmDetails.getCa()+"", font, true));
		table.addCell(addToCell(payroll.getCa() - lmDetails.getCa()+"", font, true));
		
		table.addCell(addToCell(srNo++ +"", font));
		table.addCell(addToCell(PdfUtils.UNIFORMALW, font));
		table.addCell(addToCell(payroll.getUniformAlw()+"", font, true));
		table.addCell(addToCell(lmDetails.getUniformAlw()+"", font, true));
		table.addCell(addToCell(payroll.getUniformAlw() - lmDetails.getUniformAlw()+"", font, true));
		
		table.addCell(addToCell(srNo++ +"", font));
		table.addCell(addToCell(PdfUtils.FLYPLGALW, font));
		table.addCell(addToCell(payroll.getFamilyPlaningAlw()+"", font, true));
		table.addCell(addToCell(lmDetails.getFamilyPlaningAlw()+"", font, true));
		table.addCell(addToCell(payroll.getFamilyPlaningAlw() - lmDetails.getFamilyPlaningAlw()+"", font, true));
		
		table.addCell(addToCell(srNo++ +"", font));
		table.addCell(addToCell(PdfUtils.TALW, font));
		table.addCell(addToCell(payroll.getTotallw()+"", font, true));
		table.addCell(addToCell(lmDetails.getTotallw()+"", font, true));
		table.addCell(addToCell(payroll.getTotallw() - lmDetails.getTotallw()+"", font, true));
		
		table.addCell(addToCell(srNo++ +"", font));
		table.addCell(addToCell(PdfUtils.OTAMT, font));
		table.addCell(addToCell(payroll.getOtAmt()+"", font, true));
		table.addCell(addToCell(lmDetails.getOtAmt()+"", font, true));
		table.addCell(addToCell(payroll.getOtAmt() - lmDetails.getOtAmt()+"", font, true));
		
		table.addCell(addToCell(srNo++ +"", font));
		table.addCell(addToCell(PdfUtils.OTHERS, font));
		table.addCell(addToCell(payroll.getOthers()+"", font, true));
		table.addCell(addToCell(lmDetails.getOthers()+"", font, true));
		table.addCell(addToCell(payroll.getOthers() - lmDetails.getOthers()+"", font, true));
		return srNo;
	}
	
	private int addDeductionTotals(int srNo, PdfPTable table, PaybillDetails payroll, PaybillDetails lmDetails, Font font){
		table.addCell(addToCell(srNo++ +"", font));
		table.addCell(addToCell(PdfUtils.RENT, font));
		table.addCell(addToCell(payroll.getRent()+"", font, true));
		table.addCell(addToCell(lmDetails.getRent()+"", font, true));
		table.addCell(addToCell(payroll.getRent() - lmDetails.getRent()+"", font, true));
		
		table.addCell(addToCell(srNo++ +"", font));
		table.addCell(addToCell(PdfUtils.AFKRENT, font));
		table.addCell(addToCell(payroll.getAfkRent()+"", font, true));
		table.addCell(addToCell(lmDetails.getAfkRent()+"", font, true));
		table.addCell(addToCell(payroll.getAfkRent() - lmDetails.getAfkRent()+"", font, true));
		
		table.addCell(addToCell(srNo++ +"", font));
		table.addCell(addToCell(PdfUtils.ABSDED, font));
		table.addCell(addToCell(payroll.getAbsentDed()+"", font, true));
		table.addCell(addToCell(lmDetails.getAbsentDed()+"", font, true));
		table.addCell(addToCell(payroll.getAbsentDed() - lmDetails.getAbsentDed()+"", font, true));
		
		table.addCell(addToCell(srNo++ +"", font));
		table.addCell(addToCell(PdfUtils.FAREC, font));
		table.addCell(addToCell(payroll.getFestAdvRcry()+"", font, true));
		table.addCell(addToCell(lmDetails.getFestAdvRcry()+"", font, true));
		table.addCell(addToCell(payroll.getFestAdvRcry() - lmDetails.getFestAdvRcry()+"", font, true));
		
		table.addCell(addToCell(srNo++ +"", font));
		table.addCell(addToCell(PdfUtils.LIC, font));
		table.addCell(addToCell(payroll.getLic()+"", font, true));
		table.addCell(addToCell(lmDetails.getLic()+"", font, true));
		table.addCell(addToCell(payroll.getLic() - lmDetails.getLic()+"", font, true));
		
		table.addCell(addToCell(srNo++ +"", font));
		table.addCell(addToCell(PdfUtils.SOCIETY, font));
		table.addCell(addToCell(payroll.getSocity()+"", font, true));
		table.addCell(addToCell(lmDetails.getSocity()+"", font, true));
		table.addCell(addToCell(payroll.getSocity() - lmDetails.getSocity()+"", font, true));
		
		table.addCell(addToCell(srNo++ +"", font));
		table.addCell(addToCell(PdfUtils.GIS, font));
		table.addCell(addToCell(payroll.getGis()+"", font, true));
		table.addCell(addToCell(lmDetails.getGis()+"", font, true));
		table.addCell(addToCell(payroll.getGis()- lmDetails.getGis()+"", font, true));
		
		table.addCell(addToCell(srNo++ +"", font));
		table.addCell(addToCell(PdfUtils.BANKLOANREC, font));
		table.addCell(addToCell(payroll.getBankLoanRcry()+"", font, true));
		table.addCell(addToCell(lmDetails.getBankLoanRcry()+"", font, true));
		table.addCell(addToCell(payroll.getBankLoanRcry() - lmDetails.getBankLoanRcry()+"", font, true));
		
		table.addCell(addToCell(srNo++ +"", font));
		table.addCell(addToCell(PdfUtils.VEHLOANREC, font));
		table.addCell(addToCell(payroll.getVlr()+"", font, true));
		table.addCell(addToCell(lmDetails.getVlr()+"", font, true));
		table.addCell(addToCell(payroll.getVlr() - lmDetails.getVlr()+"", font, true));
		
		table.addCell(addToCell(srNo++ +"", font));
		table.addCell(addToCell(PdfUtils.PFSCPFS, font));
		table.addCell(addToCell(payroll.getPfsCpf()+"", font, true));
		table.addCell(addToCell(lmDetails.getPfsCpf()+"", font, true));
		table.addCell(addToCell(payroll.getPfsCpf() - lmDetails.getPfsCpf()+"", font, true));
		
		table.addCell(addToCell(srNo++ +"", font));
		table.addCell(addToCell(PdfUtils.APFACPF, font));
		table.addCell(addToCell(payroll.getApfAcf()+"", font, true));
		table.addCell(addToCell(lmDetails.getApfAcf()+"", font, true));
		table.addCell(addToCell(payroll.getApfAcf() - lmDetails.getApfAcf()+"", font, true));
		
		table.addCell(addToCell(srNo++ +"", font));
		table.addCell(addToCell(PdfUtils.PFLOANREC, font));
		table.addCell(addToCell(payroll.getPfLoanRcry()+"", font, true));
		table.addCell(addToCell(lmDetails.getPfLoanRcry()+"", font, true));
		table.addCell(addToCell(payroll.getPfLoanRcry() - lmDetails.getPfLoanRcry()+"", font, true));
		
		table.addCell(addToCell(srNo++ +"", font));
		table.addCell(addToCell(PdfUtils.INCTAX, font));
		table.addCell(addToCell(payroll.getIncomTax()+"", font, true));
		table.addCell(addToCell(lmDetails.getIncomTax()+"", font, true));
		table.addCell(addToCell(payroll.getIncomTax() - lmDetails.getIncomTax()+"", font, true));
		
		table.addCell(addToCell(srNo++ +"", font));
		table.addCell(addToCell(PdfUtils.OTHERDED, font));
		table.addCell(addToCell(payroll.getOtherDeducs()+"", font, true));
		table.addCell(addToCell(lmDetails.getOtherDeducs()+"", font, true));
		table.addCell(addToCell(payroll.getOtherDeducs() - lmDetails.getOtherDeducs()+"", font, true));
		
		table.addCell(addToCell(srNo++ +"", font));
		table.addCell(addToCell(PdfUtils.MISC, font));
		table.addCell(addToCell(payroll.getMisc()+"", font, true));
		table.addCell(addToCell(lmDetails.getMisc()+"", font, true));
		table.addCell(addToCell(payroll.getMisc() - lmDetails.getMisc()+"", font, true));
		
		table.addCell(addToCell(srNo++ +"", font));
		table.addCell(addToCell(PdfUtils.UNIONFEE, font));
		table.addCell(addToCell(payroll.getUnionFee()+"", font, true));
		table.addCell(addToCell(lmDetails.getUnionFee()+"", font, true));
		table.addCell(addToCell(payroll.getUnionFee() - lmDetails.getUnionFee()+"", font, true));
		return srNo;
	}
	
}
