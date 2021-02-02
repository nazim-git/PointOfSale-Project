package controllers;

import dataAccess.RunningNumberDao;
import dataModels.InvoiceItemModel;
import dataModels.InvoiceModel;
import dataModels.ProductModel;
import dataModels.RunningNumber;

public class InvoiceController {
	
	
	public String generateInvoiceNumber() {
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

	
	public Object[] addItemToInvoice(ProductModel product, int quantity, InvoiceModel invoice) {
		InvoiceItemModel item = new InvoiceItemModel();
		Object[] object = {product.getTitle(),quantity,product.getUnit(),product.getSalePrice(),product.getPurchasePrice(),(product.getSalePrice()*quantity)};
		
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
}
