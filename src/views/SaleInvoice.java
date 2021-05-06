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

	private ArrayList<ProductModel> products;
	private ArrayList<CustomerModel> customers;

	private ProductModel selectedProduct;
	private CustomerModel selectedCustomer;

	private InvoiceModel invoice;
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
		setMaximizedBounds(Frame.getScreenBounds());
		setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		Initializations();
		setUI();
		fillWithSelectedProduct(true);
		repaint();
		revalidate();
	}

	public void Initializations() {
		productDao = new ProductDao();
		customerDao = new CustomerDao();

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
				selectedProduct = products.get(cmbProductName.getSelectedIndex());

				fillWithSelectedProduct(false);
			}
		});

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
									Integer.parseInt(txtQuantity.getText()), invoice, tableModel, txtTotal,
									txtDiscountPercent, txtDiscountAmount, txtTotalToPay, txtReceived, txtChange));
						}
					});
					popup.add(menuItem);
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});

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
					if (selectedProduct.getStock() >= Integer.parseInt(txtQuantity.getText())) {
						InvoiceController.addItemToInvoice(new AddItemVM(selectedProduct,
								Integer.parseInt(txtQuantity.getText()), invoice, tableModel, txtTotal,
								txtDiscountPercent, txtDiscountAmount, txtTotalToPay, txtReceived, txtChange));
						btnCash.setEnabled(false);
					} else {
						JOptionPane.showMessageDialog(null, "Not enough stock");
					}
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
		txtCustomerMobile.setBounds(781, 78, 150, 30);
		contentPane.add(txtCustomerMobile);
		txtCustomerMobile.setText(customers.get(0).getPhone());

		JLabel lblCustomerMobile = new JLabel("Customer Mobile");
		lblCustomerMobile.setBounds(785, 65, 120, 14);
		contentPane.add(lblCustomerMobile);

		JLabel lblCustomerName = new JLabel("Customer Name");
		lblCustomerName.setBounds(535, 65, 117, 14);
		contentPane.add(lblCustomerName);

		cmbCustomerName = new JComboBox();
		cmbCustomerName.setToolTipText("Select Product By Name.....");
		cmbCustomerName.setMaximumRowCount(5);
		cmbCustomerName.setBounds(530, 78, 241, 30);
		contentPane.add(cmbCustomerName);

		JLabel lblTotalAmount = new JLabel("Total Amount");
		lblTotalAmount.setBounds(142, 433, 86, 14);
		contentPane.add(lblTotalAmount);

		JLabel lblDiscount = new JLabel("Discount %");
		lblDiscount.setBounds(142, 458, 86, 14);
		contentPane.add(lblDiscount);

		JLabel lblDiscountAmount = new JLabel("Discount Amount");
		lblDiscountAmount.setBounds(142, 483, 86, 14);
		contentPane.add(lblDiscountAmount);

		JLabel lblTotalToPay = new JLabel("Total To Pay");
		lblTotalToPay.setBounds(142, 508, 86, 14);
		contentPane.add(lblTotalToPay);

		JLabel lblReceived = new JLabel("Received");
		lblReceived.setBounds(142, 533, 86, 14);
		contentPane.add(lblReceived);

		JLabel lblChange = new JLabel("Change");
		lblChange.setBounds(142, 558, 86, 14);
		contentPane.add(lblChange);

		txtTotal = new JTextField();
		txtTotal.setEditable(false);
		txtTotal.setText("0");
		txtTotal.setBounds(238, 430, 120, 20);
		contentPane.add(txtTotal);
		txtTotal.setColumns(10);

		txtDiscountPercent = new JTextField();
		txtDiscountPercent.setText("0");
		txtDiscountPercent.setBounds(238, 455, 120, 20);
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

		txtDiscountAmount = new JTextField();
		txtDiscountAmount.setEditable(false);
		txtDiscountAmount.setText("0");
		txtDiscountAmount.setBounds(238, 480, 120, 20);
		contentPane.add(txtDiscountAmount);
		txtDiscountAmount.setColumns(10);

		txtTotalToPay = new JTextField();
		txtTotalToPay.setEditable(false);
		txtTotalToPay.setText("0");
		txtTotalToPay.setBounds(238, 505, 120, 20);
		contentPane.add(txtTotalToPay);
		txtTotalToPay.setColumns(10);

		txtReceived = new JTextField();
		txtReceived.setText("0");
		txtReceived.setBounds(238, 530, 120, 20);
		contentPane.add(txtReceived);
		txtReceived.setColumns(10);

		txtChange = new JTextField();
		txtChange.setEditable(false);
		txtChange.setText("0");
		txtChange.setBounds(238, 555, 120, 20);
		contentPane.add(txtChange);
		txtChange.setColumns(10);

		JButton btnCalculate = new JButton("Calculate");
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				InvoiceController.calculate(new AddItemVM(invoice, txtTotal, txtDiscountPercent, txtDiscountAmount,
						txtTotalToPay, txtReceived, txtChange));
				btnCash.setEnabled(true);
			}
		});
		btnCalculate.setBounds(269, 586, 89, 23);
		contentPane.add(btnCalculate);

		btnCash = new JButton("Cash!");
		btnCash.setEnabled(false);
		btnCash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(invoice.getInvoiceItems().size() == 0) {
					JOptionPane.showMessageDialog(null, "No Product Added!");
				} else {
					if(Float.parseFloat(txtTotalToPay.getText()) <= Float.parseFloat(txtReceived.getText())) {
						InvoiceController.cash(invoice);
						JOptionPane.showMessageDialog(null, "Success!");
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "Settle Received and Total to Pay!");
					}
				}
				
			}
		});
		btnCash.setBounds(368, 586, 89, 23);
		contentPane.add(btnCash);

		txtStock = new JTextField();
		txtStock.setText("0.0");
		txtStock.setHorizontalAlignment(SwingConstants.RIGHT);
		txtStock.setEditable(false);
		txtStock.setColumns(10);
		txtStock.setBounds(10, 78, 120, 30);
		contentPane.add(txtStock);

		lblStock = new JLabel("Stock");
		lblStock.setBounds(14, 65, 89, 14);
		contentPane.add(lblStock);
		for (int i = 0; i < customers.size(); i++) {
			cmbCustomerName.addItem(customers.get(i).getName());
		}
		cmbCustomerName.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
//				cmbProductName.setSelectedIndex(cmbProductID.getSelectedIndex());

				selectedCustomer = customers.get(cmbCustomerName.getSelectedIndex());
				txtCustomerMobile.setText(selectedCustomer.getPhone());

				invoice.setCustomer(selectedCustomer.getName());
			}
		});

	}

	public void fillWithSelectedProduct(boolean initial) {
		if (initial)
			selectedProduct = products.get(0);
		txtUnitPrice.setText(String.valueOf(selectedProduct.getSalePrice()));
		txtMeasuringUnit.setText(selectedProduct.getUnit());
		txtStock.setText(String.valueOf(selectedProduct.getStock()));
	}

}
