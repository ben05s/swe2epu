package at.epu.BusinessLayer;

import at.epu.DataAccessLayer.*;

public class ApplicationManager {
	static ApplicationManager instance = null;
	DatabaseManager databaseManager = null;
	
	private ApplicationManager() {
		databaseManager = new DatabaseManager();
	}
	
	public static ApplicationManager getInstance() {
		return ApplicationManager.instance == null ?
				(ApplicationManager.instance = new ApplicationManager()) :
					ApplicationManager.instance;
	}
	
	/**
	 * Setup code.
	 */
	public void applicationStarted() {
		databaseManager.setDataSource(new DALFake());
	}
	
	/**
	 * Teardown code.
	 */
	public void applicationEnded() {
		
	}
	
	public DatabaseManager getDatabaseManager() {
		return databaseManager;
	}
}
