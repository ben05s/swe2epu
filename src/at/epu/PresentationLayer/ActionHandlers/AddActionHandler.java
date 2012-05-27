package at.epu.PresentationLayer.ActionHandlers;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JButton;

import at.epu.BusinessLayer.ApplicationManager;
import at.epu.PresentationLayer.DataModels.BackofficeTableModel;
import at.epu.PresentationLayer.ViewControllers.AddEditViewController;
import at.epu.PresentationLayer.ViewControllers.ViewController;

public class AddActionHandler extends ActionHandler {
	String addTitle = "Hinzufügen";
	BackofficeTableModel model = null;
	
	public AddActionHandler(ViewController owner_, BackofficeTableModel model) {
		owner = owner_;
		this.model = model;
	}
	
	@Override
	public Collection<JButton> getHandledButtons() {
		ArrayList<JButton> buttonList = new ArrayList<JButton>();
		
		JButton addButton = new JButton(addTitle);
		addButton.setActionCommand("ADD");
		addButton.addActionListener(this);
		
		buttonList.add(addButton);
		
		return buttonList;
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		if( ev.getActionCommand().equals("ADD") ) {
			AddEditViewController viewController = new AddEditViewController(ev.getActionCommand(), 0, model.getAddEditState().getIndexChoosable());
			
			ApplicationManager.getInstance().getDialogManager().pushDialog(viewController);
		}	
	}
}
