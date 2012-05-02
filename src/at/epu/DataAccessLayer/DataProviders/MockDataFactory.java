package at.epu.DataAccessLayer.DataProviders;

import java.text.SimpleDateFormat;
import java.util.Date;

import at.epu.DataAccessLayer.DataObjects.BankAccountDataObject;
import at.epu.DataAccessLayer.DataObjects.ContactDataObject;
import at.epu.DataAccessLayer.DataObjects.DataObject;
import at.epu.DataAccessLayer.DataObjects.DataObject.DataObjectState;
import at.epu.DataAccessLayer.DataObjects.DataObjectCollection;

public class MockDataFactory {	
	DataObjectCollection createObjects(String tableName) {
		DataObjectCollection collection = new DataObjectCollection();
		
		if( tableName.equals("Buchungszeilen") ) {
			BankAccountDataObject obj1 = new BankAccountDataObject();
			
			obj1.setAusgangsrechnung_id(1);
			obj1.setEingangsrechnung_id(1);
			obj1.setId( new Integer(2) );
			obj1.setBetrag( new Double(45110.00) );
			obj1.setBuchungsdatum( new Date() );
			obj1.setUmsatzsteuer( new Double(1252.00+1003.00) );
			obj1.setKat_mapping_id(1);
			
			collection.add(obj1);
			
			BankAccountDataObject obj2 = new BankAccountDataObject();
			
			obj2.setAusgangsrechnung_id(1);
			obj2.setEingangsrechnung_id(1);
			obj2.setId( new Integer(2) );
			obj2.setBetrag( new Double(45110.00) );
			obj2.setBuchungsdatum( new Date() );
			obj2.setUmsatzsteuer( new Double(1252.00+1003.00) );
			obj2.setKat_mapping_id(1);
			
			collection.add(obj2);
		} else if( tableName.equals("Rechnungszeilen") ) {
			ContactDataObject obj1 = new ContactDataObject();
			
			obj1.setId(new Integer(1));
			obj1.setVorname("Kathy");
			obj1.setNachname("Smith");
			obj1.setEmail("sd.sd@gmx.at");
			obj1.setTelefon("066034212502");
			obj1.setAdresse("Landstr 33/7");
			
			collection.add(obj1);
			
			ContactDataObject obj2 = new ContactDataObject();
			
			obj2.setId(new Integer(2));
			obj2.setVorname("John");
			obj2.setNachname("Doe");
			obj2.setEmail("asd.asd@gmail.com");
			obj2.setTelefon("066034212522");
			obj2.setAdresse("Hansgasse 43/2");
			
			collection.add(obj2);
		} else if( tableName.equals("Kategorien") ) {
			Object[][] data_ = {
				    {new Integer(1), "Einnahme"},
				    {new Integer(2), "Ausgabe"},
				    {new Integer(3), "Steuer"}
				};
		} else if( tableName.equals("Kontakte") ) {
			Object[][] data_ = {
				    {new Integer(1), "Kathy", "Smith",
				     "Landstr 33/7", "sd.sd@gmx.at", "066034212502"},
				    {new Integer(2), "John", "Doe",
				     "Hansgasse 43/2", "asd.asd@gmail.com", null},
				    {new Integer(3), "Sue", "Black",
				     "Hufoasdgasse 89/12", "asgdgs.s@chello.at", "06646342325"}
				};
		} else if( tableName.equals("Kunden") ) {
			Object[][] data_ = {
				    {new Integer(1), "Ben", "Hur", "Mobil Gmbh",
				     "Petergasse 345/7", "sdas.t@gmx.at", "06603412402", "Admin billig A."},
				    {new Integer(2), "Fritz", "DD", "Privat", 
				     "Zeilengasse 143/22", "f.DD@gmail.com", "06761252042", null},
				    {new Integer(3), "Heinz", "MrX", "Haus Gmbh",
				     "Franzgasse 9/12", "x.x@chello.at", "066465352325", "A003"},
				};
		} else if( tableName.equals("Eingangsrechnungen") ) {
			Object [][] data_ = {
					{new Integer(1), "InBill123", "Smith", new Integer(0), new Integer(0), "offen"},
					{new Integer(2), "InBill932", "Black", new Integer(0), new Integer(0), "offen"}
			};
		} else if( tableName.equals("Angebote") ) {
			Object[][] data_ = {
				    {new Integer(1), "Admin billig A.", "Hur", new Double(20000.00), new Integer(365),
				     new SimpleDateFormat("dd.MM.yyyy").format(new Date()), new Double(0.55)},
				    {new Integer(2), "Gutes Projekt teuer A.", "Hur", new Double(150000.00), new Integer(180),
				     new SimpleDateFormat("dd.MM.yyyy").format(new Date()), new Double(0.45)},
				    {new Integer(3), "A003", "MrX", new Double(50000.00), new Integer(85), 
				     new SimpleDateFormat("dd.MM.yyyy").format(new Date()), new Double(0.80)},
				};
		} else if( tableName.equals("Ausgangsrechnungen") ) {
			Object [][] data_ = {
					{new Integer(1), "R2054", "MrX", new Integer(2), new Integer(1), "bezahlt"},
					{new Integer(2), "R353", "Hur", new Integer(2), new Integer(1), "bezahlt"}
			};
			
		} else if( tableName.equals("Projekte") ) {
			Object [][] data_ = {
					{new Integer(1), "Admin Tool", "Admin billig A.", new Integer(1)},
					{new Integer(2), "Gutes Projekt", "Gutes Projekt teuer A.", null},
					{new Integer(3), "Nicht so gutes Projekt", "A003", new Integer(3)}
			};
		} else {
			System.err.println("[ERROR][MockDataFactory] You requested mock data that is not defined. (tableName = " + tableName + " )");
			
			return null;
		}
		
		for(DataObject obj : collection) {
			obj.setState(DataObjectState.DataObjectStateSynchronized);
		}
		
		return collection;
	}
}
