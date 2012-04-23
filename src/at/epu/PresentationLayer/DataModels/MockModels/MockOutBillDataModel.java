package at.epu.PresentationLayer.DataModels.MockModels;

import at.epu.PresentationLayer.DataModels.BackofficeTableModel;
import at.epu.PresentationLayer.DataModels.OutBillDataModel;
/*
 * ID(PK) | Kunde | Rechnungszeilen(Anz) | Buchungszeilen(Anz) | Status
 */
public class MockOutBillDataModel extends OutBillDataModel {
	private static final long serialVersionUID = -8018265297745567701L;
	
	public MockOutBillDataModel() {
		Object [][] data_ = {
				{new Integer(1), "R2054", "MrX", new Integer(2), new Integer(1), "bezahlt"},
				{new Integer(2), "R353", "Hur", new Integer(2), new Integer(1), "bezahlt"}
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