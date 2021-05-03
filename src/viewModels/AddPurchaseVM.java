package viewModels;

import javax.swing.JComboBox;
import javax.swing.JTextField;

public class AddPurchaseVM {
	public JComboBox cmbProductsPurchases;
	public JTextField txtSupplierPurchases, txtPurchasePricePurchases, txtSalePricePurchases, txtUnitPurchases,  txtQuantityPurchases;
	
	public AddPurchaseVM(JComboBox cmbProductsPurchases, JTextField txtSupplierPurchases, JTextField txtPurchasePricePurchases,
			JTextField txtSalePricePurchases, JTextField txtUnitPurchases, JTextField txtQuantityPurchases) {
		super();
		this.cmbProductsPurchases = cmbProductsPurchases;
		this.txtSupplierPurchases = txtSupplierPurchases;
		this.txtPurchasePricePurchases = txtPurchasePricePurchases;
		this.txtSalePricePurchases = txtSalePricePurchases;
		this.txtUnitPurchases = txtUnitPurchases;
		this.txtQuantityPurchases = txtQuantityPurchases;
	}
	
	
}
