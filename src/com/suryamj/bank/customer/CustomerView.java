package com.suryamj.bank.customer;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.suryamj.bank.bankview.BankView;
import com.suryamj.bank.repo.Login;

public class CustomerView {
	Scanner scan;
	Login login;
	CustomerViewModel customerViewModel;

	public CustomerView(BankView bankView) {
		scan = new Scanner(System.in);
		login = new Login(this);
		customerViewModel = new CustomerViewModel(this);

	}

	public void showMenu() {
		System.out.println("Enter User Id : ");
		String userId = scan.next();
		System.out.println("Enter User PassWord : ");
		String userPassWord = scan.next();
		if (customerViewModel.userLogin(userId, userPassWord)) {
			boolean user = true;
			while (user) {
				System.out.println("\nEnter Your choice :\n1.View Your Persional Details \n2.View Account Details"
						+ "\n3.View Transaction Details \n4.WithDraw Amount \n5.Deposit Amount \n6.LogOut");
				byte userChoice = 0;
				try {
					System.out.println("Enter your choice : ");
					userChoice = scan.nextByte();
				} catch (InputMismatchException e) {
					System.out.println("Enter the 'Numeric' value.");
					scan.next();
				}
				switch (userChoice) {
				case 1:
					customerViewModel.toSearch(userId, 0, "customerdetails");
					break;
				case 2:
					customerViewModel.toSearch(userId, 0, "accountDetails");
					break;
				case 3:
					customerViewModel.userTransactionsDetails(userId);
					break;
				case 4:
					customerViewModel.withDrawAmount(userId);
					break;
//				case 5:
//					customerViewModel.depositeAmount(userId);
//					break;
				case 6:
					user = false;
					break;
				default:
					System.out.println("Enter valid choice...\n");
				}
			}
		}

	}

}
