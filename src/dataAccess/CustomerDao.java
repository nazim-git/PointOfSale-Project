package dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import dataModels.CustomerModel;

public class CustomerDao {
	Connection con = DbConnection.getInstance();
	PreparedStatement pst;
	ResultSet rs;

	public void insertCustomer(CustomerModel customer) {
		try {
			String query = "insert into Customers" + "(name,phone,street,area,city,createdBy,createdAt) values"
					+ "(?,?,?,?,?,?,?)";
			pst = con.prepareStatement(query);

			pst.setString(1, customer.getName());
			pst.setString(2, customer.getPhone());
			pst.setString(3, customer.getStreet());
			pst.setString(4, customer.getArea());
			pst.setString(5, customer.getCity());
			pst.setString(6, customer.getCreatedBy());
			pst.setTimestamp(7, customer.getCreatedAt());

			pst.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
