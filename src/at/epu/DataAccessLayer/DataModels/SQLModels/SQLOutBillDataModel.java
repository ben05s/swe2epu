package at.epu.DataAccessLayer.DataModels.SQLModels;

import java.sql.Connection;

import at.epu.DataAccessLayer.SQLQueryProvider;
import at.epu.DataAccessLayer.DataModels.OutBillDataModel;

public class SQLOutBillDataModel extends OutBillDataModel {
	private static final long serialVersionUID = -2612819716366533584L;
	
	public SQLOutBillDataModel(Connection databaseHandle) throws Exception {
		sqlProvider = new SQLQueryProvider(databaseHandle);
		Object[][] data_ = sqlProvider.selectAll(this);
		
		setData(data_);
	}
}
