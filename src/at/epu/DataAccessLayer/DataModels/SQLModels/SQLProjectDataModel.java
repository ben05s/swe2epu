package at.epu.DataAccessLayer.DataModels.SQLModels;

import java.sql.Connection;
import java.sql.SQLException;

import at.epu.DataAccessLayer.SQLQueryProvider;
import at.epu.DataAccessLayer.DataModels.BackofficeTableModel;
import at.epu.DataAccessLayer.DataModels.ProjectDataModel;

public class SQLProjectDataModel extends ProjectDataModel {
	private static final long serialVersionUID = -6826087926269956822L;
	
	public SQLProjectDataModel(Connection databaseHandle) throws Exception {
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
		deleteChooseIndex();
	}
}
