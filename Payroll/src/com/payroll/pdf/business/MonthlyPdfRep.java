/*package com.payroll.pdf.business;

import java.util.Date;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import com.payroll.Utils;
import com.payroll.paybill.vo.PaybillBean;

public class MonthlyPdfRep extends PdfBuilder{
	
	public void mthlyCompReport(Document doc, List<PaybillBean> beanList, String imgPath){
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
        firstLine.addCell(addToCell("Sr. No.", frtHdFont));
        firstLine.addCell(addToCell("Description", frtHdFont));
        firstLine.addCell(addToCell("Current Month - "+Utils.getMonthYear(new Date()), frtHdFont, true));
        firstLine.addCell(addToCell("Last Month - "+Utils.getLastMonthYear(new Date()), frtHdFont, true));
        firstLine.addCell(addToCell("Difference", frtHdFont, true));
        doc.add(firstLine);
        doc.add(dottedline);
        
        firstLine = createPdfPTable(5, 15, tabWidths);
        firstLine.addCell(addToCell("", frtHdFont));
        firstLine.addCell(addToCell("No. of Employees:", frtHdFont));
        firstLine.addCell(addToCell("5.00", frtHdFont, true));
        firstLine.addCell(addToCell("5.00", frtHdFont, true));
        firstLine.addCell(addToCell("0.00", frtHdFont, true));
        doc.add(firstLine);
        doc.add(dottedline);
        firstLine = createPdfPTable(5, 2, tabWidths);
        for (PaybillBean paybillBean : beanList) {
        	if(paybillBean.isNewLine()){
        		doc.add(firstLine);
        		doc.add(dottedline);
        		firstLine = createPdfPTable(5, 15, tabWidths);
            	firstLine.addCell(addToCell(paybillBean.getsNo()+"", frtHdFont));
                firstLine.addCell(addToCell(paybillBean.getDescription(), frtHdFont));
                firstLine.addCell(addToCell(paybillBean.getCmValue()+"", frtHdFont, true));
                firstLine.addCell(addToCell(paybillBean.getLmValue()+"", frtHdFont, true));
                firstLine.addCell(addToCell(paybillBean.getDifference()+"", frtHdFont, true));
                doc.add(firstLine);
            	doc.add(dottedline);
            	firstLine = createPdfPTable(5, 2, tabWidths);
            }else {
            	firstLine.addCell(addToCell(paybillBean.getsNo()+"", frtHdFont));
            	firstLine.addCell(addToCell(paybillBean.getDescription(), frtHdFont));
            	firstLine.addCell(addToCell(paybillBean.getCmValue()+"", frtHdFont, true));
            	firstLine.addCell(addToCell(paybillBean.getLmValue()+"", frtHdFont, true));
            	firstLine.addCell(addToCell(paybillBean.getDifference()+"", frtHdFont, true));
            }
        }
        doc.add(firstLine);
        doc.add(dottedline);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
*/