package com.payroll.rest;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.payroll.login.vo.UserVO;

@Controller
public class LoginController
{
  
  @RequestMapping(value={"/", "/login"}, method={RequestMethod.GET})
  public ModelAndView login()
  {
	 UserVO userVO = new UserVO();
     ModelAndView model = new ModelAndView("userLogin", "command", userVO);
     return model;
  }
  
  @RequestMapping(value={"/home"}, method={RequestMethod.POST})
  public ModelAndView homePage(HttpServletRequest request, UserVO user)
  {
	 Map userMap = new HashMap<String, String>(); 
	 userMap.put("payroll", "payroll123");
	 
	 ModelAndView model = null;
	 if (userMap.get(user.getUserName())!=null && userMap.get(user.getUserName()).equals(user.getPassword())) {
	     model = new ModelAndView("dashboard");
	     request.getSession().setAttribute("user", user);
	 } else {
	     model = new ModelAndView("userLogin", "command", user);
	     model.addObject("errorMsg", "Invalid Username or Password");
	 }
     return model;
  }
}
