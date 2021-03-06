package com.payroll.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.payroll.department.business.DepartmentService;
import com.payroll.department.dataobjects.Department;
import com.payroll.headInfo.business.HeadInfoService;
import com.payroll.headInfo.dataobjects.HeadInfo;
import com.payroll.headInfo.vo.HeadInfoVO;
import com.payroll.login.dao.PermissionsDAO;
import com.payroll.login.dataobjects.User;

@Controller
public class HeadInfoController {
	
	String permissionForThis = null;
	
	/*@RequestMapping(value="/listPayHead", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<HeadInfo> getPayHeads(){
		char headInfoType = 'P'; 
	   List<HeadInfo> payHeads = new HeadInfoService().getHeadInfoList(headInfoType);
	   return payHeads;
    }
	
	@RequestMapping(value="/listDeductHead", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<HeadInfo> getDeductHeads(){
		char headInfoType = 'D'; 
	   List<HeadInfo> deductHeads = new HeadInfoService().getHeadInfoList(headInfoType);
	   return deductHeads;
    }*/
	
	@RequestMapping(value="/listHeads", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<HeadInfoVO> listHeads(){
	   List<HeadInfoVO> payHeads = new HeadInfoService().getHeadInfoList();
	   return payHeads;
    }
	@RequestMapping(value = "/viewHeads", method = RequestMethod.GET)
	public String viewPayHead(ModelMap model) {
		return "listHeads";
	}
	
	/*@RequestMapping(value = "/viewDeductHead", method = RequestMethod.GET)
	public String viewDeductHead(ModelMap model) {
		return "listDeductHead";
	}*/
	
	@RequestMapping(value="/loadHeads",method=RequestMethod.POST, produces = "application/json")
	public @ResponseBody List<HeadInfoVO> loadHeads(@RequestBody HeadInfo headInfo){
	   System.out.println("Department Id:"+headInfo.getDepartmentId());
	   List<HeadInfoVO> heads = new HeadInfoService().getHeadInfoList(headInfo.getDepartmentId());
	   return heads;
	}
	
	
	@RequestMapping(value = "/inputHead", method = RequestMethod.POST)
	public ModelAndView inputHead(HeadInfo headInfo, HttpServletRequest request) {
		
		permissionForThis = "addHead";
	    ModelAndView model = null;
		
		User loggedInUser = (User) request.getSession().getAttribute("user");
		
		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
		
			System.out.println("headInfo Id:"+headInfo.getHeadId());
			ObjectMapper mapper = new ObjectMapper();
			if(headInfo.getHeadId() !=0){
				headInfo = new HeadInfoService().getHeadInfoById(headInfo.getHeadId());
				if(headInfo!=null && headInfo.getDepartment() !=null)
					headInfo.setDepartmentId(headInfo.getDepartment().getDepartmentId());
			}
			List<Department> deptList = new DepartmentService().getDepartments();
			String depJSON = "";
			try {
				depJSON = mapper.writeValueAsString(deptList);
			} catch (Exception e) {
				e.printStackTrace();
			}
			model = new ModelAndView("headDetails", "command", headInfo);
			model.addObject("headDetails", headInfo);
			model.addObject("departments", depJSON);
			return model;
		} else {
			model = new ModelAndView("unauthorized", "message", "You do not have access to add head details. Please click home button to go back.");
		    model.addObject("unauthorizedMessage", true);
		    return model;
		}
		
	}
	
	/*@RequestMapping(value = "/inputDeductHead", method = RequestMethod.POST)
	public ModelAndView inputDeductHead(HeadInfo headInfo) {
		System.out.println("headInfo:"+headInfo);
		ModelAndView model = new ModelAndView("deductHead", "command", headInfo);
		model.addObject("deductHead", headInfo);
		return model;
	}*/
	
	@RequestMapping(value="/addHead",method=RequestMethod.POST)
	public @ResponseBody
	String addHead(@RequestBody HeadInfo headInfo){
	   System.out.println("headInfo:"+headInfo);
	   String result = new HeadInfoService().addUpdateHeadInfo(headInfo);
	   System.out.println("result:"+result);
	   return result;
	}
	
	/*@RequestMapping(value="/addDeductHead",method=RequestMethod.POST)
	public @ResponseBody
	String addDeductHead(@RequestBody HeadInfo headInfo){
	   System.out.println("headInfo:"+headInfo);
	   boolean addedDesg = new HeadInfoService().addUpdateHeadInfo(headInfo);
	   if(addedDesg)
		   return "Yes";
	   else
		   return "No";
	}*/
	
	
	@RequestMapping(value="/deleteHeadInfo",method=RequestMethod.POST)
	public String deleteHeadInfo(HeadInfo headInfo, HttpServletRequest request){
		
		permissionForThis = "addHead";
		User loggedInUser = (User) request.getSession().getAttribute("user");
		
		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
			System.out.println("headInfo:"+headInfo.getHeadId());
			String result = new HeadInfoService().deleteHeadInfo(headInfo.getHeadId());
			System.out.println("result:"+result);
			return "listHeads";
		} else {
			//FIXME: Prasad - This is not working and needs to be fixed
			 request.getSession().setAttribute("message", "You do not have access to delete head details. Please click home button to go back.");
			 request.getSession().setAttribute("unauthorizedMessage", true);
			 return "unauthorized";
		}
	}


}
