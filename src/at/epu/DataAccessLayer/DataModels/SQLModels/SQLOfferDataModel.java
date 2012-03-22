package at.epu.DataAccessLayer.DataModels.SQLModels;

import java.sql.Connection;

import at.epu.DataAccessLayer.SQLQueryProvider;
import at.epu.DataAccessLayer.DataModels.OfferDataModel;

public class SQLOfferDataModel extends OfferDataModel {
	private static final long serialVersionUID = 3836902474382893474L;
	
	public SQLOfferDataModel(Connection databaseHandle) throws Exception {
		sqlProvider = new SQLQueryProvider(databaseHandle);
		Object[][] data_ = sqlProvider.selectAll(this);
		
		setData(data_);
	}
}
