package at.epu.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import at.epu.BusinessLayer.ApplicationManager;
import at.epu.DataAccessLayer.DataObjects.CustomerDataObject;
import at.epu.DataAccessLayer.DataObjects.DataObject;
import at.epu.DataAccessLayer.DataObjects.InBillDataObject;
import at.epu.DataAccessLayer.DataObjects.DataObject.DataObjectState;
import at.epu.DataAccessLayer.DataObjects.DataObjectCollection;
import at.epu.DataAccessLayer.DataProviders.DataProvider.DataProviderException;
import at.epu.PresentationLayer.DataModels.BackofficeTableModel;

public class MockDataProviderTests {
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String[] args = {"config/appMock.properties"};
		
		ApplicationManager.getInstance().applicationStarted(args);
	}

	@Test
	public void testGetNextId() throws DataProviderException {
		BackofficeTableModel model = ApplicationManager.getInstance().getModelForTableName("Kunden");
		
		int size = model.getDataObjectCollection().size();
		
		int id = ApplicationManager.getInstance().getDatabaseManager().getNextIdForTableName("Kunden");
		
		assertEquals(size + 1, id);
	}
	
	@Test
	public void testSelectAll() throws DataProviderException {
		DataObjectCollection collection = ApplicationManager.getInstance().getDatabaseManager().getAllObjectsForTableName("Kunden");
		
		DataObject object = collection.get(0);
		
		assertEquals(CustomerDataObject.class, object.getClass());
		
		assertEquals("Ben", ((CustomerDataObject)object).getVorname() );
	}
	
	@Test
	public void testSyncDataUpdate() throws DataProviderException {
		DataObjectCollection collection = ApplicationManager.getInstance().getDatabaseManager().getAllObjectsForTableName("Kunden");
		
		DataObject object = collection.get(0);
		
		assertEquals(CustomerDataObject.class, object.getClass());
		
		CustomerDataObject customerDataObject = (CustomerDataObject)object;
		
		String testString = "TESTNACHNAME";
		customerDataObject.setNachname(testString);
		
		customerDataObject.setState(DataObjectState.DataObjectStateModified);
		
		ApplicationManager.getInstance().getDatabaseManager().synchronizeObjectsForTableName("Kunden", collection);
		
		DataObjectCollection collection2 = ApplicationManager.getInstance().getDatabaseManager().getAllObjectsForTableName("Kunden");
		DataObject object2 = collection2.get(0);
		
		assertEquals(CustomerDataObject.class, object2.getClass());
		
		CustomerDataObject customerDataObject2 = (CustomerDataObject)object2;
		
		assertEquals(testString, customerDataObject2.getNachname());
	}
	
	@Test
	public void testSyncDataInsert() throws DataProviderException {
		DataObjectCollection collection = ApplicationManager.getInstance().getDatabaseManager().getAllObjectsForTableName("Kunden");
		
		int sizeBefore = collection.size();
		
		CustomerDataObject obj1 = new CustomerDataObject();
		
		obj1.setId(1);
		obj1.setVorname("Ben");
		obj1.setNachname("Hur");
		obj1.setUnternehmen("Mobil Gmbh");
		obj1.setAdresse("Petergasse 354/7");
		obj1.setEmail("sdas.t@gmx.at");
		obj1.setTelefon("06602348192");
		obj1.setAngebot_mapping_id(1);
		
		collection.add(obj1);
		
		ApplicationManager.getInstance().getDatabaseManager().synchronizeObjectsForTableName("Kunden", collection);
		
		DataObjectCollection collection2 = ApplicationManager.getInstance().getDatabaseManager().getAllObjectsForTableName("Kunden");
		
		assertEquals(sizeBefore + 1, collection2.size());
	}
	
	@Test
	public void testSyncDataDelete() throws DataProviderException {
		DataObjectCollection collection = ApplicationManager.getInstance().getDatabaseManager().getAllObjectsForTableName("Kunden");
		
		int sizeBefore = collection.size();
		
		collection.remove(0);
		
		ApplicationManager.getInstance().getDatabaseManager().synchronizeObjectsForTableName("Kunden", collection);
		
		DataObjectCollection collection2 = ApplicationManager.getInstance().getDatabaseManager().getAllObjectsForTableName("Kunden");
		
		assertEquals(sizeBefore - 1, collection2.size());
	}
	
	@Test
	public void testSyncDataNoop() throws DataProviderException {
		DataObjectCollection collection = ApplicationManager.getInstance().getDatabaseManager().getAllObjectsForTableName("Kunden");
		
		int sizeBefore = collection.size();
		
		ApplicationManager.getInstance().getDatabaseManager().synchronizeObjectsForTableName("Kunden", collection);
		
		DataObjectCollection collection2 = ApplicationManager.getInstance().getDatabaseManager().getAllObjectsForTableName("Kunden");
		
		assertEquals(sizeBefore, collection2.size());
	}
	
	@Test
	public void testForeignKeyForName() throws DataProviderException {
		int i = ApplicationManager.getInstance().getDatabaseManager().getForeignKeyForName("Kunden", "vorname", "Ben");
		
		assertEquals(1, i);
	}
	
	@Test
	public void testForeignKeyForNameNoResult() throws DataProviderException {
		int i = ApplicationManager.getInstance().getDatabaseManager().getForeignKeyForName("Kunden", "vorname", "Johnny");
		
		assertEquals(0, i);
	}
	
	@Test
	public void testCreateMappingEntries() throws DataProviderException {
		ArrayList<Integer> keysArrayList = new ArrayList<Integer>();
		
		int i = ApplicationManager.getInstance().getDatabaseManager().createMappingEntryForValues("Kunden", "Angebote", keysArrayList);
		
		assertEquals(0, i);
	}
	
	@Test
	public void testResolveForeignKey() throws DataProviderException {
		InBillDataObject obj1 = new InBillDataObject();
		
		obj1.setId(1);
		obj1.setRechnungskürzel("InBill123");
		obj1.setKontakt_id(1);
		obj1.setBzeile_mapping_id(1);
		obj1.setStatus("bezahlt");
		
		ArrayList<String> names = ApplicationManager.getInstance().getDatabaseManager().resolveForeignKeyForTableNameAndObject("Eingangsrechnungen",
				obj1, obj1.getIndexForFieldName("kontakt_id"));
		
		assertEquals(0, names.size());
	}
}
