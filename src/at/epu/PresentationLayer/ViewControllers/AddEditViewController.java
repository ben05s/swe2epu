package at.epu.PresentationLayer.ViewControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

import at.epu.BusinessLayer.ApplicationManager;
import at.epu.BusinessLayer.DatabaseManager;
import at.epu.DataAccessLayer.DataObjects.DataObject;
import at.epu.DataAccessLayer.DataObjects.DataObject.DataObjectState;
import at.epu.DataAccessLayer.DataObjects.DataObjectCollection;
import at.epu.DataAccessLayer.DataObjects.DataObjectFactory;
import at.epu.DataAccessLayer.DataObjects.IntermediateObjects.ArrayResultSet;
import at.epu.DataAccessLayer.DataProviders.DataProvider.DataProviderException;
import at.epu.PresentationLayer.DataModels.BackofficeTableModel;
import at.epu.PresentationLayer.Views.GenericAddEditFormView;

public class AddEditViewController extends ViewController implements ActionListener {
	BackofficeTableModel model = null;								//stores all information about current tab/table
	DatabaseManager databaseManager = null;
	JFrame chooseFrame;												//open the multi choosable screen with radiobuttons or checkboxes to be able to select all needed data at once
	String cmd_;													//determines what command called the controller(Edit or Add)
	int rowindex; 													//determines what row should be edited/deleted when rightklicking on the row
	String[] columnNames = null;									//array with column names who can be changed/set
	Object[][] data = null;											//dataarray without ignored columns(id) for add/edit 
	ArrayList<JTextField> textList;									//for displaying the add/edit screen
	
	public AddEditViewController(String action, int rowindex_, ArrayList<Integer> indexChoosable_) {	
		this.cmd_ = action;
		rowindex = rowindex_;
		
		initialize_addEdit();
	}

