package at.epu.DataAccessLayer.DataModels.MockModels;

import at.epu.DataAccessLayer.DataModels.ProjectDataModel;

/*
 * ID(PK) | Angebot_ID(FK) | AusgRechnung_ID(FK/PK)
 */
public class MockProjectDataModel extends ProjectDataModel {
	private static final long serialVersionUID = -4733926290403533192L;
	
	public MockProjectDataModel() {
		Object [][] data_ = {
				{new Integer(1), new Integer(2), new Integer(1)},
				{new Integer(1), new Integer(2), new Integer(2)},
				{new Integer(2), new Integer(1), null},
				{new Integer(3), new Integer(3), new Integer(3)}
		};
		
		data = data_;
	}
}
