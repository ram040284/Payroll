//package com.kcb.hrms.payroll.dataobjects;
package com.payroll.hrms.payroll.dataobjects;

import java.util.List;

import com.payroll.employee.allowance.dataobjects.EmpAllowance;
import com.payroll.employee.leave.dataobjects.Leave;

/**
 * Created by rajendra on 12/8/17.
 */
public class EmployeePayrollDTO {

    /**
     * Transfer Employee Payroll details from DAO to service
     *
     */

    private String deptCostHead;
    private double basic;
    private double gradePay;
    private boolean handicappedFlag;
    private double overTimeHours;
    private List<Allowance> listAllowances;
    private List<EmpAllowance> listEmpAllowances;
    private boolean hraFlag;
    private double noOfAbsentDays;
    private boolean pfFlag;

    private double additionalProventFund;
    private double afkFlag;
    private double pfRecovery;
    private double pfLoanRecovery;
    private double unionFee;
    private double electricityRecovery;
    private double courtRecovery;
    private double otherDeductions;
    private List<Leave> leaves;
    private double npa;
	private double wa;
	private double ca;
	private double uniformAlw;
	private double familyPlaningAlw;
	private double lic;
	private double cca;
	private double society;

    public String getDeptCostHead() {
        return deptCostHead;
    }

    public void setDeptCostHead(String deptCostHead) {
        this.deptCostHead = deptCostHead;
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

    public boolean isHandicappedFlag() {
        return handicappedFlag;
    }

    public void setHandicappedFlag(boolean handicappedFlag) {
        this.handicappedFlag = handicappedFlag;
    }

    public double getOverTimeHours() {
        return overTimeHours;
    }

    public void setOverTimeHours(double overTimeHours) {
        this.overTimeHours = overTimeHours;
    }

    public List<Allowance> getListAllowances() {
        return listAllowances;
    }

    public void setListAllowances(List<Allowance> listAllowances) {
        this.listAllowances = listAllowances;
    }

    public boolean isHraFlag() {
        return hraFlag;
    }

    public void setHraFlag(boolean hraFlag) {
        this.hraFlag = hraFlag;
    }

    public double getNoOfAbsentDays() {
        return noOfAbsentDays;
    }

    public void setNoOfAbsentDays(double noOfAbsentDays) {
        this.noOfAbsentDays = noOfAbsentDays;
    }

    public boolean isPfFlag() {
        return pfFlag;
    }

    public void setPfFlag(boolean pfFlag) {
        this.pfFlag = pfFlag;
    }

    public double getAdditionalProventFund() {
        return additionalProventFund;
    }

    public void setAdditionalProventFund(double additionalProventFund) {
        this.additionalProventFund = additionalProventFund;
    }

    public double getPfRecovery() {
        return pfRecovery;
    }

    public void setPfRecovery(double pfRecovery) {
        this.pfRecovery = pfRecovery;
    }

    public double getPfLoanRecovery() {
        return pfLoanRecovery;
    }

    public void setPfLoanRecovery(double pfLoanRecovery) {
        this.pfLoanRecovery = pfLoanRecovery;
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

    public void setElectricityRecovery(double electtricityRecovery) {
        this.electricityRecovery = electtricityRecovery;
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

    public double getAfkFlag() {
        return afkFlag;
    }

    public void setAfkFlag(double afkFlag) {
        this.afkFlag = afkFlag;
    }

	public List<Leave> getLeaves() {
		return leaves;
	}

	public void setLeaves(List<Leave> leaves) {
		this.leaves = leaves;
	}

	public List<EmpAllowance> getListEmpAllowances() {
		return listEmpAllowances;
	}

	public void setListEmpAllowances(List<EmpAllowance> listEmpAllowances) {
		this.listEmpAllowances = listEmpAllowances;
	}

	public double getNpa() {
		return npa;
	}

	public void setNpa(double npa) {
		this.npa = npa;
	}

	public double getWa() {
		return wa;
	}

	public void setWa(double wa) {
		this.wa = wa;
	}

	public double getCa() {
		return ca;
	}

	public void setCa(double ca) {
		this.ca = ca;
	}

	public double getUniformAlw() {
		return uniformAlw;
	}

	public void setUniformAlw(double uniformAlw) {
		this.uniformAlw = uniformAlw;
	}

	public double getFamilyPlaningAlw() {
		return familyPlaningAlw;
	}

	public void setFamilyPlaningAlw(double familyPlaningAlw) {
		this.familyPlaningAlw = familyPlaningAlw;
	}

	public double getLic() {
		return lic;
	}

	public void setLic(double lic) {
		this.lic = lic;
	}

	public double getCca() {
		return cca;
	}

	public void setCca(double cca) {
		this.cca = cca;
	}

	public double getSociety() {
		return society;
	}

	public void setSociety(double society) {
		this.society = society;
	}
}