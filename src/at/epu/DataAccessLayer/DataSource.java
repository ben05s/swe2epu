package at.epu.DataAccessLayer;

import java.util.ArrayList;

import at.epu.BusinessLayer.DatabaseManager.DatabaseType;
import at.epu.DataAccessLayer.DataObjects.DataObject;
import at.epu.DataAccessLayer.DataObjects.DataObjectCollection;
import at.epu.DataAccessLayer.DataProviders.DataProvider;
import at.epu.DataAccessLayer.DataProviders.DataProvider.DataProviderException;
import at.epu.DataAccessLayer.DataProviders.Mock.MockDataProvider;
import at.epu.DataAccessLayer.DataProviders.SQL.SQLDataProvider;

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
	
	public int getNextIdForTableName(String tableName) throws DataProviderException {
		return dataProvider.getNextIdForTable(tableName);
	}
	
	public DataObjectCollection getAllObjectsForTableName(String tableName) throws DataProviderException {
		return dataProvider.selectAll(tableName);
	}
	
	public void synchronizeObjectsForTableName(String tableName, DataObjectCollection objects) throws DataProviderException {
		dataProvider.syncData(tableName, objects);
	}
	
	public ArrayList<String> resolveForeignKeyForTableNameAndObject(String tableName, DataObject object, int fieldIndex) throws DataProviderException {
		return dataProvider.resolveForeignKey(tableName, object, fieldIndex);
	}
	
	public int getForeignKeyForName(String tableName, String fieldName, String name) throws DataProviderException {
		return dataProvider.getForeignKeyForName(tableName, fieldName, name);
	}
	
	public int createMappingEntryForValues(String tableName, String mappingTableName, ArrayList<Integer> keys) throws DataProviderException {
		return dataProvider.createMappingEntryForValues(tableName, mappingTableName, keys);
	}
}
