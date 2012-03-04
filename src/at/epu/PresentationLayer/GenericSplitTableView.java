package at.epu.PresentationLayer;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;
import java.awt.SystemColor;

public class GenericSplitTableView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public GenericSplitTableView(List<JButton> buttons, TableModel tableModel) {
		setBackground(SystemColor.control);
		buttons.add(new JButton("Finden"));
		buttons.add(new JButton("Hinzufügen"));
		buttons.add(new JButton("Zeiten buchen"));
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.RED);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.anchor = GridBagConstraints.NORTHWEST;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		
		table = new JTable(tableModel);
		table.setBackground(Color.ORANGE);
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.anchor = GridBagConstraints.NORTHEAST;
		gbc_table.gridx = 1;
		gbc_table.gridy = 0;
		add(table, gbc_table);
		
		GridBagLayout btnGridBagLayout = new GridBagLayout();
		btnGridBagLayout.columnWidths = new int[]{0, 0};
		btnGridBagLayout.rowHeights = new int[]{0, 0, 0};
		btnGridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		btnGridBagLayout.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(btnGridBagLayout);
		
		int i = 0;
		for(JButton button : buttons) {
			GridBagConstraints gbc_button = new GridBagConstraints();
			gbc_button.insets = new Insets(5, 10, 5, 10);
			gbc_button.gridx = 0;
			gbc_button.gridy = i++;
			gbc_button.anchor = GridBagConstraints.NORTH;
			gbc_button.fill = GridBagConstraints.HORIZONTAL;
			panel.add(button, gbc_button);
		}
	}

}
