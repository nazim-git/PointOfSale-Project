package dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import dataModels.CustomerModel;
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

	public ArrayList<InvoiceModel> getInvoices() {
		ArrayList<InvoiceModel> sales = null;
		try {
			String query = "select * from Invoices where isDeleted = 0";
			pst = con.prepareStatement(query);
			rs = pst.executeQuery();

			sales = new ArrayList<InvoiceModel>();
			InvoiceModel sale = new InvoiceModel();

			while (rs.next()) {

				sale.setId(rs.getInt("id"));
				sale.setInvoiceNumber(rs.getString("invoiceNumber"));
				sale.setCustomerId(rs.getInt("customerId"));
				sale.setCustomer(rs.getString("customer"));
				sale.setTotal(rs.getFloat("total"));
				sale.setDiscountPercent(rs.getFloat("discountPercent"));
				sale.setDiscountAmount(rs.getFloat("discountAmount"));
				sale.setTotalToPay(rs.getFloat("totalToPay"));
				sale.setReceived(rs.getFloat("received"));
				sale.setChange(rs.getFloat("change"));
				sale.setCreatedBy(rs.getString("createdBy"));
				sale.setCreatedAt(rs.getTimestamp("createdAt"));
				sale.setDeleted(rs.getBoolean("isDeleted"));
				sale.setDeletedBy(rs.getString("deletedBy"));
				sale.setDeletedAt(rs.getTimestamp("deletedAt"));

				sales.add(sale);
				sale = new InvoiceModel();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sales;
	}
}
