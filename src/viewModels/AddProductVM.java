package viewModels;

import javax.swing.JCheckBox;
import javax.swing.JTextField;

public class AddProductVM {

	public JTextField txtTitle, txtUnit, txtSalePrice,  txtDescription;
	public JCheckBox cbStatus;
	
	public AddProductVM( JTextField txtTitle, JTextField txtUnit, JTextField txtSalePrice, JCheckBox cbStatus, JTextField txtDescription) {
		super();
		this.txtTitle = txtTitle;
		this.txtUnit = txtUnit;
		this.cbStatus = cbStatus;
		this.txtDescription = txtDescription;
		this.txtSalePrice = txtSalePrice;
	}

}
