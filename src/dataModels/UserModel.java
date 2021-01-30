package dataModels;

public class UserModel {
	public static int ID;
	public static String Name;
	public static String Username;
	public static String Password;
	
	public static void logout() {
		UserModel.ID = 0;
		UserModel.Name = null;
		UserModel.Username = null;
		UserModel.Password = null;
	}
}
