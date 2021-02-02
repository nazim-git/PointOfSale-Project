package views;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Helpers.Frame;
import controllers.InvoiceController;
import dataAccess.ProductDao;
import dataModels.InvoiceItemModel;
import dataModels.InvoiceModel;
import dataModels.ProductModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;

public class SaleInvoice extends JFrame {

	// UI
	private JPanel contentPane;
	private JLabel lblSaleInvoice, lblProductName, lblProductId, lblMeasuringUnit, lblUnitPrice;
	private JComboBox cmbProductName, cmbProductID;
	private JTextField txtMeasuringUnit, txtUnitPrice;
	private JButton btnGoBackSaleInvoice;

	private JScrollPane scrollPane;
	private JTable table;

	private DefaultTableModel tableModel;

	private ProductDao saleInvoiceDao;

	private ArrayList<ProductModel> products;

	private ProductModel selectedProduct;
	private int quantity;
	private InvoiceModel invoice;
	private ArrayList<InvoiceItemModel> items;

	String header[] = { "#", "Product", "Unit", "Unit Price", "Quantity", "Sub-Total" };

	private InvoiceController invoiceController = new InvoiceController();
	private JTextField txtQuantity;

	public SaleInvoice() {
		setMaximizedBounds(Frame.getScreenBounds());
		setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		setResizable(false);
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
		txtUnitPrice.setText(String.valueOf(selectedProduct.getSalePrice()));

//		System.out.println("---------------------------------");
//		System.out.println(selectedProduct.getId());
//		System.out.println(selectedProduct.getTitle());
//		System.out.println(selectedProduct.getDescription());
//		System.out.println(selectedProduct.getCategory());
//		System.out.println(selectedProduct.getUnit());
//		System.out.println(selectedProduct.getSalePrice());
//		System.out.println(selectedProduct.getPurchasePrice());
//		System.out.println(selectedProduct.getCreatedBy());
//		System.out.println(selectedProduct.getCreatedDate());
//		System.out.println(selectedProduct.getStatus());
	}

	public void setUI() {
		setTitle("Sale Invoice");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, screenSize.width, screenSize.height);

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setVisible(true);

		lblSaleInvoice = new JLabel("Sale Invoice");
		lblSaleInvoice.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblSaleInvoice.setBounds(10, 11, 156, 30);
		contentPane.add(lblSaleInvoice);

		lblProductName = new JLabel("Product Name");
		lblProductName.setBounds(140, 64, 102, 14);
		contentPane.add(lblProductName);

		lblProductId = new JLabel("Product ID");
		lblProductId.setBounds(10, 64, 86, 14);
		contentPane.add(lblProductId);

		cmbProductName = new JComboBox();
		cmbProductName.setBounds(140, 89, 147, 20);
		contentPane.add(cmbProductName);
		for (int i = 0; i < products.size(); i++) {
			cmbProductName.addItem(products.get(i).getTitle());
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
		cmbProductID.setBounds(10, 89, 120, 20);
		contentPane.add(cmbProductID);
		for (int i = 0; i < products.size(); i++) {
			cmbProductID.addItem(products.get(i).getId());
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

		btnGoBackSaleInvoice.setBounds(1250, 17, 89, 30);
		contentPane.add(btnGoBackSaleInvoice);

		lblMeasuringUnit = new JLabel("Measuring Unit");
		lblMeasuringUnit.setBounds(10, 120, 89, 14);
		contentPane.add(lblMeasuringUnit);

		txtMeasuringUnit = new JTextField();
		txtMeasuringUnit.setEditable(false);
		txtMeasuringUnit.setBounds(10, 145, 120, 20);
		contentPane.add(txtMeasuringUnit);
		txtMeasuringUnit.setColumns(10);

		lblUnitPrice = new JLabel("Unit Price");
		lblUnitPrice.setBounds(10, 176, 89, 14);
		contentPane.add(lblUnitPrice);

		txtUnitPrice = new JTextField();
		txtUnitPrice.setEditable(false);
		txtUnitPrice.setColumns(10);
		txtUnitPrice.setBounds(10, 201, 120, 20);
		contentPane.add(txtUnitPrice);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(140, 120, 1199, 300);
		contentPane.add(scrollPane);

		table = new JTable(tableModel);
		scrollPane.setViewportView(table);

		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setBounds(298, 64, 89, 14);
		contentPane.add(lblQuantity);

		txtQuantity = new JTextField();
		txtQuantity.setText((String) null);
		txtQuantity.setColumns(10);
		txtQuantity.setBounds(297, 89, 120, 20);
		contentPane.add(txtQuantity);

		JButton btnAddItem = new JButton("Add Item!");
		btnAddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Object[] item = invoiceController.addItemToInvoice(selectedProduct,
						Integer.parseInt(txtQuantity.getText()), invoice);

				tableModel.addRow(item);
			}
		});
		btnAddItem.setBounds(427, 88, 89, 23);
		contentPane.add(btnAddItem);

	}

	public void Initializations() {
		saleInvoiceDao = new ProductDao();
		products = new ArrayList<ProductModel>();
		selectedProduct = new ProductModel();
		invoice = new InvoiceModel();
		items = new ArrayList<InvoiceItemModel>();

		invoice.setInvoiceItems(items);

		tableModel = new DefaultTableModel(header, 0);
	}

	public void getData() {
		try {
			ResultSet rs = saleInvoiceDao.getProducts();

			ProductModel product = new ProductModel();

			while (rs.next()) {

				product.setId(Integer.parseInt(rs.getString("id")));
				product.setTitle(rs.getString("title"));
				product.setDescription(rs.getString("description"));
				product.setCategory(rs.getString("category"));
				product.setUnit(rs.getString("unit"));
				product.setSalePrice(Float.parseFloat(rs.getString("salePrice")));
				product.setPurchasePrice(Float.parseFloat(rs.getString("purchasePrice")));
				product.setCreatedBy(Integer.parseInt(rs.getString("createdBy")));
				product.setCreatedDate(Timestamp.valueOf(rs.getString("createdDate")));
				product.setStatus(rs.getBoolean("status"));

				products.add(product);
				product = new ProductModel();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
