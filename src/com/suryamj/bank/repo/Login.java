package com.suryamj.bank.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.suryamj.bank.admin.AdminViewModel;

public class Login extends DbConnection {
	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	AdminViewModel adminViewModel;

	public Login(AdminViewModel adminViewModel) {
		this.adminViewModel = adminViewModel;

	}

	public boolean adminlogin(String adminId, String adminPassWord) {

		try {
			con = getConnect();
			String str = "select adminid, adminpassword from adminlogin where adminid=98765";
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

}
