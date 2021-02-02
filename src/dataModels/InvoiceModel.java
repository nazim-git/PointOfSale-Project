package dataModels;

import java.util.ArrayList;
import java.util.Date;

public class InvoiceModel {

	private int id;
	private String invoiceNumber;
	private int customerId;
	private float discountPercent;
	private float discount;
	private float total;
	private int createdBy;
	private Date createdDate;
	
	private ArrayList<InvoiceItemModel> invoiceItems;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public float getDiscountPercent() {
		return discountPercent;
	}
	public void setDiscountPercent(float discountPercent) {
		this.discountPercent = discountPercent;
	}
	public float getDiscount() {
		return discount;
	}
	public void setDiscount(float discount) {
		this.discount = discount;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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
