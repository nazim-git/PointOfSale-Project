package dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import dataModels.User;
import dataModels.UserModel;
import viewModels.Credentials;

public class UserDao {
	Connection con = DbConnection.getInstance();
	PreparedStatement pst;

	public void login(Credentials credentials) {

		try {
			pst = con.prepareStatement("SELECT * FROM Users where username=? and password=?");
			pst.setString(1, credentials.getUsername());
			pst.setString(2, credentials.getPassword());

			ResultSet rst = pst.executeQuery();
			while (rst.next()) {

				User.ID = rst.getInt(1);
				User.Name = rst.getString(2);
				User.Username = rst.getString(3);
				User.Password = rst.getString(4);
				User.IsAdmin = rst.getBoolean(5);

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public ResultSet getUserByID(int ID) {
		try {
			String query = "select * from Customer where ID = " + ID;
			PreparedStatement pst = con.prepareStatement(query);

			ResultSet rs = pst.executeQuery();
			return rs;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public ResultSet getUsersByName(String name) {
		try {
			String query = "select * from Customer where NME like '%" + name + "%'";
			PreparedStatement pst = con.prepareStatement(query);

			ResultSet rs = pst.executeQuery();
			return rs;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public ArrayList<UserModel> getUsers() {
		ArrayList<UserModel> users = new ArrayList<UserModel>();

		try {
			String query = "select * from Users where isDeleted = 0";
			pst = con.prepareStatement(query);
			UserModel user = new UserModel();
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {

				user.setID(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setUsername(rs.getString(3));
				user.setIsAdmin(rs.getBoolean(5));

				users.add(user);
				user = new UserModel();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	public UserModel getUser(String username) {
		try {
			String query = "select * from Users where username = '" + username + "'";
			PreparedStatement pst = con.prepareStatement(query);

			ResultSet rs = pst.executeQuery();
			UserModel user = null;
			while (rs.next()) {
				user = new UserModel();
				user.setID(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setUsername(rs.getString(3));
				user.setIsAdmin(rs.getBoolean(5));
			}

			return user;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public void insertUser(UserModel user) {
		try {
			String query = "insert into Users" + "(name,username,password,isAdmin) values" + "(?,?,?,?)";
			pst = con.prepareStatement(query);

			pst.setString(1, user.getName());
			pst.setString(2, user.getUsername());
			pst.setString(3, user.getPassword());
			pst.setBoolean(4, user.isIsAdmin());

			pst.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	public void deleteUser(int id, Timestamp deletedAt) {
		try {
			String query = "UPDATE Users SET isDeleted = 1, deletedBy ='" + User.Name + "', deletedAt = '"
					+ deletedAt + "' WHERE id = " + id;
			pst = con.prepareStatement(query);

			pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateUser(int id, UserModel newDetails) {
		try {
			String query = "UPDATE Users SET name ='" + newDetails.getName() + "',username ='" + newDetails.getUsername()
			+ "',password ='" + newDetails.getPassword()+ "',isAdmin ='" + newDetails.isIsAdmin()
					+ "', isSynced = 0 WHERE id = " + id;
			pst = con.prepareStatement(query);

			pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