	public void initialize_addEdit() {
		ArrayList<JButton> buttonList = new ArrayList<JButton>();
		JButton btnSave = new JButton("Speichern");
		JButton btnCancel  = new JButton("Abbrechen");
		JButton btnChoose1 = new JButton("Auswählen");
		JButton btnChoose2 = new JButton("Auswählen");
		JButton btnChoose3 = new JButton("Auswählen");
		
		btnSave.setActionCommand("SAVE");
		btnSave.addActionListener(this);
		btnCancel.setActionCommand("CANCEL");
		btnCancel.addActionListener(this);
		//used for Kunden/Angebote/Projekte(Angebot)/Rechnungen/Rechnungszeilen(Angebot)/Bankkonto(Eingangsrechnung ID)
		btnChoose1.setActionCommand("CHOOSE1");
		btnChoose1.addActionListener(this);
		//used for Projekte(Ausgangsrechnung ID)/Bankkonto(Ausgangsrechnung ID)
		btnChoose2.setActionCommand("CHOOSE2");
		btnChoose2.addActionListener(this);
		//used for Bankkonto(Kategorie)
		btnChoose3.setActionCommand("CHOOSE3");
		btnChoose3.addActionListener(this);
		
		buttonList.add(btnSave);
		buttonList.add(btnCancel);
		buttonList.add(btnChoose1);
		buttonList.add(btnChoose2);
		buttonList.add(btnChoose3);
		
		ApplicationManager appManager = ApplicationManager.getInstance();
		
		//get to current model 
		model = appManager.getActiveTableModel();
		//special case because the detailTable(Rechnungszeilen) is within the Rechnungen Tab (Ausgangsrechnungen)
		if(model.getAddEditState().isDetailTableView()) {
			model = appManager.getModelForTableName("Rechnungszeilen");
		}
		
		columnNames = model.getAddEditState().getAddEditColNames();
		data = model.getAddEditState().getAddEditData();
		
		
		ArrayList<JLabel> labelList = new ArrayList<JLabel>();
		for(int i=0;i<columnNames.length;i++) {
			labelList.add(new JLabel(columnNames[i], JLabel.TRAILING));
		}
		
		textList = new ArrayList<JTextField>();
		if(cmd_.equals("EDIT")) {
			//fill the addEdit form with the data of the selected row
			for(int i=0;i<columnNames.length;i++) {
				if(data[rowindex][i] != null) {
					textList.add(new JTextField(data[rowindex][i].toString(),20));
				}
				else{
					textList.add(new JTextField("",20));
				}
			}
		}
		if(cmd_.equals("ADD")) {
			for(int i=0;i<columnNames.length;i++) {
				//ignore some labels because there is a button to choose this data
				if(columnNames[i].equals("Kategorie") || columnNames[i].equals("Ausgangsrechnung") ||
				   columnNames[i].equals("Eingangsrechnung") || columnNames[i].equals("Ausgangsrechnungen") ||
				   columnNames[i].equals("Angebot") || columnNames[i].equals("Kunde") || 
				   columnNames[i].equals("Angebote")) {
					//model.setChooseIndex(i);		//this value gets set correctly somewhere else. dont know where...
					continue;
				}
				
				//status column is by default "offen" has to be manually set to "bezahlt" via update funktion
				if(columnNames[i].equals("Status")) {
					textList.add(new JTextField("offen",20));
				} else {
					textList.add(new JTextField("",20));
				}
			}
		}	

		//populate the Add/Edit Form Dialog
		rootComponent = new GenericAddEditFormView(buttonList, labelList, textList, model.getAddEditState().getIndexChoosable());				
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		String cmd = event.getActionCommand();
		
		ApplicationManager appManager = ApplicationManager.getInstance();
		
		//when there are 3 different choosable buttons in the add/edit form every button needs to handle a different functionality
		if(cmd.equals("CHOOSE1")) {
			appManager.getDialogManager().pushDialog(new AddEditChooserViewController(cmd, cmd_, rowindex, model.getAddEditState().getIndexChoosable()));
		}
		
		if(cmd.equals("CHOOSE2")) {
			appManager.getDialogManager().pushDialog(new AddEditChooserViewController(cmd, cmd_, rowindex, model.getAddEditState().getIndexChoosable()));
		}
		
		if(cmd.equals("CHOOSE3")) {
			appManager.getDialogManager().pushDialog(new AddEditChooserViewController(cmd, cmd_, rowindex, model.getAddEditState().getIndexChoosable()));
		}
		
		if(cmd.equals("SAVE")) {
			Object[] data = new Object[columnNames.length];
			int skip = 0;
			for(int i=0;i<columnNames.length;i++) {
				//make a placeholder wherever there was a choosable button, the data from the choosables will be put into the data array in the saveData function
				for(int x=0;x<model.getAddEditState().getIndexChoosable().size();x++) {
					if(i == model.getAddEditState().getIndexChoosable().get(x)) {
						model.getAddEditState().setChooseIndex(i);
						data[i] = "placeholder";
						skip++;
					}
				}
				if(data[i] == null) {
					data[i] = textList.get(i-skip).getText();
				}
			}

			if(cmd_.equals("ADD") || cmd_.equals("EDIT")) {
				//check the user input. only if input is correct the frame is disposed
				
				ArrayList<String> accumulatedChooseData = new ArrayList<String>();
				
				for(int i = 0; i < appManager.getActiveTableModel().getAddEditState().getMaxChosenDataSize(); ++i) {
					accumulatedChooseData.addAll( appManager.getActiveTableModel().getAddEditState().getChoosenData(i) );
				}
				
				if(appManager.getBindingManager().checkInput(data, accumulatedChooseData, false)) {
					DataObjectCollection collection = model.getDataObjectCollection();
					
					DataObject previous = collection.get(rowindex);
					
					ArrayList< ArrayList<String> > foreignValue = appManager.getActiveTableModel().getAddEditState().getAllChosenData();
					ArrayList< ArrayList<Integer> > foreignKeys = new ArrayList<ArrayList<Integer>>();
					
					for(int i = 0; i < foreignValue.size(); ++i) {
						ArrayList<String> valuesPerIndex = foreignValue.get(i);
						foreignKeys.add(new ArrayList<Integer>());
						
						for(String value : valuesPerIndex) {
							String tableName = null;
							String fieldName = null;
								
							if(model.getTableName().equals("Kunden")){
								tableName = "Angebote";
								fieldName = "titel";
							}
							else if(model.getTableName().equals("Angebote")){
								tableName = "Kunden";
								fieldName = "nachname";
							}
							else if(model.getTableName().equals("Projekte")){
								if(i == 0) {
									tableName = "Angebote";
									fieldName = "titel";
								}
								else if(i == 1) {
									tableName = "Ausgangsrechnungen";
									fieldName = "rechnungskürzel";
								}
							}
							else if(model.getTableName().equals("Ausgangsrechnungen")){
								tableName = "Kunden";
								fieldName = "nachname";
							}
							else if(model.getTableName().equals("Eingangsrechnungen")){
								tableName = "Kontakte";
								fieldName = "nachname";
							}
							else if(model.getTableName().equals("Rechnungszeilen")) {
								tableName = "Angebote";
								fieldName = "titel";
							} 
							else if(model.getTableName().equals("Buchungszeilen")){
								if(i == 0) {
									tableName = "Eingangsrechnungen";
									fieldName = "rechnungskürzel";
								}
								else if(i == 1) {
									tableName = "Ausgangsrechnungen";
									fieldName = "rechnungskürzel";
								}
								else if(i == 2) {
									tableName = "Kategorien";
									fieldName = "name";
								}
							}
							
							try {
								foreignKeys.get(i).add(appManager.getDatabaseManager().getForeignKeyForName(tableName, fieldName, value));
							} catch (DataProviderException e) {
								e.printStackTrace();
							}
						}
					}
					
					Object[] tmp = new Object[columnNames.length + 1];
					
					if(cmd_.equals("ADD")) {
						try {
							tmp[0] = appManager.getDatabaseManager().getNextIdForTableName(model.getTableName());
						} catch (DataProviderException e2) {
							e2.printStackTrace();
						}
					} else if (cmd_.equals("EDIT")) {
						tmp[0] = previous.getId();
					}
					
					int chooseBtnNumber = 0;
					for(int i = 1; i < tmp.length; i++) {
						if( data[i - 1].equals("placeholder") ) {
							ArrayList<Integer> keys = foreignKeys.get(chooseBtnNumber);
							
							if( isMappingValue(model.getTableName(), chooseBtnNumber) ) {
								String mappingTableName = getMappingTableName(model.getTableName(), chooseBtnNumber);
								try {
									data[i - 1] = appManager.getDatabaseManager().createMappingEntryForValues(model.getTableName(), mappingTableName, keys);
								} catch (DataProviderException e) {
									e.printStackTrace();
								}
							}
							else {
								data[i - 1] = keys.get(0);
							}
							
							chooseBtnNumber++;
						}
						
						tmp[i] = data[i - 1];
					}
					
					ArrayResultSet rs = new ArrayResultSet(tmp);
					
					DataObject object = null;
					try {
						object = DataObjectFactory.createObject(model.getTableName(), rs);
					} catch (SQLException e1) {
						/** This particular one should not throw SQL exception */
						e1.printStackTrace();
					}
					
					if(cmd_.equals("ADD")) {
						object.setState(DataObjectState.DataObjectStateNew);
						
						collection.add(object);
					} else if (cmd_.equals("EDIT")) {
						collection.remove(previous);

						object.setState(DataObjectState.DataObjectStateModified);
						
						collection.add(object);
					}
					
					try {
						appManager.getDatabaseManager().synchronizeObjectsForTableName(model.getTableName(), collection);
					} catch (DataProviderException e) {
						e.printStackTrace();
					}
					
					model.setDataObjectCollection(collection);
        			model.updateTableData();
					
					appManager.getDialogManager().popDialog();	
				}
			} 			
		}
		
		if(cmd.equals("CANCEL")) {
			appManager.getDialogManager().popDialog();
		}
	}

