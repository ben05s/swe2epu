package at.epu.DataAccessLayer.DataModels.MockModels;

import at.epu.DataAccessLayer.DataModels.ContactDataModel;
/*
 * ID | Vorname | Nachname | Adresse | Email | Telefon 
 */
public class MockContactDataModel extends ContactDataModel {
	private static final long serialVersionUID = 5426244662861198543L;
	String[] columnNames;
	Object[][] data;
	
	public MockContactDataModel() {
		
		String[] columnNames_ = {"ID",
				"Vorname",
                "Nachname",
                "Adresse",
                "Email",
                "Telefon"};
		
		Object[][] data_ = {
			    {new Integer(1), "Kathy", "Smith",
			     "Landstr 33/7", "sd.sd@gmx.at", "066034212502"},
			    {new Integer(2), "John", "Doe",
			     "Hansgasse 43/2", "asd.asd@gmail.com", null},
			    {new Integer(3), "Sue", "Black",
			     "Hufoasdgasse 89/12", "asgdgs.s@chello.at", "06646342325"}
			};
		
		columnNames = columnNames_;
		data = data_;
	}

	public void filterDataModel(String filterString) {
		
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
