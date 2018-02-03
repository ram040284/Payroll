//package com.kcb.hrms.payroll.dataobjects;
package com.payroll.hrms.payroll.dataobjects;
import com.payroll.employee.allowance.dataobjects.EmpAllowance;
import com.payroll.employee.leave.dataobjects.Leave;
import com.payroll.employee.vo.EmployeeVO;
//import com.kcb.hrms.payroll.dao.EmployeePayrollDAO;
import com.payroll.hrms.payroll.dao.EmployeePayrollDAO;
import com.payroll.hrms.payroll.service.IncomeTaxCalculatorService;
import com.payroll.overtime.dataobjects.Overtime;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by rajendra on 12/7/17.
 */
public class EmployeePayroll {
    public static double DA_PERCENT = 136.00;
    public static double HRA_PERCENT = 30.00;
    public static int WORKING_DAYS=22;
    public static double PF_PERCENT=6.0;
    public static double CPF_PERCENT=10.0;

    private String empId;
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
    private double conveyanceAllowance;
    private double nonPracticingAllowance;
    private double uniformAllowance;
    private double familyPlanningAllowance;
    private double miscAllowance;
    private double totalAllowance;
    private double overTimeAmount;
    private double otherPayAmount;
    private double grossPay;
    private double absentAmount;
    private double afkRent;
    private double festAdvRecovery;
    private double profTax;
    private double lic;
    private double societyInstallment;
    private double grpInsurance;
    private boolean pfFlag;
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
    private double lfee;
   /* private double others;
    private double misc;
    */
    private boolean handicappedFlag;
    private double overTimeHours;
    private List<Allowance> listAllowances;
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
    
    private boolean hraFlag;
    private double absentDays;
    //This includes additional over time arrears or other amount which is other than regular monthly salary
    private double totalGrossPay;

    //deductions
    public EmployeePayroll(){
    	
    }
    public void setEmployee(EmployeeVO empVO){
    	this.employeeName = empVO.getFullName();
    	this.department = empVO.getDepartment();
    	this.deptCostHead = empVO.getHeadName();
    	System.out.println("deptCostHead:"+deptCostHead);
    	this.designation = empVO.getDesignation();
    	this.panNo = empVO.getPan();
    	this.dob = empVO.getDob();
    	this.joiningDate = empVO.getJoiningDate();
    	this.retirementDate = empVO.getRetirementDate();
    	this.employeeId = empVO.getEmployeeId();
    }
    
    public EmployeePayroll(double basic, double gradePay, String scale, double increment,
    		double afkRent, double pfLoanRcry, double unionFee, 
    		double electRcry, double courtRcry, double otherDeduct, double society, double cca, 
    		double licInstalAmt, double convayAlw, double fmlyPlgAlw, double npa, double wshngAlw, 
    		double uniformAlw, Boolean hraFlag, double festAdvRcry, double gis, double bankLoanRcry, 
    		double vlr, double cpfRcry, double overtimeHrs, double absentDays, String bankName, 
    		String bankAcctNo, int bankId){
    	this.basic = basic;
    	this.gradePay = gradePay;
    	this.afkRent = afkRent;
    	this.pfLoanRecovery = pfLoanRcry;
    	this.unionFee = unionFee;
    	this.electricityRecovery = electRcry;
    	this.courtRecovery = courtRcry;
    	this.otherDeductions = otherDeduct;
    	this.societyInstallment = society;
    	this.cca = cca;
    	this.lic = licInstalAmt;
    	this.conveyanceAllowance = convayAlw;
    	this.familyPlanningAllowance = fmlyPlgAlw;
    	this.nonPracticingAllowance = npa;
    	this.washingAllowance = wshngAlw;
    	this.uniformAllowance = uniformAlw;
    	this.hraFlag = hraFlag;
    	this.afkRent = afkRent;
    	this.festAdvRecovery = festAdvRcry;
    	this.grpInsurance = gis;
    	this.designation = "Business Analyst";
    	this.costHead = "Business Head";
    	this.bankAcctNo = bankAcctNo;
    	this.bankName = bankName;
    	this.bankId = bankId;
    	//this.bankLoanRecovery = bankLoanRcry;
    	//this.vlr = vlr;
    	//this.cpfRcry = cpfRcry;
    	
    	 calculateDA();
         calculateHRA();
         calculateTA();
         calculateOverTime();
         calculateProvidentFund();
         calculateGrossPay();
         calculateTotalAllowances();
         calculateTotalGrossPay();
         calculateProfessionalTax();
         processAbsentee();
         calculateDeductions();
         calculateNetPay();
    }
    
