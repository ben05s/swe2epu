package at.epu.PresentationLayer.DataModels.MockModels;

import at.epu.PresentationLayer.DataModels.BackofficeTableModel;
import at.epu.PresentationLayer.DataModels.BillRowDataModel;

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
