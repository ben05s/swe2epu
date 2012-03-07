package at.epu.DataAccessLayer.DataModels;

public abstract class BillRowDataModel extends BackofficeTableModel {
	private static final long serialVersionUID = -6391739671467084111L;
	
	public BillRowDataModel() {
		String [] columnNames_ = {"ID",
				"AusgRechnung_ID",
				"Angebot_ID",
				"Status",
				"Kommentar",
				"Anzahl",
				"Steuern",
				"Betrag"};
		
		columnNames = columnNames_;
	}
}