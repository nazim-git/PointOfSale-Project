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

			sales.get(i).setInvoiceItems(invoiceDao.getInvoiceItems(sales.get(i).getId()));
			
			Object[] object = { sales.get(i).getInvoiceNumber(), sales.get(i).getCustomer(),
					sales.get(i).getDiscountPercent(), sales.get(i).getTotal() };
			salesTableModel.addRow(object);
		}
		return sales;
	}

	public static ArrayList<InvoiceModel> filterSales(JTextField txtSearchSales, ArrayList<InvoiceModel> sales,
			DefaultTableModel salesTableModel) {
		ArrayList<InvoiceModel> filteredSales = new ArrayList<InvoiceModel>();
		salesTableModel.setRowCount(0);
		sales = invoiceDao.getInvoices();

		for (int i = 0; i < sales.size(); i++) {
			if (sales.get(i).getCustomer().toLowerCase().contains(txtSearchSales.getText().toLowerCase())
					|| sales.get(i).getCustomerPhone().contains(txtSearchSales.getText())
					|| sales.get(i).getInvoiceNumber().toLowerCase().contains(txtSearchSales.getText().toLowerCase())) {
				filteredSales.add(sales.get(i));
				Object[] object = { sales.get(i).getInvoiceNumber(), sales.get(i).getCustomer(),
						sales.get(i).getDiscountPercent(), sales.get(i).getTotal() };
				salesTableModel.addRow(object);
			}
		}
		return filteredSales;
	}

}
