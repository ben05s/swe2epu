package at.epu.BusinessLayer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

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
		
		if(args.length > 0) {
			Properties appConfig = new Properties();
			
			try {
				appConfig.load(new FileInputStream(args[0]));
			} catch (FileNotFoundException e) {
				System.err.println("Could not find the application configuration file at config/app.properties.");
			} catch (IOException e) {
				System.err.println("Could not open the application configuration file at config/app.properties.");
			}
			
			String loggingConfig = appConfig.getProperty("logging.config.filepath");
			
			if(loggingConfig != null) {
				PropertyConfigurator.configure(loggingConfig);
			} else {
				System.err.println("To use the logging framework u must specify a value for the key logging.config.filepath in your app.properties file.");
			}
			
			String databaseString = appConfig.getProperty("database.path");
			
			if(databaseString != null)
			{
				/** SQL */
				databaseManager = new DatabaseManager(databaseString);
			}
			else
			{
				/** Mock */
				databaseManager = new DatabaseManager();
			}
			
			Logger.getLogger(ApplicationManager.class.getName()).info("Read application configuration file at " + args[0] + ".");
			
			if(databaseString != null) {
				Logger.getLogger(ApplicationManager.class.getName()).info("Database string is: " + databaseString + ".");
			} else {
				Logger.getLogger(ApplicationManager.class.getName()).info("Using the mock database.");
			}
			
			Logger.getLogger(ApplicationManager.class.getName()).info("Logging configuration is at: " + loggingConfig + ".");
			Logger.getLogger(ApplicationManager.class.getName()).info("Application started.");
		} else {
			System.err.println("You must supply an application configuration file at startup. \nUsage: ./backofficedb [APP/CONFIG/FILEPATH.properties]");
		}
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
	
	public PDFManager getPdfManager() {
		return pdfManager;
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
