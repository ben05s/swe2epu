package at.epu.DataAccessLayer.DataObjects;

public class DataObject {
	enum DataObjectState {
		DataObjectStateNew,
		DataObjectStateDeleted,
		DataObjectStateModified,
		DataObjectStateSynchronized;
	}
	
	DataObjectState state;
	
	void setState(DataObjectState newState) {
		state = newState;
	}
	
	DataObjectState getState() {
		return state;
	}
}
