package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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

public class SaleInvoice extends JFrame {
	
	//UI
	private JPanel contentPane;
	private JLabel lblSaleInvoice,lblProductName,lblProductId;
	private JComboBox cmbProductName,cmbProductID;
	
	//DAO
	SaleInvoiceDao saleInvoiceDao;
	//
	ArrayList<ProductModel> products;
	
	public SaleInvoice() {
		Initializations();
		getComboData();
		setUI();
		
		setUI();
		repaint();
		revalidate();
		
		
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
		
		cmbProductName = new JComboBox();
		cmbProductName.setBounds(138, 83, 147, 20);
		contentPane.add(cmbProductName);
		
		lblProductName = new JLabel("Product Name");
		lblProductName.setBounds(26, 89, 102, 14);
		contentPane.add(lblProductName);
		
		lblProductId = new JLabel("Product ID");
		lblProductId.setBounds(42, 64, 86, 14);
		contentPane.add(lblProductId);
		
		cmbProductID = new JComboBox(products.toArray());
		cmbProductID.setBounds(138, 58, 147, 20);
		contentPane.add(cmbProductID);
	}

	public void Initializations() {
		saleInvoiceDao = new SaleInvoiceDao();
		products = new ArrayList<ProductModel>();
	}
	
	public void getComboData() {
		try {
			ResultSet rs = saleInvoiceDao.getProducts();
			
			ProductModel product = new ProductModel();
			
			while(rs.next()) {
				product.setID(Integer.parseInt(rs.getString("ID")));
				product.setName(rs.getString("title"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}






















