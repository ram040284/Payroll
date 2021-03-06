package com.payroll.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.payroll.Utils;
import com.payroll.department.business.DepartmentService;
import com.payroll.department.dataobjects.Department;
import com.payroll.employee.leave.business.LeaveService;
import com.payroll.employee.leave.dataobjects.Leave;
import com.payroll.employee.leave.dataobjects.LeaveDAO;
import com.payroll.employee.leave.dataobjects.LeaveRequest;
import com.payroll.employee.leave.dataobjects.LeaveRequestVO;
import com.payroll.employee.leave.dataobjects.LeaveType;
import com.payroll.employee.leave.vo.LeaveVO;
import com.payroll.login.dao.PermissionsDAO;
import com.payroll.login.dataobjects.User;

@Controller
public class EmpLeaveController {
	
	String permissionForThis = null;
	
	@RequestMapping(value="/listLeaves", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<LeaveVO> getEmpLeaves(){
	   List<LeaveVO> overtimes = new LeaveService().getLeaves();
	   return overtimes;
    }
	
	@RequestMapping(value="/empLeaveList", method = RequestMethod.GET)
    public ModelAndView getEmpLeaveList(HttpServletRequest request){
		  ObjectMapper mapper = new ObjectMapper();
		   List<Department> deptList = new DepartmentService().getDepartments();
		   String depJSON = "";
		   try {
			   depJSON = mapper.writeValueAsString(deptList);
		   }catch (Exception e) {
			   e.printStackTrace();
		   }
		  
		   LeaveRequest leaveReauest = new LeaveRequest();
		   ModelAndView model = new ModelAndView("empLeaveList", "command", leaveReauest);
		   model.addObject("employee", leaveReauest);
		   request.getSession().setAttribute("departments", depJSON);
		   request.getSession().setAttribute("empLeaveList", new ArrayList<LeaveVO>());
		   return model;
    }
	
	@RequestMapping(value="/empLeaveSearch", method = RequestMethod.GET)
    public ModelAndView getEmpLeaveSearch(HttpServletRequest request, LeaveRequest leaveReauest){
		  ObjectMapper mapper = new ObjectMapper();
		  List<LeaveRequestVO> empLeaveList = new LeaveService().getLeaveRequests(leaveReauest.getDepartmentId(), leaveReauest.getHeadId(), leaveReauest.getFirstName());
		  request.getSession().setAttribute("empLeaveList", empLeaveList);
		   ModelAndView model = new ModelAndView("empLeaveList", "command", leaveReauest);
		   model.addObject("employee", leaveReauest);
		   return model;
    }
	
	@RequestMapping(value="/empLeaveListApply", method = RequestMethod.GET)
    public ModelAndView getEmpLeaveListApply(HttpServletRequest request, LeaveRequest leaveReauest){
		  ObjectMapper mapper = new ObjectMapper();
		  List<LeaveVO> empLeaveList = new LeaveService().getLeavesData(leaveReauest.getListDeptId(), leaveReauest.getListHeadId(), leaveReauest.getListName());
		  request.getSession().setAttribute("empLeaveList", empLeaveList);
		   ModelAndView model = new ModelAndView("empLeaveList", "command", leaveReauest);
		   model.addObject("employee", leaveReauest);
		   return model;
    }
	
	@RequestMapping(value = "/viewLeave_1", method = RequestMethod.GET)
	public ModelAndView viewLeave_1(LeaveVO leaveVO, HttpServletRequest request) {
		//return "listLeaves";
		
		permissionForThis = "viewEmployeeLeaves";
		User loggedInUser = (User) request.getSession().getAttribute("user");
		ModelAndView model = null;
			
		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
			return listResult(leaveVO);
		} else {
			model = new ModelAndView("unauthorized", "message", "You do not have access to view employee leaves. Please click home button to go back.");
			   model.addObject("unauthorizedMessage", true);
			   return model;
		}
	}
	
