package views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import dataModels.CustomerModel;
import dataModels.ExpenseModel;
import dataModels.InvoiceModel;
import dataModels.ProductModel;
import dataModels.PurchasesModel;
import dataModels.User;
import dataModels.UserModel;
import viewModels.AddCustomerVM;
import viewModels.AddExpenseVM;
import viewModels.AddProductVM;
import viewModels.AddPurchaseVM;
import viewModels.AddUserVM;

import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Array;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

import controllers.CustomerController;
import controllers.ExpenseController;
import controllers.ProductController;
import controllers.PurchasesController;
import controllers.SalesController;
import controllers.SyncController;
import controllers.UserController;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JCheckBox;
import javax.swing.table.TableModel;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;

public class App extends JFrame {

	private JPanel contentPane;
	private JLayeredPane layeredPane;

	// Models
	ArrayList<JFrame> invoiceWindows = new ArrayList<JFrame>();
	private ArrayList<ProductModel> products;
	private ArrayList<CustomerModel> customers;
	private ArrayList<PurchasesModel> purchases;
	private ArrayList<ExpenseModel> expenses;
	private ArrayList<UserModel> users;
	private ArrayList<InvoiceModel> sales;
	private ArrayList<InvoiceModel> filteredSales;

	// Home
	JPanel Home;
	JLabel lblTime;

	// Add Product
	private JPanel Product, Customer;
	JTextField txtTitle, txtUnit, txtDescription;
	JCheckBox cbStatus;
	private ProductModel selectedProduct;
	private JTable productTable;
	private AddProductVM productForm;

	// Add Customer
	private JTextField txtCustomerName, txtCustomerPhone;
	private CustomerModel selectedCustomer;
	private JTable customerTable;
	private AddCustomerVM customerForm;

	// Purchases
	private JPanel Purchases;
	private JTextField txtSupplierPurchase, txtPurchasePricePurchase, txtSalePricePurchase, txtUnitPurchase,
			txtQuantityPurchase;
	private JTable purchasesTable;
	private AddPurchaseVM purchaseForm;
	private JComboBox cmbProductsPurchases;

	// Expenses
	private JPanel Expenses;
	private AddExpenseVM expenseForm;
	private JTextField txtAmountExpense;
	private JTextField txtDescriptionExpense;
	private JTable expensesTable;
	private ExpenseModel selectedExpense;
	private PurchasesModel selectedPurchase;

	// Other
	private DefaultTableModel productsTableModel, customersTableModel, purchasesTableModel, expensesTableModel,
			userTableModel, salesTableModel;

	// Users
	JPanel Users;
	private JTextField txtUserId, txtNameUser, txtUsername;
	private JPasswordField txtPassword;
	private JTable usersTable;
	private UserModel selectedUser;
	private AddUserVM userForm;
	private JCheckBox chkboxIsAdmin;
	private JTable salesTable;

	// Sasles
	JPanel Sales;
	private JTextField txtSearchSales;
	private JTextField txtSalePrice;
	private InvoiceModel selectedInvoice;

	public void switchPanels(JPanel panel) {
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
	}

	public void InstanciateApp() {

		setTitle("Home-Point Of Sale");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 931, 550);
		setResizable(false);
		setVisible(true);
		
		System.out.println(new Timestamp(new Date().getTime()));
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JPanel Header = new JPanel();
		Header.setBounds(10, 11, 904, 66);
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

		JPanel UserDetailsPanelHome = new JPanel();
		UserDetailsPanelHome.setBounds(549, 0, 355, 67);
		Header.add(UserDetailsPanelHome);
		UserDetailsPanelHome.setLayout(null);

