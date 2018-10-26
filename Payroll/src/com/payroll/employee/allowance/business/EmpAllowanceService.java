package com.payroll.employee.allowance.business;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.payroll.employee.allowance.dataobjects.EmpAllowance;
import com.payroll.employee.allowance.dataobjects.EmpAllowanceDAO;
import com.payroll.employee.allowance.vo.EmpAllowanceVO;
import com.payroll.employee.deductions.dao.EmpVarDeductionsDAO;
import com.payroll.employee.deductions.dataobjects.EmpVarDeductionsVO;

public class EmpAllowanceService {
	
	public List<EmpAllowanceVO> getEmpAllowanceList(){
		return new EmpAllowanceDAO().getEmpAllowanceList();
	}
	
	public String addUpdateEmpAllowance(EmpAllowance empAllowance){
		return new EmpAllowanceDAO().addUpdateEmpAllowance(empAllowance);
	}
	public String deleteEmpAllowance(String empId){
		return new EmpAllowanceDAO().deleteEmpAllowance(empId);
	}
	public EmpAllowanceVO getEmpAllowanceById(String empId){
		return new EmpAllowanceDAO().getEmpAllowanceById(empId);
	}
	
	public double getTotalEmpAllowanceById(String empId){
		EmpAllowanceVO empAllowanceVO = new EmpAllowanceDAO().getEmpAllowanceById(empId);
		double totalEmpAllowance = empAllowanceVO.getCca() + empAllowanceVO.getCycleAlwance()+empAllowanceVO.getFamilyPlanAlwance() +empAllowanceVO.getNonPracAwance() +
				empAllowanceVO.getUniformAlwance() + empAllowanceVO.getWashingAlwance() + empAllowanceVO.getOtherAllowance();
		return totalEmpAllowance;
	}
	
public String addEmployeeAllowances(MultipartFile multipartFile) throws ParseException {
		
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
		
		List<EmpAllowance> empAllowances = new ArrayList<EmpAllowance>();
		
		try {
			List<String[]> csvRows = csvReader.readAll();
			ListIterator<String[]> eachRowFromCSVRows = csvRows.listIterator();
			
			while (eachRowFromCSVRows.hasNext()) {
				String[] rd = eachRowFromCSVRows.next();
				if (rd.length != 1) {
				    if (rd[16].endsWith("A")) {
					empAllowances.add(new EmpAllowance(rd[0],Double.valueOf(rd[1]), Double.valueOf(rd[2]),Double.valueOf(rd[3]),Double.valueOf(rd[4]),Double.valueOf(rd[5])
							,Double.valueOf(rd[6]),Double.valueOf(rd[7]),Double.valueOf(rd[8]),Boolean.valueOf(rd[9]),Boolean.valueOf(rd[10]),Boolean.valueOf(rd[11]),
							Byte.valueOf(rd[12]), Byte.valueOf(rd[13]),Byte.valueOf(rd[14]),rd[15]));
					
					}
				}
			}
			
			result = new EmpAllowanceDAO().addOrUpdateEmpAllowances(empAllowances);
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
