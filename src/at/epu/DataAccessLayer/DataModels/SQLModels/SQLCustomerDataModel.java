package at.epu.DataAccessLayer.DataModels.SQLModels;

import java.sql.Connection;
import java.sql.SQLException;

import at.epu.DataAccessLayer.SQLQueryProvider;
import at.epu.DataAccessLayer.DataModels.BackofficeTableModel;
import at.epu.DataAccessLayer.DataModels.CustomerDataModel;

public class SQLCustomerDataModel extends CustomerDataModel {
	private static final long serialVersionUID = 577982588626053494L;
	
	public SQLCustomerDataModel(Connection databaseHandle) throws Exception {
		sqlProvider = new SQLQueryProvider(databaseHandle);
		Object[][] data_ = sqlProvider.selectAll(this);
		
		setData(data_);
	}
	
	@Override
	public void saveData(BackofficeTableModel model, Object[] data_){
		try {
			sqlProvider.saveData(this, data_);
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		setData(sqlProvider.selectAll(this));
		updateTableData();
		resetChoosenData();
	}
	
	/*
	@Override
	void update(DataObject obj){
		SQLQueryProvider.update(this, obj);
	}
	
	@Override
	void delete(DataObject obj){
		SQLQueryProvider.delete(this, obj);
	}*/
}
