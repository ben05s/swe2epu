package at.epu.PresentationLayer.ViewControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import at.epu.BusinessLayer.ApplicationManager;
import at.epu.BusinessLayer.DatabaseManager;
import at.epu.PresentationLayer.GenericAddEditFormView;

public class AddEditViewController implements ActionListener{
	
	DatabaseManager databaseManager = ApplicationManager.getInstance().getDatabaseManager();
	Object[][] data = null;
	String[] columnNames = null;
	JPanel rootComponent = new JPanel();
	JFrame newFrame;
	JFrame chooseFrame;
	String cmd_;			//determines what command called the controller(Edit or Add)
	int rowindex; 
	ArrayList<JTextField> textList;
	JFrame parent;
	String title;
	ArrayList<Integer> indexChoosable = new ArrayList<Integer>();
	ArrayList<Integer> chooseIndex = new ArrayList<Integer>();
	
	public void setNewFrame(JFrame newFrame) {
		this.newFrame = newFrame;
	}

	public AddEditViewController(String title, String action, int rowindex_, JFrame parent_, ArrayList<Integer> indexChoosable_) {
		if(title == "Kontakte"){
			columnNames = databaseManager.getDataSource().getContactDataModel().getAddEditColNames();
			data = databaseManager.getDataSource().getContactDataModel().getAddEditData();
		}
		if(title == "Kunden"){
			columnNames = databaseManager.getDataSource().getCustomerDataModel().getAddEditColNames();
			data = databaseManager.getDataSource().getCustomerDataModel().getAddEditData();
		}
		if(title == "Angebote"){
			columnNames = databaseManager.getDataSource().getOfferDataModel().getAddEditColNames();
			data = databaseManager.getDataSource().getOfferDataModel().getAddEditData();
		}
		if(title == "Projekte"){
			columnNames = databaseManager.getDataSource().getProjectDataModel().getAddEditColNames();
			data = databaseManager.getDataSource().getProjectDataModel().getAddEditData();
		}
		if(title == "Rechnungen"){
			columnNames = databaseManager.getDataSource().getOutBillDataModel().getAddEditColNames();
			data = databaseManager.getDataSource().getOutBillDataModel().getAddEditData();
		}
		if(title == "Bankkonto"){
			columnNames = databaseManager.getDataSource().getBankAccountDataModel().getAddEditColNames();
			data = databaseManager.getDataSource().getBankAccountDataModel().getAddEditData();
		}
		if(title == "Rechnungszeilen"){
			columnNames = databaseManager.getDataSource().getBillRowDataModel().getAddEditColNames();
			data = databaseManager.getDataSource().getBillRowDataModel().getAddEditData();
		}
		
		cmd_ = action;
		rowindex = rowindex_;
		this.parent = parent_;
		this.title = title;
		this.indexChoosable = indexChoosable_;
		
		initialize();
	}

