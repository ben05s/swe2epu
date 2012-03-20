package at.epu.DataAccessLayer.DataModels;

public abstract class CustomerDataModel extends BackofficeTableModel {
	private static final long serialVersionUID = -9038852003577424584L;
	
	public CustomerDataModel() {
		tableName = "Kunden";
		String[] mappingTableName_ = {"Angebote_Mapping"};
		String[] foreignTableName_ = {"Angebote"};
		String[] foreignTableColumns_ = {"Angebote"};
		String[] foreignKeyColumns_ = {"angebot_mapping_id"};
		String[] foreignKeyFromMappingCol_ = {"angebot_id"};
		String[] desiredColFromForeignKey_ = {"titel"};
		
		mappingTableName = mappingTableName_;
		foreignTableName = foreignTableName_;
		foreignKeyColumns = foreignKeyColumns_;
		foreignKeyFromMappingCol = foreignKeyFromMappingCol_;
		desiredColFromForeignKey = desiredColFromForeignKey_;
		foreignTableColumns = foreignTableColumns_;
		
		String[] columnNames_ = {"ID",
				"Vorname",
                "Nachname",
                "Unternehmen",
                "Adresse",
                "Email",
                "Telefon",
                "Angebote"};
		
		setColumnNames(columnNames_);
		
		String[] addEditColNames_ = {"Vorname",
				"Nachname",
				"Unternehmen",
				"Adresse",
				"Email",
				"Telefon",
				"Angebote"};
		
		setAddEditColNames(addEditColNames_);
	}
}
