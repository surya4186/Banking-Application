package com.suryamj.bank.createaccount;

import com.suryamj.bank.dto.Customers;
import com.suryamj.bank.repo.AccountRepository;

public class CreateAccountViewModel {
	CreateAccount createAccount;
	AccountRepository accountRepo;

	public CreateAccountViewModel(CreateAccount createAccount) {
		this.createAccount = createAccount;
		this.accountRepo = new AccountRepository(this);

	}

	public boolean isValidEmail(String email) {
		// TODO Auto-generated method stub
		return email.matches("^(.+)@(\\S+)$");

	}

	public static boolean isValidPhoneNumber(String number) {
		return number.matches("^\\d{10}$");
	}

	public boolean isValidPassWord(String password) {
		return password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,12}$");
	}

	public boolean isValidPanCard(String pancard) {
		return pancard.matches("[A-Z]{5}{0-9}{4}[A-Z]{1}");
	}

	public void insertData(Customers customer) {
		System.out.println(customer.getPhoneNumber() + "number");

		accountRepo.insertData(customer);

	}

}
