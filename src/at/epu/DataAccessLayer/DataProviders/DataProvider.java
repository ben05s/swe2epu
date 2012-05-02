package at.epu.DataAccessLayer.DataProviders;

import at.epu.DataAccessLayer.DataObjects.DataObjectCollection;

public interface DataProvider {
	public class DataProviderException extends Exception {
		public DataProviderException() {
			
		}
		
		public DataProviderException(String str) {
			super(str);
		}
	}
	
	public int getNextIdForTable(String tableName);
	public DataObjectCollection selectAll(String tableName) throws DataProviderException;
	public void syncData(String tableName, DataObjectCollection collection) throws DataProviderException;
}
