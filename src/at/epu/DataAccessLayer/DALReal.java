package at.epu.DataAccessLayer;

import at.epu.DataAccessLayer.DataModels.*;
import at.epu.DataAccessLayer.DataModels.SQLModels.*;

public class DALReal implements DALInterface {
	@Override
	public ContactDataModel getContactDataModel() {
		return new SQLContactDataModel();
	}

	@Override
	public CustomerDataModel getCustomerDataModel() {
		return new SQLCustomerDataModel();
	}

	@Override
	public OfferDataModel getOfferDataModel() {
		return new SQLOfferDataModel();
	}

	@Override
	public ProjectDataModel getProjectDataModel() {
		return new SQLProjectDataModel();
	}

	@Override
	public BankAccountDataModel getBankAccountDataModel() {
		return new SQLBankAccountDataModel();
	}

	@Override
	public OutBillDataModel getOutBillDataModel() {
		return new SQLOutBillDataModel();
	}

	@Override
	public BillRowDataModel getBillRowDataModel() {
		return new SQLBillRowDataModel();
	}

	@Override
	public BookRowDataModel getBookRowDataModel() {
		return new SQLBookRowDataModel();
	}

	@Override
	public CategoryDataModel getCategoryDataModel() {
		return new SQLCategoryDataModel();
	}

	@Override
	public InBillDataModel getInBillDataModel() {
		return new SQLInBillDataModel();
	}
}
