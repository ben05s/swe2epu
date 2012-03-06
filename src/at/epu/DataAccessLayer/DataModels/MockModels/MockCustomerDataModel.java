package at.epu.DataAccessLayer.DataModels.MockModels;

import at.epu.DataAccessLayer.DataModels.CustomerDataModel;
/*
 * ID | Vorname | Nachname | Unternehmen | Adresse | Email | Telefon | Angebote(FK/PK) 
 */
public class MockCustomerDataModel extends CustomerDataModel {
	private static final long serialVersionUID = 8742379763002040092L;
	private String[] columnNames;
	private Object[][] data;
	
	public MockCustomerDataModel() {

		String[] columnNames_ = {"ID",
				"Vorname",
                "Nachname",
                "Adresse",
                "Email",
                "Telefon",
                "Angebote"};
		
		Object[][] data_ = {
			    {new Integer(1), "Ben", "Hur", "Mobil Gmbh",
			     "Petergasse 345/7", "sdas.t@gmx.at", "06603412402", new Integer(1)},
			    {new Integer(1), "Ben", "Hur", "Mobil Gmbh",
				     "Petergasse 345/7", "sdas.t@gmx.at", "06603412402", new Integer(2)},
			    {new Integer(2), "Fritz", "DD", "Privat", 
			     "Zeilengasse 143/22", "f.DD@gmail.com", "06761252042", null},
			    {new Integer(3), "Heinz", "MrX", "Haus Gmbh",
			     "Franzgasse 9/12", "x.x@chello.at", "066465352325", new Integer(3)},
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
