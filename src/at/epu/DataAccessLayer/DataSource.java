package at.epu.DataAccessLayer;

import at.epu.BusinessLayer.DatabaseManager.DatabaseType;
import at.epu.DataAccessLayer.DataObjects.DataObjectCollection;
import at.epu.DataAccessLayer.DataProviders.DataProvider;
import at.epu.DataAccessLayer.DataProviders.DataProvider.DataProviderException;
import at.epu.DataAccessLayer.DataProviders.MockDataProvider;
import at.epu.DataAccessLayer.DataProviders.SQLDataProvider;

public class DataSource {
	DataProvider dataProvider;
	
	public DataSource(DatabaseType type, String argString) {
		switch(type) {
		case DatabaseTypeMock:
			dataProvider = new MockDataProvider();
			break;
		case DatabaseTypeSQL:
			dataProvider = new SQLDataProvider(argString);
			break;
		}
	}
	
	public DataObjectCollection getAllObjectsForTableName(String tableName) throws DataProviderException {
		return dataProvider.selectAll(tableName);
	}
	
	public void synchronizeObjectsForTableName(String tableName, DataObjectCollection objects) throws DataProviderException {
		dataProvider.syncData(tableName, objects);
	}
}
