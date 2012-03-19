package at.epu.PresentationLayer.ViewControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import at.epu.BusinessLayer.ApplicationManager;
import at.epu.BusinessLayer.DatabaseManager;
import at.epu.PresentationLayer.GenericChooserFormView;

public class AddEditChooserViewController implements ActionListener {
	
	DatabaseManager databaseManager = ApplicationManager.getInstance().getDatabaseManager();
	Object[][] data = null;
	String[] columnNames = null;
	JPanel rootComponent = new JPanel();
	JFrame chooseFrame;
	ArrayList<JCheckBox> checkList;
	ArrayList<JRadioButton> radioList;
	String cmd_;			//determines what command called the controller(Edit or Add)
	int rowindex; 
	JFrame parent;
	String title;
	ArrayList<String> preselectedItems = new ArrayList<String>();
	
	public void setNewFrame(JFrame newFrame) {
		this.chooseFrame = newFrame;
	}
	
	public AddEditChooserViewController(String title, String action, int rowindex_, JFrame parent_) {
		if(title == "Kunden"){
			data = databaseManager.getDataSource().getOfferDataModel().getData();
		}
		if(title == "Angebote"){
			data = databaseManager.getDataSource().getOfferDataModel().getAddEditData();
			preselectedItems = databaseManager.getDataSource().getCustomerDataModel().getChoosenData();
		}
		if(title == "Projekte"){
			data = databaseManager.getDataSource().getProjectDataModel().getAddEditData();
		}
		if(title == "Rechnungen"){
			data = databaseManager.getDataSource().getOutBillDataModel().getAddEditData();
		}
		if(title == "Bankkonto"){
			data = databaseManager.getDataSource().getBankAccountDataModel().getAddEditData();
		}
		if(title == "Rechnungszeilen"){
			data = databaseManager.getDataSource().getBillRowDataModel().getAddEditData();
		}
		
		this.cmd_ = action;
		rowindex = rowindex_;
		this.parent = parent_;
		this.title = title;
		
		initialize();
	}

	private void initialize() {
		ArrayList<JButton> buttonList = new ArrayList<JButton>();
		JButton btnOk = new JButton("OK");
		JButton btnDeselect  = new JButton("Deselect All");
		
		btnOk.setActionCommand("OK");
		btnOk.addActionListener(this);
		btnDeselect.setActionCommand("DESELECT");
		btnDeselect.addActionListener(this);
		
		buttonList.add(btnOk);
		buttonList.add(btnDeselect);

		
		checkList = new ArrayList<JCheckBox>();
		if(cmd_.equals("EDIT")) {
			for(int i=0;i<data.length;i++) {
				//checkList.add(new JCheckBox(this.data[i][1].toString(), false));
			}
		}
		if(cmd_.equals("ADD")) {
			for(int i=0;i<this.data.length;i++) {
				JCheckBox box;
				if(preselectedItems.isEmpty()) {
					box = new JCheckBox(this.data[i][1].toString(), false);
				} else {
					if(preselectedItems.get(i).equals(this.data[i][1].toString())) {
						box = new JCheckBox(this.data[i][1].toString(), true);
					} else {
						box = new JCheckBox(this.data[i][1].toString(), false);
					}
				}
				box.setActionCommand("CHECKBOX"+i);
				box.addActionListener(this);
				checkList.add(box);
			}
		}	
		
		rootComponent = new GenericChooserFormView(buttonList, checkList, radioList);	
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String cmd = event.getActionCommand();
		
		for(int i=0;i<this.data.length;i++) {
			if(cmd.equals("CHECKBOX"+i)) {
				AbstractButton ab = (AbstractButton) event.getSource();
				if(ab.getModel().isSelected()) {
					if(title == "Kunden") { 
						if(cmd_.equals("ADD")) { databaseManager.getDataSource().getCustomerDataModel().addChoosenData(this.data[i][1].toString()); }
						if(cmd_.equals("EDIT")) { }
					}
				}
				if(! ab.getModel().isSelected()) {
					if(title == "Kunden") { 
						if(cmd_.equals("ADD")) { databaseManager.getDataSource().getCustomerDataModel().removeChoosenData(this.data[i][1].toString()); }
						if(cmd_.equals("EDIT")) { }
					}
				}
			}
		}
		if(cmd.equals("OK")) {
			chooseFrame.dispose();
		}
	}
	
	public JPanel getRootComponent() {
		return rootComponent;
	}

}
