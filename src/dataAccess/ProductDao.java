package dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import dataModels.ProductModel;
import dataModels.User;
import viewModels.AddPurchaseVM;

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

				product.setTitle(rs.getString("title"));
				product.setDescription(rs.getString("description"));
				product.setUnit(rs.getString("unit"));
				product.setSalePrice(Float.parseFloat(rs.getString("salePrice")));
				product.setPurchasePrice(Float.parseFloat(rs.getString("purchasePrice")));
				product.setStock(Integer.parseInt(rs.getString("stock")));
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

				product.setTitle(rs.getString(1));
				product.setDescription(rs.getString(2));
				product.setUnit(rs.getString(3));
				product.setSalePrice(rs.getFloat(4));
				product.setPurchasePrice(rs.getFloat(5));
				product.setStock(rs.getInt(6));
				product.setCreatedBy(rs.getString(7));
				product.setCreatedAt(rs.getTimestamp(8));
				product.setStatus(rs.getBoolean(9));
				product.setDeleted(rs.getBoolean(10));
				product.setDeletedBy(rs.getString(11));
				product.setDeletedAt(rs.getTimestamp(12));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return product;
	}

	public void insertProduct(ProductModel product) {
		try {
			String query = "insert into Products"
					+ "(title,description,unit,createdBy,createdAt,status,salePrice) values" + "(?,?,?,?,?,?,?)";
			pst = con.prepareStatement(query);

			pst.setString(1, product.getTitle());
			pst.setString(2, product.getDescription());
			pst.setString(3, product.getUnit());
			pst.setString(4, product.getCreatedBy());
			pst.setTimestamp(5, product.getCreatedAt());
			pst.setBoolean(6, product.getStatus());
			pst.setFloat(7, product.getSalePrice());

			pst.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteCustomer(String title, Timestamp deletedAt) {
		try {
			String query = "UPDATE Products SET isDeleted = 1, deletedBy ='" + User.Username + "', deletedAt = '"
					+ deletedAt + "' WHERE title = '" + title + "'";
			pst = con.prepareStatement(query);

			pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateProduct(String title, ProductModel newDetails) {
		try {
			String query = "UPDATE Products SET title ='" + newDetails.getTitle() + "',description ='"
					+ newDetails.getDescription() + "',unit ='" + newDetails.getUnit() + "',status ='"
					+ newDetails.getStatus() + "',salePrice ='" + newDetails.getSalePrice() + "' WHERE title = '"
					+ title + "'";
			pst = con.prepareStatement(query);

			pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateStock(AddPurchaseVM purchaseForm) {
		try {
			String query = "UPDATE Products SET stock = stock + ?,purchasePrice = ?, salePrice = ? where title = ?";

			pst = con.prepareStatement(query);
			pst.setInt(1, Integer.parseInt(purchaseForm.txtQuantityPurchases.getText()));
			pst.setFloat(2, Float.parseFloat(purchaseForm.txtPurchasePricePurchases.getText()));
			pst.setFloat(3, Float.parseFloat(purchaseForm.txtSalePricePurchases.getText()));
			pst.setString(4, (String) purchaseForm.cmbProductsPurchases.getSelectedItem());

			pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateStock(String title, int quantity) {
		try {
			String query = "UPDATE Products SET stock = stock - ? where title = ?";

			pst = con.prepareStatement(query);
			pst.setInt(1, quantity);
			pst.setString(2, title);

			pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateStockRefund(int invoiceId) {
		try {
			String query = "UPDATE pr SET pr.stock = pr.stock + ii.quantity FROM Products pr JOIN InvoiceItems ii ON LOWER(pr.title) = LOWER(ii.title) WHERE ii.invoiceId = "
					+ invoiceId;

			pst = con.prepareStatement(query);

			pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
