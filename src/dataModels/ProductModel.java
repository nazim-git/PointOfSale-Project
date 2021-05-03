package dataModels;

import java.sql.Timestamp;
import java.util.Date;

public class ProductModel {
	private int id;
	private String title;
	private String description;
	private String unit;
	private float salePrice;
	private float purchasePrice;
	private int stock;
	private String createdBy;
	private Timestamp createdAt;
	private boolean status;
	private boolean isDeleted;
	private String deletedBy;
	private Timestamp deletedAt;

	public ProductModel() {
		super();
	}

	public ProductModel(String title, String description, String unit, String createdBy, Timestamp createdAt, boolean status) {
		super();
		this.title=title;
		this.description=description;
		this.unit=unit;
		this.createdBy=createdBy;
		this.createdAt=createdAt;
		this.status=status;
	}

	public ProductModel(String title, String description, String unit, boolean status) {
		super();
		this.title=title;
		this.description=description;
		this.unit=unit;
		this.status=status;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public float getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(float salePrice) {
		this.salePrice = salePrice;
	}

	public float getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(float purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdAt;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public void setCreatedDate(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
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

	public Date getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(Timestamp deletedAt) {
		this.deletedAt = deletedAt;
	}

}
