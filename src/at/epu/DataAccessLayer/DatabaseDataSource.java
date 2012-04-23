package at.epu.DataAccessLayer;

import java.sql.DriverManager;
import java.sql.SQLException;

import at.epu.PresentationLayer.DataModels.SQLModels.*;

public class DatabaseDataSource extends DataSource {	
	public DatabaseDataSource(String databaseName) throws Exception {
		String db_url = "jdbc:hsqldb:file:" + databaseName;
		
		try {
			databaseHandle = DriverManager.getConnection(db_url, "SA", "");	
			
		} catch (SQLException e) {
			System.err.println("Could not open connection to database at " + db_url);
			System.exit(0);
		} 
		
		
		contactModel = new SQLContactDataModel();
		customerModel = new SQLCustomerDataModel();
		offerModel = new SQLOfferDataModel();
		projectModel = new SQLProjectDataModel();
		outbillModel = new SQLOutBillDataModel();
		bankaccountModel = new SQLBankAccountDataModel();
		billrowModel = new SQLBillRowDataModel();
		categoryModel = new SQLCategoryDataModel();
		inbillModel = new SQLInBillDataModel();
	}
}
