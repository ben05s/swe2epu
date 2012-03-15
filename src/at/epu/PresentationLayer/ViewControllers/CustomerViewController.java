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

public class CustomerViewController extends ViewController implements ActionListener{
	
	private JFrame parent;
	private String tab_title;
	private JFrame newFrame;
	
	public CustomerViewController(JFrame mainWindow) {
		parent = mainWindow;
	}
	
	@Override
	void initialize() {
		DatabaseManager databaseManager = ApplicationManager.getInstance().getDatabaseManager();
		
		ArrayList<JButton> buttonList = new ArrayList<JButton>();
		JButton btnFind = new JButton("Finden");
		JButton btnAdd  = new JButton("Hinzufügen");
		
		btnFind.setActionCommand("FILTER");
		btnFind.addActionListener(this);
		btnAdd.setActionCommand("ADD");
		btnAdd.addActionListener(this);
		
		buttonList.add(btnFind);
		buttonList.add(btnAdd);

		ArrayList<JLabel> labelList = new ArrayList<JLabel>();
		
		ArrayList<JMenuItem> menuList = new ArrayList<JMenuItem>();
		menuList.add(new JMenuItem("Editieren"));
		menuList.add(new JMenuItem("Löschen"));
		tab_title = "Kunden";
		rootComponent = new GenericSplitTableView(buttonList, labelList, menuList, tab_title, parent,
                									databaseManager.getDataSource().getCustomerDataModel());
		
		title = "Kunden";
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String cmd = event.getActionCommand();
		
		if( cmd.equals("FILTER") ) {
			DatabaseManager databaseManager = ApplicationManager.getInstance().getDatabaseManager();

		}
		
		if( cmd.equals("ADD") ) {
			AddEditViewController controller = new AddEditViewController(this.getTitle(), cmd, 0, parent);
			newFrame = new JFrame();
			newFrame.setTitle("Hinzufügen");
			newFrame.add(controller.getRootComponent());
			newFrame.pack();
			newFrame.setLocationRelativeTo(parent);
    		newFrame.setVisible(true);
    		controller.setNewFrame(newFrame);
		}
	}
}
