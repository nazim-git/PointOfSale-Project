package dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import dataModels.CustomerModel;
import dataModels.ExpenseModel;
import dataModels.User;

public class ExpenseDao {
	Connection con = DbConnection.getInstance();
	PreparedStatement pst;
	ResultSet rs;

	public void insertExpense(ExpenseModel expense) {
		try {
			String query = "insert into Expenses" + "(amount,description,createdBy,createdAt) values" + "(?,?,?,?)";
			pst = con.prepareStatement(query);

			pst.setFloat(1, expense.getAmount());
			pst.setString(2, expense.getDescription());
			pst.setString(3, expense.getCreatedBy());
			pst.setTimestamp(4, expense.getCreatedAt());

			pst.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<ExpenseModel> getExpenses() {
		ArrayList<ExpenseModel> expenses = null;
		try {
			String query = "select * from Expenses where isDeleted = 0";
			pst = con.prepareStatement(query);
			rs = pst.executeQuery();

			expenses = new ArrayList<ExpenseModel>();
			ExpenseModel expense = new ExpenseModel();

			while (rs.next()) {

				expense.setId(Integer.parseInt(rs.getString("id")));
				expense.setAmount(rs.getFloat("amount"));
				expense.setDescription(rs.getString("description"));
				expense.setCreatedBy(rs.getString("createdBy"));
				expense.setCreatedAt(rs.getTimestamp("createdAt"));
				expense.setDeleted(Boolean.parseBoolean(rs.getString("isDeleted")));
				expense.setDeletedBy(rs.getString("deletedBy"));
				expense.setDeletedAt(rs.getTimestamp("deletedAt"));

				expenses.add(expense);
				expense = new ExpenseModel();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return expenses;
	}

	public void deleteExpense(int id, Timestamp deletedAt) {
		try {
			String query = "UPDATE Expenses SET isDeleted = 1, deletedBy ='" + User.Name + "', deletedAt = '"
					+ deletedAt + "' WHERE id = " + id;
			pst = con.prepareStatement(query);

			pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
