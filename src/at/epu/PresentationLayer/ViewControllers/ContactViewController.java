package at.epu.PresentationLayer.ViewControllers;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import at.epu.BusinessLayer.ApplicationManager;
import at.epu.BusinessLayer.DatabaseManager;
import at.epu.PresentationLayer.GenericSplitTableView;

public class ContactViewController extends ViewController implements ActionListener {	
	JPanel panel = new JPanel();
	JFrame newFrame;
	
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
		
		rootComponent = new GenericSplitTableView(buttonList, labelList, menuList,
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
			newFrame = new JFrame();
			newFrame.setTitle("Hinzufügen/Editieren");
			
			JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			newFrame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
			tabbedPane.addTab(this.getTitle(), new AddEditViewController(this.getTitle()).getRootComponent());
			newFrame.setBounds(300, 150, 300, 500);
    		newFrame.setVisible(true);
		}
	}
	
	public void addTabtoWindow(AddEditViewController viewController) {
		
	}
}
