package com.payroll.report.vo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.payroll.Utils;
import com.payroll.employee.business.EmployeeService;
import com.payroll.employee.salary.vo.SalaryVO;
import com.payroll.employee.vo.EmpContactVO;

public class EmployeeReportVO implements Serializable {

	private String firstName;
  private String lastName;
  private String middleName;
  private String designation;
  private String email;
  private String phone;
  private String joiningDate;
  private String lastWorkingDate;
  private String address;
  private String secAddress;
  private String dob;
  private String contactNo;
  private String pan;
  private String adharNo;
  private String lastPromotionDate;
  private int departmentId;
  private int designationId;
  private int employeeId;
  private String department;
  private String fullName;
  private String addressLine1;
  private String addressLine2;
  private String addressLine3;
  private String status;
  private String gender;
  private String headName;
  private Date rowUpdatedDate;
  private SalaryVO salaryVo;
  private EmpContactVO empContact;
  
  public EmployeeReportVO() {}
  
  public String getFullName()
  {
    return this.fullName;
  }
  
  private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
  
  public EmployeeReportVO(int employeeId, String firstName, String lastName, String middleName)
  {
    this.employeeId = employeeId;
    this.fullName = getName(firstName, middleName, lastName);
  }
  
  public EmployeeReportVO(int employeeId, String firstName, String lastName, String middleName, 
		  String pan, String aadhar, Date dob, String department, String head, String designation, 
		  String gender, Date joiningDate)
  {
    this.employeeId = employeeId;
   // this.email = Utils.safeTrim(email);
    this.pan = Utils.safeTrim(pan);
    this.adharNo = Utils.safeTrim(aadhar);
 //   this.phone = Utils.safeTrim(phone);
    this.firstName = Utils.safeTrim(firstName);
    this.middleName = Utils.safeTrim(middleName);
    this.lastName = Utils.safeTrim(lastName);
    this.designation = Utils.safeTrim(designation);
    this.department = Utils.safeTrim(department);
    this.dob = (dob != null ? this.dateFormat.format(dob) : "");
    /*this.addressLine1 = Utils.safeTrim(addressLine1);
    this.addressLine2 = Utils.safeTrim(addressLine2);
    this.addressLine3 = Utils.safeTrim(addressLine3);*/
   
    this.gender = Utils.safeTrim(gender);
    this.fullName = getName(firstName, middleName, lastName);
    this.joiningDate = (joiningDate != null ? this.dateFormat.format(joiningDate) : "");
    this.headName = Utils.safeTrim(head);
  }
  
  public EmployeeReportVO(int employeeId, String firstName, String lastName, String middleName, 
		  String email, String phone, String pan, String aadhar, Date dob, String department, 
		  String head, String designation, String addressLine1, String addressLine2, String addressLine3, 
		  String gender, Date joiningDate)
  {
    this.employeeId = employeeId;
    
    this.email = Utils.safeTrim(email);
    this.pan = Utils.safeTrim(pan);
    this.adharNo = Utils.safeTrim(aadhar);
    this.phone = Utils.safeTrim(phone);
    this.firstName = Utils.safeTrim(firstName);
    this.middleName = Utils.safeTrim(middleName);
    this.lastName = Utils.safeTrim(lastName);
    this.designation = Utils.safeTrim(designation);
    this.department = Utils.safeTrim(department);
    this.dob = (dob != null ? this.dateFormat.format(dob) : "");
    this.addressLine1 = Utils.safeTrim(addressLine1);
    this.addressLine2 = Utils.safeTrim(addressLine2);
    this.addressLine3 = Utils.safeTrim(addressLine3);
    this.address = (addressLine1 + " " + addressLine2 + " " + addressLine3);
    this.gender = Utils.safeTrim(gender);
    this.fullName = getName(firstName, middleName, lastName);
    this.joiningDate = (joiningDate != null ? this.dateFormat.format(joiningDate) : "");
    this.headName = Utils.safeTrim(head);
  }
  
