package at.epu.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import at.epu.BusinessLayer.ApplicationManager;

public class DataBindingTests {
	ApplicationManager appManager = ApplicationManager.getInstance();
	
	@Test
	public void checkInputEmpty() {		
		Object[] data = {""};
		ArrayList<String> choosenData = new ArrayList<String>();
		choosenData.add("Dummy");
		assertEquals(false, appManager.getBindingManager().checkInput(data, choosenData));
		
		Object[] data2 = {"Fritz"};
		assertEquals(true, appManager.getBindingManager().checkInput(data2, choosenData));
	}
	
	@Test
	public void checkInputIllegal() {
		Object[] data = {"drop table kontakte;", "delete * from buchungszeilen;"};
		ArrayList<String> choosenData = new ArrayList<String>();
		choosenData.add("Dummy");
		assertEquals(false, appManager.getBindingManager().checkInput(data, choosenData));
	}
	
	@Test
	public void checkInputChoose() {
		Object[] data = {"Heinz", "Klaus"};
		ArrayList<String> choosenData = new ArrayList<String>();
		choosenData.add("Dummy");
		assertEquals(true, appManager.getBindingManager().checkInput(data, choosenData));
	}

}
