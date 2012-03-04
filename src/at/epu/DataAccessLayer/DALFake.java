package at.epu.DataAccessLayer;

import java.util.ArrayList;
import java.util.Date;

import at.epu.DataAccessLayer.DataModels.BankAccountDataModel;
import at.epu.DataAccessLayer.DataModels.BillDataModel;
import at.epu.DataAccessLayer.DataModels.ContactDataModel;
import at.epu.DataAccessLayer.DataModels.CustomerDataModel;
import at.epu.DataAccessLayer.DataModels.OfferDataModel;
import at.epu.DataAccessLayer.DataModels.ProjectDataModel;
import at.epu.DataAccessLayer.DataObjects.*;

public class DALFake implements DALInterface {

	@Override
	public ContactDataModel getContactDataModel() {
		return new MockContactDataModel();
	}

	@Override
	public CustomerDataModel getCustomerDataModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OfferDataModel getOfferDataModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProjectDataModel getProjectDataModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BillDataModel getBillDataModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BankAccountDataModel getBankAccountDataModel() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
