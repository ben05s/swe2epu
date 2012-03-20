package at.epu.PresentationLayer;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import at.epu.BusinessLayer.ApplicationManager;

public class GenericFilterDialogueView extends JPanel implements ActionListener {
	private static final long serialVersionUID = 309131349168669114L;
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public GenericFilterDialogueView(ActionListener parent, String prefilterText) {
		setBounds(100, 100, 300, 80);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{Double.MIN_VALUE, 1.0};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0};
		setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("Suchtext:");
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(15, 10, 5, 5);
		gbc_panel.anchor = GridBagConstraints.NORTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(lblNewLabel, gbc_panel);
		
		if(prefilterText != null) {
			textField = new JTextField(prefilterText);
		} else {
			textField = new JTextField();
		}
		
		GridBagConstraints gbc_text = new GridBagConstraints();
		gbc_text.insets = new Insets(10, 10, 5, 5);
		gbc_text.fill = GridBagConstraints.HORIZONTAL;
		gbc_text.anchor = GridBagConstraints.NORTHEAST;
		gbc_text.gridx = 1;
		gbc_text.gridy = 0;
		add(textField, gbc_text);
		textField.setColumns(10);
		
		JButton button = new JButton("Suchen");
		button.setActionCommand("FILTER");
		GridBagConstraints gbc_btn = new GridBagConstraints();
		gbc_btn.insets = new Insets(10, 10, 5, 5);
		gbc_btn.fill = GridBagConstraints.HORIZONTAL;
		gbc_btn.anchor = GridBagConstraints.SOUTHEAST;
		gbc_btn.gridx = 1;
		gbc_btn.gridy = 1;
		add(button, gbc_btn);
		
		JButton button2 = new JButton("Abbrechen");
		button2.setActionCommand("CANCEL");
		GridBagConstraints gbc_btn2 = new GridBagConstraints();
		gbc_btn2.insets = new Insets(10, 10, 5, 5);
		gbc_btn2.fill = GridBagConstraints.HORIZONTAL;
		gbc_btn2.anchor = GridBagConstraints.SOUTHWEST;
		gbc_btn2.gridx = 0;
		gbc_btn2.gridy = 1;
		add(button2, gbc_btn2);
		
		button.addActionListener(parent);
		button2.addActionListener(this);
	}
	
	public String getTextFieldValue() {
		return textField.getText();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getActionCommand().equals("CANCEL")) {
			ApplicationManager.getInstance().getDialogManager().popDialog();
		}
	}
}
