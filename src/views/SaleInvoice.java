package views;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Helpers.Frame;
import Helpers.InputValidation;
import controllers.InvoiceController;
import dataAccess.ProductDao;
import dataModels.InvoiceItemModel;
import dataModels.InvoiceModel;
import dataModels.ProductModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;
import viewModels.AddItemVM;

import java.awt.Dialog.ModalExclusionType;
import java.awt.Window.Type;
import javax.swing.SwingConstants;

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
	private InvoiceModel invoice;
	private ArrayList<InvoiceItemModel> items;

	String header[] = { "#", "Product", "Unit", "Unit Price", "Quantity", "Sub-Total" };

	private JTextField txtQuantity;
	private JTextField txtCustomerMobile;
	private JTextField txtCustomerName;

	public SaleInvoice() {
		setMaximizedBounds(Frame.getScreenBounds());
		setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
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
		txtUnitPrice.setText(String.valueOf(selectedProduct.getSalePrice()));
		txtMeasuringUnit.setText(selectedProduct.getUnit());
	}

	public void setUI() {
		setTitle("Sale Invoice");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		setVisible(true);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, screenSize.width, screenSize.height);

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblSaleInvoice = new JLabel("Sale Invoice");
		lblSaleInvoice.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblSaleInvoice.setBounds(10, 11, 156, 30);
		contentPane.add(lblSaleInvoice);

		lblProductName = new JLabel("Product Name");
		lblProductName.setBounds(142, 65, 102, 14);
		contentPane.add(lblProductName);

		lblProductId = new JLabel("Product ID");
		lblProductId.setBounds(10, 65, 86, 14);
		contentPane.add(lblProductId);

		cmbProductName = new JComboBox();
		cmbProductName.setMaximumRowCount(5);
		cmbProductName.setToolTipText("Select Product By Name.....");
		cmbProductName.setBounds(138, 78, 250, 30);
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
		cmbProductID.setToolTipText("Select Product By Code");
		cmbProductID.setMaximumRowCount(5);
		cmbProductID.setBounds(6, 78, 120, 30);
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

		btnGoBackSaleInvoice.setBounds(1253, 11, 86, 68);
		contentPane.add(btnGoBackSaleInvoice);

		lblMeasuringUnit = new JLabel("Measuring Unit");
		lblMeasuringUnit.setBounds(14, 175, 89, 14);
		contentPane.add(lblMeasuringUnit);

		txtMeasuringUnit = new JTextField();
		txtMeasuringUnit.setHorizontalAlignment(SwingConstants.RIGHT);
		txtMeasuringUnit.setToolTipText("Measuring Unit of the Selected Product.....");
		txtMeasuringUnit.setEditable(false);
		txtMeasuringUnit.setBounds(10, 190, 120, 30);
		contentPane.add(txtMeasuringUnit);
		txtMeasuringUnit.setColumns(10);

		lblUnitPrice = new JLabel("Unit Price");
		lblUnitPrice.setBounds(14, 120, 89, 14);
		contentPane.add(lblUnitPrice);

		txtUnitPrice = new JTextField();
		txtUnitPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		txtUnitPrice.setEditable(false);
		txtUnitPrice.setColumns(10);
		txtUnitPrice.setBounds(10, 133, 120, 30);
		contentPane.add(txtUnitPrice);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(140, 120, 1199, 300);
		contentPane.add(scrollPane);

		table = new JTable(tableModel);
		scrollPane.setViewportView(table);

		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setBounds(402, 65, 89, 14);
		contentPane.add(lblQuantity);

		txtQuantity = new JTextField();
		txtQuantity.setToolTipText("Enter quantity of the item here....");
		txtQuantity.setText((String) null);
		txtQuantity.setColumns(10);
		txtQuantity.setBounds(400, 78, 120, 30);
		contentPane.add(txtQuantity);

		JButton btnAddItem = new JButton("Add Item!");
		btnAddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (InvoiceController.validateQuantity(txtQuantity)) {
					InvoiceController.addItemToInvoice(new AddItemVM(selectedProduct, Integer.parseInt(txtQuantity.getText()), invoice, tableModel));
				}

				for (InvoiceItemModel item : invoice.getInvoiceItems()) {
					System.out.println(" ID:" + item.getId() + " InvoiceId:" + item.getInvoiceId() + " ProductId:"
							+ item.getProductId() + " Title:" + item.getTitle() + " SalePrice:" + item.getSalePrice()
							+ " Quantity:" + item.getQuantity() + " InvoiceId:" + item.getSubTotal());
				}
			}

		});
		btnAddItem.setBounds(10, 232, 120, 30);
		contentPane.add(btnAddItem);

		txtCustomerMobile = new JTextField();
		txtCustomerMobile.setToolTipText("Enter Customer Mobile Here.....");
		txtCustomerMobile.setText((String) null);
		txtCustomerMobile.setEditable(false);
		txtCustomerMobile.setColumns(10);
		txtCustomerMobile.setBounds(744, 78, 150, 30);
		contentPane.add(txtCustomerMobile);

		JLabel lblCustomerMobile = new JLabel("Customer Mobile");
		lblCustomerMobile.setBounds(748, 65, 120, 14);
		contentPane.add(lblCustomerMobile);

		JLabel lblCustomerName = new JLabel("Customer Name");
		lblCustomerName.setBounds(535, 65, 117, 14);
		contentPane.add(lblCustomerName);

		txtCustomerName = new JTextField();
		txtCustomerName.setToolTipText("Enter Customer Name Here....");
		txtCustomerName.setEditable(false);
		txtCustomerName.setColumns(10);
		txtCustomerName.setBounds(532, 78, 200, 30);
		contentPane.add(txtCustomerName);

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
