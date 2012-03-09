package at.epu.DataAccessLayer.DataModels;

import java.util.ArrayList;

public class DataFilterProvider {
	static public Object[][] filterDataModel(String filterString, Object[][] data) {
		ArrayList<Object[]> new_data = new ArrayList<Object[]>();
		
		for(Object[] objarr : data) {
			for(Object obj : objarr) {
				if(obj == null) {
					continue;
				}
				
				if(obj.getClass() == filterString.getClass()) {
					String str = (String)obj;
					
					if(filterString.equals(str) || str.contains(filterString)) {
						new_data.add(objarr);
					}
				}
			}
		}
		
		Object[][] tmp = new Object[new_data.size()][data[0].length];
		
		for(int i = 0; i < new_data.size(); i++) {
			tmp[i] = new_data.get(i);
		}
		
		return tmp;
	}
}
