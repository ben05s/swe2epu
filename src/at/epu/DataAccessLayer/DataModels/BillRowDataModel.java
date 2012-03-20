package at.epu.DataAccessLayer.DataModels;

public abstract class BillRowDataModel extends BackofficeTableModel {
	private static final long serialVersionUID = -6391739671467084111L;
	
	public BillRowDataModel() {
		tableName = "Rechnungszeilen";
		String[] mappingTableName_ = {"-"};
		String[] foreignTableName_ = {"Angebote"};
		String[] foreignTableColumns_ = {"Angebot"};
		String[] foreignKeyColumns_ = {"angebot_id"};
		String[] foreignKeyFromMappingCol_ = {"-"};
		String[] desiredColFromForeignKey_ = {"titel"};
		
		mappingTableName = mappingTableName_;
		foreignTableName = foreignTableName_;
		foreignKeyColumns = foreignKeyColumns_;
		foreignKeyFromMappingCol = foreignKeyFromMappingCol_;
		desiredColFromForeignKey = desiredColFromForeignKey_;
		foreignTableColumns = foreignTableColumns_;
		
		String [] columnNames_ = {"ID",
				"Ausgangsrechnung ID",
				"Angebot",
				"Kommentar",
				"Steuern",
				"Betrag"};
		
		setColumnNames(columnNames_);
		
		String [] addEditColNames_ = {"Angebot",
				"Kommentar",
				"Steuern",
				"Betrag"};
		
		setAddEditColNames(addEditColNames_);
	}
}