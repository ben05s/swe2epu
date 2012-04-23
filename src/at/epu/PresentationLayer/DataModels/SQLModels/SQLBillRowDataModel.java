package at.epu.PresentationLayer.DataModels.SQLModels;

import java.sql.SQLException;
import at.epu.PresentationLayer.DataModels.BackofficeTableModel;
import at.epu.PresentationLayer.DataModels.BillRowDataModel;

public class SQLBillRowDataModel extends BillRowDataModel {
	private static final long serialVersionUID = 1259019063593769044L;
	
	public SQLBillRowDataModel() throws Exception {
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
}
