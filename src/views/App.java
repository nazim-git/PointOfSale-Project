package views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dataModels.CustomerModel;
import dataModels.ExpenseModel;
import dataModels.ProductModel;
import dataModels.PurchasesModel;
import dataModels.UserModel;
import viewModels.AddCustomerVM;
import viewModels.AddExpenseVM;
import viewModels.AddProductVM;
import viewModels.AddPurchaseVM;

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
import controllers.ExpenseController;
import controllers.ProductController;
import controllers.PurchasesController;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JCheckBox;
import javax.swing.table.TableModel;
import javax.swing.JComboBox;

public class App extends JFrame {

	private JPanel contentPane;

	private JLayeredPane layeredPane;

	// Models
	private ArrayList<ProductModel> products;
	private ArrayList<CustomerModel> customers;
	private ArrayList<PurchasesModel> purchases;
	private ArrayList<ExpenseModel> expenses;

	// Home
	JPanel Home;

	// Add Product
	private JPanel Product, Customer;
	JTextField txtID, txtTitle, txtUnit, txtDescription;
	JCheckBox cbStatus;
	private ProductModel selectedProduct;
	private JTable productTable;
	private AddProductVM productForm;

	// Add Customer
	private JTextField txtIdCustomer, txtCustomerName, txtCustomerPhone;
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

	//Expenses
	private JPanel Expenses;
	private AddExpenseVM expenseForm;
	private JTextField txtAmountExpense;
	private JTextField txtDescriptionExpense;
	private JTable expensesTable;
	private ExpenseModel selectedExpense;
	
	// Other
	private DefaultTableModel productsTableModel, customersTableModel, purchasesTableModel,expensesTableModel;
	

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

