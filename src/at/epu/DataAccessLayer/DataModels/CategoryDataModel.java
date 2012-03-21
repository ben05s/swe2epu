package at.epu.DataAccessLayer.DataModels;

public abstract class CategoryDataModel extends BackofficeTableModel {
	private static final long serialVersionUID = -5289173316101225933L;
	
	public CategoryDataModel() {
		tableName = "Kategorien";
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
		"Name"};
		
		setColumnNames(columnNames_);
	}
}
