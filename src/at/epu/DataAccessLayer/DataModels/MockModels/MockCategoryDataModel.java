package at.epu.DataAccessLayer.DataModels.MockModels;

import at.epu.DataAccessLayer.DataModels.CategoryDataModel;
/*
 * ID | Name
 */
public class MockCategoryDataModel extends CategoryDataModel{
	private static final long serialVersionUID = -7645574178851868140L;
	
	public MockCategoryDataModel() {		
		Object[][] data_ = {
			    {new Integer(1), "Einnahme"},
			    {new Integer(2), "Ausgabe"},
			    {new Integer(3), "Steuer"}
			};
		
		data = data_;
	}
}
