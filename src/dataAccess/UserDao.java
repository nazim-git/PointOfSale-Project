package dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dataModels.UserModel;
import viewModels.Credentials;


public class UserDao {
	Connection con = DbConnection.getInstance();

	public void login(Credentials credentials) {
		PreparedStatement pstmt;
		
		try {
			pstmt = con.prepareStatement("SELECT * FROM Users where username=? and password=?");
			pstmt.setString(1, credentials.getUsername());
			pstmt.setString(2, credentials.getPassword());

			ResultSet rst = pstmt.executeQuery();
			while (rst.next()) {

				UserModel.ID = rst.getInt(1);
				UserModel.Name = rst.getString(2);
				UserModel.Username = rst.getString(3);
				UserModel.Password = rst.getString(4);

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
//	public boolean registerUser(UserModel user) {
//		try {
//			String query = "insert into Users (Name) values (?)";
//			PreparedStatement pstmt = con.prepareStatement(query);
//
//			pstmt.setString(1, user.getName());
//
//			pstmt.execute();
//
//			return true;
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			return false;
//		}
//	}

	public ResultSet getUserByID(int ID) {
		try {
			String query = "select * from Customer where ID = " + ID;
			PreparedStatement pstmt = con.prepareStatement(query);

			ResultSet rs = pstmt.executeQuery();
			return rs;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public ResultSet getUsersByName(String name) {
		try {
			String query = "select * from Customer where NME like '%" + name + "%'";
			PreparedStatement pstmt = con.prepareStatement(query);

			ResultSet rs = pstmt.executeQuery();
			return rs;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
}

