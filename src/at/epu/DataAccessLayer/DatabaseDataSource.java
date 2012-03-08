package at.epu.DataAccessLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import at.epu.DataAccessLayer.DataModels.SQLModels.*;

public class DatabaseDataSource extends DataSource {
	Connection databaseHandle = null;
	
	public DatabaseDataSource(String databaseName) {
		contactModel = new SQLContactDataModel();
		customerModel = new SQLCustomerDataModel();
		offerModel = new SQLOfferDataModel();
		projectModel = new SQLProjectDataModel();
		bankaccountModel = new SQLBankAccountDataModel();
		outbillModel = new SQLOutBillDataModel();
		billrowModel = new SQLBillRowDataModel();
		bookrowModel = new SQLBookRowDataModel();
		categoryModel = new SQLCategoryDataModel();
		inbillModel = new SQLInBillDataModel();
	
		String db_url = "jdbc:hsqldb:file:" + databaseName;
		
		try {
			databaseHandle = DriverManager.getConnection(db_url, "SA", "");
		} catch (SQLException e) {
			System.err.println("Could not open connection to database at " + db_url);
			System.exit(0);
		}
	}
}
