package controllers;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import dataAccess.ProductDao;
import dataModels.ProductModel;
import dataModels.UserModel;
import viewModels.AddProductVM;

public class ProductController {
	static ProductDao productDao = new ProductDao();

	public static ArrayList<ProductModel> fillTableWithProducts(ArrayList<ProductModel> products,
			DefaultTableModel productsTableModel) {

		productsTableModel.setRowCount(0);
		products = productDao.getProducts();

		for (int i = 0; i < products.size(); i++) {

			Object[] object = { products.get(i).getTitle(), products.get(i).getCategory(),
					products.get(i).getPurchasePrice(), products.get(i).getSalePrice(), products.get(i).getUnit(),
					products.get(i).getStock(), products.get(i).getStatus() };
			productsTableModel.addRow(object);
		}
		return products;
	}

	public static boolean validateAddProductInput(AddProductVM productForm) {
		if (productForm.txtTitle.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Product Title can't be Empty!");
			productForm.txtTitle.requestFocus();
			return false;
		} else if (productForm.txtCategory.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Product Category can't be Empty!");
			productForm.txtCategory.requestFocus();
			return false;
		} else if (productForm.txtPurchasePrice.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Product Purchase Price can't be Empty!");
			productForm.txtPurchasePrice.requestFocus();
			return false;
		} else if (productForm.txtSalePrice.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Product Sale Price can't be Empty!");
			productForm.txtSalePrice.requestFocus();
			return false;
		} else if (productForm.txtUnit.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Product Unit can't be Empty!");
			productForm.txtUnit.requestFocus();
			return false;
		} else if (productForm.txtStock.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Product Stock can't be Empty!");
			productForm.txtStock.requestFocus();
			return false;
		} else {
			return true;
		}
	}

	public static void resetFields(AddProductVM productForm) {
		productForm.txtID.setText(null);
		productForm.txtTitle.setText(null);
		productForm.txtCategory.setText(null);
		productForm.txtUnit.setText(null);
		productForm.txtPurchasePrice.setText(null);
		productForm.txtSalePrice.setText(null);
		productForm.txtStock.setText(null);
		productForm.cbStatus.setSelected(true);
		productForm.txtDescription.setText(null);
	}

	public static void addNewProduct(AddProductVM productForm) {
		if (validateProduct(productForm)) {
			ProductModel product = new ProductModel(productForm.txtTitle.getText(),
					productForm.txtDescription.getText(), productForm.txtCategory.getText(),
					productForm.txtUnit.getText(), Float.parseFloat(productForm.txtSalePrice.getText()),
					Float.parseFloat(productForm.txtPurchasePrice.getText()),
					Integer.parseInt(productForm.txtStock.getText()), UserModel.Name,
					new Timestamp(new Date().getTime()), productForm.cbStatus.isSelected());
			productDao.insertProduct(product);
			JOptionPane.showMessageDialog(null, "Product Added Successfully!");
			resetFields(productForm);
		} else {
			JOptionPane.showMessageDialog(null, "Product with given name already exists!");
		}
	}

	private static boolean validateProduct(AddProductVM productForm) {
		ProductModel product = productDao.getProduct(productForm.txtTitle.getText());
		if (product == null) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean deleteCustomer(ProductModel selectedProduct) {
		if (JOptionPane.showConfirmDialog(null, "Are you sure?", "WARNING",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			productDao.deleteCustomer(selectedProduct.getId(), new Timestamp(new Date().getTime()));
			return true;
		} else {
			return false;
		}
	}


	public static boolean updateCustomer(ProductModel selectedProduct, AddProductVM productForm) {
		if (JOptionPane.showConfirmDialog(null, "Are you sure?", "WARNING",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			if (validateProduct(productForm) || selectedProduct.getTitle().equals(productForm.txtTitle.getText())) {
				ProductModel newDetails = new ProductModel(productForm.txtTitle.getText(),
														   productForm.txtDescription.getText(),
														   productForm.txtCategory.getText(),
														   productForm.txtUnit.getText(),
														   Float.parseFloat(productForm.txtSalePrice.getText()),
														   Float.parseFloat(productForm.txtPurchasePrice.getText()),
														   productForm.cbStatus.isSelected(),
														   Integer.parseInt(productForm.txtStock.getText()));
				productDao.updateProduct(selectedProduct.getId(), newDetails);
				return true;
			}
			else {
				JOptionPane.showMessageDialog(null, "Product with given title already exists!");
				return false;
			}
		} else {
			return false;
		}
	}

}
