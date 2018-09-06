//package com.kcb.hrms.payroll.dataobjects;
package com.payroll.hrms.payroll.dataobjects;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.payroll.employee.allowance.dataobjects.EmpAllowance;
import com.payroll.employee.vo.EmployeeVO;

/**
 * Created by rajendra on 12/7/17.
 */
public class EmployeePayroll {
    public static double DA_PERCENT = 139.00;
    public static double HRA_PERCENT = 30.00;
    public static int WORKING_DAYS=22;
    public static double PF_PERCENT=6.0;
    public static double CPF_PERCENT=10.0;
  //  private String empId;
    private int employeeId;
    //income
    private String birthDate;
    private String deptCostHead;

    private double basic;
    private double gradePay;
    private double dearnessAllowance;
    private double houseRentAllowance;
    private double travelAllowance;
    private double bankLoanRcry;
    private double vehclLoanRcry;
    private double pfInstment;
    private double unionFeeKss;
    private double cca;
    private double washingAllowance;
    private double nonPracticingAllowance;
    private double uniformAllowance;
    private double familyPlanningAllowance;
    

	private double miscAllowance;
    private double totalAllowance;
    private double overTimeAmount;
    private double otherPayAmount;
    private double grossPay;
    private double absentAmount;
    private double festAdvRecovery;
    private double profTax;
    private double lic;
    private double societyInstallment;
    private double grpInsurance;
    private byte pfFlag;
    private double providentFund;
    private double additionalProventFund;
    private double pfRecovery;
    private double pfLoanRecovery;
    private double incomeTax;
    private double unionFee;
    private double electricityRecovery;
    private double courtRecovery;
    private double otherDeductions;
    private double totalDeductions;
    private double netPay;
    private double apfacpf;
    private double cpfRecovery;
    private double afkRent; //AFKRENT
    private double lfee; //RENT from EMP_VAR_DEDUCTIONS table
   /* private double others;
    private double misc;
    */
    private byte handicappedFlag;
    private byte taFlag; // false if employee leaves within 2km radiu of office
    private double overTimeHours;
    private List<EmpAllowance> listEmpAllowances;
    private String employeeName;
    private String designation;
    private String department;
    private String costHead;
    private String joiningDate;
    private String retirementDate;
    private String dob;
    private String bankAcctNo;
    private String bankName;
    private int bankId;
    private String incrementDate;
    private double incrementAmt;
    //private double daRate;
    //private double hraRate;
    private String scale;
    private String panNo;
    private String aadharNo;
    private String pfNumber;
    private String scalePay;
    
    private boolean hraFlag;
    private double absentDays;
    //This includes additional over time arrears or other amount which is other than regular monthly salary
    private double totalGrossPay;
    private double ca;
    private double otherAllowance;
    private double tAllowance;
    private int month;

    //deductions
    public EmployeePayroll(){
    	
    }
    public void setEmployee(EmployeeVO empVO){
    	this.employeeName = empVO.getFullName();
    	this.department = empVO.getDepartment();
    	this.deptCostHead = empVO.getHeadName();
    	this.designation = empVO.getDesignation();
    	this.panNo = empVO.getPan();
    	this.dob = empVO.getDob();
    	this.joiningDate = empVO.getJoiningDate();
    	this.retirementDate = empVO.getRetirementDate();
    	this.employeeId = empVO.getEmployeeId();
    }
    
