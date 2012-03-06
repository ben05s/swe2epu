package at.epu.DataAccessLayer.DataModels.MockModels;

import at.epu.DataAccessLayer.DataModels.OutBillDataModel;
/*
 * ID(PK) | Rechnungszeile_ID(FK/PK) | Kunde_ID(FK) | Buchungszeile_ID
 */
public class MockOutBillDataModel extends OutBillDataModel {
	private static final long serialVersionUID = -8018265297745567701L;
	private String[] columnNames;
	private Object[][] data;
	
	public MockOutBillDataModel() {
		
		String [] columnNames_ = {"ID",
				"Rechnungszeile_ID",
				"Kunde_ID",
				"Buchungszeile_ID"};

		Object [][] data_ = {
				{new Integer(1), new Integer(1), new Integer(1), new Integer(1)},
				{new Integer(1), new Integer(2), new Integer(1), new Integer(1)},
				{new Integer(2), new Integer(3), new Integer(2), new Integer(2)},
				{new Integer(3), new Integer(4), new Integer(3), new Integer(3)}
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
