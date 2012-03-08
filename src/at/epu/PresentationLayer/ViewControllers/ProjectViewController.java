package at.epu.PresentationLayer.ViewControllers;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

import at.epu.BusinessLayer.ApplicationManager;
import at.epu.BusinessLayer.DatabaseManager;
import at.epu.PresentationLayer.GenericSplitTableView;

public class ProjectViewController extends ViewController{
	@Override
	void initialize() {
		DatabaseManager databaseManager = ApplicationManager.getInstance().getDatabaseManager();
		
		ArrayList<JButton> buttonList = new ArrayList<JButton>();
		buttonList.add(new JButton("Finden"));
		buttonList.add(new JButton("Hinzufügen"));
		buttonList.add(new JButton("Zeiten Buchen"));
		
		ArrayList<JLabel> labelList = new ArrayList<JLabel>();
		labelList.add(new JLabel("Offene Projekte: "));
		labelList.add(new JLabel("Stundensatz(gesamt): "));
		
		ArrayList<JMenuItem> menuList = new ArrayList<JMenuItem>();
		
		rootComponent = new GenericSplitTableView(buttonList, labelList, menuList,
					                              databaseManager.getDataSource().getProjectDataModel());
		
		title = "Projekte";
	}
}
