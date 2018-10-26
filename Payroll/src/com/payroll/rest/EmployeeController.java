package com.payroll.rest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

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
	
	private static final String UPLOADED_FOLDER = null;
	String permissionForThis = null;
	private PermissionsDAO permissionsDAO = new PermissionsDAO();
	   //@RequestMapping(value = "/employee", method = RequestMethod.GET)
	   //public ModelAndView getEmployees(Employee employee) {
		@RequestMapping(value = "/employee", method = RequestMethod.GET)
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
		/* check
		 @RequestMapping(value="/getEmployeeList", method = RequestMethod.GET, produces = "application/json")
		    public @ResponseBody List<EmployeeVO> getEmployeeList(@RequestParam String firstName, @RequestParam String departmentId, @RequestParam String headId){
			//   List<EmployeeVO> employees = new EmployeeService().getEmployees(0,0,null);		
			 List<EmployeeVO> employees = null;
			 //Employee employee = new Employee();
			   if(departmentId != "0" || firstName != null || firstName != ""){
				   employees = new EmployeeService().getEmployees(
					   Integer.valueOf(departmentId), Integer.valueOf(headId), firstName);
			   }
		       return employees;
		    }
	   	*/	@RequestMapping(value="/getEmployeeList", method = RequestMethod.GET, produces = "application/json")
 public @ResponseBody List<EmployeeVO> getEmployeeList(@RequestParam String firstName, @RequestParam String departmentId, @RequestParam String headId,@RequestParam String employeeType, HttpServletRequest request){

	   			
	 List<EmployeeVO> employees = null;
            permissionForThis = "viewEmployees";
			ModelAndView model = null;
			
			User loggedInUser = (User) request.getSession().getAttribute("user");
			
			if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
			//	List<EmployeeVO> employees = null;
				 if(departmentId != "0" || firstName != null || firstName != ""){
					employees = new EmployeeService().getEmployees(
					Integer.valueOf(departmentId), Integer.valueOf(headId), firstName,Integer.valueOf(employeeType));
					}
				return employees;
			} else {
				model = new ModelAndView("unauthorized", "message", "You do not have access to view employees. Please click home button to go back.");
			    model.addObject("unauthorizedMessage", true);
			    return (List<EmployeeVO>) model;
			}
 
}
	   	
	   	
	   	//check
	   @RequestMapping(value="/view", method = RequestMethod.GET, produces = "application/json")
	    public @ResponseBody List<EmployeeVO> getEmployees(HttpServletRequest request){  
		   permissionForThis = "viewEmployees";
			ModelAndView model = null;
			
			User loggedInUser = (User) request.getSession().getAttribute("user");
			
			if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
				List<EmployeeVO> employees = new EmployeeService().getEmployees(0,0,null,1);
				return employees;
			} else {
				model = new ModelAndView("unauthorized", "message", "You do not have access to view employees. Please click home button to go back.");
			    model.addObject("unauthorizedMessage", true);
			    return (List<EmployeeVO>) model;
			}
	   }
	   @RequestMapping(value = "/viewEmp", method = RequestMethod.GET)
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
			if(!employee.getEmployeeId().equalsIgnoreCase("0")){
					employee = new EmployeeService().getEmployeeById(employee.getEmployeeId());
				//System.out.println("employee type is :"+employee.getEmployeeType());
				}
			//System.out.println("employee type is :"+employee.getEmployeeType());
				  
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
	   
	   @RequestMapping(value="/deleteEmp",method=RequestMethod.GET)
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
				   employee.getDepartmentId(), employee.getHeadId(), employee.getFirstName(),employee.getEmployeeType());
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
			   model.addObject("departments", depJSON);
			   //request.getSession().setAttribute("departments", depJSON);
			   request.getSession().removeAttribute("employees");
			   return model;
	    }
	   
	   @RequestMapping(value={"/empContactListSearch"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
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
	   
	   @RequestMapping(value = "/viewEmpContact", method = RequestMethod.GET)
	   public ModelAndView  viewEmpContact(HttpServletRequest request, SearchCriteria employee) {
		   EmpContactVO empContactVO = null;
			if(!employee.getEmployeeId().equalsIgnoreCase("0")){
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
			if(empContact.getEmployeeId() !="0"){
				result = new EmployeeService().addUpdateEmpContact(empContact);
			} 
			System.out.println(empContact.getEmployeeId() +" :result:"+result);
			
			//SearchCriteria employee = new SearchCriteria();
			
			ModelAndView model = new ModelAndView("empContactList", "command", employee);
			model.addObject("employee", employee);
		 
			return model;
	   }
	   
	   @RequestMapping(value={"/empContactListBack"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
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
	   
	   @RequestMapping(value = "/getEmployeeServiceBook", method = RequestMethod.POST)
	   public ModelAndView  getEmployeeServiceBook(com.payroll.employee.Employee employee, HttpServletRequest request) {
		   
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
			if(!employee.getEmployeeId().equalsIgnoreCase("0")){
					employee = new EmployeeService().getEmployeeById(employee.getEmployeeId());
				//System.out.println("employee type is :"+employee.getEmployeeType());
				}
			//System.out.println("employee type is :"+employee.getEmployeeType());
				  
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
		/*@RequestMapping(value = "/saveEmp", method= RequestMethod.POST)
		public String saveEmp(@RequestPart(value = "file") MultipartFile multipartFile) throws ParseException {
			new EmployeeService().saveEmp(multipartFile);
			return "redirect:/viewEmp";
			//return "viewEmpAlwnce";
		}*/
		
	   private String saveDirectory = "E:\\payroll workspace\\Payroll\\Payroll\\WebContent\\resources\\images\\";

		@RequestMapping(value = "/uploadFile/{id}", method= RequestMethod.POST)
		private String handleFileUpload(HttpServletRequest request,
         @RequestParam CommonsMultipartFile[] fileUpload,
         @PathParam("id") String id) throws Exception 
		{
		
		
		String relativePath = request.getContextPath();
		//	 @RequestParam("file") MultipartFile file)
        ModelAndView model = null;
        com.payroll.employee.Employee employee = null;
			/*User loggedInUser = (User) request.getSession().getAttribute("user");
			
			if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
				//return listResult(employee);
			}
			 
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
	        System.out.println("description: " + request.getParameter("description"));*/
	        
	        if (fileUpload != null && fileUpload.length > 0) {
	            for (CommonsMultipartFile aFile : fileUpload){
	            System.out.println("Saving file: " + aFile.getOriginalFilename());
	                String filePath = saveDirectory + id +".pdf";
	                System.out.println("file path: " + filePath);

	                if (!aFile.getOriginalFilename().equals("")) {
	                    aFile.transferTo(new File(filePath));
	                    if(id != null){
	                    	employee = new EmployeeService().getEmployeeById(id);
	    				}
	    				
	                   // model = new ModelAndView(new RedirectView("/employee"));
	                   // return model;

	                    //return ((ModelAndView)listResult(employee)).setViewName("");;
	                }
	            }
	        
	        // returns to the view "Result"
	          
//	        }
			


	    }
    		return "redirect:/employee";

			  //return model;
	}
}
