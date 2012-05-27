package at.epu.PresentationLayer.DataModels;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;
import at.epu.BusinessLayer.ApplicationManager;
import at.epu.DataAccessLayer.DataObjects.DataObject;
import at.epu.DataAccessLayer.DataObjects.DataObjectCollection;
import at.epu.DataAccessLayer.DataProviders.DataProvider.DataProviderException;

public class BackofficeTableModel extends DefaultTableModel implements FilterableDataModel {
	private static final long serialVersionUID = 2110831280426094363L;
	
	DataObjectCollection dataObjects      = null;
	DataObjectCollection presentedObjects = null;
	String tableName 					  = null;
	ArrayList<String> columnNames 		  = null;
	AddEditState state					  = null;
	
	public BackofficeTableModel(String tableName, ArrayList<String> columnNames) {
		this.tableName = tableName;
		this.columnNames = columnNames;
		this.state = AddEditStateFactory.createAddEditStateForTableName(tableName, this);
		
		try {
			presentedObjects = dataObjects = ApplicationManager.getInstance().getDatabaseManager().getAllObjectsForTableName(tableName);
		} catch (DataProviderException e) {
			System.err.println("Data provider failed to receive data for tablename: " + tableName + ".");
			e.printStackTrace();
		}
	}
	
	public DataObject getObjectAtRow(int rownum) {
		return presentedObjects.get(rownum);
	}
	
	public DataObjectCollection getDataObjectCollection() {
		return presentedObjects;
	}
	
	public void setDataObjectCollection(DataObjectCollection collection) {
		presentedObjects = dataObjects = collection;
	}
	
	public String getTableName() {
		return tableName;
	}
	
	public void updateTableData() {
		this.fireTableDataChanged();
	}

	@Override
	public int getColumnCount() {
		return getColumnNames().length;
	}
	
	@Override
	public int getRowCount() {
		if(presentedObjects != null) { return presentedObjects.size(); } 
		else { return 0; }
	}
	
	@Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(presentedObjects.get(rowIndex).isForeignKeyField(columnIndex)) {
			try {
				ArrayList<String> tmp = ApplicationManager.getInstance()
						 								  .getDatabaseManager()
						 				.resolveForeignKeyForTableNameAndObject(tableName, presentedObjects.get(rowIndex), columnIndex);
				
				StringBuilder builder = new StringBuilder();
				
				int i = 0;
				/** Reformat to single string. */
				for(String string : tmp) {
					if( i != 0 ) {
						builder.append(", ");
					}
					
					builder.append(string);
					i++;
				}
				
				return builder.toString();
			} catch (DataProviderException e) {
				e.printStackTrace();
			}
			
			return "";
		} else {
			return presentedObjects.toDataArray()[rowIndex][columnIndex];
		}
	}
	
	@Override
	public String getColumnName(int colnum) {
		return getColumnNames()[colnum];
	}

	public String[] getColumnNames() {
		String[] retVal = new String[columnNames.size()];
		
		for(int i = 0; i < columnNames.size(); i++) {
			retVal[i] = columnNames.get(i);
		}
		
		return retVal;
	}

	public void setColumnNames(ArrayList<String> columnNames) {
		this.columnNames = columnNames;
	}
	
	public void filterDataModel(String filterString) {
		if(filterString.isEmpty()) {
			presentedObjects = dataObjects;
		} else {
			presentedObjects = DataFilterProvider.filterDataModel(filterString, dataObjects);
		}
		
		updateTableData();
	}
	
	public AddEditState getAddEditState() {
		return state;
	}
}
