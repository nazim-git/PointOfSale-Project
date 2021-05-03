package viewModels;

import javax.swing.JCheckBox;
import javax.swing.JTextField;

public class AddProductVM {

	public JTextField txtId, txtTitle, txtUnit, txtSalePrice,  txtDescription;
	public JCheckBox cbStatus;
	
	public AddProductVM(JTextField txtId, JTextField txtTitle, JTextField txtUnit, JCheckBox cbStatus, JTextField txtDescription) {
		super();
		this.txtId = txtId;
		this.txtTitle = txtTitle;
		this.txtUnit = txtUnit;
		this.cbStatus = cbStatus;
		this.txtDescription = txtDescription;
	}

}
