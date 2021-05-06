package viewModels;

import javax.swing.JTextField;

public class AddCustomerVM {
	public JTextField txtCustomerName, txtCustomerPhone;

	public AddCustomerVM(JTextField txtCustomerName, JTextField txtCustomerPhone) {
		super();
		this.txtCustomerName = txtCustomerName;
		this.txtCustomerPhone = txtCustomerPhone;
	}

}
