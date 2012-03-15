package at.epu.PresentationLayer.ViewControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import at.epu.BusinessLayer.ApplicationManager;
import at.epu.BusinessLayer.DatabaseManager;
import at.epu.PresentationLayer.GenericAddEditFormView;
import at.epu.PresentationLayer.GenericSplitTableView;

public class AddEditViewController implements ActionListener{
	
	DatabaseManager databaseManager = ApplicationManager.getInstance().getDatabaseManager();
	Object[][] data = null;
	String[] columnNames = null;
	JPanel rootComponent = new JPanel();
	JFrame newFrame;
	String cmd_;			//determines what command called the controller(Edit or Add)
	int rowindex; 
	ArrayList<JTextField> textList;
	JFrame parent;
	String title;
	
	public void setNewFrame(JFrame newFrame) {
		this.newFrame = newFrame;
	}

	public AddEditViewController(String title, String action, int rowindex_, JFrame parent_) {
		if(title == "Kontakte"){
			columnNames = databaseManager.getDataSource().getContactDataModel().getColumnNames();
			data = databaseManager.getDataSource().getContactDataModel().getData();
		}
		if(title == "Kunden"){
			columnNames = databaseManager.getDataSource().getCustomerDataModel().getColumnNames();
			data = databaseManager.getDataSource().getCustomerDataModel().getData();
		}
		if(title == "Angebote"){
			columnNames = databaseManager.getDataSource().getOfferDataModel().getColumnNames();
			data = databaseManager.getDataSource().getOfferDataModel().getData();
		}
		if(title == "Projekte"){
			columnNames = databaseManager.getDataSource().getProjectDataModel().getColumnNames();
			data = databaseManager.getDataSource().getProjectDataModel().getData();
		}
		//TODO: distinguish between IN and OUT bill 
		if(title == "Rechnungen"){
			columnNames = databaseManager.getDataSource().getOutBillDataModel().getColumnNames();
			data = databaseManager.getDataSource().getOutBillDataModel().getData();
		}
		if(title == "Bankkonto"){
			columnNames = databaseManager.getDataSource().getBankAccountDataModel().getColumnNames();
			data = databaseManager.getDataSource().getBankAccountDataModel().getData();
		}
		
		cmd_ = action;
		rowindex = rowindex_;
		this.parent = parent_;
		this.title = title;
		
		initialize();
	}

	void initialize() {
		ArrayList<JButton> buttonList = new ArrayList<JButton>();
		JButton btnSave = new JButton("Speichern");
		JButton btnCancel  = new JButton("Abbrechen");
 
		btnSave.setActionCommand("SAVE");
		btnSave.addActionListener(this);
		btnCancel.setActionCommand("CANCEL");
		btnCancel.addActionListener(this);
		
		buttonList.add(btnSave);
		buttonList.add(btnCancel);

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
				textList.add(new JTextField("",20));
			}
		}
		
		
		rootComponent = new GenericAddEditFormView(buttonList, labelList, textList);
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		String cmd = event.getActionCommand();
		
		if(cmd.equals("SAVE")) {
			Object[] data = new Object[textList.size()];
			
			for(int i=0;i<textList.size();i++) {
				data[i] = textList.get(i).getText();
			}
			if(title == "Kontakte") { 
				if(cmd_.equals("ADD")) { databaseManager.getDataSource().getContactDataModel().saveData(data); } 
				if(cmd_.equals("EDIT")) { databaseManager.getDataSource().getContactDataModel().updateData(data,rowindex);  } 
			}
			if(title == "Kunden") { 
				if(cmd_.equals("ADD")) {databaseManager.getDataSource().getCustomerDataModel().saveData(data); }
				if(cmd_.equals("EDIT")) { databaseManager.getDataSource().getCustomerDataModel().updateData(data,rowindex); }
			}
			if(title == "Angebote") { 
				if(cmd_.equals("ADD")) {databaseManager.getDataSource().getOfferDataModel().saveData(data); }
				if(cmd_.equals("EDIT")) { databaseManager.getDataSource().getOfferDataModel().updateData(data,rowindex); }
			}
			if(title == "Projekte") { 
				if(cmd_.equals("ADD")) {databaseManager.getDataSource().getProjectDataModel().saveData(data); }
				if(cmd_.equals("EDIT")) { databaseManager.getDataSource().getProjectDataModel().updateData(data,rowindex); }
			}
			//TODO: distinguish between IN and OUT bill 
			if(title == "Rechnungen") { 
				if(cmd_.equals("ADD")) {databaseManager.getDataSource().getOutBillDataModel().saveData(data); }
				if(cmd_.equals("EDIT")) { databaseManager.getDataSource().getOutBillDataModel().updateData(data,rowindex); }
			}
			if(title == "Bankkonto") { 
				if(cmd_.equals("ADD")) {databaseManager.getDataSource().getBankAccountDataModel().saveData(data); }
				if(cmd_.equals("EDIT")) { databaseManager.getDataSource().getBankAccountDataModel().updateData(data,rowindex); }
			}
			newFrame.dispose();
			
		}
		
		if(cmd.equals("CANCEL")) {
			newFrame.dispose();
		}
	}
	
	public JPanel getRootComponent() {
		return rootComponent;
	}
	
}
