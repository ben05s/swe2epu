package at.epu.DataAccessLayer.DataModels.MockModels;

import at.epu.DataAccessLayer.DataModels.OutBillDataModel;
/*
 * ID(PK) | Rechnungszeile_ID(FK/PK) | Kunde_ID(FK) | Buchungszeile_ID
 */
public class MockOutBillDataModel extends OutBillDataModel {
	private static final long serialVersionUID = -8018265297745567701L;
	
	public MockOutBillDataModel() {
		Object [][] data_ = {
				{new Integer(1), new Integer(1), new Integer(1), new Integer(1)},
				{new Integer(1), new Integer(2), new Integer(1), new Integer(1)},
				{new Integer(2), new Integer(3), new Integer(2), new Integer(2)},
				{new Integer(3), new Integer(4), new Integer(3), new Integer(3)}
		};
		
		data = data_;
	}
	
	public void filterDataModel(String filterString) {
		// TODO Auto-generated method stub

	}
}
