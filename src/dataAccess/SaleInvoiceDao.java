package dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SaleInvoiceDao {
	Connection con = DbConnection.getInstance();
	PreparedStatement pst;
	ResultSet rst;
	
	public ResultSet getProducts() {
		try {
			String query = "select * from Products";
			pst = con.prepareStatement(query);
			rst = pst.executeQuery();
			return rst;
		} catch (SQLException e) {
			e.printStackTrace();
			return rst;
		}
	}
	
	public ResultSet ProductByIdOrName(String id) {
		try {
			String query = "select * from Products where ID='"+id+"'";
			pst = con.prepareStatement(query);
			rst = pst.executeQuery();
			return rst;
		} catch (SQLException e) {
			e.printStackTrace();
			return rst;
		}
	}
}
