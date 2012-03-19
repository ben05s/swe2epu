package at.epu.PresentationLayer.ViewControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import at.epu.BusinessLayer.ApplicationManager;
import at.epu.BusinessLayer.DatabaseManager;
import at.epu.PresentationLayer.GenericDetailTableView;

public class DetailViewController implements ActionListener{

	DatabaseManager databaseManager = ApplicationManager.getInstance().getDatabaseManager();
	JPanel rootComponent = new JPanel();
	JFrame newFrame;
	int rowindex; 	
	JFrame parent;	
	Object[][] data = null;
	String[] columnNames = null;
	ArrayList<Integer> indexChoosable = new ArrayList<Integer>();
	
	public DetailViewController(int rowindex_, JFrame parent_) {
		columnNames = databaseManager.getDataSource().getBillRowDataModel().getColumnNames();
		data = databaseManager.getDataSource().getBillRowDataModel().getData();
		
		rowindex = rowindex_;
		this.parent = parent_;
		
		initialize();
	}
	
	void initialize() {
		ArrayList<JButton> buttonList = new ArrayList<JButton>();
		JButton btnSave = new JButton("Speichern");
		JButton btnCancel  = new JButton("Abbrechen");
		JButton btnAdd  = new JButton("Hinzufügen");
 
		btnSave.setActionCommand("SAVE");
		btnSave.addActionListener(this);
		btnCancel.setActionCommand("CANCEL");
		btnCancel.addActionListener(this);
		btnAdd.setActionCommand("ADD");
		btnAdd.addActionListener(this);
		
		buttonList.add(btnSave);
		buttonList.add(btnCancel);
		buttonList.add(btnAdd);
		
		ArrayList<JMenuItem> menuList = new ArrayList<JMenuItem>();
		menuList.add(new JMenuItem("Editieren"));
		menuList.add(new JMenuItem("Löschen"));
		indexChoosable.add(0);
		rootComponent = new GenericDetailTableView(buttonList, menuList, parent, databaseManager.getDataSource().getBillRowDataModel());	
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		String cmd = event.getActionCommand();
		
		if(cmd.equals("SAVE")) {
			newFrame.dispose();
		}
		
		if(cmd.equals("CANCEL")) {
			newFrame.dispose();
		}		
		
		if(cmd.equals("ADD")) {
			String title = "Rechnungszeilen";
			newFrame = new JFrame();
			newFrame.setTitle("Hinzufügen");
			AddEditViewController controller = new AddEditViewController(title, cmd, rowindex, newFrame, indexChoosable);
			newFrame.add(controller.getRootComponent());
			newFrame.pack();
			newFrame.setLocationRelativeTo(parent);
			newFrame.setVisible(true);
			controller.setNewFrame(newFrame);
		}
	}
	
	public void setNewFrame(JFrame newFrame) {
		this.newFrame = newFrame;
	}
	
	public JPanel getRootComponent() {
		return rootComponent;
	}
}
