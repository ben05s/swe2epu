package at.epu.DataAccessLayer.DataModels.SQLModels;

import java.sql.Connection;

import at.epu.DataAccessLayer.SQLQueryProvider;
import at.epu.DataAccessLayer.DataModels.CustomerDataModel;

public class SQLCustomerDataModel extends CustomerDataModel {
	private static final long serialVersionUID = 577982588626053494L;
	
	public SQLCustomerDataModel(Connection databaseHandle) throws Exception {
		SQLQueryProvider sqlProvider = new SQLQueryProvider();
		Object[][] data_ = sqlProvider.selectAll(this, databaseHandle);
		
		setData(data_);
	}
	/*
	@Override
	void insert(DataObject obj){
		SQLQueryProvider.insert(this, obj);
	}
	
	@Override
	void update(DataObject obj){
		SQLQueryProvider.update(this, obj);
	}
	
	@Override
	void delete(DataObject obj){
		SQLQueryProvider.delete(this, obj);
	}*/
}
