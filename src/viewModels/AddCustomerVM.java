package viewModels;

import javax.swing.JTextField;

public class AddCustomerVM {
	public JTextField txtIdCustomer,txtCustomerName, txtCustomerPhone;

	public AddCustomerVM(JTextField txtIdCustomer,JTextField txtCustomerName, JTextField txtCustomerPhone) {
		super();
		this.txtIdCustomer = txtIdCustomer;
		this.txtCustomerName = txtCustomerName;
		this.txtCustomerPhone = txtCustomerPhone;
	}

}
