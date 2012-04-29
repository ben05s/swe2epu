package at.epu.PresentationLayer.DataModels;

import at.epu.BusinessLayer.ApplicationManager;

public class AddEditStateFactory {
	public static AddEditState createAddEditStateForTableName(String tableName, BackofficeTableModel parent) {
		AddEditState state = new AddEditState(parent);
		
		if( tableName.equals("Buchungszeilen") ) {
			String [] addEditColNames_ = {"Eingangsrechnung",
					"Ausgangsrechnung",
					"Betrag",
					"Umsatzsteuer",
					"Buchungsdatum",
					"Kategorie"};
			
			state.setAddEditColNames(addEditColNames_);
		} else if( tableName.equals("Rechnungszeilen") ) {
			String [] addEditColNames_ = {"Angebot",
					"Kommentar",
					"Steuern",
					"Betrag"};
			
			state.setAddEditColNames(addEditColNames_);
		} else if( tableName.equals("Kategorien") ) {
			// NOP
		} else if( tableName.equals("Kontakte") ) {
			String[] addEditColNames_ = {"Vorname",
	                "Nachname",
	                "Adresse",
	                "Email",
	                "Telefon"};
			
			state.setAddEditColNames(addEditColNames_);
		} else if( tableName.equals("Kunden") ) {
			String[] addEditColNames_ = {"Vorname",
					"Nachname",
					"Unternehmen",
					"Adresse",
					"Email",
					"Telefon",
					"Angebote"};
			
			state.setAddEditColNames(addEditColNames_);
		} else if( tableName.equals("Eingangsrechnungen") ) {
			// NOP
		} else if( tableName.equals("Angebote") ) {
			String[] addEditColNames_ = {"Titel",
					"Kunde",
	                "Summe",
	                "Dauer",
	                "Datum",
	                "Chance"};
			
			state.setAddEditColNames(addEditColNames_);
		} else if( tableName.equals("Ausgangsrechnungen") ) {
			String [] addEditColNames_ = {"Rechnungskürzel",
					"Kunde",
					"Status"};
			
			state.setAddEditColNames(addEditColNames_);
		} else if( tableName.equals("Projekte") ) {
			String [] addEditColNames_ = {"Titel",
					"Angebot",
					"Ausgangsrechnungen"};
			
			state.setAddEditColNames(addEditColNames_);
		} else {
			System.err.println("[ERROR][AddEditStateFactory] You requested AddEditState data that is not defined. (tableName = " + tableName + " )");
			
			return null;
		}
		
		return state;
	}
}
