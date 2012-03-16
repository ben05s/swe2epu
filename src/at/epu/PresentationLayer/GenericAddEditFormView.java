package at.epu.PresentationLayer;

import java.awt.SystemColor;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class GenericAddEditFormView extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3770598781016144457L;

	public GenericAddEditFormView(ArrayList<JButton> buttons, ArrayList<JLabel> labels, ArrayList<JTextField> texte) {
		setBackground(SystemColor.control);
		JPanel springLayout = new JPanel(new SpringLayout());
		
		int numPairs = labels.size();

		//Create and populate the panel.
		for (int i = 0; i < numPairs; i++) {
		    springLayout.add(labels.get(i));
		    springLayout.add(texte.get(i));
		}
		springLayout.add(buttons.get(0));
		springLayout.add(buttons.get(1));
		
		//Lay out the panel.
		SpringUtilities.makeCompactGrid(springLayout,
		                                numPairs+1, 2, //rows +1 because of button row, cols
		                                6, 6,        //initX, initY
		                                6, 6);       //xPad, yPad
		this.add(springLayout);
	}
}
