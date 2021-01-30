package dataModels;

public class UserModel {
	private int ID;
	private String Name;
	private String Username;
	private String Password;
	
	public UserModel(String Username, String Password) {
		this.Username = Username;
		this.Password = Password;
	}
	
	public UserModel(int ID, String Name, String Username, String Password) {
		this.ID = ID;
		this.Name = Name;
		this.Username = Username;
		this.Password = Password;
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	
}
