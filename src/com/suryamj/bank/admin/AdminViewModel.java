package com.suryamj.bank.admin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.suryamj.bank.repo.AccountRepository;
import com.suryamj.bank.repo.Login;

public class AdminViewModel {
	AdminView admin;
	Login login;
	Scanner scan = new Scanner(System.in);
	AccountRepository accountRepo;

	AdminViewModel(AdminView adminView) {
		this.admin = adminView;
		this.login = new Login(this);
		this.accountRepo = new AccountRepository(this);

	}

	public boolean adminlogin(String adminId, String adminPassWord) {

		return login.adminlogin(adminId, adminPassWord);
	}

	public void allUserDetails() {
		System.out.println("Enter Your Choice : \n1.Customer Persional Details" + "\n2.Customer Account Details : ");
		byte accountDetailChoice = 0;
		try {
			System.out.println("Enter your choice : ");
			accountDetailChoice = scan.nextByte();
			accountRepo.allUserDetails(accountDetailChoice);
		} catch (InputMismatchException e) {
			System.out.println("Enter the 'Numeric' value.");
			scan.next();

		}
	}

	public void customerPersionalDeatails(ResultSet rs) {
		try {
			while (rs.next()) {
				System.out.println("\n***********************USER INFO***********************\n");
				System.out.println("Customer ID      : " + rs.getString("customerid"));
				System.out.println("Name             : " + rs.getString("name"));
				System.out.println("Gender           : " + rs.getString("gender"));
				System.out.println("Date of Birth    : " + rs.getString("dateofbirth"));
				System.out.println("Email            : " + rs.getString("email"));
				System.out.println("Phone Number     : " + rs.getString("phonenumber"));
				System.out.println("Account Type     : " + rs.getString("accounttype"));
				System.out.println("City             : " + rs.getString("city"));
				System.out.println("PassWord         : " + rs.getString("password"));
				System.out.println("\n***********************USER INFO***********************\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void customerAccountDetails(ResultSet rs) {
		try {
			while (rs.next()) {
				System.out.println("\n***********************ACCOUNT INFO***********************\n");
				System.out.println("Customer ID      : " + rs.getString("customerid"));
				System.out.println("Account Number   : " + rs.getString("accountnumber"));
				System.out.println("Date of AC Open  : " + rs.getString("dateofaccountopen"));
				System.out.println("Account Balance  : " + rs.getString("accountbalance"));
				System.out.println("Account Status   : " + rs.getString("accountstatus"));
				System.out.println("\n***********************ACCOUNT INFO***********************\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public boolean toRemoveUserAccount(int userId) {
		toSearchUserDetails("" + userId);
		System.out.println("press 1 to confirm Delete");
		byte delete = 0;
		boolean remove = true;
		try {
			System.out.println("Enter your choice : ");
			delete = scan.nextByte();
			remove = accountRepo.toRemoveUserAccount(delete, userId);
		} catch (InputMismatchException e) {
			System.out.println("Enter the 'Numeric' value.");
			scan.next();
		}
		return remove;
	}

	public void toSearchUserDetails(String user) {
		boolean continueSearch = true;
		while (continueSearch) {
			byte search = 0;
			System.out.println("\nEnter You Want to Search \n1.Name \n2.Customer ID  \n5.Back ");
			try {
				System.out.println("Enter your choice : ");
				search = scan.nextByte();
			} catch (InputMismatchException e) {
				System.out.println("Enter the 'Numeric' value.");
				scan.next();
			}

			switch (search) {

			case 1:
				scan.nextLine();
				System.out.println("\nEnter Name to Search : ");
				String name = scan.nextLine();
				if (accountRepo.toSearch(name, search, "customerdetails")) {
					continueSearch = false;
				} else {
					System.out.println("Name Not Found...");
				}
				break;
			case 2:
				String customerId;
				scan.nextLine();
				System.out.println("\nEnter Customer ID to Search : ");
				customerId = scan.nextLine();

				if (accountRepo.toSearch(customerId, search, "customerdetails")) {
					continueSearch = false;
				} else {
					System.out.println("Customer ID Not Found...");
				}
				break;

			case 5:
				continueSearch = false;
				break;
			default:
				System.out.println("\nEnter valid number...\n");
			}
		}
	}

}
