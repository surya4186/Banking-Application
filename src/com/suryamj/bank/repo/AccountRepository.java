package com.suryamj.bank.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.suryamj.bank.admin.AdminViewModel;
import com.suryamj.bank.createaccount.CreateAccountViewModel;
import com.suryamj.bank.customer.CustomerViewModel;
import com.suryamj.bank.dto.Customers;

public class AccountRepository extends DbConnection {
	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	AdminViewModel adminViewModel;
	CreateAccountViewModel createAccountViewModel;
	CustomerViewModel customerViewModel;

//	AccountManagement accountManagement;
	public AccountRepository(AdminViewModel adminViewModel2) {
		this.adminViewModel = adminViewModel2;

	}

	public AccountRepository(CreateAccountViewModel createAccountViewModel) {
		this.createAccountViewModel = createAccountViewModel;

	}

	public AccountRepository(CustomerViewModel customerViewModel) {
		this.customerViewModel = customerViewModel;
	}

	public void insertData(Customers bankcustomer) {
		try {
			con = getConnect();
			String str = "insert into customerdetails(name, gender, dateofbirth, email, phonenumber, accounttype, city, password) "
					+ "Values(" + "'" + bankcustomer.getCustomerFullName() + "', " + "'" + bankcustomer.getGender()
					+ "', " + "'" + bankcustomer.getDateOfBirth() + "', " + "'" + bankcustomer.getEmail() + "', " + "'"
					+ bankcustomer.getPhoneNumber() + "', " + "'" + bankcustomer.getAccounttype() + "', " + "'"
					+ bankcustomer.getCity() + "', " + "'" + bankcustomer.getPassWord() + "'" + ")";
			pst = con.prepareStatement(str);

			int x = pst.executeUpdate();
			if (x == 1) {
				str = "select customerid from customerdetails where phonenumber=" + bankcustomer.getPhoneNumber();
				pst = con.prepareStatement(str);
				rs = pst.executeQuery();
				rs.next();
				bankcustomer.setCustomerid(Integer.parseInt(rs.getString("customerid")));
				bankcustomer.setDateOfOpen("" + java.time.LocalDate.now());
				bankcustomer.setAccountStatus("Active");
				str = "insert into accountdetails(customerid, dateofaccountopen, accountbalance, accountstatus) "
						+ "Values(" + "'" + bankcustomer.getCustomerid() + "', " + "'" + bankcustomer.getDateOfOpen()
						+ "', " + "'" + bankcustomer.getBalance() + "', " + "'" + bankcustomer.getAccountStatus() + "'"
						+ ")";
				pst = con.prepareStatement(str);
				x = pst.executeUpdate();
				if (x == 1) {
					str = "select accountnumber from accountdetails where customerid=" + bankcustomer.getCustomerid();
					pst = con.prepareStatement(str);
					rs = pst.executeQuery();
					rs.next();
					System.out.println("\nYour Account is Successfully Opened\n" + "Your Account Number is : "
							+ rs.getString("accountnumber") + "\n" + "Your Customer ID is : "
							+ bankcustomer.getCustomerid());
				}
			}

		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public void allUserDetails(byte accountDetailChoice) {
		try {

			con = getConnect();
			String str;
			switch (accountDetailChoice) {
			case 1:
				str = "SELECT COUNT(*) FROM customerdetails";
				pst = con.prepareStatement(str);
				rs = pst.executeQuery();
				str = "select * from customerdetails";
				pst = con.prepareStatement(str);
				rs = pst.executeQuery();
				adminViewModel.customerPersionalDeatails(rs);
				break;
			case 2:
				str = "SELECT COUNT(*) FROM accountdetails";
				pst = con.prepareStatement(str);
				rs = pst.executeQuery();
				str = "select * from accountdetails";
				pst = con.prepareStatement(str);
				rs = pst.executeQuery();
				adminViewModel.customerAccountDetails(rs);
				break;
			default:
				System.out.println("\nEnter Valid Input...\n");
			}

		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public boolean adminlogin(String adminId, String adminPassWord) {

		try {
			con = getConnect();
			String str = "select adminid, adminpassword from adminlogin where adminid=24681012";
			pst = con.prepareStatement(str);
			rs = pst.executeQuery();
			rs.next();
			String correctAdminId = rs.getString("adminid");
			String correctAdminPassWord = rs.getNString("adminpassword");
			if (correctAdminId.equals(adminId)) {
				if (correctAdminPassWord.equals(adminPassWord)) {
					return true;
				} else {
					System.out.println("Please Enter Valid Admin Password...");
					return false;
				}
			} else {
				System.out.println("Please Enter Valid AdminId...");
				return false;
			}

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return false;
	}

	public boolean toRemoveUserAccount(int delete, int userId) {
		try {
			con = getConnect();
			String str;
			if (delete == 1) {

				str = "delete from transactionsdetails where customerid = " + userId;
				pst = con.prepareStatement(str);
				int x = pst.executeUpdate();
				if (x == 0) {
					x = 1;
				}
				if (x == 1) {
					str = "delete from accountdetails where customerid = " + userId;
					pst = con.prepareStatement(str);
					x = pst.executeUpdate();
					if (x == 1) {
						str = "delete from customerdetails where customerid = " + userId;
						pst = con.prepareStatement(str);
						x = pst.executeUpdate();
						if (x == 1) {
							System.out.println("\nDeletion Completed\n");
							return true;
						}
					}
				}
			} else {
				System.out.println("\nDeletion Cancelled\n");
				return true;
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return false;
	}

	public ResultSet toSearch(String info, int val, String table) {
		if (val == 1) {
			info = " name = " + "'" + info + "'";
		} else {
			info = " customerid = " + info;
		}
		try {
//			accountManagement = new AccountManagement();
			con = getConnect();
			String str = "select * from " + table + " where " + info;
			pst = con.prepareStatement(str);
			rs = pst.executeQuery();
			if (table.equals("customerdetails")) {
//				adminViewModel.customerPersionalDeatails(rs);
				return rs;
			} else {
//				adminViewModel.customerAccountDetails(rs);
				return rs;
			}
		} catch (Exception e) {
			System.out.println();
			return null;
		}

	}

	public boolean toChangeAdminPassWord(String aId, String pW) {
		try {
			con = getConnect();
			String str = "UPDATE adminlogin SET adminpassword = " + "'" + pW + "'" + " WHERE adminid = " + aId;
			pst = con.prepareStatement(str);
			int x = pst.executeUpdate();
			if (x == 1) {
				System.out.println("\nUpdate Completed\n");
				return true;
			} else {
				System.out.println("\nNot Updated...\n");
				return false;
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return true;
	}

	public void userTransactionsDetails(String userId) {
		try {
			System.out.println(userId + "get");
			pst = con.prepareStatement(userId);
			rs = pst.executeQuery();
			System.out.println();
			customerViewModel.customerTransactionDetails(rs);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public boolean toUpdateTransactionTable(String customerId, String transactionType, long transactionAmount) {
		try {
			con = getConnect();
			String dateOfTransaction = "" + java.time.LocalDate.now();
			String str = "insert into transactionsdetails(customerid, dateoftransaction, transactiontype, transactionamount) "
					+ "Values(" + "'" + customerId + "', " + "'" + dateOfTransaction + "', " + "'" + transactionType
					+ "', " + "'" + transactionAmount + "'" + ")";
			pst = con.prepareStatement(str);
			int x = pst.executeUpdate();
			if (x == 1) {
				return true;
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return true;
	}

	public boolean withDrawAmount(String userId, long amount) {
		try {
			con = getConnect();
			String str = "select accountbalance from accountdetails where customerid=" + userId;
			pst = con.prepareStatement(str);
			rs = pst.executeQuery();
			rs.next();
			String accountBalance = rs.getString("accountbalance");

			if (100000 > Integer.parseInt(accountBalance)) {
				if (amount < Integer.parseInt(accountBalance)) {
					System.out.println("Your WithDrawn Amount is : " + amount);
					long amount1 = Long.parseLong(accountBalance) - amount;
					str = "update accountdetails set accountbalance = " + amount1 + " where customerid=" + userId;
					pst = con.prepareStatement(str);
					int x = pst.executeUpdate();
					if (x == 1) {
						if (toUpdateTransactionTable(userId, "WithDrawn", amount)) {
							System.out.println("Your Balance Amount is : " + amount1);
							return false;
						} else {
							System.out.println("\ntransaction Details Error...\n");
							return true;
						}
					}
				} else {
					System.out.println("\nYour WithDrawn Amount is must be less than Available Balance...\n");
					return true;
				}
			} else {
				return true;
			}

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return true;
	}

}