		JButton btnProducts = new JButton("Products");
		btnProducts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchPanels(Product);
			}
		});
		btnProducts.setBounds(0, 48, 200, 50);
		Navigation.add(btnProducts);

		JButton btnCustomers = new JButton("Customers");
		btnCustomers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchPanels(Customer);
			}
		});
		btnCustomers.setBounds(0, 96, 200, 50);
		Navigation.add(btnCustomers);

		JButton btnPurchasesHome = new JButton("Purchases");
		btnPurchasesHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchPanels(Purchases);
			}
		});
		btnPurchasesHome.setBounds(0, 144, 200, 50);
		Navigation.add(btnPurchasesHome);
		
		JButton btnExpensesHome = new JButton("Expenses");
		btnExpensesHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(Expenses);
			}
		});
		btnExpensesHome.setBounds(0, 192, 200, 50);
		Navigation.add(btnExpensesHome);

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

		JButton btnUpdateProduct = new JButton("Update!");
		btnUpdateProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				productForm = new AddProductVM(txtID, txtTitle, txtUnit, cbStatus, txtDescription);
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
		btnUpdateProduct.setBounds(172, 65, 150, 23);
		ProducDetails.add(btnUpdateProduct);

		JButton btnDeleteProduct = new JButton("Delete!");
		btnDeleteProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				productForm = new AddProductVM(txtID, txtTitle, txtUnit, cbStatus, txtDescription);

				if (selectedProduct == null && productTable.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "No Product is selected to delete!");
				} else {
					boolean isDeleted = ProductController.deleteCustomer(selectedProduct);
					if (isDeleted) {
						JOptionPane.showMessageDialog(null,
								"Product '" + selectedProduct.getTitle() + "' deleted successfully!");
						productDefaults();
						products = ProductController.fillTableWithProducts(products, productsTableModel);
					}

				}
			}
		});
		btnDeleteProduct.setBounds(172, 99, 150, 23);
		ProducDetails.add(btnDeleteProduct);

		JButton btnAddProduct = new JButton("Add!");
		btnAddProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				productForm = new AddProductVM(txtID, txtTitle, txtUnit, cbStatus, txtDescription);
				if (ProductController.validateAddProductInput(productForm)) {
					ProductController.addNewProduct(productForm);
					products = ProductController.fillTableWithProducts(products, productsTableModel);
				}
			}
		});

		btnAddProduct.setBounds(10, 65, 152, 23);
		ProducDetails.add(btnAddProduct);

		JLabel lblID = new JLabel("ID");
		lblID.setBounds(12, 87, 150, 14);
		ProducDetails.add(lblID);

		txtID = new JTextField();
		txtID.setEditable(false);
		txtID.setColumns(10);
		txtID.setBounds(12, 101, 150, 20);
		ProducDetails.add(txtID);

		JLabel lblTitle = new JLabel("Product Title");
		lblTitle.setBounds(12, 132, 150, 14);
		ProducDetails.add(lblTitle);

		txtTitle = new JTextField();
		txtTitle.setBounds(12, 146, 150, 20);
		ProducDetails.add(txtTitle);
		txtTitle.setColumns(10);

		JLabel lblUnit = new JLabel("Measuring Unit");
		lblUnit.setBounds(172, 132, 150, 14);
		ProducDetails.add(lblUnit);

		txtUnit = new JTextField();
		txtUnit.setColumns(10);
		txtUnit.setBounds(172, 146, 150, 20);
		ProducDetails.add(txtUnit);

		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(10, 222, 150, 14);
		ProducDetails.add(lblDescription);

		txtDescription = new JTextField();
		txtDescription.setColumns(10);
		txtDescription.setBounds(10, 236, 310, 50);
		ProducDetails.add(txtDescription);

		cbStatus = new JCheckBox("Active");
		cbStatus.setBounds(172, 173, 150, 23);
		ProducDetails.add(cbStatus);
		cbStatus.setSelected(true);

		JLabel lblStatus = new JLabel("Status");
		lblStatus.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStatus.setBounds(12, 177, 150, 14);
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
				txtUnit.setText(selectedProduct.getUnit());
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
				customerForm = new AddCustomerVM(txtIdCustomer, txtCustomerName, txtCustomerPhone);
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
		btnUpdateCustomer.setBounds(168, 143, 150, 23);
		CustomerDetails.add(btnUpdateCustomer);

		JButton btnDeleteCustomer = new JButton("Delete!");
		btnDeleteCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				customerForm = new AddCustomerVM(txtIdCustomer, txtCustomerName, txtCustomerPhone);

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
		btnDeleteCustomer.setBounds(168, 94, 150, 23);
		CustomerDetails.add(btnDeleteCustomer);

		JButton btnAddCustomer = new JButton("Add!");
		btnAddCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				customerForm = new AddCustomerVM(txtIdCustomer, txtCustomerName, txtCustomerPhone);
				if (CustomerController.validateAddCustomerInput(customerForm)) {
					CustomerController.addNewCustomer(customerForm);
					customers = CustomerController.fillTableWithCustomers(customers, customersTableModel);
				}
			}
		});
		btnAddCustomer.setBounds(10, 94, 152, 23);
		CustomerDetails.add(btnAddCustomer);

		JLabel lblIdCustomer = new JLabel("ID");
		lblIdCustomer.setBounds(10, 128, 150, 14);
		CustomerDetails.add(lblIdCustomer);

		txtIdCustomer = new JTextField();
		txtIdCustomer.setEditable(false);
		txtIdCustomer.setColumns(10);
		txtIdCustomer.setBounds(10, 144, 150, 20);
		CustomerDetails.add(txtIdCustomer);

		JLabel lblCustomerName = new JLabel("Customer Name");
		lblCustomerName.setBounds(10, 175, 150, 14);
		CustomerDetails.add(lblCustomerName);

		txtCustomerName = new JTextField();
		txtCustomerName.setColumns(10);
		txtCustomerName.setBounds(10, 189, 150, 20);
		CustomerDetails.add(txtCustomerName);

		JLabel lblCustomerPhone = new JLabel("Customer Phone");
		lblCustomerPhone.setBounds(170, 175, 150, 14);
		CustomerDetails.add(lblCustomerPhone);

		txtCustomerPhone = new JTextField();
		txtCustomerPhone.setColumns(10);
		txtCustomerPhone.setBounds(170, 189, 150, 20);
		CustomerDetails.add(txtCustomerPhone);

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

		JLabel lblPurchaseHistory = new JLabel("Purchase History");
		lblPurchaseHistory.setFont(new Font("Calibri", Font.PLAIN, 25));
		lblPurchaseHistory.setBounds(352, 10, 199, 32);
		Purchases.add(lblPurchaseHistory);

		JPanel ProducDetails = new JPanel();
		ProducDetails.setLayout(null);
		ProducDetails.setBounds(10, 53, 332, 369);
		Purchases.add(ProducDetails);

		JButton btnPurchase = new JButton("Purchase");
		btnPurchase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				purchaseForm = new AddPurchaseVM(cmbProductsPurchases,txtSupplierPurchase, txtPurchasePricePurchase, txtSalePricePurchase, txtUnitPurchase,  txtQuantityPurchase);
				if (PurchasesController.validateAddPurchaseInput(purchaseForm)) {
					PurchasesController.addNewPurchase(purchaseForm);
					purchases = PurchasesController.fillTableWithPurchases(purchases, purchasesTableModel);
					products = ProductController.fillTableWithProducts(products, productsTableModel);
				}
			}
		});
		btnPurchase.setBounds(90, 206, 152, 23);
		ProducDetails.add(btnPurchase);

		JLabel lblTitlePurchase = new JLabel("Product Title");
		lblTitlePurchase.setBounds(10, 71, 150, 14);
		ProducDetails.add(lblTitlePurchase);

		JLabel lblSupplierPurchase = new JLabel("Supplier");
		lblSupplierPurchase.setBounds(170, 71, 150, 14);
		ProducDetails.add(lblSupplierPurchase);

		txtSupplierPurchase = new JTextField();
		txtSupplierPurchase.setColumns(10);
		txtSupplierPurchase.setBounds(170, 85, 150, 20);
		ProducDetails.add(txtSupplierPurchase);

		JLabel lblPurchasePricePurchase = new JLabel("Purchase Price");
		lblPurchasePricePurchase.setBounds(10, 116, 150, 14);
		ProducDetails.add(lblPurchasePricePurchase);

		txtPurchasePricePurchase = new JTextField();
		txtPurchasePricePurchase.setColumns(10);
		txtPurchasePricePurchase.setBounds(10, 130, 150, 20);
		ProducDetails.add(txtPurchasePricePurchase);

		JLabel lblSalePricePurchase = new JLabel("Sale Price");
		lblSalePricePurchase.setBounds(170, 116, 150, 14);
		ProducDetails.add(lblSalePricePurchase);

		txtSalePricePurchase = new JTextField();
		txtSalePricePurchase.setColumns(10);
		txtSalePricePurchase.setBounds(170, 130, 150, 20);
		ProducDetails.add(txtSalePricePurchase);

		JLabel lblUnitPurchase = new JLabel("Measuring Unit");
		lblUnitPurchase.setBounds(10, 161, 150, 14);
		ProducDetails.add(lblUnitPurchase);

		txtUnitPurchase = new JTextField();
		txtUnitPurchase.setEditable(false);
		txtUnitPurchase.setColumns(10);
		txtUnitPurchase.setBounds(10, 175, 150, 20);
		ProducDetails.add(txtUnitPurchase);
		txtUnitPurchase.setText(products.get(0).getUnit());

		JLabel lblQuantityPurchase = new JLabel("Quantity");
		lblQuantityPurchase.setBounds(170, 161, 150, 14);
		ProducDetails.add(lblQuantityPurchase);

		txtQuantityPurchase = new JTextField();
		txtQuantityPurchase.setColumns(10);
		txtQuantityPurchase.setBounds(170, 175, 150, 20);
		ProducDetails.add(txtQuantityPurchase);

		cmbProductsPurchases = new JComboBox();
		cmbProductsPurchases.setBounds(10, 85, 150, 20);
		ProducDetails.add(cmbProductsPurchases);
		for (int i = 0; i < products.size(); i++) {
			cmbProductsPurchases.addItem(products.get(i).getTitle());
		}
		cmbProductsPurchases.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				txtUnitPurchase.setText(products.get(cmbProductsPurchases.getSelectedIndex()).getUnit());
			}
		});
		
		JScrollPane scrollPanePurchases = new JScrollPane();
		scrollPanePurchases.setBounds(352, 53, 662, 369);
		Purchases.add(scrollPanePurchases);

		purchasesTable = new JTable(purchasesTableModel);
		scrollPanePurchases.setViewportView(purchasesTable);
	}
	
	public void ExpensesGUI() {
		Expenses = new JPanel();
		Expenses.setLayout(null);
		layeredPane.add(Expenses, "name_2466418055850600");
		
		JLabel lblSelectedExpense = new JLabel("Selected Expense");
		lblSelectedExpense.setFont(new Font("Calibri", Font.PLAIN, 25));
		lblSelectedExpense.setBounds(10, 10, 180, 32);
		Expenses.add(lblSelectedExpense);
		
		JLabel lblAllExpenses = new JLabel("All Expenses");
		lblAllExpenses.setFont(new Font("Calibri", Font.PLAIN, 25));
		lblAllExpenses.setBounds(352, 10, 135, 32);
		Expenses.add(lblAllExpenses);
		
		JPanel ExpenseDetails = new JPanel();
		ExpenseDetails.setLayout(null);
		ExpenseDetails.setBounds(10, 53, 332, 369);
		Expenses.add(ExpenseDetails);
		
		JButton btnDeleteExpense = new JButton("Delete!");
		btnDeleteExpense.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				expenseForm = new AddExpenseVM(txtAmountExpense, txtDescriptionExpense);

				if (selectedExpense == null && expensesTable.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "No Expense selected to delete!");
				} else {
					boolean isDeleted = ExpenseController.deleteExpense(selectedExpense);
					if (isDeleted) {
						JOptionPane.showMessageDialog(null,
								"Expense deleted successfully!");
						expenseDefaults();
						expenses = ExpenseController.fillTableWithExpenses(expenses, expensesTableModel);
					}

				}
			}
		});
		btnDeleteExpense.setBounds(172, 98, 150, 23);
		ExpenseDetails.add(btnDeleteExpense);
		
		JButton btnAddExpense = new JButton("Add!");
		btnAddExpense.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				expenseForm = new AddExpenseVM(txtAmountExpense, txtDescriptionExpense);
				
				if (ExpenseController.validateAddExpenseInput(expenseForm)) {
					ExpenseController.addNewExpense(expenseForm);
					expenses = ExpenseController.fillTableWithExpenses(expenses, expensesTableModel);
				}
			}
		});
		btnAddExpense.setBounds(10, 98, 152, 23);
		ExpenseDetails.add(btnAddExpense);
		
		JLabel lblAmountExpense = new JLabel("Amount");
		lblAmountExpense.setBounds(10, 132, 150, 14);
		ExpenseDetails.add(lblAmountExpense);
		
		txtAmountExpense = new JTextField();
		txtAmountExpense.setColumns(10);
		txtAmountExpense.setBounds(10, 146, 150, 20);
		ExpenseDetails.add(txtAmountExpense);
		
		JLabel lblDescriptionExpense = new JLabel("Description");
		lblDescriptionExpense.setBounds(10, 177, 150, 14);
		ExpenseDetails.add(lblDescriptionExpense);
		
		txtDescriptionExpense = new JTextField();
		txtDescriptionExpense.setColumns(10);
		txtDescriptionExpense.setBounds(10, 191, 310, 50);
		ExpenseDetails.add(txtDescriptionExpense);
		
		JScrollPane scrollPaneExpenses = new JScrollPane();
		scrollPaneExpenses.setBounds(352, 53, 662, 369);
		Expenses.add(scrollPaneExpenses);
		
		expensesTable = new JTable(expensesTableModel);
		scrollPaneExpenses.setViewportView(expensesTable);
		
		expensesTable.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
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

	public App() {
		InstanciateApp();
		HomeGUI();
		AddProductGUI();
		CustomerGUI();
		PurchasesGUI();
		ExpensesGUI();
	}

	public void homeButtonClicked() {

		productDefaults();
		customerDefaults();
	}

	public void productDefaults() {
		productForm = new AddProductVM(txtID, txtTitle, txtUnit, cbStatus, txtDescription);
		ProductController.resetFields(productForm);
		selectedProduct = null;
		productTable.clearSelection();
	}

	public void customerDefaults() {
		customerForm = new AddCustomerVM(txtIdCustomer, txtCustomerName, txtCustomerPhone);
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
}