		JButton btnHome = new JButton("Home");
		btnHome.setFont(new Font("Cambria Math", Font.PLAIN, 14));
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				homeButtonClicked();
				switchPanels(Home);
			}
		});
		btnHome.setBounds(127, 21, 100, 23);
		UserDetailsPanelHome.add(btnHome);

		JButton btnLogoutHome = new JButton("Logout!");
		btnLogoutHome.setFont(new Font("Cambria Math", Font.PLAIN, 14));
		btnLogoutHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User.logout();
				dispose();
				for (int i = 0; i < invoiceWindows.size(); i++) {
					invoiceWindows.get(i).dispose();
				}
				new Login();
			}
		});
		btnLogoutHome.setBounds(245, 21, 100, 23);
		UserDetailsPanelHome.add(btnLogoutHome);

		JButton btnAddUser = new JButton("Users");
		btnAddUser.setFont(new Font("Cambria Math", Font.PLAIN, 14));
		btnAddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(Users);
				users = UserController.fillTableWithUsers(users, userTableModel);
			}
		});
		btnAddUser.setBounds(25, 21, 100, 23);
		UserDetailsPanelHome.add(btnAddUser);
		if (!User.IsAdmin) {
			btnAddUser.setVisible(false);
		}

		layeredPane = new JLayeredPane();
		layeredPane.setBounds(10, 77, 904, 433);
		contentPane.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));

		String productsHeader[] = { "Product Title", "Purchase Price", "Sale Price", "Unit", "Stock", "Status" };

		productsTableModel = new DefaultTableModel(productsHeader, 0) {
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		};
		products = ProductController.fillTableWithProducts(products, productsTableModel);

		String customersHeader[] = { "Name", "Phone" };

		customersTableModel = new DefaultTableModel(customersHeader, 0) {
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		};

		customers = CustomerController.fillTableWithCustomers(customers, customersTableModel);

		String purchasesHeader[] = { "Title", "PurchasePrice", "Sale Price", "Quantity", "Unit", "Supplier", "By" };

		purchasesTableModel = new DefaultTableModel(purchasesHeader, 0) {
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		};
		purchases = PurchasesController.fillTableWithPurchases(purchases, purchasesTableModel);

		String expensesHeader[] = { "Amount", "Description", "By" };

		expensesTableModel = new DefaultTableModel(expensesHeader, 0) {
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		};
		expenses = ExpenseController.fillTableWithExpenses(expenses, expensesTableModel);

		String usersHeader[] = { "Name", "Username", "IsAdmin" };

		userTableModel = new DefaultTableModel(usersHeader, 0) {
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		};
		users = UserController.fillTableWithUsers(users, userTableModel);

		String salesHeader[] = { "Invoice Number", "Customer", "Discount Percent", "Total" };

		salesTableModel = new DefaultTableModel(salesHeader, 0) {
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		};
		sales = SalesController.fillTableWithSales(sales, salesTableModel);

		filteredSales = sales;
	}

	public void HomeGUI() {
		Home = new JPanel();
		Home.setBorder(null);
		layeredPane.add(Home, "name_2178593783278100");
		Home.setLayout(null);

		JPanel Navigation = new JPanel();
		Navigation.setBounds(10, 11, 201, 411);
		Home.add(Navigation);
		Navigation.setLayout(null);

		JButton btnSaleInvoiceHome = new JButton("Sale Invoice");
		btnSaleInvoiceHome.setFont(new Font("Cambria Math", Font.BOLD, 26));
		btnSaleInvoiceHome.setBounds(0, 0, 200, 70);
		Navigation.add(btnSaleInvoiceHome);
		btnSaleInvoiceHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (customers.size() < 1) {
					JOptionPane.showMessageDialog(null, "No Customer Found. Add customer to generate invoice!");
				} else if (products.size() < 1) {
					JOptionPane.showMessageDialog(null, "No Products Found. Add products to generate invoice!");
				} else {
					JFrame frame = new SaleInvoice();
					invoiceWindows.add(frame);
				}
			}
		});

		JButton btnProducts = new JButton("Products");
		btnProducts.setFont(new Font("Cambria Math", Font.BOLD, 26));
		btnProducts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				products = ProductController.fillTableWithProducts(products, productsTableModel);
				switchPanels(Product);
			}
		});
		btnProducts.setBounds(0, 271, 200, 70);
		Navigation.add(btnProducts);

		JButton btnCustomers = new JButton("Customers");
		btnCustomers.setFont(new Font("Cambria Math", Font.BOLD, 26));
		btnCustomers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchPanels(Customer);
			}
		});
		btnCustomers.setBounds(0, 203, 200, 70);
		Navigation.add(btnCustomers);

		JButton btnPurchasesHome = new JButton("Purchases");
		btnPurchasesHome.setFont(new Font("Cambria Math", Font.BOLD, 26));
		btnPurchasesHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				products = ProductController.fillTableWithProducts(products, productsTableModel);
				switchPanels(Purchases);
			}
		});
		btnPurchasesHome.setBounds(0, 67, 200, 70);
		Navigation.add(btnPurchasesHome);

		JButton btnExpensesHome = new JButton("Expenses");
		btnExpensesHome.setFont(new Font("Cambria Math", Font.BOLD, 26));
		btnExpensesHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(Expenses);
			}
		});
		btnExpensesHome.setBounds(0, 339, 200, 70);
		Navigation.add(btnExpensesHome);

		JButton btnAllSalesHome = new JButton("All Sales");
		btnAllSalesHome.setFont(new Font("Cambria Math", Font.BOLD, 26));
		btnAllSalesHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sales = SalesController.fillTableWithSales(sales, salesTableModel);
				switchPanels(Sales);

				filteredSales = sales;
			}
		});
		btnAllSalesHome.setBounds(0, 135, 200, 70);
		Navigation.add(btnAllSalesHome);

		JLabel lblHome = new JLabel("Home");
		lblHome.setBounds(784, 11, 113, 38);
		Home.add(lblHome);
		lblHome.setHorizontalTextPosition(SwingConstants.LEFT);
		lblHome.setHorizontalAlignment(SwingConstants.CENTER);
		lblHome.setFont(new Font("Cambria Math", Font.BOLD, 32));
		lblHome.setToolTipText("Home Page");

		JLabel lblCurrentShiftStarted = new JLabel("Current Shift Started At ");
		lblCurrentShiftStarted.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCurrentShiftStarted.setFont(new Font("Cambria Math", Font.BOLD, 16));
		lblCurrentShiftStarted.setBounds(696, 116, 201, 26);
		Home.add(lblCurrentShiftStarted);

		lblTime = new JLabel("time");
		lblTime.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTime.setVerticalAlignment(SwingConstants.BOTTOM);
		lblTime.setFont(new Font("Cambria Math", Font.PLAIN, 20));
		lblTime.setBounds(662, 141, 235, 26);
		Home.add(lblTime);
		

		lblTime.setText((new Timestamp(new Date().getTime())).toLocaleString());
		
				JLabel lblActiveUserNameHome = new JLabel("User Not Found!");
				lblActiveUserNameHome.setBounds(687, 93, 210, 22);
				Home.add(lblActiveUserNameHome);
				lblActiveUserNameHome.setHorizontalAlignment(SwingConstants.RIGHT);
				lblActiveUserNameHome.setFont(new Font("Cambria Math", Font.PLAIN, 18));
				lblActiveUserNameHome.setText(User.Name);
				
				JLabel lblCurrentUser = new JLabel("Current User");
				lblCurrentUser.setHorizontalAlignment(SwingConstants.RIGHT);
				lblCurrentUser.setFont(new Font("Cambria Math", Font.BOLD, 16));
				lblCurrentUser.setBounds(696, 66, 201, 26);
				Home.add(lblCurrentUser);
				
						JButton btnSyncData = new JButton("Sync Data");
						btnSyncData.setBounds(672, 24, 102, 23);
						Home.add(btnSyncData);
						btnSyncData.setFont(new Font("Cambria Math", Font.PLAIN, 14));
						btnSyncData.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								JOptionPane.showMessageDialog(null, "Syncing!....");
								SyncController.Sync();
								JOptionPane.showMessageDialog(null, "Syncing Done!....");
							}
						});

	}

	public void AddProductGUI() {
		Product = new JPanel();
		layeredPane.add(Product, "name_4853073551000");
		Product.setLayout(null);

		JPanel ProducDetails = new JPanel();
		ProducDetails.setBounds(113, 53, 666, 107);
		Product.add(ProducDetails);
		ProducDetails.setLayout(null);

		JButton btnUpdateProduct = new JButton("Update!");
		btnUpdateProduct.setFont(new Font("Cambria Math", Font.PLAIN, 16));
		btnUpdateProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				productForm = new AddProductVM(txtTitle, txtUnit, txtSalePrice, cbStatus, txtDescription);
				if (selectedProduct == null && productTable.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "No Product is selected to update!");
				} else {
					boolean isUpdated = ProductController.updateProduct(selectedProduct, productForm);
					if (isUpdated) {
						JOptionPane.showMessageDialog(null,
								"Product '" + selectedProduct.getTitle() + "' updated successfully!");
						productDefaults();
						products = ProductController.fillTableWithProducts(products, productsTableModel);
					}
				}
			}
		});
		btnUpdateProduct.setBounds(505, 40, 150, 23);
		ProducDetails.add(btnUpdateProduct);

		JButton btnDeleteProduct = new JButton("Delete!");
		btnDeleteProduct.setFont(new Font("Cambria Math", Font.PLAIN, 16));
		btnDeleteProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				productForm = new AddProductVM(txtTitle, txtUnit, txtSalePrice, cbStatus, txtDescription);

				if (selectedProduct == null && productTable.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "No Product is selected to delete!");
				} else {
					boolean isDeleted = ProductController.deleteProduct(selectedProduct);
					if (isDeleted) {
						JOptionPane.showMessageDialog(null,
								"Product '" + selectedProduct.getTitle() + "' deleted successfully!");
						productDefaults();
						products = ProductController.fillTableWithProducts(products, productsTableModel);
					}

				}
			}
		});
		btnDeleteProduct.setBounds(505, 70, 150, 23);
		ProducDetails.add(btnDeleteProduct);

		JButton btnAddProduct = new JButton("Add!");
		btnAddProduct.setFont(new Font("Cambria Math", Font.PLAIN, 16));
		btnAddProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				productForm = new AddProductVM(txtTitle, txtUnit, txtSalePrice, cbStatus, txtDescription);
				if (ProductController.validateAddProductInput(productForm)) {
					ProductController.addNewProduct(productForm);
					products = ProductController.fillTableWithProducts(products, productsTableModel);
				}
			}
		});

		btnAddProduct.setBounds(505, 11, 150, 23);
		ProducDetails.add(btnAddProduct);

		JLabel lblTitle = new JLabel("Product Title");
		lblTitle.setFont(new Font("Cambria Math", Font.PLAIN, 14));
		lblTitle.setBounds(10, 11, 150, 14);
		ProducDetails.add(lblTitle);

		txtTitle = new JTextField();
		txtTitle.setFont(new Font("Cambria Math", Font.PLAIN, 14));
		txtTitle.setBounds(10, 25, 150, 20);
		ProducDetails.add(txtTitle);
		txtTitle.setColumns(10);

		JLabel lblUnit = new JLabel("Measuring Unit");
		lblUnit.setFont(new Font("Cambria Math", Font.PLAIN, 14));
		lblUnit.setBounds(170, 11, 150, 14);
		ProducDetails.add(lblUnit);

		txtUnit = new JTextField();
		txtUnit.setFont(new Font("Cambria Math", Font.PLAIN, 14));
		txtUnit.setColumns(10);
		txtUnit.setBounds(170, 25, 150, 20);
		ProducDetails.add(txtUnit);

		JLabel lblDescription = new JLabel("Description");
		lblDescription.setFont(new Font("Cambria Math", Font.PLAIN, 14));
		lblDescription.setBounds(10, 56, 310, 14);
		ProducDetails.add(lblDescription);

		txtDescription = new JTextField();
		txtDescription.setFont(new Font("Cambria Math", Font.PLAIN, 14));
		txtDescription.setColumns(10);
		txtDescription.setBounds(10, 70, 310, 23);
		ProducDetails.add(txtDescription);

		cbStatus = new JCheckBox("Active");
		cbStatus.setFont(new Font("Cambria Math", Font.PLAIN, 14));
		cbStatus.setBounds(326, 77, 150, 13);
		ProducDetails.add(cbStatus);
		cbStatus.setSelected(true);

		JLabel lblStatus = new JLabel("Status");
		lblStatus.setFont(new Font("Cambria Math", Font.PLAIN, 14));
		lblStatus.setHorizontalAlignment(SwingConstants.LEFT);
		lblStatus.setBounds(330, 56, 146, 14);
		ProducDetails.add(lblStatus);

		txtSalePrice = new JTextField();
		txtSalePrice.setFont(new Font("Cambria Math", Font.PLAIN, 14));
		txtSalePrice.setColumns(10);
		txtSalePrice.setBounds(330, 25, 150, 20);
		ProducDetails.add(txtSalePrice);

		JLabel lblSalePrice = new JLabel("Sale Price");
		lblSalePrice.setFont(new Font("Cambria Math", Font.PLAIN, 14));
		lblSalePrice.setBounds(330, 11, 150, 14);
		ProducDetails.add(lblSalePrice);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 171, 884, 251);
		Product.add(scrollPane);

		productTable = new JTable(productsTableModel);
		scrollPane.setViewportView(productTable);

		JLabel lblProducts = new JLabel("Products");
		lblProducts.setFont(new Font("Cambria Math", Font.BOLD, 32));
		lblProducts.setBounds(10, 11, 140, 42);
		Product.add(lblProducts);
		productTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		productTable.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				selectedProduct = products.get(productTable.getSelectedRow());
				txtTitle.setText(selectedProduct.getTitle());
				txtUnit.setText(selectedProduct.getUnit());
				txtDescription.setText(selectedProduct.getDescription());
				txtSalePrice.setText(String.valueOf(selectedProduct.getSalePrice()));
				if (selectedProduct.getStatus()) {
					cbStatus.setSelected(true);
				} else {
					cbStatus.setSelected(false);
				}
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				selectedProduct = products.get(productTable.getSelectedRow());
				txtTitle.setText(selectedProduct.getTitle());
				txtUnit.setText(selectedProduct.getUnit());
				txtDescription.setText(selectedProduct.getDescription());
				txtSalePrice.setText(String.valueOf(selectedProduct.getSalePrice()));
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

		JPanel CustomerDetails = new JPanel();
		CustomerDetails.setLayout(null);
		CustomerDetails.setBounds(117, 43, 662, 90);
		Customer.add(CustomerDetails);

		JButton btnUpdateCustomer = new JButton("Update!");
		btnUpdateCustomer.setFont(new Font("Cambria Math", Font.BOLD, 14));
		btnUpdateCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				customerForm = new AddCustomerVM(txtCustomerName, txtCustomerPhone);
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
		btnUpdateCustomer.setBounds(264, 47, 150, 32);
		CustomerDetails.add(btnUpdateCustomer);

		JButton btnDeleteCustomer = new JButton("Delete!");
		btnDeleteCustomer.setFont(new Font("Cambria Math", Font.BOLD, 14));
		btnDeleteCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				customerForm = new AddCustomerVM(txtCustomerName, txtCustomerPhone);

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
		btnDeleteCustomer.setBounds(424, 25, 150, 54);
		CustomerDetails.add(btnDeleteCustomer);

		JButton btnAddCustomer = new JButton("Add!");
		btnAddCustomer.setFont(new Font("Cambria Math", Font.BOLD, 14));
		btnAddCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				customerForm = new AddCustomerVM(txtCustomerName, txtCustomerPhone);
				if (CustomerController.validateAddCustomerInput(customerForm)) {
					CustomerController.addNewCustomer(customerForm);
					customers = CustomerController.fillTableWithCustomers(customers, customersTableModel);
				}
			}
		});
		btnAddCustomer.setBounds(102, 47, 152, 32);
		CustomerDetails.add(btnAddCustomer);

		JLabel lblCustomerName = new JLabel("Customer Name");
		lblCustomerName.setFont(new Font("Cambria Math", Font.PLAIN, 14));
		lblCustomerName.setBounds(102, 11, 150, 14);
		CustomerDetails.add(lblCustomerName);

		txtCustomerName = new JTextField();
		txtCustomerName.setFont(new Font("Cambria Math", Font.PLAIN, 12));
		txtCustomerName.setColumns(10);
		txtCustomerName.setBounds(102, 25, 150, 20);
		CustomerDetails.add(txtCustomerName);

		JLabel lblCustomerPhone = new JLabel("Customer Phone");
		lblCustomerPhone.setFont(new Font("Cambria Math", Font.PLAIN, 14));
		lblCustomerPhone.setBounds(264, 11, 150, 14);
		CustomerDetails.add(lblCustomerPhone);

		txtCustomerPhone = new JTextField();
		txtCustomerPhone.setFont(new Font("Cambria Math", Font.PLAIN, 12));
		txtCustomerPhone.setColumns(10);
		txtCustomerPhone.setBounds(264, 25, 150, 20);
		CustomerDetails.add(txtCustomerPhone);

		JScrollPane scrollPaneCustomers = new JScrollPane();
		scrollPaneCustomers.setBounds(10, 144, 884, 278);
		Customer.add(scrollPaneCustomers);

		customerTable = new JTable(customersTableModel);
		scrollPaneCustomers.setViewportView(customerTable);

		JLabel lblCustomers = new JLabel("Customers");
		lblCustomers.setFont(new Font("Cambria Math", Font.BOLD, 33));
		lblCustomers.setBounds(359, 11, 190, 40);
		Customer.add(lblCustomers);
		customerTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		customerTable.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				selectedCustomer = customers.get(customerTable.getSelectedRow());

				txtCustomerName.setText(selectedCustomer.getName());
				txtCustomerPhone.setText(selectedCustomer.getPhone());
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				selectedCustomer = customers.get(customerTable.getSelectedRow());

				txtCustomerName.setText(selectedCustomer.getName());
				txtCustomerPhone.setText(selectedCustomer.getPhone());
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

	public void PurchasesGUI() {
		Purchases = new JPanel();
		Purchases.setLayout(null);
		layeredPane.add(Purchases, "name_2418596197522100");

		JPanel ProducDetails = new JPanel();
		ProducDetails.setLayout(null);
		ProducDetails.setBounds(131, 53, 662, 100);
		Purchases.add(ProducDetails);

		JButton btnPurchase = new JButton("Purchase");
		btnPurchase.setFont(new Font("Cambria Math", Font.BOLD, 18));
		btnPurchase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				purchaseForm = new AddPurchaseVM(cmbProductsPurchases, txtSupplierPurchase, txtPurchasePricePurchase,
						txtSalePricePurchase, txtUnitPurchase, txtQuantityPurchase);
				if (PurchasesController.validateAddPurchaseInput(purchaseForm)) {
					PurchasesController.addNewPurchase(purchaseForm);
					purchases = PurchasesController.fillTableWithPurchases(purchases, purchasesTableModel);
					products = ProductController.fillTableWithProducts(products, productsTableModel);
				}
			}
		});
		btnPurchase.setBounds(22, 56, 152, 36);
		ProducDetails.add(btnPurchase);

		JLabel lblTitlePurchase = new JLabel("Product Title");
		lblTitlePurchase.setFont(new Font("Cambria Math", Font.PLAIN, 14));
		lblTitlePurchase.setBounds(22, 11, 150, 14);
		ProducDetails.add(lblTitlePurchase);

		JLabel lblSupplierPurchase = new JLabel("Supplier");
		lblSupplierPurchase.setFont(new Font("Cambria Math", Font.PLAIN, 14));
		lblSupplierPurchase.setBounds(182, 11, 150, 14);
		ProducDetails.add(lblSupplierPurchase);

		txtSupplierPurchase = new JTextField();
		txtSupplierPurchase.setFont(new Font("Cambria Math", Font.PLAIN, 14));
		txtSupplierPurchase.setColumns(10);
		txtSupplierPurchase.setBounds(182, 25, 150, 20);
		ProducDetails.add(txtSupplierPurchase);

		JLabel lblPurchasePricePurchase = new JLabel("Purchase Price");
		lblPurchasePricePurchase.setFont(new Font("Cambria Math", Font.PLAIN, 14));
		lblPurchasePricePurchase.setBounds(342, 11, 150, 14);
		ProducDetails.add(lblPurchasePricePurchase);

		txtPurchasePricePurchase = new JTextField();
		txtPurchasePricePurchase.setFont(new Font("Cambria Math", Font.PLAIN, 14));
		txtPurchasePricePurchase.setColumns(10);
		txtPurchasePricePurchase.setBounds(342, 25, 150, 20);
		ProducDetails.add(txtPurchasePricePurchase);

		JLabel lblSalePricePurchase = new JLabel("Sale Price");
		lblSalePricePurchase.setFont(new Font("Cambria Math", Font.PLAIN, 14));
		lblSalePricePurchase.setBounds(342, 58, 150, 14);
		ProducDetails.add(lblSalePricePurchase);

		txtSalePricePurchase = new JTextField();
		txtSalePricePurchase.setFont(new Font("Cambria Math", Font.PLAIN, 14));
		txtSalePricePurchase.setColumns(10);
		txtSalePricePurchase.setBounds(342, 72, 150, 20);
		ProducDetails.add(txtSalePricePurchase);

		JLabel lblUnitPurchase = new JLabel("Measuring Unit");
		lblUnitPurchase.setFont(new Font("Cambria Math", Font.PLAIN, 14));
		lblUnitPurchase.setBounds(502, 11, 150, 14);
		ProducDetails.add(lblUnitPurchase);

		txtUnitPurchase = new JTextField();
		txtUnitPurchase.setFont(new Font("Cambria Math", Font.PLAIN, 14));
		txtUnitPurchase.setEditable(false);
		txtUnitPurchase.setColumns(10);
		txtUnitPurchase.setBounds(502, 25, 150, 20);
		ProducDetails.add(txtUnitPurchase);
		if (products.size() > 0)
			txtUnitPurchase.setText(products.get(0).getUnit());

		JLabel lblQuantityPurchase = new JLabel("Quantity");
		lblQuantityPurchase.setFont(new Font("Cambria Math", Font.PLAIN, 14));
		lblQuantityPurchase.setBounds(184, 58, 150, 14);
		ProducDetails.add(lblQuantityPurchase);

		txtQuantityPurchase = new JTextField();
		txtQuantityPurchase.setFont(new Font("Cambria Math", Font.PLAIN, 14));
		txtQuantityPurchase.setColumns(10);
		txtQuantityPurchase.setBounds(184, 72, 150, 20);
		ProducDetails.add(txtQuantityPurchase);

		cmbProductsPurchases = new JComboBox();
		cmbProductsPurchases.setFont(new Font("Cambria Math", Font.PLAIN, 14));
		cmbProductsPurchases.setBounds(22, 25, 150, 20);
		ProducDetails.add(cmbProductsPurchases);
		
		JButton btnDeletePurchase = new JButton("Delete");
		btnDeletePurchase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (selectedPurchase == null && purchasesTable.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Nothing selected to delete!");
				} else {
					boolean isDeleted = PurchasesController.deletePurchase(selectedPurchase.getId());
					if (isDeleted) {
						JOptionPane.showMessageDialog(null,
								"Deletion successfully!");
						selectedPurchase = null;
						purchasesTable.clearSelection();
						purchases = PurchasesController.fillTableWithPurchases(purchases, purchasesTableModel);
					}

				}
			}
		});
		btnDeletePurchase.setFont(new Font("Cambria Math", Font.BOLD, 18));
		btnDeletePurchase.setBounds(502, 56, 150, 36);
		ProducDetails.add(btnDeletePurchase);
		for (int i = 0; i < products.size(); i++) {
			cmbProductsPurchases.addItem(products.get(i).getTitle());
		}
		cmbProductsPurchases.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				txtUnitPurchase.setText(products.get(cmbProductsPurchases.getSelectedIndex()).getUnit());
				txtSalePricePurchase.setText(String.valueOf(products.get(cmbProductsPurchases.getSelectedIndex()).getSalePrice()));
			}
		});

		JScrollPane scrollPanePurchases = new JScrollPane();
		scrollPanePurchases.setBounds(10, 164, 884, 258);
		Purchases.add(scrollPanePurchases);

		purchasesTable = new JTable(purchasesTableModel);
		scrollPanePurchases.setViewportView(purchasesTable);
		
		purchasesTable.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				selectedPurchase = purchases.get(purchasesTable.getSelectedRow());
			}

			@Override
			public void mousePressed(MouseEvent e) {
				selectedPurchase = purchases.get(purchasesTable.getSelectedRow());
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		JLabel lblPurchases = new JLabel("Purchases");
		lblPurchases.setFont(new Font("Cambria Math", Font.BOLD, 32));
		lblPurchases.setBounds(343, 11, 185, 31);
		Purchases.add(lblPurchases);
		purchasesTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	public void ExpensesGUI() {
		Expenses = new JPanel();
		Expenses.setLayout(null);
		layeredPane.add(Expenses, "name_2466418055850600");

		JPanel ExpenseDetails = new JPanel();
		ExpenseDetails.setLayout(null);
		ExpenseDetails.setBounds(199, 53, 539, 131);
		Expenses.add(ExpenseDetails);

		JButton btnDeleteExpense = new JButton("Delete!");
		btnDeleteExpense.setFont(new Font("Cambria Math", Font.BOLD, 20));
		btnDeleteExpense.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				expenseForm = new AddExpenseVM(txtAmountExpense, txtDescriptionExpense);

				if (selectedExpense == null && expensesTable.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "No Expense selected to delete!");
				} else {
					boolean isDeleted = ExpenseController.deleteExpense(selectedExpense);
					if (isDeleted) {
						JOptionPane.showMessageDialog(null, "Expense deleted successfully!");
						expenseDefaults();
						expenses = ExpenseController.fillTableWithExpenses(expenses, expensesTableModel);
					}

				}
			}
		});
		btnDeleteExpense.setBounds(394, 11, 135, 109);
		ExpenseDetails.add(btnDeleteExpense);

		JButton btnAddExpense = new JButton("Add!");
		btnAddExpense.setFont(new Font("Cambria Math", Font.BOLD, 18));
		btnAddExpense.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				expenseForm = new AddExpenseVM(txtAmountExpense, txtDescriptionExpense);

				if (ExpenseController.validateAddExpenseInput(expenseForm)) {
					ExpenseController.addNewExpense(expenseForm);
					expenses = ExpenseController.fillTableWithExpenses(expenses, expensesTableModel);
				}
			}
		});
		btnAddExpense.setBounds(234, 11, 150, 48);
		ExpenseDetails.add(btnAddExpense);

		JLabel lblAmountExpense = new JLabel("Amount");
		lblAmountExpense.setFont(new Font("Cambria Math", Font.PLAIN, 14));
		lblAmountExpense.setBounds(10, 11, 150, 14);
		ExpenseDetails.add(lblAmountExpense);

		txtAmountExpense = new JTextField();
		txtAmountExpense.setFont(new Font("Cambria Math", Font.PLAIN, 14));
		txtAmountExpense.setColumns(10);
		txtAmountExpense.setBounds(10, 25, 214, 20);
		ExpenseDetails.add(txtAmountExpense);

		JLabel lblDescriptionExpense = new JLabel("Description");
		lblDescriptionExpense.setFont(new Font("Cambria Math", Font.PLAIN, 14));
		lblDescriptionExpense.setBounds(10, 56, 150, 14);
		ExpenseDetails.add(lblDescriptionExpense);

		txtDescriptionExpense = new JTextField();
		txtDescriptionExpense.setFont(new Font("Cambria Math", Font.PLAIN, 14));
		txtDescriptionExpense.setColumns(10);
		txtDescriptionExpense.setBounds(10, 70, 374, 50);
		ExpenseDetails.add(txtDescriptionExpense);

		JScrollPane scrollPaneExpenses = new JScrollPane();
		scrollPaneExpenses.setBounds(10, 195, 884, 227);
		Expenses.add(scrollPaneExpenses);

		expensesTable = new JTable(expensesTableModel);
		scrollPaneExpenses.setViewportView(expensesTable);

		JLabel lblExpenses = new JLabel("Expenses");
		lblExpenses.setFont(new Font("Cambria Math", Font.BOLD, 32));
		lblExpenses.setBounds(375, 11, 171, 31);
		Expenses.add(lblExpenses);
		expensesTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		expensesTable.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				selectedExpense = expenses.get(expensesTable.getSelectedRow());
				txtAmountExpense.setText(String.valueOf(selectedExpense.getAmount()));
				txtDescriptionExpense.setText(selectedExpense.getDescription());
			}

			@Override
			public void mousePressed(MouseEvent e) {
				selectedExpense = expenses.get(expensesTable.getSelectedRow());
				txtAmountExpense.setText(String.valueOf(selectedExpense.getAmount()));
				txtDescriptionExpense.setText(selectedExpense.getDescription());
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}

	public void UsersGUI() {
		Users = new JPanel();
		Users.setLayout(null);
		layeredPane.add(Users, "name_2603488046786400");

		JPanel UserDetails = new JPanel();
		UserDetails.setLayout(null);
		UserDetails.setBounds(154, 55, 586, 102);
		Users.add(UserDetails);

		JButton btnUpdateUser = new JButton("Update!");
		btnUpdateUser.setFont(new Font("Cambria Math", Font.PLAIN, 14));
		btnUpdateUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userForm = new AddUserVM(txtUserId, txtNameUser, txtUsername, txtPassword, chkboxIsAdmin);
				if (selectedUser == null && usersTable.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "No User selected to update!");
				} else {
					boolean isUpdated = UserController.updateUser(selectedUser, userForm);
					if (isUpdated) {
						JOptionPane.showMessageDialog(null,
								"User '" + selectedUser.getName() + "' updated successfully!");
						userDefaults();
						users = UserController.fillTableWithUsers(users, userTableModel);
					}
				}
			}
		});
		btnUpdateUser.setBounds(424, 41, 93, 23);
		UserDetails.add(btnUpdateUser);

		JButton btnDeleteUser = new JButton("Delete!");
		btnDeleteUser.setFont(new Font("Cambria Math", Font.PLAIN, 14));
		btnDeleteUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userForm = new AddUserVM(txtUserId, txtNameUser, txtUsername, txtPassword, chkboxIsAdmin);

				if (selectedUser == null && usersTable.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "No User selected to delete!");
				} else {
					boolean isDeleted = UserController.deleteUser(selectedUser);
					if (isDeleted) {
						JOptionPane.showMessageDialog(null, "User deleted successfully!");
						userDefaults();
						users = UserController.fillTableWithUsers(users, userTableModel);
					}

				}
			}
		});
		btnDeleteUser.setBounds(424, 73, 93, 23);
		UserDetails.add(btnDeleteUser);

		JButton btnAddUser = new JButton("Add!");
		btnAddUser.setFont(new Font("Cambria Math", Font.PLAIN, 14));
		btnAddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userForm = new AddUserVM(txtUserId, txtNameUser, txtUsername, txtPassword, chkboxIsAdmin);

				if (UserController.validateAddUserInput(userForm)) {
					UserController.addNewUser(userForm);
					users = UserController.fillTableWithUsers(users, userTableModel);
				}
			}
		});
		btnAddUser.setBounds(424, 9, 93, 23);
		UserDetails.add(btnAddUser);

		JLabel lblIdCustomer = new JLabel("ID");
		lblIdCustomer.setFont(new Font("Cambria Math", Font.PLAIN, 14));
		lblIdCustomer.setBounds(338, 60, 60, 14);
		UserDetails.add(lblIdCustomer);

		txtUserId = new JTextField();
		txtUserId.setFont(new Font("Cambria Math", Font.PLAIN, 14));
		txtUserId.setEditable(false);
		txtUserId.setColumns(10);
		txtUserId.setBounds(338, 76, 60, 20);
		UserDetails.add(txtUserId);

		JLabel lblUserName = new JLabel("Name");
		lblUserName.setFont(new Font("Cambria Math", Font.PLAIN, 14));
		lblUserName.setBounds(88, 15, 150, 14);
		UserDetails.add(lblUserName);

		txtNameUser = new JTextField();
		txtNameUser.setFont(new Font("Cambria Math", Font.PLAIN, 14));
		txtNameUser.setColumns(10);
		txtNameUser.setBounds(88, 29, 150, 20);
		UserDetails.add(txtNameUser);

		JLabel lblUsernameUser = new JLabel("Username");
		lblUsernameUser.setFont(new Font("Cambria Math", Font.PLAIN, 14));
		lblUsernameUser.setBounds(248, 15, 150, 14);
		UserDetails.add(lblUsernameUser);

		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Cambria Math", Font.PLAIN, 14));
		txtUsername.setColumns(10);
		txtUsername.setBounds(248, 29, 150, 20);
		UserDetails.add(txtUsername);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Cambria Math", Font.PLAIN, 14));
		lblPassword.setBounds(90, 60, 150, 14);
		UserDetails.add(lblPassword);

		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Cambria Math", Font.PLAIN, 12));
		txtPassword.setBounds(88, 76, 150, 20);
		UserDetails.add(txtPassword);

		chkboxIsAdmin = new JCheckBox("IsAdmin");
		chkboxIsAdmin.setFont(new Font("Cambria Math", Font.PLAIN, 14));
		chkboxIsAdmin.setBounds(248, 75, 84, 23);
		UserDetails.add(chkboxIsAdmin);

		JScrollPane scrollPaneUsers = new JScrollPane();
		scrollPaneUsers.setBounds(10, 168, 884, 254);
		Users.add(scrollPaneUsers);

		usersTable = new JTable(userTableModel);
		scrollPaneUsers.setViewportView(usersTable);

		JLabel lblUsers = new JLabel("Users");
		lblUsers.setBounds(403, 11, 100, 39);
		Users.add(lblUsers);
		lblUsers.setFont(new Font("Cambria Math", Font.BOLD, 32));
		usersTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		usersTable.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				selectedUser = users.get(usersTable.getSelectedRow());
				txtUserId.setText(String.valueOf(selectedUser.getID()));
				txtNameUser.setText(selectedUser.getName());
				txtUsername.setText(selectedUser.getUsername());
				chkboxIsAdmin.setSelected(selectedUser.isIsAdmin());
			}

			@Override
			public void mousePressed(MouseEvent e) {
				selectedUser = users.get(usersTable.getSelectedRow());
				txtUserId.setText(String.valueOf(selectedUser.getID()));
				txtNameUser.setText(selectedUser.getName());
				txtUsername.setText(selectedUser.getUsername());
				chkboxIsAdmin.setSelected(selectedUser.isIsAdmin());
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}

	public void SalesGUI() {
		Sales = new JPanel();
		Sales.setLayout(null);
		layeredPane.add(Sales, "name_2635393860615400");

		JLabel lblSalesHistory = new JLabel("Sales History");
		lblSalesHistory.setFont(new Font("Cambria Math", Font.BOLD, 32));
		lblSalesHistory.setBounds(10, 9, 260, 32);
		Sales.add(lblSalesHistory);

		JScrollPane scrollPaneSales = new JScrollPane();
		scrollPaneSales.setBounds(10, 54, 884, 369);
		Sales.add(scrollPaneSales);

		salesTable = new JTable(salesTableModel);
		scrollPaneSales.setViewportView(salesTable);
		salesTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		salesTable.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				selectedInvoice = filteredSales.get(salesTable.getSelectedRow());
			}

			@Override
			public void mousePressed(MouseEvent e) {
				selectedInvoice = filteredSales.get(salesTable.getSelectedRow());
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		txtSearchSales = new JTextField();
		txtSearchSales.setFont(new Font("Cambria Math", Font.PLAIN, 12));
		txtSearchSales.setBounds(694, 20, 200, 20);
		Sales.add(txtSearchSales);
		txtSearchSales.setColumns(10);

		JButton btnRefund = new JButton("Refund");
		btnRefund.setFont(new Font("Cambria Math", Font.PLAIN, 14));
		btnRefund.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (selectedInvoice == null && salesTable.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "No Row Selected!");
				} else {
					JFrame frame = new SaleInvoice(selectedInvoice);
					invoiceWindows.add(frame);
					switchPanels(Home);
				}

			}
		});
		btnRefund.setBounds(595, 19, 89, 23);
		Sales.add(btnRefund);

		txtSearchSales.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				filteredSales = SalesController.filterSales(txtSearchSales, sales, salesTableModel);
				selectedInvoice = null;
				salesTable.clearSelection();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				filteredSales = SalesController.filterSales(txtSearchSales, sales, salesTableModel);
				selectedInvoice = null;
				salesTable.clearSelection();
			}

			@Override
			public void changedUpdate(DocumentEvent arg0) {
			}
		});
	}

	public App() {
		InstanciateApp();
		HomeGUI();
		AddProductGUI();
		CustomerGUI();
		PurchasesGUI();
		ExpensesGUI();
		UsersGUI();
		SalesGUI();
	}

	public void homeButtonClicked() {

		productDefaults();
		customerDefaults();
		expenseDefaults();
		userDefaults();
	}

	public void productDefaults() {
		productForm = new AddProductVM(txtTitle, txtUnit, txtSalePrice, cbStatus, txtDescription);
		ProductController.resetFields(productForm);
		selectedProduct = null;
		productTable.clearSelection();
	}

	public void customerDefaults() {
		customerForm = new AddCustomerVM(txtCustomerName, txtCustomerPhone);
		CustomerController.resetFields(customerForm);
		selectedCustomer = null;
		customerTable.clearSelection();
	}

	public void expenseDefaults() {
		expenseForm = new AddExpenseVM(txtAmountExpense, txtDescriptionExpense);
		ExpenseController.resetFields(expenseForm);
		selectedExpense = null;
		expensesTable.clearSelection();
	}

	public void userDefaults() {
		userForm = new AddUserVM(txtUserId, txtNameUser, txtUsername, txtPassword, chkboxIsAdmin);
		UserController.resetFields(userForm);
		selectedUser = null;
		usersTable.clearSelection();
	}

	public void salesDefaults() {
		selectedInvoice = null;
		salesTable.clearSelection();
		txtSearchSales.setText(null);
	}
}
