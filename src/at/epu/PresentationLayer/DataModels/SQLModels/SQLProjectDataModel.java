package at.epu.PresentationLayer.DataModels.SQLModels;

import java.sql.SQLException;
import at.epu.PresentationLayer.DataModels.BackofficeTableModel;
import at.epu.PresentationLayer.DataModels.ProjectDataModel;

public class SQLProjectDataModel extends ProjectDataModel {
	private static final long serialVersionUID = -6826087926269956822L;
	
	public SQLProjectDataModel() throws Exception {
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
