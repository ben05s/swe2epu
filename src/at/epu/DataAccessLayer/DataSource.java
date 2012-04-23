package at.epu.DataAccessLayer;

import java.sql.Connection;

import at.epu.PresentationLayer.DataModels.*;

public abstract class DataSource {
	ContactDataModel contactModel;
	CustomerDataModel customerModel;
	OfferDataModel offerModel;
	ProjectDataModel projectModel;
	BankAccountDataModel bankaccountModel;
	OutBillDataModel outbillModel;
	BillRowDataModel billrowModel;
	CategoryDataModel categoryModel;
	InBillDataModel inbillModel;
	Connection databaseHandle = null;
	
	public Connection getConnection() {
		if(databaseHandle == null) {
			throw new NullPointerException("There is no database initialized.");
		}
		
		return databaseHandle;
	}
	
	public ContactDataModel getContactDataModel() {
		return contactModel;
	}

	
	public CustomerDataModel getCustomerDataModel() {
		return customerModel;
	}

	
	public OfferDataModel getOfferDataModel() {
		return offerModel;
	}

	
	public ProjectDataModel getProjectDataModel() {
		return projectModel;
	}

	
	public BankAccountDataModel getBankAccountDataModel() {
		return bankaccountModel;
	}

	
	public OutBillDataModel getOutBillDataModel() {
		return outbillModel;
	}

	
	public BillRowDataModel getBillRowDataModel() {
		return billrowModel;
	}
	
	
	public CategoryDataModel getCategoryDataModel() {
		return categoryModel;
	}

	
	public InBillDataModel getInBillDataModel() {
		return inbillModel;
	}
}