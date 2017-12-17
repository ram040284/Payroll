package com.payroll.rest;

import com.payroll.department.business.DepartmentService;
import com.payroll.department.dataobjects.Department;
import com.payroll.employee.SearchCriteria;
import com.payroll.employee.salary.vo.SalaryVO;
import com.payroll.headInfo.dataobjects.HeadInfoDAO;
import com.payroll.headInfo.vo.HeadInfoVO;
import com.payroll.report.business.EmployeeReportService;
import com.payroll.report.vo.EmpSalaryReportVO;
import com.payroll.report.vo.EmployeeReportVO;
import java.io.IOException;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
    request.getSession().setAttribute("employees", new ArrayList());
    request.getSession().setAttribute("departments", depJSON);
    
    SearchCriteria employee = new SearchCriteria();
    request.getSession().setAttribute("reportName", "Employee Information");
    request.getSession().setAttribute("recordsSize", Integer.valueOf(-1));
    ModelAndView model = new ModelAndView("employeeReport", "command", employee);
    model.addObject("search", employee);
    return model;
  }
  
  @RequestMapping(value={"/empSalarySearch"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView getEmployeesSalarySearch(HttpServletRequest request, ModelMap modelMap)
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
    request.getSession().setAttribute("empSalaryReport", new ArrayList());
    request.getSession().setAttribute("departments", depJSON);
    
    SearchCriteria employee = new SearchCriteria();
    request.getSession().setAttribute("reportName", "Employee Salary Information");
    request.getSession().setAttribute("recordsSize", Integer.valueOf(-1));
    ModelAndView model = new ModelAndView("employeeSalaryReport", "command", employee);
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
  
  @RequestMapping(value={"/empSalaryReport"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView getEmployeesSalaryReport(HttpServletRequest request, SearchCriteria search)
  {
    List<EmpSalaryReportVO> employeesList = new EmployeeReportService().getEmpSalaryReport(search.getDepartmentId(), search.getHeadId());
    
    List<EmpSalaryReportVO> employees = new ArrayList();
    String name = search.getFirstName() != null ? search.getFirstName().trim() : "";
    if ((!name.equals("")) && (employeesList != null) && (employeesList.size() != 0)) {
      for (EmpSalaryReportVO empVO : employeesList) {
        if (empVO.getFullName().toUpperCase().contains(name.toUpperCase())) {
          employees.add(empVO);
        }
      }
    } else {
      employees = employeesList;
    }
    System.out.println("Salary Report Size:" + employees.size());
    
    request.getSession().setAttribute("empSalaryReport", employees);
    request.getSession().setAttribute("reportName", "Employee Salary Information");
    request.getSession().setAttribute("recordsSize", Integer.valueOf(employees.size()));
    ModelAndView model = new ModelAndView("employeeSalaryReport", "command", search);
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
    StringBuilder fileContent = new StringBuilder("Name, Department, Head, Designation, Gender, Date of Birth, Joining Date, Phone, Email, Address, PAN, Aadhar, Year, Basic Pay, Grade Pay, Scale Pay, Scale Increment,").append("\n");
    for (EmployeeReportVO employeeVO : employeesList)
    {
      fileContent.append(employeeVO.getFullName()).append(", ");
      fileContent.append(employeeVO.getDepartment()).append(", ");
      fileContent.append(employeeVO.getHeadName()).append(", ");
      fileContent.append(employeeVO.getDesignation()).append(", ");
      fileContent.append(employeeVO.getGender()).append(", ");
      fileContent.append(employeeVO.getDob()).append(", ");
      fileContent.append(employeeVO.getJoiningDate()).append(", ");
      fileContent.append(employeeVO.getPhone()).append(", ");
      fileContent.append(employeeVO.getEmail()).append(", ");
      fileContent.append(employeeVO.getAddress()).append(", ");
      fileContent.append(employeeVO.getPan()).append(", ");
      fileContent.append(employeeVO.getAdharNo()).append(", ");
      SalaryVO salVo = employeeVO.getSalaryVo();
      if (salVo != null)
      {
        fileContent.append(salVo.getYear()).append(", ");
        fileContent.append(salVo.getBasic()).append(", ");
        fileContent.append(salVo.getGradePay()).append(", ");
        fileContent.append(salVo.getScalePay()).append(", ");
        fileContent.append(salVo.getScaleInc()).append(", ");
      }
      fileContent.append("\n");
    }
    response.setContentType("text/csv");
    response.setHeader("Content-Disposition", "attachment; filename=" + csvFileName);
    
    ServletOutputStream os = response.getOutputStream();
    os.write(fileContent.toString().getBytes());
    os.flush();
    os.close();
  }
  
  @RequestMapping(value={"/empSalaryRptDownload"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"text/csv"})
  @ResponseBody
  public void downloadEmpSalReport(HttpServletRequest request, HttpServletResponse response)
    throws IOException
  {
    DateFormat format = new SimpleDateFormat("yyyyMMdd_HHmmss");
    String csvFileName = "EmployeeSalaryReport_" + format.format(new Date()) + ".csv";
    
    List<EmpSalaryReportVO> employeesList = (List)request.getSession().getAttribute("empSalaryReport");
    StringBuilder fileContent = new StringBuilder("Name, Department, Head, Designation, Basic, Grade Pay, CA, UFA, FPA, TA, HRA, PF, LWP").append("\n");
    for (EmpSalaryReportVO employeeVO : employeesList)
    {
      fileContent.append(employeeVO.getFullName()).append(", ");
      fileContent.append(employeeVO.getDepartment()).append(", ");
      fileContent.append(employeeVO.getHeadName()).append(", ");
      fileContent.append(employeeVO.getDesignation()).append(", ");
      fileContent.append(employeeVO.getBasic()).append(", ");
      fileContent.append(employeeVO.getGradePay()).append(", ");
      fileContent.append(employeeVO.getCa()).append(", ");
      fileContent.append(employeeVO.getUfa()).append(", ");
      fileContent.append(employeeVO.getFpa()).append(", ");
      fileContent.append(employeeVO.getTa()).append(", ");
      fileContent.append(employeeVO.isHraFlag()).append(", ");
      fileContent.append(employeeVO.isPfFlag()).append(", ");
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
