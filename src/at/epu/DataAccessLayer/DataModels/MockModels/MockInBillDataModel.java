package at.epu.DataAccessLayer.DataModels.MockModels;

import at.epu.DataAccessLayer.DataModels.InBillDataModel;
/*
 * ID(PK) | Buchungszeile(FK/PK) | Kontakt(FK) 
 */
public class MockInBillDataModel extends InBillDataModel{
	private static final long serialVersionUID = -1104930535218893118L;
	
	public MockInBillDataModel() {
		Object[][] data_ = {
			    {new Integer(1), new Integer(1), new Integer(1)},
			    {new Integer(1), new Integer(2), new Integer(1)}
			};
		
		data = data_;
	}
	
	public void filterDataModel(String filterString) {
		// TODO Auto-generated method stub

	}
}
