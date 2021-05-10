package controllers;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import dataAccess.RemoteDbConnection;
import dataAccess.SyncDao;
import dataModels.InvoiceModel;

public class SyncController {

	static SyncDao dao = new SyncDao();
	static Connection con = RemoteDbConnection.getInstance();
	static Statement stmt;
	static StringBuilder sb;
	static StringBuilder delSb;
	static int itemCounter = 0;
	static boolean isJobExecutionPending = false;
	private static int batchSize;

	public static void Sync() {
		batchSize = 50;
		try {
			stmt = con.createStatement();

			ArrayList<InvoiceModel> invoices = dao.getInvoices();

			int itemCounter = 0;
			delSb = new StringBuilder();
			delSb.append("delete from Invoices where Invoices.id in (");
			for (InvoiceModel invc : invoices) {
				sb = new StringBuilder();
				delSb.append(invc.getId() + ",");

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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void CommitJob() {
		String deleteQuery = delSb.toString();
		deleteQuery = deleteQuery.substring(0, deleteQuery.length() - 1);
		deleteQuery = deleteQuery + ")";
		prepareDeleteQuery(deleteQuery);

		try {
			stmt.executeBatch();
			con.commit();
			stmt.clearBatch();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void prepareDeleteQuery(String deleteQuery) {
		try {
			Statement stmt = con.createStatement();
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
			stmt = con.createStatement();
			isJobExecutionPending = false;
			itemCounter = 0;
			delSb = new StringBuilder();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void JobExecutionInvoices() {
		try {
			ArrayList<InvoiceModel> invoices = dao.getInvoices();
			delSb.append("delete from Invoices where Invoices.id in (");
			for (InvoiceModel invc : invoices) {
				sb = new StringBuilder();
				delSb.append(invc.getId() + ",");

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
