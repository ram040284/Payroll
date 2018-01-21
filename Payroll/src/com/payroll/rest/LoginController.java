package com.payroll.rest;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.payroll.employee.vo.EmployeeVO;
import com.payroll.login.business.UserService;
import com.payroll.login.dataobjects.User;
import com.payroll.login.vo.UserVO;

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
	 Map userMap = new HashMap<String, String>(); 
	 userMap.put("payroll", "payroll123");
	 
	 ModelAndView model = null;
	 User userDb = UserService.validateUser(user);
	 if (userDb != null) {
		 model = new ModelAndView("dashboard");
		 model.addObject("welcomeMsg", true);
	     request.getSession().setAttribute("user", userDb);
	 } else if (userMap.get(user.getUserId())!=null && userMap.get(user.getUserId().toLowerCase()).equals(user.getPassword())) {
	     model = new ModelAndView("dashboard");
	     model.addObject("welcomeMsg", true);
	     user.setEmployee(new EmployeeVO(0, "Payroll", "User", ""));
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
