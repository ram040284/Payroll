package com.payroll.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import com.payroll.employee.leave.business.LeaveService;
import com.payroll.employee.leave.dataobjects.Leave;
import com.payroll.employee.leave.dataobjects.LeaveRequest;
import com.payroll.employee.leave.vo.LeaveVO;

@Controller
public class EmpLeaveController {
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
	
	@RequestMapping(value="/empLeaveSearch", method = RequestMethod.POST)
    public ModelAndView getEmpLeaveSearch(HttpServletRequest request, LeaveRequest leaveReauest){
		  ObjectMapper mapper = new ObjectMapper();
		  List<LeaveVO> empLeaveList = new LeaveService().getLeaves(leaveReauest.getDepartmentId(), leaveReauest.getHeadId(), leaveReauest.getFirstName());
		  request.getSession().setAttribute("empLeaveList", empLeaveList);
		   ModelAndView model = new ModelAndView("empLeaveList", "command", leaveReauest);
		   model.addObject("employee", leaveReauest);
		   return model;
    }
	
	@RequestMapping(value="/empLeaveListApply", method = RequestMethod.POST)
    public ModelAndView getEmpLeaveListApply(HttpServletRequest request, LeaveRequest leaveReauest){
		  ObjectMapper mapper = new ObjectMapper();
		  List<LeaveVO> empLeaveList = new LeaveService().getLeaves(leaveReauest.getListDeptId(), leaveReauest.getListHeadId(), leaveReauest.getListName());
		  request.getSession().setAttribute("empLeaveList", empLeaveList);
		   ModelAndView model = new ModelAndView("empLeaveList", "command", leaveReauest);
		   model.addObject("employee", leaveReauest);
		   return model;
    }
	
	@RequestMapping(value = "/viewLeave_1", method = RequestMethod.POST)
	public ModelAndView viewLeave_1(LeaveVO leaveVO) {
		//return "listLeaves";
		return listResult(leaveVO);
	}
	
	
	//@RequestMapping(value = "/viewLeave", method = RequestMethod.GET)
	//public String viewLeave(ModelMap model) {
	@RequestMapping(value = "/viewLeave", method = RequestMethod.POST)
	public ModelAndView viewLeave(LeaveRequest leaveReauest) {
		//return "listLeaves";
		LeaveService service = new LeaveService();
		LeaveVO  leaveVoDb = service.getLeaveDetailsById(leaveReauest.getEmployeeId());
		//Map<String, String> leaveTypes = service.getLeaveTypes(leaveVoDb.getEmployee().getGender());
		Map<String, String> leaveBalance = service.getLeaveBalance(leaveVoDb);
		ModelAndView model = new ModelAndView("applyLeave", "command", leaveReauest);
		model.addObject("employee", leaveReauest);
		model.addObject("leave", leaveVoDb);
		model.addObject("leaveBalance", leaveBalance);
		//model.addObject("leaveTypes", leaveTypes);
  	    return model;
	}
	
	@RequestMapping(value = "/applyLeave", method = RequestMethod.POST)
	public ModelAndView applyLeave(HttpServletRequest request, LeaveRequest leaveRequest) {
		//return "listLeaves";
		LeaveService service = new LeaveService();
		Leave leave = service.getLeaveDetailsByLeaveType(leaveRequest.getEmployeeId(), leaveRequest.getLeaveType());
		int noOfLeaves = leave.getLeaveBalance();
		if (noOfLeaves >= leaveRequest.getNoOfLeaves())
		leave.setLeaveBalance(noOfLeaves - leaveRequest.getNoOfLeaves());
		leaveRequest.setLeave(leave);
		leaveRequest.setEmployee(leave.getEmployee());
		String result = service.addLeaveRequest(leaveRequest);
		
		LeaveRequest leaveReq = new LeaveRequest();
		leaveReq.setDepartmentId(leaveRequest.getDepartmentId());
		leaveReq.setHeadId(leaveRequest.getHeadId());
		leaveReq.setFirstName(leaveRequest.getFirstName());
		leaveReq.setListDeptId(leaveRequest.getListDeptId());
		leaveReq.setListHeadId(leaveRequest.getListHeadId());
		leaveReq.setListName(leaveRequest.getListName());
		
		List<LeaveVO> empLeaveList = new LeaveService().getLeaves(leaveRequest.getListDeptId(), leaveRequest.getListHeadId(), leaveRequest.getListName());
		request.getSession().setAttribute("empLeaveList", empLeaveList);
		  
		ModelAndView model = new ModelAndView("empLeaveList", "command", leaveReq);
		if (result!= null && result.equalsIgnoreCase("YES")) 
			model.addObject("message", "Leave request successfully submitted");
		else
			model.addObject("message", "Leave request submition failed");
		model.addObject("employee", leaveReq);
  	    return model;
	}
	
