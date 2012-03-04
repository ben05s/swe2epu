package at.epu.PresentationLayer.ViewControllers;

import java.util.ArrayList;

import javax.swing.JButton;

import at.epu.BusinessLayer.ApplicationManager;
import at.epu.BusinessLayer.DatabaseManager;
import at.epu.PresentationLayer.GenericSplitTableView;

public class ContactViewController extends ViewController {
	@Override
	void initialize() {
		DatabaseManager databaseManager = ApplicationManager.getInstance().getDatabaseManager();
		
		ArrayList<JButton> buttonList = new ArrayList<JButton>();
		buttonList.add(new JButton("Finden"));
		buttonList.add(new JButton("Hinzufügen"));

		rootComponent = new GenericSplitTableView(buttonList, 
					                              databaseManager.getDataSource().getContactDataModel());
		
		title = "Kontakte";
	}
}
