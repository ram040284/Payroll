package com.payroll.rest;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.payroll.employee.dataobjects.Employee;
import com.payroll.employee.dataobjects.EmployeeDAO;
import com.payroll.employee.vo.EmployeeVO;
import com.payroll.login.dao.PermissionsDAO;
import com.payroll.login.dataobjects.User;
import com.payroll.login.dataobjects.UserDAO;
import com.payroll.login.vo.UserVO;
import com.payroll.utils.PasswordUtils;

@Controller
public class LoginController
{
  
  @RequestMapping(value={"/", "/login", "/home"}, method={RequestMethod.GET})
  public ModelAndView login(HttpServletRequest request)
  {
	 request.getSession().removeAttribute("user");
	 User user = new User();
     ModelAndView model = new ModelAndView("userLogin", "command", user);
     return model;
  }
  
  @RequestMapping(value={"/home"}, method={RequestMethod.POST})
  public ModelAndView homePage(HttpServletRequest request, User user)
  {
	 Map<String, String> userMap = new HashMap<String, String>(); 
	 //userMap.put("payroll", "payroll123");
	 
	 User retrievedUser = new UserDAO().getUserByEmpId(user);
	 
	 userMap.put(retrievedUser.getUserName(), retrievedUser.getPassword());
	 
	 ModelAndView model = null;
	/* User userDb = UserService.validateUser(user);
	 if (userDb != null) {
		 model = new ModelAndView("dashboard");
		 model.addObject("welcomeMsg", true);
	     request.getSession().setAttribute("user", userDb);
	 } else*/ if (userMap.get(user.getUserName())!=null && userMap.get(user.getUserName().toLowerCase()).equals(PasswordUtils.getEncryptedPassword(user.getPassword()))) {
		 
		 Employee employee  = new EmployeeDAO().getById(retrievedUser.getEmpId());
		 
		 String permissionForCEODashboard = "viewCEODashboard";
		 
		 if (new PermissionsDAO().getPermissions(retrievedUser.getEmpId()).contains(permissionForCEODashboard)) {
			 model = new ModelAndView("dashboard2");
			 request.getSession().setAttribute("isCEO", true);
		 } else {
			 model = new ModelAndView("dashboard");
			 request.getSession().setAttribute("isCEO", false);
		 }
	     
	     model.addObject("welcomeMsg", true);
	     byte handicapFlag = 0;
	     user.setEmployee(new EmployeeVO(employee.getEmployeeId(), employee.getFirstName(), employee.getLastName(), "", handicapFlag));
	   
	     String userRole = new UserDAO().getUserRolesByEmpId(employee.getEmployeeId());
	     user.setRoleNames(userRole);
	     
	     //user.setEmployee(new EmployeeVO(0, "Payroll", "User", "", handicapFlag));
	     request.getSession().setAttribute("user", user);
	 } else {
	     model = new ModelAndView("userLogin", "command", user);
	     model.addObject("errorMsg", "Invalid Username or Password");
	 }
     return model;
  }
  
  @RequestMapping(value={"/logout"}, method={RequestMethod.GET})
  public ModelAndView logout(HttpServletRequest request)
  {
	 request.getSession().removeAttribute("user");
	 request.getSession().invalidate();
	 UserVO userVO = new UserVO();
     ModelAndView model = new ModelAndView("userLogin", "command", userVO);
     return model;
  }
  
}
