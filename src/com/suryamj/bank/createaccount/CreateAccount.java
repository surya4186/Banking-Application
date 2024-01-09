package com.suryamj.bank.createaccount;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.suryamj.bank.bankview.BankView;
import com.suryamj.bank.dto.Customers;

public class CreateAccount {
	CreateAccountViewModel CreateAccountViewModel;
	Scanner scan;
	Customers customer;
	String accType;

	public CreateAccount(BankView bankView) {
		this.CreateAccountViewModel = new CreateAccountViewModel(this);
		customer = new Customers();
		scan = new Scanner(System.in);

	}

	public void showMenu() {

		boolean accountOption = true;

		while (accountOption) {
			System.out.println("\nEnter Account Type : \n1.Savings \n2.Current \n3.Exit ");
			byte accountType = 0;
			try {
				System.out.println("Enter your choice : ");
				accountType = scan.nextByte();
			} catch (InputMismatchException e) {
				System.out.println("Enter the 'Numeric' value.");
				scan.next();
			}
			System.out.println("Enter Name : ");
			String name = "surya";

			customer.setCustomerFullName(name);

			if (accountType == 1) {
				boolean check = true;
				while (check != false) {
					try {

						System.out.println("Enter Your Date of Birth ");
						System.out.println("Enter Year : ");
						int year = scan.nextInt();
						System.out.println("Enter Month : ");
						int month = scan.nextInt();
						System.out.println("Enter Date : ");
						int date = scan.nextInt();
						String dob = "" + year + "-" + month + "-" + date;

						if (year > 1950 && year < Calendar.getInstance().get(Calendar.YEAR) - 18) {
							DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
							df.setLenient(false);
							df.parse(dob);
							customer.setDateOfBirth(dob);
							check = false;
						} else {
							System.out.println("\nEnter valid Date...\n");
						}
					} catch (Exception e) {
						System.out.println("\nEnter valid Date...\n");
					}
					check = false;
				}
				check = true;
				while (check != false) {
					System.out.println("Enter Gender(M-Male, F-Female, T-Trangender : ");
					String gender = "" + scan.next().toUpperCase().charAt(0);
					if (gender.equals("M") || gender.equals("F") || gender.equals("T")) {
						customer.setGender(gender);
						check = false;
					} else {
						System.out.println("\nEnter Valid Details...");
					}
				}
				check = true;
				while (check != false) {
					System.out.println("Enter Email : ");
					String email = scan.next();
					if (CreateAccountViewModel.isValidEmail(email)) {
						customer.setEmail(email);
						check = false;
					} else {
						System.out.println("Enter Valid Email id...");
					}
				}
				check = true;
				while (check != false) {
					System.out.println("Enter PassWord : ");
					String passWord = scan.next();
					if (CreateAccountViewModel.isValidPassWord(passWord)) {
						customer.setPassWord(passWord);
						check = false;
					} else {
						System.out.println("Create PassWord(8 to 12) digits using numbers, "
								+ "characters(upper,lower), one symbol must(!@#$%&*()-+=^.),...");
					}
				}
				check = true;
				while (check != false) {
					System.out.println("Enter phone Number : ");
					String number = scan.next();
					if (CreateAccountViewModel.isValidPhoneNumber(number)) {
						customer.setPhoneNumber(number);
						check = false;
					} else {
						System.out.println("Enter Valid Phone Number...");
					}
				}
				System.out.print("1.saving Account /n 2.Current Account");
				System.out.println("Enter the options:");
				int options = scan.nextInt();

				check = true;
				int warning = 0;
				while (check) {

					if (options == 1) {
						System.out.println("Enter Mininmum Account Balance Amount \n"
								+ "It must 1000 or more Amount for Savings Account : ");
						accType = "saving";
					} else {
						System.out.println("Enter Mininmum Account Balance Amount \n"
								+ "(OR) It must 3000 or more Amount for Current Account :");
						accType = "Current";
					}
					long balance = scan.nextLong();
					if (balance < 1000 && options == 1) {
						if (warning != 1) {
							System.out.println("WARNING \nPlease enter Correct amount "
									+ "if you do this mistake again it will be terminated...");
							warning++;
						} else {
							return;
						}
					} else if (balance < 3000 && options == 2) {
						if (warning != 1) {
							System.out.println("WARNING \nPlease enter Correct amount "
									+ "if you do this mistake again it will be terminated...");
							warning++;
						} else {
							return;
						}
					} else {
						customer.setBalance(balance);
						check = false;
					}
				}
				check = true;
				if (check != false) {
					System.out.println("Enter City : ");
					String city = scan.nextLine().toLowerCase();
					customer.setCity(city);

				}
				customer.setAccounttype(accType);
				CreateAccountViewModel.insertData(customer);

//				accountProcess.insertData(bankcustomer);

				accountOption = false;
			} else if (accountType == 2) {
				accountOption = false;
			} else {
				System.out.println("\nEnter Valid Option...\n");
			}
		}

	}

}
