package at.epu.DataAccessLayer.DataProviders;

public interface DataProvider {
	//public DataObjectCollection selectAll(BackofficeTableModel model);
	public void saveData();
	public void deleteData();
	public void updateData();
}
