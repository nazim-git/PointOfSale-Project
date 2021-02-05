package controllers;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Helpers.InputValidation;
import dataAccess.RunningNumberDao;
import dataModels.InvoiceItemModel;
import dataModels.InvoiceModel;
import dataModels.ProductModel;
import dataModels.RunningNumber;
import viewModels.AddItemVM;

public class InvoiceController {
	
	
	public static String generateInvoiceNumber() {
		RunningNumberDao runningNumberDao = new RunningNumberDao();
		
		String invoiceType = "SaleInvoice";
		String invoiceNumber = null;
		
		Boolean firstTime = false;
		RunningNumber runningNumber = runningNumberDao.getRunningNumber(invoiceType);
		
		if(runningNumber == null) {
			//1st time only.
			runningNumber = new RunningNumber();
			
			firstTime = true;
			runningNumber.setType(invoiceType);
			runningNumber.setNumber(1);
			runningNumber.setPrefix("SI");
		}
		
		invoiceNumber = runningNumber.getPrefix()+"-"+runningNumber.getNumber();
		
		runningNumber.setNumber(runningNumber.getNumber()+1);
		
		if(firstTime) {
			runningNumberDao.addRunningNumber(runningNumber);
		}
		else {
			runningNumberDao.updateRunningNumber(runningNumber);
		}
		
		return invoiceNumber;
	}

	
	public static void addItemToInvoice(AddItemVM model) {
		boolean alreadyInInvoice = false;
		int index = 0;
		
		for (InvoiceItemModel item : model.getInvoice().getInvoiceItems()) {
			if(item.getTitle().equals(model.getProduct().getTitle())) {
				alreadyInInvoice = true;
				break;
			}
			index++;
		}
		
		if(!alreadyInInvoice) {
			InvoiceItemModel item = new InvoiceItemModel();
			
			item.setProductId(model.getProduct().getId());
			item.setTitle(model.getProduct().getTitle());
			item.setQuantity(model.getQuantity());
			item.setUnit(model.getProduct().getUnit());
			item.setSalePrice(model.getProduct().getSalePrice());
			item.setPurchasePrice(model.getProduct().getPurchasePrice());
			item.setSubTotal(model.getProduct().getSalePrice()*model.getQuantity());
			
			model.getInvoice().getInvoiceItems().add(item);
			
		}
		else {
			model.getInvoice().getInvoiceItems().get(index).setQuantity(model.getQuantity());
			model.getInvoice().getInvoiceItems().get(index).setSubTotal(model.getProduct().getSalePrice()*model.getQuantity());
		}
		
		model.getTableModel().setRowCount(0);
		
		for (int i = 0; i < model.getInvoice().getInvoiceItems().size(); i++) {

			Object[] object = { "", model.getInvoice().getInvoiceItems().get(i).getTitle(),
					model.getInvoice().getInvoiceItems().get(i).getUnit(),
					model.getInvoice().getInvoiceItems().get(i).getSalePrice(), model.getInvoice().getInvoiceItems().get(i).getQuantity(),
					(model.getInvoice().getInvoiceItems().get(i).getSubTotal()) };
			model.getTableModel().addRow(object);
		}
		
	}
	
	public static Boolean validateQuantity(JTextField quantity) {
		if (!quantity.getText().isEmpty()) {
			if(InputValidation.validateNumbers(quantity.getText()) && Integer.parseInt(quantity.getText()) != 0) {
				return true;
			}
			else {
				JOptionPane.showMessageDialog(null, "Quantity must be a valid positive Number!");
				quantity.requestFocus();
				return false;
			}
		} else {
			JOptionPane.showMessageDialog(null, "Enter Quantity of the product!");
			quantity.requestFocus();
			return false;
		}
	}
	
}