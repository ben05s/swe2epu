package at.epu.PresentationLayer.ViewControllers;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;

import at.epu.BusinessLayer.ApplicationManager;
import at.epu.BusinessLayer.DatabaseManager;
import at.epu.PresentationLayer.GenericSplitTableView;

public class OfferViewController extends ViewController{
	@Override
	void initialize() {
		DatabaseManager databaseManager = ApplicationManager.getInstance().getDatabaseManager();
		
		ArrayList<JButton> buttonList = new ArrayList<JButton>();
		buttonList.add(new JButton("Finden"));
		buttonList.add(new JButton("Hinzufügen"));
		buttonList.add(new JButton("Jahresprognose der Angebote (PDF)"));

		ArrayList<JLabel> labelList = new ArrayList<JLabel>();
		
		rootComponent = new GenericSplitTableView(buttonList, labelList,
					                              databaseManager.getDataSource().getOfferDataModel());
		
		title = "Angebote";
	}
}
