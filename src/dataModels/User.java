package dataModels;

public class User {
	//properties
	public static int ID;
	public static String Name;
	public static String Username;
	public static String Password;
	public static boolean IsAdmin;
	
	public static void logout() {
		User.ID = 0;
		User.Name = null;
		User.Username = null;
		User.Password = null;
		User.IsAdmin = false;
	}
}
