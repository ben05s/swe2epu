package at.epu.BusinessLayer;

import at.epu.DataAccessLayer.*;
import java.lang.NullPointerException;

public class DatabaseManager {
	DALInterface dataSource = null;
	
	public void setDataSource(DALInterface dataSource_) {
		dataSource = dataSource_;
	}
	
	public DALInterface getDataSource() {
		if(dataSource == null) {
			throw new NullPointerException("Data source was null. You must supply a data source first.");
		}
		
		return dataSource;
	}
}
