package at.epu.DataAccessLayer;

import at.epu.PresentationLayer.DataModels.MockModels.*;

public class MockDataSource extends DataSource {	
	public MockDataSource() {
		contactModel = new MockContactDataModel();
		customerModel = new MockCustomerDataModel();
		offerModel = new MockOfferDataModel();
		projectModel = new MockProjectDataModel();
		bankaccountModel = new MockBankAccountDataModel();
		outbillModel = new MockOutBillDataModel();
		billrowModel = new MockBillRowDataModel();
		categoryModel = new MockCategoryDataModel();
		inbillModel = new MockInBillDataModel();
	}
}
