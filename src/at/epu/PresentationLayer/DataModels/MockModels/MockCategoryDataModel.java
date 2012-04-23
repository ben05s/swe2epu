package at.epu.PresentationLayer.DataModels.MockModels;

import at.epu.PresentationLayer.DataModels.BackofficeTableModel;
import at.epu.PresentationLayer.DataModels.CategoryDataModel;
/*
 * ID | Name
 */
public class MockCategoryDataModel extends CategoryDataModel{
	private static final long serialVersionUID = -7645574178851868140L;
	
	public MockCategoryDataModel() {		
		Object[][] data_ = {
			    {new Integer(1), "Einnahme"},
			    {new Integer(2), "Ausgabe"},
			    {new Integer(3), "Steuer"}
			};
		
		setData(data_);
	}
	
	@Override
	public void saveData(BackofficeTableModel model, Object[] data_){
		getMockDataProvider().saveData(model, data_);
	}
	
	@Override
	public void updateData(BackofficeTableModel model, Object[] data_, int rowindex) {	
		getMockDataProvider().updateData(model, data_, rowindex);
	}
	
	@Override
	public void deleteData(BackofficeTableModel model, int rowindex) {
		getMockDataProvider().deleteData(model, rowindex);
	}
}
