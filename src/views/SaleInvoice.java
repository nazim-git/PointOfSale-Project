package views;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import Helpers.Frame;
import Helpers.InputValidation;
import controllers.InvoiceController;
import controllers.SalesController;
import dataAccess.CustomerDao;
import dataAccess.InvoiceDao;
import dataAccess.ProductDao;
import dataModels.CustomerModel;
import dataModels.InvoiceItemModel;
import dataModels.InvoiceModel;
import dataModels.ProductModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
	private JLabel lblSaleInvoice, lblProductName, lblMeasuringUnit, lblUnitPrice;
	private JComboBox cmbProductName, cmbCustomerName;
	private JTextField txtMeasuringUnit, txtUnitPrice;

	private JScrollPane scrollPane;
	private JTable table;

	private DefaultTableModel tableModel;

	private ProductDao productDao;
	private CustomerDao customerDao;
	private InvoiceDao invoiceDao;

	private ArrayList<ProductModel> products;
	private ArrayList<CustomerModel> customers;

	private ProductModel selectedProduct;
	private CustomerModel selectedCustomer;

	private InvoiceModel invoice = new InvoiceModel();
	private ArrayList<InvoiceItemModel> items;

	String header[] = { "Product", "Unit", "Unit Price", "Quantity", "Sub-Total" };

	private JTextField txtQuantity;
	private JTextField txtCustomerMobile;
	private JTextField txtTotal;
	private JTextField txtDiscountPercent;
	private JTextField txtDiscountAmount;
	private JTextField txtTotalToPay;
	private JTextField txtReceived;
	private JTextField txtChange;
	private JButton btnCash;
	private JTextField txtStock;
	private JLabel lblStock;

	public SaleInvoice() {
		setResizable(false);
//		setMaximizedBounds(Frame.getScreenBounds());
//		setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		Initializations();
		setUI();
		fillWithSelectedProduct(true);
		repaint();
		revalidate();
	}

	public SaleInvoice(InvoiceModel selectedInvoice) {
//		setMaximizedBounds(Frame.getScreenBounds());
//		setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		Initializations();
		setUI();
		fillWithSelectedProduct(true);
		repaint();
		revalidate();
		System.out.println(selectedInvoice.getId());
		this.invoice = invoiceDao.getInvoice(selectedInvoice.getId());
		this.invoice.setInvoiceItems(invoiceDao.getInvoiceItems(this.invoice.getId()));
		populateInvoice();
	}

	private void populateInvoice() {
		cmbCustomerName.setSelectedItem(invoice.getCustomer());
		InvoiceController.fillTableWithInvoiceItems(new AddItemVM(invoice, tableModel, txtTotal, txtDiscountPercent,
				txtDiscountAmount, txtTotalToPay, txtReceived, txtChange));
	}

	public void Initializations() {
		productDao = new ProductDao();
		customerDao = new CustomerDao();
		invoiceDao = new InvoiceDao();

		products = new ArrayList<ProductModel>();
		customers = new ArrayList<CustomerModel>();

		selectedProduct = new ProductModel();
		selectedCustomer = new CustomerModel();

		invoice = new InvoiceModel();
		items = new ArrayList<InvoiceItemModel>();

		products = productDao.getProducts();
		customers = customerDao.getCustomers();

		invoice.setInvoiceItems(items);

		tableModel = new DefaultTableModel(header, 0) {
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		};

		selectedCustomer = customers.get(0);
		invoice.setCustomer(selectedCustomer.getName());
		invoice.setCustomerPhone(selectedCustomer.getPhone());
	}

	public void setUI() {
		setTitle("Sale Invoice");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		setVisible(true);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, 963, 607);

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblSaleInvoice = new JLabel("Sale Invoice");
		lblSaleInvoice.setFont(new Font("Cambria Math", Font.BOLD, 24));
		lblSaleInvoice.setBounds(405, 11, 156, 30);
		contentPane.add(lblSaleInvoice);

		JLabel lblCustomerName = new JLabel("Customer Name");
		lblCustomerName.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		lblCustomerName.setBounds(47, 52, 235, 14);
		contentPane.add(lblCustomerName);

		cmbCustomerName = new JComboBox();
		cmbCustomerName.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		cmbCustomerName.setToolTipText("Select Product By Name.....");
		cmbCustomerName.setMaximumRowCount(5);
		cmbCustomerName.setBounds(47, 65, 235, 30);
		contentPane.add(cmbCustomerName);
		cmbCustomerName.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
