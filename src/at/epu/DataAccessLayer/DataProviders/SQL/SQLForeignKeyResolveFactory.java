package at.epu.DataAccessLayer.DataProviders.SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import at.epu.DataAccessLayer.DataObjects.DataObject;
import at.epu.DataAccessLayer.DataProviders.DataProvider.DataProviderException;

public class SQLForeignKeyResolveFactory {
	static Connection databaseHandle = null;
	
	public static void setDatabaseHandle(Connection databaseHandle) {
		SQLForeignKeyResolveFactory.databaseHandle = databaseHandle;
	}
	
	public static int getForeignNameResult(String tableName, String fieldName, String name) throws DataProviderException {
		int retVal = 0;
		
		String sql = "SELECT id FROM " + tableName + " WHERE " + fieldName + " = ?";
		
		PreparedStatement statement = null;
		
		try {
			statement = databaseHandle.prepareStatement(sql);

			statement.setString(1, name);
			
			if( statement.execute() ) {
				ResultSet result = statement.getResultSet();
				
				while( result.next() ) {
					return result.getInt(1);
				}
			}
		} catch (SQLException e) {
			System.err.println("Failed to get name for foreign key " + tableName + ", fieldName = " + fieldName + ", name = " + name + ".");
			throw new DataProviderException(e.getMessage());
		}
		
		return retVal;
	}
	
	public static ArrayList<String> getForeignKeyResults(String tableName, DataObject object, int fieldIndex) throws DataProviderException {
		ArrayList<String> retVal = new ArrayList<String>();
		String fieldName = object.getFieldNames().get(fieldIndex);
		int foreignKey = Integer.parseInt( object.getFieldValues().get(fieldIndex).toString() );
		
		if( tableName.equals("Buchungszeilen") ) {
			if(fieldName.equals("eingangsrechnung_id")) {
				retVal = getNameForForeignKey("Eingangsrechnungen", foreignKey, "rechnungskürzel");
			} else if(fieldName.equals("ausgangsrechnung_id")) {
				retVal = getNameForForeignKey("Ausgangsrechnungen", foreignKey, "rechnungskürzel");
			} else if(fieldName.equals("kat_mapping_id")) {
				retVal = getAllNamesForTableNameWithMapping("Kategorien", "name", "kat_mapping", foreignKey);
			}
		} else if( tableName.equals("Kontakte") ) {
			// NOP
		} else if( tableName.equals("Kategorien") ) {
			// NOP
		} else if( tableName.equals("Kunden") ) {
			if(fieldName.equals("angebot_mapping_id")) {
				retVal = getAllNamesForTableNameWithMapping("Angebote", "titel", "angebote_mapping", foreignKey);
			}
		} else if( tableName.equals("Eingangsrechnungen") ) {
			if(fieldName.equals("kontakt_id")) {
				retVal = getNameForForeignKey("Kontakte", foreignKey, "nachname");
			} else if(fieldName.equals("bzeile_mapping_id")) {
				retVal = getAllNamesForTableNameWithMapping("Buchungszeilen", "id", "bzeilen_mapping", foreignKey);
			}
		} else if( tableName.equals("Angebote") ) {
			if(fieldName.equals("kunde_id")) {
				retVal = getNameForForeignKey("Kunden", foreignKey, "nachname");
			}
		} else if( tableName.equals("Ausgangsrechnungen") ) {
			if(fieldName.equals("kunde_id")) {
				retVal = getNameForForeignKey("Kunden", foreignKey, "nachname");
			} else if(fieldName.equals("rzeile_mapping_id")) {
				retVal = getAllNamesForTableNameWithMapping("Rechnungszeilen", "id", "rzeilen_mapping", foreignKey);
			} else if(fieldName.equals("bzeile_mapping_id")) {
				retVal = getAllNamesForTableNameWithMapping("Buchungszeilen", "id", "bzeilen_mapping", foreignKey);
			}
		} else if( tableName.equals("Projekte") ) {
			if(fieldName.equals("angebot_id")) {
				retVal = getNameForForeignKey("Angebote", foreignKey, "titel");
			} else if(fieldName.equals("ausgr_mapping_id")) {
				retVal = getAllNamesForTableNameWithMapping("Ausgangsrechnungen", "rechnungskürzel", "ausgangsrechnungen_mapping", foreignKey);
			}
		}  else if( tableName.equals("Rechnungszeilen") ) {
			if(fieldName.equals("angebot_id")) {
				retVal = getNameForForeignKey("Angebote", foreignKey, "titel");
			} else if(fieldName.equals("ausgangsrechnung_id")) {
				retVal = getNameForForeignKey("Ausgangsrechnungen", foreignKey, "rechnungskürzel");
			}
		}  
		else {
			System.err.println("[ERROR][SQLForeignKeyResolveFactory] You requested foreign key data that is not defined. (tableName = " +
							   tableName + ", fieldName = " + fieldName + " )");
			
			return null;
		}
		
		return retVal;
	}
	
	static ArrayList<String> getNameForForeignKey(String tableName, int foreignKey, String fieldName) throws DataProviderException {
		ArrayList<String> retVal = new ArrayList<String>();
		
		String sql = "SELECT " + fieldName + " FROM " + tableName + " WHERE id = ?";
		
		PreparedStatement statement = null;
		
		try {
			statement = databaseHandle.prepareStatement(sql);

			statement.setInt(1, foreignKey);
			
			if( statement.execute() ) {
				ResultSet result = statement.getResultSet();
				
				while( result.next() ) {
					
					retVal.add( result.getString(1) );
				}
				
				return retVal;
			}
		} catch (SQLException e) {
			System.err.println("Failed to get name for foreign key " + tableName + ", fieldName = " + fieldName + ", foreignKey = " + foreignKey + ".");
			throw new DataProviderException(e.getMessage());
		}
		
		return retVal;
	}
	
	static ArrayList<String> getAllNamesForTableNameWithMapping(String tableName, String fieldName, String mappingTableName, int foreignKey) throws DataProviderException {
		ArrayList<String> retVal = new ArrayList<String>();
		
		String sql = "SELECT * FROM " + mappingTableName + " WHERE id = ?";
		
		PreparedStatement statement = null;
		
		try {
			statement = databaseHandle.prepareStatement(sql);

			statement.setInt(1, foreignKey);
			
			ArrayList<Integer> foreignIndices = new ArrayList<Integer>();
			
			if( statement.execute() ) {
				ResultSet result = statement.getResultSet();

				while( result.next() ) {
					foreignIndices.add( result.getInt(2) );
				}
				
				for(Integer i : foreignIndices) {
					retVal.addAll( getNameForForeignKey(tableName, i, fieldName) );
				}
			}
		} catch (SQLException e) {
			System.err.println("Failed to get all name mappings from table: " + tableName);
			throw new DataProviderException(e.getMessage());
		}
		
		return retVal;
	}
}