    public EmployeePayroll(EmployeePayrollDTO employeePayrollDTO){

        this.basic = employeePayrollDTO.getBasic();
        this.gradePay = employeePayrollDTO.getGradePay();
        this.handicappedFlag = employeePayrollDTO.isHandicappedFlag();
        this.overTimeHours = employeePayrollDTO.getOverTimeHours();
        this.listAllowances = employeePayrollDTO.getListAllowances();
        this.hraFlag = employeePayrollDTO.isHraFlag();
        this.afkRent = employeePayrollDTO.getAfkFlag();
        this.pfRecovery = employeePayrollDTO.getPfRecovery();
        this.pfLoanRecovery = employeePayrollDTO.getPfLoanRecovery();
        this.unionFee = employeePayrollDTO.getUnionFee();
        this.electricityRecovery = employeePayrollDTO.getElectricityRecovery();
        this.courtRecovery = employeePayrollDTO.getCourtRecovery();
        this.otherDeductions = employeePayrollDTO.getOtherDeductions();
        
        calculateDA();
        calculateHRA();
        calculateTA();
        calculateOverTime();
        calculateProvidentFund();
        calculateTotalAllowances();
        calculateGrossPay();
        calculateTotalGrossPay();
        processAbsentee();
        calculateDeductions();
        
    }

    /**
     * Load the Payroll data
     * Earnings: Basic pay, grade pay, HRA Flag, , LWP /
     * Absentee, over time details, allowances
     *  Deductions : PF Flag, AFK Rent, Light Bill, Festival Advance Installment, PF loan Amount
     */

    public void loadPayrollInfo(){
        EmployeePayrollDAO employeePayrollDAO = new EmployeePayrollDAO();
       // EmployeePayrollDTO empPayrollDTO = employeePayrollDAO.loadPayrollInfo(this.employeeId, new Date());
        //EmployeePayroll empPayroll = new EmployeePayroll(empPayrollDTO);
    }

    /**
     *
     */
    private void calculateDA(){
        this.dearnessAllowance = 0.0;
        if (this.basic + this.gradePay >0)
            this.dearnessAllowance = DA_PERCENT * (this.basic+ this.gradePay)/100;
    }

    /**
     * Calculate HRA
     */
    private void calculateHRA(){
        this.houseRentAllowance = 0.0;
        if (this.basic + this.gradePay >0 && hraFlag)
            this.houseRentAllowance = HRA_PERCENT * (this.basic+ this.gradePay)/100;

    }

