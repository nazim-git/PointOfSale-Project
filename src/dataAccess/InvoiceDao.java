package dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import dataModels.InvoiceItemModel;
import dataModels.InvoiceModel;

public class InvoiceDao {
	Connection con = DbConnection.getInstance();
	PreparedStatement pst;
	ResultSet rs;

	public void insertInvoice(InvoiceModel invoice) {
		try {
			String query = "insert into Invoices"
					+ "(invoiceNumber,customerId,customer,total,discountPercent,discountAmount,totalToPay,received,change,createdBy,createdAt) values"
					+ "( 	   ?,          ?,       ?,		?,           ?,             ?,          ?,        ?,      ?,      ?,        ?)";
			pst = con.prepareStatement(query);

			pst.setString(1, invoice.getInvoiceNumber());
			pst.setInt(2, invoice.getCustomerId());
			pst.setString(3, invoice.getCustomer());
			pst.setFloat(4, invoice.getTotal());
			pst.setFloat(5, invoice.getDiscountPercent());
			pst.setFloat(6, invoice.getDiscountAmount());
			pst.setFloat(7, invoice.getTotalToPay());
			pst.setFloat(8, invoice.getReceived());
			pst.setFloat(9, invoice.getChange());
			pst.setString(10, invoice.getCreatedBy());
			pst.setTimestamp(11, invoice.getCreatedAt());

			pst.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getInvoiceLastId(Timestamp createdAt) {
		System.out.println();
		int id = -1;
		try {
			String query = "select * from Invoices";
			pst = con.prepareStatement(query);

			rs = pst.executeQuery();

			while (rs.next()) {
				id = rs.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	public void insertItems(int invoiceId, ArrayList<InvoiceItemModel> invoiceItems) {
		try {
			String query = "insert into InvoiceItems"
					+ "(invoiceId,productId,title,quantity,unit,salePrice,purchasePrice,subTotal,createdBy,createdAt) values"
					+ "( 	   ?,          ?,       ?,		?,           ?,             ?,          ?,        ?,      ?,      ?)";
			for(int i=0;i<invoiceItems.size();i++) {
				pst = con.prepareStatement(query);

				pst.setInt(1, invoiceId);
				pst.setInt(2, invoiceItems.get(i).getProductId());
				pst.setString(3, invoiceItems.get(i).getTitle());
				pst.setInt(4, invoiceItems.get(i).getQuantity());
				pst.setString(5, invoiceItems.get(i).getUnit());
				pst.setFloat(6, invoiceItems.get(i).getSalePrice());
				pst.setFloat(7, invoiceItems.get(i).getPurchasePrice());
				pst.setFloat(8, invoiceItems.get(i).getSubTotal());
				pst.setString(9, invoiceItems.get(i).getCreatedBy());
				pst.setTimestamp(10, invoiceItems.get(i).getCreatedAt());

				pst.execute();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
