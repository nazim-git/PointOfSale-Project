package dataModels;

import java.sql.Timestamp;

public class CustomerModel {

	private String name, phone, createdBy;
	private Timestamp createdAt;
	private boolean isDeleted;
	private String deletedBy;
	private Timestamp deletedAt;

	public CustomerModel(String name, String phone, String createdBy, Timestamp createdAt) {
		super();
		this.name = name;
		this.phone = phone;
		this.createdBy = createdBy;
		this.createdAt = createdAt;
	}

	public CustomerModel(String name, String phone) {
		super();
		this.name = name;
		this.phone = phone;
	}

	public CustomerModel() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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
