package at.epu.DataAccessLayer.DataModels;

public abstract class CustomerDataModel extends BackofficeTableModel {
	private static final long serialVersionUID = -9038852003577424584L;
	
	public CustomerDataModel() {
		String[] columnNames_ = {"ID",
				"Vorname",
                "Nachname",
                "Adresse",
                "Email",
                "Telefon",
                "Angebote"};
		
		columnNames = columnNames_;
	}
}
