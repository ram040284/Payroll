package com.payroll.incomtax.business;

import java.util.List;

import com.payroll.incomtax.dataobjects.IncomtaxSlab;
import com.payroll.incomtax.dataobjects.IncomtaxSlabDAO;

public class IncomtaxSlabService {
	
	public List<IncomtaxSlab> getTaxSlabs(){
		return new IncomtaxSlabDAO().getIncomtaxSlabs();
	}
	
	public String addUpdateTaxSlab(IncomtaxSlab incomtaxSlab){
		return new IncomtaxSlabDAO().addUpdate(incomtaxSlab);
	}
	
	public boolean deleteTaxSlab(int incomtaxId){
		return new IncomtaxSlabDAO().deleteIncomtaxSlab(incomtaxId);
	}
	
	public IncomtaxSlab getTaxSlabById(int incomtaxId){
		return new IncomtaxSlabDAO().getIncomtaxSlabById(incomtaxId);
	}


}
