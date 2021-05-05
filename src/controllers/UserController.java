package controllers;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import Helpers.InputValidation;
import dataAccess.UserDao;
import dataModels.CustomerModel;
import dataModels.User;
import dataModels.UserModel;
import viewModels.AddUserVM;

public class UserController {

	static UserDao userDao = new UserDao();

	public static ArrayList<UserModel> fillTableWithUsers(ArrayList<UserModel> users,
			DefaultTableModel userTableModel) {
		userTableModel.setRowCount(0);
		users = userDao.getUsers();

		for (int i = 0; i < users.size(); i++) {

			Object[] object = { users.get(i).getName(), users.get(i).getUsername(), users.get(i).isIsAdmin() };
			userTableModel.addRow(object);
		}
		return users;
	}

	public static boolean validateAddUserInput(AddUserVM userForm) {
		if (userForm.txtNameUser.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Name can't be Empty!");
			userForm.txtNameUser.requestFocus();
			return false;
		} else if (userForm.txtUsername.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Username can't be Empty!");
			userForm.txtUsername.requestFocus();
			return false;
		} else if (userForm.txtPassword.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Password can't be Empty!");
			userForm.txtPassword.requestFocus();
			return false;
		} else {
			return true;
		}
	}

	public static void addNewUser(AddUserVM userForm) {
		if (validateUser(userForm)) {
			UserModel user = new UserModel(userForm.txtNameUser.getText(),
					userForm.txtUsername.getText(), userForm.txtPassword.getText(),userForm.chkboxIsAdmin.isSelected());
//					new Timestamp(new Date().getTime()));

			userDao.insertUser(user);

			JOptionPane.showMessageDialog(null, "Customer Added Successfully!");
			resetFields(userForm);
		} else {
			JOptionPane.showMessageDialog(null, "Username already exists!");
			userForm.txtUsername.requestFocus();
		}
	}

	public static void resetFields(AddUserVM userForm) {
		userForm.txtUserId.setText(null);
		userForm.txtNameUser.setText(null);
		userForm.txtUsername.setText(null);
		userForm.txtPassword.setText(null);
		userForm.chkboxIsAdmin.setSelected(false);
	}

	private static boolean validateUser(AddUserVM userForm) {
		UserModel user = userDao.getUser(userForm.txtUsername.getText());
		if (user == null) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean deleteUser(UserModel user) {
		if (JOptionPane.showConfirmDialog(null, "Are you sure?", "WARNING",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			userDao.deleteUser(user.getID(), new Timestamp(new Date().getTime()));
			return true;
		} else {
			return false;
		}
	}

	public static boolean updateUser(UserModel selectedUser, AddUserVM userForm) {
		if (JOptionPane.showConfirmDialog(null, "Are you sure?", "WARNING",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			if (validateUser(userForm) || selectedUser.getUsername().equals(userForm.txtUsername.getText())) {
				UserModel newDetails = new UserModel(userForm.txtNameUser.getText(),
						userForm.txtUsername.getText(),userForm.txtPassword.getText(),userForm.chkboxIsAdmin.isSelected());
				userDao.updateUser(selectedUser.getID(), newDetails);
				return true;
			}
			else {
				JOptionPane.showMessageDialog(null, "Username already exists!");
				return false;
			}
		} else {
			return false;
		}
	}

}
