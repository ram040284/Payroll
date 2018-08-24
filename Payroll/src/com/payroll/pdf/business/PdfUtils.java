package com.payroll.pdf.business;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class PdfUtils {
	public static double DA_PERCENT = 139.00;
    public static double HRA_PERCENT = 30.00;
    
	public static final String IMG_PATH= "/resources/images/";
	public static final String BASICS = "Basics:";
	public static final String GRADEPAY = "Grade Pay/DP:";
	public static final String DA = "DA:";
	public static final String HRA = "HRA:";
	public static final String CCA = "CCA:";
	public static final String TA = "TA:";
	public static final String NPA = "NPA:";
	public static final String WA = "WA:";
	public static final String CA = "CA:";
	public static final String UNIFORMALW = "Uniform Alw:";
	public static final String FLYPLGALW = "Fly/Plg Alw:";
	public static final String TALW = "TAlw:";
	public static final String OTAMT = "OTAmt:";
	public static final String OTHERS = "Others:";
	public static final String GROSS = "Gross Pay:";
	public static final String SNO = "Sr.No.:";
	public static final String HEAD = "Budget HEAD:";
	public static final String EMPLOYEE_ID = "Employee Id:";
	public static final String CODE_NAME = "Code Name:";
	public static final String DESIGNATION = "Designation:";
	public static final String BANK_NAME = "Bank Name:";
	public static final String BANK_ACCTNO = "Bank A/c No.:";
	public static final String BIRTH_DATE = "Birth Date:";
	public static final String APPOINTMENT_DATE = "Appointment Dt.:";
	public static final String RETIRE_DATE = "Retirement Dt.:";
	public static final String SCALE = "Scale:";
	public static final String INC_MONTH = "Increment Mth.:";
	public static final String INC_AMT = "Increment Amt.:";
	public static final String PAN = "PAN:";
	public static final String DA_RATE = "DA Rate:";
	public static final String HRA_RATE = "HRA Rate:";
	public static final String L_FEES = "L. Fees:";
	public static final String AFKRENT = "AFKRent:";
	public static final String RENT = "Rent:";
	public static final String ABSDED = "AbsDed:";
	public static final String FAREC = "Fest. Adv. Rec:";
	public static final String PTAX = "P.Tax:";
	public static final String LIC = "LIC:";
	public static final String SOCIETY = "Society:";
	public static final String GIS = "GIS:";
	public static final String BANKLOANREC = "Bank Loan Rec:";
	public static final String VEHLOANREC = "Veh. Loan Rec:";
	public static final String PFSCPFS = "PF/CPF[6%/10%]:";
	public static final String APFACPF = "APF/ACPF:";
	public static final String PFLOANREC = "PF Loan Rec:";
	public static final String CPFREC = "CPFRec:";
	public static final String INCTAX = "Inc. Tax:";
	public static final String UNIONFEE = "Union Fee:";
	public static final String ELECREC = "Elec. Rec:";
	public static final String COURTREC = "Court Rec:";
	public static final String OTHERDED = "Other Ded:";
	public static final String MISC = "MISC:";
	public static final String UNFEE_KSS = "Union Fee (KSS):";
	public static final String TOTDEDUCTION = "Total Deductions:";
	public static final String PFINST = "PF Inst.:";
	public static final String NETPAY = "Net Pay:";
	public static final String INCOME_SIDE = "Income Side:";
	public static final String DEDUCT_SIDE = "Deduction Side:";
	
	public static final String DEDUCTIONS = "Deductions:";
	public static final String EARNINGS = "Earnings:";
	public static final String EARNINGSTOT = "Earnings Totals:";
	public static final String DEDUCTIONSTOT = "Deductions Totals:";
	
	public static final String EMPNO = "Employee#:";
	public static final String EMPNAME = "Name:";
	public static final String GENDER = "Gender:";
	public static final String DEPARTMENT = "Department:";
	public static final String PF_NO = "PF No:";
	public static final String IN_WORDS = "In Words - Rupees:";
	
	
	public static final String COMP_NAME = "KHADKI CANTONMENT BOARD";
	public static final String ADD_LINE1 = "17, Field Marshall, Cariappa Road, Khadki,";
	public static final String ADD_LINE2 = "Pune, Maharashtra 411003";
	public static final String[] PBITEMS = {"Basics", "Grade Pay/DP", "DA", "HRA", "CCA", "TA", "NPA", "WA", "CA",
			"Uniform Alw", "Fly/Plg Alw", "Total Alw", "OTAmt", "Others", "Grosspay", "Rent", "AFK Rent", "Absent Deductions",
			"Fest.Adv.Recovery", "PT", "LIC", "Society", "GIS", "Bank Loan Recovery", "VLR", "PFS+CPF", "APF+ACPF", "PF Loan Recovery",
			"CPF Recovery", "I. Tax", "Union Fees", "Elec.Recovery", "Court Recovery", "Other Deduction(s)", "Misc.",
			"Total Deductions", "Netpay"};
	public static final String[] MTHLY_RPT_HEAD = {"S. No.", "Description", "Current Month - ", "Last Month - ", "Difference"};
	public static final String NOOFEMPS = "No. of Employees:";
	public static com.itextpdf.text.Image getWaterMarkImg(String waterMarkImg){
		com.itextpdf.text.Image wmarkimge = null;
		try{
		  wmarkimge = com.itextpdf.text.Image.getInstance(waterMarkImg);
	      wmarkimge.scaleToFit(350f, 350f);
	      wmarkimge.setAbsolutePosition(240f, 600f);
		}catch(Exception e){
			e.printStackTrace();
		}
		return wmarkimge;
	}
	public static PdfPTable getMainHeader(String imagePath){
		PdfPTable head1_tbl = null;
		try{
			float [] mainHeaderColWidth = {90F, 400F};
		      head1_tbl = new PdfPTable(mainHeaderColWidth);
		      head1_tbl.getDefaultCell().setBorder(0);
		      head1_tbl.setHorizontalAlignment(Element.ALIGN_LEFT);
		      float[] headTextWidth = {350F};
		      PdfPTable headTextTab = new PdfPTable(headTextWidth);
		      headTextTab.getDefaultCell().setBorder(0);
		      headTextTab.setHorizontalAlignment(Element.ALIGN_RIGHT);
		      
		      Font titleFont = FontFactory.getFont(FontFactory.HELVETICA);
		      titleFont.setSize(12);
		      titleFont.setColor(BaseColor.BLUE);
		      
		      Font addressFont = FontFactory.getFont(FontFactory.HELVETICA);
		      addressFont.setSize(9);
		      
		      headTextTab.addCell(addToCell(4, COMP_NAME, titleFont));
		      headTextTab.addCell(addToCell(3, ADD_LINE1, addressFont));
		      headTextTab.addCell(addToCell(3, ADD_LINE2, addressFont));
		      
		      PdfPCell headCell = new PdfPCell();
		      headCell.setBorder(0);              
		      com.itextpdf.text.Image logoImg = com.itextpdf.text.Image.getInstance(imagePath);
		      logoImg.scaleToFit(75f, 80f);
		      logoImg.setAbsolutePosition(5f, 35f);
		      headCell.setPadding(10);
		      headCell.addElement(logoImg);
		      head1_tbl.addCell(headCell);
		      headCell = new PdfPCell();
		      headCell.setBorder(0);
		      headCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		      headCell.addElement(headTextTab);
		      head1_tbl.addCell(headCell);
		      
		}catch(Exception e){
			e.printStackTrace();
		}
		return head1_tbl;
	}
	
	private static PdfPCell addToCell(int padding, String text, Font font){
		  PdfPCell cell = new PdfPCell();
		  cell.setBorder(0);
		  cell.setPadding(padding);
		  cell.setPhrase(new Phrase(text, font));
	      return cell;
	}

}
