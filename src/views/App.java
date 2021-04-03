package views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dataModels.CustomerModel;
import dataModels.ProductModel;
import dataModels.UserModel;
import viewModels.AddCustomerVM;
import viewModels.AddProductVM;

import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

import controllers.CustomerController;
import controllers.ProductController;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JCheckBox;
import javax.swing.table.TableModel;

public class App extends JFrame {

	private JPanel contentPane;

	private JLayeredPane layeredPane;

	// Models
	private ArrayList<ProductModel> products;
	private ArrayList<CustomerModel> customers;

	// Home
	JPanel Home;

	// Add Product
	private JPanel Product, Customer;
	JTextField txtID, txtTitle, txtCategory, txtPurchasePrice, txtSalePrice, txtUnit, txtStock, txtDescription;
	JCheckBox cbStatus;
	private ProductModel selectedProduct;
	private JTable productTable;
	private AddProductVM productForm;

	// Add Customer
	private JTextField txtIdCustomer, txtCustomerName, txtCustomerPhone, txtStreet, txtArea, txtCity;
	private CustomerModel selectedCustomer;
	private JTable customerTable;
	private AddCustomerVM customerForm;

	// Other
	private DefaultTableModel productsTableModel, customersTableModel;

	public void switchPanels(JPanel panel) {
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
	}

	public void InstanciateApp() {

		setTitle("Home-Point Of Sale");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1050, 550);
		setResizable(false);
		setVisible(true);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JPanel Header = new JPanel();
		Header.setBounds(10, 11, 1024, 66);
		contentPane.add(Header);
		Header.setLayout(null);

		JPanel LogoPanelHome = new JPanel();
		LogoPanelHome.setBounds(0, 0, 334, 67);
		Header.add(LogoPanelHome);
		LogoPanelHome.setBorder(null);
		LogoPanelHome.setLayout(null);

		JLabel lblLogoNameHome = new JLabel("Point Of Sale");
		lblLogoNameHome.setBounds(6, 16, 220, 49);
		LogoPanelHome.add(lblLogoNameHome);
		lblLogoNameHome.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogoNameHome.setFont(new Font("Segoe Script", Font.BOLD, 30));

		JLabel lblLogoCityNameHome = new JLabel("Dublin.");
		lblLogoCityNameHome.setBounds(236, 16, 49, 26);
		LogoPanelHome.add(lblLogoCityNameHome);
		lblLogoCityNameHome.setFont(new Font("Segoe Print", Font.PLAIN, 14));

		JPanel UserDetailsPanelHome = new JPanel();
		UserDetailsPanelHome.setBounds(669, 0, 355, 67);
		Header.add(UserDetailsPanelHome);
		UserDetailsPanelHome.setLayout(null);

