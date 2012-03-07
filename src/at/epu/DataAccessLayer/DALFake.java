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
	public BankAccountDataModel getBankAccountDataModel() {
		return new MockBankAccountDataModel();
	}

	@Override
	public OutBillDataModel getOutBillDataModel() {
		return new MockOutBillDataModel();
	}

	@Override
	public BillRowDataModel getBillRowDataModel() {
		return new MockBillRowDataModel();
	}

	@Override
	public BookRowDataModel getBookRowDataModel() {
		return new MockBookRowDataModel();
	}

	@Override
	public CategoryDataModel getCategoryDataModel() {
		return new MockCategoryDataModel();
	}

	@Override
	public InBillDataModel getInBillDataModel() {
		return new MockInBillDataModel();
	}
}
