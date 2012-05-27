package at.epu.PresentationLayer.ViewControllers;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import at.epu.PresentationLayer.ActionHandlers.ActionHandler;

public class ViewController implements ActionListener {
	protected Component rootComponent;													//holds all Swing compontents to be presented on the screen
	protected String title;
	protected Icon icon;
	private ArrayList<ActionHandler> actionHandlers = new ArrayList<ActionHandler>();
	
	public ViewController() {
		initialize();
	}
	
	public ViewController(JFrame parent_) {
		initialize();
	}
	
	/**
	 * Initialize the view and its properties here.
	 */
	void initialize() {
		rootComponent = new JPanel();
		title = "";
		icon = null;
	}
	
	public Component getRootComponent() {
		return rootComponent;
	}
	
	public String getTitle() {
		return title;
	}
	
	public Icon getIcon() {
		return icon;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for(ActionHandler handler : actionHandlers) {
			handler.actionPerformed(e);
		}
	}
	
	public void registerActionHandler(ActionHandler ah) {
		actionHandlers.add(ah);
	}
	
	public ArrayList<JButton> getButtonsFromHandlers() {
		ArrayList<JButton> buttonList = new ArrayList<JButton>();
		
		for(ActionHandler handler : actionHandlers) {
			buttonList.addAll(handler.getHandledButtons());
		}
		
		return buttonList;
	}
}
