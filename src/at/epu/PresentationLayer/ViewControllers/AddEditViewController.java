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
	String cmd;			//determines what command called the controller(Edit or Add)
	int rowindex; 
	ArrayList<JTextField> textList;
	JFrame parent;
	
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
		
		cmd = action;
		rowindex = rowindex_;
		this.parent = parent_;
		
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
		if(cmd.equals("EDIT")) {
			for(int i=0;i<columnNames.length;i++) {
				if(data[rowindex][i] != null) {
					textList.add(new JTextField(data[rowindex][i].toString(),20));
				}
				else{
					textList.add(new JTextField("",20));
				}
			}
		}
		if(cmd.equals("ADD")) {
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
			Object[] data = {textList.get(0).getText(), textList.get(1).getText(),
					textList.get(2).getText(),textList.get(3).getText(),textList.get(4).getText(),
					textList.get(5).getText()}; 
			databaseManager.getDataSource().getContactDataModel().saveData(data);
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
