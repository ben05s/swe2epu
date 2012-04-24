package at.epu.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import at.epu.BusinessLayer.ApplicationManager;
import at.epu.PresentationLayer.DataModels.BackofficeTableModel;

public class MockDataTests {
	ApplicationManager appManager = ApplicationManager.getInstance();
	
	@Test
	public void saveDataTest() {
		
		BackofficeTableModel tableModel = appManager.getDatabaseManager().getDataSource().getContactDataModel();
		Object[] data = {"Heinz", "Meier", "Hausgasse", "email", "0664123123"};
		tableModel.saveData(tableModel, data);
		
		assertEquals("Heinz", tableModel.getData()[tableModel.getData().length][1]);
	}
}
