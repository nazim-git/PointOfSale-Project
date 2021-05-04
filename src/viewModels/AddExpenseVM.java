package viewModels;

import javax.swing.JTextField;

public class AddExpenseVM {
	public JTextField txtAmountExpense,txtDescriptionExpense;

	public AddExpenseVM(JTextField txtAmountExpense,JTextField txtDescriptionExpense) {
		super();
		this.txtAmountExpense = txtAmountExpense;
		this.txtDescriptionExpense = txtDescriptionExpense;
	}
}
