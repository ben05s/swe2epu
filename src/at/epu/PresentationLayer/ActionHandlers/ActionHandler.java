package at.epu.PresentationLayer.ActionHandlers;

import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.JButton;

import at.epu.PresentationLayer.ViewControllers.ViewController;

public abstract class ActionHandler implements ActionListener {
	protected ViewController owner = null;
	public Collection<JButton> getHandledButtons() {
		return null;
	};
}
