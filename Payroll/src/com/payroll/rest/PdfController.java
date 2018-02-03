package com.payroll.rest;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
@Controller
@RequestMapping(value = "/pdf", method = RequestMethod.GET)
public class PdfController extends AbstractController {

	   @Override
	   protected ModelAndView handleRequestInternal(HttpServletRequest request,
	      HttpServletResponse response) throws Exception {
	      //user data
	      Map<String,String> userData = new HashMap<String,String>();
	      userData.put("1", "Mahesh");
	      userData.put("2", "Suresh");
	      userData.put("3", "Ramesh");
	      userData.put("4", "Naresh");
	      return new ModelAndView("UserSummary","userData",userData);
	   }
	}


