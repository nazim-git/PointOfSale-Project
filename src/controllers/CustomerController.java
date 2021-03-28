package controllers;

import java.sql.Timestamp;
import java.util.Date;

import javax.swing.JOptionPane;

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
		} else {
			return true;
		}
	}

	public static void resetFields(AddCustomerVM customerForm) {
		customerForm.txtCustomerName.setText(null);
		customerForm.txtCustomerPhone.setText(null);
		customerForm.txtStreet.setText(null);
		customerForm.txtArea.setText(null);
		customerForm.txtCity.setText(null);
	}

	public static void addNewCustomer(AddCustomerVM customerForm) {
		CustomerModel customer = new CustomerModel(customerForm.txtCustomerName.getText(),
				customerForm.txtCustomerPhone.getText(), customerForm.txtStreet.getText(),
				customerForm.txtArea.getText(), customerForm.txtCity.getText(), UserModel.Name,
				new Timestamp(new Date().getTime()));
		
		customerDao.insertCustomer(customer);
	}

}
