package dataModels;

import java.util.Date;

public class ProductModel {
	private int id;
	private String title;
	private String description;
	private String category;
	private String unit;
	private float salePrice;
	private float purchasePrice;
	private int createdBy;
	private Date createdDate;
	private boolean status;
	private int stock;

	public ProductModel() {
		super();
	}

	public ProductModel(String title, String description, String category, String unit, float salePrice,
			float purchasePrice, boolean status, int stock) {
		super();
		this.title = title;
		this.description = description;
		this.category = category;
		this.unit = unit;
		this.salePrice = salePrice;
		this.purchasePrice = purchasePrice;
		this.status = status;
		this.stock = stock;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
