package views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import Helpers.Frame;
import Helpers.InputValidation;
import dataAccess.UserDao;
import dataModels.UserModel;
import viewModels.Credentials;

import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JPasswordField;

public class Login extends JFrame {

	private JPanel contentPane, panel;
	private JLabel lblLogin, lblUsername, lblPassword;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JButton btnLogin;

	private UserDao userDao;
	
	
	public void Initializations() {
		userDao = new UserDao();
	}
	
	public void UI() {
		setTitle("Login-Point Of Sale");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(550,50,699,368);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(10, 11, 664, 311);
		contentPane.add(panel);

		lblLogin = new JLabel("Login");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblLogin.setBounds(10, 11, 85, 32);
		panel.add(lblLogin);

		lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblUsername.setBounds(206, 101, 90, 20);
		panel.add(lblUsername);

		lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblPassword.setBounds(206, 163, 90, 20);
		panel.add(lblPassword);

		usernameField = new JTextField();
		usernameField.setColumns(10);
		usernameField.setBounds(206, 132, 200, 20);
		panel.add(usernameField);

		passwordField = new JPasswordField();
		passwordField.setBounds(206, 194, 200, 20);
		panel.add(passwordField);

		btnLogin = new JButton("Login!");
		btnLogin.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(rootPane, "Fill all the Fields!!!");
				} else {
					Credentials credentials = new Credentials(usernameField.getText(), passwordField.getText());

					userDao.login(credentials);

					if (UserModel.ID != 0) {
						dispose();
						new App();
					} else {
						JOptionPane.showMessageDialog(rootPane, "Invalid Credentials");
					}
				}
			}
		});
		btnLogin.setBounds(270, 235, 90, 23);
		panel.add(btnLogin);

		JPanel LogoPanel = new JPanel();
		LogoPanel.setLayout(null);
		LogoPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "",

				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		LogoPanel.setBounds(422, 11, 232, 95);
		panel.add(LogoPanel);

		JLabel lblPointOfSale = new JLabel("Point Of Sale");
		lblPointOfSale.setHorizontalAlignment(SwingConstants.CENTER);
		lblPointOfSale.setFont(new Font("Segoe Script", Font.BOLD, 30));
		lblPointOfSale.setBounds(6, 16, 220, 49);
		LogoPanel.add(lblPointOfSale);

		JLabel label_1 = new JLabel("Dublin.");
		label_1.setFont(new Font("Segoe Print", Font.PLAIN, 14));
		label_1.setBounds(177, 62, 49, 26);
		LogoPanel.add(label_1);

		setVisible(true);
	}

	public Login() {
		Initializations();
		UI();

	}
}
