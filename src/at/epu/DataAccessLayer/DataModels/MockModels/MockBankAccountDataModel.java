package at.epu.DataAccessLayer.DataModels.MockModels;

import at.epu.DataAccessLayer.DataModels.BankAccountDataModel;
/*
 * ID | Kontonummer | Vorname | Nachname | Bank | BLZ
 */
public class MockBankAccountDataModel extends BankAccountDataModel {
	private static final long serialVersionUID = -1513528315959701530L;
	private String[] columnNames;
	private Object[][] data;
	
	public MockBankAccountDataModel() {
		
		String [] columnNames_ = {"ID",
				"Kontonummer",
				"Vorname",
				"Nachname",
				"Bank",
				"BLZ"};

		Object [][] data_ = {
				{new Integer(1), "12029521420", "Fritz", "Witz", "Volksbank",
					new Integer(1400)},
				{new Integer(2), "474433059332", "Otto", "Schmitt", "Erste Bank",
					new Integer(22000)},
				{new Integer(3), "464020340897", "Harald", "Heinz", "Bawag",
					new Integer(43000)},
		};
		columnNames = columnNames_;
		data = data_;
	}
	
	public void filterDataModel(String filterString) {
		;
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
