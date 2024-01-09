package com.suryamj.bank.bankview;

import com.suryamj.bank.dto.Account;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import com.suryamj.bank.admin.AdminView;
import com.suryamj.bank.bankviewmodel.BankViewModel;
import com.suryamj.bank.createaccount.CreateAccount;

public class BankView {
	BankViewModel viewModel;
	Map<Integer, Account> account;
	Scanner scan = new Scanner(System.in);
	CreateAccount createAccount;
	AdminView adminView;

	public BankView() {
		this.createAccount = new CreateAccount(this);
		this.adminView = new AdminView(this);

	}

	public void start() {
		while (true) {
			System.out.println("========Bank Application==========");
			System.out.println("\t1. create Account \n\t2.Manage Account \n\t3.exit");

			System.out.println("enter the Nummber");
			switch (scan.nextInt()) {
			case 1: {
				createAccount.showMenu();
				break;
			}

			case 2: {
				System.out.println("\nEnter Your Choice : \n1.Admin \n2.Customer \n3.Back ");
				int options = scan.nextInt();
				if (options == 1) {
					adminView.showMenu();

				}
//				break;
			}

			case 3: {
				System.out.println("Thank you");
				System.exit(0);
			}
			default:
				System.out.println("Invalid Option");
			}
			break;
		}

	}
//
//	public void createAccount() {
//		System.out.println("Enter username : ");
//		String username = scan.next();
//		String password;
//		System.out.println("Enter password : ");
//		password = scan.next();
//		System.out.println("Enter mobile Number : ");
//		long mobileNumber = scan.nextLong();
//
//		long amount = 1000;
//		account = viewModel.createAccount(username, password, mobileNumber, amount);
//		for (Entry<Integer, Account> entry : account.entrySet())
//			System.out.println("AccountNumber = " + entry.getKey() + ", balance = " + entry.getValue().getBalance());
//
//	}

}
