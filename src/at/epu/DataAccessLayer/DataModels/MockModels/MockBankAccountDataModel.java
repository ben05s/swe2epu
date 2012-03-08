package at.epu.DataAccessLayer.DataModels.MockModels;

import at.epu.DataAccessLayer.DataModels.BankAccountDataModel;
/*
 * ID | Kontonummer | Vorname | Nachname | Bank | BLZ
 */
public class MockBankAccountDataModel extends BankAccountDataModel {
	private static final long serialVersionUID = -1513528315959701530L;
	
	public MockBankAccountDataModel() {
		Object [][] data_ = {
				{new Integer(1), "12029521420", "Fritz", "Witz", "Volksbank",
					new Integer(1400)},
				{new Integer(2), "474433059332", "Otto", "Schmitt", "Erste Bank",
					new Integer(22000)},
				{new Integer(3), "464020340897", "Harald", "Heinz", "Bawag",
					new Integer(43000)},
		};

		data = data_;
	}
}
