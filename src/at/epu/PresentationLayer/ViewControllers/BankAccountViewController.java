package at.epu.PresentationLayer.ViewControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

import at.epu.BusinessLayer.ApplicationManager;
import at.epu.PresentationLayer.ActionHandlers.AddActionHandler;
import at.epu.PresentationLayer.ActionHandlers.FilterActionHandler;
import at.epu.PresentationLayer.DataModels.BackofficeTableModel;
import at.epu.PresentationLayer.Views.GenericSplitTableView;

public class BankAccountViewController extends ViewController implements ActionListener{
	public BankAccountViewController(JFrame mainWindow) {
		super(mainWindow);
	}
	
	@Override
	void initialize() {
		title = "Bankkonto";
		
		ApplicationManager appManager = ApplicationManager.getInstance();
		
		BackofficeTableModel model = appManager.getModelForTableName("Buchungszeilen");
		
		registerActionHandler(new FilterActionHandler(this));
		registerActionHandler(new AddActionHandler(this, model));
		
		ArrayList<JButton> buttonList = getButtonsFromHandlers();
		
		ArrayList<JLabel> labelList = new ArrayList<JLabel>();
		
		ArrayList<JMenuItem> menuList = new ArrayList<JMenuItem>();
		menuList.add(new JMenuItem("Editieren"));
		menuList.add(new JMenuItem("Löschen"));
		/*EDIT: NOT NEEDED ACCORIND TO SZACZEC
		menuList.add(new JMenuItem("Rechnung Splitten"));*/
		
		model.getAddEditState().getIndexChoosable().add(0);
		model.getAddEditState().getIndexChoosable().add(1);
		model.getAddEditState().getIndexChoosable().add(5);
		
		rootComponent = new GenericSplitTableView(buttonList, labelList, menuList, title,
                										appManager.getModelForTableName("Buchungszeilen"));
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		super.actionPerformed(event);
	}
}
