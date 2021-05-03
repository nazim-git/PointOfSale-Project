package dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import dataModels.PurchasesModel;

public class PurchasesDao {

	Connection con = DbConnection.getInstance();
	PreparedStatement pst;
	ResultSet rs;

	public ArrayList<PurchasesModel> getPurchases() {
		ArrayList<PurchasesModel> purchases = null;
		try {
			String query = "select * from Purchases where isDeleted = 0";
			pst = con.prepareStatement(query);
			rs = pst.executeQuery();

			purchases = new ArrayList<PurchasesModel>();
			PurchasesModel purchase = new PurchasesModel();

			while (rs.next()) {

				purchase.setId(Integer.parseInt(rs.getString("id")));
				purchase.setTitle(rs.getString("title"));
				purchase.setPurchasePrice(Float.parseFloat(rs.getString("purchasePrice")));
				purchase.setSalePrice(Float.parseFloat(rs.getString("salePrice")));
				purchase.setQuantity(Integer.parseInt(rs.getString("quantity")));
				purchase.setUnit(rs.getString("unit"));
				purchase.setSupplier(rs.getString("supplier"));
				purchase.setCreatedBy(rs.getString("createdBy"));
				purchase.setCreatedAt(Timestamp.valueOf(rs.getString("createdAt")));

				purchases.add(purchase);
				purchase = new PurchasesModel();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return purchases;
	}

	public void insertPurchase(PurchasesModel purchase) {
		try {
			String query = "insert into Purchases"
					+ "(title,supplier,purchasePrice,salePrice,unit,quantity,createdBy,createdAt) values"
					+ "(?,?,?,?,?,?,?,?)";
			pst = con.prepareStatement(query);

			pst.setString(1, purchase.getTitle());
			pst.setString(2, purchase.getSupplier());
			pst.setFloat(3, purchase.getPurchasePrice());
			pst.setFloat(4, purchase.getSalePrice());
			pst.setString(5, purchase.getUnit());
			pst.setInt(6, purchase.getQuantity());
			pst.setString(7, purchase.getCreatedBy());
			pst.setTimestamp(8, purchase.getCreatedAt());

			pst.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
