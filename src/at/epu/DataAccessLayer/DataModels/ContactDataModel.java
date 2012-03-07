package at.epu.DataAccessLayer.DataModels;

public abstract class ContactDataModel extends BackofficeTableModel {
	private static final long serialVersionUID = -6391739671467084101L;
	
	public ContactDataModel() {
		String[] columnNames_ = {"ID",
				"Vorname",
                "Nachname",
                "Adresse",
                "Email",
                "Telefon"};
		
		columnNames = columnNames_;
	}
}
