package at.epu.PresentationLayer.ViewControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTable;

import at.epu.BusinessLayer.ApplicationManager;
import at.epu.BusinessLayer.DatabaseManager;
import at.epu.PresentationLayer.GenericSplitTableView;

public class ContactViewController extends ViewController implements ActionListener {	
	JPanel panel = new JPanel();
	private JFrame newFrame;
	private JFrame parent;
	private String tab_title;
	private JTable table;
	
	public JTable getTable() {
		return table;
	}
	public void setTable(JTable table) {
		this.table = table;
	}
	public ContactViewController(JFrame mainWindow) {
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
		JMenuItem popEdit = new JMenuItem("Editieren");
		popEdit.setActionCommand("EDIT");
		popEdit.addActionListener(this);
		menuList.add(popEdit);
		
		JMenuItem popDelete = new JMenuItem("Löschen");
		popEdit.setActionCommand("DELETE");
		popEdit.addActionListener(this);
		menuList.add(popDelete);
		tab_title = "Kontakte";
		rootComponent = new GenericSplitTableView(buttonList, labelList, menuList, tab_title, parent,
					                              databaseManager.getDataSource().getContactDataModel());
		
		title = "Kontakte";
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String cmd = event.getActionCommand();
		
		if( cmd.equals("FILTER") ) {
			DatabaseManager databaseManager = ApplicationManager.getInstance().getDatabaseManager();
			
			databaseManager.getDataSource().getContactDataModel().filterDataModel("Kathy");
		}
		
		if( cmd.equals("ADD") ) {
			AddEditViewController controller = new AddEditViewController(this.getTitle(), cmd, 0, parent);
			newFrame = new JFrame();
			newFrame.setTitle("Hinzufügen/Editieren");
			newFrame.add(controller.getRootComponent());
			newFrame.pack();
			newFrame.setLocationRelativeTo(parent);
    		newFrame.setVisible(true);
    		controller.setNewFrame(newFrame);
		}	
		
		if( cmd.equals("DELETE")) {
			DatabaseManager databaseManager = ApplicationManager.getInstance().getDatabaseManager();
			
			databaseManager.getDataSource().getContactDataModel().deleteData(0);
			
		}
	}
}