   public EmployeePayroll(int employeeId, byte handicapFlag, double basic, double gradePay, String scalePay, String scaleCode, double otherPayAmount,
    		double cca, double cycleAllowance, double otherAllowance, double fmlyPlgAlw, double npa, double wshngAlw, double uniformAlw, boolean hraFlag,byte pfFlag, byte taFlag, double tAllowance,
    		double unionFee, double unionFeeKss, double lfee, double electricityRecovery, double courtRcry, double gis, double afkRent, double pfLoanRecovery, double otherDeduct,
    		double society,  double incomeTax, double licInstalAmt, double pfLoanRcry, double cpfCont, double apfacpf, double cpfRcry,
    		double festAdvRcry,  double bankLoanRcry,  double absentAmount, double overtimeHours, String bankName, 
    		String bankAcctNo, int bankId, Date incrementDate, double incrementAmt, int month){
	   
	    this.basic = basic;
    	this.gradePay = gradePay;
    	this.scalePay = scalePay;
    	this.scale = scaleCode;
//    	this.pfLoanRecovery = pfLoanRcry;
    	this.unionFeeKss = unionFeeKss;
    	this.unionFee = unionFee;
    	this.ca = cycleAllowance;
    	this.courtRecovery = courtRcry;
    	this.otherDeductions = otherDeduct;
    	this.societyInstallment = society;
    	this.cca = cca;
    	this.lic = licInstalAmt;
    	this.familyPlanningAllowance = fmlyPlgAlw;
    	this.nonPracticingAllowance = npa;
    	this.washingAllowance = wshngAlw;
    	this.uniformAllowance = uniformAlw;
    	this.hraFlag = hraFlag;
//    	this.afkRent = afkRent; Code repeat - Prasad
    	this.festAdvRecovery = festAdvRcry;
    	this.grpInsurance = gis;
    	this.designation = "Business Analyst";
    	this.costHead = "Business Head";
    	this.bankAcctNo = bankAcctNo;
    	this.bankName = bankName;
    	this.bankId = bankId;
    	this.pfFlag = pfFlag;
    	this.apfacpf = apfacpf;
    	this.incomeTax = incomeTax;
    	this.afkRent = afkRent; //AFKRent
    	this.lfee = lfee; //RENT
    	this.pfLoanRecovery = pfLoanRecovery;
    	this.incrementDate = new SimpleDateFormat("MMM yyyy", Locale.ENGLISH).format(incrementDate);
    	this.incrementAmt = incrementAmt;
    	this.taFlag = taFlag;
    	this.handicappedFlag = handicapFlag;
    	this.otherAllowance = otherAllowance;
    	this.otherPayAmount = otherPayAmount;
    	this.absentAmount = absentAmount;
    	this.cpfRecovery = cpfRcry;
    	this.tAllowance = tAllowance;
    	this.employeeId = employeeId;
    	//this.bankLoanRecovery = bankLoanRcry;
    	//this.vlr = vlr;
    	//this.cpfRcry = cpfRcry;
    	this.month = month;
    	
    	 calculateDA();
         calculateHRA();
         this.travelAllowance = calculateTA();
         calculateOverTime();
         calculateProvidentFund();
         calculateTotalAllowances();
         calculateGrossPay();
         calculateTotalGrossPay();
         calculateProfessionalTax();
         processAbsentee();
         calculateDeductions();
         calculateNetPay();
    }
    
//    Is it being used??? - Prasad
//    public EmployeePayroll(EmployeePayrollDTO employeePayrollDTO){
//
//        this.basic = employeePayrollDTO.getBasic();
//        this.gradePay = employeePayrollDTO.getGradePay();
//        this.handicappedFlag = employeePayrollDTO.isHandicappedFlag();
//        this.overTimeHours = employeePayrollDTO.getOverTimeHours();
//        this.listEmpAllowances = employeePayrollDTO.getListEmpAllowances();
//        this.hraFlag = employeePayrollDTO.isHraFlag();
//        this.pfRecovery = employeePayrollDTO.getPfRecovery();
//        this.pfLoanRecovery = employeePayrollDTO.getPfLoanRecovery();
//        this.unionFee = employeePayrollDTO.getUnionFee();
//        this.electricityRecovery = employeePayrollDTO.getElectricityRecovery();
//        this.courtRecovery = employeePayrollDTO.getCourtRecovery();
//        this.otherDeductions = employeePayrollDTO.getOtherDeductions();
//        this.incomeTax = employeePayrollDTO.getIncomeTax();
//        this.afkRent = employeePayrollDTO.getAfkFlag();
//        this.lfee = employeePayrollDTO.getRent();
//        this.otherPayAmount = employeePayrollDTO.getOtherPay();
//        
//        calculateDA();
//        calculateHRA();
//        this.travelAllowance = calculateTA();
//        calculateOverTime();
//        calculateProvidentFund();
//        calculateTotalAllowances();
//        calculateGrossPay();
//        calculateTotalGrossPay();
//        processAbsentee();
//        calculateDeductions();
//        
//    }

