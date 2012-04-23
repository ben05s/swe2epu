package at.epu.PresentationLayer.DataModels;

public abstract class ContactDataModel extends BackofficeTableModel {
	private static final long serialVersionUID = -6391739671467084101L;
	
	public ContactDataModel() {
		tableName = "Kontakte";
		String[] mappingTableName_ = {"-"};
		String[] foreignTableName_ = {"-"};
		String[] foreignTableColumns_ = {"-"};
		String[] foreignKeyColumns_ = {"-"};
		String[] foreignKeyFromMappingCol_ = {"-"};
		String[] desiredColFromForeignKey_ = {"-"};
		
		mappingTableName = mappingTableName_;
		foreignTableName = foreignTableName_;
		foreignKeyColumns = foreignKeyColumns_;
		foreignKeyFromMappingCol = foreignKeyFromMappingCol_;
		desiredColFromForeignKey = desiredColFromForeignKey_;
		foreignTableColumns = foreignTableColumns_;
		
		String[] columnNames_ = {"ID",
				"Vorname",
                "Nachname",
                "Adresse",
                "Email",
                "Telefon"};
		
		columnNames = columnNames_;
		
		String[] addEditColNames_ = {"Vorname",
                "Nachname",
                "Adresse",
                "Email",
                "Telefon"};
		
		setAddEditColNames(addEditColNames_);
	}
}
