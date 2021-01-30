package views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;

import Helpers.InputValidation;
import dataModels.UserModel;

import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Box;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.GeneralPath;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelListener;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class App extends JFrame {

	private JPanel contentPane;

	private JLayeredPane layeredPane;

	// Home
	private JPanel Home, UserDetailsPanelHome, LogoPanelHome;
	private JLabel lblHome, lblActiveUserHome, lblActiveUserNameHome, lblLogoNameHome, lblLogoCityNameHome;
	private JButton btnSaleInvoiceHome, btnLogoutHome;
	
	private TableModel model;

	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	public void InstanciateApp() {
		setTitle("Home-Point Of Sale");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		layeredPane = new JLayeredPane();
		layeredPane.setBounds(10, 11, 674, 349);
		contentPane.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		
	}

	public void HomeGUI() {
		Home = new JPanel();
		Home.setBorder(null);
		layeredPane.add(Home, "name_2178593783278100");
		Home.setLayout(null);

		lblHome = new JLabel("Home");
		lblHome.setHorizontalAlignment(SwingConstants.CENTER);
		lblHome.setFont(new Font("Times New Roman", Font.BOLD, 26));
		lblHome.setToolTipText("Home Page");
		lblHome.setBounds(10, 11, 75, 31);
		Home.add(lblHome);

		btnSaleInvoiceHome = new JButton("Sale Invoice");
		btnSaleInvoiceHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new SaleInvoice();
			}
		});
		btnSaleInvoiceHome.setBounds(20, 53, 150, 30);
		Home.add(btnSaleInvoiceHome);

		UserDetailsPanelHome = new JPanel();
		UserDetailsPanelHome.setLayout(null);
		UserDetailsPanelHome.setBounds(504, 258, 160, 80);
		Home.add(UserDetailsPanelHome);

		btnLogoutHome = new JButton("Logout!");
		btnLogoutHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserModel.logout();
				dispose();
				new Login();
			}
		});
		btnLogoutHome.setBounds(59, 51, 100, 23);
		UserDetailsPanelHome.add(btnLogoutHome);

		lblActiveUserHome = new JLabel("Active User");
		lblActiveUserHome.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblActiveUserHome.setBounds(89, 11, 70, 14);
		UserDetailsPanelHome.add(lblActiveUserHome);

		lblActiveUserNameHome = new JLabel("User Not Found!");
		lblActiveUserNameHome.setHorizontalAlignment(SwingConstants.RIGHT);
		lblActiveUserNameHome.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblActiveUserNameHome.setBounds(10, 25, 149, 22);
		UserDetailsPanelHome.add(lblActiveUserNameHome);

		LogoPanelHome = new JPanel();
		LogoPanelHome.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		LogoPanelHome.setBounds(432, 11, 232, 95);
		Home.add(LogoPanelHome);
		LogoPanelHome.setLayout(null);

		lblLogoNameHome = new JLabel("Point Of Sale");
		lblLogoNameHome.setBounds(6, 16, 220, 49);
		LogoPanelHome.add(lblLogoNameHome);
		lblLogoNameHome.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogoNameHome.setFont(new Font("Segoe Script", Font.BOLD, 30));

		lblLogoCityNameHome = new JLabel("Dublin.");
		lblLogoCityNameHome.setBounds(177, 62, 49, 26);
		LogoPanelHome.add(lblLogoCityNameHome);
		lblLogoCityNameHome.setFont(new Font("Segoe Print", Font.PLAIN, 14));
	}


	public App() {

		setResizable(false);

		InstanciateApp();

		HomeGUI();
		
		setVisible(true);
		lblActiveUserNameHome.setText(UserModel.Name);
	}
}
