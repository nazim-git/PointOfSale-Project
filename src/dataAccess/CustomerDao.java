package dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import dataModels.CustomerModel;
import dataModels.ProductModel;
import dataModels.UserModel;

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

	public CustomerModel getCustomer(String phone) {
		CustomerModel customer = null;
		try {
			String query = "select * from Customers where phone = ? and isDeleted = 0";
			pst = con.prepareStatement(query);

			pst.setString(1, phone);

			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				customer = new CustomerModel();

				customer.setId(rs.getInt(1));
				customer.setName(rs.getString(2));
				customer.setPhone(rs.getString(3));
				customer.setStreet(rs.getString(4));
				customer.setArea(rs.getString(5));
				customer.setCity(rs.getString(6));
				customer.setCreatedBy(rs.getString(7));
				customer.setCreatedAt(rs.getTimestamp(8));
				customer.setDeleted(rs.getBoolean(9));
				customer.setDeletedBy(rs.getString(10));
				customer.setDeletedAt(rs.getTimestamp(11));
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

				customer.setId(Integer.parseInt(rs.getString("id")));
				customer.setName(rs.getString("name"));
				customer.setPhone(rs.getString("phone"));
				customer.setStreet(rs.getString("street"));
				customer.setArea(rs.getString("area"));
				customer.setCity(rs.getString("city"));
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

	public void deleteCustomer(int id, Timestamp deletedAt) {
		System.out.println(deletedAt);
		try {
			String query = "UPDATE Customers SET isDeleted = 1, deletedBy ='" + UserModel.Name + "', deletedAt = '"
					+ deletedAt + "' WHERE id = " + id;
			pst = con.prepareStatement(query);

			pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
