package controllers;

import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dataAccess.InvoiceDao;
import dataModels.InvoiceModel;

public class SalesController {

	static InvoiceDao invoiceDao = new InvoiceDao();

	public static ArrayList<InvoiceModel> fillTableWithSales(ArrayList<InvoiceModel> sales,
			DefaultTableModel salesTableModel) {
		salesTableModel.setRowCount(0);
		sales = invoiceDao.getInvoices();

		for (int i = 0; i < sales.size(); i++) {

			Object[] object = { sales.get(i).getInvoiceNumber(), sales.get(i).getCustomer(),
					sales.get(i).getDiscountPercent(), sales.get(i).getTotal() };
			salesTableModel.addRow(object);
		}
		return sales;
	}

	public static void filterSales(JTextField txtSearchSales, ArrayList<InvoiceModel> sales,
			DefaultTableModel salesTableModel) {
		salesTableModel.setRowCount(0);
		sales = invoiceDao.getInvoices();

		for (int i = 0; i < sales.size(); i++) {
			if (sales.get(i).getCustomer().contains(txtSearchSales.getText())
					|| sales.get(i).getCustomerPhone().contains(txtSearchSales.getText())
					|| sales.get(i).getInvoiceNumber().toLowerCase().contains(txtSearchSales.getText().toLowerCase())) {
				Object[] object = { sales.get(i).getInvoiceNumber(), sales.get(i).getCustomer(),
						sales.get(i).getDiscountPercent(), sales.get(i).getTotal() };
				salesTableModel.addRow(object);
			}
		}
//		return sales;
	}

}
