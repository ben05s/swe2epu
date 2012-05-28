package at.epu.BusinessLayer;

import java.util.ArrayList;

import at.epu.DataAccessLayer.*;
import at.epu.DataAccessLayer.DataObjects.DataObject;
import at.epu.DataAccessLayer.DataObjects.DataObjectCollection;
import at.epu.DataAccessLayer.DataProviders.DataProvider.DataProviderException;

public class DatabaseManager {
	DataSource dataSource = null;
	DatabaseType type = null;
	
	public enum DatabaseType {
		DatabaseTypeMock,
		DatabaseTypeSQL
	}
	
	/** No arguments, default to Mock */
	public DatabaseManager() {
		type = DatabaseType.DatabaseTypeMock;
		dataSource = new DataSource(type, null);
	}
	
	/** argument is the database name, instantiate as SQL database */
	public DatabaseManager(String databaseName) {
		type = DatabaseType.DatabaseTypeSQL;
		dataSource = new DataSource(type, databaseName);
	}
	
	public int getNextIdForTableName(String tableName) throws DataProviderException {
		return dataSource.getNextIdForTableName(tableName);
	}
	
	public DataObjectCollection getAllObjectsForTableName(String tableName) throws DataProviderException {
		return dataSource.getAllObjectsForTableName(tableName);
	}
	
	public void synchronizeObjectsForTableName(String tableName, DataObjectCollection objects) throws DataProviderException {
		dataSource.synchronizeObjectsForTableName(tableName, objects);
	}
	
	public ArrayList<String> resolveForeignKeyForTableNameAndObject(String tableName, DataObject object, int fieldIndex) throws DataProviderException {
		return dataSource.resolveForeignKeyForTableNameAndObject(tableName, object, fieldIndex);
	}
	
	public int getForeignKeyForName(String tableName, String fieldName, String name) throws DataProviderException {
		return dataSource.getForeignKeyForName(tableName, fieldName, name);
	}
}
