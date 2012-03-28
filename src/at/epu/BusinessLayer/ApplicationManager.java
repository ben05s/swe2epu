package at.epu.BusinessLayer;

import javax.swing.JFrame;

import at.epu.DataAccessLayer.*;
import at.epu.DataAccessLayer.DataModels.BackofficeTableModel;
import at.epu.PresentationLayer.GenericSplitTableView;
import at.epu.PresentationLayer.MainWindow;

public class ApplicationManager {
	static ApplicationManager instance = null;
	DatabaseManager databaseManager    = null;
	DialogManager dialogManager 	   = null;
	MainWindow mainWindow			   = null;
	PDFManager pdfManager			   = null;
	JSONManager JSONManager			   = null;
	
	private ApplicationManager() {
		databaseManager = new DatabaseManager();
		dialogManager   = new DialogManager();
		pdfManager      = new PDFManager();
		JSONManager     = new JSONManager();
	}
	
	public static synchronized ApplicationManager getInstance() {
		return ApplicationManager.instance == null ?
				(ApplicationManager.instance = new ApplicationManager()) :
					ApplicationManager.instance;
	}
	
	/**
	 * Setup code.
	 * @throws Exception 
	 */
	public void applicationStarted(String[] args) throws Exception {		
		if(args.length > 0)
		{
			databaseManager.setDataSource(new DatabaseDataSource(args[0]));
		}
		else
		{
			databaseManager.setDataSource(new MockDataSource());
		}
		
		JSONManager.writeRandomTimeTableToFile("timetables/timetable.json");
		System.out.println(JSONManager.getTimeTotalFromFile("timetables/timetable.json"));
	}
	
	/**
	 * Tear-down code.
	 */
	public void applicationEnded() {
		
	}
	
	public DatabaseManager getDatabaseManager() {
		return databaseManager;
	}
	
	public DialogManager getDialogManager() {
		return dialogManager;
	}
	
	public void setMainWindow(MainWindow mainWindow_) {
		mainWindow = mainWindow_;
	}
	
	public JFrame getMainFrame() {
		return mainWindow.getMainFrame();
	}
	
	public MainWindow getMainWindow() {
		return mainWindow;
	}
	
	public BackofficeTableModel getActiveTableModel() {
		return ((GenericSplitTableView)getMainWindow().getActiveViewController().getRootComponent()).getModel();
	}
}
