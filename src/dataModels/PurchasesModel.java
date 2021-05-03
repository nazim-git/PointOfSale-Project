package dataModels;

import java.sql.Timestamp;

public class PurchasesModel {

	private int id;
	private String title;
	private float purchasePrice;
	private float salePrice;
	private int quantity;
	private String unit;
	private String supplier;
	private String createdBy;
	private Timestamp createdAt;
	private boolean isDeleted;
	private String deletedBy;
	private Timestamp deletedAt;
	
	public PurchasesModel(Object title, String supplier, float purchasePrice, float salePrice, String unit, int quantity,
			String createdBy, Timestamp createdAt) {
		this.title = (String) title;
		this.supplier = supplier;
		this.purchasePrice = purchasePrice;
		this.salePrice = salePrice;
		this.unit = unit;
		this.quantity = quantity;
		this.createdBy = createdBy;
		this.createdAt = createdAt;
	}
	public PurchasesModel() {
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public float getPurchasePrice() {
		return purchasePrice;
	}
	public void setPurchasePrice(float purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	public float getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(float salePrice) {
		this.salePrice = salePrice;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
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
	
}
