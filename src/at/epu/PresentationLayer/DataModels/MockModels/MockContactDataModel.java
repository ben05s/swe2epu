package at.epu.PresentationLayer.DataModels.MockModels;

import at.epu.PresentationLayer.DataModels.BackofficeTableModel;
import at.epu.PresentationLayer.DataModels.ContactDataModel;

/*
 * ID | Vorname | Nachname | Adresse | Email | Telefon 
 */
public class MockContactDataModel extends ContactDataModel {
	private static final long serialVersionUID = 5426244662861198543L;
	
	public MockContactDataModel() {
		Object[][] data_ = {
			    {new Integer(1), "Kathy", "Smith",
			     "Landstr 33/7", "sd.sd@gmx.at", "066034212502"},
			    {new Integer(2), "John", "Doe",
			     "Hansgasse 43/2", "asd.asd@gmail.com", null},
			    {new Integer(3), "Sue", "Black",
			     "Hufoasdgasse 89/12", "asgdgs.s@chello.at", "06646342325"}
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
