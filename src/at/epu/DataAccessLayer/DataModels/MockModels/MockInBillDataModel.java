package at.epu.DataAccessLayer.DataModels.MockModels;

import at.epu.DataAccessLayer.DataModels.InBillDataModel;
/*
 * ID(PK) | Kunde | Rechnungszeilen(Anz) | Buchungszeilen(Anz) | Status
 */
public class MockInBillDataModel extends InBillDataModel{
	private static final long serialVersionUID = -1104930535218893118L;
	
	public MockInBillDataModel() {
		Object [][] data_ = {
				{new Integer(1), "Smith", new Integer(0), new Integer(0), "offen"},
				{new Integer(2), "Black", new Integer(0), new Integer(0), "offen"}
		};
		
		data = data_;
	}
}
