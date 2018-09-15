package com.payroll.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.payroll.dashboard.business.Dashboard2Service;
import com.payroll.dashboard.dataobjects.MonthlySummary;
import com.payroll.dashboard.dataobjects.PaybillChart;
import com.payroll.dashboard.dataobjects.PaybillPieChart;
import com.payroll.login.dao.PermissionsDAO;
import com.payroll.login.dataobjects.User;

@Controller
public class Dashboard2Controller {
	
	private String permissionForThis;

	@RequestMapping(value = "/dashboard2")
	public String getDashboard(HttpServletRequest request) {
		
		permissionForThis = "viewCEODashboard";
		
		User loggedInUser = (User) request.getSession().getAttribute("user");
		
		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis)) {
			return "dashboard2";
		} else {
			request.getSession().setAttribute("message", "You do not have access to view dashboard. Please click home button to go back.");
			request.getSession().setAttribute("unauthorizedMessage", true);
			return "unauthorized";
		}
	}
	
	@RequestMapping(value = "/paybillchart", method = RequestMethod.GET)
	@ResponseBody 
	public List<PaybillChart> getPaybillChart() {
		return new Dashboard2Service().getPaybillChart();
	}
	
	@RequestMapping(value = "/paybillpiechart", method = RequestMethod.GET)
	@ResponseBody 
	public List<PaybillPieChart> getPaybillPieChart() {
		return new Dashboard2Service().getPaybillPieChart();
	}
	
	@RequestMapping(value = "/monthlysummary", method = RequestMethod.GET)
	@ResponseBody 
	public List<MonthlySummary> getMonthlySummary() {
		return new Dashboard2Service().getMonthlySummary();
	}

}
