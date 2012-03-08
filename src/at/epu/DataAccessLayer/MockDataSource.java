package at.epu.DataAccessLayer;

import at.epu.DataAccessLayer.DataModels.MockModels.*;

public class MockDataSource extends DataSource {	
	public MockDataSource() {
		contactModel = new MockContactDataModel();
		customerModel = new MockCustomerDataModel();
		offerModel = new MockOfferDataModel();
		projectModel = new MockProjectDataModel();
		bankaccountModel = new MockBankAccountDataModel();
		outbillModel = new MockOutBillDataModel();
		billrowModel = new MockBillRowDataModel();
		bookrowModel = new MockBookRowDataModel();
		categoryModel = new MockCategoryDataModel();
		inbillModel = new MockInBillDataModel();
	}
}
