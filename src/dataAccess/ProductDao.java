package dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import dataModels.ProductModel;

public class ProductDao {
	Connection con = DbConnection.getInstance();
	PreparedStatement pst;
	ResultSet rs;
	
	public ArrayList<ProductModel> getProducts() {
		try {
			String query = "select * from Products";
			pst = con.prepareStatement(query);
			rs = pst.executeQuery();
			

			ArrayList<ProductModel> products = new ArrayList<ProductModel>();
			ProductModel product = new ProductModel();

			while (rs.next()) {

				product.setId(Integer.parseInt(rs.getString("id")));
				product.setTitle(rs.getString("title"));
				product.setDescription(rs.getString("description"));
				product.setCategory(rs.getString("category"));
				product.setUnit(rs.getString("unit"));
				product.setSalePrice(Float.parseFloat(rs.getString("salePrice")));
				product.setPurchasePrice(Float.parseFloat(rs.getString("purchasePrice")));
				product.setCreatedBy(Integer.parseInt(rs.getString("createdBy")));
				product.setCreatedDate(Timestamp.valueOf(rs.getString("createdDate")));
				product.setStatus(rs.getBoolean("status"));

				products.add(product);
				product = new ProductModel();
			}
			return products;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public ResultSet ProductByIdOrName(String id) {
		try {
			String query = "select * from Products where ID='"+id+"'";
			pst = con.prepareStatement(query);
			rs = pst.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			return rs;
		}
	}
}
