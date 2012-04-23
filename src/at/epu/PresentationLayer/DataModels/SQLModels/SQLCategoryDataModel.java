package at.epu.PresentationLayer.DataModels.SQLModels;

import java.sql.SQLException;
import at.epu.PresentationLayer.DataModels.BackofficeTableModel;
import at.epu.PresentationLayer.DataModels.CategoryDataModel;

public class SQLCategoryDataModel extends CategoryDataModel{
	private static final long serialVersionUID = -2567049208593892316L;
	
	public SQLCategoryDataModel() {
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
