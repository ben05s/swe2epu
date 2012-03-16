package at.epu.DataAccessLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import at.epu.DataAccessLayer.DataModels.SQLModels.SQLBankAccountDataModel;
import at.epu.DataAccessLayer.DataModels.SQLModels.SQLBillRowDataModel;
import at.epu.DataAccessLayer.DataModels.SQLModels.SQLBookRowDataModel;
import at.epu.DataAccessLayer.DataModels.SQLModels.SQLCategoryDataModel;
import at.epu.DataAccessLayer.DataModels.SQLModels.SQLContactDataModel;
import at.epu.DataAccessLayer.DataModels.SQLModels.SQLCustomerDataModel;
import at.epu.DataAccessLayer.DataModels.SQLModels.SQLInBillDataModel;
import at.epu.DataAccessLayer.DataModels.SQLModels.SQLOfferDataModel;
import at.epu.DataAccessLayer.DataModels.SQLModels.SQLOutBillDataModel;
import at.epu.DataAccessLayer.DataModels.SQLModels.SQLProjectDataModel;

public class DatabaseDataSource extends DataSource {
	Connection databaseHandle = null;
	
	public DatabaseDataSource(String databaseName) {
		
	
		String db_url = "jdbc:hsqldb:file:" + databaseName;
		
		try {
			databaseHandle = DriverManager.getConnection(db_url, "SA", "");	
		} catch (SQLException e) {
			System.err.println("Could not open connection to database at " + db_url);
			System.exit(0);
		} 
		
		contactModel = new SQLContactDataModel(databaseHandle);
		customerModel = new SQLCustomerDataModel();
		offerModel = new SQLOfferDataModel();
		projectModel = new SQLProjectDataModel();
		bankaccountModel = new SQLBankAccountDataModel();
		outbillModel = new SQLOutBillDataModel();
		billrowModel = new SQLBillRowDataModel();
		bookrowModel = new SQLBookRowDataModel();
		categoryModel = new SQLCategoryDataModel();
		inbillModel = new SQLInBillDataModel();
	}
}
