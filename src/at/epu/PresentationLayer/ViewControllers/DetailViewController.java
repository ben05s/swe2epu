package at.epu.PresentationLayer.ViewControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import at.epu.BusinessLayer.ApplicationManager;
import at.epu.PresentationLayer.DataModels.BackofficeTableModel;
import at.epu.PresentationLayer.Views.GenericDetailTableView;

public class DetailViewController extends ViewController implements ActionListener{

	ApplicationManager appManager = ApplicationManager.getInstance();
	int rowindex; 	
	
	public DetailViewController(int rowindex_) {
		rowindex = rowindex_;
		
		initialize_after();
	}
	
	void initialize_after() {
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
		
		BackofficeTableModel model = appManager.getModelForTableName("Rechnungszeilen");
		
		model.getAddEditState().getIndexChoosable().add(0);
		
		rootComponent = new GenericDetailTableView(buttonList, menuList, appManager.getModelForTableName("Rechnungszeilen"));	
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		String cmd = event.getActionCommand();
		
		if(cmd.equals("SAVE")) {
			ApplicationManager.getInstance().getModelForTableName("Ausgangsrechnungen").getAddEditState().unsetDetailTableView();
			ApplicationManager.getInstance().getDialogManager().popDialog();
		}
		
		if(cmd.equals("CANCEL")) {
			ApplicationManager.getInstance().getModelForTableName("Ausgangsrechnungen").getAddEditState().unsetDetailTableView();
			ApplicationManager.getInstance().getDialogManager().popDialog();
		}		
		
		if(cmd.equals("ADD")) {
			AddEditViewController controller = new AddEditViewController(cmd, rowindex, appManager.getModelForTableName("Projekte").getAddEditState().getIndexChoosable());
			
			ApplicationManager.getInstance().getDialogManager().pushDialog(controller);
		}
	}
}