   /**
     *
     */
    private void calculateDA(){
        this.dearnessAllowance = 0.0;
        if (this.basic + this.gradePay >0)
            this.dearnessAllowance = Math.round(DA_PERCENT * (this.basic+ this.gradePay)/100);
    }

    /**
     * Calculate HRA
     */
    private void calculateHRA(){
    	System.out.println("******Entered :"+ "calculateHR()*******************************");
    	
        this.houseRentAllowance = 0.0;
        if (this.basic + this.gradePay >0 && hraFlag)
            this.houseRentAllowance = Math.round(HRA_PERCENT * (this.basic+ this.gradePay)/100);
        
        System.out.println("******Entered :"+ "calculateHR()*******************************"+ houseRentAllowance);

    }

    /**
     * Calculate Traveling allowance. If person leaves within 2 km radius TA =0;
     *
     */
    private double calculateTA(){
    	
    	//FIXME: Prasad - TA flag needs to set correctly for employees who are on medical leave
    	
    	if ((taFlag == 1) || (taFlag == 2 && this.month != 5) ) {
    		
    		if (handicappedFlag == 0 || handicappedFlag == 2) {
    			
    			if (this.gradePay >= 5400.00)
    				travelAllowance = 2400.00;
    			else if (this.gradePay >= 4400.00 && this.gradePay < 5400.00)
    				travelAllowance = 1200.00;
    			else
    				travelAllowance = 400.00;
    		} else{
    			if(this.gradePay>=4400.00)
    				travelAllowance = 2400;
    			else
    				travelAllowance = 2000;
    		}
    		
    	} else {
    		travelAllowance = 0;
    	}
    	
    	return travelAllowance;
    	

    }

    /**
     * Calculate Traveling allowance this does not include DA, HRA and TA
     *
     */
    private void calculateTotalAllowances(){

        this.totalAllowance = 0.0;
        
        this.totalAllowance = Math.round(this.cca + this.nonPracticingAllowance + this.washingAllowance + this.uniformAllowance + this.ca + this.familyPlanningAllowance + this.otherAllowance);
    }

    /**
     * Calculate over time
     *
     */
    private void calculateGrossPay(){
    	

        this.grossPay = this.basic + this.gradePay + this.dearnessAllowance + this.travelAllowance + this.houseRentAllowance + this.totalAllowance + this.otherPayAmount + this.tAllowance;
        this.totalGrossPay = Math.round(this.grossPay + this.overTimeAmount);
        //if leave without pay or absent
        if(absentDays > 0.0)
            processAbsentee();
    }
    //if employee has absentee or leave without pay
    private void processAbsentee(){
    	if(absentDays > 0)
    		this.absentAmount = Math.round((this.grossPay / WORKING_DAYS) * absentDays);
       
    }
    private void calculateNetPay(){
    	this.netPay = 0;
    	
		if(this.totalGrossPay>this.grossPay) {
			this.netPay = Math.round(this.totalGrossPay - this.totalDeductions);
		} else {
			this.netPay = Math.round(this.grossPay - this.totalDeductions);
		}
		
		if (this.netPay <= 0 ) {
			this.netPay = 0;
			this.providentFund = 0;
			this.lfee = 0;
			this.afkRent = 0;
			this.festAdvRecovery = 0;
			this.profTax = 0;
			this.lic = 0;
			this.societyInstallment = 0;
			this.grpInsurance = 0;
			this.providentFund = 0;
			this.apfacpf = 0;
			this.pfLoanRecovery = 0;
			this.cpfRecovery = 0;
			this.incomeTax = 0;
			this.unionFee = 0;
			this.courtRecovery = 0;
			this.otherDeductions = 0;
			this.miscAllowance = 0;
			this.unionFeeKss = 0;
			this.pfInstment = 0;
		}
		
		this.totalDeductions = addDeductions();
		
    	
    }
    /**
     * Calculate over time
     *
     */
    private void calculateOverTime(){
        double perHourRate = (this.grossPay / WORKING_DAYS)/8;
        this.overTimeAmount = Math.round(this.overTimeHours * perHourRate);
    }

