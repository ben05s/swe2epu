package at.epu.PresentationLayer.ViewControllers;

import java.awt.event.ActionListener;

import at.epu.PresentationLayer.DataModels.BackofficeTableModel;
import at.epu.PresentationLayer.Views.GenericFilepathDialogueView;

public class FilepathViewController extends ViewController implements ActionListener {
	String prefilterText;
	ActionListener owner;
	BackofficeTableModel model = null;
	
	public FilepathViewController(ActionListener owner_, BackofficeTableModel model) {
		owner = owner_;
		this.model = model;
		initialize_after();
	}
	
	void initialize_after() {
		rootComponent = new GenericFilepathDialogueView(owner, model);
		title = "";
		icon = null;
	}

}
