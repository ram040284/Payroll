package com.payroll.bank.business;

import java.util.List;

import com.payroll.bank.dataobjects.BankDetails;
import com.payroll.bank.dataobjects.BankDetailsDAO;

public class BankDetailsService {
	
	
		public List<BankDetails> getBankDetails(){
			return new BankDetailsDAO().getBankDetails();
		}
		
		public String addUpdateBankDetails(BankDetails bankDetails){
			return new BankDetailsDAO().addUpdateBankDetails(bankDetails);
		}
		
		public boolean deleteBankDetails(int bankId){
			return new BankDetailsDAO().deleteBankDetails(bankId);
		}
		
		public BankDetails getBankDetailsById(int bankId){
			return new BankDetailsDAO().getBankDetailsById(bankId);
		}


}
