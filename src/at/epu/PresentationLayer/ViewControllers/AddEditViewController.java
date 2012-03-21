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
	BackofficeTableModel model = null;
	JFrame chooseFrame;
	String cmd_;			//determines what command called the controller(Edit or Add)
	int rowindex; 
	String title;
	ArrayList<JTextField> textList;
	ArrayList<Integer> indexChoosable = new ArrayList<Integer>();
	ArrayList<Integer> chooseIndex = new ArrayList<Integer>();

	public AddEditViewController(String title_, String action, int rowindex_, ArrayList<Integer> indexChoosable_) {	
		this.cmd_ = action;
		rowindex = rowindex_;
		this.title = title_;
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
		DatabaseManager databaseManager = ApplicationManager.getInstance().getDatabaseManager();
		model = appManager.getActiveTableModel();	
		String[] columnNames = null;
		Object[][] data = null;
		
		if(model.isDetailTableView()) {
			columnNames = databaseManager.getDataSource().getBillRowDataModel().getAddEditColNames();
			data = databaseManager.getDataSource().getBillRowDataModel().getAddEditData();
		} else {
			columnNames = model.getAddEditColNames();
			data = model.getAddEditData();
		}
		
		ArrayList<JLabel> labelList = new ArrayList<JLabel>();
		for(int i=0;i<columnNames.length;i++) {
			labelList.add(new JLabel(columnNames[i], JLabel.TRAILING));
		}
		
		textList = new ArrayList<JTextField>();
		if(cmd_.equals("EDIT")) {
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
				//ignore some label because there is a button to choose this data
				if(columnNames[i].equals("Kategorie") || columnNames[i].equals("Eingangsrechnung ID") || 
				   columnNames[i].equals("Ausgangsrechnung ID") || columnNames[i].equals("Angebot") ||
				   columnNames[i].equals("Angebot") || columnNames[i].equals("Kunde") || 
				   columnNames[i].equals("Angebote")) {
					chooseIndex = model.getChooseIndex();
					continue;
				}
				
				if(columnNames[i].equals("Status")) {
					textList.add(new JTextField("offen",20));
				} else {
					textList.add(new JTextField("",20));
				}
			}
		}	
		
		rootComponent = new GenericAddEditFormView(buttonList, labelList, textList, indexChoosable);				
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		String cmd = event.getActionCommand();
		String[] columnNames = model.getAddEditColNames();
		ApplicationManager appManager = ApplicationManager.getInstance();
		
		if(cmd.equals("CHOOSE1")) {
			appManager.getDialogManager().pushDialog(new AddEditChooserViewController(cmd, cmd_, rowindex));
		}
		
		if(cmd.equals("CHOOSE2")) {
			appManager.getDialogManager().pushDialog(new AddEditChooserViewController(cmd, cmd_, rowindex));
		}
		
		if(cmd.equals("CHOOSE3")) {
			appManager.getDialogManager().pushDialog(new AddEditChooserViewController(cmd, cmd_, rowindex));
		}
		
		if(cmd.equals("SAVE")) {
			Object[] data = new Object[columnNames.length];
			int skip = 0;
			for(int i=0;i<columnNames.length;i++) {
				for(int x=0;x<this.chooseIndex.size();x++) {
					if(i == this.chooseIndex.get(x)) {
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
				model.updateData(data,rowindex,title); 
			} 
			if(model.isDetailTableView()) { model.unsetDetailTableView(); }
			appManager.getDialogManager().popDialog();
			
		}
		
		if(cmd.equals("CANCEL")) {
			if(model.isDetailTableView()) { model.unsetDetailTableView(); }
			appManager.getDialogManager().popDialog();
		}
	}
}
