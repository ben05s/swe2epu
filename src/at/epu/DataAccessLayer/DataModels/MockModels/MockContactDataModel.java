package at.epu.DataAccessLayer.DataModels.MockModels;

import at.epu.DataAccessLayer.DataModels.ContactDataModel;

public class MockContactDataModel extends ContactDataModel {
	private static final long serialVersionUID = 5426244662861198543L;

	public void filterDataModel(String filterString) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public String getColumnName(int column)
	{
		return "ASDF";
	}
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

}
