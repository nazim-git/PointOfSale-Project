package viewModels;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import dataModels.InvoiceModel;
import dataModels.ProductModel;

public class AddItemVM {

	ProductModel product;
	int quantity;
	InvoiceModel invoice;
	DefaultTableModel tableModel;

	public AddItemVM(ProductModel product, int quantity, InvoiceModel invoice, DefaultTableModel tableModel) {
		this.product = product;
		this.quantity = quantity;
		this.invoice = invoice;
		this.tableModel = tableModel;
	}

	public ProductModel getProduct() {
		return product;
	}

	public void setProduct(ProductModel product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public InvoiceModel getInvoice() {
		return invoice;
	}

	public void setInvoice(InvoiceModel invoice) {
		this.invoice = invoice;
	}

	public DefaultTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
	}

}
