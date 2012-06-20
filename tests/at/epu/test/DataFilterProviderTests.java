package at.epu.test;

import static org.junit.Assert.*;

import org.junit.Test;

import at.epu.DataAccessLayer.DataObjects.CustomerDataObject;
import at.epu.DataAccessLayer.DataObjects.DataObjectCollection;
import at.epu.PresentationLayer.DataModels.DataFilterProvider;

public class DataFilterProviderTests {

	
	@Test
	public void testDataFilterNoResult() {
		DataObjectCollection collection = new DataObjectCollection();
		
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
		
		DataObjectCollection result = DataFilterProvider.filterDataModel("Longstringneverfound", collection);
		
		assertEquals(0, result.size());
	}
	
	@Test
	public void testDataFilterOneResult() {
		DataObjectCollection collection = new DataObjectCollection();
		
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
		
		DataObjectCollection result = DataFilterProvider.filterDataModel("Ben", collection);
		
		assertEquals(1, result.size());
	}
	
	@Test
	public void testDataFilterMultipleResultsNoneRemoved() {
		DataObjectCollection collection = new DataObjectCollection();
		
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
		collection.add(obj1);
		
		DataObjectCollection result = DataFilterProvider.filterDataModel("Ben", collection);
		
		assertEquals(2, result.size());
	}
	
	@Test
	public void testDataFilterMultipleResultsWithRemoved() {
		DataObjectCollection collection = new DataObjectCollection();
		
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
		collection.add(obj1);
		
		CustomerDataObject obj2 = new CustomerDataObject();
		
		obj2.setId(1);
		obj2.setVorname("John");
		obj2.setNachname("Hur");
		obj2.setUnternehmen("Mobil Gmbh");
		obj2.setAdresse("Petergasse 354/7");
		obj2.setEmail("sdas.t@gmx.at");
		obj2.setTelefon("06602348192");
		obj2.setAngebot_mapping_id(1);
		
		collection.add(obj2);
		
		DataObjectCollection result = DataFilterProvider.filterDataModel("Ben", collection);
		
		assertEquals(2, result.size());
	}
	
}
