package at.epu.test;

import org.junit.BeforeClass;
import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import at.epu.BusinessLayer.ApplicationManager;
import at.epu.DataAccessLayer.DataObjects.BankAccountDataObject;
import at.epu.DataAccessLayer.DataObjects.BillRowDataObject;
import at.epu.DataAccessLayer.DataObjects.CategoryDataObject;
import at.epu.DataAccessLayer.DataObjects.ContactDataObject;
import at.epu.DataAccessLayer.DataObjects.CustomerDataObject;
import at.epu.DataAccessLayer.DataObjects.DataObject;
import at.epu.DataAccessLayer.DataObjects.DataObjectFactory;
import at.epu.DataAccessLayer.DataObjects.InBillDataObject;
import at.epu.DataAccessLayer.DataObjects.OfferDataObject;
import at.epu.DataAccessLayer.DataObjects.OutBillDataObject;
import at.epu.DataAccessLayer.DataObjects.ProjectDataObject;
import at.epu.DataAccessLayer.DataObjects.IntermediateObjects.ArrayResultSet;

public class DataObjectFactoryTests {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String[] args = {"config/app.properties"};
		
		
		ApplicationManager.getInstance().applicationStarted(args);
	}
	
	@Test
	public void testBuchungszeilen() throws SQLException {
		Object[] data = {1, 2, 3, 2.0d, 2.0d, new java.sql.Date(new java.util.Date().getTime()), 1}; 
		ArrayResultSet resultSet = new ArrayResultSet(data);
		
		DataObject object = DataObjectFactory.createObject("Buchungszeilen", resultSet);
		
		assertEquals(BankAccountDataObject.class, object.getClass());
	}

	@Test
	public void testInvalidTablename() throws SQLException {
		DataObject object = null;
		
		object = DataObjectFactory.createObject("GibberishTable", null);
		
		assertEquals(null, object);
	}
	
	@Test
	public void testKunden() throws SQLException {
		Object[] data = 
			    {new Integer(2), "Fritz", "DD", "Privat", 
			     "Zeilengasse 143/22", "f.DD@gmail.com", "06761252042", 2};

		ArrayResultSet resultSet = new ArrayResultSet(data);
		
		DataObject object = DataObjectFactory.createObject("Kunden", resultSet);
		
		assertEquals(CustomerDataObject.class, object.getClass());
	}
	
	@Test
	public void testAngebote() throws SQLException {
		Object[] data = 
			{new Integer(1), "Admin billig A.", new Integer(5), new Double(20000.00), new Integer(365),
				new java.sql.Date(new java.util.Date().getTime()), new Double(0.55)};

		ArrayResultSet resultSet = new ArrayResultSet(data);
		
		DataObject object = DataObjectFactory.createObject("Angebote", resultSet);
		
		assertEquals(OfferDataObject.class, object.getClass());
	}
	
	@Test
	public void testProjekte() throws SQLException {
		Object[] data = 
			{new Integer(1), "Admin Tool", 1, 2, new Double(1.5)};

		ArrayResultSet resultSet = new ArrayResultSet(data);
		
		DataObject object = DataObjectFactory.createObject("Projekte", resultSet);
		
		assertEquals(ProjectDataObject.class, object.getClass());
	}
	
	@Test
	public void testAusgangsrechnungen() throws SQLException {
		Object[] data = 
			{new Integer(1), "R2054", 3, "bezahlt"};

		ArrayResultSet resultSet = new ArrayResultSet(data);
		
		DataObject object = DataObjectFactory.createObject("Ausgangsrechnungen", resultSet);
		
		assertEquals(OutBillDataObject.class, object.getClass());
	}
	
	@Test
	public void testRechnungszeilen() throws SQLException {
		Object[] data = 
			{new Integer(1), 2, 3, "R2054", 3.0d, 3.0d};

		ArrayResultSet resultSet = new ArrayResultSet(data);
		
		DataObject object = DataObjectFactory.createObject("Rechnungszeilen", resultSet);
		
		assertEquals(BillRowDataObject.class, object.getClass());
	}
	
	@Test
	public void testKategorien() throws SQLException {
		Object[] data = 
			{new Integer(1), "R2054"};

		ArrayResultSet resultSet = new ArrayResultSet(data);
		
		DataObject object = DataObjectFactory.createObject("Kategorien", resultSet);
		
		assertEquals(CategoryDataObject.class, object.getClass());
	}
	
	@Test
	public void testKontakte() throws SQLException {
		Object[] data = 
			{new Integer(1), "R2054", "bezahlt", "bezahlt", "bezahlt", "bezahlt"};

		ArrayResultSet resultSet = new ArrayResultSet(data);
		
		DataObject object = DataObjectFactory.createObject("Kontakte", resultSet);
		
		assertEquals(ContactDataObject.class, object.getClass());
	}
	
	   @Test
	    public void testEingangsrechnungen() throws SQLException {
	        Object[] data = 
	            {new Integer(1), "R01321", 1, "bezahlt"};

	        ArrayResultSet resultSet = new ArrayResultSet(data);
	        
	        DataObject object = DataObjectFactory.createObject("Eingangsrechnungen", resultSet);
	        
	        assertEquals(InBillDataObject.class, object.getClass());
	    }
}
