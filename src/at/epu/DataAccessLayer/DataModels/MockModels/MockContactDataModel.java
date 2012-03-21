package at.epu.DataAccessLayer.DataModels.MockModels;

import at.epu.DataAccessLayer.MockDataProvider;
import at.epu.DataAccessLayer.DataModels.BackofficeTableModel;
import at.epu.DataAccessLayer.DataModels.ContactDataModel;

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
		mockProvider = new MockDataProvider();
		mockProvider.saveData(model, data_);
	}
}
