package at.epu.PresentationLayer;

import java.awt.SystemColor;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import java.awt.*;

public class GenericAddEditFormView extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3770598781016144457L;
	private JPanel panel = new JPanel();
	
	public GenericAddEditFormView(ArrayList<JButton> buttons, ArrayList<JLabel> labels, ArrayList<JTextField> texte) {
		setBackground(SystemColor.control);
		
		int numPairs = 2;

		//Create and populate the panel.
		JPanel panel = new JPanel(new SpringLayout());
		for (int i = 0; i < numPairs; i++) {
		    panel.add(labels.get(i));
		    panel.add(texte.get(i));
		}

		//Lay out the panel.
		SpringUtilities.makeCompactGrid(panel,
		                                numPairs, 2, //rows, cols
		                                6, 6,        //initX, initY
		                                6, 6);       //xPad, yPad
		/*
		int i = 0;
		for(JLabel label : labels) {
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.insets = new Insets(5, 10, 5, 10);
			gbc_label.gridx = 0;
			gbc_label.gridy = i++;
			gbc_label.anchor = GridBagConstraints.NORTH;
			gbc_label.fill = GridBagConstraints.HORIZONTAL;
			panel.add(label, gbc_label);
		}
		i = 0;
		for(JTextField text : texte) {
			GridBagConstraints gbc_text = new GridBagConstraints();
			gbc_text.insets = new Insets(5, 10, 5, 10);
			gbc_text.gridx = 1;
			gbc_text.gridy = i++;
			gbc_text.anchor = GridBagConstraints.NORTH;
			gbc_text.fill = GridBagConstraints.HORIZONTAL;
			panel.add(text, gbc_text);
		}
		
		for(JButton button : buttons) {
			GridBagConstraints gbc_button = new GridBagConstraints();
			gbc_button.insets = new Insets(5, 10, 5, 10);
			gbc_button.gridx = i++;
			gbc_button.gridy = 0;
			gbc_button.anchor = GridBagConstraints.NORTH;
			gbc_button.fill = GridBagConstraints.HORIZONTAL;
			panel.add(button, gbc_button);
		}*/
	}
}
