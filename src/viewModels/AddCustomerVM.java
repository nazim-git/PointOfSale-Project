package viewModels;

import javax.swing.JTextField;

public class AddCustomerVM {
	public JTextField txtCustomerName, txtCustomerPhone, txtStreet, txtArea, txtCity;

	public AddCustomerVM(JTextField txtCustomerName, JTextField txtCustomerPhone, JTextField txtStreet,
			JTextField txtArea, JTextField txtCity) {
		super();
		this.txtCustomerName = txtCustomerName;
		this.txtCustomerPhone = txtCustomerPhone;
		this.txtStreet = txtStreet;
		this.txtArea = txtArea;
		this.txtCity = txtCity;
	}

}