	boolean isMappingValue(String tableName, int buttonIndex) {
		if(tableName.equals("Kunden") && buttonIndex == 0 ||
	       tableName.equals("Projekte") && buttonIndex == 1 ||
	       tableName.equals("Ausgangsrechnungen") && (buttonIndex == 1 || buttonIndex == 2) ||
	       tableName.equals("Eingangsrechnungen") && buttonIndex == 1 ||
	       tableName.equals("Buchungszeilen") && buttonIndex == 2 ) {
			return true;
		}
		else {
			return false;
		}
	}
	
	String getMappingTableName(String tableName, int buttonIndex) {
		if(tableName.equals("Kunden") && buttonIndex == 0 ) {
			return "angebote_mapping";
		} else if(tableName.equals("Projekte") && buttonIndex == 1 ) {
			return "ausgangsrechnungen_mapping";
		} else if( tableName.equals("Ausgangsrechnungen") && buttonIndex == 1) {
			return "rzeilen_mapping";
		} else if( tableName.equals("Ausgangsrechnungen") && buttonIndex == 2 ) {
			return "bzeilen_mapping";
		} else if( tableName.equals("Eingangsrechnungen") && buttonIndex == 1 ) {
			return "bzeilen_mapping";
		} else if( tableName.equals("Buchungszeilen") && buttonIndex == 2 ) {
			return "kat_mapping";
		} else {
			Logger.getLogger(AddEditViewController.class.getName()).error("[ERROR] Requested mapping table name for a table that has no mapping table name defined.");
			return "";
		}
	}
}