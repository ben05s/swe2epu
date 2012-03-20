package at.epu.PresentationLayer.ViewControllers;

import java.awt.event.ActionListener;
import at.epu.PresentationLayer.GenericFilterDialogueView;
import at.epu.PresentationLayer.ActionHandlers.ActionHandler;

public class FilterViewController extends ViewController implements ActionListener {
	String prefilterText;
	ActionHandler owner;
	
	public FilterViewController(ActionHandler owner_, String prefilterText_) {
		owner = owner_;
		prefilterText = prefilterText_;
		
		initialize_after();
	}
	
	void initialize_after() {
		rootComponent = new GenericFilterDialogueView(owner, prefilterText);
		title = "";
		icon = null;
	}
}
