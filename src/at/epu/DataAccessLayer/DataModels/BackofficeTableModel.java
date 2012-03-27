package at.epu.DataAccessLayer.DataModels;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import at.epu.DataAccessLayer.MockDataProvider;
import at.epu.DataAccessLayer.SQLQueryProvider;

public abstract class BackofficeTableModel extends DefaultTableModel implements FilterableDataModel {
	private static final long serialVersionUID = 2110831280426094363L;

	protected SQLQueryProvider sqlProvider = null;							//used in the SQL Models to get data from Database
	protected MockDataProvider mockProvider = null;							//used in the Mock Models to save/update/delete Data properly
	
	protected String[] mappingTableName = null;								//stores the Names of the MappingTables in the database (if there is no mapping for this object, value "-" is given
	protected String[] foreignTableName = null;								//stores the Names of the Tables that provide data for foreign key relationships
	protected String[] foreignKeyColumns = null;							//stores the actual column names of foreign keys in the database of that specific table
	protected String[] foreignTableColumns = null;							//string that is equal to the string in the displayed table where the data from the foreign key should be displayed
	protected String[] foreignKeyFromMappingCol = null;						//holds the column in the mapping table which points to another table
	protected String[] desiredColFromForeignKey = null;						//stores the column of the foreign table which holds the desired data to be displayed
	
	protected String tableName = null;										//stores the name of the current tab/table (Kunden/Angebote/Rechnungen/...)
	protected String[] columnNames 	= null;									//holds all column names that are displayed in the table grid on the screen
	protected String[] addEditColNames = null;								//holds the columns that should be add/editable in the add/Edit form dialog
	protected Object[][] data 		= null;									//stores all data that is displayed in a table
	protected Object[][] presented_data = null;				//dont know ... but is somehow used
	protected Object[][] addEditData = null;								//stores the data which is provided after calling the add/edit dialog (ignore some specified columns(missingCols))
	
	protected ArrayList<String> choosenData = new ArrayList<String>();		//stores the data which has been choosen from the auswählen function in the add/edit dialog
	protected ArrayList<Integer> missingCols = new ArrayList<Integer>();	//holds the index of columns which are not editable or cannot be set for adding data(id for example)
	protected ArrayList<Integer> chooseIndex = new ArrayList<Integer>();	//stores the index in the add/Edit data array where a placeholder is
	
	protected boolean detailTableView = false;								//variable is true if the selected tab is "Rechnungen". In detail function popup with rechnungszeilen should be the active table model(is only set if this variable is 'true'
	
	public void updateTableData() {
		this.fireTableDataChanged();
	}
	
	public String[] getAddEditColNames() {
		return addEditColNames;
	}

	public boolean isDetailTableView() {
		return detailTableView;
	}
	
	public void setDetailTableView() {
		this.detailTableView = true;
	}
	
	public void unsetDetailTableView() {
		this.detailTableView = false;
	}
	

	public void setDetailTableView(boolean detailTableView) {
		this.detailTableView = detailTableView;
	}
	
	public void setAddEditColNames(String[] addEditColNames) {
		this.addEditColNames = addEditColNames;
	}
	
	@Override
	public String getColumnName(int column) {
		return getColumnNames()[column];
	}
	
	public String getTableName() {
		return tableName;
	}
	
	public String[] getMappingTableName() {
		return mappingTableName;
	}
	
	public String[] getForeignTableName() {
		return foreignTableName;
	}

	public String[] getForeignKeyColumns() {
		return foreignKeyColumns;
	}
	
	public String[] getForeignTableColumns() {
		return foreignTableColumns;
	}
	
	public String[] getForeignKeyNameFromMappingCol() {
		return foreignKeyFromMappingCol;
	}
	
	public String[] getDesiredColFromForeignKey() {
		return desiredColFromForeignKey;
	}
	
	public ArrayList<Integer> getMissingCols() {
		return missingCols;
	}

	public void setMissingCols(ArrayList<Integer> missingCols) {
		this.missingCols = missingCols;
	}

	@Override
	public int getColumnCount() {
		return getColumnNames().length;
	}
	
	@Override
	public int getRowCount() {
		if(presented_data != null) { return presented_data.length; } 
		else { return 0; }
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return presented_data[rowIndex][columnIndex];
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
		presented_data = data;
		this.data = data;
	}
	
	public void addStringArrayListToData(ArrayList<String> list, int row, int col) {
		this.data[row][col] = list;
	}
	
	public void addIntegerArrayListToData(ArrayList<Integer> list, int row, int col) {
		this.data[row][col] = list;
	}
	
	public ArrayList<String> getChoosenData() {
		return this.choosenData;
	}
	
	public void resetChoosenData() {
		this.choosenData = new ArrayList<String>();
	}
	
	public void addChoosenData(String data_) {
		this.choosenData.add(data_);
	}
	
	public void setChooseIndex(int index) {
		this.chooseIndex.add(index);
	}
	
	public ArrayList<Integer> getChooseIndex() {
		return this.chooseIndex;
	}
	
	public void deleteChooseIndex() {
		this.chooseIndex = new ArrayList<Integer>();
	}
	
	public void removeChoosenData(String data_) {
		for(int i=0;i<this.choosenData.size();i++) {
			if(this.choosenData.get(i).equals(data_)) {
				this.choosenData.remove(i);
			}
		}
	}
	
	public void setChoosenData(String data_) {
		this.choosenData = new ArrayList<String>();
		this.choosenData.add(data_);
	}
	
	public void deleteChoosenData() {
		this.choosenData = new ArrayList<String>();
	}
	
	public void filterDataModel(String filterString) {
		if(filterString.isEmpty()) {
			presented_data = data;
		} else {
			presented_data = DataFilterProvider.filterDataModel(filterString, data);
		}
		
		fireTableDataChanged();
	}
	
	/*
	 * Creates the array to be presented when editing or adding some data.
	 * must be different because some specified columns (missingCols) should not be in the add/Edit screen
	 */
	public Object[][] getAddEditData() {		
		int z=-1;
		Object[][] addEditData = new Object[this.data.length][this.addEditColNames.length];
		for(int i=0;i<this.data.length;i++) {
			for(int x=0;x<this.addEditColNames.length;x++) {
				z++;
				while(! this.addEditColNames[x].equals(this.columnNames[z])) {	
					if(i == 0) {
						missingCols.add(z);
					}
					z++;
				}
				addEditData[i][x] = this.data[i][z];
				
			}
			z=0;
		}
		return addEditData;
	}
	
	public void saveData(BackofficeTableModel model, Object[] data_) {
		try {
			throw new Exception();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateData(BackofficeTableModel model, Object[] data_, int rowindex) {
		try {
			throw new Exception();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteData(BackofficeTableModel model, int rowindex) {
		try {
			throw new Exception();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
