package at.epu.DataAccessLayer.DataModels;

public abstract class BookRowDataModel extends BackofficeTableModel {
	private static final long serialVersionUID = 4229937417100390790L;
	
	public BookRowDataModel() {
		String [] columnNames_ = {"Index",
				"ID",
				"EingRechnung",
				"AusgRechnung",
				"Betrag",
				"Umsatzsteuer",
				"Buchungsdatum",
				"Kategorie"};
		
		setColumnNames(columnNames_);
	}
}
