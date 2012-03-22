package at.epu.DataAccessLayer.DataModels.MockModels;

import at.epu.DataAccessLayer.MockDataProvider;
import at.epu.DataAccessLayer.DataModels.BackofficeTableModel;
import at.epu.DataAccessLayer.DataModels.BillRowDataModel;

/*
 * ID | AusgRechnung_ID | Angebot |Kommentar | Steuern | Betrag
 */
public class MockBillRowDataModel extends BillRowDataModel{

	private static final long serialVersionUID = -1087876451866035526L;
	
	public MockBillRowDataModel() {
		Object [][] data_ = {
				{new Integer(1), new Integer(2), "Admin billig A.", "Frische Äpfel",
					new Double(100.00), new Double(1252.00)},
				{new Integer(2), new Integer(2), "Admin billig A.", "Neuer Bildschirm",
					new Double(40.00), new Double(1003.00)},
				{new Integer(3), new Integer(1), "A003", "Arbeitsstunden",
					new Double(200.00), new Double(34000.00)},
				{new Integer(4), new Integer(1), "A003", "Zusatzkosten wegen Spezi Änderung",
					new Double(100.00), new Double(100050.00)}
		};

		setData(data_);
	}
	
	@Override
	public void saveData(BackofficeTableModel model, Object[] data_){
		mockProvider = new MockDataProvider();
		mockProvider.saveData(model, data_);
	}
	
	@Override
	public void updateData(BackofficeTableModel model, Object[] data_, int rowindex) {	
		mockProvider = new MockDataProvider();
		mockProvider.updateData(model, data_, rowindex);
	}
}
