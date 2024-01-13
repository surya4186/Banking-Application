package com.suryamj.bank.customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.suryamj.bank.repo.AccountRepository;
import com.suryamj.bank.repo.Login;

public class CustomerViewModel {
	CustomerView customerView;
	Login login;
	AccountRepository repo;
	Scanner scan = new Scanner(System.in);

	public CustomerViewModel(CustomerView customerView) {
		this.customerView = customerView;
		login = new Login();
		this.repo = new AccountRepository(this);

	}

	public boolean userLogin(String userId, String userPassWord) {

		return login.userLogin(userId, userPassWord);
	}

	public void toSearch(String userId, int i, String string) {
		if (string.equals("customerdetails")) {
			customerPersionalDeatails(repo.toSearch(userId, i, string));
		} else {
			customerAccountDetails(repo.toSearch(userId, i, string));

		}

		return;
	}

	public boolean customerPersionalDeatails(ResultSet rs) {
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
			return false;
		}
		return true;
	}

	public boolean customerAccountDetails(ResultSet rs) {
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
			return false;
		}
		return true;

	}

	public void userTransactionsDetails(String userId) {
		String str = "";
		if (userId.equals("admin")) {
			boolean transaction = true;
			while (transaction) {
				System.out.println("\nEnter Your Choice \n1.All User Details \n2.Single User Details \n3.Back ");
				String choice2 = scan.next();
				if (choice2.equals("1")) {
					str = "select * from transactionsdetails";
					transaction = false;
				} else if (choice2.equals("2")) {
					System.out.println("\nEnter Customer Id : ");
					String custId = scan.next();
					str = "select * from transactionsdetails where customerid = " + custId;
					transaction = false;
				} else if (choice2.equals("3")) {
					transaction = false;
					return;
				} else {
					System.out.println("\nEnter Valid Input...\n");
				}
			}

		} else {
			str = "select * from transactionsdetails where customerid = " + userId;
		}
		repo.userTransactionsDetails(str);
	}

	public void customerTransactionDetails(ResultSet rs) {
		int count = 0;
		System.out.println("\n***********************TRANSACTION INFO***********************\n");
		try {
			while (rs.next()) {
				count++;
				System.out.println("Customer Id          : " + rs.getString("customerid"));
				System.out.println("Date of Transaction  : " + rs.getString("dateoftransaction"));
				System.out.println("Transaction Type     : " + rs.getString("transactiontype"));
				System.out.println("Transaction Amount   : " + rs.getString("transactionamount"));
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (count == 0) {
			System.out.println("\nNo Transaction Found\n");
		}
		System.out.println("\n***********************TRANSACTION INFO***********************\n");
	}

	public void withDrawAmount(String userId) {
		boolean withdrawn = true;
		while (withdrawn) {
			System.out.println("\nEnter Amount to WithDraw : ");
			long amount = scan.nextInt();
			withdrawn = repo.withDrawAmount(userId, amount);
		}
	}

}
