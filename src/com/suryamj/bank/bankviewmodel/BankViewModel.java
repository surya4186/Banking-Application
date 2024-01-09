package com.suryamj.bank.bankviewmodel;

import java.util.HashMap;
import java.util.Map;

import com.suryamj.bank.bankmodel.BankModel;
import com.suryamj.bank.bankview.BankView;

public class BankViewModel {
	BankView bankView;
	BankModel bankModel;

	public BankViewModel(BankView bankView) {
		this.bankView = bankView;
		bankModel = new BankModel(this);

	}

	public Map createAccount(String username, String password, long mobileNumber, long amount) {
		
		return bankModel.createAccount( username,password,mobileNumber,amount);

	}

}
