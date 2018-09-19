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

import com.payroll.department.business.DepartmentService;
import com.payroll.department.dataobjects.Department;
import com.payroll.employee.dataobjects.EmployeeDAO;
import com.payroll.employee.vo.EmployeeVO;
import com.payroll.login.dataobjects.Roles;
import com.payroll.login.dataobjects.User;
import com.payroll.login.dataobjects.UserDAO;
import com.payroll.utils.PasswordUtils;

@Controller
public class UserController
{
	  @RequestMapping(value={"/usersList"}, method={RequestMethod.GET})
	  public ModelAndView getUsersList(HttpServletRequest request)
	  {
		 ObjectMapper mapper = new ObjectMapper();
		    List<Department> deptList = new DepartmentService().getDepartments();
		    List<User> usersList = new UserDAO().getUsersList(0, 0);
		    List<Roles> roles = new UserDAO().getRoles();

		    String depJSON = "";
		    String rolesJSON = "";
		    try
		    {
		      depJSON = mapper.writeValueAsString(deptList);
		      rolesJSON = mapper.writeValueAsString(roles);
		    }
		    catch (Exception e)
		    {
		      e.printStackTrace();
		    }
		    request.getSession().setAttribute("departments", depJSON);
		    request.getSession().setAttribute("roles", rolesJSON);
		    request.getSession().setAttribute("userRoles", roles);
		    request.getSession().setAttribute("deptList", deptList);
		    
		    User user = new User();
		    ModelAndView model = new ModelAndView("userReport", "command", user);
		    model.addObject("users", usersList);
		    return model;
		    
	  }
	  
	  @RequestMapping(value={"/usersListFilter"}, method={RequestMethod.POST})
	  public ModelAndView getUsersListFilter(HttpServletRequest request, User user)
	  {
		    List<User> usersList = new UserDAO().getUsersList(user.getListDeptId(), user.getListRoleId());
		    
		    ModelAndView model = new ModelAndView("userReport", "command", user);
		    model.addObject("users", usersList);
		    return model;
	  }
	  
	  
	  @RequestMapping(value={"/addUser"}, method={RequestMethod.POST})
	  public ModelAndView addUser(HttpServletRequest request, User user)
	  {
		  	user.setDeptId(user.getListDeptId());
		  	user.setRoleId(user.getListRoleId());
		    ModelAndView model = new ModelAndView("userAdd", "command", user);
		    model.addObject("user", user);
		    return model;
	  }
  
	  @RequestMapping(value={"/editUser"}, method={RequestMethod.POST})
	  public ModelAndView editUser(HttpServletRequest request, User userVo)
	  {
		  	User user = new User();
		  	if (userVo.getUserId()!=null && userVo.getUserId()!=0) {
		  		user = new UserDAO().getUserRoleByUserId(userVo);
		  		user.setPassword("");
		  		user.setConfirmPassword("");
		  	}
		    
		  	user.setListDeptId(userVo.getListDeptId());
		  	user.setListRoleId(userVo.getListRoleId());
		    ModelAndView model = new ModelAndView("userEdit", "command", user);
		    model.addObject("user", user);
		    request.setAttribute("roleIds", user.getRolesList().toArray());
		    request.setAttribute("deptIds", user.getDeptIdsArray());
		    return model;
	  }
  
	  @RequestMapping(value={"/addUserConfirm"}, method={RequestMethod.POST})
	  public ModelAndView addUserConfirm(HttpServletRequest request, User userVo)
	  {
		  	UserDAO dao = new UserDAO();
		  	User userExist = dao.getUserByEmpId(userVo);
		  	boolean result = false;
		  	boolean userExt = false;
		  	if (userExist == null) {
			  	userVo.setPassword(PasswordUtils.getEncryptedPassword(userVo.getPassword()));
			  	userVo.setUserName(userVo.getUserId().toString());
			  	result = new UserDAO().addUser(userVo);
		  	} else {
		  		userExt = true;
		  	}
			User user = new User();
		    user.setListDeptId(userVo.getListDeptId());
		  	user.setListRoleId(userVo.getListRoleId());
		    List<User> usersList = new UserDAO().getUsersList(user.getListDeptId(), user.getListRoleId());
		  	
		    ModelAndView model = new ModelAndView("userReport", "command", user);
		    model.addObject("users", usersList);
		    if (userExt) {
		    	model.addObject("message", "User already created for this employee");
		    } else if (result) {
		    	model.addObject("message", "User created successfully");
		    } else {
		    	model.addObject("message", "User creation failed");
		    }
		    return model;
	  }
  
  @RequestMapping(value={"/editUserConfirm"}, method={RequestMethod.POST})
  public ModelAndView editUserConfirm(HttpServletRequest request, User userVo)
  {
	  	//userVo.setPassword(PasswordUtils.getEncryptedPassword(userVo.getPassword()));
	  	boolean result = new UserDAO().updateUser(userVo);

	  	User user = new User();
	    user.setListDeptId(userVo.getListDeptId());
	  	user.setListRoleId(userVo.getListRoleId());
	    List<User> usersList = new UserDAO().getUsersList(user.getListDeptId(), user.getListRoleId());
	    
	    ModelAndView model = new ModelAndView("userReport", "command", user);
	    model.addObject("users", usersList);
	    if (result) {
	    	model.addObject("message", "User updated successfully");
	    } else {
	    	model.addObject("message", "User updation failed");
	    }
	    return model;
  }
  
  @RequestMapping(value={"/deleteUser"}, method={RequestMethod.POST})
  public ModelAndView deleteUser(HttpServletRequest request, User user)
  {
	  	boolean result = new UserDAO().deleteUser(user.getUserId());
	  	List<User> usersList = new UserDAO().getUsersList(user.getListDeptId(), user.getListRoleId() );
	    
	    ModelAndView model = new ModelAndView("userReport", "command", user);
	    model.addObject("users", usersList);
	    if (result) {
	    	model.addObject("message", "User deleted successfully");
	    } else {
	    	model.addObject("message", "User deletion failed");
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
	   userVo.setUserNameAvailabe(new UserDAO().isUserNameAvailable(userVo.getUserName().trim()));
	   return userVo;
	}
  
}
