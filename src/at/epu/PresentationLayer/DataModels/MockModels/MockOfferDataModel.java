package at.epu.PresentationLayer.DataModels.MockModels;

import java.text.SimpleDateFormat;
import java.util.Date;
import at.epu.PresentationLayer.DataModels.BackofficeTableModel;
import at.epu.PresentationLayer.DataModels.OfferDataModel;
/*
 *  ID | Kunde_ID | Summe | Dauer | Datum | Umsetzungs Chance
 */
public class MockOfferDataModel extends OfferDataModel {
	private static final long serialVersionUID = -3046727189620679978L;
	
	public MockOfferDataModel() {		
		Object[][] data_ = {
			    {new Integer(1), "Admin billig A.", "Hur", new Double(20000.00), new Integer(365),
			     new SimpleDateFormat("dd.MM.yyyy").format(new Date()), new Double(0.55)},
			    {new Integer(2), "Gutes Projekt teuer A.", "Hur", new Double(150000.00), new Integer(180),
			     new SimpleDateFormat("dd.MM.yyyy").format(new Date()), new Double(0.45)},
			    {new Integer(3), "A003", "MrX", new Double(50000.00), new Integer(85), 
			     new SimpleDateFormat("dd.MM.yyyy").format(new Date()), new Double(0.80)},
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
