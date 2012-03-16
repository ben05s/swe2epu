package at.epu.DataAccessLayer.DataModels;

public abstract class OfferDataModel extends BackofficeTableModel {
	private static final long serialVersionUID = 8460071465554605263L;
	
	public OfferDataModel() {
		String[] columnNames_ = {"ID",
				"Kunde_ID",
                "Summe",
                "Dauer",
                "Datum",
                "Chance"};
		
		setColumnNames(columnNames_);
	}
}
