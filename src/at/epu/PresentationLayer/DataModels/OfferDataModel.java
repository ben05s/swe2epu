package at.epu.PresentationLayer.DataModels;

public abstract class OfferDataModel extends BackofficeTableModel {
	private static final long serialVersionUID = 8460071465554605263L;
	
	public OfferDataModel() {
		tableName = "Angebote";
		String[] mappingTableName_ = {"-"};
		String[] foreignTableName_ = {"Kunden"};
		String[] foreignTableColumns_ = {"Kunde"};
		String[] foreignKeyColumns_ = {"kunde_id"};
		String[] foreignKeyFromMappingCol_ = {"-"};
		String[] desiredColFromForeignKey_ = {"nachname"};
		
		mappingTableName = mappingTableName_;
		foreignTableName = foreignTableName_;
		foreignKeyColumns = foreignKeyColumns_;
		foreignKeyFromMappingCol = foreignKeyFromMappingCol_;
		desiredColFromForeignKey = desiredColFromForeignKey_;
		foreignTableColumns = foreignTableColumns_;
		
		String[] columnNames_ = {"ID",
				"Titel",
				"Kunde",
                "Summe",
                "Dauer",
                "Datum",
                "Chance"};
		
		setColumnNames(columnNames_);
		
		String[] addEditColNames_ = {"Titel",
				"Kunde",
                "Summe",
                "Dauer",
                "Datum",
                "Chance"};
		
		setAddEditColNames(addEditColNames_);
	}
}
