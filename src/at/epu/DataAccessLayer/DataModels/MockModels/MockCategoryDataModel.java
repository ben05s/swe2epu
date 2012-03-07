package at.epu.DataAccessLayer.DataModels.MockModels;

import at.epu.DataAccessLayer.DataModels.CategoryDataModel;
/*
 * ID | Name
 */
public class MockCategoryDataModel extends CategoryDataModel{

	private static final long serialVersionUID = -7645574178851868140L;
	private String[] columnNames;
	private Object[][] data;
	
	public MockCategoryDataModel() {
		
		String[] columnNames_ = {"ID",
				"Name"};
		
		Object[][] data_ = {
			    {new Integer(1), "Einnahme"},
			    {new Integer(2), "Ausgabe"},
			    {new Integer(3), "Steuer"}
			};
		
		columnNames = columnNames_;
		data = data_;
	}
	public void filterDataModel(String filterString) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getColumnName(int column)
	{
		return columnNames[column];
	}
	
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return data.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return data[rowIndex][columnIndex];
	}
}
