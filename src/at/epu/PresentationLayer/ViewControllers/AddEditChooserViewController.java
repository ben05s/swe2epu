package at.epu.PresentationLayer.ViewControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import at.epu.BusinessLayer.ApplicationManager;
import at.epu.BusinessLayer.DatabaseManager;
import at.epu.PresentationLayer.GenericChooserFormView;

public class AddEditChooserViewController extends ViewController implements ActionListener {
	
	DatabaseManager databaseManager = ApplicationManager.getInstance().getDatabaseManager();
	Object[][] data = null;
	String[] columnNames = null;
	JPanel rootComponent = new JPanel();
	JFrame chooseFrame;
	ArrayList<JCheckBox> checkList;
	ArrayList<JRadioButton> radioList;
	String cmd_;			//determines what command called the controller(Edit or Add)
	int rowindex; 
	String title;
	ArrayList<String> preselectedItems = new ArrayList<String>();
	
	public void setNewFrame(JFrame newFrame) {
		this.chooseFrame = newFrame;
	}
	
	public AddEditChooserViewController(String title, String action, int rowindex_) {
		if(title == "Kunden"){
			data = databaseManager.getDataSource().getOfferDataModel().getData();
			preselectedItems = databaseManager.getDataSource().getCustomerDataModel().getChoosenData();
		}
		if(title == "Angebote"){
			data = databaseManager.getDataSource().getCustomerDataModel().getAddEditData();
			preselectedItems = databaseManager.getDataSource().getOfferDataModel().getChoosenData();
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
		this.title = title;
		
		initialize();
	}

	@Override
	public void initialize() {
		ArrayList<JButton> buttonList = new ArrayList<JButton>();
		JButton btnOk = new JButton("OK");
		JButton btnDeselect  = new JButton("Deselect All");
		
		btnOk.setActionCommand("OK");
		btnOk.addActionListener(this);
		btnDeselect.setActionCommand("DESELECT");
		btnDeselect.addActionListener(this);
		
		buttonList.add(btnOk);
		buttonList.add(btnDeselect);

		boolean checked[] = new boolean[this.data.length];
		
		checkList = new ArrayList<JCheckBox>();
		if(cmd_.equals("EDIT")) {
			for(int i=0;i<data.length;i++) {
				//checkList.add(new JCheckBox(this.data[i][1].toString(), false));
			}
		}
		if(cmd_.equals("ADD")) {
			for(int i=0;i<this.data.length;i++) {
				JCheckBox box;
				if(databaseManager.getDataSource().getCustomerDataModel().getChoosenData().isEmpty()) {
					checked[i] = false;
				} /*else {
					checked[i] = false;
					System.out.println(databaseManager.getDataSource().getCustomerDataModel().getChoosenData().size());
					for(int x=0;x<this.data.length;x++) {
						for(int z=count;z<databaseManager.getDataSource().getCustomerDataModel().getChoosenData().size();z++) {
							if(databaseManager.getDataSource().getCustomerDataModel().getChoosenData().get(z).equals(this.data[x][1].toString())) {
								checked[i] = true;
								count++;
								break;
							}
						}
					}
				}*/
				box = new JCheckBox(this.data[i][1].toString(), checked[i]);
				box.setActionCommand("CHECKBOX"+i);
				box.addActionListener(this);
				checkList.add(box);
			}
		}
			
		radioList = new ArrayList<JRadioButton>();
		ButtonGroup group = new ButtonGroup();
		
		if(cmd_.equals("EDIT")) {
			for(int i=0;i<data.length;i++) {
				//checkList.add(new JCheckBox(this.data[i][1].toString(), false));
			}
		}
		if(cmd_.equals("ADD")) {
			for(int i=0;i<this.data.length;i++) {
				JRadioButton box;
				if(databaseManager.getDataSource().getOfferDataModel().getChoosenData().isEmpty()) {
					checked[i] = false;
				} /*else {
					checked[i] = false;
					System.out.println(databaseManager.getDataSource().getCustomerDataModel().getChoosenData().size());
					for(int x=0;x<this.data.length;x++) {
						for(int z=count;z<databaseManager.getDataSource().getCustomerDataModel().getChoosenData().size();z++) {
							if(databaseManager.getDataSource().getCustomerDataModel().getChoosenData().get(z).equals(this.data[x][1].toString())) {
								checked[i] = true;
								count++;
								break;
							}
						}
					}
				}*/
				box = new JRadioButton(this.data[i][1].toString(), checked[i]);
				group.add(box);
				box.setActionCommand("CHECKBOX"+i);
				box.addActionListener(this);
				radioList.add(box);
			}
		}	
		
		rootComponent = new GenericChooserFormView(buttonList, checkList, radioList, title);	
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
					if(title == "Angebote") { 
						if(cmd_.equals("ADD")) { databaseManager.getDataSource().getOfferDataModel().addChoosenData(this.data[i][1].toString()); }
						if(cmd_.equals("EDIT")) { }
					}
				}
				if(! ab.getModel().isSelected()) {
					if(title == "Kunden") { 
						if(cmd_.equals("ADD")) { databaseManager.getDataSource().getCustomerDataModel().removeChoosenData(this.data[i][1].toString()); }
						if(cmd_.equals("EDIT")) { }
					}
					if(title == "Angebote") { 
						if(cmd_.equals("ADD")) { databaseManager.getDataSource().getOfferDataModel().removeChoosenData(this.data[i][1].toString()); }
						if(cmd_.equals("EDIT")) { }
					}
				}
			}
		}
		if(cmd.equals("OK")) {
			chooseFrame.dispose();
		}
	}
}
