package dataAccess;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class RemoteDbConnection {
	private static Connection connection;
	private RemoteDbConnection() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			connection = DriverManager.getConnection("jdbc:sqlserver://pos-server-nazim.database.windows.net:1433;database=POS;user=muhammadnazim@pos-server-nazim;password=2kRxAWxBgr6FJMF;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;");
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getInstance() {
		if (connection == null)
			new RemoteDbConnection();
		return connection;
	}

}

//var config = {
//	    user: 'muhammadnazim',
//	    password: '2kRxAWxBgr6FJMF',
//	    server: 'pos-server-nazim.database.windows.net',
//	    database: 'POS',
//	    port: 1433
//	};
