package at.epu.PresentationLayer.DataModels;

public abstract class InBillDataModel extends BackofficeTableModel {
	private static final long serialVersionUID = -8915600980699424021L;
	
	public InBillDataModel() {
		tableName = "Eingangsrechnungen";
		
		String [] columnNames_ = {"Eingangsrechnung ID",
				"Kontakt",
				"Anz Rechnungszeilen",
				"Anz Buchungszeilen",
				"Status"};
		
		setColumnNames(columnNames_);
	}
}
