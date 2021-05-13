package controllers;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dataAccess.DbConnection;
import dataAccess.RemoteDbConnection;
import dataAccess.SyncDao;
import dataModels.CustomerModel;
import dataModels.ExpenseModel;
import dataModels.InvoiceItemModel;
import dataModels.InvoiceModel;
import dataModels.ProductModel;
import dataModels.PurchasesModel;

public class SyncController {

	static SyncDao dao = new SyncDao();
	static Connection remoteCon = RemoteDbConnection.getInstance();
	static Connection localCon = DbConnection.getInstance();
	static Statement stmt;
	static StringBuilder sb;
	static StringBuilder delSb;
	static StringBuilder updateSb;
	static int itemCounter = 0;
	static boolean isJobExecutionPending = false;
	private static int batchSize = 20;

	public static void Sync() {
		PreSync();
		JobExecutionInvoices();

		PreSync();
		JobExecutionInvoiceItems();

		PreSync();
		JobExecutionCustomers();

		PreSync();
		JobExecutionExpense();

		PreSync();
		JobExecutionProducts();

		PreSync();
		JobExecutionPurchases();
	}

	private static void JobExecutionPurchases() {
		try {
			ArrayList<PurchasesModel> purchases = dao.getPurchases();
			delSb.append("delete from Purchases where id in (");
			updateSb.append("update Purchases set isSynced = 1 where id in (");
			for (PurchasesModel items : purchases) {
				sb = new StringBuilder();
				delSb.append(items.getId() + ",");
				updateSb.append(items.getId() + ",");

				sb.append(
						"  INSERT INTO Purchases(id,title ,purchasePrice ,salePrice ,quantity ,unit ,supplier ,createdBy ,createdAt) VALUES ");
				sb.append("(" + items.getId() + ",'" + items.getTitle() + "'," + items.getPurchasePrice() + ","
						+ items.getSalePrice() + "," + items.getQuantity() + ",'" + items.getUnit() + "','"
						+ items.getSupplier() + "','" + items.getCreatedBy() + "','"
						+ items.getCreatedAt() + "')");

				itemCounter++;
				String query = sb.toString();
				writefile(query);
				stmt.addBatch(query);
				isJobExecutionPending = true;

				if (itemCounter >= batchSize) {
					itemCounter = 0;
					CommitJob();
					isJobExecutionPending = false;
				}
			}

			if (isJobExecutionPending) {
				CommitJob();
				isJobExecutionPending = false;
			}
		} catch (

		SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void JobExecutionProducts() {
		try {
			ArrayList<ProductModel> products = dao.getProducts();
			delSb.append("delete from Products where title in (");
			updateSb.append("update Products set isSynced = 1 where title in (");
			for (ProductModel items : products) {
				sb = new StringBuilder();
				delSb.append( "'" + items.getTitle() + "',");
				updateSb.append( "'" + items.getTitle() + "',");

				sb.append(
						" INSERT INTO Products(title ,description ,unit ,salePrice ,purchasePrice ,stock ,createdBy ,createdAt ,status) VALUES ");
				sb.append("('" + items.getTitle() + "','" + items.getDescription() + "','" + items.getUnit() + "',"
						+ items.getSalePrice() + "," + items.getPurchasePrice() + "," + items.getStock() + ",'"
						+ items.getCreatedBy() + "','" + items.getCreatedAt() + "','" + items.getStatus() + "')");

				itemCounter++;
				String query = sb.toString();
				writefile(query);
				stmt.addBatch(query);
				isJobExecutionPending = true;

				if (itemCounter >= batchSize) {
					itemCounter = 0;
					CommitJob();
					isJobExecutionPending = false;
				}
			}

			if (isJobExecutionPending) {
				CommitJob();
				isJobExecutionPending = false;
			}
		} catch (

		SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void JobExecutionExpense() {
		try {
			ArrayList<ExpenseModel> expenses = dao.getExpenses();
			delSb.append("delete from Expenses where id in (");
			updateSb.append("update Expenses set isSynced = 1 where id in (");
			for (ExpenseModel items : expenses) {
				sb = new StringBuilder();
				delSb.append(items.getId() + ",");
				updateSb.append(items.getId() + ",");

				sb.append(
						" INSERT INTO Expenses(id, amount ,description ,createdBy ,createdAt ) VALUES ");
				sb.append("(" + items.getId() + "," + items.getAmount() + ",'" + items.getDescription() + "','"
						+ items.getCreatedBy() + "','" + items.getCreatedAt() + "')");

				itemCounter++;
				String query = sb.toString();
				writefile(query);
				stmt.addBatch(query);
				isJobExecutionPending = true;

				if (itemCounter >= batchSize) {
					itemCounter = 0;
					CommitJob();
					isJobExecutionPending = false;
				}
			}

			if (isJobExecutionPending) {
				CommitJob();
				isJobExecutionPending = false;
			}
		} catch (

		SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void JobExecutionCustomers() {
		// TODO Auto-generated method stub
		try {
			ArrayList<CustomerModel> customers = dao.getCustomers();
			delSb.append("delete from Customers where phone in (");
			updateSb.append("update Customers set isSynced = 1 where phone in (");
			for (CustomerModel items : customers) {
				sb = new StringBuilder();
				delSb.append("'" + items.getOldPhone() + "',");
				updateSb.append("'" + items.getPhone() + "',");

				sb.append("INSERT INTO Customers(phone ,name ,createdBy ,createdAt) VALUES ");
				sb.append("('" + items.getPhone() + "','" + items.getName() + "','" + items.getCreatedBy() + "','"
						+ items.getCreatedAt() + "')");

				itemCounter++;

				String query = sb.toString();
				writefile(query);
				stmt.addBatch(query);
				isJobExecutionPending = true;

				if (itemCounter >= batchSize) {
					itemCounter = 0;
					CommitJob();
					isJobExecutionPending = false;
				}
			}

			if (isJobExecutionPending) {
				CommitJob();
				isJobExecutionPending = false;
			}
		} catch (

		SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void JobExecutionInvoiceItems() {
		try {
			ArrayList<InvoiceItemModel> invoiceItems = dao.getInvoiceItems();
			delSb.append("delete from InvoiceItems where id in (");
			updateSb.append("update InvoiceItems set isSynced = 1 where id in (");
			for (InvoiceItemModel items : invoiceItems) {
				sb = new StringBuilder();
				delSb.append(items.getId() + ",");
				updateSb.append(items.getId() + ",");

				sb.append(
						" INSERT INTO InvoiceItems(id,invoiceId ,title ,quantity ,unit ,salePrice ,purchasePrice ,subTotal ,createdBy ,createdAt) VALUES ");
				sb.append("(" + items.getId() + "," + items.getInvoiceId() + ",'" + items.getTitle() + "',"
						+ items.getQuantity() + ",'" + items.getUnit() + "'," + items.getSalePrice() + ","
						+ items.getPurchasePrice() + "," + items.getSubTotal() + ",'" + items.getCreatedBy() + "','"
						+ items.getCreatedAt() + "')");

				itemCounter++;
				String query = sb.toString();
				writefile(query);
				stmt.addBatch(query);
				isJobExecutionPending = true;

				if (itemCounter >= batchSize) {
					itemCounter = 0;
					CommitJob();
					isJobExecutionPending = false;
				}
			}

			if (isJobExecutionPending) {
				CommitJob();
				isJobExecutionPending = false;
			}
		} catch (

		SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void CommitJob() {
		String deleteQuery = delSb.toString();
		String updateQuery = updateSb.toString();

		deleteQuery = deleteQuery.substring(0, deleteQuery.length() - 1);
		deleteQuery = deleteQuery + ")";

		updateQuery = updateQuery.substring(0, updateQuery.length() - 1);
		updateQuery = updateQuery + ")";

		prepareDeleteQuery(deleteQuery);
		prepareUpdateQuery(updateQuery);

		try {
			stmt.executeBatch();
			remoteCon.commit();
			stmt.clearBatch();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void prepareUpdateQuery(String updateQuery) {
		try {
			Statement stmt = localCon.createStatement();
			stmt.execute(updateQuery);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void prepareDeleteQuery(String deleteQuery) {
		try {
			Statement stmt = remoteCon.createStatement();
			stmt.execute(deleteQuery);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void writefile(String data) {
		try {
			FileWriter myWriter = new FileWriter("invoicequery.txt", true);
			myWriter.write(data);
			myWriter.close();
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	public static void PreSync() {
		try {
			stmt = remoteCon.createStatement();

			stmt = remoteCon.createStatement();
			isJobExecutionPending = false;
			itemCounter = 0;
			delSb = new StringBuilder();
			updateSb = new StringBuilder();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void JobExecutionInvoices() {
		try {
			ArrayList<InvoiceModel> invoices = dao.getInvoices();
			delSb.append("delete from Invoices where Invoices.id in (");
			updateSb.append("update Invoices set isSynced = 1 where Invoices.id in (");
			for (InvoiceModel invc : invoices) {
				sb = new StringBuilder();
				delSb.append(invc.getId() + ",");
				updateSb.append(invc.getId() + ",");

				sb.append(
						"INSERT INTO Invoices(id,invoiceNumber,refInvoiceNumber,customerPhone,customer,total,discountPercent,discountAmount,totalToPay,received,change,createdBy,createdAt)"
								+ "     VALUES");
				sb.append("(" + invc.getId() + ",'" + invc.getInvoiceNumber() + "','" + invc.getRefInvoiceNumber()
						+ "','" + invc.getCustomerPhone() + "','" + invc.getCustomer() + "'," + invc.getTotal() + ","
						+ invc.getDiscountPercent() + "," + invc.getDiscountAmount() + "," + invc.getTotalToPay() + ","
						+ invc.getReceived() + "," + invc.getChange() + ",'" + invc.getCreatedBy() + "','"
						+ invc.getCreatedAt() + "')");

				itemCounter++;
				String query = sb.toString();
				writefile(query);
				stmt.addBatch(query);
				isJobExecutionPending = true;

				if (itemCounter >= batchSize) {
					itemCounter = 0;
					CommitJob();
					isJobExecutionPending = false;
				}
			}

			if (isJobExecutionPending) {
				CommitJob();
				isJobExecutionPending = false;
			}
		} catch (

		SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void PostSync() {

	}
}
