package at.epu.BusinessLayer;

import java.sql.SQLException;

import at.epu.DataAccessLayer.*;

public class ApplicationManager {
	static ApplicationManager instance = null;
	DatabaseManager databaseManager    = null;
	
	private ApplicationManager() {
		databaseManager = new DatabaseManager();
	}
	
	public static synchronized ApplicationManager getInstance() {
		return ApplicationManager.instance == null ?
				(ApplicationManager.instance = new ApplicationManager()) :
					ApplicationManager.instance;
	}
	
	/**
	 * Setup code.
	 * @throws SQLException 
	 */
	public void applicationStarted(String[] args) throws SQLException {
		if(args.length > 0)
		{
			databaseManager.setDataSource(new DatabaseDataSource(args[0]));
		}
		else
		{
			databaseManager.setDataSource(new MockDataSource());
		}
	}
	
	/**
	 * Tear-down code.
	 */
	public void applicationEnded() {
		
	}
	
	public DatabaseManager getDatabaseManager() {
		return databaseManager;
	}
}
