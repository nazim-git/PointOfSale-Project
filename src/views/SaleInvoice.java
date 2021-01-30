package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListDataListener;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import dataAccess.SaleInvoiceDao;
import dataModels.ProductModel;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SaleInvoice extends JFrame {

	// UI
	private JPanel contentPane;
	private JLabel lblSaleInvoice, lblProductName, lblProductId, lblMeasuringUnit, lblUnitPrice;
	private JComboBox cmbProductName, cmbProductID;
	private JTextField txtMeasuringUnit, txtUnitPrice;
	private JButton btnGoBackSaleInvoice;

	// DAO
	SaleInvoiceDao saleInvoiceDao;

	ArrayList<ProductModel> products;
	ProductModel selectedProduct;

	public SaleInvoice() {
		Initializations();
		getData();
		setUI();
		fillWithSelectedProduct(true);
		repaint();
		revalidate();
	}

	public void fillWithSelectedProduct(boolean initial) {
		if (initial)
			selectedProduct = products.get(0);

		txtMeasuringUnit.setText(selectedProduct.getUnit());
		txtUnitPrice.setText(String.valueOf(selectedProduct.getPrice()));
	}

	public void setUI() {
		setTitle("Sale Invoice");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 768, 457);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setVisible(true);

		lblSaleInvoice = new JLabel("Sale Invoice");
		lblSaleInvoice.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblSaleInvoice.setBounds(10, 11, 156, 30);
		contentPane.add(lblSaleInvoice);

		lblProductName = new JLabel("Product Name");
		lblProductName.setBounds(221, 64, 102, 14);
		contentPane.add(lblProductName);

		lblProductId = new JLabel("Product ID");
		lblProductId.setBounds(42, 64, 86, 14);
		contentPane.add(lblProductId);

		cmbProductName = new JComboBox();
		cmbProductName.setBounds(231, 88, 147, 20);
		contentPane.add(cmbProductName);
		for (int i = 0; i < products.size(); i++) {
			cmbProductName.addItem(products.get(i).getName());
		}
		cmbProductName.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cmbProductID.setSelectedIndex(cmbProductName.getSelectedIndex());

				selectedProduct = products.get(cmbProductName.getSelectedIndex());

				fillWithSelectedProduct(false);
			}
		});

		cmbProductID = new JComboBox();
		cmbProductID.setBounds(52, 88, 147, 20);
		contentPane.add(cmbProductID);
		for (int i = 0; i < products.size(); i++) {
			cmbProductID.addItem(products.get(i).getID());
		}
		cmbProductID.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cmbProductName.setSelectedIndex(cmbProductID.getSelectedIndex());

				selectedProduct = products.get(cmbProductID.getSelectedIndex());
			}
		});

		btnGoBackSaleInvoice = new JButton("Go Back!");
		btnGoBackSaleInvoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new App();
			}
		});

		btnGoBackSaleInvoice.setBounds(653, 21, 89, 30);
		contentPane.add(btnGoBackSaleInvoice);

		lblMeasuringUnit = new JLabel("Measuring Unit");
		lblMeasuringUnit.setBounds(594, 64, 89, 14);
		contentPane.add(lblMeasuringUnit);

		txtMeasuringUnit = new JTextField();
		txtMeasuringUnit.setEditable(false);
		txtMeasuringUnit.setBounds(597, 88, 120, 20);
		contentPane.add(txtMeasuringUnit);
		txtMeasuringUnit.setColumns(10);

		lblUnitPrice = new JLabel("Unit Price");
		lblUnitPrice.setBounds(594, 119, 89, 14);
		contentPane.add(lblUnitPrice);

		txtUnitPrice = new JTextField();
		txtUnitPrice.setEditable(false);
		txtUnitPrice.setColumns(10);
		txtUnitPrice.setBounds(597, 143, 120, 20);
		contentPane.add(txtUnitPrice);

	}

	public void Initializations() {
		saleInvoiceDao = new SaleInvoiceDao();
		products = new ArrayList<ProductModel>();
		selectedProduct = new ProductModel();
	}

	public void getData() {
		try {
			ResultSet rs = saleInvoiceDao.getProducts();

			ProductModel product = new ProductModel();

			while (rs.next()) {
				product.setID(Integer.parseInt(rs.getString("ID")));
				product.setName(rs.getString("title"));
				product.setPrice(Float.parseFloat(rs.getString("price")));
				product.setUnit(rs.getString("unit"));
				products.add(product);
				product = new ProductModel();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
