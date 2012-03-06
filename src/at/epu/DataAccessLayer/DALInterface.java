package at.epu.DataAccessLayer;

import at.epu.DataAccessLayer.DataModels.*;

public interface DALInterface {
	public ContactDataModel 	getContactDataModel();
	public CustomerDataModel	getCustomerDataModel();
	public OfferDataModel 		getOfferDataModel();
	public ProjectDataModel 	getProjectDataModel();
	public OutBillDataModel 		getBillDataModel();
	public BankAccountDataModel getBankAccountDataModel();
}