package com.suryamj.bank.dto;

import java.util.ArrayList;

public class Account {
	private static int add = 2200565;
	private int accountNumber = 2200564;
	private final String ifsc = "SBI06134";
	private final String name;
	private final long mobileNumber;
	private long balance;
	private final String password;
	private ArrayList<String> transaction;

	public Account(String username, String password, long mobileNumber2, long balance) {
		this.accountNumber = add++;
		this.name = username;
		this.password = password;
		this.mobileNumber = mobileNumber2;
		this.balance = balance;
		transaction = new ArrayList<>();
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

	public String getPassword() {
		return password;
	}

	public ArrayList<String> getTransaction() {
		return transaction;
	}

	public void setTransaction(ArrayList<String> transaction) {
		this.transaction = transaction;
	}
}
