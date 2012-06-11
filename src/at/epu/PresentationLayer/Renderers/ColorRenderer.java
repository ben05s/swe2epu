package at.epu.PresentationLayer.Renderers;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ColorRenderer extends JLabel implements TableCellRenderer {

	private static final long serialVersionUID = -1016998634304903402L;

	public ColorRenderer(String column) {
		super(column);
		setOpaque(true);
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		
        if (value != null) {
        	setText(value.toString());
        }
        
        if(isSelected)
        {
        	setBackground(table.getSelectionBackground());
        	setForeground(table.getSelectionForeground());
        }
        else
        {
        	setBackground(table.getBackground());
        	setForeground(table.getForeground());
        
        	if(row % 2 == 0) {
        		setBackground(new Color(242, 242, 255));
        	}
        }
        
		return this;
	}

}
