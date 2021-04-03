package dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import dataModels.CustomerModel;
import dataModels.ProductModel;
import dataModels.UserModel;

public class ProductDao {
	Connection con = DbConnection.getInstance();
	PreparedStatement pst;
	ResultSet rs;

	public ArrayList<ProductModel> getProducts() {
		ArrayList<ProductModel> products = null;
		try {
			String query = "select * from Products where isDeleted = 0";
			pst = con.prepareStatement(query);
			rs = pst.executeQuery();

			products = new ArrayList<ProductModel>();
			ProductModel product = new ProductModel();

			while (rs.next()) {

				product.setId(Integer.parseInt(rs.getString("id")));
				product.setTitle(rs.getString("title"));
				product.setDescription(rs.getString("description"));
				product.setCategory(rs.getString("category"));
				product.setUnit(rs.getString("unit"));
				product.setSalePrice(Float.parseFloat(rs.getString("salePrice")));
				product.setPurchasePrice(Float.parseFloat(rs.getString("purchasePrice")));
				product.setCreatedBy(rs.getString("createdBy"));
				product.setCreatedDate(Timestamp.valueOf(rs.getString("createdAt")));
				product.setStatus(rs.getBoolean("status"));
				product.setStock(rs.getInt("stock"));

				products.add(product);
				product = new ProductModel();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return products;
	}

	public ResultSet ProductByIdOrName(String id) {
		try {
			String query = "select * from Products where ID='" + id + "' and isDeleted = 0";
			pst = con.prepareStatement(query);
			rs = pst.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			return rs;
		}
	}

	public ProductModel getProduct(String title) {
		ProductModel product = null;
		try {
			String query = "select * from Products where title = ? and isDeleted = 0";
			pst = con.prepareStatement(query);

			pst.setString(1, title);

			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				product = new ProductModel();

				product.setId(rs.getInt(1));
				product.setTitle(rs.getString(2));
				product.setDescription(rs.getString(3));
				product.setCategory(rs.getString(4));
				product.setUnit(rs.getString(5));
				product.setSalePrice(rs.getFloat(6));
				product.setPurchasePrice(rs.getFloat(7));
				product.setStock(rs.getInt(8));
				product.setCreatedBy(rs.getString(9));
				product.setCreatedAt(rs.getTimestamp(10));
				product.setStatus(rs.getBoolean(11));
				product.setDeleted(rs.getBoolean(12));
				product.setDeletedBy(rs.getString(13));
				product.setDeletedAt(rs.getTimestamp(14));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return product;
	}

	public void insertProduct(ProductModel product) {
		try {
			String query = "insert into Products"
					+ "(title,description,category,unit,salePrice,purchasePrice,stock,createdBy,createdAt,status) values"
					+ "(?,?,?,?,?,?,?,?,?,?)";
			pst = con.prepareStatement(query);

			pst.setString(1, product.getTitle());
			pst.setString(2, product.getDescription());
			pst.setString(3, product.getCategory());
			pst.setString(4, product.getUnit());
			pst.setFloat(5, product.getSalePrice());
			pst.setFloat(6, product.getPurchasePrice());
			pst.setInt(7, product.getStock());
			pst.setString(8, product.getCreatedBy());
			pst.setTimestamp(9, product.getCreatedAt());
			pst.setBoolean(10, product.getStatus());

			pst.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteCustomer(int id, Timestamp deletedAt) {
		try {
			String query = "UPDATE Products SET isDeleted = 1, deletedBy ='" + UserModel.Name + "', deletedAt = '"
					+ deletedAt + "' WHERE id = " + id;
			pst = con.prepareStatement(query);

			pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateProduct(int id, ProductModel newDetails) {
		try {
			String query = "UPDATE Products SET title ='" + newDetails.getTitle() + 
											 "',description ='" + newDetails.getDescription()+ 
											 "',category ='" + newDetails.getCategory() + 
											 "',unit ='" + newDetails.getUnit() + 
											 "',salePrice ='"+ newDetails.getSalePrice() + 
											 "',purchasePrice ='"+ newDetails.getPurchasePrice() +
											 "',stock ='"+ newDetails.getStock() + 
											 "',status ='"+ newDetails.getStatus() + 
											 "' WHERE id = " + id;
			pst = con.prepareStatement(query);

			pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
