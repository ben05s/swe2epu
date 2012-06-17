package at.epu.PresentationLayer.ViewControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

import at.epu.BusinessLayer.ApplicationManager;
import at.epu.BusinessLayer.JSONManager;
import at.epu.DataAccessLayer.DataObjects.DataObject;
import at.epu.DataAccessLayer.DataObjects.DataObject.DataObjectState;
import at.epu.DataAccessLayer.DataObjects.DataObjectCollection;
import at.epu.DataAccessLayer.DataObjects.ProjectDataObject;
import at.epu.DataAccessLayer.DataProviders.DataProvider.DataProviderException;
import at.epu.PresentationLayer.ActionHandlers.AddActionHandler;
import at.epu.PresentationLayer.ActionHandlers.FilterActionHandler;
import at.epu.PresentationLayer.DataModels.BackofficeTableModel;
import at.epu.PresentationLayer.Views.GenericSplitTableView;

public class ProjectViewController extends ViewController implements ActionListener {	
	public ProjectViewController(JFrame mainWindow) {
		super(mainWindow);
	}
	
	@Override
	void initialize() {
		ApplicationManager appManager = ApplicationManager.getInstance();
		
		BackofficeTableModel model = appManager.getModelForTableName("Projekte");
		
		registerActionHandler(new FilterActionHandler(this));
		registerActionHandler(new AddActionHandler(this, model));
		
		ArrayList<JButton> buttonList = getButtonsFromHandlers();
		
		JButton bookTimeButton = new JButton("Zeiten Buchen");
		bookTimeButton.setActionCommand("BOOK_TIME");
		bookTimeButton.addActionListener(this);
		buttonList.add(bookTimeButton);
		
		ArrayList<JLabel> labelList = new ArrayList<JLabel>();
		labelList.add(new JLabel("Stundensatz(gesamt): "));
		
		ArrayList<JMenuItem> menuList = new ArrayList<JMenuItem>();
		menuList.add(new JMenuItem("Editieren"));
		menuList.add(new JMenuItem("Löschen"));
		menuList.add(new JMenuItem("Ausgangsrechnung stellen"));
		
		title = "Projekte";
		
		model.getAddEditState().getIndexChoosable().add(1);
		model.getAddEditState().getIndexChoosable().add(2);
		
		rootComponent = new GenericSplitTableView(buttonList, labelList, menuList, title,
												  appManager.getModelForTableName("Projekte"));
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		super.actionPerformed(event);
		
		ApplicationManager applicationManager = ApplicationManager.getInstance();
		
		if(event.getActionCommand().equals("BOOK_TIME")) {
			JFileChooser chooser = new JFileChooser();
			
			chooser.setCurrentDirectory(new File("timetables"));
			
			int retVal = chooser.showOpenDialog(null);
			
			if( retVal == JFileChooser.APPROVE_OPTION ) {
				String filepath = chooser.getSelectedFile().getPath();
						
				Logger.getLogger(ProjectViewController.class.getName()).info("Selected filepath: " + filepath);
				
				JSONManager jsonManager = applicationManager.getJsonManager();
				
				try {
					JSONManager.TimeTable table = jsonManager.serializeTimeTable(filepath);
					
					Logger.getLogger(ProjectViewController.class.getName()).info("Parsed time table data: Project: " 
															+ table.getProjectName() + ", Time: " + table.getTotal());
					
					try {
						int id = applicationManager.getDatabaseManager().getForeignKeyForName("projekte", "titel", table.getProjectName());
						
						DataObjectCollection collection = applicationManager.getModelForTableName("Projekte").getDataObjectCollection();
						
						ProjectDataObject dataObject = null;
						for(DataObject object : collection) {
							if(object.getId() == id) {
								dataObject = (ProjectDataObject) object;
								break;
							}
						}
						
						if( dataObject != null ) {
							dataObject.setZeit(dataObject.getZeit() + table.getTotal());
							
							dataObject.setState(DataObjectState.DataObjectStateModified);
							
							try {
								applicationManager.getDatabaseManager().synchronizeObjectsForTableName("projekte", collection);
							} catch (DataProviderException e) {
								e.printStackTrace();
							}
							
							applicationManager.getModelForTableName("Projekte").setDataObjectCollection(collection);
							applicationManager.getModelForTableName("Projekte").updateTableData();
							
							Logger.getLogger(ProjectViewController.class.getName()).info("Added " + table.getTotal() + 
																	" hours to project " + table.getProjectName() + ".");
						} else {
							JOptionPane.showMessageDialog(null, 
									"Das Projekt " + table.getProjectName() + " mit ID " + id +  " existiert nicht.",
									"Error", JOptionPane.ERROR_MESSAGE);
						}
					} catch (DataProviderException e) {
						JOptionPane.showMessageDialog(null, "Das Projekt " + table.getProjectName() + " existiert nicht.", "Error", JOptionPane.ERROR_MESSAGE);
					}
					
				} catch(JsonSyntaxException e1) {
					JOptionPane.showMessageDialog(null, "Die gewählte Datei konnte nicht gelesen werden.", "Error", JOptionPane.ERROR_MESSAGE);
				} catch(JsonParseException e2) {
					JOptionPane.showMessageDialog(null, "Die gewählte Datei konnte nicht gelesen werden.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
}
