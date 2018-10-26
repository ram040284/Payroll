package com.payroll.employee.business;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.payroll.Utils;
import com.payroll.employee.allowance.dataobjects.EmpAllowance;
import com.payroll.employee.allowance.dataobjects.EmpAllowanceDAO;
import com.payroll.employee.dataobjects.EmpContact;
import com.payroll.employee.dataobjects.Employee;
import com.payroll.employee.dataobjects.EmployeeDAO;
import com.payroll.employee.vo.EmpContactVO;
import com.payroll.employee.vo.EmployeeVO;

public class EmployeeService {
	public Map getIndianStates() {
		Map states = new TreeMap<String, String>();
		states.put("AP", "Andhra Pradesh");
		states.put("AR", "Arunachal Pradesh");
		states.put("AS", "Assam");
		states.put("BR", "Bihar");
		states.put("CT", "Chhattisgarh");
		states.put("GA", "Goa");
		states.put("GJ", "Gujarat");
		states.put("HR", "Haryana");
		states.put("HP", "Himachal Pradesh");
		states.put("JK", "Jammu and Kashmir");
		states.put("JH", "Jharkhand");
		states.put("KA", "Karnataka");
		states.put("KL", "Kerala");
		states.put("MP", "Madhya Pradesh");
		states.put("MH", "Maharashtra");
		states.put("MN", "Manipur");
		states.put("ML", "Meghalaya");
		states.put("MZ", "Mizoram");
		states.put("NL", "Nagaland");
		states.put("OR", "Odisha");
		states.put("PB", "Punjab");
		states.put("RJ", "Rajasthan");
		states.put("SK", "Sikkim");
		states.put("TN", "Tamil Nadu");
		states.put("TG", "Telangana");
		states.put("TR", "Tripura");
		states.put("UT", "Uttarakhand");
		states.put("UP", "Uttar Pradesh");
		states.put("WB", "West Bengal");
		states.put("AN", "Andaman and Nicobar Islands");
		states.put("CH", "Chandigarh");
		states.put("DN", "Dadra and Nagar Haveli");
		states.put("DD", "Daman and Diu");
		states.put("DL", "Delhi");
		states.put("LD", "Lakshadweep");
		states.put("PY", "Puducherry");
		
		return states;
	}
	public List<EmployeeVO> getEmployees(int deptId, int headId, String name, int employeeType){
		return new EmployeeDAO().getEmployees(deptId, headId, name, employeeType);
	}
	public List<EmployeeVO> getEmployees(int deptId, int desgId){
		return new EmployeeDAO().getEmployees(deptId, desgId);
	}
	
	public List<EmployeeVO> getEmployees(int desgId){
		return new EmployeeDAO().getEmployeesByDesgId(desgId);
	}
	
	public List<EmployeeVO> getAllEmployees(){
		return new EmployeeDAO().getAllEmployees();
	}
	
	public List<EmployeeVO> getAllContactEmployees(){
		return new EmployeeDAO().getAllContractEmployees();
	}
	
	public String addUpdateEmployee(com.payroll.employee.Employee emp){
			return new EmployeeDAO().addUpdateEmployee(copyEmp(emp));
	}
	public com.payroll.employee.Employee getEmployeeById(String empId){
		return copyDBEmp(new EmployeeDAO().getEmployeeById(empId));
	}
	
	public List<Employee> getEmployeeByEmployeeId(String id) {
		return new EmployeeDAO().getEmployeeByEmployeeId(id);
	}
	
	public EmpContactVO getEmployeeContactDetailsById(String empId){
		return new EmployeeDAO().getEmployeeContactDetailsById(empId);
	}
	
	public boolean addUpdateEmpContact(EmpContact empContact){
		return new EmployeeDAO().addUpdateEmpContact(empContact);
	}
	public boolean deleteEmp(String empId){
		return new EmployeeDAO().deleteEmp(empId);
	}
	
	public com.payroll.employee.Employee getEmployeeServiceBook(String empId){
		return copyDBEmp(new EmployeeDAO().getEmployeeServiceBook(empId));
	}
		
	private Employee copyEmp(com.payroll.employee.Employee emp){
		Employee dbEmp = null;
		try{
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			dbEmp =  new Employee();
			dbEmp.setAddressLine1(emp.getAddressLine1());
			dbEmp.setAddressLine2(emp.getAddressLine2());
			dbEmp.setAddressLine3(emp.getAddressLine3());
			dbEmp.setAdharNo(emp.getAdharNo());
			dbEmp.setContactNo(emp.getContactNo());
			dbEmp.setDepartmentId(emp.getDepartmentId());
			dbEmp.setJoiningDate(!Utils.isEmpty(emp.getJoiningDate())? dateFormat.parse(emp.getJoiningDate()): 
				new Date());
			dbEmp.setDob(!Utils.isEmpty(emp.getDob())? dateFormat.parse(emp.getDob()):new Date());
			dbEmp.setEmail(emp.getEmail());
			dbEmp.setFirstName(emp.getFirstName());
			dbEmp.setLastName(emp.getLastName());
			dbEmp.setMiddleName(emp.getMiddleName());
			dbEmp.setPhone(emp.getPhone());
			dbEmp.setPan(emp.getPan());
			dbEmp.setHandicapFlag(emp.getHandicapFlag());
			dbEmp.setEmployeeId(emp.getEmployeeId());
			dbEmp.setDesignationId(emp.getDesignationId());
			dbEmp.setGender(emp.getGender());
			dbEmp.setHeadId(emp.getHeadId());
			dbEmp.setEmployeeType(emp.getEmployeeType());
			if(!Utils.isEmpty(emp.getRetirementDate()))
				dbEmp.setRetirementDate(dateFormat.parse(emp.getRetirementDate()));
			
			//dbEmp.setRowUpdatedDate(new Date());
		}catch(Exception e){
			e.printStackTrace();
		}
		return dbEmp;
	}
	
	private com.payroll.employee.Employee copyDBEmp(EmployeeVO emp){
		com.payroll.employee.Employee dbEmp = null;
		try{
		//SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			dbEmp =  new com.payroll.employee.Employee();
			dbEmp.setAddressLine1(emp.getAddressLine1());
			dbEmp.setAddressLine2(emp.getAddressLine2());
			dbEmp.setAddressLine3(emp.getAddressLine3());
			dbEmp.setAdharNo(emp.getAdharNo());
			dbEmp.setContactNo(emp.getContactNo());
			dbEmp.setDepartmentId(emp.getDepartmentId());
			dbEmp.setJoiningDate(emp.getJoiningDate());
			dbEmp.setDob(emp.getDob());
			dbEmp.setEmail(emp.getEmail());
			dbEmp.setFirstName(emp.getFirstName());
			dbEmp.setLastName(emp.getLastName());
			dbEmp.setMiddleName(emp.getMiddleName());
			dbEmp.setPhone(emp.getPhone());
			dbEmp.setPan(emp.getPan());
			dbEmp.setEmployeeId(emp.getEmployeeId());
			dbEmp.setDesignationId(emp.getDesignationId());
			dbEmp.setGender(emp.getGender());
			dbEmp.setHandicapFlag(emp.getHandicapFlag());
			dbEmp.setEmployeeType(emp.getEmployeeType());
			dbEmp.setHeadId(emp.getHeadId());
			dbEmp.setRetirementDate(emp.getRetirementDate());
		}catch(Exception e){
			e.printStackTrace();
		}
		return dbEmp;
	}
}

























