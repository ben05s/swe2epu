package at.epu.PresentationLayer.DataModels;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class DataModelFactory {
	HashMap<String, BackofficeTableModel> modelCache = null;
	
	public DataModelFactory() {
		modelCache = new HashMap<String, BackofficeTableModel>();
	}
	
	public BackofficeTableModel getModelForTableName(String tableName) {
		BackofficeTableModel model = null;
		
		if( (model = modelCache.get(tableName)) != null ) {
			return model;
		} else {
			model = createModelForTableName(tableName);
			modelCache.put(tableName, model);
			
			return model;
		}
	}
	
	BackofficeTableModel createModelForTableName(String tableName) {
		String[] tmp = null;
		ArrayList<String> colNames = null;
		
		if( tableName.equals("Buchungszeilen") ) {
			String[] columnNames_ = {"ID",
					"Eingangsrechnung",
					"Ausgangsrechnung",
					"Betrag",
					"Umsatzsteuer",
					"Buchungsdatum",
					"Kategorie"};
			
			tmp = columnNames_;
		} else if( tableName.equals("Rechnungszeilen") ) {
			String [] columnNames_ = {"ID",
					"Ausgangsrechnung",
					"Angebot",
					"Kommentar",
					"Steuern",
					"Betrag"};
			
			tmp = columnNames_;
		} else if( tableName.equals("Kategorien") ) {
			String[] columnNames_ = {"ID",
			"Name"};
			
			tmp = columnNames_;
		} else if( tableName.equals("Kontakte") ) {
			String[] columnNames_ = {"ID",
	                "Nachname",
					"Vorname",
	                "Adresse",
	                "Email",
	                "Telefon"};
			
			tmp = columnNames_;
		} else if( tableName.equals("Kunden") ) {
			String[] columnNames_ = {"ID",
	                "Nachname",
					"Vorname",
	                "Unternehmen",
	                "Adresse",
	                "Email",
	                "Telefon",
	                "Angebote"};
			
			tmp = columnNames_;
		} else if( tableName.equals("Eingangsrechnungen") ) {
			String [] columnNames_ = {"Eingangsrechnung ID",
					"Rechnungskürzel",
					"Kontakt",
					"Buchungszeilen ID",
					"Status"};
			
			tmp = columnNames_;
		} else if( tableName.equals("Angebote") ) {
			String[] columnNames_ = {"ID",
					"Titel",
					"Kunde",
	                "Summe",
	                "Dauer",
	                "Datum",
	                "Chance"};
			
			tmp = columnNames_;
		} else if( tableName.equals("Ausgangsrechnungen") ) {
			String [] columnNames_ = {"Ausgangsrechnung ID",
					"Rechnungskürzel",
					"Kunde",
					"Rechnungszeilen ID",
					"Buchungszeilen ID",
					"Status"};
			
			tmp = columnNames_;
		} else if( tableName.equals("Projekte") ) {
			String [] columnNames_ = {"ID",
					"Titel",
					"Angebot",
					"Ausgangsrechnungen"};
			
			tmp = columnNames_;
		} else {
			System.err.println("[ERROR][DataModelFactory] You requested a table model that is not defined. (tableModel = " + tableName + " )");
			
			return null;
		}
		
		colNames = new ArrayList<String>( Arrays.asList(tmp) );
		
		return new BackofficeTableModel(tableName, colNames);
	}
}
