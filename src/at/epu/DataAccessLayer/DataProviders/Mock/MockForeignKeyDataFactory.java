package at.epu.DataAccessLayer.DataProviders.Mock;

import java.util.ArrayList;

import at.epu.BusinessLayer.ApplicationManager;
import at.epu.DataAccessLayer.DataObjects.DataObject;
import at.epu.DataAccessLayer.DataObjects.DataObjectCollection;
import at.epu.DataAccessLayer.DataProviders.DataProvider.DataProviderException;

public class MockForeignKeyDataFactory {
	public static ArrayList<String> createForeignKeyResults(String tableName, DataObject object, int fieldIndex) {
		ArrayList<String> retVal = new ArrayList<String>();
		String fieldName = object.getFieldNames().get(fieldIndex);
		int foreignKey = Integer.parseInt( object.getFieldValues().get(fieldIndex).toString() );
		
		if( tableName.equals("Buchungszeilen") ) {
			if(fieldName.equals("eingangsrechnung_id")) {
				retVal = getNameForForeignKey("Eingangsrechnungen", foreignKey, "rechnungskuerzel");
			} else if(fieldName.equals("ausgangsrechnung_id")) {
				retVal = getNameForForeignKey("Ausgangsrechnungen", foreignKey, "rechnungskuerzel");
			} else if(fieldName.equals("kat_mapping_id")) {
				retVal = getAllNamesForTableName("Kategorien", "name");
			}
		} else if( tableName.equals("Kontakte") ) {
			// NOP
		} else if( tableName.equals("Kategorien") ) {
			// NOP
		} else if( tableName.equals("Kunden") ) {
			if(fieldName.equals("angebot_mapping_id")) {
				retVal = getAllNamesForTableName("Angebote", "titel");
			}
		} else if( tableName.equals("Eingangsrechnungen") ) {
			if(fieldName.equals("kontakt_id")) {
				retVal = getNameForForeignKey("Kontakte", foreignKey, "nachname");
			} else if(fieldName.equals("bzeile_mapping_id")) {
				retVal = getAllNamesForTableName("Buchungszeilen", "id");
			}
		} else if( tableName.equals("Angebote") ) {
			if(fieldName.equals("kunde_id")) {
				retVal = getNameForForeignKey("Kunden", foreignKey, "nachname");
			}
		} else if( tableName.equals("Ausgangsrechnungen") ) {
			if(fieldName.equals("kunde_id")) {
				retVal = getNameForForeignKey("Kunden", foreignKey, "nachname");
			} else if(fieldName.equals("rzeile_mapping_id")) {
				retVal = getAllNamesForTableName("Rechnungszeilen", "id");
			} else if(fieldName.equals("bzeile_mapping_id")) {
				retVal = getAllNamesForTableName("Buchungszeilen", "id");
			}
		} else if( tableName.equals("Projekte") ) {
			if(fieldName.equals("angebot_id")) {
				retVal = getNameForForeignKey("Angebote", foreignKey, "titel");
			} else if(fieldName.equals("ausgr_mapping_id")) {
				retVal = getAllNamesForTableName("Ausgangsrechnungen", "rechnungskuerzel");
			}
		} else {
			System.err.println("[ERROR][MockForeignKeyDataFactory] You requested foreign key mock data that is not defined. (tableName = " + tableName + " )");
			
			return null;
		}
		
		return retVal;
	}
	
	static ArrayList<String> getNameForForeignKey(String tableName, int foreignKey, String fieldName) {
		ArrayList<String> retVal = new ArrayList<String>();
		
		try {
			DataObjectCollection collection = ApplicationManager.getInstance().getDatabaseManager().getAllObjectsForTableName(tableName);
			
			if(collection.isEmpty()) {
				return retVal;
			}
			
			/** Get field index for fieldName */
			int fieldIndex = 0;
			for(String name : collection.get(0).getFieldNames()) {
				if(name.equals(fieldName)) {
					break;
				}
				fieldIndex++;
			}
			
			if( fieldIndex == 0) {
				return retVal;
			}
			
			for(DataObject object : collection) {
				if( object.getId() == foreignKey ) {
					retVal.add( object.getFieldValues().get(fieldIndex).toString() );
				}
			}
		} catch (DataProviderException e) {
			e.printStackTrace();
		}
		
		return retVal;
	}
	
	static ArrayList<String> getAllNamesForTableName(String tableName, String fieldName) {
		ArrayList<String> retVal = new ArrayList<String>();
		
		try {
			DataObjectCollection collection = ApplicationManager.getInstance().getDatabaseManager().getAllObjectsForTableName(tableName);
			
			if(collection.isEmpty()) {
				return retVal;
			}
			
			/** Get field index for fieldName */
			int fieldIndex = 0;
			for(String name : collection.get(0).getFieldNames()) {
				if(name.equals(fieldName)) {
					break;
				}
				fieldIndex++;
			}
			
			if( fieldIndex == 0) {
				return retVal;
			}
			
			for(DataObject object : collection) {
				retVal.add( object.getFieldValues().get(fieldIndex).toString() );
			}
		} catch (DataProviderException e) {
			e.printStackTrace();
		}
		
		return retVal;
	}
}
