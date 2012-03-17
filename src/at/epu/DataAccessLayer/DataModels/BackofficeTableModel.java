package at.epu.DataAccessLayer.DataModels;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

public abstract class BackofficeTableModel extends DefaultTableModel implements FilterableDataModel {
	private static final long serialVersionUID = 2110831280426094363L;

	protected String[] columnNames 	= null;
	protected Object[][] data 		= null;
	
	protected Statement stm;
	protected ResultSet rs;
	protected String sql;
	protected String sql_count;
	
	public void closeConnection(Connection databaseHandle) {
		if ( databaseHandle != null )
		{
			try {
				databaseHandle.close();
			} catch ( SQLException f ) {
				f.printStackTrace();
			}
		}
		System.exit(0);
	}
	
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
		int counter = 0;
		Object[][] newData = new Object[row-1][col];

		for(int i=0;i<row;i++) {
			for(int x=0;x<col;x++) {
				if(i != rowindex) {
					newData[counter][x] = this.data[i][x];
				}
			}
			if(i != rowindex) { counter++; }
		}
		setData(newData);
		fireTableDataChanged();
	}
	
	public void saveData(Object[] data_) {
		int col = this.columnNames.length;
		int row = this.data.length;
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
		fireTableDataChanged();
	}
	
	public void updateData(Object[] data_, int rowindex) {
		for(int i=0;i<this.columnNames.length;i++) {
			System.out.println(this.columnNames.length+" rowindex"+ rowindex);
			this.data[rowindex][i] = data_[i];
		}
		fireTableDataChanged();
	}
}
