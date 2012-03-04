package at.epu.DataAccessLayer;

import at.epu.DataAccessLayer.DataModels.*;
import at.epu.DataAccessLayer.DataModels.MockModels.*;

public class DALFake implements DALInterface {
	@Override
	public ContactDataModel getContactDataModel() {
		return new MockContactDataModel();
	}

	@Override
	public CustomerDataModel getCustomerDataModel() {
		return new MockCustomerDataModel();
	}

	@Override
	public OfferDataModel getOfferDataModel() {
		return new MockOfferDataModel();
	}

	@Override
	public ProjectDataModel getProjectDataModel() {
		return new MockProjectDataModel();
	}

	@Override
	public BillDataModel getBillDataModel() {
		return new MockBillDataModel();
	}

	@Override
	public BankAccountDataModel getBankAccountDataModel() {
		return new MockBankAccountDataModel();
	}
}
