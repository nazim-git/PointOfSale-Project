package controllers;

import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import dataAccess.ProductDao;
import dataModels.ProductModel;

public class ProductController {
	static ProductDao productDao = new ProductDao();

	public static ArrayList<ProductModel> fillTableWithProducts(ArrayList<ProductModel> products,
			DefaultTableModel tableModel) {

		products = productDao.getProducts();

		for (int i = 0; i < products.size(); i++) {

			Object[] object = { "", products.get(i).getTitle(), products.get(i).getCategory(),
					products.get(i).getPurchasePrice(), products.get(i).getSalePrice(), products.get(i).getUnit(),
					products.get(i).getStock(), products.get(i).getStatus() };
			tableModel.addRow(object);
		}
		return products;
	}

	public static boolean validateAddProductInput(JTextField txtTitle, JTextField txtCategory, JTextField txtUnit,
			JTextField txtPurchasePrice, JTextField txtSalePrice, JTextField txtStock) {
		if (txtTitle.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Product Title can't be Empty!");
			txtTitle.requestFocus();
			return false;
		} else if (txtCategory.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Product Category can't be Empty!");
			txtCategory.requestFocus();
			return false;
		} else if (txtPurchasePrice.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Product Purchase Price can't be Empty!");
			txtPurchasePrice.requestFocus();
			return false;
		} else if (txtSalePrice.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Product Sale Price can't be Empty!");
			txtSalePrice.requestFocus();
			return false;
		} else if (txtUnit.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Product Unit can't be Empty!");
			txtUnit.requestFocus();
			return false;
		} else if (txtStock.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Product Stock can't be Empty!");
			txtStock.requestFocus();
			return false;
		} else {
			return true;
		}
	}

	public static void resetFields(JTextField txtTitle, JTextField txtCategory, JTextField txtUnit,
			JTextField txtPurchasePrice, JTextField txtSalePrice, JTextField txtStock, JCheckBox cbStatus) {
		txtTitle.setText(null);
		txtCategory.setText(null);
		txtUnit.setText(null);
		txtPurchasePrice.setText(null);
		txtSalePrice.setText(null);
		txtStock.setText(null);
		cbStatus.setSelected(true);
	}

	public static void addNewProduct(ProductModel product) {
		System.out.println(product.getTitle());
		System.out.println(product.getCategory());
		System.out.println(product.getUnit());
		System.out.println(product.getPurchasePrice());
		System.out.println(product.getSalePrice());
		System.out.println(product.getStatus());
		System.out.println(product.getStock());
	}

}
