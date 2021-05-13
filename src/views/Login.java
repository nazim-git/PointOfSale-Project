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
import dataModels.User;
import viewModels.Credentials;

import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JPasswordField;
import javax.swing.border.BevelBorder;

public class Login extends JFrame {

	private JPanel contentPane, panel;
	private JLabel lblUsername, lblPassword;
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
		setBounds(550,50,500,300);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(10, 11, 464, 240);
		contentPane.add(panel);

		lblUsername = new JLabel("Username");
		lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsername.setFont(new Font("Cambria Math", Font.PLAIN, 18));
		lblUsername.setBounds(80, 88, 90, 20);
		panel.add(lblUsername);

		lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setFont(new Font("Cambria Math", Font.PLAIN, 18));
		lblPassword.setBounds(80, 118, 90, 20);
		panel.add(lblPassword);

		usernameField = new JTextField();
		usernameField.setFont(new Font("Cambria Math", Font.PLAIN, 14));
		usernameField.setColumns(10);
		usernameField.setBounds(180, 88, 200, 20);
		panel.add(usernameField);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Cambria Math", Font.PLAIN, 14));
		passwordField.setBounds(180, 119, 200, 20);
		panel.add(passwordField);

		btnLogin = new JButton("Login!");
		btnLogin.setFont(new Font("Cambria Math", Font.BOLD, 18));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(rootPane, "Fill all the Fields!!!");
					usernameField.requestFocus();
				} else {
					Credentials credentials = new Credentials(usernameField.getText(), passwordField.getText());

					userDao.login(credentials);

					if (User.ID != 0) {
						dispose();
						new App();
					} else {
						JOptionPane.showMessageDialog(rootPane, "Invalid Credentials");
					}
				}
			}
		});
		btnLogin.setBounds(290, 169, 90, 40);
		panel.add(btnLogin);
		
				JLabel lblPointOfSale = new JLabel("Point Of Sale");
				lblPointOfSale.setBounds(128, 11, 220, 49);
				panel.add(lblPointOfSale);
				lblPointOfSale.setHorizontalAlignment(SwingConstants.CENTER);
				lblPointOfSale.setFont(new Font("Segoe Script", Font.BOLD, 30));

		setVisible(true);
	}

	public Login() {
		Initializations();
		UI();

	}
}
