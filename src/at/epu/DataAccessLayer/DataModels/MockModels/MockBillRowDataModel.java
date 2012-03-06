package at.epu.DataAccessLayer.DataModels.MockModels;

import at.epu.DataAccessLayer.DataModels.BillRowDataModel;

/*
 * ID | AusgRechnung_ID | Angebot_ID | Status | Kommentar | Anzahl | Steuern | Betrag
 */
public class MockBillRowDataModel extends BillRowDataModel{

	private static final long serialVersionUID = -1087876451866035526L;
	private String[] columnNames;
	private Object[][] data;
	
	public MockBillRowDataModel() {
		
		String [] columnNames_ = {"ID",
				"AusgRechnung_ID",
				"Angebot_ID",
				"Status",
				"Kommentar",
				"Anzahl",
				"Steuern",
				"Betrag"};

		Object [][] data_ = {
				{new Integer(1), new Integer(1), new Integer(1), "offen", null,
					new Integer(5), new Double(100.00), new Double(1252.00)},
				{new Integer(2), new Integer(1), new Integer(1), "offen", null,
					new Integer(2), new Double(40.00), new Double(1003.00)},
				{new Integer(3), new Integer(2), new Integer(2), "bezahlt", null,
					new Integer(1), new Double(200.00), new Double(34000.00)},
				{new Integer(4), new Integer(3), new Integer(3), "offen", null,
					new Integer(10), new Double(100.00), new Double(100050.00)}
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
