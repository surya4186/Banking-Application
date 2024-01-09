package com.suryamj.bank.bankmodel;

import java.util.HashMap;
import java.util.Map;

import com.suryamj.bank.bankviewmodel.BankViewModel;
import com.suryamj.bank.dto.Account;

public class BankModel {
	BankViewModel bankViewModel;

	public BankModel(BankViewModel bankViewModel) {
		this.bankViewModel = bankViewModel;

	}

	public Map createAccount(String username, String password, long mobileNumber, long amount) {
		Account account = new Account(username, password, mobileNumber, amount);
		Map<Integer, Account> accountMap = new HashMap<>();
		accountMap.put(account.getAccountNumber(), account);
		System.out.println("Account created Successfully");

		return accountMap;
	}

}
