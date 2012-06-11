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

public class ProjectViewController extends ViewController implements ActionListener{
	public ProjectViewController(JFrame mainWindow) {
		super(mainWindow);
	}
	
	@Override
	void initialize() {
		ApplicationManager appManager = ApplicationManager.getInstance();
		
		BackofficeTableModel model = appManager.getModelForTableName("Projekte");
		
		registerActionHandler(new FilterActionHandler(this));
		registerActionHandler(new AddActionHandler(this, model));
		
		ArrayList<JButton> buttonList = getButtonsFromHandlers();
		buttonList.add(new JButton("Zeiten Buchen"));
		
		ArrayList<JLabel> labelList = new ArrayList<JLabel>();
		labelList.add(new JLabel("Stundensatz(gesamt): "));
		
		ArrayList<JMenuItem> menuList = new ArrayList<JMenuItem>();
		menuList.add(new JMenuItem("Editieren"));
		menuList.add(new JMenuItem("Löschen"));
		menuList.add(new JMenuItem("Ausgangsrechnung stellen"));
		
		title = "Projekte";
		
		model.getAddEditState().getIndexChoosable().add(1);
		model.getAddEditState().getIndexChoosable().add(2);
		
		rootComponent = new GenericSplitTableView(buttonList, labelList, menuList, title,
												  appManager.getModelForTableName("Projekte"));
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		super.actionPerformed(event);
	}
}
