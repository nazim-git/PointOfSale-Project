package dataModels;

public class ProductModel {
	private int ID;
	private String Name;
	private float Price;
	private String Unit;
	
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

	public float getPrice() {
		return Price;
	}

	public void setPrice(float price) {
		Price = price;
	}

	public String getUnit() {
		return Unit;
	}

	public void setUnit(String unit) {
		Unit = unit;
	}
	
	
}
