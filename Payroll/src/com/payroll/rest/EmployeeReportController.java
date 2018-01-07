package com.payroll.rest;

import com.payroll.department.business.DepartmentService;
import com.payroll.department.dataobjects.Department;
import com.payroll.employee.SearchCriteria;
import com.payroll.headInfo.dataobjects.HeadInfoDAO;
import com.payroll.headInfo.vo.HeadInfoVO;
import com.payroll.report.business.EmployeeReportService;
import com.payroll.report.vo.EmpAllowanceReportVO;
import com.payroll.report.vo.EmployeeReportVO;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmployeeReportController
{
  @RequestMapping(value={"/employeeSearch"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView getEmployeesSearch(HttpServletRequest request, ModelMap modelMap)
  {
    ObjectMapper mapper = new ObjectMapper();
    List<Department> deptList = new DepartmentService().getDepartments();
    
    String depJSON = "";
    try
    {
      depJSON = mapper.writeValueAsString(deptList);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    request.getSession().removeAttribute("recordsSize");
    request.getSession().removeAttribute("employees");
    request.getSession().setAttribute("departments", depJSON);
    
    SearchCriteria employee = new SearchCriteria();
    request.getSession().setAttribute("reportName", "Employee Information");
    ModelAndView model = new ModelAndView("employeeReport", "command", employee);
    model.addObject("search", employee);
    return model;
  }
  
  @RequestMapping(value={"/empAllowanceSearch"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView getEmployeesAllowancesSearch(HttpServletRequest request, ModelMap modelMap)
  {
    ObjectMapper mapper = new ObjectMapper();
    List<Department> deptList = new DepartmentService().getDepartments();
    
    String depJSON = "";
    try
    {
      depJSON = mapper.writeValueAsString(deptList);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    request.getSession().removeAttribute("empAllowanceReport");
    request.getSession().removeAttribute("recordsSize");
    request.getSession().setAttribute("departments", depJSON);
    
    SearchCriteria employee = new SearchCriteria();
    request.getSession().setAttribute("reportName", "Employee Allowances Report");
    ModelAndView model = new ModelAndView("empAllowanceReport", "command", employee);
    model.addObject("search", employee);
    return model;
  }
  
  @RequestMapping(value={"/employeeReport"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView getEmployeesReport(HttpServletRequest request, SearchCriteria search)
  {
    List<EmployeeReportVO> employeesList = new EmployeeReportService().getEmployees(search.getDepartmentId(), search.getHeadId());
    
    List<EmployeeReportVO> employees = new ArrayList();
    String name = search.getFirstName() != null ? search.getFirstName().trim() : "";
    if ((!name.equals("")) && (employeesList != null) && (employeesList.size() != 0)) {
      for (EmployeeReportVO empVO : employeesList) {
        if (empVO.getFullName().toUpperCase().contains(name.toUpperCase())) {
          employees.add(empVO);
        }
      }
    } else {
      employees = employeesList;
    }
   
   /* for (int i=0; i<4; i++) {
    	employees.addAll(employees);
    }*/
    
    request.getSession().setAttribute("reportName", "Employee Information");
    request.getSession().setAttribute("recordsSize", Integer.valueOf(employees.size()));
    request.getSession().setAttribute("employees", employees);
    ModelAndView model = new ModelAndView("employeeReport", "command", search);
    model.addObject("search", search);
    if (search.getDepartmentId() != 0)
    {
      HeadInfoDAO dao = new HeadInfoDAO();
      List<HeadInfoVO> headList = dao.getHeadInfoList(search.getDepartmentId());
      String headJSON = "";
      try
      {
        ObjectMapper mapper = new ObjectMapper();
        headJSON = mapper.writeValueAsString(headList);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
      model.addObject("headDetails", headJSON);
    }
    return model;
  }
  
  @RequestMapping(value={"/empAllowanceReport"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView getEmpAllowanceReport(HttpServletRequest request, SearchCriteria search)
  {
    List<EmpAllowanceReportVO> employeesList = new EmployeeReportService().getEmpAllowancesReport(search.getDepartmentId(), search.getHeadId());
    
    List<EmpAllowanceReportVO> employees = new ArrayList();
    String name = search.getFirstName() != null ? search.getFirstName().trim() : "";
    if ((!name.equals("")) && (employeesList != null) && (employeesList.size() != 0)) {
      for (EmpAllowanceReportVO empVO : employeesList) {
        if (empVO.getFullName().toUpperCase().contains(name.toUpperCase())) {
          employees.add(empVO);
        }
      }
    } else {
      employees = employeesList;
    }
    System.out.println("Salary Report Size:" + employees.size());
    
    request.getSession().setAttribute("empAllowanceReport", employees);
    request.getSession().setAttribute("reportName", "Employee Allowances Report");
    request.getSession().setAttribute("recordsSize", Integer.valueOf(employees.size()));
    ModelAndView model = new ModelAndView("empAllowanceReport", "command", search);
    model.addObject("search", search);
    if (search.getDepartmentId() != 0)
    {
      HeadInfoDAO dao = new HeadInfoDAO();
      List<HeadInfoVO> headList = dao.getHeadInfoList(search.getDepartmentId());
      String headJSON = "";
      try
      {
        ObjectMapper mapper = new ObjectMapper();
        headJSON = mapper.writeValueAsString(headList);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
      model.addObject("headDetails", headJSON);
    }
    return model;
  }
  
  @RequestMapping(value={"/employeeRptDownload"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"text/csv"})
  @ResponseBody
  public void downloadEmployeeReport(HttpServletRequest request, HttpServletResponse response)
    throws IOException
  {
    DateFormat format = new SimpleDateFormat("yyyyMMdd_HHmmss");
    String csvFileName = "EmployeeReport_" + format.format(new Date()) + ".csv";
    
    List<EmployeeReportVO> employeesList = (List)request.getSession().getAttribute("employees");
    StringBuilder fileContent = new StringBuilder("Name, Department, Head, Designation, Gender, Date of Birth, Joining Date, Phone, Email, Address, PAN, Aadhar,").append("\n");
    for (EmployeeReportVO employeeVO : employeesList)
    {
      fileContent.append(employeeVO.getFullName()).append(", ");
      fileContent.append(employeeVO.getDepartment()).append(", ");
      fileContent.append(employeeVO.getHeadName()).append(", ");
      fileContent.append(employeeVO.getDesignation()).append(", ");
      fileContent.append(employeeVO.getAddress()).append(", ");
      fileContent.append(employeeVO.getDob()).append(", ");
      fileContent.append(employeeVO.getGender()).append(", ");
      fileContent.append(employeeVO.getJoiningDate()).append(", ");
      fileContent.append(employeeVO.getPhone()).append(", ");
      fileContent.append(employeeVO.getEmail()).append(", ");
      fileContent.append(employeeVO.getAdharNo()).append(", ");
      fileContent.append(employeeVO.getPan()).append(", ");
      fileContent.append("\n");
    }
    response.setContentType("text/csv");
    response.setHeader("Content-Disposition", "attachment; filename=" + csvFileName);
    
    ServletOutputStream os = response.getOutputStream();
    os.write(fileContent.toString().getBytes());
    os.flush();
    os.close();
  }
  
  @RequestMapping(value={"/empAllowanceRptDownload"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"text/csv"})
  @ResponseBody
  public void downloadEmpAllowancesReport(HttpServletRequest request, HttpServletResponse response)
    throws IOException
  {
    DateFormat format = new SimpleDateFormat("yyyyMMdd_HHmmss");
    String csvFileName = "EmpAllowanceReport_" + format.format(new Date()) + ".csv";
    
    List<EmpAllowanceReportVO> employeesList = (List)request.getSession().getAttribute("empAllowanceReport");
    StringBuilder fileContent = new StringBuilder("Name, Department, Head, CCA, Washing Allowance, Conveyance Allowance, " +
    "Non Practice Allowance, Uniform Allowance, Family Plan Allowance, Cycle Allowance, HRA").append("\n");
    for (EmpAllowanceReportVO employeeVO : employeesList)
    {
      fileContent.append(employeeVO.getFullName()).append(", ");
      fileContent.append(employeeVO.getDepartment()).append(", ");
      fileContent.append(employeeVO.getHeadName()).append(", ");
      //fileContent.append(employeeVO.getDesignation()).append(", ");
      fileContent.append(employeeVO.getCca()).append(", ");
      fileContent.append(employeeVO.getWashingAllowance()).append(", ");
      fileContent.append(employeeVO.getConvAllowance()).append(", ");
      fileContent.append(employeeVO.getNonPractAllowance()).append(", ");
      fileContent.append(employeeVO.getUniformAllowance()).append(", ");
      fileContent.append(employeeVO.getFamilyPlanAllowance()).append(", ");
      fileContent.append(employeeVO.getCycleAllowance()).append(", ");
      fileContent.append(employeeVO.isHraFlag()? "Yes" : "No").append(", ");
      fileContent.append("\n");
    }
    response.setContentType("text/csv");
    response.setHeader("Content-Disposition", "attachment; filename=" + csvFileName);
    
    ServletOutputStream os = response.getOutputStream();
    os.write(fileContent.toString().getBytes());
    os.flush();
    os.close();
  }
  
  @RequestMapping(value={"/printTemplate"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView getEmployeesSearch()
  {
    ModelAndView model = new ModelAndView("printTemplate");
    return model;
  }
}
