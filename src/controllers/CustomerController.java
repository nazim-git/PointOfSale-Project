package controllers;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import Helpers.InputValidation;
import dataAccess.CustomerDao;
import dataModels.CustomerModel;
import dataModels.UserModel;
import viewModels.AddCustomerVM;

public class CustomerController {
	static CustomerDao customerDao = new CustomerDao();

	public static boolean validateAddCustomerInput(AddCustomerVM customerForm) {
		if (customerForm.txtCustomerName.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Customer Name can't be Empty!");
			customerForm.txtCustomerName.requestFocus();
			return false;
		} else if (customerForm.txtCustomerPhone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Customer Phone can't be Empty!");
			customerForm.txtCustomerPhone.requestFocus();
			return false;
		} else if (!InputValidation.validateNumbers(customerForm.txtCustomerPhone.getText())) {
			JOptionPane.showMessageDialog(null, "Customer Phone must be Numeric only!");
			customerForm.txtCustomerPhone.requestFocus();
			return false;
		} else {
			return true;
		}
	}

	public static void resetFields(AddCustomerVM customerForm) {
		customerForm.txtIdCustomer.setText(null);
		customerForm.txtCustomerName.setText(null);
		customerForm.txtCustomerPhone.setText(null);
	}

	public static void addNewCustomer(AddCustomerVM customerForm) {
		if (validateCustomer(customerForm)) {
			CustomerModel customer = new CustomerModel(customerForm.txtCustomerName.getText(),
					customerForm.txtCustomerPhone.getText(), UserModel.Name,
					new Timestamp(new Date().getTime()));

			customerDao.insertCustomer(customer);

			JOptionPane.showMessageDialog(null, "Customer Added Successfully!");
			resetFields(customerForm);
		} else {
			JOptionPane.showMessageDialog(null, "Customer with given Phone already exists!");
			customerForm.txtCustomerPhone.requestFocus();
		}
	}

	private static boolean validateCustomer(AddCustomerVM customerForm) {
		CustomerModel customer = customerDao.getCustomer(customerForm.txtCustomerPhone.getText());
		if (customer == null) {
			return true;
		} else {
			return false;
		}
	}

	public static ArrayList<CustomerModel> fillTableWithCustomers(ArrayList<CustomerModel> customers,
			DefaultTableModel customersTableModel) {
		customersTableModel.setRowCount(0);
		customers = customerDao.getCustomers();

		for (int i = 0; i < customers.size(); i++) {

			Object[] object = { customers.get(i).getName(), customers.get(i).getPhone()};
			customersTableModel.addRow(object);
		}
		return customers;
	}

	public static boolean deleteCustomer(CustomerModel customer) {
		if (JOptionPane.showConfirmDialog(null, "Are you sure?", "WARNING",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			customerDao.deleteCustomer(customer.getId(), new Timestamp(new Date().getTime()));
			return true;
		} else {
			return false;
		}

	}

	public static boolean updateCustomer(CustomerModel selectedCustomer, AddCustomerVM customerForm) {
		if (JOptionPane.showConfirmDialog(null, "Are you sure?", "WARNING",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			if (validateCustomer(customerForm) || selectedCustomer.getPhone().equals(customerForm.txtCustomerPhone.getText())) {
				CustomerModel newDetails = new CustomerModel(customerForm.txtCustomerName.getText(),
						customerForm.txtCustomerPhone.getText());
				customerDao.updateCustomer(selectedCustomer.getId(), newDetails);
				return true;
			}
			else {
				JOptionPane.showMessageDialog(null, "Customer with given Phone already exists!");
				return false;
			}
		} else {
			return false;
		}
	}

}
