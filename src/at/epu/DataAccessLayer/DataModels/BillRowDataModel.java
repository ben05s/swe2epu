package at.epu.DataAccessLayer.DataModels;

public abstract class BillRowDataModel extends BackofficeTableModel {
	private static final long serialVersionUID = -6391739671467084111L;
	
	public BillRowDataModel() {
		String [] columnNames_ = {"ID",
				"Ausgangsrechnung ID",
				"Angebot",
				"Kommentar",
				"Steuern",
				"Betrag"};
		
		setColumnNames(columnNames_);
	}
}