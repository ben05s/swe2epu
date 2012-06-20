package at.epu.test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.BeforeClass;
import org.junit.Test;

import at.epu.BusinessLayer.ApplicationManager;
import at.epu.PresentationLayer.DataModels.BackofficeTableModel;

public class PDFManagerTests {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String[] args = {"config/appMock.properties"};
		
		ApplicationManager.getInstance().applicationStarted(args);
	}

	@Test
	public void testCreateAnnualPrognosis() {
		String path = new java.util.Date().toString();
		BackofficeTableModel model = ApplicationManager.getInstance().getModelForTableName("Angebote");
		
		ApplicationManager.getInstance().getPdfManager().createAnnualPrognosis(model, path);
		
		File file = new File(path);
		
		assertEquals(true, file.exists());
		
		file.delete();
	}
	
	@Test
	public void testCreateBillReportPdf() {
		String path = new java.util.Date().toString();
		BackofficeTableModel model = ApplicationManager.getInstance().getModelForTableName("Ausgangsrechnungen");
		
		ApplicationManager.getInstance().getPdfManager().createBillReportPDF(model, path);
		
		File file = new File(path);
		
		assertEquals(true, file.exists());
		
		file.delete();
	}

	@Test
	public void testCreateInOutBillReportPdf() {
		String path = new java.util.Date().toString();
		BackofficeTableModel model = ApplicationManager.getInstance().getModelForTableName("Buchungszeilen");
		
		ApplicationManager.getInstance().getPdfManager().createInOutBillReportPDF(model, path);
		
		File file = new File(path);
		
		assertEquals(true, file.exists());
		
		file.delete();
	}
}
