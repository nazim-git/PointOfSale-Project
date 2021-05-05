package viewModels;

import javax.swing.JCheckBox;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class AddUserVM {

	public JTextField txtUserId, txtNameUser, txtUsername;
	public JPasswordField txtPassword;
	public JCheckBox chkboxIsAdmin;
	
	public AddUserVM(JTextField txtUserId, JTextField txtNameUser, JTextField txtUsername, JPasswordField txtPassword,
			JCheckBox chkboxIsAdmin) {
		this.txtUserId = txtUserId;
		this.txtNameUser = txtNameUser;
		this.txtUsername = txtUsername;
		this.txtPassword = txtPassword;
		this.chkboxIsAdmin = chkboxIsAdmin;
	}

}
