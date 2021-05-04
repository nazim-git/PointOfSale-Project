package dataModels;

import java.sql.Timestamp;

import javax.swing.JTextField;

public class ExpenseModel {
	private int id;
	private float amount;
	private String description, createdBy;
	private Timestamp createdAt;
	private boolean isDeleted;
	private String deletedBy;
	private Timestamp deletedAt;
	
	public ExpenseModel(float amount, String description, String createdBy, Timestamp createdAt) {
		this.amount = amount;
		this.description = description;
		this.createdBy = createdBy;
		this.createdAt = createdAt;
	}
	public ExpenseModel() {
		// TODO Auto-generated constructor stub
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