	@RequestMapping(value = "/viewLeave", method = RequestMethod.GET)
	public ModelAndView viewLeave(LeaveRequest leaveReauest) {
		//return "listLeaves";
		ModelAndView model = null;
		LeaveVO  leaveVoDb = null;
		Map<String, String> leaveTypes = null;
		Map<String, String> leaveBalance = null;
		LeaveRequestVO empLeaveList = null;
		List<Department> deptList = new DepartmentService().getDepartments();
		List<LeaveType> types = new LeaveService().getLeaveTypes();
		   //List<Designation> desigList = new DesignationService().getDesignationList();
			
		ObjectMapper mapper = new ObjectMapper();
		String depJSON = "";
		String leaveJson = "";
		try {
			depJSON = mapper.writeValueAsString(deptList);
			leaveJson = mapper.writeValueAsString(types);
			//desigJSON = mapper.writeValueAsString(desigList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(!leaveReauest.getEmployeeId().equalsIgnoreCase("0")){
			LeaveService service = new LeaveService();
			leaveVoDb = service.getLeaveDetailsById(leaveReauest.getEmployeeId(), leaveReauest.getFirstName());
			leaveTypes = service.getLeaveTypes(leaveVoDb.getEmployee().getGender());
			leaveBalance = service.getLeaveBalance(leaveVoDb);
			empLeaveList = new LeaveService().getEmpLeaveRequest(leaveReauest.getEmployeeId());
		}
		
		model = new ModelAndView("applyLeave", "command", leaveReauest);
		model.addObject("employee", leaveReauest);
		model.addObject("leave", leaveVoDb);
		model.addObject("leaveBalance", leaveBalance);
		model.addObject("leaveRequest", empLeaveList);
		model.addObject("leaveTypes", leaveTypes);
		model.addObject("departments", depJSON);
		model.addObject("leavetype", leaveJson);
		
		
  	    return model;
	}


	@RequestMapping(value = "/applyLeave", method = RequestMethod.POST)
	public @ResponseBody String applyLeave(@RequestBody LeaveRequest leaveRequest) {
		LeaveService service = new LeaveService();
//		Leave leave = service.getLeaveDetailsByLeaveType(leaveRequest.getEmployeeId(), leaveRequest.getLeaveType());
		Leave leave = service.getLeaveDetailsByLeaveType(leaveRequest.getEmployeeId(), leaveRequest.getLeaveTypes());
		int noOfLeaves = leave.getLeaveBalance();
		if (noOfLeaves >= leaveRequest.getNoOfLeaves())
		leave.setLeaveBalance(noOfLeaves - leaveRequest.getNoOfLeaves());
		leaveRequest.setLeave(leave);
		leaveRequest.setEmployee(leave.getEmployee());
		String result = service.addLeaveRequest(leaveRequest);
  	    return result;
	}
	
