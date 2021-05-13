package dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import dataModels.CustomerModel;
import dataModels.ExpenseModel;
import dataModels.InvoiceItemModel;
import dataModels.InvoiceModel;
import dataModels.ProductModel;
import dataModels.PurchasesModel;

public class SyncDao {
	
	Connection con = DbConnection.getInstance();
	PreparedStatement pst;
	ResultSet rs;
	
	public ArrayList<InvoiceModel> getInvoices() {
		ArrayList<InvoiceModel> sales = null;
		try {
			String query = "select * from Invoices where isDeleted = 0 and isSynced = 0";
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
	
	public ArrayList<InvoiceItemModel> getInvoiceItems() {
		ArrayList<InvoiceItemModel> items = null;
		try {
			String query = "select * from InvoiceItems where isDeleted = 0 and isSynced = 0";
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

	public ArrayList<PurchasesModel> getPurchases() {
		ArrayList<PurchasesModel> purchases = null;
		try {
			String query = "select * from Purchases where isDeleted = 0 and isSynced = 0";
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
	
	public ArrayList<ExpenseModel> getExpenses() {
		ArrayList<ExpenseModel> expenses = null;
		try {
			String query = "select * from Expenses where isDeleted = 0 and isSynced = 0";
			pst = con.prepareStatement(query);
			rs = pst.executeQuery();

			expenses = new ArrayList<ExpenseModel>();
			ExpenseModel expense = new ExpenseModel();

			while (rs.next()) {

				expense.setId(Integer.parseInt(rs.getString("id")));
				expense.setAmount(rs.getFloat("amount"));
				expense.setDescription(rs.getString("description"));
				expense.setCreatedBy(rs.getString("createdBy"));
				expense.setCreatedAt(rs.getTimestamp("createdAt"));
				expense.setDeleted(Boolean.parseBoolean(rs.getString("isDeleted")));
				expense.setDeletedBy(rs.getString("deletedBy"));
				expense.setDeletedAt(rs.getTimestamp("deletedAt"));

				expenses.add(expense);
				expense = new ExpenseModel();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return expenses;
	}

	public ArrayList<CustomerModel> getCustomers() {
		ArrayList<CustomerModel> customers = null;
		try {
			String query = "select * from Customers where isDeleted = 0  and isSynced = 0";
			pst = con.prepareStatement(query);
			rs = pst.executeQuery();

			customers = new ArrayList<CustomerModel>();
			CustomerModel customer = new CustomerModel();

			while (rs.next()) {

				customer.setName(rs.getString("name"));
				customer.setPhone(rs.getString("phone"));
				customer.setCreatedBy(rs.getString("createdBy"));
				customer.setCreatedAt(rs.getTimestamp("createdAt"));
				customer.setDeleted(Boolean.parseBoolean(rs.getString("isDeleted")));
				customer.setDeletedBy(rs.getString("deletedBy"));
				customer.setDeletedAt(rs.getTimestamp("deletedAt"));
				customer.setOldPhone(rs.getString("oldPhone"));

				customers.add(customer);
				customer = new CustomerModel();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customers;
	}

	public ArrayList<ProductModel> getProducts() {
		ArrayList<ProductModel> products = null;
		try {
			String query = "select * from Products where isDeleted = 0 and isSynced = 0";
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
}
