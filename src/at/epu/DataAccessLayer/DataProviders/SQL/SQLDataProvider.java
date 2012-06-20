package at.epu.DataAccessLayer.DataProviders.SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.log4j.Logger;

import at.epu.DataAccessLayer.DataObjects.DataObject;
import at.epu.DataAccessLayer.DataObjects.DataObject.DataObjectState;
import at.epu.DataAccessLayer.DataObjects.DataObjectCollection;
import at.epu.DataAccessLayer.DataObjects.DataObjectFactory;
import at.epu.DataAccessLayer.DataProviders.DataProvider;

public class SQLDataProvider implements DataProvider {
	Connection databaseHandle = null;
	
	public SQLDataProvider(String databaseName) {
		String db_url = "jdbc:hsqldb:file:" + databaseName;
		
		try {
			databaseHandle = DriverManager.getConnection(db_url, "SA", "");	
			
			SQLForeignKeyResolveFactory.setDatabaseHandle(databaseHandle);
		} catch (SQLException e) {
			Logger.getLogger(SQLDataProvider.class.getName()).error("Could not open connection to database at " + db_url);
			e.printStackTrace();
			System.exit(0);
		} 
	}
	
	@Override
	public int getNextIdForTable(String tableName) {
		String sql = "SELECT MAX(id) FROM " + tableName;
		int retVal = 0;
		
		try {
			Statement stm = databaseHandle.createStatement();
			
			ResultSet rs = stm.executeQuery(sql);
			
			rs.next();
			retVal = rs.getInt(1) + 1;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return retVal;
	}
	
	@Override
	public DataObjectCollection selectAll(String tableName)
			throws DataProviderException {
		String selectString = "SELECT * FROM " + tableName;
		
		PreparedStatement statement = null;
		
		try {
			statement = databaseHandle.prepareStatement(selectString);

			if( statement.execute() ) {
				ResultSet result = statement.getResultSet();
				
				DataObjectCollection retVal = new DataObjectCollection();

				while( result.next() ) {
					retVal.add( DataObjectFactory.createObject(tableName, result) );
				}
				
				return retVal;
			} else {
				return new DataObjectCollection();
			}
		} catch (SQLException e) {
			Logger.getLogger(SQLDataProvider.class.getName()).error("Failed to get all dataObjects from table: " + tableName);
			throw new DataProviderException(e.getMessage());
		}
	}
	
	@Override
	public void syncData(String tableName, DataObjectCollection collection) throws DataProviderException {
		Iterator<DataObject> iterator = collection.iterator();
		
		while(iterator.hasNext()) {
			DataObject obj = iterator.next();
			try {
				if( obj.getState() == DataObjectState.DataObjectStateNew ) {
					insert(tableName, obj);
				} else if( obj.getState() == DataObjectState.DataObjectStateDeleted ) {
					delete(tableName, obj);
					iterator.remove();
				} else if( obj.getState() == DataObjectState.DataObjectStateModified ) {
					update(tableName, obj);
				}
			} catch(SQLException e) {
				Logger.getLogger(SQLDataProvider.class.getName()).error("Failed to synchronize with database.");
				throw new DataProviderException(e.getMessage());
			}
			
			obj.setState(DataObjectState.DataObjectStateSynchronized);
		}
	}
	
	void insert(String tableName, DataObject object) throws SQLException, DataProviderException {
		StringBuilder builder = new StringBuilder();
		
		builder.append("INSERT INTO " + tableName + " (");
		
		for(String fieldName : object.getFieldNames()) {
			builder.append(fieldName + ",");
		}
		
		builder.replace(builder.length()-1, builder.length(), ")");
		builder.append(" VALUES (");
		
		for(int i = 0; i < object.getFieldValues().size(); ++i) {
			builder.append("?,");
		}
		
		builder.replace(builder.length()-1, builder.length(), ")");
		
		String sql = builder.toString();
		
		PreparedStatement statement = databaseHandle.prepareStatement(sql);
		
		setParams(statement, object);
		
		statement.execute();
	}
	
	void setParams(PreparedStatement statement, DataObject object) throws SQLException {
		int index = 1;
		for(Object obj : object.getFieldValues()) {
			if(obj.getClass().equals(String.class)) {
				statement.setString(index, (String)obj);
			} else if(obj.getClass().equals(Integer.class)) {
				statement.setInt(index, (Integer)obj);
			} else if(obj.getClass().equals(Double.class)) {
				statement.setDouble(index, (Double)obj);
			} else if(obj.getClass().equals(java.sql.Date.class)) {
				statement.setDate(index, (java.sql.Date)obj);
			} else if(obj.getClass().equals(java.util.Date.class)) {
				statement.setDate(index, new java.sql.Date( ((java.util.Date)obj).getTime()));
			} else {
				Logger.getLogger(this.getClass().getName()).error("Can't transmit an object of type " + obj.getClass().getName() + " to the SQL database.");
			}
			
			index++;
		}
	}
	
	void delete(String tableName, DataObject object) throws SQLException {
		String deleteString = "DELETE FROM " + tableName + " WHERE id = ?";
		
		PreparedStatement statement = databaseHandle.prepareStatement(deleteString);
		
		statement.setInt(1, object.getId());
		
		statement.execute();
	}
	
	void update(String tableName, DataObject object) throws SQLException {		
		StringBuilder builder = new StringBuilder();
		
		builder.append("UPDATE " + tableName + " SET ");
		
		ArrayList<String> fieldNames = object.getFieldNames();
		
		for(int i = 0; i < object.getFieldNames().size(); i++) {
			String fieldName = fieldNames.get(i);
			Object value = "?";
			
			builder.append(fieldName + "=" + value);
			
			if( i != object.getFieldNames().size() - 1) {
				builder.append(", ");
			}
		}
		
		builder.append(" WHERE id = ?");
		
		String sql = builder.toString();
		
		PreparedStatement statement = databaseHandle.prepareStatement(sql);
		
		setParams(statement, object);
		
		statement.setInt(object.getFieldValues().size() + 1, object.getId());
		
		int numUpdates = statement.executeUpdate();
		
		if( numUpdates != 1) {
			throw new SQLException("[ERROR] Your update for one object updated more than one value.");
		}
	}

	@Override
	public ArrayList<String> resolveForeignKey(String tableName,
			DataObject object, int fieldIndex) throws DataProviderException {
		return SQLForeignKeyResolveFactory.getForeignKeyResults(tableName, object, fieldIndex);
	}

	@Override
	public int getForeignKeyForName(String tableName, String fieldName,
			String name) throws DataProviderException {
		return SQLForeignKeyResolveFactory.getForeignNameResult(tableName, fieldName, name);
	}

	@Override
	public int createMappingEntryForValues(String tableName,
			String mappingTableName, ArrayList<Integer> keys)
			throws DataProviderException {
		int id = getNextIdForTable(mappingTableName);
		
		for(int key : keys) {
			String sql = "INSERT INTO " + mappingTableName + " VALUES (?, ?)";
			
			try {
				PreparedStatement statement = databaseHandle.prepareStatement(sql);
				
				statement.setInt(1, id);
				statement.setInt(2, key);
			
				statement.execute();
			} catch (SQLException e) {
				throw new DataProviderException(e.getMessage());
			}
		}
		
		return id;
	}
}
