package com.suryamj.bank.repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;

public class DbConnection {
	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	public Connection getConnect() {
		if (con == null) {
			try {
//				Class.forName("com.mysql.cj.jdbc.Driver");
				DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
				return DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "1234");
//				return con;
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		} else {
			return con;
		}

		return null;
	}
}
