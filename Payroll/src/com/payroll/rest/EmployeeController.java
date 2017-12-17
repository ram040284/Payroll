package com.payroll.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.payroll.department.dataobjects.Department;
import com.payroll.Utils;
import com.payroll.department.business.DepartmentService;
import com.payroll.designation.dataobjects.Designation;
import com.payroll.employee.Employee;
import com.payroll.employee.business.EmployeeService;
import com.payroll.employee.vo.EmployeeVO;
import com.payroll.designation.business.DesignationService;

import org.springframework.ui.ModelMap;

@Controller
public class EmployeeController {
	   //@RequestMapping(value = "/employee", method = RequestMethod.POST)
	   //public ModelAndView getEmployees(Employee employee) {
		@RequestMapping(value = "/employee", method = RequestMethod.POST)
		public ModelAndView  getEmployees(com.payroll.employee.Employee employee) {
		   /*ObjectMapper mapper = new ObjectMapper();
		   List<Department> deptList = new DepartmentService().getDepartments();
		   String depJSON = "";
		   try {
			   depJSON = mapper.writeValueAsString(deptList);
		   }catch (Exception e) {
			   e.printStackTrace();
		   }
		   if(employee == null)
			   employee = new Employee();
		   
		   List<EmployeeVO> employees = null;
		   if(employee.getDepartmentId() !=0 || !Utils.isEmpty(employee.getFirstName())){
			   employees = new EmployeeService().getEmployees(
				   employee.getDepartmentId(), employee.getHeadId(), employee.getFirstName());
		   }
		   ModelAndView model = new ModelAndView("listEmp", "command", employee);
		   model.addObject("employees", employees);
		   model.addObject("departments", depJSON);
		   return model;*/
			return listResult(employee);
	   }
	   
	   @RequestMapping(value="/view", method = RequestMethod.GET, produces = "application/json")
	    public @ResponseBody List<EmployeeVO> getEmployees(){
		   List<EmployeeVO> employees = new EmployeeService().getEmployees(0,0,null);/*new ArrayList<Employee>();
		   employees.add(new Employee("Rajendra", "Gangarde", "", "Vice President", "raj@gmail.com", "9878687678"));
		   employees.add(new Employee("Ramanjaneyulu", "Kummari", "", "Tech Lead", "ram040284@gmail.com", "8939345488"));
		   employees.add(new Employee("Srinivasa", "Mukku", "", "Tech Lead", "srini.mukku@gmail.com", "98787687686"));*/
				   
	        return employees;
	    }
	   @RequestMapping(value = "/viewEmp", method = RequestMethod.POST)
	   public ModelAndView  viewEmp(com.payroll.employee.Employee employee) {
		   ObjectMapper mapper = new ObjectMapper();
		   
		   List<Department> deptList = new DepartmentService().getDepartments();
		   //List<Designation> desigList = new DesignationService().getDesignationList();
		   
		   String depJSON = "";
		   String desigJSON = "";
			try {
				depJSON = mapper.writeValueAsString(deptList);
				//desigJSON = mapper.writeValueAsString(desigList);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(employee.getEmployeeId() !=0){
				employee = new EmployeeService().getEmployeeById(employee.getEmployeeId());
			}
			System.out.println("employee:"+employee);
			  
			ModelAndView model = new ModelAndView("employee", "command", employee);
			model.addObject(employee);
			model.addObject("departments", depJSON);
			//model.addObject("designations", desigJSON);
		 
			return model;
	   }
	   
	   @RequestMapping(value="/addEmp",method=RequestMethod.POST)
	    public @ResponseBody String addEmp(@RequestBody Employee employee){
	   //public ModelAndView addEmp(@RequestBody Employee employee){
		   System.out.println("Employee"+employee);
		   String result = new EmployeeService().addUpdateEmployee(employee);
		   System.out.println("result:"+result);
		   return result;
		   //return listResult(employee);
	    }
	   
	   @RequestMapping(value="/deleteEmp",method=RequestMethod.POST)
		public ModelAndView deleteEmp(Employee employee){
		   System.out.println("employee:"+employee);
		   if(new EmployeeService().deleteEmp(employee.getEmployeeId()))
			   System.out.println("Successfully deleted Employee!!");
		   else
			   System.out.println("Failed to deleted Employee!!");
		   return listResult(employee);
		}
	   
	   private ModelAndView listResult(Employee employee) {
		   ObjectMapper mapper = new ObjectMapper();
		   List<Department> deptList = new DepartmentService().getDepartments();
		   String depJSON = "";
		   try {
			   depJSON = mapper.writeValueAsString(deptList);
		   }catch (Exception e) {
			   e.printStackTrace();
		   }
		   List<EmployeeVO> employees = null;
		   if(employee.getDepartmentId() !=0 || !Utils.isEmpty(employee.getFirstName())){
			   employees = new EmployeeService().getEmployees(
				   employee.getDepartmentId(), employee.getHeadId(), employee.getFirstName());
		   }

		   ModelAndView model = new ModelAndView("listEmp", "command", employee);
		   model.addObject("employees", employees);
		   model.addObject("departments", depJSON);
		   return model;

	   }
}	
