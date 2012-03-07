package at.epu.DataAccessLayer.DataModels;

public abstract class CategoryDataModel extends BackofficeTableModel {
	private static final long serialVersionUID = -5289173316101225933L;
	
	public CategoryDataModel() {
		String[] columnNames_ = {"ID",
		"Name"};
		
		columnNames = columnNames_;
	}
}
