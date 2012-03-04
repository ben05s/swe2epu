package at.epu.BusinessLayer;

import at.epu.DataAccessLayer.*;
import javax.naming.ConfigurationException;

public class DatabaseManager {
	DALInterface dataSource = null;
	
	public void setDataSource(DALInterface dataSource_) {
		dataSource = dataSource_;
	}
	
	public DALInterface getDataSource() throws ConfigurationException {
		if(dataSource == null) {
			throw new ConfigurationException("Data source was null. You must supply a data source first.");
		}
		
		return dataSource;
	}
}