    /*
     * Calculate total Gross pay monthly recurring + ovr time + other amount like arrears
     *
      */
    private void calculateTotalGrossPay(){
        this.totalGrossPay = Math.round(this.grossPay);
    }

    //Calculate Deductions
    // Calculate PF
    private void calculateProvidentFund(){
    	
    		if (this.basic + this.gradePay > 0) {
    			
    			if (pfFlag == 0) { //Calculate CPF
    				this.providentFund = Math.round((this.basic + this.gradePay+ this.dearnessAllowance)*CPF_PERCENT/100); // 10 %
    			} else if (pfFlag == 1) { //Calculate PF
    				this.providentFund = Math.round((this.basic + this.gradePay)*PF_PERCENT/100); // 6% BEfore 01/01/2004
    			} else if (pfFlag == 2 || pfFlag == 3) { // (2) Employees whose PF is not setup or (3) Employees who are retiring in 6 months
    				this.providentFund = 0;
    			}
    			
    			//FIXME: Prasad - CPF will be 0 upto 1 year for newly joined employees. It needs to be recovered in next year.
    			
    		}
    	
    }

    // Calculate Professional Tax
    private void calculateProfessionalTax(){
    	
    	profTax = 0.0;
    	
    	
    	if (handicappedFlag == 0) {
    		profTax = 200.00;
    		Date date= new Date();
    		
    		Calendar cal = Calendar.getInstance();
    		cal.setTime(date); 
    		int month = cal.get(Calendar.MONTH);
    		if(month == 1)
    			profTax = 300.00;
    	}
        
    }
    
    private void calculateDeductions() {
    	this.totalDeductions = 0;
    	this.totalDeductions = addDeductions();
    	
    }
    
    private double addDeductions() {
    	return Math.round(this.absentAmount
				+ this.lfee 
				+ this.afkRent
				+ this.festAdvRecovery
				+ this.profTax
				+ this.lic
				+ this.societyInstallment
				+ this.grpInsurance
				+ this.providentFund
				+ this.apfacpf
				+ this.pfLoanRecovery
				+ this.cpfRecovery
				+ this.incomeTax
				+ this.unionFee
				+ this.courtRecovery
				+ this.otherDeductions
				+ this.miscAllowance
				+ this.unionFeeKss
				+ this.pfInstment);
    }
    
	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getCostHead() {
		return costHead;
	}

	public void setCostHead(String costHead) {
		this.costHead = costHead;
	}

	public String getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getPanNo() {
		return panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	public String getAadharNo() {
		return aadharNo;
	}

	public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
	}

	public String getPfNumber() {
		return pfNumber;
	}

	public void setPfNumber(String pfNumber) {
		this.pfNumber = pfNumber;
	}

	public String getBankAcctNo() {
		return bankAcctNo;
	}

	public void setBankAcctNo(String bankAcctNo) {
		this.bankAcctNo = bankAcctNo;
	}

	public double getBasic() {
		return basic;
	}

	public void setBasic(double basic) {
		this.basic = basic;
	}

	public double getGradePay() {
		return gradePay;
	}

