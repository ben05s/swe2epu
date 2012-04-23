package at.epu.PresentationLayer.DataModels.SQLModels;

import java.sql.SQLException;
import at.epu.PresentationLayer.DataModels.BackofficeTableModel;
import at.epu.PresentationLayer.DataModels.ContactDataModel;

public class SQLContactDataModel extends ContactDataModel {
	private static final long serialVersionUID = -4462317932452701225L;
	
	public SQLContactDataModel() {
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
