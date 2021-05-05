package dataModels;

public class UserModel {
	int ID;
	String Name;
	String Username;
	String Password;
	boolean IsAdmin;
	
	public UserModel(String Name,String Username,String Password,boolean IsAdmin) {
		this.Name = Name;
		this.Username = Username;
		this.Password = Password;
		this.IsAdmin = IsAdmin;
	}

	public UserModel() {
		// TODO Auto-generated constructor stub
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

	public boolean isIsAdmin() {
		return IsAdmin;
	}

	public void setIsAdmin(boolean isAdmin) {
		IsAdmin = isAdmin;
	}
	
	
}
