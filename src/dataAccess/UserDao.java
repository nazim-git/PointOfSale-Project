package dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dataModels.UserModel;

public class UserDao {
	Connection con = DbConnection.getInstance();

	public UserModel login(UserModel loginUser) {
		PreparedStatement pstmt;

		UserModel user= null;
		try {
			pstmt = con.prepareStatement("SELECT * FROM Users where username=? and password=?");
			pstmt.setString(1, loginUser.getUsername());
			pstmt.setString(2, loginUser.getPassword());

			ResultSet rst = pstmt.executeQuery();
			while (rst.next()) {

				int ID = rst.getInt(1);
				String name = rst.getString(2);
				String username = rst.getString(3);
				String password = rst.getString(4);
				user = new UserModel(ID, name, username, password);
			}
			return user;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public boolean registerUser(UserModel user) {
		try {
			String query = "insert into Users (Name) values (?)";
			PreparedStatement pstmt = con.prepareStatement(query);

			pstmt.setString(1, user.getName());

			pstmt.execute();

			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

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

//	public boolean updateCustomer(int id, Customer customer) {
//		try {
//			
//			String query = "UPDATE Customer SET NME ='"+customer.getNME()+"',EMAIL ='"+customer.getEMAIL()+"',PHNE ="+customer.getPHNE()+" ,ACC_CARD ="+customer.getACC_CRD()+" ,ACCS_LVL ='"+customer.getACCS_LVL()+"' ,SBSC ='"+customer.getSBSC()+"' WHERE ID ="+id;
//			System.out.println(query);
//			PreparedStatement pstmt = con.prepareStatement(query);
//			pstmt.execute();
//
//			return true;
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			return false;
//		}
//	}
//

//	
//	
//	public ResultSet getLoyaltyPoints(int id) {
//		try {
//			String query = "select LYLTY_PNTS from Customer where ID = "+id;
//			PreparedStatement pstmt = con.prepareStatement(query);
//
//			ResultSet rs = pstmt.executeQuery();
////			while(rs.next()) {
////				System.out.println(rs.getString("LYLTY_PNTS"));
////			}
//			return rs;
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			return null;
//		}
//	}
//	
//	public boolean updateLoyaltyPoints(int id) {
//		try {
//			
//			String query = "UPDATE Customer SET LYLTY_PNTS = LYLTY_PNTS + 10 WHERE ID ="+id;
//			PreparedStatement pstmt = con.prepareStatement(query);
//			pstmt.execute();
//
//			return true;
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			return false;
//		}
//	}
//	
//	public boolean redeemLoyaltyPoints(int id) {
//		try {
//			
//			String query = "UPDATE Customer SET LYLTY_PNTS = LYLTY_PNTS - 100 WHERE ID ="+id;
//			System.out.println(query);
//			PreparedStatement pstmt = con.prepareStatement(query);
//			pstmt.execute();
//
//			return true;
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			return false;
//		}
//	}
