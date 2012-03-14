package at.epu.DataAccessLayer.DataModels;

import javax.swing.table.AbstractTableModel;

public abstract class BackofficeTableModel extends AbstractTableModel implements FilterableDataModel {
	private static final long serialVersionUID = 2110831280426094363L;

	protected String[] columnNames 	= null;
	protected Object[][] data 		= null;
	
	@Override
	public String getColumnName(int column) {
		return getColumnNames()[column];
	}
	
	@Override
	public int getColumnCount() {
		return getColumnNames().length;
	}
	
	@Override
	public int getRowCount() {
		if(data != null)
		{
			return data.length;
		}
		else
		{
			return 0;
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return data[rowIndex][columnIndex];
	}
	
	public void filterDataModel(String filterString) {
		data = DataFilterProvider.filterDataModel(filterString, data);
		fireTableDataChanged();
	}

	public String[] getColumnNames() {
		return columnNames;
	}

	public void setColumnNames(String[] columnNames) {
		this.columnNames = columnNames;
	}

	public Object[][] getData() {
		return data;
	}

	public void setData(Object[][] data) {
		this.data = data;
	}
	
	public void saveData(Object[] data) {
		/*for(int i=0;i<2;i++) {
			this.data[0][i] = data[i];
		}*/
	}
}
