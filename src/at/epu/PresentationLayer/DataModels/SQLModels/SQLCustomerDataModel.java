package at.epu.PresentationLayer.DataModels.SQLModels;

import java.sql.SQLException;
import at.epu.PresentationLayer.DataModels.BackofficeTableModel;
import at.epu.PresentationLayer.DataModels.CustomerDataModel;

public class SQLCustomerDataModel extends CustomerDataModel {
	private static final long serialVersionUID = 577982588626053494L;
	
	public SQLCustomerDataModel() throws Exception {
		Object[][] data_ = getSQLQueryProvider().selectAll(this);
		
		setData(data_);
	}
	
	@Override
	public void saveData(BackofficeTableModel model, Object[] data_){
		try {
			getSQLQueryProvider().saveData(this, data_);
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		setData(getSQLQueryProvider().selectAll(this));
		updateTableData();
		resetChoosenData();
		deleteChooseIndex();
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
