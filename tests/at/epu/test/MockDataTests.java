package at.epu.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import at.epu.BusinessLayer.ApplicationManager;
import at.epu.DataAccessLayer.DataObjects.ContactDataObject;
import at.epu.DataAccessLayer.DataObjects.DataObjectCollection;
import at.epu.DataAccessLayer.DataProviders.DataProvider.DataProviderException;
import at.epu.PresentationLayer.DataModels.BackofficeTableModel;

public class MockDataTests {
	ApplicationManager appManager = ApplicationManager.getInstance();
	
	@Test
	public void saveDataTest() {
		String[] args= new String[0];
		
		appManager.applicationStarted(args);
		
		BackofficeTableModel tableModel = appManager.getModelForTableName("Kontakte");
		
		ContactDataObject object = new ContactDataObject();
		object.setVorname("Heinz");
		object.setNachname("Meier");
		object.setAddresse("Hausgasse");
		object.setEmail("email");
		object.setTelefon("0664414123");
		
		DataObjectCollection collection = tableModel.getDataObjectCollection();
		collection.add(object);
		
		try {
			appManager.getDatabaseManager().synchronizeObjectsForTableName("Kontakte", collection);
		} catch (DataProviderException e) {
			e.printStackTrace();
		}
		
		tableModel.setDataObjectCollection(collection);
		
		assertEquals("Heinz", tableModel.getDataObjectCollection().toDataArray()[tableModel.getDataObjectCollection().size()][1]);
	}
}
