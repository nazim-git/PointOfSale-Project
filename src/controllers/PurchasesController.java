package controllers;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dataAccess.ProductDao;
import dataAccess.PurchasesDao;
import dataModels.ProductModel;
import dataModels.PurchasesModel;
import dataModels.User;
import viewModels.AddProductVM;
import viewModels.AddPurchaseVM;

public class PurchasesController {

	static PurchasesDao purchasesDao = new PurchasesDao();
	static ProductDao productDao = new ProductDao();

	public static ArrayList<PurchasesModel> fillTableWithPurchases(ArrayList<PurchasesModel> purchases,
			DefaultTableModel purchasesTableModel) {
		purchasesTableModel.setRowCount(0);
		purchases = purchasesDao.getPurchases();

		for (int i = 0; i < purchases.size(); i++) {

			Object[] object = { purchases.get(i).getTitle(), purchases.get(i).getPurchasePrice(),
					purchases.get(i).getSalePrice(), purchases.get(i).getQuantity(), purchases.get(i).getUnit(),
					purchases.get(i).getSupplier(), purchases.get(i).getCreatedBy() };
			purchasesTableModel.addRow(object);
		}
		return purchases;
	}

	public static boolean validateAddPurchaseInput(AddPurchaseVM purchaseForm) {
		if (purchaseForm.txtSupplierPurchases.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Supplier can't be Empty!");
			purchaseForm.txtSupplierPurchases.requestFocus();
			return false;
		} else if (purchaseForm.txtPurchasePricePurchases.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Purchase Price can't be Empty!");
			purchaseForm.txtPurchasePricePurchases.requestFocus();
			return false;
		} else if (purchaseForm.txtSalePricePurchases.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Sale Price can't be Empty!");
			purchaseForm.txtSalePricePurchases.requestFocus();
			return false;
		} else if (purchaseForm.txtUnitPurchases.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Unit can't be Empty!");
			purchaseForm.txtUnitPurchases.requestFocus();
			return false;
		} else if (purchaseForm.txtQuantityPurchases.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Quantity can't be Empty!");
			purchaseForm.txtQuantityPurchases.requestFocus();
			return false;
		} else {
			return true;
		}
	}

	public static void addNewPurchase(AddPurchaseVM purchaseForm) {

		PurchasesModel purchase = new PurchasesModel(purchaseForm.cmbProductsPurchases.getSelectedItem(),
				purchaseForm.txtSupplierPurchases.getText(),
				Float.parseFloat(purchaseForm.txtPurchasePricePurchases.getText()),
				Float.parseFloat(purchaseForm.txtSalePricePurchases.getText()), purchaseForm.txtUnitPurchases.getText(),
				Integer.parseInt(purchaseForm.txtQuantityPurchases.getText()), User.Name,
				new Timestamp(new Date().getTime()));
		purchasesDao.insertPurchase(purchase);
		productDao.updateStock(purchaseForm);
		JOptionPane.showMessageDialog(null, "Purchase Recorded Successfully!");
		resetFields(purchaseForm);

	}
	
	public static void resetFields(AddPurchaseVM purchaseForm) {
		purchaseForm.cmbProductsPurchases.setSelectedIndex(0);
		purchaseForm.txtSupplierPurchases.setText(null);
		purchaseForm.txtPurchasePricePurchases.setText(null);
		purchaseForm.txtSalePricePurchases.setText(null);
		purchaseForm.txtUnitPurchases.setText(null);
		purchaseForm.txtQuantityPurchases.setText(null);
	}

}
