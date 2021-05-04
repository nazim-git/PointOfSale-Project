package controllers;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import Helpers.InputValidation;
import dataAccess.ExpenseDao;
import dataModels.CustomerModel;
import dataModels.ExpenseModel;
import dataModels.UserModel;
import viewModels.AddExpenseVM;

public class ExpenseController {
	
	static ExpenseDao expenseDao = new ExpenseDao();

	public static boolean validateAddExpenseInput(AddExpenseVM expenseForm) {
		if (expenseForm.txtAmountExpense.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Expense Amount can't be Empty!");
			expenseForm.txtAmountExpense.requestFocus();
			return false;
		} else if (expenseForm.txtDescriptionExpense.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Expense Description can't be Empty!");
			expenseForm.txtDescriptionExpense.requestFocus();
			return false;
		} else if (!InputValidation.validateNumbers(expenseForm.txtAmountExpense.getText())) {
			JOptionPane.showMessageDialog(null, "Expense Amount must be Numeric only!");
			expenseForm.txtAmountExpense.requestFocus();
			return false;
		} else {
			return true;
		}
	}

	public static void addNewExpense(AddExpenseVM expenseForm) {

		ExpenseModel expense = new ExpenseModel(Float.parseFloat(expenseForm.txtAmountExpense.getText()),
				expenseForm.txtDescriptionExpense.getText(), UserModel.Name, new Timestamp(new Date().getTime()));

		expenseDao.insertExpense(expense);

		JOptionPane.showMessageDialog(null, "Expense Added Successfully!");
		resetFields(expenseForm);

	}

	public static void resetFields(AddExpenseVM expenseForm) {
		expenseForm.txtAmountExpense.setText(null);
		expenseForm.txtDescriptionExpense.setText(null);
	}

	public static ArrayList<ExpenseModel> fillTableWithExpenses(ArrayList<ExpenseModel> expenses,
			DefaultTableModel expensesTableModel) {
		expensesTableModel.setRowCount(0);
		expenses = expenseDao.getExpenses();

		for (int i = 0; i < expenses.size(); i++) {

			Object[] object = { expenses.get(i).getAmount(), expenses.get(i).getDescription(), expenses.get(i).getCreatedBy()};
			expensesTableModel.addRow(object);
		}
		System.out.println(expenses.size());
		return expenses;
	}

	public static boolean deleteExpense(ExpenseModel selectedExpense) {
		if (JOptionPane.showConfirmDialog(null, "Are you sure?", "WARNING",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			expenseDao.deleteExpense(selectedExpense.getId(), new Timestamp(new Date().getTime()));
			return true;
		} else {
			return false;
		}
	}

}
