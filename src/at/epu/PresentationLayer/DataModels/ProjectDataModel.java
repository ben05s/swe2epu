package at.epu.PresentationLayer.DataModels;

public abstract class ProjectDataModel extends BackofficeTableModel {
	private static final long serialVersionUID = -4590807310742861522L;
	
	public ProjectDataModel() {
		tableName = "Projekte";
		String[] mappingTableName_ = {"-", "ausgangsrechnungen_mapping"};
		String[] foreignTableName_ = {"Angebote","Ausgangsrechnungen"};
		String[] foreignTableColumns_ = {"Angebot","Ausgangsrechnungen"};
		String[] foreignKeyColumns_ = {"angebot_id","ausgr_mapping_id"};
		String[] foreignKeyFromMappingCol_ = {"-", "ausgangsrechnung_id"};
		String[] desiredColFromForeignKey_ = {"titel","rechnungskürzel"};
		
		mappingTableName = mappingTableName_;
		foreignTableName = foreignTableName_;
		foreignKeyColumns = foreignKeyColumns_;
		foreignKeyFromMappingCol = foreignKeyFromMappingCol_;
		desiredColFromForeignKey = desiredColFromForeignKey_;
		foreignTableColumns = foreignTableColumns_;
		
		String [] columnNames_ = {"ID",
				"Titel",
				"Angebot",
				"Ausgangsrechnungen"};
		
		setColumnNames(columnNames_);
	
		String [] addEditColNames_ = {"Titel",
				"Angebot",
				"Ausgangsrechnungen"};
		
		setAddEditColNames(addEditColNames_);
	}
}
