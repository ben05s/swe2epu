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

	public GenericAddEditFormView(ArrayList<JButton> buttons, ArrayList<JLabel> labels, ArrayList<JTextField> texte, ArrayList<Integer> indexChoosable) {
		setBackground(SystemColor.control);
		JPanel springLayout = new JPanel(new SpringLayout());
		
		int numLabels = labels.size();
		int index = 0;
		int textcount = 0;
		int defaultButtons = 2;
		
		//Create and populate the panel.
		if(indexChoosable.isEmpty()) {
			for (int i = 0; i < numLabels; i++) {
			    springLayout.add(labels.get(i));
			    springLayout.add(texte.get(i));
			}
		} else {
			for (int i = 0; i < numLabels; i++) {
			    springLayout.add(labels.get(i));
			    if( i != indexChoosable.get(index) ) {
			    	springLayout.add(texte.get(textcount));
			    	textcount++;
			    } else {
			    	if(indexChoosable.size() > index + 1) { index++; }
			    	springLayout.add(buttons.get(defaultButtons));
			    	defaultButtons++;
			    }
			}
		}
		springLayout.add(buttons.get(0));
		springLayout.add(buttons.get(1));
		
		//Lay out the panel.
		SpringUtilities.makeCompactGrid(springLayout,
		                                numLabels+1, 2, //rows +1 because of button row, cols
		                                6, 6,        //initX, initY
		                                6, 6);       //xPad, yPad
		this.add(springLayout);
	}
}
