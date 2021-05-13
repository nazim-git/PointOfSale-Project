package controllers;

import java.awt.Desktop;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import Helpers.InputValidation;
import dataAccess.InvoiceDao;
import dataAccess.ProductDao;
import dataAccess.RunningNumberDao;
import dataModels.InvoiceItemModel;
import dataModels.InvoiceModel;
import dataModels.ProductModel;
import dataModels.RunningNumber;
import dataModels.User;
import viewModels.AddCustomerVM;
import viewModels.AddItemVM;

public class InvoiceController {

	static InvoiceDao invoiceDao = new InvoiceDao();
	static ProductDao productDao = new ProductDao();

	public static String generateInvoiceNumber() {
		RunningNumberDao runningNumberDao = new RunningNumberDao();

		String invoiceType = "SaleInvoice";
		String invoiceNumber = null;

		Boolean firstTime = false;
		RunningNumber runningNumber = runningNumberDao.getRunningNumber(invoiceType);

		if (runningNumber == null) {
			// 1st time only.
			runningNumber = new RunningNumber();

			firstTime = true;
			runningNumber.setType(invoiceType);
			runningNumber.setNumber(1);
			runningNumber.setPrefix("SI");
		}

		invoiceNumber = runningNumber.getPrefix() + "-" + runningNumber.getNumber();

		runningNumber.setNumber(runningNumber.getNumber() + 1);

		if (firstTime) {
			runningNumberDao.addRunningNumber(runningNumber);
		} else {
			runningNumberDao.updateRunningNumber(runningNumber);
		}

		return invoiceNumber;
	}

	public static void addItemToInvoice(AddItemVM model) {
		boolean alreadyInInvoice = false;
		int index = 0;

		for (InvoiceItemModel item : model.getInvoice().getInvoiceItems()) {
			if (item.getTitle().equals(model.getProduct().getTitle())) {
				alreadyInInvoice = true;
				break;
			}
			index++;
		}

		if (!alreadyInInvoice) {
			InvoiceItemModel item = new InvoiceItemModel();

			item.setTitle(model.getProduct().getTitle());
			item.setQuantity(model.getQuantity());
			item.setUnit(model.getProduct().getUnit());
			item.setSalePrice(model.getProduct().getSalePrice());
			item.setPurchasePrice(model.getProduct().getPurchasePrice());
			item.setSubTotal(model.getProduct().getSalePrice() * model.getQuantity());

			model.getInvoice().getInvoiceItems().add(item);

		} else {
			model.getInvoice().getInvoiceItems().get(index).setQuantity(model.getQuantity());
			model.getInvoice().getInvoiceItems().get(index)
					.setSubTotal(model.getProduct().getSalePrice() * model.getQuantity());
		}

		fillTableWithInvoiceItems(model);
	}

