package at.epu.DataAccessLayer.DataModels.MockModels;

import java.text.SimpleDateFormat;
import java.util.Date;

import at.epu.DataAccessLayer.DataModels.OfferDataModel;
/*
 *  ID | Kunde_ID | Summe | Dauer | Datum | Umsetzungs Chance
 */
public class MockOfferDataModel extends OfferDataModel {
	private static final long serialVersionUID = -3046727189620679978L;
	private String[] columnNames;
	private Object[][] data;
	
	public MockOfferDataModel() {
		
		String[] columnNames_ = {"ID",
				"Kunde_ID",
                "Summe",
                "Dauer",
                "Datum",
                "Chance"};
		
		Object[][] data_ = {
			    {new Integer(1), new Integer(1), new Double(20000.00), new Integer(365),
			     new SimpleDateFormat("dd.MM.yyyy").format(new Date()), new Double(0.55)},
			    {new Integer(2), new Integer(1), new Double(150000.00), new Integer(180),
			     new SimpleDateFormat("dd.MM.yyyy").format(new Date()), new Double(0.45)},
			    {new Integer(3), new Integer(3), new Double(50000.00), new Integer(85), 
			     new SimpleDateFormat("dd.MM.yyyy").format(new Date()), new Double(0.80)},
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
