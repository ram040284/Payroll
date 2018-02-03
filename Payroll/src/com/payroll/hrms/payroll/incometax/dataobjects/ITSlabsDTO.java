package com.payroll.hrms.payroll.incometax.dataobjects;

/**
 * Created by rajendra on 12/13/17.
 */
public class ITSlabsDTO {
    private double lowerSlabAmount;
    private double upperSlabAmount;
    private double incomeTaxPercent;
    private double surchargeAmount;
    private double cess;

    public double getLowerSlabAmount() {
        return lowerSlabAmount;
    }

    public void setLowerSlabAmount(double lowerSlabAmount) {
        this.lowerSlabAmount = lowerSlabAmount;
    }

    public double getUpperSlabAmount() {
        return upperSlabAmount;
    }

    public void setUpperSlabAmount(double upperSlabAmount) {
        this.upperSlabAmount = upperSlabAmount;
    }

    public double getIncomeTaxPercent() {
        return incomeTaxPercent;
    }

    public void setIncomeTaxPercent(double incomeTaxPercent) {
        this.incomeTaxPercent = incomeTaxPercent;
    }

    public double getSurchargeAmount() {
        return surchargeAmount;
    }

    public void setSurchargeAmount(double surchargeAmount) {
        this.surchargeAmount = surchargeAmount;
    }

    public double getCess() {
        return cess;
    }

    public void setCess(double cess) {
        this.cess = cess;
    }
}