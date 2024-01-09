package com.suryamj.bank.admin;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.suryamj.bank.bankview.BankView;

public class AdminView {
	BankView bankView;
	Scanner scan = new Scanner(System.in);
	AdminViewModel adminViewModel;

	public AdminView(BankView bankView) {
		this.bankView = bankView;
		adminViewModel = new AdminViewModel(this);

	}

	public void showMenu() {
		System.out.println("Enter Admin Id : ");
		String adminId = scan.next();
		System.out.println("Enter Admin PassWord : ");
		String adminPassWord = scan.next();
		if (adminViewModel.adminlogin(adminId, adminPassWord)) {
			boolean admin = true;
			while (admin) {
				System.out.println("\nEnter Your choice :\n1.View All User Details \n2.Remove User Account"
						+ "\n3.Search User Details \n4.Change Admin Password \n5.LogOut");
				byte adminControl = 0;
				try {
					System.out.println("Enter your choice : ");
					adminControl = scan.nextByte();
				} catch (InputMismatchException e) {
					System.out.println("Enter the 'Numeric' value.\n");
					scan.next();
				}
				switch (adminControl) {
				case 1:
					adminViewModel.allUserDetails();
					break;
				case 2:
					boolean accountDelete = true;
					while (accountDelete) {
						System.out.println("Enter Account Number For Remove Account : ");
						try {
							int accountNumber = scan.nextInt();
							if (adminViewModel.toRemoveUserAccount(accountNumber)) {
								accountDelete = false;
							}
						} catch (InputMismatchException e) {
							System.out.println("Enter the 'Numeric' value.\n");
							scan.next();
						}
					}
					break;
//				case 3:
//					accountManagement.toSearchUserDetails("admin");
//					break;
//				case 4:
//					boolean changePassword = true;
//					while (changePassword) {
//						System.out.println("\nEnter New PassWord : ");
//						String newPassWord = scan.next();
//						System.out.println("\nConfirm New PassWord : ");
//						String confirmPassWord = scan.next();
//						if (account.isValidPassWord(confirmPassWord) && newPassWord.equals(confirmPassWord)) {
//							accountProcess.toChangeAdminPassWord(adminId, confirmPassWord);
//							changePassword = false;
//						} else {
//							System.out.println("Enter Valid PassWord...");
//						}
//					}
//					break;
				case 5:
					admin = false;
					break;
				default:
					System.out.println("Enter valid choice...\n");
				}
			}
		}

	}

}
