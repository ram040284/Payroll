package com.payroll.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.payroll.department.business.DepartmentService;
import com.payroll.department.dataobjects.Department;
import com.payroll.employee.dataobjects.EmployeeDAO;
import com.payroll.employee.vo.EmployeeVO;
import com.payroll.login.dataobjects.User;
import com.payroll.login.dataobjects.UserDAO;
import com.payroll.login.dataobjects.UserRoles;
import com.payroll.utils.PasswordUtils;

@Controller
public class UserController
{
	  @RequestMapping(value={"/usersList"}, method={RequestMethod.GET})
	  public ModelAndView getUsersList(HttpServletRequest request)
	  {
		 ObjectMapper mapper = new ObjectMapper();
		    List<Department> deptList = new DepartmentService().getDepartments();
		    List<User> usersList = new UserDAO().getUsersList(0);
		    String depJSON = "";
		    try
		    {
		      depJSON = mapper.writeValueAsString(deptList);
		    }
		    catch (Exception e)
		    {
		      e.printStackTrace();
		    }
		    request.getSession().setAttribute("departments", depJSON);
		    request.getSession().setAttribute("users", usersList);
		    
		    User user = new User();
		    ModelAndView model = new ModelAndView("userReport", "command", user);
		    model.addObject("users", usersList);
		    return model;
	  }
	  
	  @RequestMapping(value={"/usersListFilter"}, method={RequestMethod.POST})
	  public ModelAndView getUsersListFilter(HttpServletRequest request, User user)
	  {
		 ObjectMapper mapper = new ObjectMapper();
		    List<Department> deptList = new DepartmentService().getDepartments();
		    List<User> usersList = new UserDAO().getUsersList(user.getDeptId()==null? 0 : user.getDeptId());
		    String depJSON = "";
		    try
		    {
		      depJSON = mapper.writeValueAsString(deptList);
		    }
		    catch (Exception e)
		    {
		      e.printStackTrace();
		    }
		    request.getSession().setAttribute("departments", depJSON);
		    request.getSession().setAttribute("users", usersList);
		    
		    ModelAndView model = new ModelAndView("userReport", "command", user);
		    model.addObject("users", usersList);
		    return model;
	  }
	  
	  
  @RequestMapping(value={"/addUser"}, method={RequestMethod.POST})
  public ModelAndView addUser(HttpServletRequest request, User user)
  {
	 ObjectMapper mapper = new ObjectMapper();
	    List<Department> deptList = new DepartmentService().getDepartments();
	    List<UserRoles> userRoles = new UserDAO().getUserRoles();

	    String depJSON = "";
	    String rolesJSON = "";
	    try
	    {
	      depJSON = mapper.writeValueAsString(deptList);
	      rolesJSON = mapper.writeValueAsString(userRoles);
	    }
	    catch (Exception e)
	    {
	      e.printStackTrace();
	    }
	    request.getSession().setAttribute("departments", depJSON);
	    request.getSession().setAttribute("roles", rolesJSON);
	    
	    ModelAndView model = new ModelAndView("userAdd", "command", user);
	    return model;
  }
  
  @RequestMapping(value={"/addUserConfirm"}, method={RequestMethod.POST})
  public ModelAndView addUserConfirm(HttpServletRequest request, User userVo)
  {
	  	System.out.println("addUserConfirm ...");
	  	userVo.setPassword(PasswordUtils.getEncryptedPassword(userVo.getPassword()));
	  	boolean result = new UserDAO().addUser(userVo);
	  	ObjectMapper mapper = new ObjectMapper();
	    List<Department> deptList = new DepartmentService().getDepartments();
	    List<UserRoles> userRoles = new UserDAO().getUserRoles();

	    String depJSON = "";
	    String rolesJSON = "";
	    try
	    {
	      depJSON = mapper.writeValueAsString(deptList);
	      rolesJSON = mapper.writeValueAsString(userRoles);
	    }
	    catch (Exception e)
	    {
	      e.printStackTrace();
	    }
	    request.getSession().setAttribute("departments", depJSON);
	    request.getSession().setAttribute("roles", rolesJSON);
	    
	    User userVO = new User();
	    ModelAndView model = new ModelAndView("userAdd", "command", userVO);
	    if (result) {
	    	model.addObject("message", "User Created successfully");
	    } else {
	    	model.addObject("message", "User Creation failed");
	    }
	    return model;
  }
  
  @RequestMapping(value="/loadDeptEmployees",method=RequestMethod.POST, produces = "application/json")
	public @ResponseBody List<EmployeeVO> loadEmployees(@RequestBody User userVo){
	   List<EmployeeVO> employees = new EmployeeDAO().getEmployeesByDeptId(userVo.getDeptId());
	   return employees;
	}
  
  @RequestMapping(value="/userNameCheck",method=RequestMethod.POST, produces = "application/json")
	public @ResponseBody User userNameCheck(@RequestBody User userVo){
	   userVo.setUserNameAvailabe(new UserDAO().isUserNameAvailable(userVo.getUserId().trim()));
	   return userVo;
	}
}