	@RequestMapping(value="/empLeaveReport", method = RequestMethod.GET)
    public ModelAndView getEmpLeaveReport(HttpServletRequest request){
		  ObjectMapper mapper = new ObjectMapper();
		   List<Department> deptList = new DepartmentService().getDepartments();
		   String depJSON = "";
		   try {
			   depJSON = mapper.writeValueAsString(deptList);
		   }catch (Exception e) {
			   e.printStackTrace();
		   }
		  
		   LeaveRequest leaveReauest = new LeaveRequest();
		   ModelAndView model = new ModelAndView("empLeaveReport", "command", leaveReauest);
		   model.addObject("employee", leaveReauest);
		   request.getSession().setAttribute("departments", depJSON);
		   request.getSession().setAttribute("empLeaveReport", new ArrayList<LeaveRequest>());
		   return model;
    }
	
	@RequestMapping(value="/empLeaveReportSearch", method = RequestMethod.POST)
    public ModelAndView getEmpLeaveReportSearch(HttpServletRequest request, LeaveRequest leaveReauest){
		LeaveService service = new LeaveService();
		List<LeaveRequest> leaveRequests = service.getLeaveRequests(leaveReauest.getDepartmentId(), leaveReauest.getHeadId(), leaveReauest.getFirstName());
		   ModelAndView model = new ModelAndView("empLeaveReport", "command", leaveReauest);
		   model.addObject("employee", leaveReauest);
		   request.getSession().setAttribute("empLeaveReport", leaveRequests);
		   return model;
    }
	@RequestMapping(value = "/inputLeave", method = RequestMethod.POST)
	public ModelAndView inputLeave(LeaveVO leave) {
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
		if(leave.getEmployeeId()!=0)
			leave = new LeaveService().getLeaveByIde(leave.getEmployeeId());
		ModelAndView model = new ModelAndView("leave", "command", leave);
		model.addObject("leave", leave);
		model.addObject("departments", depJSON);
		//model.addObject("designations", desigJSON);
		return model;
	}
	   
	@RequestMapping(value="/addLeave",method=RequestMethod.POST)
	public @ResponseBody String addLeave(@RequestBody LeaveVO leave){
	   System.out.println("addLeave -- Leave:"+leave);
	   String result = new LeaveService().addUpdateLeave(leave);
	   System.out.println("Result:"+result);
	   return result;
	}
	
	@RequestMapping(value="/deleteLeave",method=RequestMethod.POST)
	public ModelAndView deleteLeave(LeaveVO leave){
	   System.out.println("deleteLeave -- Leave:"+leave.getEmployeeId());
	   String result = new LeaveService().deleteLeave(leave.getEmployeeId());
	   System.out.println("result:"+result);
	   //return "listLeaves";
	   return listResult(leave);
	}

	@RequestMapping(value="/empLeaves",method=RequestMethod.POST)
	public String getLeavesByEmp(LeaveVO leave){
	   System.out.println("empLeaves -- Leave:"+leave.getEmployeeId());
	   List<LeaveVO> leaveVoNew = new LeaveService().getEmpAvailableLeaves(leave.getLeaveId());
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
			   leaveVOList = new LeaveService().getLeaves(
					   leaveVO.getDepartmentId(), leaveVO.getHeadId(), leaveVO.getFirstName());
		   }
		   ModelAndView model = new ModelAndView("listLeaves", "command", leaveVO);
		   model.addObject("leave", leaveVO);
		   model.addObject("leaveVOList", leaveVOList);
		   model.addObject("departments", depJSON);
		   return model;

	   }

	
}
