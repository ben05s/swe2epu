package at.epu.PresentationLayer.ViewControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

import at.epu.BusinessLayer.ApplicationManager;
import at.epu.BusinessLayer.DatabaseManager;
import at.epu.PresentationLayer.GenericSplitTableView;
import at.epu.PresentationLayer.ActionHandlers.AddActionHandler;
import at.epu.PresentationLayer.ActionHandlers.FilterActionHandler;

public class CustomerViewController extends ViewController implements ActionListener{
	public CustomerViewController(JFrame mainWindow) {
		super(mainWindow);
	}
	
	@Override
	void initialize() {
		DatabaseManager databaseManager = ApplicationManager.getInstance().getDatabaseManager();
		
		registerActionHandler(new FilterActionHandler(this));
		registerActionHandler(new AddActionHandler(this));
		
		ArrayList<JButton> buttonList = getButtonsFromHandlers();

		ArrayList<JLabel> labelList = new ArrayList<JLabel>();
		
		ArrayList<JMenuItem> menuList = new ArrayList<JMenuItem>();
		menuList.add(new JMenuItem("Editieren"));
		menuList.add(new JMenuItem("L�schen"));
		
		title = "Kunden";
		
		getIndexChoosable().add(6);
		
		rootComponent = new GenericSplitTableView(buttonList, labelList, menuList, title, 
                									databaseManager.getDataSource().getCustomerDataModel());
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		super.actionPerformed(event);
	}
}
