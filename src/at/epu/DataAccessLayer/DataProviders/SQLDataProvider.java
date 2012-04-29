package at.epu.DataAccessLayer.DataProviders;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import at.epu.DataAccessLayer.DataObjects.DataObject;
import at.epu.DataAccessLayer.DataObjects.DataObjectCollection;
import at.epu.DataAccessLayer.DataObjects.DataObject.DataObjectState;
import at.epu.DataAccessLayer.DataObjects.DataObjectFactory;

public class SQLDataProvider implements DataProvider {
	Connection databaseHandle = null;
	
	public SQLDataProvider(String databaseName) {
		String db_url = "jdbc:hsqldb:file:" + databaseName;
		
		try {
			databaseHandle = DriverManager.getConnection(db_url, "SA", "");	
			
		} catch (SQLException e) {
			System.err.println("Could not open connection to database at " + db_url);
			e.printStackTrace();
			System.exit(0);
		} 
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
			System.err.println("Failed to get all dataObjects from table: " + tableName);
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
				System.err.println("Failed to synchronize with database.");
				throw new DataProviderException(e.getMessage());
			}
			
			obj.setState(DataObjectState.DataObjectStateSynchronized);
		}
	}
	
	void insert(String tableName, DataObject object) throws SQLException {
		StringBuilder builder = new StringBuilder();
		
		builder.append("INSERT INTO " + tableName + " (");
		
		for(String fieldName : object.getFieldNames()) {
			builder.append(fieldName + ",");
		}
		
		builder.replace(builder.length()-2, builder.length()-1, ")");
		builder.append(" VALUES (");
		
		for(Object obj : object.getFieldValues()) {
			builder.append(obj + ",");
		}
		
		builder.replace(builder.length()-2, builder.length()-1, ")");
		
		PreparedStatement statement = databaseHandle.prepareStatement(builder.toString());
		
		statement.execute();
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
		ArrayList<Object> fieldValues = object.getFieldValues();
		
		for(int i = 0; i < object.getFieldNames().size(); i++) {
			String fieldName = fieldNames.get(i);
			Object value = fieldValues.get(i);
			builder.append(fieldName + "=" + value + ",");
		}
		
		builder.append(" WHERE id = ?");
		
		PreparedStatement statement = databaseHandle.prepareStatement(builder.toString());
		
		statement.setInt(1, object.getId());
		
		int numUpdates = statement.executeUpdate();
		
		if( numUpdates != 1) {
			throw new SQLException("[ERROR] Your update for one object updated more than one value.");
		}
	}
}