  public EmployeeReportVO(int employeeId, String firstName, String lastName, String middleName, String email, String phone, 
		  String pan, String aadhar, Date dob, String department, String designation, String addressLine1, String addressLine2, 
		  String addressLine3, String gender, Date joiningDate, SalaryVO salVo)
  {
    this.employeeId = employeeId;
    
    this.email = email;
    this.pan = pan;
    this.adharNo = aadhar;
    this.phone = phone;
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.designation = designation;
    this.department = department;
    this.dob = (dob != null ? this.dateFormat.format(dob) : "");
    this.addressLine1 = Utils.safeTrim(addressLine1);
    this.addressLine2 = Utils.safeTrim(addressLine2);
    this.addressLine3 = Utils.safeTrim(addressLine3);
    this.address = (addressLine1 + addressLine2 + addressLine3);
    this.gender = gender;
    this.fullName = getName(firstName, middleName, lastName);
    this.joiningDate = (joiningDate != null ? this.dateFormat.format(joiningDate) : "");
    this.salaryVo = salVo;
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
  
  public String getEmail()
  {
    return this.email;
  }
  
  public String getPhone()
  {
    return this.phone;
  }
  
  public String getJoiningDate()
  {
    return this.joiningDate;
  }
  
  public String getLastWorkingDate()
  {
    return this.lastWorkingDate;
  }
  
  public String getAddress()
  {
    return this.address;
  }
  
  public String getDob()
  {
    return this.dob;
  }
  
  public String getContactNo()
  {
    return this.contactNo;
  }
  
  public String getPan()
  {
    return this.pan;
  }
  
  public String getAdharNo()
  {
    return this.adharNo;
  }
  
  public String getLastPromotionDate()
  {
    return this.lastPromotionDate;
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
  
  public String getAddressLine1()
  {
    return this.addressLine1;
  }
  
  public String getAddressLine2()
  {
    return this.addressLine2;
  }
  
  public String getAddressLine3()
  {
    return this.addressLine3;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public String getGender()
  {
    return this.gender;
  }
  
  public Date getRowUpdatedDate()
  {
    return this.rowUpdatedDate;
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
  
  public SalaryVO getSalaryVo()
  {
    return this.salaryVo;
  }
  
  public void setSalaryVo(SalaryVO salaryVo)
  {
    this.salaryVo = salaryVo;
  }
  
  public String getHeadName()
  {
    return this.headName;
  }
  
  public void setHeadName(String headName)
  {
    this.headName = headName;
  }

public EmpContactVO getEmpContact() {
	return empContact;
}

public void setEmpContact(EmpContactVO empContact) {
	if (empContact == null) {
		this.address = "";
		this.secAddress = "";
		empContact = new EmpContactVO();
	} else {
		this.empContact = empContact;
		Map<String, String> statesMap = new EmployeeService().getIndianStates();
		this.address = Utils.safeTrim(empContact.getAddressLine1()) + ", " ;
		this.address += Utils.isEmpty(empContact.getAddressLine2())? "" : Utils.safeTrim(empContact.getAddressLine2()) + ", "; 
		this.address += Utils.isEmpty(empContact.getAddressLine3())? "" : Utils.safeTrim(empContact.getAddressLine3()) + ", ";
		this.address += Utils.isEmpty(empContact.getCity())? "" : Utils.safeTrim(empContact.getCity())+ ", ";
		this.address += Utils.isEmpty(empContact.getState())? "" : statesMap.get(Utils.safeTrim(empContact.getState())) + ", ";
		this.address += Utils.isEmpty(empContact.getPin())? "" : Utils.safeTrim(empContact.getPin());
		 
		this.secAddress = Utils.safeTrim(empContact.getSecAddressLine1()) + ", " ;
		this.secAddress += Utils.isEmpty(empContact.getSecAddressLine2())? "" : Utils.safeTrim(empContact.getSecAddressLine2()) + ", "; 
		this.secAddress += Utils.isEmpty(empContact.getSecAddressLine3())? "" : Utils.safeTrim(empContact.getSecAddressLine3()) + ", ";
		this.secAddress += Utils.isEmpty(empContact.getSecCity())? "" : Utils.safeTrim(empContact.getSecCity())+ ", ";
		this.secAddress += Utils.isEmpty(empContact.getSecState())? "" : Utils.safeTrim(empContact.getSecState()) + ", ";
		this.secAddress += Utils.isEmpty(empContact.getSecPin())? "" : Utils.safeTrim(empContact.getSecPin());
	}
}

public String getSecAddress() {
	return secAddress;
}

public void setSecAddress(String secAddress) {
	this.secAddress = secAddress;
}
}
