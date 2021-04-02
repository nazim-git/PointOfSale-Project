package viewModels;

import javax.swing.JCheckBox;
import javax.swing.JTextField;

public class AddProductVM {

	public JTextField txtID,txtTitle, txtCategory, txtUnit, txtPurchasePrice, txtSalePrice, txtStock, txtDescription;
	public JCheckBox cbStatus;
	
	public AddProductVM(JTextField txtID,JTextField txtTitle, JTextField txtCategory, JTextField txtUnit, JTextField txtPurchasePrice,
			JTextField txtSalePrice, JTextField txtStock,JCheckBox cbStatus,JTextField txtDescription) {
		super();
		this.txtID = txtID;
		this.txtTitle = txtTitle;
		this.txtCategory = txtCategory;
		this.txtUnit = txtUnit;
		this.txtPurchasePrice = txtPurchasePrice;
		this.txtSalePrice = txtSalePrice;
		this.txtStock = txtStock;
		this.cbStatus = cbStatus;
		this.txtDescription = txtDescription;
	}

}