	public void setGradePay(double gradePay) {
		this.gradePay = gradePay;
	}

	public double getHouseRentAllowance() {
		return houseRentAllowance;
	}

	public void setHouseRentAllowance(double houseRentAllowance) {
		this.houseRentAllowance = houseRentAllowance;
	}

	public double getTravelAllowance() {
		return travelAllowance;
	}

	public void setTravelAllowance(double travelAllowance) {
		this.travelAllowance = travelAllowance;
	}

	public double getTotalAllowance() {
		return totalAllowance;
	}

	public void setTotalAllowance(double totalAllowance) {
		this.totalAllowance = totalAllowance;
	}

	public double getOverTimeAmount() {
		return overTimeAmount;
	}

	public void setOverTimeAmount(double overTimeAmount) {
		this.overTimeAmount = overTimeAmount;
	}

	public double getGrossPay() {
		return grossPay;
	}

	public void setGrossPay(double grossPay) {
		this.grossPay = grossPay;
	}

	public double getTotalGrossPay() {
		return totalGrossPay;
	}

	public void setTotalGrossPay(double totalGrossPay) {
		this.totalGrossPay = totalGrossPay;
	}

	public double getAfkRent() {
		return afkRent;
	}

	public void setAfkRent(double afkRent) {
		this.afkRent = afkRent;
	}

	public double getFestAdvRecovery() {
		return festAdvRecovery;
	}

	public void setFestAdvRecovery(double festAdvRecovery) {
		this.festAdvRecovery = festAdvRecovery;
	}

	public double getProfTax() {
		return profTax;
	}

	public void setProfTax(double profTax) {
		this.profTax = profTax;
	}

	public double getLic() {
		return lic;
	}

	public void setLic(double lic) {
		this.lic = lic;
	}

	public double getGrpInsurance() {
		return grpInsurance;
	}

	public void setGrpInsurance(double grpInsurance) {
		this.grpInsurance = grpInsurance;
	}

	public double getPfLoanRecovery() {
		return pfLoanRecovery;
	}

	public void setPfLoanRecovery(double pfLoanRecovery) {
		this.pfLoanRecovery = pfLoanRecovery;
	}

	public double getIncomeTax() {
		return incomeTax;
	}

	public void setIncomeTax(double incomeTax) {
		this.incomeTax = incomeTax;
	}

	public double getUnionFee() {
		return unionFee;
	}

	public void setUnionFee(double unionFee) {
		this.unionFee = unionFee;
	}

	public double getElectricityRecovery() {
		return electricityRecovery;
	}

	public void setElectricityRecovery(double electricityRecovery) {
		this.electricityRecovery = electricityRecovery;
	}

	public double getCourtRecovery() {
		return courtRecovery;
	}

	public void setCourtRecovery(double courtRecovery) {
		this.courtRecovery = courtRecovery;
	}

	public double getOtherDeductions() {
		return otherDeductions;
	}

	public void setOtherDeductions(double otherDeductions) {
		this.otherDeductions = otherDeductions;
	}

	public double getTotalDeductions() {
		return totalDeductions;
	}

	public void setTotalDeductions(double totalDeductions) {
		this.totalDeductions = totalDeductions;
	}

	public double getNetPay() {
		return netPay;
	}

	public void setNetPay(double netPay) {
		this.netPay = netPay;
	}

	public double getDearnessAllowance() {
		return dearnessAllowance;
	}

	public void setDearnessAllowance(double dearnessAllowance) {
		this.dearnessAllowance = dearnessAllowance;
	}

	public double getCca() {
		return cca;
	}

	public void setCca(double cca) {
		this.cca = cca;
	}

	public double getWashingAllowance() {
		return washingAllowance;
	}

	public void setWashingAllowance(double washingAllowance) {
		this.washingAllowance = washingAllowance;
	}


	public double getNonPracticingAllowance() {
		return nonPracticingAllowance;
	}