//				cmbProductName.setSelectedIndex(cmbProductID.getSelectedIndex());

				selectedCustomer = customers.get(cmbCustomerName.getSelectedIndex());
				txtCustomerMobile.setText(selectedCustomer.getPhone());

				invoice.setCustomer(selectedCustomer.getName());
				invoice.setCustomerPhone(selectedCustomer.getPhone());
			}
		});

		JLabel lblCustomerMobile = new JLabel("Customer Mobile");
		lblCustomerMobile.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		lblCustomerMobile.setBounds(294, 52, 163, 14);
		contentPane.add(lblCustomerMobile);

		txtCustomerMobile = new JTextField();
		txtCustomerMobile.setHorizontalAlignment(SwingConstants.RIGHT);
		txtCustomerMobile.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		txtCustomerMobile.setToolTipText("Enter Customer Mobile Here.....");
		txtCustomerMobile.setText((String) null);
		txtCustomerMobile.setEditable(false);
		txtCustomerMobile.setColumns(10);
		txtCustomerMobile.setBounds(294, 65, 163, 30);
		contentPane.add(txtCustomerMobile);
		txtCustomerMobile.setText(customers.get(0).getPhone());

		lblProductName = new JLabel("Product Name");
		lblProductName.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		lblProductName.setBounds(47, 106, 235, 14);
		contentPane.add(lblProductName);

		cmbProductName = new JComboBox();
		cmbProductName.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		cmbProductName.setMaximumRowCount(5);
		cmbProductName.setToolTipText("Select Product By Name.....");
		cmbProductName.setBounds(47, 119, 235, 30);
		contentPane.add(cmbProductName);
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getStatus() && products.get(i).getStock() > 0) {
				cmbProductName.addItem(products.get(i).getTitle());
			}
		}
		cmbProductName.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedProduct = products.get(cmbProductName.getSelectedIndex());

				fillWithSelectedProduct(false);
			}
		});

		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		lblQuantity.setBounds(292, 106, 165, 14);
		contentPane.add(lblQuantity);

		txtQuantity = new JTextField();
		txtQuantity.setHorizontalAlignment(SwingConstants.RIGHT);
		txtQuantity.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		txtQuantity.setToolTipText("Enter quantity of the item here....");
		txtQuantity.setText((String) null);
		txtQuantity.setColumns(10);
		txtQuantity.setBounds(292, 119, 165, 30);
		txtQuantity.setText("0");
		contentPane.add(txtQuantity);

		lblStock = new JLabel("Stock");
		lblStock.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		lblStock.setBounds(467, 106, 85, 14);
		contentPane.add(lblStock);

		txtStock = new JTextField();
		txtStock.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		txtStock.setText("0.0");
		txtStock.setHorizontalAlignment(SwingConstants.RIGHT);
		txtStock.setEditable(false);
		txtStock.setColumns(10);
		txtStock.setBounds(467, 119, 85, 30);
		contentPane.add(txtStock);

		lblUnitPrice = new JLabel("Unit Price");
		lblUnitPrice.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		lblUnitPrice.setBounds(562, 106, 85, 14);
		contentPane.add(lblUnitPrice);

		txtUnitPrice = new JTextField();
		txtUnitPrice.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		txtUnitPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		txtUnitPrice.setEditable(false);
		txtUnitPrice.setColumns(10);
		txtUnitPrice.setBounds(562, 119, 85, 30);
		contentPane.add(txtUnitPrice);

		lblMeasuringUnit = new JLabel("Unit");
		lblMeasuringUnit.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		lblMeasuringUnit.setBounds(657, 106, 85, 14);
		contentPane.add(lblMeasuringUnit);

		txtMeasuringUnit = new JTextField();
		txtMeasuringUnit.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		txtMeasuringUnit.setHorizontalAlignment(SwingConstants.RIGHT);
		txtMeasuringUnit.setToolTipText("Measuring Unit of the Selected Product.....");
		txtMeasuringUnit.setEditable(false);
		txtMeasuringUnit.setBounds(657, 119, 85, 30);
		contentPane.add(txtMeasuringUnit);
		txtMeasuringUnit.setColumns(10);

		JButton btnAddItem = new JButton("Add Item!");
		btnAddItem.setFont(new Font("Cambria Math", Font.BOLD, 22));
		btnAddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (InvoiceController.validateQuantity(txtQuantity)) {
					if (selectedProduct.getStock() >= Integer.parseInt(txtQuantity.getText())) {
						InvoiceController.addItemToInvoice(new AddItemVM(selectedProduct,
								Integer.parseInt(txtQuantity.getText()), invoice, tableModel, txtTotal,
								txtDiscountPercent, txtDiscountAmount, txtTotalToPay, txtReceived, txtChange));
						btnCash.setEnabled(false);
					} else {
						JOptionPane.showMessageDialog(null, "Not enough stock");
					}
				}
			}

		});
		btnAddItem.setBounds(752, 52, 143, 97);
		contentPane.add(btnAddItem);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(47, 160, 848, 300);
		contentPane.add(scrollPane);

		table = new JTable(tableModel);
		scrollPane.setViewportView(table);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				int r = table.rowAtPoint(e.getPoint());
				if (r >= 0 && r < table.getRowCount()) {
					table.setRowSelectionInterval(r, r);
				} else {
					table.clearSelection();
				}

				int rowindex = table.getSelectedRow();
				if (rowindex < 0)
					return;
				if (e.isPopupTrigger() && e.getComponent() instanceof JTable) {
					JPopupMenu popup = new JPopupMenu();
					JMenuItem menuItem = new JMenuItem("Delete");
					menuItem.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0) {
							invoice.getInvoiceItems().remove(rowindex);
							InvoiceController.fillTableWithInvoiceItems(new AddItemVM(selectedProduct,
									Integer.parseInt(txtQuantity.getText().isEmpty() ? txtQuantity.getText() : "0"),
									invoice, tableModel, txtTotal, txtDiscountPercent, txtDiscountAmount, txtTotalToPay,
									txtReceived, txtChange));
						}
					});
					popup.add(menuItem);
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});

		JLabel lblTotalAmount = new JLabel("Total Amount");
		lblTotalAmount.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		lblTotalAmount.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotalAmount.setBounds(47, 471, 100, 14);
		contentPane.add(lblTotalAmount);

		txtTotal = new JTextField();
		txtTotal.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		txtTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTotal.setEditable(false);
		txtTotal.setText("0");
		txtTotal.setBounds(47, 489, 100, 20);
		contentPane.add(txtTotal);
		txtTotal.setColumns(10);

		JLabel lblDiscount = new JLabel("Discount %");
		lblDiscount.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		lblDiscount.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDiscount.setBounds(157, 471, 100, 14);
		contentPane.add(lblDiscount);

		txtDiscountPercent = new JTextField();
		txtDiscountPercent.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		txtDiscountPercent.setHorizontalAlignment(SwingConstants.RIGHT);
		txtDiscountPercent.setText("0");
		txtDiscountPercent.setBounds(157, 489, 100, 20);
		contentPane.add(txtDiscountPercent);
		txtDiscountPercent.setColumns(10);
		txtDiscountPercent.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				btnCash.setEnabled(false);
			}

			@Override
			public void insertUpdate(DocumentEvent e) {

			}

			@Override
			public void changedUpdate(DocumentEvent arg0) {
			}
		});

		JLabel lblDiscountAmount = new JLabel("Discount Amount");
		lblDiscountAmount.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		lblDiscountAmount.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDiscountAmount.setBounds(267, 471, 100, 14);
		contentPane.add(lblDiscountAmount);

		txtDiscountAmount = new JTextField();
		txtDiscountAmount.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		txtDiscountAmount.setHorizontalAlignment(SwingConstants.RIGHT);
		txtDiscountAmount.setEditable(false);
		txtDiscountAmount.setText("0");
		txtDiscountAmount.setBounds(267, 489, 100, 20);
		contentPane.add(txtDiscountAmount);
		txtDiscountAmount.setColumns(10);

		JLabel lblTotalToPay = new JLabel("Total To Pay");
		lblTotalToPay.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		lblTotalToPay.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotalToPay.setBounds(377, 471, 100, 14);
		contentPane.add(lblTotalToPay);

		txtTotalToPay = new JTextField();
		txtTotalToPay.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		txtTotalToPay.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTotalToPay.setEditable(false);
		txtTotalToPay.setText("0");
		txtTotalToPay.setBounds(377, 489, 100, 20);
		contentPane.add(txtTotalToPay);
		txtTotalToPay.setColumns(10);

		JLabel lblReceived = new JLabel("Received");
		lblReceived.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		lblReceived.setHorizontalAlignment(SwingConstants.RIGHT);
		lblReceived.setBounds(487, 471, 100, 14);
		contentPane.add(lblReceived);

		txtReceived = new JTextField();
		txtReceived.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		txtReceived.setHorizontalAlignment(SwingConstants.RIGHT);
		txtReceived.setText("0");
		txtReceived.setBounds(487, 489, 100, 20);
		contentPane.add(txtReceived);
		txtReceived.setColumns(10);

		JLabel lblChange = new JLabel("Change");
		lblChange.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		lblChange.setHorizontalAlignment(SwingConstants.RIGHT);
		lblChange.setBounds(597, 471, 100, 14);
		contentPane.add(lblChange);

		txtChange = new JTextField();
		txtChange.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		txtChange.setHorizontalAlignment(SwingConstants.RIGHT);
		txtChange.setEditable(false);
		txtChange.setText("0");
		txtChange.setBounds(597, 489, 100, 20);
		contentPane.add(txtChange);
		txtChange.setColumns(10);

		JButton btnCalculate = new JButton("Calculate");
		btnCalculate.setFont(new Font("Cambria Math", Font.PLAIN, 14));
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				InvoiceController.calculate(new AddItemVM(invoice, txtTotal, txtDiscountPercent, txtDiscountAmount,
						txtTotalToPay, txtReceived, txtChange));
				btnCash.setEnabled(true);
			}
		});
		btnCalculate.setBounds(707, 471, 89, 42);
		contentPane.add(btnCalculate);

		btnCash = new JButton("Cash!");
		btnCash.setFont(new Font("Cambria Math", Font.BOLD, 20));
		btnCash.setEnabled(false);
		btnCash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(invoice.getId());
				if (invoice.getInvoiceItems().size() == 0 && invoice.getId() == 0) {
					JOptionPane.showMessageDialog(null, "No Product Added!");
				} else {
					if (Float.parseFloat(txtTotalToPay.getText()) <= Float.parseFloat(txtReceived.getText())) {
						if (invoice.getId() != 0) {
							InvoiceController.refund(invoice);
						} else {
							InvoiceController.cash(invoice);
						}

						JOptionPane.showMessageDialog(null, "Success!");
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "Settle Received and Total to Pay!");
					}
				}

			}
		});
		btnCash.setBounds(806, 471, 89, 42);
		contentPane.add(btnCash);
		for (int i = 0; i < customers.size(); i++) {
			cmbCustomerName.addItem(customers.get(i).getName());
		}

	}

	public void fillWithSelectedProduct(boolean initial) {
		if (initial)
			selectedProduct = products.get(0);
		txtUnitPrice.setText(String.valueOf(selectedProduct.getSalePrice()));
		txtMeasuringUnit.setText(selectedProduct.getUnit());
		txtStock.setText(String.valueOf(selectedProduct.getStock()));
	}

}
