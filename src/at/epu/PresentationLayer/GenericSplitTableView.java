package at.epu.PresentationLayer;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

public class GenericSplitTableView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JFrame newFrame;
	
	/**
	 * Create the panel.
	 */
	public GenericSplitTableView(List<JButton> buttons, List<JLabel> labels, final List<JMenuItem> menuList, TableModel tableModel) {
		setBackground(SystemColor.control);
		
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
		
		GridBagLayout btnGridBagLayout = new GridBagLayout();
		btnGridBagLayout.columnWidths = new int[]{0, 0};
		btnGridBagLayout.rowHeights = new int[]{0, 0, 0};
		btnGridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		btnGridBagLayout.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(btnGridBagLayout);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.HORIZONTAL;
		gbc_scrollPane.anchor = GridBagConstraints.NORTHEAST;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 0;
		add(scrollPane, gbc_scrollPane);
		scrollPane.setBackground(Color.GRAY);
		scrollPane.setViewportView(table);
		
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
		
		for(JLabel label : labels) {
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.insets = new Insets(5, 10, 5, 10);
			gbc_label.gridx = 0;
			gbc_label.gridy = i++;
			gbc_label.anchor = GridBagConstraints.NORTH;
			gbc_label.fill = GridBagConstraints.HORIZONTAL;
			panel.add(label, gbc_label);
		}
		
		table.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseReleased(MouseEvent e) {
	            int r = table.rowAtPoint(e.getPoint());
	            if (r >= 0 && r < table.getRowCount()) {
	                table.setRowSelectionInterval(r, r);
	            } else {
	                table.clearSelection();
	            }

	            int rowindex = table.getSelectedRow();
	            if (rowindex < 0)
	                return;
	            if (e.isPopupTrigger() && e.getComponent() instanceof JTable ) {
	                JPopupMenu popup = new JPopupMenu();
	                for(JMenuItem menu : menuList) {
	                	popup.add(menu);
	                	menu.addActionListener(new ActionListener() {
	                		public void actionPerformed(ActionEvent e) {
	                			newFrame = new JFrame();
		                		newFrame.setTitle("Umbenennen");
		                		newFrame.setBounds(300, 150, 300, 500);
		                		newFrame.setVisible(true);
	                		}
	                	});
	                }
	                popup.show(e.getComponent(), e.getX(), e.getY());
	            }
	        } 
	 });
	}
}