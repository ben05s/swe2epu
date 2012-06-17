package at.epu.BusinessLayer;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.swing.JFrame;

import at.epu.PresentationLayer.DataModels.BackofficeTableModel;
import at.epu.PresentationLayer.DataModels.DataModelFactory;
import at.epu.PresentationLayer.Views.GenericSplitTableView;
import at.epu.PresentationLayer.MainWindow;
/*
 * 
 */
public class ApplicationManager {
	static ApplicationManager instance = null;
	DatabaseManager databaseManager    = null;
	DialogManager dialogManager 	   = null;
	MainWindow mainWindow			   = null;
	PDFManager pdfManager			   = null;
	JSONManager jsonManager			   = null;
	BindingManager bindingManager 	   = null;
	DataModelFactory dataModelFactory  = null;
	
	private ApplicationManager() {
		dialogManager    = new DialogManager();
		pdfManager       = new PDFManager();
		jsonManager      = new JSONManager();
		bindingManager   = new BindingManager();
		dataModelFactory = new DataModelFactory();
	}
	
	public static synchronized ApplicationManager getInstance() {
		return ApplicationManager.instance == null ?
				(ApplicationManager.instance = new ApplicationManager()) :
					ApplicationManager.instance;
	}
	
	/**
	 * Setup code.
	 */
	public void applicationStarted(String[] args) {	
		
		PropertyConfigurator.configure("config/logging.properties");
		
		if(args.length > 0)
		{
			/** SQL */
			databaseManager = new DatabaseManager(args[0]);
		}
		else
		{
			/** Mock */
			databaseManager = new DatabaseManager();
		}
		
		Logger.getLogger(ApplicationManager.class.getName()).info("Application started.");
	}
	
	/**
	 * Tear-down code.
	 */
	public void applicationEnded() {
		Logger.getLogger(ApplicationManager.class.getName()).info("Application ended.");
	}
	
	public DatabaseManager getDatabaseManager() {
		if(databaseManager == null) {
			Logger.getLogger(ApplicationManager.class.getName()).error("DatabaseManager is null. Don't call it in constructors.");
		}
		
		return databaseManager;
	}
	
	public DialogManager getDialogManager() {
		return dialogManager;
	}
	
	public BindingManager getBindingManager() {
		return bindingManager;
	}
	
	public JSONManager getJsonManager() {
		return jsonManager;
	}
	
	public BackofficeTableModel getModelForTableName(String tableName) {
		return dataModelFactory.getModelForTableName(tableName);
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
