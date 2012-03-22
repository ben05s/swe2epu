package at.epu.DataAccessLayer.DataModels.SQLModels;

import java.sql.Connection;

import at.epu.DataAccessLayer.SQLQueryProvider;
import at.epu.DataAccessLayer.DataModels.ProjectDataModel;

public class SQLProjectDataModel extends ProjectDataModel {
	private static final long serialVersionUID = -6826087926269956822L;
	
	public SQLProjectDataModel(Connection databaseHandle) throws Exception {
		sqlProvider = new SQLQueryProvider(databaseHandle);
		Object[][] data_ = sqlProvider.selectAll(this);
		
		setData(data_);
	}
}
