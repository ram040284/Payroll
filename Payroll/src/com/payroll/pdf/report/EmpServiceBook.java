package com.payroll.pdf.report;

import java.util.Date;
import java.util.Iterator;

import com.itextpdf.io.IOException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import com.payroll.Utils;
import com.payroll.employee.servicebill.dataobject.EmpServiceBill;
import com.payroll.hrms.payroll.dataobjects.PaybillDetails;
import com.payroll.hrms.payroll.dataobjects.ReportDetails;
import com.payroll.pdf.business.PdfBuilder;
import com.payroll.pdf.business.PdfUtils;

public class EmpServiceBook  extends PdfBuilder{
	int srNo = 0;
	Font boldFont = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.BOLD);
	static Font headHdFont = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD);
	
	public static final String DEST = "results/tables/cell_method.pdf";
    public static final String FONT = "resources/fonts/FreeSans.ttf";
	
	public void empServiceBook(Document doc, EmpServiceBill empServiceBill, String imgPath){
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
	        String deptName = Utils.safeTrim(empServiceBill.getfName()+" "+empServiceBill.getmName()+" "+empServiceBill.getlName());
	        StringBuffer titleText = new StringBuffer("Employee Service Book ");
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
	        PdfPTable paybillTab3 = null; 
	        
	        paybillTab2 = createPdfPTable(4, 6, tabWidths);
	        paybillTab3 = createPdfPTable(4, 5, tabWidths);
	        createFirstTable(paybillTab2, empServiceBill,frtHdFont);
	        //empBankDetails(paybillTab3, empServiceBill);
	        doc.add(paybillTab2);
	        //doc.add(paybillTab3);
	        
	        
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void createFirstTable(PdfPTable paybillTab2, EmpServiceBill empServiceBill, Font font) throws DocumentException {
		
		float tabWidths[] = {2.8f, 2.2f, 1.9f, 2.2f};
		Font fontStandard = FontFactory.getFont("Arial", 10);
		int w[] = { 50, 50 };
		
        // the cell object
        PdfPCell cell;
        // we add a cell with colspan 3
        cell = new PdfPCell(getNormalCell("Employee Details", null, 15));
        //cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setColspan(6);
        paybillTab2.addCell(cell);
        paybillTab2.addCell(getNormalCell("Employee Name", null, 7));
        paybillTab2.addCell(getNormalCell(empServiceBill.getfName() + " " + empServiceBill.getmName() + " " + empServiceBill.getlName(), null, 7));
        paybillTab2.addCell(getNormalCell("Date Of Birth", null, 7));
        paybillTab2.addCell(getNormalCell(empServiceBill.getBirthDate().toString(), null, 7));
        paybillTab2.addCell(getNormalCell("Designation", null, 7));
        paybillTab2.addCell(getNormalCell(empServiceBill.getDesgname(), null, 7));
        paybillTab2.addCell(getNormalCell("Joining Date", null, 7));
        paybillTab2.addCell(getNormalCell(empServiceBill.getEmpJoiningDate().toString(), null, 7));
        paybillTab2.addCell(getNormalCell("Department Name", null, 7));
        paybillTab2.addCell(getNormalCell(empServiceBill.getEmpJoiningDate().toString(), null, 7));
        paybillTab2.addCell(getNormalCell("Bank Name", null, 7));
        paybillTab2.addCell(getNormalCell(empServiceBill.getBankName(), null, 7));
        paybillTab2.addCell(getNormalCell("Employee Pan", null, 7));
        paybillTab2.addCell(getNormalCell(empServiceBill.getEmpPan(), null, 7));
        paybillTab2.addCell(getNormalCell("IFSC CODE", null, 7));
        paybillTab2.addCell(getNormalCell(empServiceBill.getIfscCode(), null, 7));
        paybillTab2.addCell(getNormalCell("Basic", null, 7));
        paybillTab2.addCell(addToCell(Utils.getDecimalFormat(empServiceBill.getBasic()), font));
        paybillTab2.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
    }
	
public void empBankDetails(PdfPTable paybillTab2, EmpServiceBill empServiceBill) throws DocumentException {
		
		float tabWidths[] = {2.8f, 2.2f, 1.9f, 2.2f};
		Font fontStandard = FontFactory.getFont("Arial", 10);
		int w[] = { 50, 50 };
		
        // the cell object
        PdfPCell cell;
        // we add a cell with colspan 3
        cell = new PdfPCell(getNormalCell("Employee Details", null, 15));
        //cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setColspan(6);
        paybillTab2.addCell(cell);
        paybillTab2.addCell(getNormalCell("Bank Name", null, 7));
        paybillTab2.addCell(getNormalCell(empServiceBill.getBankName(), null, 7));
        paybillTab2.addCell(getNormalCell("Employee Pan", null, 7));
        paybillTab2.addCell(getNormalCell(empServiceBill.getEmpPan(), null, 7));
        paybillTab2.addCell(getNormalCell("IFSC CODE", null, 7));
        paybillTab2.addCell(getNormalCell(empServiceBill.getIfscCode(), null, 7));
        paybillTab2.addCell(getNormalCell("Joining Date", null, 7));
        paybillTab2.addCell(getNormalCell(empServiceBill.getEmpJoiningDate().toString(), null, 7));
        paybillTab2.addCell(getNormalCell("Department Name", null, 7));
        paybillTab2.addCell(getNormalCell(empServiceBill.getEmpJoiningDate().toString(), null, 7));
        paybillTab2.addCell("");
        paybillTab2.addCell("");
        paybillTab2.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
    }
	
	
	
	public static PdfPCell getNormalCell(String string, String language, float size)
            throws DocumentException, IOException {
        if(string != null && "".equals(string)){
            return new PdfPCell();
        }
        Font f  = headHdFont;
        if(size < 0) {
            f.setColor(BaseColor.RED);
            size = -size;
        }
        f.setSize(size);
        PdfPCell cell = new PdfPCell(new Phrase(string, f));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        return cell;
    }
	
	public static Font getFontForThisLanguage(String language) {
        if ("czech".equals(language)) {
            return FontFactory.getFont(FONT, "Cp1250", true);
        }
        if ("greek".equals(language)) {
            return FontFactory.getFont(FONT, "Cp1253", true);
        }
        return FontFactory.getFont(FONT, null, true);
    }
	
}
