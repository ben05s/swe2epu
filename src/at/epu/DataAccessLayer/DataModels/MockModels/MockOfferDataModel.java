package at.epu.DataAccessLayer.DataModels.MockModels;

import java.text.SimpleDateFormat;
import java.util.Date;

import at.epu.DataAccessLayer.DataModels.OfferDataModel;
/*
 *  ID | Kunde_ID | Summe | Dauer | Datum | Umsetzungs Chance
 */
public class MockOfferDataModel extends OfferDataModel {
	private static final long serialVersionUID = -3046727189620679978L;
	
	public MockOfferDataModel() {		
		Object[][] data_ = {
			    {new Integer(1), new Integer(1), new Double(20000.00), new Integer(365),
			     new SimpleDateFormat("dd.MM.yyyy").format(new Date()), new Double(0.55)},
			    {new Integer(2), new Integer(1), new Double(150000.00), new Integer(180),
			     new SimpleDateFormat("dd.MM.yyyy").format(new Date()), new Double(0.45)},
			    {new Integer(3), new Integer(3), new Double(50000.00), new Integer(85), 
			     new SimpleDateFormat("dd.MM.yyyy").format(new Date()), new Double(0.80)},
			};
		
		data = data_;
	}
	
	public void filterDataModel(String filterString) {
		// TODO Auto-generated method stub

	}
}
