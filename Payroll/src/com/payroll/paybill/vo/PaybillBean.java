package com.payroll.paybill.vo;

public class PaybillBean {
	
	private int sNo;
	private String description;
	private double cmValue;
	private double lmValue;
	private double difference;
	private boolean newLine;
	private int space;
	
	public PaybillBean(int sNo, String description, double cmValue, double lmValue){
		this.sNo = sNo;
		this.description = description;
		this.cmValue = cmValue;
		this.lmValue = lmValue;
		this.difference = (cmValue - lmValue);
		if(description.equalsIgnoreCase("Netpay") || description.equalsIgnoreCase("Total Deductions") || 
				description.equalsIgnoreCase("Grosspay")){
			if(description.equalsIgnoreCase("Netpay"))
				space =18; 
			newLine = true;
		}
	}

	public int getsNo() {
		return sNo;
	}

	public String getDescription() {
		return description;
	}

	public double getCmValue() {
		return cmValue;
	}

	public double getLmValue() {
		return lmValue;
	}

	public double getDifference() {
		return difference;
	}

	public boolean isNewLine() {
		return newLine;
	}

	public int getSpace() {
		return space;
	}
	
}
