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
	ArrayList<Integer> indexChoosable = new ArrayList<Integer>();
	JRadioButton clearAll = null;
	
	public void setNewFrame(JFrame newFrame) {
		this.chooseFrame = newFrame;
	}
	
	
	public AddEditChooserViewController(String chooseCommand, String cmd_, int rowindex_, ArrayList<Integer> indexChoosable_) {
		ApplicationManager appManager = ApplicationManager.getInstance();
		model = appManager.getActiveTableModel();
		this.title = model.getTableName();
		this.indexChoosable = indexChoosable_;
		
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
			int count = 0;
			for(int i=0;i<this.data.length;i++) {
				JCheckBox box;
				if(model.getChoosenData().isEmpty()) {
					checked[i] = false;
				} else {
					checked[i] = false;
					for(int z=count;z<model.getChoosenData().size();z++) {
						if(model.getChoosenData().get(z).equals(this.data[i][1].toString())) {
							checked[i] = true;
							count++;
							break;
						}
					}
				}
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
			int count = 0;
			for(int i=0;i<this.data.length;i++) {
				JRadioButton box;
				if(model.getChoosenData().isEmpty()) {
					checked[i] = false;
				} else {
					checked[i] = false;
					for(int z=count;z<model.getChoosenData().size();z++) {
						if(model.getChoosenData().get(z).equals(this.data[i][1].toString())) {
							checked[i] = true;
							count++;
							break;
						}
					}
				}
				box = new JRadioButton(this.data[i][1].toString(), checked[i]);
				group.add(box);
				box.setActionCommand("RADIOBUTTON"+i);
				box.addActionListener(this);
				radioList.add(box);
			}
		}	
		
		clearAll = new JRadioButton("none selected", false);
		group.add(clearAll);
		
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
					if(command.equals("ADD")) { model.addChoosenData(this.data[i][1].toString()); }
					if(command.equals("EDIT")) { }	
				}
				if(! ab.getModel().isSelected()) {
					if(command.equals("ADD")) { model.removeChoosenData(this.data[i][1].toString()); }
					if(command.equals("EDIT")) { }
				}
			}
		}
		
		for(int i=0;i<this.data.length;i++) {
			if(cmd.equals("RADIOBUTTON"+i)) {
				AbstractButton ab = (AbstractButton) event.getSource();
				if(ab.getModel().isSelected()) {
					if(command.equals("ADD")) { model.setChoosenData(this.data[i][1].toString()); }
					if(command.equals("EDIT")) { }	
				}
			}
		}
		
		if(cmd.equals("DESELECT")) {
			model.deleteChoosenData();
			clearAll.setSelected(true);
			for(int i=0;i<this.data.length;i++) {
				checkList.get(i).setSelected(false);
			}
		}
		
		if(cmd.equals("OK")) {
			System.out.println(databaseManager.getDataSource().getCustomerDataModel().getChoosenData().size());
			appManager.getDialogManager().popDialog();
		}
	}
}