	void initialize() {
		ArrayList<JButton> buttonList = new ArrayList<JButton>();
		JButton btnSave = new JButton("Speichern");
		JButton btnCancel  = new JButton("Abbrechen");
		JButton btnChoose1 = new JButton("Auswählen");
		JButton btnChoose2 = new JButton("Auswählen");
		JButton btnChoose3 = new JButton("Auswählen");
		
		btnSave.setActionCommand("SAVE");
		btnSave.addActionListener(this);
		btnCancel.setActionCommand("CANCEL");
		btnCancel.addActionListener(this);
		//used for Kunden/Angebote/Projekte(Angebot)/Rechnungen/Rechnungszeilen(Angebot)/Bankkonto(Eingangsrechnung ID)
		btnChoose1.setActionCommand("CHOOSE1");
		btnChoose1.addActionListener(this);
		//used for Projekte(Ausgangsrechnung ID)/Bankkonto(Ausgangsrechnung ID)
		btnChoose2.setActionCommand("CHOOSE2");
		btnChoose2.addActionListener(this);
		//used for Bankkonto(Kategorie)
		btnChoose3.setActionCommand("CHOOSE3");
		btnChoose3.addActionListener(this);
		
		buttonList.add(btnSave);
		buttonList.add(btnCancel);
		buttonList.add(btnChoose1);
		buttonList.add(btnChoose2);
		buttonList.add(btnChoose3);

		
		ArrayList<JLabel> labelList = new ArrayList<JLabel>();
		for(int i=0;i<columnNames.length;i++) {
			labelList.add(new JLabel(columnNames[i], JLabel.TRAILING));
		}
		
		textList = new ArrayList<JTextField>();
		if(cmd_.equals("EDIT")) {
			for(int i=0;i<columnNames.length;i++) {
				if(data[rowindex][i] != null) {
					textList.add(new JTextField(data[rowindex][i].toString(),20));
				}
				else{
					textList.add(new JTextField("",20));
				}
			}
		}
		if(cmd_.equals("ADD")) {
			for(int i=0;i<columnNames.length;i++) {
				//ignore Angebote label because there is a button to choose angebote
				if(columnNames[i].equals("Angebote")) {
					databaseManager.getDataSource().getCustomerDataModel().setChooseIndex(i);
					chooseIndex = databaseManager.getDataSource().getCustomerDataModel().getChooseIndex();
					continue;
				}
				if(columnNames[i].equals("Kunde")) {
					databaseManager.getDataSource().getOfferDataModel().setChooseIndex(i);
					chooseIndex = databaseManager.getDataSource().getOfferDataModel().getChooseIndex();
					continue;
				}
				if(columnNames[i].equals("Angebot")) {
					continue;
				}
				if(columnNames[i].equals("Ausgangsrechnung ID")) {
					continue;
				}
				if(columnNames[i].equals("Eingangsrechnung ID")) {
					continue;
				}
				if(columnNames[i].equals("Kategorie")) {
					continue;
				}
				
				if(columnNames[i].equals("Status")) {
					textList.add(new JTextField("offen",20));
				} else {
					textList.add(new JTextField("",20));
				}
			}
		}	
		
		rootComponent = new GenericAddEditFormView(buttonList, labelList, textList, indexChoosable);				
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		String cmd = event.getActionCommand();
		
		if(cmd.equals("CHOOSE1")) {
			chooseFrame = new JFrame();
			chooseFrame.setTitle("Auswählen");
			AddEditChooserViewController controller = new AddEditChooserViewController(title, cmd_, rowindex, newFrame);
			chooseFrame.add(controller.getRootComponent());
			chooseFrame.pack();
			chooseFrame.setLocationRelativeTo(parent);
    		chooseFrame.setVisible(true);
    		controller.setNewFrame(chooseFrame);
		}
		
		if(cmd.equals("SAVE")) {
			Object[] data = new Object[this.columnNames.length];
			int skip = 0;
			for(int i=0;i<this.columnNames.length;i++) {
				for(int x=0;x<this.chooseIndex.size();x++) {
					if(i == this.chooseIndex.get(x)) {
						data[i] = "placeholder";
						skip++;
					}
				}
				if(data[i] == null) {
					data[i] = textList.get(i-skip).getText();
				}
			}
			if(title == "Kontakte") { 
				if(cmd_.equals("ADD")) { databaseManager.getDataSource().getContactDataModel().saveData(data,title); } 
				if(cmd_.equals("EDIT")) { databaseManager.getDataSource().getContactDataModel().updateData(data,rowindex,title);  } 
			}
			if(title == "Kunden") { 
				if(cmd_.equals("ADD")) {databaseManager.getDataSource().getCustomerDataModel().saveData(data,title); }
				if(cmd_.equals("EDIT")) { databaseManager.getDataSource().getCustomerDataModel().updateData(data,rowindex,title); }
			}
			if(title == "Angebote") { 
				if(cmd_.equals("ADD")) {databaseManager.getDataSource().getOfferDataModel().saveData(data,title); }
				if(cmd_.equals("EDIT")) { databaseManager.getDataSource().getOfferDataModel().updateData(data,rowindex,title); }
			}
			if(title == "Projekte") { 
				if(cmd_.equals("ADD")) {databaseManager.getDataSource().getProjectDataModel().saveData(data,title); }
				if(cmd_.equals("EDIT")) { databaseManager.getDataSource().getProjectDataModel().updateData(data,rowindex,title); }
			}
			if(title == "Rechnungen") { 
				if(cmd_.equals("ADD")) {databaseManager.getDataSource().getOutBillDataModel().saveData(data,title); }
				if(cmd_.equals("EDIT")) { databaseManager.getDataSource().getOutBillDataModel().updateData(data,rowindex,title); }
			}
			if(title == "Bankkonto") { 
				if(cmd_.equals("ADD")) {databaseManager.getDataSource().getBankAccountDataModel().saveData(data,title); }
				if(cmd_.equals("EDIT")) { databaseManager.getDataSource().getBankAccountDataModel().updateData(data,rowindex,title); }
			}
			if(title == "Rechnungszeilen") {
				if(cmd_.equals("ADD")) {databaseManager.getDataSource().getBillRowDataModel().saveData(data,title); }
				if(cmd_.equals("EDIT")) { databaseManager.getDataSource().getBillRowDataModel().updateData(data,rowindex,title); }
			}
			newFrame.dispose();
			
		}
		
		if(cmd.equals("CANCEL")) {
			newFrame.dispose();
		}
	}
	
	public JPanel getRootComponent() {
		return rootComponent;
	}
	
}
