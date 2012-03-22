package at.epu.DataAccessLayer.DataModels.SQLModels;

import java.sql.Connection;

import at.epu.DataAccessLayer.SQLQueryProvider;
import at.epu.DataAccessLayer.DataModels.BankAccountDataModel;

public class SQLBankAccountDataModel extends BankAccountDataModel {
	private static final long serialVersionUID = -8371682062900518962L;
	
	public SQLBankAccountDataModel(Connection databaseHandle) throws Exception {
		sqlProvider = new SQLQueryProvider(databaseHandle);
		Object[][] data_ = sqlProvider.selectAll(this);
		
		setData(data_);
	}
}
