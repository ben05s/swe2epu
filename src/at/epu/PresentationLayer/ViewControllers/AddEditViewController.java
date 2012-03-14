package at.epu.PresentationLayer.ViewControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import at.epu.BusinessLayer.ApplicationManager;
import at.epu.BusinessLayer.DatabaseManager;
import at.epu.PresentationLayer.GenericAddEditFormView;

public class AddEditViewController extends ViewController implements ActionListener{
	
	DatabaseManager databaseManager = ApplicationManager.getInstance().getDatabaseManager();
	Object[][] data = null;
	String[] columnNames = null;
	
	public AddEditViewController(String title) {
		if(title == "Kontakte"){
			columnNames = databaseManager.getDataSource().getContactDataModel().getColumnNames();
			data = databaseManager.getDataSource().getContactDataModel().getData();
		}
		if(title == "Kunden"){
			columnNames = databaseManager.getDataSource().getContactDataModel().getColumnNames();
			data = databaseManager.getDataSource().getContactDataModel().getData();
		}
		if(title == "Projekte"){
			columnNames = databaseManager.getDataSource().getContactDataModel().getColumnNames();
			data = databaseManager.getDataSource().getContactDataModel().getData();
		}
		//TODO: all tabs
		initialize();
	}

	void initialize() {
		ArrayList<JButton> buttonList = new ArrayList<JButton>();
		JButton btnSave = new JButton("Speichern");
		JButton btnCancel  = new JButton("Abbrechen");

		btnSave.setActionCommand("SAVE");
		btnSave.addActionListener(this);
		
		buttonList.add(btnSave);
		buttonList.add(btnCancel);

		ArrayList<JLabel> labelList = new ArrayList<JLabel>();
		for(int i=0;i<2;i++) {
			labelList.add(new JLabel("Hallo"));
		}
		
		ArrayList<JTextField> textList = new ArrayList<JTextField>();
		for(int i=0;i<2;i++) {
			textList.add(new JTextField("some data",20));
		}
		
		rootComponent = new GenericAddEditFormView(buttonList, labelList, textList);
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//save changes from form
	}
	
}
