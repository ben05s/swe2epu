package at.epu.DataAccessLayer.DataModels;

public abstract class OfferDataModel extends BackofficeTableModel {
	private static final long serialVersionUID = 8460071465554605263L;
	
	public OfferDataModel() {
		String[] columnNames_ = {"ID",
				"Titel",
				"Kunde",
                "Summe",
                "Dauer",
                "Datum",
                "Chance"};
		
		setColumnNames(columnNames_);
		
		String[] addEditColNames_ = {"Titel",
				"Kunde",
                "Summe",
                "Dauer",
                "Datum",
                "Chance"};
		
		setAddEditColNames(addEditColNames_);
	}
}
