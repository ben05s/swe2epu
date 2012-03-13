package at.epu.PresentationLayer.ViewControllers;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

import at.epu.BusinessLayer.ApplicationManager;
import at.epu.BusinessLayer.DatabaseManager;
import at.epu.PresentationLayer.GenericSplitTableView;

// TODO: combine OutBill and InBill DataObjects
// currently only OutBill
public class BillViewController extends ViewController{
	@Override
	void initialize() {
		DatabaseManager databaseManager = ApplicationManager.getInstance().getDatabaseManager();
		
		ArrayList<JButton> buttonList = new ArrayList<JButton>();
		buttonList.add(new JButton("Finden"));
		buttonList.add(new JButton("Hinzufügen"));
		buttonList.add(new JButton("PDF aller Rechnungen generieren"));
		buttonList.add(new JButton("Rechnungsreport PDF ..."));
		buttonList.add(new JButton("Ein- Ausgaben Report PDF ..."));
		
		ArrayList<JLabel> labelList = new ArrayList<JLabel>();
		labelList.add(new JLabel("Offene Rechnungen: "));
		
		ArrayList<JMenuItem> menuList = new ArrayList<JMenuItem>();
		menuList.add(new JMenuItem("Editieren"));
		menuList.add(new JMenuItem("Details"));
		menuList.add(new JMenuItem("Löschen"));
		
		rootComponent = new GenericSplitTableView(buttonList, labelList, menuList,
					                              databaseManager.getDataSource().getOutBillDataModel());
		
		title = "Rechnungen";
	}
}