	public static Boolean validateQuantity(JTextField quantity) {
		if (!quantity.getText().isEmpty()) {
			if (InputValidation.validateNumbers(quantity.getText()) && Integer.parseInt(quantity.getText()) != 0) {
				return true;
			} else {
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

	public static boolean validateCalculateInput(AddItemVM model) {
		boolean isValid = true;
		Float discountPercentage;
		try {
			
			discountPercentage = Float.parseFloat(model.getTxtDiscountPercent().getText());
			if (discountPercentage < 0 || discountPercentage > 100) {
				isValid = false;
				JOptionPane.showMessageDialog(null, "Enter valid value for Discount!");
				return isValid;
			}
		} catch (NumberFormatException ex) {
			discountPercentage = (float) 0;
			JOptionPane.showMessageDialog(null, "Enter valid value for Discount!");
			isValid = false;
			return isValid;
		}
		
		Float received;
		try {
			
			received = Float.parseFloat(model.getTxtReceived().getText());

		} catch (NumberFormatException ex) {
			discountPercentage = (float) 0;
			JOptionPane.showMessageDialog(null, "Enter valid value for Received!");
			isValid = false;
			return isValid;
		}

		return isValid;

	}

	public static void calculate(AddItemVM model) {
		if (validateCalculateInput(model)) {
			float total = 0;

			for (InvoiceItemModel item : model.getInvoice().getInvoiceItems()) {
				total = total + item.getSubTotal();
			}

			float discountPercent = Float.parseFloat(model.getTxtDiscountPercent().getText());
			float discountAmount = (total / 100) * discountPercent;
			float totalToPay = total - discountAmount;
			float received = Float.parseFloat(model.getTxtReceived().getText());
			float change = received - totalToPay;

			model.getInvoice().setTotal(total);
			model.getInvoice().setDiscountPercent(discountPercent);
			model.getInvoice().setDiscountAmount(discountAmount);
			model.getInvoice().setTotalToPay(totalToPay);
			model.getInvoice().setReceived(received);
			model.getInvoice().setChange(change);

			model.getTxtTotal().setText(String.valueOf(total));
			model.getTxtDiscountPercent().setText(String.valueOf(discountPercent));
			model.getTxtDiscountAmount().setText(String.valueOf(discountAmount));
			model.getTxtTotalToPay().setText(String.valueOf(totalToPay));
			model.getTxtReceived().setText(String.valueOf(received));
			model.getTxtChange().setText(String.valueOf(change));
		}
	}

	public static void fillTableWithInvoiceItems(AddItemVM model) {
		model.getTableModel().setRowCount(0);

		for (int i = 0; i < model.getInvoice().getInvoiceItems().size(); i++) {

			Object[] object = { model.getInvoice().getInvoiceItems().get(i).getTitle(),
					model.getInvoice().getInvoiceItems().get(i).getUnit(),
					model.getInvoice().getInvoiceItems().get(i).getSalePrice(),
					model.getInvoice().getInvoiceItems().get(i).getQuantity(),
					(model.getInvoice().getInvoiceItems().get(i).getSubTotal()) };
			model.getTableModel().addRow(object);
		}
		calculate(model);
	}

	public static void cash(InvoiceModel invoice) {
		invoice.setInvoiceNumber(generateInvoiceNumber());
		invoice.setCreatedBy(User.Username);
		invoice.setCreatedAt(new Timestamp(new Date().getTime()));

		invoiceDao.insertInvoice(invoice);
		int invoiceId = invoiceDao.getInvoiceLastId(invoice.getCreatedAt());
		invoiceDao.insertItems(invoiceId, invoice.getInvoiceItems());
		for (int i = 0; i < invoice.getInvoiceItems().size(); i++) {
			productDao.updateStock(invoice.getInvoiceItems().get(i).getTitle(),
					invoice.getInvoiceItems().get(i).getQuantity());
		}

		printInvoice(invoice);
	}

	public static void returnAll(int invoiceId) {
		invoiceDao.deleteInvocie(invoiceId);
		invoiceDao.deleteInvocieItems(invoiceId);
		productDao.updateStockRefund(invoiceId);
	}

	public static void refund(InvoiceModel invoice) {
		returnAll(invoice.getId());
		invoice.setRefInvoiceNumber(invoiceDao.getInvoiceLastNumber(invoice.getCreatedAt()));
		cash(invoice);
	}

	public static void printInvoice(InvoiceModel invoice) {
		try {
			String RESULT_FOLDER = "invoices\\";
			String outputName = "invoice" + invoice.getInvoiceNumber() + ".pdf";
			String invoiceHtml = PrepareHtmlPageFromInvoice(invoice);

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			Document document = new Document();
			PdfWriter writer = PdfWriter.getInstance(document, baos);
			document.open();
			InputStream is = new ByteArrayInputStream(invoiceHtml.getBytes());
			XMLWorkerHelper.getInstance().parseXHtml(writer, document, is);
			document.close();
			baos.writeTo(new FileOutputStream(new File(RESULT_FOLDER, outputName)));

			File myFile = new File(RESULT_FOLDER + outputName);
			Desktop.getDesktop().open(myFile);
			// myFile.canRead();
			myFile.setReadOnly();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String InvoiceHeader(InvoiceModel invoice) {
		String invoiceNumbCol = "Invoice Number: " + invoice.getInvoiceNumber();
		String invoiceTypeColumn = "Type: Cash";
		String invoiceDateCol = "Date: " + DateTimeToDateString(invoice.getCreatedAt());
		String invoiceCustCol = "Customer: " + invoice.getCustomer();
		String customerContactCol = "Customer Phone: " + invoice.getCustomerPhone();

		String invoiceMainHeader = "<table>	<tr><td>" + invoiceNumbCol + "</td>" + "<td>" + invoiceTypeColumn + "</td>"
				+ "<td>" + invoiceDateCol + "</td>" + "</tr>" + "<tr><td>" + invoiceCustCol + "</td>" + "<td>"
				+ customerContactCol + "</td>" + "</tr></table>";

		return invoiceMainHeader;
	}

	private static String DateTimeToDateString(Timestamp timestamp) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(timestamp);
	}

	public static String invoiceItemHeader() {
		String invoiceItemHeader = "<table><tbody><tr>" + "<th>	Serial # 	</th>" + "<th>	Item Name	</th>"
				+ "<th>	Quanity		</th>" + "<th>	Unit Price	</th>" + "<th>	Total Price </th></tr>";

		return invoiceItemHeader;
	}

	public static String PrepareHtmlPageFromInvoice(InvoiceModel invoice) {
		int invoiceItemsPerPage = 10;
		int serialNumber = 1;
		int pageitemsCounter = 0;

		double invoiceTotalPerPage = 0;
		StringBuilder sb = new StringBuilder();
		boolean isFirstPage = true, pageBodyClosed = false;

		for (InvoiceItemModel item : invoice.getInvoiceItems()) {
			if (isFirstPage || pageitemsCounter >= invoiceItemsPerPage) {
				pageitemsCounter = 0;
				invoiceTotalPerPage = 0;
				pageBodyClosed = false;
				sb.append("<!--?xml version=\"1.0\" encoding=\"UTF-8\"?-->");
				sb.append("<html><body>");
				sb.append(
						"<style> table {width:100%;border-collapse: separate;border-bottom: 1px solid black;}th {color: #4287f5;border: 1px solid black;}th, td {width: 150px;text-align: center;border-bottom: 1px solid black;padding: 5px;}h2 {color: #4287f5;}"
								+ ".footer { position: fixed; left: 0; bottom: 0; width: 100%; color: black; text-align: center;}</style>");
				sb.append("<table><tr><td><h2>Sale Invoice</h2></td></tr></table>");
				sb.append(InvoiceHeader(invoice));
				sb.append(invoiceItemHeader());
				isFirstPage = false;
			}

			String serialCol = "<td>" + serialNumber + "</td>";
			String itemCol = "<td>" + item.getTitle() + "</td>";
			String quantityCol = "<td>" + item.getQuantity() + "</td>";
			String unitPrice = "<td>" + item.getSalePrice() + "</td>";
			String totalCol = "<td>" + (item.getSalePrice() * item.getQuantity()) + "</td>";

			sb.append("<tr>");
			sb.append(serialCol);
			sb.append(itemCol);
			sb.append(quantityCol);
			sb.append(unitPrice);
			sb.append(totalCol);
			sb.append("</tr>");

			invoiceTotalPerPage = invoiceTotalPerPage + item.getSubTotal();
			pageitemsCounter++;
			serialNumber++;

			if (pageitemsCounter >= invoiceItemsPerPage) {
				sb.append(
						"</tbody></table><table class=\"footer\"><tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>Page Wise Sum</td><td>"
								+ invoiceTotalPerPage + " </td></tr>");
				sb.append("<tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>Total</td><td>" + invoice.getTotal()
						+ "</td></tr></table>");
				sb.append("<div style=\"page-break-before:always\">&nbsp;</div>");
				sb.append("</body></html>");
				pageBodyClosed = true;
			}
		}
		if (!pageBodyClosed) {
			sb.append(
					"</tbody></table><table class=\"footer\"><tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>Page Wise Sum</td><td>"
							+ invoiceTotalPerPage + " </td></tr>");
			sb.append("<tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>Total</td><td>" + invoice.getTotal()
					+ "</td></tr></table>");

			sb.append("</body></html>");
			pageBodyClosed = true;
		}
		writefile(sb.toString());
		return sb.toString();
	}

	public static void writefile(String data) {
		try {
			FileWriter myWriter = new FileWriter("invoicehtml.txt", true);
			myWriter.write(data);
			myWriter.close();
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

}