	@RequestMapping(value="/empLeaveReport", method = RequestMethod.GET)
    public ModelAndView getEmpLeaveReport(HttpServletRequest request){
		
		permissionForThis = "viewEmployeeLeaveReport";
		User loggedInUser = (User) request.getSession().getAttribute("user");
		ModelAndView model = null;
			
		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
			ObjectMapper mapper = new ObjectMapper();
			   List<Department> deptList = new DepartmentService().getDepartments();
			   String depJSON = "";
			   try {
				   depJSON = mapper.writeValueAsString(deptList);
			   }catch (Exception e) {
				   e.printStackTrace();
			   }
			  
			   LeaveRequest leaveReauest = new LeaveRequest();
			   model = new ModelAndView("empLeaveReport", "command", leaveReauest);
			   model.addObject("employee", leaveReauest);
			   request.getSession().setAttribute("departments", depJSON);
			   request.getSession().setAttribute("empLeaveReport", new ArrayList<LeaveRequest>());
			   return model;
		} else {
			model = new ModelAndView("unauthorized", "message", "You do not have access to view employee leaves report. Please click home button to go back.");
			   model.addObject("unauthorizedMessage", true);
			   return model;
		}
		  
    }
	
	@RequestMapping(value = "/empLeaveReportSearch", method = RequestMethod.GET)
	public ModelAndView getEmpLeaveReportSearch(HttpServletRequest request, LeaveRequest leaveReauest) {
		
		permissionForThis = "viewEmployeeLeaveSearch";
		User loggedInUser = (User) request.getSession().getAttribute("user");
		ModelAndView model = null;
			
		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
			LeaveService service = new LeaveService();
			List<LeaveRequestVO> leaveRequests = service.getLeaveRequests(leaveReauest.getDepartmentId(), leaveReauest.getHeadId(), leaveReauest.getFirstName());

			for (LeaveRequestVO leaveReq : leaveRequests) {
				leaveReq.setLeave(new LeaveDAO().getEmpLeave(leaveReq.getEmployee().getEmployeeId(), leaveReq.getLeaveType().getId()));
			}

			model = new ModelAndView("empLeaveReport", "command", leaveReauest);
			model.addObject("employee", leaveReauest);
			request.getSession().setAttribute("empLeaveReport", leaveRequests);
			return model;
		} else {
			model = new ModelAndView("unauthorized", "message", "You do not have access to search employee leaves. Please click home button to go back.");
			   model.addObject("unauthorizedMessage", true);
			   return model;
		}
		
	}
	@RequestMapping(value = "/inputLeave", method = RequestMethod.GET)
	public ModelAndView inputLeave(LeaveVO leave, HttpServletRequest request) {
		
		permissionForThis = "addEmployeeeLeave";
		User loggedInUser = (User) request.getSession().getAttribute("user");
		ModelAndView model = null;
			
		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
			ObjectMapper mapper = new ObjectMapper();
			System.out.println("inputLeave -- leave:"+leave);
			//List<Designation> desigList = new DesignationService().getDesignationList();
			List<Department> deptList = new DepartmentService().getDepartments();
			String desigJSON = "";
			String depJSON = "";
			try {
				depJSON = mapper.writeValueAsString(deptList);
				//desigJSON = mapper.writeValueAsString(desigList);
			} catch (Exception e) {
				e.printStackTrace();
			}
		if(!leave.getEmployeeId().equals("0"))
				leave = new LeaveService().getLeaveByIde(leave.getEmployeeId());
			model = new ModelAndView("leave", "command", leave);
			model.addObject("leave", leave);
			model.addObject("departments", depJSON);
			//model.addObject("designations", desigJSON);
			return model;
		} else {
			 model = new ModelAndView("unauthorized", "message", "You do not have access to add employee leave. Please click home button to go back.");
			   model.addObject("unauthorizedMessage", true);
			   return model;
		}
		
		
		
	}
	   
	@RequestMapping(value="/addLeave",method=RequestMethod.POST)
	public @ResponseBody String addLeave(@RequestBody LeaveVO leave, HttpServletRequest request){
		
		permissionForThis = "addEmployeeeLeave";
		User loggedInUser = (User) request.getSession().getAttribute("user");
			
		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
			System.out.println("addLeave -- Leave:"+leave);
			   String result = new LeaveService().addUpdateLeave(leave);
			   System.out.println("Result:"+result);
			   return result;
		} else {
			request.getSession().setAttribute("message", "You do not have access to add employee leave. Please click home button to go back.");
			request.getSession().setAttribute("unauthorizedMessage", true);
			return "unauthorized";
		}
		
		
	   
	}
	
	@RequestMapping(value="/deleteLeave",method=RequestMethod.GET)
	public ModelAndView deleteLeave(LeaveVO leave, HttpServletRequest request){
		
		permissionForThis = "deleteEmployeeeLeave";
		ModelAndView model = null;
		User loggedInUser = (User) request.getSession().getAttribute("user");
			
		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
			System.out.println("deleteLeave -- Leave:"+leave.getEmployeeId());
			   String result = new LeaveService().deleteLeave(leave.getEmployeeId(), leave.getLeaveId()+"");
			   System.out.println("result:"+result);
			   //return "listLeaves";
			   return listResult(leave);
		} else {
			model = new ModelAndView("unauthorized", "message", "You do not have access to add employee leave. Please click home button to go back.");
			   model.addObject("unauthorizedMessage", true);
			   return model;
		}
	   
	}

	@RequestMapping(value="/empLeaves",method=RequestMethod.GET)
	public String getLeavesByEmp(LeaveVO leave){
	   System.out.println("empLeaves -- Leave:"+leave.getEmployeeId());
	   List<LeaveVO> leaveVoNew = new LeaveService().getEmpAvailableLeaves(leave.getEmployeeId());
	   System.out.println("result:"+leaveVoNew);
	   return "listLeaves";
	}
	
	private ModelAndView listResult(LeaveVO leaveVO) {
		   ObjectMapper mapper = new ObjectMapper();
		   List<Department> deptList = new DepartmentService().getDepartments();
		   String depJSON = "";
		   try {
			   depJSON = mapper.writeValueAsString(deptList);
		   }catch (Exception e) {
			   e.printStackTrace();
		   }
		   System.out.println("leaveVO:"+leaveVO);
		   List<LeaveVO> leaveVOList = null;
		   if(leaveVO.getDepartmentId() !=0 || !Utils.isEmpty(leaveVO.getFirstName())){
			   leaveVOList = new LeaveService().getLeavesData(
					   leaveVO.getDepartmentId(), leaveVO.getHeadId(), leaveVO.getFirstName());
		   } else {
			   leaveVOList = new LeaveService().getLeavesData(
					   leaveVO.getDepartmentId(), leaveVO.getHeadId(), leaveVO.getFirstName());
		   }
		   
		   ModelAndView model = new ModelAndView("listLeaves", "command", leaveVO);
		   model.addObject("leave", leaveVO);
		   model.addObject("leaveVOList", leaveVOList);
		   model.addObject("departments", depJSON);
		   return model;

	   }

	
}
