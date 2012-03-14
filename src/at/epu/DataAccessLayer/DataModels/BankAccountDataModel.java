package at.epu.DataAccessLayer.DataModels;

public abstract class BankAccountDataModel extends BackofficeTableModel {
	private static final long serialVersionUID = -7180241673211578694L;
	
	public BankAccountDataModel() {
		String [] columnNames_ = {"ID",
				"Kontonummer",
				"Vorname",
				"Nachname",
				"Bank",
				"BLZ"};

		setColumnNames(columnNames_);
	}
}
