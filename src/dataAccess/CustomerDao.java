package dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import dataModels.CustomerModel;
import dataModels.User;

public class CustomerDao {
	Connection con = DbConnection.getInstance();
	PreparedStatement pst;
	ResultSet rs;

	public void insertCustomer(CustomerModel customer) {
		try {
			String query = "insert into Customers" + "(name,phone,createdBy,createdAt) values" + "(?,?,?,?)";
			pst = con.prepareStatement(query);

			pst.setString(1, customer.getName());
			pst.setString(2, customer.getPhone());
			pst.setString(3, customer.getCreatedBy());
			pst.setTimestamp(4, customer.getCreatedAt());

			pst.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public CustomerModel getCustomer(String phone) {
		CustomerModel customer = null;
		try {
			String query = "select * from Customers where phone = ? and isDeleted = 0";
			pst = con.prepareStatement(query);

			pst.setString(1, phone);

			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				customer = new CustomerModel();

				customer.setName(rs.getString(1));
				customer.setPhone(rs.getString(2));
				customer.setCreatedBy(rs.getString(3));
				customer.setCreatedAt(rs.getTimestamp(4));
				customer.setDeleted(rs.getBoolean(5));
				customer.setDeletedBy(rs.getString(6));
				customer.setDeletedAt(rs.getTimestamp(7));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customer;
	}

	public ArrayList<CustomerModel> getCustomers() {
		ArrayList<CustomerModel> customers = null;
		try {
			String query = "select * from Customers where isDeleted = 0";
			pst = con.prepareStatement(query);
			rs = pst.executeQuery();

			customers = new ArrayList<CustomerModel>();
			CustomerModel customer = new CustomerModel();

			while (rs.next()) {

				customer.setName(rs.getString("name"));
				customer.setPhone(rs.getString("phone"));
				customer.setCreatedBy(rs.getString("createdBy"));
				customer.setCreatedAt(rs.getTimestamp("createdAt"));
				customer.setDeleted(Boolean.parseBoolean(rs.getString("isDeleted")));
				customer.setDeletedBy(rs.getString("deletedBy"));
				customer.setDeletedAt(rs.getTimestamp("deletedAt"));

				customers.add(customer);
				customer = new CustomerModel();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customers;
	}

	public void deleteCustomer(String phone, Timestamp deletedAt) {
		try {
			String query = "UPDATE Customers SET isDeleted = 1, deletedBy ='" + User.Name + "', deletedAt = '"
					+ deletedAt + "' WHERE phone = '" + phone + "'";
			pst = con.prepareStatement(query);

			pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateCustomer(String phone, CustomerModel newDetails) {
		try {
			String query = "UPDATE Customers SET name ='" + newDetails.getName() + "',phone ='" + newDetails.getPhone()
					+ "' WHERE phone = '" + phone + "'";
			pst = con.prepareStatement(query);

			pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
