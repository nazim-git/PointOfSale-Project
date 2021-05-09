package dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import dataModels.CustomerModel;
import dataModels.InvoiceItemModel;
import dataModels.InvoiceModel;
import dataModels.User;

public class InvoiceDao {
	Connection con = DbConnection.getInstance();
	PreparedStatement pst;
	ResultSet rs;

	public void insertInvoice(InvoiceModel invoice) {
		try {
			String query = "insert into Invoices"
					+ "(invoiceNumber,customer,total,discountPercent,discountAmount,totalToPay,received,change,createdBy,createdAt,customerPhone,refInvoiceNumber) values"
					+ "( 	   ?,        ?,		?,           ?,             ?,          ?,        ?,      ?,      ?,        ?,?,?)";
			pst = con.prepareStatement(query);

			pst.setString(1, invoice.getInvoiceNumber());
			pst.setString(2, invoice.getCustomer());
			pst.setFloat(3, invoice.getTotal());
			pst.setFloat(4, invoice.getDiscountPercent());
			pst.setFloat(5, invoice.getDiscountAmount());
			pst.setFloat(6, invoice.getTotalToPay());
			pst.setFloat(7, invoice.getReceived());
			pst.setFloat(8, invoice.getChange());
			pst.setString(9, invoice.getCreatedBy());
			pst.setTimestamp(10, invoice.getCreatedAt());
			pst.setString(11, invoice.getCustomerPhone());
			pst.setString(12, invoice.getRefInvoiceNumber());

			pst.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getInvoiceLastId(Timestamp createdAt) {

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
	
	public String getInvoiceLastNumber(Timestamp createdAt) {

		String invoiceNumber = "";
		try {
			String query = "select * from Invoices where createdAt = '"+createdAt+"'";
			pst = con.prepareStatement(query);

			rs = pst.executeQuery();

			while (rs.next()) {
				invoiceNumber = rs.getString("invoiceNumber");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return invoiceNumber;
	}

	public void insertItems(int invoiceId, ArrayList<InvoiceItemModel> invoiceItems) {
		try {
			String query = "insert into InvoiceItems"
					+ "(invoiceId,title,quantity,unit,salePrice,purchasePrice,subTotal,createdBy,createdAt) values"
					+ "( 	   ?,       ?,		?,           ?,             ?,          ?,        ?,      ?,      ?)";
			for (int i = 0; i < invoiceItems.size(); i++) {
				pst = con.prepareStatement(query);

				pst.setInt(1, invoiceId);
				pst.setString(2, invoiceItems.get(i).getTitle());
				pst.setInt(3, invoiceItems.get(i).getQuantity());
				pst.setString(4, invoiceItems.get(i).getUnit());
				pst.setFloat(5, invoiceItems.get(i).getSalePrice());
				pst.setFloat(6, invoiceItems.get(i).getPurchasePrice());
				pst.setFloat(7, invoiceItems.get(i).getSubTotal());
				pst.setString(8, User.Username);
				pst.setTimestamp(9, new Timestamp(new Date().getTime()));

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
				sale.setCustomerPhone(rs.getString("customerPhone"));
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

	public ArrayList<InvoiceItemModel> getInvoiceItems(int id) {
		ArrayList<InvoiceItemModel> items = null;
		try {
			String query = "select * from InvoiceItems where invoiceId = " + id + " and isDeleted = 0";
			pst = con.prepareStatement(query);
			rs = pst.executeQuery();

			items = new ArrayList<InvoiceItemModel>();
			InvoiceItemModel item = new InvoiceItemModel();

			while (rs.next()) {

				item.setId(rs.getInt("id"));
				item.setInvoiceId(rs.getInt("invoiceId"));
				item.setTitle(rs.getString("title"));
				item.setQuantity(rs.getInt("quantity"));
				item.setUnit(rs.getString("unit"));
				item.setSalePrice(rs.getFloat("salePrice"));
				item.setPurchasePrice(rs.getFloat("purchasePrice"));
				item.setSubTotal(rs.getFloat("subTotal"));
				item.setCreatedBy(rs.getString("createdBy"));
				item.setCreatedAt(rs.getTimestamp("createdAt"));
				item.setDeleted(rs.getBoolean("isDeleted"));
				item.setDeletedBy(rs.getString("deletedBy"));
				item.setDeletedAt(rs.getTimestamp("deletedAt"));

				items.add(item);
				item = new InvoiceItemModel();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return items;
	}

	public InvoiceModel getInvoice(int id) {
		InvoiceModel sale = null;
		try {
			String query = "select * from Invoices where id = " + id + " and isDeleted = 0";
			pst = con.prepareStatement(query);
			rs = pst.executeQuery();

			sale = new InvoiceModel();

			while (rs.next()) {

				sale.setId(rs.getInt("id"));
				sale.setInvoiceNumber(rs.getString("invoiceNumber"));
				sale.setCustomerPhone(rs.getString("customerPhone"));
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

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sale;
	}

	public void deleteInvocie(int invoiceId) {
		try {
			String query = "UPDATE Invoices SET isDeleted = 1, deletedBy = '" + User.Username + "' ,deletedAt = '"
					+ new Timestamp(new Date().getTime()) + "' WHERE id = " + invoiceId;
			pst = con.prepareStatement(query);
			pst.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteInvocieItems(int invoiceId) {
		try {
			String query = "UPDATE InvoiceItems SET isDeleted = 1, deletedBy = '" + User.Username + "' ,deletedAt = '"+ new Timestamp(new Date().getTime()) + "' WHERE invoiceId = "
					+ invoiceId;
		
			pst = con.prepareStatement(query);
			pst.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
