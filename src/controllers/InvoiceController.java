package controllers;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Helpers.InputValidation;
import dataAccess.RunningNumberDao;
import dataModels.InvoiceItemModel;
import dataModels.InvoiceModel;
import dataModels.ProductModel;
import dataModels.RunningNumber;

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

	
	public static Object[] addItemToInvoice(ProductModel product, int quantity, InvoiceModel invoice) {
		boolean alreadyInInvoice = false;
		int index = 0;
		
		for (InvoiceItemModel item : invoice.getInvoiceItems()) {
			if(item.getTitle().equals(product.getTitle())) {
				alreadyInInvoice = true;
				break;
			}
			index++;
		}
		
		if(!alreadyInInvoice) {
			InvoiceItemModel item = new InvoiceItemModel();
			
			Object[] object = {"",product.getTitle(),product.getUnit(),product.getSalePrice(),quantity,(product.getSalePrice()*quantity)};
			
			item.setProductId(product.getId());
			item.setTitle(product.getTitle());
			item.setQuantity(quantity);
			item.setUnit(product.getUnit());
			item.setSalePrice(product.getSalePrice());
			item.setPurchasePrice(product.getPurchasePrice());
			item.setSubTotal(product.getSalePrice()*quantity);
			
			invoice.getInvoiceItems().add(item);
			
			return object;
		}
		else {
			invoice.getInvoiceItems().get(index).setQuantity(quantity);
			invoice.getInvoiceItems().get(index).setSubTotal(product.getSalePrice()*quantity);
			Object[] object = {quantity,index,product.getSalePrice()*quantity};
			return object;
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
