package at.epu.DataAccessLayer;

import at.epu.DataAccessLayer.DataModels.*;

public interface DALInterface {
	public ContactDataModel 	getContactDataModel();
	public CustomerDataModel	getCustomerDataModel();
	public OfferDataModel 		getOfferDataModel();
	public ProjectDataModel 	getProjectDataModel();
	public OutBillDataModel 	getOutBillDataModel();
	public BankAccountDataModel getBankAccountDataModel();
	public BillRowDataModel 	getBillRowDataModel();
	public BookRowDataModel		getBookRowDataModel();
	public CategoryDataModel	getCategoryDataModel();
	public InBillDataModel		getInBillDataModel();
}