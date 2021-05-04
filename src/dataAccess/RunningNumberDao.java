package dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dataModels.RunningNumber;
import dataModels.UserModel;

public class RunningNumberDao {
	Connection con = DbConnection.getInstance();
	PreparedStatement pst;
	ResultSet rst;

	public RunningNumber getRunningNumber(String type) {
		try {
			pst = con.prepareStatement("SELECT * FROM RunningNumber where type=?");

			pst.setString(1, type);

			rst = pst.executeQuery();
			RunningNumber runningNumber = new RunningNumber();
			while (rst.next()) {
				runningNumber.setId(Integer.parseInt(rst.getString("id")));
				runningNumber.setType(rst.getString("type"));
				runningNumber.setNumber(Integer.parseInt(rst.getString("number")));
				runningNumber.setPrefix(rst.getString("prefix"));
			}
			return runningNumber;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Boolean addRunningNumber(RunningNumber runningNumber) {
		try {
			pst = con.prepareStatement("Insert into RunningNumber(type, number, prefix) VALUES (?, ?, ?)");

			pst.setString(1, runningNumber.getType());
			pst.setInt(2, runningNumber.getNumber());
			pst.setString(3, runningNumber.getPrefix());

			rst = pst.executeQuery();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public Boolean updateRunningNumber(RunningNumber runningNumber) {
		try {
			pst = con.prepareStatement("Update RunningNumber SET number = ? where type = ?");

			pst.setInt(1, runningNumber.getNumber());
			pst.setString(2, runningNumber.getType());
			
			return pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
