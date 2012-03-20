package at.epu.DataAccessLayer.DataModels;

public abstract class OutBillDataModel extends BackofficeTableModel {
	private static final long serialVersionUID = -2573224010608787797L;
	
	public OutBillDataModel() {
		tableName = "Ausgangsrechnungen";
		String[] mappingTableName_ = {"-","rzeilen_mapping","bzeilen_mapping"};
		String[] foreignTableName_ = {"Kunden","Rechnungszeilen","Buchungszeilen"};
		String[] foreignTableColumns_ = {"Kunde","Rechnungszeilen ID","Buchungszeilen ID"};
		String[] foreignKeyColumns_ = {"kunde_id","rzeile_mapping_id","bzeile_mapping_id"};
		String[] foreignKeyFromMappingCol_ = {"-","rechnungszeile_id","buchungszeile_id"};
		String[] desiredColFromForeignKey_ = {"nachname","id","id"};
		
		mappingTableName = mappingTableName_;
		foreignTableName = foreignTableName_;
		foreignKeyColumns = foreignKeyColumns_;
		foreignKeyFromMappingCol = foreignKeyFromMappingCol_;
		desiredColFromForeignKey = desiredColFromForeignKey_;
		foreignTableColumns = foreignTableColumns_;
		
		String [] columnNames_ = {"Ausgangsrechnung ID",
				"Kunde",
				"Rechnungszeilen ID",
				"Buchungszeilen ID",
				"Status"};
		
		setColumnNames(columnNames_);
		
		String [] addEditColNames_ = {"Kunde",
				"Status"};
		
		setAddEditColNames(addEditColNames_);
	}
}
