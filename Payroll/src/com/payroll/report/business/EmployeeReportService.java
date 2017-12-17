package com.payroll.report.business;

import java.util.List;

import com.payroll.report.dataobjects.EmployeeReportDAO;
import com.payroll.report.vo.EmpSalaryReportVO;
import com.payroll.report.vo.EmployeeReportVO;

public class EmployeeReportService
{
  public List<EmployeeReportVO> getEmployees(int deptId, int headId)
  {
    return new EmployeeReportDAO().getEmployees(deptId, headId, "");
  }
  
  public List<EmpSalaryReportVO> getEmpSalaryReport(int deptId, int headId)
  {
    return new EmployeeReportDAO().getEmpSalaryReport(deptId, headId);
  }
}
