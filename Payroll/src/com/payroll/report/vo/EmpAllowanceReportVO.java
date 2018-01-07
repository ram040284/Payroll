package com.payroll.report.vo;

import com.payroll.Utils;
import java.io.Serializable;
import java.text.SimpleDateFormat;

public class EmpAllowanceReportVO implements Serializable {
	
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
  private double cca;
  private double washingAllowance;
  private double convAllowance;
  private double nonPractAllowance;
  private double uniformAllowance;
  private double familyPlanAllowance;
  private double cycleAllowance;
  private boolean hraFlag;
  
  public EmpAllowanceReportVO() {}
  
  public String getFullName()
  {
    return this.fullName;
  }
  
  private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
  
  public EmpAllowanceReportVO(int employeeId, String firstName, String lastName, String middleName, 
		  double cca, double wa, double convAlwance, double npa, double ua, double fpa, double cycleAlwance, boolean hraFlag, 
		  String deptName, String headName, String designation)
  {
    this.employeeId = employeeId;
    this.fullName = getName(firstName, middleName, lastName);
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
  //CCA, WASHING_ALLOWANCE, CONV_ALLOWANCE, NON_PRACT_ALLOWANCE, UNIFORM_ALLOWANCE,
  	//FAMILY_PLANNING_ALLOWANCE, CYCLE_ALLOWANCE, HRA_FLAG ,
    this.cca = cca;
    this.washingAllowance = wa;
    this.convAllowance = convAlwance;
    this.nonPractAllowance = npa;
    this.uniformAllowance = ua;
    this.familyPlanAllowance = fpa;
    this.cycleAllowance = cycleAlwance;
    this.hraFlag = hraFlag;
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

public double getCca() {
	return cca;
}

public double getWashingAllowance() {
	return washingAllowance;
}

public double getConvAllowance() {
	return convAllowance;
}

public double getNonPractAllowance() {
	return nonPractAllowance;
}

public double getFamilyPlanAllowance() {
	return familyPlanAllowance;
}

public double getCycleAllowance() {
	return cycleAllowance;
}

public boolean isHraFlag() {
	return hraFlag;
}

public double getUniformAllowance() {
	return uniformAllowance;
}
  

}
