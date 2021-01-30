package dataAccess;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	private static Connection connection;
	private DbConnection() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			connection = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=PointofSaleJAVA;integratedSecurity=false;user=sa;password=sa123;");
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getInstance() {
		if (connection == null)
			new DbConnection();
		System.out.println("DataBase connected Successfully!!!");
		return connection;
	}

}


