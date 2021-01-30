package dataModels;

public class ProductModel {
	private int ID;
	private String Name;
	
	public ProductModel(String Name) {
		this.Name = Name;
	}
	
	public ProductModel() {
	}

	public int getID() {
		return ID;
	}
	public void setID(int id) {
		ID = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
}
