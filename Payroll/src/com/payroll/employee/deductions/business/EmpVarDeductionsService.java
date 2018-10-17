package com.payroll.employee.deductions.business;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.payroll.employee.attendance.dao.EmployeeAttendanceDAO;
import com.payroll.employee.attendance.dataobjects.EmployeeAttendance;
import com.payroll.employee.deductions.dao.EmpVarDeductionsDAO;
import com.payroll.employee.deductions.dataobjects.EmpVarDeductions;
import com.payroll.employee.deductions.dataobjects.EmpVarDeductionsVO;

public class EmpVarDeductionsService {
	
	private static final DateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	public List<EmpVarDeductions> getEmpVarDeductions(){
		return new EmpVarDeductionsDAO().getEmpVarDeductions();
	}
	
	public String addUpdateEmpDeductions(EmpVarDeductions empVarDeductions){
		EmpVarDeductionsVO empVarDeductionVO = copyProperties(empVarDeductions);
		return new EmpVarDeductionsDAO().addUpdateEmpDeductions(empVarDeductionVO);
	}
	public String deleteEmpDeductions(String empId){
		return new EmpVarDeductionsDAO().deleteEmpDeductions(empId);
	}
	
	public EmpVarDeductions getEmpDeductionsById(String empId){
		return new EmpVarDeductionsDAO().getEmpVarDeductions(empId);
	}
	
	private EmpVarDeductionsVO copyProperties(EmpVarDeductions empVarDeductions){
		EmpVarDeductionsVO empVarDeductionsVO = new EmpVarDeductionsVO();
		try{
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			empVarDeductionsVO.setAfkRent(empVarDeductions.getAfkRent());
			empVarDeductionsVO.setEmployeeId(empVarDeductions.getEmployeeId());
			empVarDeductionsVO.setSociety(empVarDeductions.getSociety());
			empVarDeductionsVO.setPfLoanRecovery(empVarDeductions.getPfLoanRecovery());
			empVarDeductionsVO.setOtherDeductions(empVarDeductions.getOtherDeductions());
			empVarDeductionsVO.setMiscRecovery(empVarDeductions.getMiscRecovery());
			empVarDeductionsVO.setAddUpdate(empVarDeductions.getAddUpdate());
			empVarDeductionsVO.setNote(empVarDeductions.getNote());
			
			empVarDeductionsVO.setMonthDate((empVarDeductions.getMonthDate()!= null ? dateFormat.parse(empVarDeductions.getMonthDate()): new Date()));
			
			
		}catch(Exception e){
			
		}
		return empVarDeductionsVO;
	}
	
	public String addEmployeeVarDeductions(MultipartFile multipartFile) throws ParseException {
		
		String result = null;
		File file = null;
		try {
			file = convertMultiPartToFile(multipartFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Reader reader = null;
		try {
			reader = new FileReader(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		CSVParser parser = new CSVParserBuilder()
			    .withSeparator(',')
			    .withIgnoreQuotations(true)
			    .build();
		
		CSVReader csvReader = new CSVReaderBuilder(reader)
			    .withSkipLines(1)
			    .withCSVParser(parser)
			    .build();
		
		List<EmpVarDeductionsVO> empVarDeductions = new ArrayList<EmpVarDeductionsVO>();
		
		try {
			List<String[]> csvRows = csvReader.readAll();
			ListIterator<String[]> eachRowFromCSVRows = csvRows.listIterator();
			
			while (eachRowFromCSVRows.hasNext()) {
				String[] rd = eachRowFromCSVRows.next();
				if (rd.length != 1) {
				    if (rd[8].endsWith("A")) {
				    	empVarDeductions.add(new EmpVarDeductionsVO(rd[0],Double.valueOf(rd[1]),Double.valueOf(rd[2]),Double.valueOf(rd[3]),
								Double.valueOf(rd[4]), Double.valueOf(rd[5]), Double.valueOf(rd[6]), Double.valueOf(rd[7]), rd[8]));
					}
				    
				}
			}
			
			result = new EmpVarDeductionsDAO().addOrUpdateEmpVarDeductions(empVarDeductions);
			System.out.println("Result " + result);
			
		} catch (IOException e) {
			result = "failure";
			e.printStackTrace();
		}
		
		return result;
	}
	
	private File convertMultiPartToFile(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}
}
