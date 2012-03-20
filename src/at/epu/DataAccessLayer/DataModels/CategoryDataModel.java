package at.epu.DataAccessLayer.DataModels;

public abstract class CategoryDataModel extends BackofficeTableModel {
	private static final long serialVersionUID = -5289173316101225933L;
	
	public CategoryDataModel() {
		tableName = "Kategorie";
		
		String[] columnNames_ = {"ID",
		"Name"};
		
		setColumnNames(columnNames_);
	}
}