		JButton btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				homeButtonClicked();
				switchPanels(Home);
			}
		});
		btnHome.setBounds(135, 33, 100, 23);
		UserDetailsPanelHome.add(btnHome);

		JButton btnLogoutHome = new JButton("Logout!");
		btnLogoutHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserModel.logout();
				dispose();
				new Login();
			}
		});
		btnLogoutHome.setBounds(245, 33, 100, 23);
		UserDetailsPanelHome.add(btnLogoutHome);

		JLabel lblActiveUserNameHome = new JLabel("User Not Found!");
		lblActiveUserNameHome.setHorizontalAlignment(SwingConstants.RIGHT);
		lblActiveUserNameHome.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblActiveUserNameHome.setBounds(135, 11, 210, 22);
		UserDetailsPanelHome.add(lblActiveUserNameHome);
		lblActiveUserNameHome.setText(UserModel.Name);

		layeredPane = new JLayeredPane();
		layeredPane.setBounds(10, 77, 1024, 433);
		contentPane.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));

		String productsHeader[] = { "Product Title", "Category", "Purchase Price", "Sale Price", "Unit", "Stock",
				"Status" };

		productsTableModel = new DefaultTableModel(productsHeader, 0) {
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		};
		products = ProductController.fillTableWithProducts(products, productsTableModel);

		String customersHeader[] = { "Name", "Phone", "Street", "Area", "City" };

		customersTableModel = new DefaultTableModel(customersHeader, 0) {
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		};

		customers = CustomerController.fillTableWithCustomers(customers, customersTableModel);

	}

	public void HomeGUI() {
		Home = new JPanel();
		Home.setBorder(null);
		layeredPane.add(Home, "name_2178593783278100");
		Home.setLayout(null);

		JPanel Navigation = new JPanel();
		Navigation.setBounds(10, 11, 200, 411);
		Home.add(Navigation);
		Navigation.setLayout(null);

		JButton btnSaleInvoiceHome = new JButton("Sale Invoice");
		btnSaleInvoiceHome.setBounds(0, 0, 200, 50);
		Navigation.add(btnSaleInvoiceHome);
		btnSaleInvoiceHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new SaleInvoice();
			}
		});

		JButton btnAddProduct = new JButton("Add New Product");
		btnAddProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchPanels(Product);
			}
		});
		btnAddProduct.setBounds(0, 49, 200, 50);
		Navigation.add(btnAddProduct);

		JButton btnAddCustomer = new JButton("Add New Customer");
		btnAddCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchPanels(Customer);
			}
		});
		btnAddCustomer.setBounds(0, 98, 200, 50);
		Navigation.add(btnAddCustomer);

		JLabel lblHome = new JLabel("Home");
		lblHome.setBounds(935, 11, 79, 31);
		Home.add(lblHome);
		lblHome.setHorizontalTextPosition(SwingConstants.LEFT);
		lblHome.setHorizontalAlignment(SwingConstants.CENTER);
		lblHome.setFont(new Font("Times New Roman", Font.BOLD, 26));
		lblHome.setToolTipText("Home Page");

	}

	public void AddProductGUI() {
		Product = new JPanel();
		layeredPane.add(Product, "name_4853073551000");
		Product.setLayout(null);

		JLabel lblSelectedProduct = new JLabel("Selected Product");
		lblSelectedProduct.setFont(new Font("Calibri", Font.PLAIN, 25));
		lblSelectedProduct.setBounds(10, 10, 180, 32);
		Product.add(lblSelectedProduct);

		JLabel lblAllProducts = new JLabel("All Products");
		lblAllProducts.setFont(new Font("Calibri", Font.PLAIN, 25));
		lblAllProducts.setBounds(352, 10, 135, 32);
		Product.add(lblAllProducts);

		JPanel ProducDetails = new JPanel();
		ProducDetails.setBounds(10, 53, 332, 369);
		Product.add(ProducDetails);
		ProducDetails.setLayout(null);

		JButton btnUpdate = new JButton("Update!");
		btnUpdate.setBounds(172, 11, 150, 23);
		ProducDetails.add(btnUpdate);

		JButton btnDelete = new JButton("Delete!");
		btnDelete.setBounds(172, 45, 150, 23);
		ProducDetails.add(btnDelete);

		JButton btnAdd = new JButton("Add!");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				productForm = new AddProductVM(txtID, txtTitle, txtCategory, txtUnit, txtPurchasePrice, txtSalePrice,
						txtStock, cbStatus, txtDescription);
				if (ProductController.validateAddProductInput(productForm)) {
					ProductController.addNewProduct(productForm);
					JOptionPane.showMessageDialog(null, "Product Added Successfully!");
					ProductController.resetFields(productForm);
				}
			}
		});
		btnAdd.setBounds(10, 11, 152, 23);
		ProducDetails.add(btnAdd);

		JLabel lblID = new JLabel("ID");
		lblID.setBounds(12, 33, 150, 14);
		ProducDetails.add(lblID);

		txtID = new JTextField();
		txtID.setEditable(false);
		txtID.setColumns(10);
		txtID.setBounds(12, 47, 150, 20);
		ProducDetails.add(txtID);

		JLabel lblTitle = new JLabel("Product Title");
		lblTitle.setBounds(12, 78, 150, 14);
		ProducDetails.add(lblTitle);

		txtTitle = new JTextField();
		txtTitle.setBounds(12, 92, 150, 20);
		ProducDetails.add(txtTitle);
		txtTitle.setColumns(10);

		JLabel lblCategory = new JLabel("Poduct Category");
		lblCategory.setBounds(172, 78, 150, 14);
		ProducDetails.add(lblCategory);

		txtCategory = new JTextField();
		txtCategory.setColumns(10);
		txtCategory.setBounds(172, 92, 150, 20);
		ProducDetails.add(txtCategory);

		JLabel lblPurchasePrice = new JLabel("Purchase Price");
		lblPurchasePrice.setBounds(12, 123, 150, 14);
		ProducDetails.add(lblPurchasePrice);

		txtPurchasePrice = new JTextField();
		txtPurchasePrice.setColumns(10);
		txtPurchasePrice.setBounds(12, 137, 150, 20);
		ProducDetails.add(txtPurchasePrice);

		JLabel lblSalePrice = new JLabel("Sale Price");
		lblSalePrice.setBounds(172, 123, 150, 14);
		ProducDetails.add(lblSalePrice);

		txtSalePrice = new JTextField();
		txtSalePrice.setColumns(10);
		txtSalePrice.setBounds(172, 137, 150, 20);
		ProducDetails.add(txtSalePrice);

		JLabel lblUnit = new JLabel("Measuring Unit");
		lblUnit.setBounds(12, 168, 150, 14);
		ProducDetails.add(lblUnit);

		txtUnit = new JTextField();
		txtUnit.setColumns(10);
		txtUnit.setBounds(12, 182, 150, 20);
		ProducDetails.add(txtUnit);

		JLabel lblStock = new JLabel("Product Stock");
		lblStock.setBounds(172, 168, 150, 14);
		ProducDetails.add(lblStock);

		txtStock = new JTextField();
		txtStock.setColumns(10);
		txtStock.setBounds(172, 182, 150, 20);
		ProducDetails.add(txtStock);

		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(12, 213, 150, 14);
		ProducDetails.add(lblDescription);

		txtDescription = new JTextField();
		txtDescription.setColumns(10);
		txtDescription.setBounds(12, 227, 310, 50);
		ProducDetails.add(txtDescription);

		cbStatus = new JCheckBox("Active");
		cbStatus.setBounds(172, 284, 150, 23);
		ProducDetails.add(cbStatus);
		cbStatus.setSelected(true);

		JLabel lblStatus = new JLabel("Status");
		lblStatus.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStatus.setBounds(12, 288, 150, 14);
		ProducDetails.add(lblStatus);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(352, 53, 662, 369);
		Product.add(scrollPane);

		productTable = new JTable(productsTableModel);
		scrollPane.setViewportView(productTable);

		productTable.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				selectedProduct = products.get(productTable.getSelectedRow());
				txtID.setText(String.valueOf(selectedProduct.getId()));
				txtTitle.setText(selectedProduct.getTitle());
				txtCategory.setText(selectedProduct.getCategory());
				txtPurchasePrice.setText(String.valueOf(selectedProduct.getPurchasePrice()));
				txtSalePrice.setText(String.valueOf(selectedProduct.getSalePrice()));
				txtUnit.setText(selectedProduct.getUnit());
				txtStock.setText(String.valueOf(selectedProduct.getStock()));
				txtDescription.setText(selectedProduct.getDescription());
				if (selectedProduct.getStatus()) {
					cbStatus.setSelected(true);
				} else {
					cbStatus.setSelected(false);
				}

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

	}

	public void CustomerGUI() {
		Customer = new JPanel();
		layeredPane.add(Customer, "name_2883570159100");
		Customer.setLayout(null);

		JLabel lblSelectedCustomer = new JLabel("Selected Customer");
		lblSelectedCustomer.setFont(new Font("Calibri", Font.PLAIN, 25));
		lblSelectedCustomer.setBounds(10, 10, 200, 32);
		Customer.add(lblSelectedCustomer);

		JLabel lblAllCustomers = new JLabel("All Customers");
		lblAllCustomers.setFont(new Font("Calibri", Font.PLAIN, 25));
		lblAllCustomers.setBounds(352, 10, 160, 32);
		Customer.add(lblAllCustomers);

		JPanel CustomerDetails = new JPanel();
		CustomerDetails.setLayout(null);
		CustomerDetails.setBounds(10, 53, 332, 369);
		Customer.add(CustomerDetails);

		JButton btnUpdateCustomer = new JButton("Update!");
		btnUpdateCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				customerForm = new AddCustomerVM(txtIdCustomer, txtCustomerName, txtCustomerPhone, txtStreet, txtArea,
						txtCity);
				if (selectedCustomer == null && customerTable.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "No Customer selected to update!");
				} else {
					boolean isUpdated = CustomerController.updateCustomer(selectedCustomer, customerForm);
					if (isUpdated) {
						JOptionPane.showMessageDialog(null,
								"Customer '" + selectedCustomer.getName() + "' updated successfully!");
						customerDefaults();
						customers = CustomerController.fillTableWithCustomers(customers, customersTableModel);
					}
				}
			}
		});
		btnUpdateCustomer.setBounds(172, 97, 150, 23);
		CustomerDetails.add(btnUpdateCustomer);

		JButton btnDeleteCustomer = new JButton("Delete!");
		btnDeleteCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				customerForm = new AddCustomerVM(txtIdCustomer, txtCustomerName, txtCustomerPhone, txtStreet, txtArea,
						txtCity);

				if (selectedCustomer == null && customerTable.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "No Customer selected to delete!");
				} else {
					boolean isDeleted = CustomerController.deleteCustomer(selectedCustomer);
					if (isDeleted) {
						JOptionPane.showMessageDialog(null,
								"Customer '" + selectedCustomer.getName() + "' deleted successfully!");
						customerDefaults();
						customers = CustomerController.fillTableWithCustomers(customers, customersTableModel);
					}

				}

			}
		});
		btnDeleteCustomer.setBounds(172, 48, 150, 23);
		CustomerDetails.add(btnDeleteCustomer);

		JButton btnAddCustomer = new JButton("Add!");
		btnAddCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				customerForm = new AddCustomerVM(txtIdCustomer, txtCustomerName, txtCustomerPhone, txtStreet, txtArea,
						txtCity);
				if (CustomerController.validateAddCustomerInput(customerForm)) {
					CustomerController.addNewCustomer(customerForm);
					customers = CustomerController.fillTableWithCustomers(customers, customersTableModel);
				}
			}
		});
		btnAddCustomer.setBounds(14, 48, 152, 23);
		CustomerDetails.add(btnAddCustomer);

		JLabel lblIdCustomer = new JLabel("ID");
		lblIdCustomer.setBounds(14, 82, 150, 14);
		CustomerDetails.add(lblIdCustomer);

		txtIdCustomer = new JTextField();
		txtIdCustomer.setEditable(false);
		txtIdCustomer.setColumns(10);
		txtIdCustomer.setBounds(14, 98, 150, 20);
		CustomerDetails.add(txtIdCustomer);

		JLabel lblCustomerName = new JLabel("Customer Name");
		lblCustomerName.setBounds(14, 129, 150, 14);
		CustomerDetails.add(lblCustomerName);

		txtCustomerName = new JTextField();
		txtCustomerName.setColumns(10);
		txtCustomerName.setBounds(14, 143, 150, 20);
		CustomerDetails.add(txtCustomerName);

		JLabel lblCustomerPhone = new JLabel("Customer Phone");
		lblCustomerPhone.setBounds(174, 129, 150, 14);
		CustomerDetails.add(lblCustomerPhone);

		txtCustomerPhone = new JTextField();
		txtCustomerPhone.setColumns(10);
		txtCustomerPhone.setBounds(174, 143, 150, 20);
		CustomerDetails.add(txtCustomerPhone);

		JLabel lblStreet = new JLabel("Street");
		lblStreet.setBounds(14, 174, 150, 14);
		CustomerDetails.add(lblStreet);

		txtStreet = new JTextField();
		txtStreet.setColumns(10);
		txtStreet.setBounds(14, 188, 310, 20);
		CustomerDetails.add(txtStreet);

		JLabel lblArea = new JLabel("Area");
		lblArea.setBounds(14, 219, 150, 14);
		CustomerDetails.add(lblArea);

		txtArea = new JTextField();
		txtArea.setColumns(10);
		txtArea.setBounds(14, 233, 310, 20);
		CustomerDetails.add(txtArea);

		JLabel lblCity = new JLabel("City");
		lblCity.setBounds(94, 264, 150, 14);
		CustomerDetails.add(lblCity);

		txtCity = new JTextField();
		txtCity.setColumns(10);
		txtCity.setBounds(94, 278, 150, 20);
		CustomerDetails.add(txtCity);

		JScrollPane scrollPaneCustomers = new JScrollPane();
		scrollPaneCustomers.setBounds(352, 53, 662, 369);
		Customer.add(scrollPaneCustomers);

		customerTable = new JTable(customersTableModel);
		scrollPaneCustomers.setViewportView(customerTable);

		customerTable.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				selectedCustomer = customers.get(customerTable.getSelectedRow());

				txtIdCustomer.setText(String.valueOf(selectedCustomer.getId()));
				txtCustomerName.setText(selectedCustomer.getName());
				txtCustomerPhone.setText(selectedCustomer.getPhone());
				txtStreet.setText(selectedCustomer.getStreet());
				txtArea.setText(selectedCustomer.getArea());
				txtCity.setText(selectedCustomer.getCity());
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

	public App() {
		InstanciateApp();
		HomeGUI();
		AddProductGUI();
		CustomerGUI();
	}

	public void homeButtonClicked() {

		productDefaults();
		customerDefaults();
	}

	public void productDefaults() {
		productForm = new AddProductVM(txtID, txtTitle, txtCategory, txtUnit, txtPurchasePrice, txtSalePrice, txtStock,
				cbStatus, txtDescription);
		ProductController.resetFields(productForm);
		selectedProduct = null;
		productTable.clearSelection();
	}

	public void customerDefaults() {
		customerForm = new AddCustomerVM(txtIdCustomer, txtCustomerName, txtCustomerPhone, txtStreet, txtArea, txtCity);
		CustomerController.resetFields(customerForm);
		selectedCustomer = null;
		customerTable.clearSelection();
	}
}
