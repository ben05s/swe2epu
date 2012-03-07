package at.epu.DataAccessLayer.DataModels.MockModels;

import java.text.SimpleDateFormat;
import java.util.Date;

import at.epu.DataAccessLayer.DataModels.BookRowDataModel;
/*
 * Index(PK) | ID(PK) | EingRechnung(FK) | AusgRechnung(FK) 
 * | Betrag | Umsatzsteuer | Buchungsdatum | Kategorie(FK)
 */
public class MockBookRowDataModel extends BookRowDataModel {

	private static final long serialVersionUID = 1845438830429021423L;
	private String[] columnNames;
	private Object[][] data;
	
	public MockBookRowDataModel() {
		
		String [] columnNames_ = {"Index",
				"ID",
				"EingRechnung",
				"AusgRechnung",
				"Betrag",
				"Umsatzsteuer",
				"Buchungsdatum",
				"Kategorie"};

		Object [][] data_ = {
				{new Integer(1), new Integer(1), new Integer(1), null, new Double(100000.00),
				 new Double(0.20), new SimpleDateFormat("dd.MM.yyyy").format(new Date()),
				 new Integer(1)},
				{new Integer(2), new Integer(2), new Integer(1), null, new Double(1500.00),
				 new Double(0.50), new SimpleDateFormat("dd.MM.yyyy").format(new Date()),
				 new Integer(3)
				}
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
