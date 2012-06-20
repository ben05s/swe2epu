package at.epu.test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import java.io.File;

import org.junit.Test;

import at.epu.BusinessLayer.ApplicationManager;
import at.epu.BusinessLayer.JSONManager;


public class JSONManagerTests {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String[] args = {"config/app.properties"};
		
		ApplicationManager.getInstance().applicationStarted(args);
	}

	@Test
	public void testWriteRandom() {
		String filepath = new java.util.Date().toString();
		ApplicationManager.getInstance().getJsonManager().writeRandomTimeTableToFile(filepath);
		
		File file = new File(filepath);
		
		assertEquals(true, file.exists());
		
		file.delete();
	}
	
	@Test
	public void testSerialize() {
		String filepath = new java.util.Date().toString();
		ApplicationManager.getInstance().getJsonManager().writeRandomTimeTableToFile(filepath);
		
		JSONManager.TimeTable timeTable = ApplicationManager.getInstance().getJsonManager().serializeTimeTable(filepath);
		
		assertEquals(true, timeTable != null);
		
		File file = new File(filepath);
		
		assertEquals(true, file.exists());
		
		file.delete();
	}
	
	@Test
	public void testGetTotal() {
		String filepath = new java.util.Date().toString();
		ApplicationManager.getInstance().getJsonManager().writeRandomTimeTableToFile(filepath);
		
		JSONManager.TimeTable timeTable = ApplicationManager.getInstance().getJsonManager().serializeTimeTable(filepath);
		
		float total = timeTable.getTotal();
		
		assertEquals(true, total >= 0);
		
		File file = new File(filepath);
		
		assertEquals(true, file.exists());
		
		file.delete();
	}
}
