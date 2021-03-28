package defaultPackage;



import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import views.Login;
import views.SaleInvoice;

public class MainClass {
public static void main(String[] args) {
		
		try {
			Date currentDate = new Date();  
	        System.out.println("Current Date: "+currentDate);  
	        
	        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	        System.out.println(formatter.format(currentDate));
			
			Login l = new Login();
			//SaleInvoice l = new SaleInvoice();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
