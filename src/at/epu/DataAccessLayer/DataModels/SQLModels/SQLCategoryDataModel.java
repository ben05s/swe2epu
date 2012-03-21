package at.epu.DataAccessLayer.DataModels.SQLModels;

import java.sql.Connection;

import at.epu.DataAccessLayer.SQLQueryProvider;
import at.epu.DataAccessLayer.DataModels.CategoryDataModel;

public class SQLCategoryDataModel extends CategoryDataModel{
	private static final long serialVersionUID = -2567049208593892316L;
	
	public SQLCategoryDataModel(Connection databaseHandle) {
		sqlProvider = new SQLQueryProvider(databaseHandle);
		Object[][] data_ = sqlProvider.selectAll(this);
		
		setData(data_);
	}
}
