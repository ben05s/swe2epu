package at.epu.DataAccessLayer.DataModels;

public abstract class InBillDataModel extends BackofficeTableModel {
	private static final long serialVersionUID = -8915600980699424021L;
	
	public InBillDataModel() {
		String[] columnNames_ = {"ID",
				"Buchungszeile",
				"Kontakt"};
		
		columnNames = columnNames_;
	}
}
