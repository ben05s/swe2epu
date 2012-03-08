package at.epu.BusinessLayer;

import at.epu.DataAccessLayer.*;
import java.lang.NullPointerException;

public class DatabaseManager {
	DataSource dataSource = null;
	
	public void setDataSource(DataSource dataSource_) {
		dataSource = dataSource_;
	}
	
	public DataSource getDataSource() {
		if(dataSource == null) {
			throw new NullPointerException("Data source was null. You must supply a data source first.");
		}
		
		return dataSource;
	}
}
