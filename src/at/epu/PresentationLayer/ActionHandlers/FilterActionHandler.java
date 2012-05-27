package at.epu.PresentationLayer.ActionHandlers;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JButton;
import at.epu.BusinessLayer.ApplicationManager;
import at.epu.PresentationLayer.ViewControllers.FilterViewController;
import at.epu.PresentationLayer.ViewControllers.ViewController;
import at.epu.PresentationLayer.Views.GenericFilterDialogueView;
import at.epu.PresentationLayer.Views.GenericSplitTableView;

public class FilterActionHandler extends ActionHandler {
	String prefilterText;
	FilterViewController viewController;
	
	public FilterActionHandler(ViewController owner_) {
		owner = owner_;
	}
	
	@Override
	public Collection<JButton> getHandledButtons() {
		ArrayList<JButton> buttonList = new ArrayList<JButton>();
		
		JButton filterButton = new JButton("Finden");
		filterButton.setActionCommand("FILTER_OPEN");
		filterButton.addActionListener(this);
		
		buttonList.add(filterButton);
		
		return buttonList;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if( e.getActionCommand().equals("FILTER_OPEN") ) {
			viewController = new FilterViewController(this, prefilterText);
			ApplicationManager.getInstance().getDialogManager().pushDialog(viewController);
		}
		
		if( e.getActionCommand().equals("FILTER")) {
			prefilterText = ((GenericFilterDialogueView)viewController.getRootComponent()).getTextFieldValue();
			
			((GenericSplitTableView)owner.getRootComponent()).getModel().filterDataModel(prefilterText);
			
			ApplicationManager.getInstance().getDialogManager().popDialog();
		}
	}
}