    /**
     * Calculate Traveling allowance
     *
     */
    private void calculateTA(){

        if (!handicappedFlag) {

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
    }

    /**
     * Calculate Traveling allowance this does not include DA, HRA and TA
     *
     */
    private void calculateTotalAllowances(){

        this.totalAllowance = 0.0;
        /*for (Allowance allowance: this.listAllowances){
            this.totalAllowance = this.totalAllowance + allowance.getAllowance();
        }*/
        this.totalAllowance = this.cca 
				+ this.nonPracticingAllowance 
				+ this.washingAllowance 
				+ this.conveyanceAllowance 
				+ this.uniformAllowance
				+ this.familyPlanningAllowance;
        //this.totalAllowance += this.travelAllowance;
    }

    /**
     * Calculate over time
     *
     */
    private void calculateGrossPay(){

        this.grossPay = this.basic + this.gradePay + this.dearnessAllowance
                + this.travelAllowance 
                + this.totalAllowance;
            this.totalGrossPay = this.grossPay + this.overTimeAmount + this.otherPayAmount;
        //if leave without pay or absent
        if(absentDays > 0.0)
            processAbsentee();
    }
    //if employee has absentee or leave without pay
    private void processAbsentee(){
    	if(absentDays > 0){
	    	absentAmount = absentDays*this.basic/absentDays;
	        this.basic = (WORKING_DAYS-absentDays) * this.basic/WORKING_DAYS;
	        absentAmount += absentDays*this.gradePay/absentDays;
	        this.gradePay = (WORKING_DAYS-absentDays) * this.gradePay / WORKING_DAYS;
	        absentAmount = absentDays*this.houseRentAllowance/absentDays;
	        this.houseRentAllowance = (WORKING_DAYS-absentDays) * this.houseRentAllowance / WORKING_DAYS;
	        absentAmount = absentDays * this.travelAllowance/absentDays;
	        this.travelAllowance = (WORKING_DAYS - absentDays) * this.travelAllowance / WORKING_DAYS;
	
	        //this.totalAllowance = 0.0;
	        /*for (Allowance allowance: this.listAllowances){
	            allowance.setAllowance((allowance.getAllowance()/WORKING_DAYS)*(WORKING_DAYS - absentDays));
	            this.totalAllowance = this.totalAllowance + allowance.getAllowance();
	        }*/
    	}
        /*this.grossPay = this.basic
                    + this.gradePay
                    + this.dearnessAllowance
                    + this.travelAllowance
                    +this.houseRentAllowance
                    + this.totalAllowance;
                    */
    }
    private void calculateNetPay(){
    	this.netPay = 0;
    	this.netPay = this.grossPay 
    				- this.totalDeductions;
    }
    /**
     * Calculate over time
     *
     */
    private void calculateOverTime(){
        double perHourRate = (this.grossPay / WORKING_DAYS)/8;
        this.overTimeAmount = this.overTimeHours * perHourRate;
    }

    /*
     * Calculate total Gross pay monthly recurring + ovr time + other amount like arrears
     *
      */
    private void calculateTotalGrossPay(){
        this.totalGrossPay = this.grossPay + this.overTimeAmount + this.otherPayAmount;
    }

    //Calculate Deductions
    // Calculate PF
    private void calculateProvidentFund(){
        if (this.basic + this.gradePay > 0)
            if(pfFlag)
                this.providentFund = (this.basic + this.gradePay)*PF_PERCENT/100;
            else
                this.providentFund = (this.basic + this.gradePay)*CPF_PERCENT/100;
    }

    // Calculate Professional Tax
    private void calculateProfessionalTax(){
        profTax = 200.00;
        Date date= new Date();
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(date); 
        int month = cal.get(Calendar.MONTH);
        if(month == 1)
            profTax = 300.00;
    }
 
    private void calculateDeductions() {
    	this.totalDeductions = 0;
    	this.totalDeductions = this.absentAmount
    						+ this.lfee // need to check what is it mean
    						+ this.afkRent
    						+ this.festAdvRecovery
    						+ this.profTax
    						+ this.lic
    						+ this.societyInstallment
    						+ this.grpInsurance
    						+ this.bankLoanRcry
    						+ this.vehclLoanRcry
    						+ this.providentFund
    						+ this.apfacpf
    						+ this.pfLoanRecovery
    						+ this.cpfRecovery
    						+ this.incomeTax
    						+ this.unionFee
    						+ this.electricityRecovery
    						+ this.courtRecovery
    						+ this.otherDeductions
    						+ this.miscAllowance
    						+ this.unionFeeKss
    						+ this.pfInstment;
    						
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

	public double getConveyanceAllowance() {
		return conveyanceAllowance;
	}

	public void setConveyanceAllowance(double conveyanceAllowance) {
		this.conveyanceAllowance = conveyanceAllowance;
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

	/*public double getOthers() {
		return others;
	}*/

	/*public double getMisc() {
		return misc;
	}*/
}