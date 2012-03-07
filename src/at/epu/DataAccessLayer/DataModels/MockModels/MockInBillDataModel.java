package at.epu.DataAccessLayer.DataModels.MockModels;

import at.epu.DataAccessLayer.DataModels.InBillDataModel;
/*
 * ID(PK) | Buchungszeile(FK/PK) | Kontakt(FK) 
 */
public class MockInBillDataModel extends InBillDataModel{

	private static final long serialVersionUID = -1104930535218893118L;
	private String[] columnNames;
	private Object[][] data;
	
	public MockInBillDataModel() {
		
		String[] columnNames_ = {"ID",
				"Buchungszeile",
				"Kontakt"};
		
		Object[][] data_ = {
			    {new Integer(1), new Integer(1), new Integer(1)},
			    {new Integer(1), new Integer(2), new Integer(1)}
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
