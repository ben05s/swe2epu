package at.epu.PresentationLayer.ViewControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

import at.epu.BusinessLayer.ApplicationManager;
import at.epu.PresentationLayer.GenericSplitTableView;
import at.epu.PresentationLayer.ActionHandlers.AddActionHandler;
import at.epu.PresentationLayer.ActionHandlers.FilterActionHandler;

public class ContactViewController extends ViewController implements ActionListener {
	public ContactViewController(JFrame mainWindow) {
		super(mainWindow);
	}
	
	@Override
	void initialize() {
		ApplicationManager appManager = ApplicationManager.getInstance();
		
		registerActionHandler(new FilterActionHandler(this));
		registerActionHandler(new AddActionHandler(this));
		
		ArrayList<JButton> buttonList = getButtonsFromHandlers();

		ArrayList<JLabel> labelList = new ArrayList<JLabel>();
		
		ArrayList<JMenuItem> menuList = new ArrayList<JMenuItem>();
		JMenuItem popEdit = new JMenuItem("Editieren");
		menuList.add(popEdit);
		
		JMenuItem popDelete = new JMenuItem("Löschen");
		menuList.add(popDelete);
		
		title = "Kontakte";
		
		rootComponent = new GenericSplitTableView(buttonList, labelList, menuList, title,
					                              appManager.getModelForTableName("Kontakte"));
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		super.actionPerformed(event);
	}
}
