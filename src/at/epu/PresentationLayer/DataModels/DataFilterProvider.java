package at.epu.PresentationLayer.DataModels;

import at.epu.DataAccessLayer.DataObjects.DataObjectCollection;

public class DataFilterProvider {
	static public DataObjectCollection filterDataModel(String filterString, DataObjectCollection dataObjects) {
		Object[][] data = dataObjects.toDataArray();
		
		DataObjectCollection filteredObjects = new DataObjectCollection();
		
		int i = 0;
		for(Object[] objarr : data) {
			
			for(Object obj : objarr) {
				if(obj == null) {
					continue;
				}
				
				if(obj.getClass() == filterString.getClass()) {
					String str = (String)obj;
					
					if(str.toLowerCase().contains(filterString.toLowerCase())) {
						filteredObjects.add( dataObjects.get(i) );
						break;
					}
				}
			}
			
			i++;
		}
		
		return filteredObjects;
	}
}
