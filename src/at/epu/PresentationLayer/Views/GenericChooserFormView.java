package at.epu.PresentationLayer.Views;

import java.awt.Dimension;
import java.awt.SystemColor;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SpringLayout;

import at.epu.BusinessLayer.ApplicationManager;
import at.epu.PresentationLayer.SpringUtilities;
import at.epu.PresentationLayer.DataModels.BackofficeTableModel;

public class GenericChooserFormView extends JPanel {

	private static final long serialVersionUID = -3094659823177438706L;
	
	public GenericChooserFormView(ArrayList<JButton> buttons, ArrayList<JCheckBox> checkList, 
									ArrayList<JRadioButton> radioList, String chooseCommand) {
		ApplicationManager appManager = ApplicationManager.getInstance();
		BackofficeTableModel model = null;
		model = appManager.getActiveTableModel();
		String title = model.getTableName();
		
		setBackground(SystemColor.control);
		JPanel springLayout = new JPanel(new SpringLayout());
		ArrayList<JLabel> labels = new ArrayList<JLabel>();
		
		for(int x = 0; x < checkList.size(); x++) {
			JLabel label = new JLabel();
			Dimension d = label.getPreferredSize();
			label.setPreferredSize(new Dimension(d.width+60,d.height));
			labels.add(label);
		}	
		int numPairs = checkList.size();
		
		//Create and populate the panel.
		if(title.equals("Kunden")) {
			for (int i = 0; i < numPairs; i++) {
				springLayout.add(checkList.get(i));
				springLayout.add(labels.get(i));
			}
		}
		if(title.equals("Angebote")) {
			for (int i = 0; i < numPairs; i++) {
				springLayout.add(radioList.get(i));
				springLayout.add(labels.get(i));
			}
		}
		if(title.equals("Projekte")) {
			if(chooseCommand.equals("CHOOSE1")) {
				for (int i = 0; i < numPairs; i++) {
					springLayout.add(radioList.get(i));
					springLayout.add(labels.get(i));
				}
			}
			if(chooseCommand.equals("CHOOSE2")) {
				for (int i = 0; i < numPairs; i++) {
					springLayout.add(checkList.get(i));
					springLayout.add(labels.get(i));
				}
			}	
		}
		if(title.equals("Ausgangsrechnungen")) {
			if(model.getAddEditState().isDetailTableView()) {
				for (int i = 0; i < numPairs; i++) {
					springLayout.add(radioList.get(i));
					springLayout.add(labels.get(i));
				}
			} else {
				for (int i = 0; i < numPairs; i++) {
					springLayout.add(radioList.get(i));
					springLayout.add(labels.get(i));
				}
			}
		}
		if(title.equals("Eingangsrechnungen")) {
			if(model.getAddEditState().isDetailTableView()) {
				for (int i = 0; i < numPairs; i++) {
					springLayout.add(radioList.get(i));
					springLayout.add(labels.get(i));
				}
			} else {
				for (int i = 0; i < numPairs; i++) {
					springLayout.add(radioList.get(i));
					springLayout.add(labels.get(i));
				}
			}
		}
		if(title.equals("Buchungszeilen")) {
			if(chooseCommand.equals("CHOOSE1")) {
				for (int i = 0; i < numPairs; i++) {
					springLayout.add(radioList.get(i));
					springLayout.add(labels.get(i));
				}
			}
			if(chooseCommand.equals("CHOOSE2")) {
				for (int i = 0; i < numPairs; i++) {
					springLayout.add(radioList.get(i));
					springLayout.add(labels.get(i));
				}
			}
			if(chooseCommand.equals("CHOOSE3")) {
				for (int i = 0; i < numPairs; i++) {
					springLayout.add(checkList.get(i));
					springLayout.add(labels.get(i));
				}
			}
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
