package at.epu.PresentationLayer.ViewControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import at.epu.BusinessLayer.ApplicationManager;
import at.epu.BusinessLayer.DatabaseManager;
import at.epu.DataAccessLayer.DataModels.BackofficeTableModel;
import at.epu.PresentationLayer.GenericAddEditFormView;

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
		indexChoosable = indexChoosable_;
		
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
		databaseManager = ApplicationManager.getInstance().getDatabaseManager();
		
		//get to current model 
		model = appManager.getActiveTableModel();
		//special case because the detailTable(Rechnungszeilen) is within the Rechnungen Tab (Ausgangsrechnungen)
		if(model.isDetailTableView()) {
			model = databaseManager.getDataSource().getBillRowDataModel();
		}
		
		columnNames = model.getAddEditColNames();
		data = model.getAddEditData();
		
		
		ArrayList<JLabel> labelList = new ArrayList<JLabel>();
		for(int i=0;i<columnNames.length;i++) {
			labelList.add(new JLabel(columnNames[i], JLabel.TRAILING));
		}
		
		textList = new ArrayList<JTextField>();
		if(cmd_.equals("EDIT")) {
			//fill teh addEdit form with the data of the selected row
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
		rootComponent = new GenericAddEditFormView(buttonList, labelList, textList, indexChoosable);				
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		String cmd = event.getActionCommand();
			
		ApplicationManager appManager = ApplicationManager.getInstance();
		
		//when there are 3 different choosable buttons in the add/edit form every button needs to handle a different functionality
		if(cmd.equals("CHOOSE1")) {
			appManager.getDialogManager().pushDialog(new AddEditChooserViewController(cmd, cmd_, rowindex, indexChoosable));
		}
		
		if(cmd.equals("CHOOSE2")) {
			appManager.getDialogManager().pushDialog(new AddEditChooserViewController(cmd, cmd_, rowindex, indexChoosable));
		}
		
		if(cmd.equals("CHOOSE3")) {
			appManager.getDialogManager().pushDialog(new AddEditChooserViewController(cmd, cmd_, rowindex, indexChoosable));
		}
		
		if(cmd.equals("SAVE")) {
			Object[] data = new Object[columnNames.length];
			int skip = 0;
			for(int i=0;i<columnNames.length;i++) {
				//make a placeholder wherever there was a choosable button, the data from the choosables will be put into the data array in the saveData function
				for(int x=0;x<this.indexChoosable.size();x++) {
					if(i == this.indexChoosable.get(x)) {
						model.setChooseIndex(i);
						data[i] = "placeholder";
						skip++;
					}
				}
				if(data[i] == null) {
					data[i] = textList.get(i-skip).getText();
				}
			}

			if(cmd_.equals("ADD")) {
				model.saveData(model, data); 
			} 
			if(cmd_.equals("EDIT")) { 
				model.updateData(model, data, rowindex); 
			} 
			
			appManager.getDialogManager().popDialog();
			
		}
		
		if(cmd.equals("CANCEL")) {
			appManager.getDialogManager().popDialog();
		}
	}
}
