package com.payroll.report.vo;

import com.payroll.Utils;
import java.io.Serializable;
import java.text.SimpleDateFormat;

public class EmpSalaryReportVO
  implements Serializable
{
  private String firstName;
  private String lastName;
  private String middleName;
  private String designation;
  private int departmentId;
  private int designationId;
  private int employeeId;
  private String department;
  private String fullName;
  private String headName;
  private double basic;
  private double gradePay;
  private double ca;
  private double ufa;
  private double fpa;
  private double ta;
  private boolean hraFlag;
  private boolean pfFlag;
  private int lwp;
  
  public EmpSalaryReportVO() {}
  
  public String getFullName()
  {
    return this.fullName;
  }
  
  private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
  
  public EmpSalaryReportVO(int employeeId, String firstName, String lastName, String middleName, double basic, double gradePay, double ca, double ufa, double fpa, double ta, boolean hraFlag, boolean pfFlag, int lwp, String deptName, String headName, String designation)
  {
    this.employeeId = employeeId;
    this.fullName = getName(firstName, middleName, lastName);
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.basic = basic;
    this.gradePay = gradePay;
    this.ca = ca;
    this.ufa = ufa;
    this.fpa = fpa;
    this.ta = ta;
    this.hraFlag = hraFlag;
    this.pfFlag = pfFlag;
    this.lwp = lwp;
    this.department = deptName;
    this.headName = headName;
    this.designation = designation;
  }
  
  public String getFirstName()
  {
    return this.firstName;
  }
  
  public String getLastName()
  {
    return this.lastName;
  }
  
  public String getMiddleName()
  {
    return this.middleName;
  }
  
  public String getDesignation()
  {
    return this.designation;
  }
  
  public int getDepartmentId()
  {
    return this.departmentId;
  }
  
  public int getDesignationId()
  {
    return this.designationId;
  }
  
  public int getEmployeeId()
  {
    return this.employeeId;
  }
  
  public String getDepartment()
  {
    return this.department;
  }
  
  public SimpleDateFormat getDateFormat()
  {
    return this.dateFormat;
  }
  
  private String getName(String fName, String mName, String lName)
  {
    StringBuffer fullNameSB = new StringBuffer(fName);
    mName = Utils.safeTrim(mName);
    if (!mName.equals("")) {
      fullNameSB.append(" ");
    }
    fullNameSB.append(Utils.safeTrim(mName)).append(" ").append(Utils.safeTrim(lName));
    return fullNameSB.toString();
  }
  
  public String getHeadName()
  {
    return this.headName;
  }
  
  public void setHeadName(String headName)
  {
    this.headName = headName;
  }
  
  public double getBasic()
  {
    return this.basic;
  }
  
  public double getGradePay()
  {
    return this.gradePay;
  }
  
  public double getCa()
  {
    return this.ca;
  }
  
  public double getUfa()
  {
    return this.ufa;
  }
  
  public double getFpa()
  {
    return this.fpa;
  }
  
  public double getTa()
  {
    return this.ta;
  }
  
  public boolean isHraFlag()
  {
    return this.hraFlag;
  }
  
  public boolean isPfFlag()
  {
    return this.pfFlag;
  }
  
  public int getLwp()
  {
    return this.lwp;
  }
}
