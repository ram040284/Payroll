package com.payroll.rest;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.payroll.Utils;
import com.payroll.department.business.DepartmentService;
import com.payroll.department.dataobjects.Department;
import com.payroll.employee.Employee;
import com.payroll.employee.SearchCriteria;
import com.payroll.employee.business.EmployeeService;
import com.payroll.employee.dataobjects.EmpContact;
import com.payroll.employee.vo.EmpContactVO;
import com.payroll.employee.vo.EmployeeVO;
import com.payroll.headInfo.dataobjects.HeadInfoDAO;
import com.payroll.headInfo.vo.HeadInfoVO;
import com.payroll.login.dao.PermissionsDAO;
import com.payroll.login.dataobjects.User;
import com.payroll.report.business.EmployeeReportService;
import com.payroll.report.vo.EmployeeReportVO;

@Controller
public class EmployeeController {
	
	String permissionForThis = null;
	private PermissionsDAO permissionsDAO = new PermissionsDAO();
	   //@RequestMapping(value = "/employee", method = RequestMethod.POST)
	   //public ModelAndView getEmployees(Employee employee) {
		@RequestMapping(value = "/employee", method = RequestMethod.POST)
		public ModelAndView  getEmployees(com.payroll.employee.Employee employee, HttpServletRequest request) {
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
			permissionForThis = "viewEmployees";
			ModelAndView model = null;
			
			User loggedInUser = (User) request.getSession().getAttribute("user");
			
			if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
				return listResult(employee);
			} else {
				model = new ModelAndView("unauthorized", "message", "You do not have access to view employees. Please click home button to go back.");
			    model.addObject("unauthorizedMessage", true);
			    return model;
			}
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
	   public ModelAndView  viewEmp(com.payroll.employee.Employee employee, HttpServletRequest request) {
		   
		   permissionForThis = "addEmployee";
		   ModelAndView model = null;
			
		   User loggedInUser = (User) request.getSession().getAttribute("user");
			
		   if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
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
				  
				model = new ModelAndView("employee", "command", employee);
				model.addObject(employee);
				model.addObject("departments", depJSON);
				//model.addObject("designations", desigJSON);
			 
				return model;
		   } else {
			   model = new ModelAndView("unauthorized", "message", "You do not have access to add employee. Please click home button to go back.");
			   model.addObject("unauthorizedMessage", true);
			   return model;
		   }
		   
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
		public ModelAndView deleteEmp(Employee employee, HttpServletRequest request){
		   
		   permissionForThis = "deleteEmployee";
		   ModelAndView model = null;
			
		   User loggedInUser = (User) request.getSession().getAttribute("user");
			
		   if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
			   System.out.println("employee:"+employee);
			   if(new EmployeeService().deleteEmp(employee.getEmployeeId()))
				   System.out.println("Successfully deleted Employee!!");
			   else
				   System.out.println("Failed to deleted Employee!!");
			   return listResult(employee);
		   } else {
			   model = new ModelAndView("unauthorized", "message", "You do not have access to delete employee. Please click home button to go back.");
			   model.addObject("unauthorizedMessage", true);
			   return model;
		   }
		   
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
	   
	   @RequestMapping(value="/empContactList", method = RequestMethod.GET)
	    public ModelAndView getEmpContactList(HttpServletRequest request){
			  ObjectMapper mapper = new ObjectMapper();
			   List<Department> deptList = new DepartmentService().getDepartments();
			   String depJSON = "";
			   try {
				   depJSON = mapper.writeValueAsString(deptList);
			   }catch (Exception e) {
				   e.printStackTrace();
			   }
			  
			   SearchCriteria employee = new SearchCriteria();
			   ModelAndView model = new ModelAndView("empContactList", "command", employee);
			   model.addObject("employee", employee);
			   request.getSession().setAttribute("departments", depJSON);
			   request.getSession().removeAttribute("employees");
			   return model;
	    }
	   
	   @RequestMapping(value={"/empContactListSearch"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
	   public ModelAndView getEmpContactListSearch(HttpServletRequest request, SearchCriteria search)
	   {
	     List<EmployeeReportVO> employeesList = new EmployeeReportService().getEmployees(search.getDepartmentId(), search.getHeadId());
	     
	     List<EmployeeReportVO> employees = new ArrayList();
	     String name = search.getFirstName() != null ? search.getFirstName().trim() : "";
	     if ((!name.equals("")) && (employeesList != null) && (employeesList.size() != 0)) {
	       for (EmployeeReportVO empVO : employeesList) {
	         if (empVO.getFullName().toUpperCase().contains(name.toUpperCase())) {
	           employees.add(empVO);
	         }
	       }
	     } else {
	       employees = employeesList;
	     }
	     
	     request.getSession().setAttribute("employees", employees);
	     ModelAndView model = new ModelAndView("empContactList", "command", search);
	     model.addObject("employee", search);
	     if (search.getDepartmentId() != 0)
	     {
	       HeadInfoDAO dao = new HeadInfoDAO();
	       List<HeadInfoVO> headList = dao.getHeadInfoList(search.getDepartmentId());
	       String headJSON = "";
	       try
	       {
	         ObjectMapper mapper = new ObjectMapper();
	         headJSON = mapper.writeValueAsString(headList);
	       }
	       catch (Exception e)
	       {
	         e.printStackTrace();
	       }
	       model.addObject("headDetails", headJSON);
	     }
	     return model;
	   }
	   
	   @RequestMapping(value = "/viewEmpContact", method = RequestMethod.POST)
	   public ModelAndView  viewEmpContact(HttpServletRequest request, SearchCriteria employee) {
		   EmpContactVO empContactVO = null;
			if(employee.getEmployeeId() !=0){
				empContactVO = new EmployeeService().getEmployeeContactDetailsById(employee.getEmployeeId());
			} 
			if (empContactVO == null){
				empContactVO = new EmpContactVO();
			}
			System.out.println("empContactVO:"+empContactVO);
			request.getSession().setAttribute("states", new EmployeeService().getIndianStates());
			ModelAndView model = new ModelAndView("empContact", "command", empContactVO);
			model.addObject("empContact", empContactVO);
		 
			return model;
	   }
	   
	   @RequestMapping(value = "/addUpdateEmpContact", method = RequestMethod.POST)
	   public ModelAndView  addUpdateEmpContact(EmpContact empContact, SearchCriteria employee) {
		   boolean result = false;
			if(empContact.getEmployeeId() !=0){
				result = new EmployeeService().addUpdateEmpContact(empContact);
			} 
			System.out.println(empContact.getEmployeeId() +" :result:"+result);
			
			//SearchCriteria employee = new SearchCriteria();
			
			ModelAndView model = new ModelAndView("empContactList", "command", employee);
			model.addObject("employee", employee);
		 
			return model;
	   }
	   
	   @RequestMapping(value={"/empContactListBack"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
	   public ModelAndView getEmpContactListBack(HttpServletRequest request, SearchCriteria search)
	   {
	     ModelAndView model = new ModelAndView("empContactList", "command", search);
	     model.addObject("employee", search);
	     if (search.getDepartmentId() != 0)
	     {
	       HeadInfoDAO dao = new HeadInfoDAO();
	       List<HeadInfoVO> headList = dao.getHeadInfoList(search.getDepartmentId());
	       String headJSON = "";
	       try
	       {
	         ObjectMapper mapper = new ObjectMapper();
	         headJSON = mapper.writeValueAsString(headList);
	       }
	       catch (Exception e)
	       {
	         e.printStackTrace();
	       }
	       model.addObject("headDetails", headJSON);
	     }
	     return model;
	   }
}	
