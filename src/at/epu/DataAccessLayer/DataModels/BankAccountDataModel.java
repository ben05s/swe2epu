package at.epu.DataAccessLayer.DataModels;

public abstract class BankAccountDataModel extends BackofficeTableModel {
	private static final long serialVersionUID = -7180241673211578694L;
	
	public BankAccountDataModel() {
		String [] columnNames_ = {"ID",
				"Eingangsrechnung ID",
				"Ausgangsrechnung ID",
				"Betrag",
				"Umsatzsteuer",
				"Buchungsdatum",
				"Kategorie"};

		setColumnNames(columnNames_);
		
		String [] addEditColNames_ = {"Eingangsrechnung ID",
				"Ausgangsrechnung ID",
				"Betrag",
				"Umsatzsteuer",
				"Buchungsdatum",
				"Kategorie"};
		
		setAddEditColNames(addEditColNames_);
	}
}
