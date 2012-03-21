package at.epu.PresentationLayer.ViewControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JRadioButton;

import at.epu.BusinessLayer.ApplicationManager;
import at.epu.BusinessLayer.DatabaseManager;
import at.epu.DataAccessLayer.DataModels.BackofficeTableModel;
import at.epu.PresentationLayer.GenericChooserFormView;

public class AddEditChooserViewController extends ViewController implements ActionListener {
	
	DatabaseManager databaseManager = ApplicationManager.getInstance().getDatabaseManager();
	BackofficeTableModel model = null;
	Object[][] data = null;
	String[] columnNames = null;
	JFrame chooseFrame;
	ArrayList<JCheckBox> checkList;
	ArrayList<JRadioButton> radioList;
	String command;			//determines what command called the controller(Edit or Add)
	int rowindex; 
	String title;
	String chooseCommand;
	ArrayList<String> preselectedItems = new ArrayList<String>();
	
	public void setNewFrame(JFrame newFrame) {
		this.chooseFrame = newFrame;
	}
	
	
	public AddEditChooserViewController(String chooseCommand, String cmd_, int rowindex_) {
		ApplicationManager appManager = ApplicationManager.getInstance();
		model = appManager.getActiveTableModel();
		this.title = model.getTableName();
		
		if(this.title == "Kunden"){
			data = databaseManager.getDataSource().getOfferDataModel().getData();
			preselectedItems = databaseManager.getDataSource().getCustomerDataModel().getChoosenData();
		}
		if(this.title == "Angebote"){
			data = databaseManager.getDataSource().getCustomerDataModel().getAddEditData();
			preselectedItems = databaseManager.getDataSource().getOfferDataModel().getChoosenData();
		}
		if(this.title == "Projekte"){
			if(chooseCommand == "CHOOSE1") {
				data = databaseManager.getDataSource().getOfferDataModel().getData();
				preselectedItems = databaseManager.getDataSource().getProjectDataModel().getChoosenData();
			}
			if(chooseCommand == "CHOOSE2") {
				data = databaseManager.getDataSource().getOutBillDataModel().getData();
				preselectedItems = databaseManager.getDataSource().getProjectDataModel().getChoosenData();	
			}
		}
		if(this.title == "Ausgangsrechnungen"){
			if(model.isDetailTableView()) {
				data = databaseManager.getDataSource().getOfferDataModel().getData();
				preselectedItems = databaseManager.getDataSource().getBillRowDataModel().getChoosenData();
			} else {
				data = databaseManager.getDataSource().getCustomerDataModel().getAddEditData();
				preselectedItems = databaseManager.getDataSource().getOutBillDataModel().getChoosenData();
			}
		}
		if(this.title == "Buchungszeilen"){
			if(chooseCommand == "CHOOSE1") {
				data = databaseManager.getDataSource().getInBillDataModel().getData();
				preselectedItems = databaseManager.getDataSource().getBankAccountDataModel().getChoosenData();
			}
			if(chooseCommand == "CHOOSE2") {
				data = databaseManager.getDataSource().getOutBillDataModel().getData();
				preselectedItems = databaseManager.getDataSource().getBankAccountDataModel().getChoosenData();	
			}
			if(chooseCommand == "CHOOSE3") {
				data = databaseManager.getDataSource().getCategoryDataModel().getData();
				preselectedItems = databaseManager.getDataSource().getBankAccountDataModel().getChoosenData();	
			}
		}
		
		this.chooseCommand = chooseCommand;
		this.command = cmd_;
		rowindex = rowindex_;
		
		initialize_chooser();
	}

	public void initialize_chooser() {
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
		if(command.equals("EDIT")) {
			for(int i=0;i<this.data.length;i++) {
				//checkList.add(new JCheckBox(this.data[i][1].toString(), false));
			}
		}
		if(command.equals("ADD")) {
			for(int i=0;i<this.data.length;i++) {
				JCheckBox box;
				if(model.getChoosenData().isEmpty()) {
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
		
		if(command.equals("EDIT")) {
			for(int i=0;i<data.length;i++) {
				//checkList.add(new JCheckBox(this.data[i][1].toString(), false));
			}
		}
		if(command.equals("ADD")) {
			for(int i=0;i<this.data.length;i++) {
				JRadioButton box;
				if(model.getChoosenData().isEmpty()) {
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
		
		rootComponent = new GenericChooserFormView(buttonList, checkList, radioList, chooseCommand);	
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		ApplicationManager appManager = ApplicationManager.getInstance();
		String cmd = event.getActionCommand();
		
		for(int i=0;i<this.data.length;i++) {
			if(cmd.equals("CHECKBOX"+i)) {
				AbstractButton ab = (AbstractButton) event.getSource();
				if(ab.getModel().isSelected()) {
					if(title == "Kunden") { 
						if(command.equals("ADD")) { databaseManager.getDataSource().getCustomerDataModel().addChoosenData(this.data[i][1].toString()); }
						if(command.equals("EDIT")) { }
					}
					if(title == "Angebote") { 
						if(command.equals("ADD")) { databaseManager.getDataSource().getOfferDataModel().addChoosenData(this.data[i][1].toString()); }
						if(command.equals("EDIT")) { }
					}
				}
				if(! ab.getModel().isSelected()) {
					if(title == "Kunden") { 
						if(command.equals("ADD")) { databaseManager.getDataSource().getCustomerDataModel().removeChoosenData(this.data[i][1].toString()); }
						if(command.equals("EDIT")) { }
					}
					if(title == "Angebote") { 
						if(command.equals("ADD")) { databaseManager.getDataSource().getOfferDataModel().removeChoosenData(this.data[i][1].toString()); }
						if(command.equals("EDIT")) { }
					}
				}
			}
		}
		if(cmd.equals("OK")) {
			appManager.getDialogManager().popDialog();
		}
	}
}
