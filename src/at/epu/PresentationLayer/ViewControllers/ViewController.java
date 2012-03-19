package at.epu.PresentationLayer.ViewControllers;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.JPanel;

public class ViewController {
	protected Component rootComponent;
	protected String title;
	protected Icon icon;
	protected ArrayList<Integer> indexChoosable = new ArrayList<Integer>();
	
	public ViewController() {
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
}
