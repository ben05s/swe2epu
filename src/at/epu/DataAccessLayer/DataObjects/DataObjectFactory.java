package at.epu.DataAccessLayer.DataObjects;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DataObjectFactory {
	public static DataObject createObject(String tableName, ResultSet resultSet) throws SQLException {
		DataObject retVal = null;
		
		if( tableName.equals("Buchungszeilen") ) {
			BankAccountDataObject tmp = new BankAccountDataObject();
			
			tmp.setId(resultSet.getInt(1));
			tmp.setEingangsrechnung_id(resultSet.getInt(2));
			tmp.setAusgangsrechnung_id(resultSet.getInt(3));
			tmp.setBetrag(resultSet.getDouble(4));
			tmp.setUmsatzsteuer(resultSet.getDouble(5));
			tmp.setBuchungsdatum(resultSet.getDate(6));
			tmp.setKat_mapping_id(resultSet.getInt(7));
			
			retVal = tmp;
		} else if( tableName.equals("Rechnungszeilen") ) {
			BillRowDataObject tmp = new BillRowDataObject();
			
			tmp.setId(resultSet.getInt(1));
			tmp.setAusgangsrechnung_id(resultSet.getInt(2));
			tmp.setAngebot_id(resultSet.getInt(3));
			tmp.setKommentar(resultSet.getString(4));
			tmp.setSteuern(resultSet.getDouble(5));
			tmp.setBetrag(resultSet.getDouble(6));
			
			retVal = tmp;
		} else if( tableName.equals("Kategorien") ) {
			CategoryDataObject tmp = new CategoryDataObject();
			
			tmp.setId(resultSet.getInt(1));
			tmp.setName(resultSet.getString(2));
			
			retVal = tmp;
		} else if( tableName.equals("Kontakte") ) {
			ContactDataObject tmp = new ContactDataObject();

			tmp.setId(resultSet.getInt(1));
			tmp.setVorname(resultSet.getString(2));
			tmp.setNachname(resultSet.getString(3));
			tmp.setAdresse(resultSet.getString(4));
			tmp.setEmail(resultSet.getString(5));
			tmp.setTelefon(resultSet.getString(6));
			
			retVal = tmp;
		} else if( tableName.equals("Kunden") ) {
			CustomerDataObject tmp = new CustomerDataObject();
			
			tmp.setId(resultSet.getInt(1));
			tmp.setVorname(resultSet.getString(2));
			tmp.setNachname(resultSet.getString(3));
			tmp.setUnternehmen(resultSet.getString(4));
			tmp.setAdresse(resultSet.getString(5));
			tmp.setEmail(resultSet.getString(6));
			tmp.setTelefon(resultSet.getString(7));
			tmp.setAngebot_mapping_id(resultSet.getInt(8));
			
			retVal = tmp;
		} else if( tableName.equals("Eingangsrechnungen") ) {
			InBillDataObject tmp = new InBillDataObject();

			tmp.setId(resultSet.getInt(1));
			tmp.setRechnungskürzel(resultSet.getString(2));
			tmp.setKontakt_id(resultSet.getInt(3));
			tmp.setRzeile_mapping_id(resultSet.getInt(4));
			tmp.setBzeile_mapping_id(resultSet.getInt(5));
			tmp.setStatus(resultSet.getString(6));
			
			retVal = tmp;
		} else if( tableName.equals("Angebote") ) {
			OfferDataObject tmp = new OfferDataObject();

			tmp.setId(resultSet.getInt(1));
			tmp.setTitel(resultSet.getString(2));
			tmp.setKunde_id(resultSet.getInt(3));
			tmp.setSumme(resultSet.getDouble(4));
			tmp.setDauer(resultSet.getInt(5));
			tmp.setDatum(resultSet.getDate(6));
			tmp.setChance(resultSet.getDouble(7));
			
			retVal = tmp;
		} else if( tableName.equals("Ausgangsrechnungen") ) {
			OutBillDataObject tmp = new OutBillDataObject();
			
			tmp.setId(resultSet.getInt(1));
			tmp.setRechnungskürzel(resultSet.getString(2));
			tmp.setKunde_id(resultSet.getInt(3));
			tmp.setRzeile_mapping_id(resultSet.getInt(4));
			tmp.setBzeile_mapping_id(resultSet.getInt(5));
			tmp.setStatus(resultSet.getString(6));
			
			retVal = tmp;
		} else if( tableName.equals("Projekte") ) {
			ProjectDataObject tmp = new ProjectDataObject();
			
			tmp.setId(resultSet.getInt(1));
			tmp.setTitel(resultSet.getString(2));
			tmp.setAngebot_id(resultSet.getInt(3));
			tmp.setAusgr_mapping_id(resultSet.getInt(4));
			tmp.setZeit(resultSet.getDouble(5));
			
			retVal = tmp;
		} else {
			System.err.println("[ERROR][DataObjectFactory] You requested an object for a table that is not defined. (table = " + tableName + " )");
		}
		
		return retVal;
	}
}
