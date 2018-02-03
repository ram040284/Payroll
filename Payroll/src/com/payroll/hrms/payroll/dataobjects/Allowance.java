//package com.kcb.hrms.payroll.dataobjects;
package com.payroll.hrms.payroll.dataobjects;
/**
 * Created by rajendra on 12/8/17.
 */
public class Allowance {
    private String allowanceName;
    private double allowance;

    public String getAllowanceName(){
        return this.allowanceName;
    }
    public double getAllowance(){
        return this.allowance;
    }

    public void setAllowanceName(String allowanceName){
        this.allowanceName = allowanceName;
    }
    public void setAllowance(double allowance){
        this.allowance = allowance;
    }
}
