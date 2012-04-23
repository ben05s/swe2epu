package at.epu.PresentationLayer.DataModels.MockModels;

import at.epu.PresentationLayer.DataModels.BackofficeTableModel;
import at.epu.PresentationLayer.DataModels.ProjectDataModel;

/*
 * ID(PK) | Angebot_ID(FK) | AusgRechnung_ID(FK/PK)
 */
public class MockProjectDataModel extends ProjectDataModel {
	private static final long serialVersionUID = -4733926290403533192L;
	
	public MockProjectDataModel() {
		Object [][] data_ = {
				{new Integer(1), "Admin Tool", "Admin billig A.", new Integer(1)},
				{new Integer(2), "Gutes Projekt", "Gutes Projekt teuer A.", null},
				{new Integer(3), "Nicht so gutes Projekt", "A003", new Integer(3)}
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
