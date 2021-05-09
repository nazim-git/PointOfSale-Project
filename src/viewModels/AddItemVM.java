package viewModels;

import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dataModels.InvoiceModel;
import dataModels.ProductModel;

public class AddItemVM {

	ProductModel product;
	int quantity;
	InvoiceModel invoice;
	DefaultTableModel tableModel;
	JTextField txtTotal, txtDiscountPercent, txtDiscountAmount, txtTotalToPay, txtReceived, txtChange;

	public AddItemVM(ProductModel product, int quantity, InvoiceModel invoice, DefaultTableModel tableModel) {
		this.product = product;
		this.quantity = quantity;
		this.invoice = invoice;
		this.tableModel = tableModel;
	}

	public AddItemVM(InvoiceModel invoice, JTextField txtTotal, JTextField txtDiscountPercent,
			JTextField txtDiscountAmount, JTextField txtTotalToPay, JTextField txtReceived, JTextField txtChange) {
		this.invoice = invoice;
		this.txtTotal = txtTotal;
		this.txtDiscountPercent = txtDiscountPercent;
		this.txtDiscountAmount = txtDiscountAmount;
		this.txtTotalToPay = txtTotalToPay;
		this.txtReceived = txtReceived;
		this.txtChange = txtChange;
	}

	public AddItemVM(ProductModel product, int quantity, InvoiceModel invoice, DefaultTableModel tableModel,JTextField txtTotal, JTextField txtDiscountPercent,
			JTextField txtDiscountAmount, JTextField txtTotalToPay, JTextField txtReceived, JTextField txtChange) {
		this.product = product;
		this.quantity = quantity;
		this.invoice = invoice;
		this.tableModel = tableModel;
		this.txtTotal = txtTotal;
		this.txtDiscountPercent = txtDiscountPercent;
		this.txtDiscountAmount = txtDiscountAmount;
		this.txtTotalToPay = txtTotalToPay;
		this.txtReceived = txtReceived;
		this.txtChange = txtChange;
	}

	public AddItemVM(InvoiceModel invoice, DefaultTableModel tableModel, JTextField txtTotal,
			JTextField txtDiscountPercent, JTextField txtDiscountAmount, JTextField txtTotalToPay,
			JTextField txtReceived, JTextField txtChange) {
		this.invoice = invoice;
		this.tableModel = tableModel;
		this.txtTotal = txtTotal;
		this.txtDiscountPercent = txtDiscountPercent;
		this.txtDiscountAmount = txtDiscountAmount;
		this.txtTotalToPay = txtTotalToPay;
		this.txtReceived = txtReceived;
		this.txtChange = txtChange;
	}

	public JTextField getTxtTotal() {
		return txtTotal;
	}

	public void setTxtTotal(JTextField txtTotal) {
		this.txtTotal = txtTotal;
	}

	public JTextField getTxtDiscountPercent() {
		return txtDiscountPercent;
	}

	public void setTxtDiscountPercent(JTextField txtDiscountPercent) {
		this.txtDiscountPercent = txtDiscountPercent;
	}

	public JTextField getTxtDiscountAmount() {
		return txtDiscountAmount;
	}

	public void setTxtDiscountAmount(JTextField txtDiscountAmount) {
		this.txtDiscountAmount = txtDiscountAmount;
	}

	public JTextField getTxtTotalToPay() {
		return txtTotalToPay;
	}

	public void setTxtTotalToPay(JTextField txtTotalToPay) {
		this.txtTotalToPay = txtTotalToPay;
	}

	public JTextField getTxtReceived() {
		return txtReceived;
	}

	public void setTxtReceived(JTextField txtReceived) {
		this.txtReceived = txtReceived;
	}

	public JTextField getTxtChange() {
		return txtChange;
	}

	public void setTxtChange(JTextField txtChange) {
		this.txtChange = txtChange;
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