	public void setNonPracticingAllowance(double nonPracticingAllowance) {
		this.nonPracticingAllowance = nonPracticingAllowance;
	}

	public double getUniformAllowance() {
		return uniformAllowance;
	}

	public void setUniformAllowance(double uniformAllowance) {
		this.uniformAllowance = uniformAllowance;
	}

	public double getFamilyPlanningAllowance() {
		return familyPlanningAllowance;
	}

	public void setFamilyPlanningAllowance(double familyPlanningAllowance) {
		this.familyPlanningAllowance = familyPlanningAllowance;
	}

	public double getAbsentAmount() {
		return absentAmount;
	}

	public void setAbsentAmount(double absentAmount) {
		this.absentAmount = absentAmount;
	}

	public double getSocietyInstallment() {
		return societyInstallment;
	}

	public void setSocietyInstallment(double societyInstallmanet) {
		this.societyInstallment = societyInstallmanet;
	}

	public double getProvidentFund() {
		return providentFund;
	}

	public void setProvidentFund(double providentFund) {
		this.providentFund = providentFund;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getRetirementDate() {
		return retirementDate;
	}

	public void setRetirementDate(String retirementDate) {
		this.retirementDate = retirementDate;
	}

	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	public String getIncrementDate() {
		return incrementDate;
	}

	public void setIncrementDate(String incrementDate) {
		this.incrementDate = incrementDate;
	}

	public double getIncrementAmt() {
		return incrementAmt;
	}

	public void setIncrementAmt(double incrementAmt) {
		this.incrementAmt = incrementAmt;
	}

	public double getDaRate() {
		return DA_PERCENT;
	}

	/*public void setDaRate(double daRate) {
		this.daRate = daRate;
	}*/

	public double getHraRate() {
		return HRA_PERCENT;
	}

	/*public void setHraRate(double hraRate) {
		this.hraRate = hraRate;
	}*/

	public double getBankLoanRcry() {
		return bankLoanRcry;
	}

	public void setBankLoanRcry(double bankLoanRcry) {
		this.bankLoanRcry = bankLoanRcry;
	}

	public double getVehclLoanRcry() {
		return vehclLoanRcry;
	}

	public void setVehclLoanRcry(double vehclLoanRcry) {
		this.vehclLoanRcry = vehclLoanRcry;
	}

	public double getPfInstment() {
		return pfInstment;
	}

	public void setPfInstment(double pfInstment) {
		this.pfInstment = pfInstment;
	}

	public String getScalePay() {
		return scalePay;
	}
	public void setScalePay(String scalePay) {
		this.scalePay = scalePay;
	}
	public double getUnionFeeKss() {
		return unionFeeKss;
	}

	public void setUnionFeeKss(double unionFeeKss) {
		this.unionFeeKss = unionFeeKss;
	}

	public double getApfacpf() {
		return apfacpf;
	}

	public double getCpfRecovery() {
		return cpfRecovery;
	}

	public double getLfee() {
		return lfee;
	}

	public double getOtherPayAmount() {
		return otherPayAmount;
	}

	public double getMiscAllowance() {
		return miscAllowance;
	}
	public String getDeptCostHead() {
		return deptCostHead;
	}
	public void setDeptCostHead(String deptCostHead) {
		this.deptCostHead = deptCostHead;
	}
	public void setLfee(double lfee) {
		this.lfee = lfee;
	}
	public int getBankId() {
		return bankId;
	}
	public double getCa() {
		return ca;
	}
	public void setCa(double ca) {
		this.ca = ca;
	}
	public void setOtherPayAmount(double otherPayAmount) {
		this.otherPayAmount = otherPayAmount;
	}
	public double gettAllowance() {
		return tAllowance;
	}
	public void settAllowance(double tAllowance) {
		this.tAllowance = tAllowance;
	}

	/*public double getOthers() {
		return others;
	}*/

	/*public double getMisc() {
		return misc;
	}*/
}