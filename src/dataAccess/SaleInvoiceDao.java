package dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SaleInvoiceDao {
	Connection con = DbConnection.getInstance();
	PreparedStatement pst;
	ResultSet rst;

}
