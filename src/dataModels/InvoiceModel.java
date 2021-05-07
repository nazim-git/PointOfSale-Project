package dataModels;

import java.sql.Timestamp;
import java.util.ArrayList;

public class InvoiceModel {

	private int id;
	private String invoiceNumber;
	private String customerPhone;
	private String customer;
	private float total;
	private float discountPercent;
	private float discountAmount;
	private float totalToPay;
	private float received;
	private float change;
	private String createdBy;
	private Timestamp createdAt;
	private boolean isDeleted;
	private String deletedBy;
	private Timestamp deletedAt;

	
	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getDeletedBy() {
		return deletedBy;
	}

	public void setDeletedBy(String deletedBy) {
		this.deletedBy = deletedBy;
	}

	public Timestamp getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(Timestamp deletedAt) {
		this.deletedAt = deletedAt;
	}

	public float getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(float discountAmount) {
		this.discountAmount = discountAmount;
	}

	public float getTotalToPay() {
		return totalToPay;
	}

	public void setTotalToPay(float totalToPay) {
		this.totalToPay = totalToPay;
	}

	public float getReceived() {
		return received;
	}

	public void setReceived(float received) {
		this.received = received;
	}

	public float getChange() {
		return change;
	}

	public void setChange(float change) {
		this.change = change;
	}

	private ArrayList<InvoiceItemModel> invoiceItems;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public float getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(float discountPercent) {
		this.discountPercent = discountPercent;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public ArrayList<InvoiceItemModel> getInvoiceItems() {
		return invoiceItems;
	}

	public void setInvoiceItems(ArrayList<InvoiceItemModel> invoiceItems) {
		this.invoiceItems = invoiceItems;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

}
