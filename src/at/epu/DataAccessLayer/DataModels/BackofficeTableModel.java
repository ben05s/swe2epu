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
	
	public void deleteData(int rowindex) {
		int row = this.data.length;
		int col = this.columnNames.length;
		Object[][] newData = new Object[row][col];
		for(int i=0;i<row;i++) {
			for(int x=0;x<col;x++) {
				if(i != rowindex) {
					newData[i][x] = this.data[i][x]; 
				}
			}
		}
		setData(newData);
		fireTableDataChanged();
	}
	
	public void saveData(Object[] data_) {
		int col = this.columnNames.length;
		int row = this.data.length;
		
		for(int z=0;z<row;z++) {
			if(this.data[z][0].toString().equals(data_[0].toString())) {
				for(int e=0;e<col;e++) {
					this.data[z][e] = data_[e];
				}
				break;
			}
			else { 
				if(z == row-1) {
					row++;
					Object[][] newData = new Object[row][col];
					for(int i=0;i<row;i++) {
						for(int x=0;x<col;x++) {
							if(i < row-1) {
								newData[i][x] = this.data[i][x]; 
							}
							if(i == row-1) {
								newData[i][x] = data_[x];
							}
						}
					}
					setData(newData);
					break;
				}
			}
		}
		fireTableDataChanged();
	}
}
