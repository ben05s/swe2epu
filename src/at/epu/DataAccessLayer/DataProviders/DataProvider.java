package at.epu.DataAccessLayer.DataProviders;

import java.util.ArrayList;

import at.epu.DataAccessLayer.DataObjects.DataObject;
import at.epu.DataAccessLayer.DataObjects.DataObjectCollection;

public interface DataProvider {
	public class DataProviderException extends Exception {

		private static final long serialVersionUID = 3568632986988450471L;

		public DataProviderException() {
			
		}
		
		public DataProviderException(String str) {
			super(str);
		}
	}
	
	public int getNextIdForTable(String tableName);
	public DataObjectCollection selectAll(String tableName) throws DataProviderException;
	public void syncData(String tableName, DataObjectCollection collection) throws DataProviderException;
	public ArrayList<String> resolveForeignKey(String tableName, DataObject object, int fieldIndex) throws DataProviderException;
}
