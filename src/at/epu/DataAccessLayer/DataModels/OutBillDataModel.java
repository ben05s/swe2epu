package at.epu.DataAccessLayer.DataModels;

public abstract class OutBillDataModel extends BackofficeTableModel {
	private static final long serialVersionUID = -2573224010608787797L;
	
	public OutBillDataModel() {
		String [] columnNames_ = {"Ausgangsrechnung ID",
				"Kunde",
				"Anz Rechnungszeilen",
				"Anz Buchungszeilen",
				"Status"};
		
		setColumnNames(columnNames_);
	}
}
