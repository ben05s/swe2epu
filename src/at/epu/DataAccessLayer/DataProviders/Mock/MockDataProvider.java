package at.epu.DataAccessLayer.DataProviders.Mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import at.epu.DataAccessLayer.DataObjects.DataObject;
import at.epu.DataAccessLayer.DataObjects.DataObject.DataObjectState;
import at.epu.DataAccessLayer.DataObjects.DataObjectCollection;
import at.epu.DataAccessLayer.DataProviders.DataProvider;

public class MockDataProvider implements DataProvider {
	/** Mock objects are held in process memory */
	Map<String, DataObjectCollection> objectCache = null;
	MockDataFactory dataFactory = null;
	
	public MockDataProvider() {
		objectCache = new HashMap<String, DataObjectCollection>();
		dataFactory = new MockDataFactory();
	}
	
	@Override
	public int getNextIdForTable(String tableName) {
		int retVal = 0;
		
		DataObjectCollection collection = objectCache.get(tableName);
		
		/** Find highest Id */
		for(DataObject object : collection) {
			if(object.getId() > retVal) {
				retVal = object.getId();
			}
		}
		
		return retVal + 1;
	}

	@Override
	public DataObjectCollection selectAll(String tableName)
			throws DataProviderException {
		DataObjectCollection retVal = null;
		DataObjectCollection cache = objectCache.get(tableName);
		
		if( cache == null ) {
			retVal = dataFactory.createObjects(tableName);
			objectCache.put(tableName, retVal);
		} else {
			retVal = cache;
		}
		
		return retVal;
	}

	@Override
	public void syncData(String tableName, DataObjectCollection collection) throws DataProviderException {
		Iterator<DataObject> iterator = collection.iterator();
		
		while(iterator.hasNext()) {
			DataObject obj = iterator.next();
			
			if( obj.getState() == DataObjectState.DataObjectStateNew ) {
				// NOP
			} else if( obj.getState() == DataObjectState.DataObjectStateDeleted ) {
				iterator.remove();
			} else if( obj.getState() == DataObjectState.DataObjectStateModified ) {
				// NOP
			}
			
			obj.setState(DataObjectState.DataObjectStateSynchronized);
		}
		
		objectCache.remove(tableName);
		objectCache.put(tableName, collection);
	}

	@Override
	public ArrayList<String> resolveForeignKey(String tableName,
			DataObject object, int fieldIndex) throws DataProviderException {
		return MockForeignKeyDataFactory.createForeignKeyResults(tableName, object, fieldIndex);
	}
}
