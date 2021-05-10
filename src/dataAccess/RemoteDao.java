package dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RemoteDao {
	Connection con = RemoteDbConnection.getInstance();
	PreparedStatement pst;
	ResultSet rs;

}
