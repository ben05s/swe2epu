package at.epu.DataAccessLayer.DataModels.MockModels;

import java.text.SimpleDateFormat;
import java.util.Date;

import at.epu.DataAccessLayer.DataModels.BookRowDataModel;
/*
 * Index(PK) | ID(PK) | EingRechnung(FK) | AusgRechnung(FK) 
 * | Betrag | Umsatzsteuer | Buchungsdatum | Kategorie(FK)
 */
public class MockBookRowDataModel extends BookRowDataModel {

	private static final long serialVersionUID = 1845438830429021423L;
	
	public MockBookRowDataModel() {
		Object [][] data_ = {
				{new Integer(1), new Integer(1), new Integer(1), null, new Double(100000.00),
				 new Double(0.20), new SimpleDateFormat("dd.MM.yyyy").format(new Date()),
				 new Integer(1)},
				{new Integer(2), new Integer(2), new Integer(1), null, new Double(1500.00),
				 new Double(0.50), new SimpleDateFormat("dd.MM.yyyy").format(new Date()),
				 new Integer(3)
				}
		};
		
		data = data_;
	}
	
	public void filterDataModel(String filterString) {
		;
	}
}
