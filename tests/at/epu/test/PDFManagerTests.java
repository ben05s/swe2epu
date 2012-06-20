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
	public void testCreateAnnualPrognosisEmpty() {
		String path = new java.util.Date().toString();
		BackofficeTableModel model = ApplicationManager.getInstance().getModelForTableName("Angebote");
		
		/** Clear the model */
		model.getDataObjectCollection().removeAll(model.getDataObjectCollection());
		
		assertEquals(0, model.getDataObjectCollection().size());
		
		ApplicationManager.getInstance().getPdfManager().createAnnualPrognosis(model, path);
		
		File file = new File(path);
		
		assertEquals(true, file.exists());
		
		file.delete();
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
	public void testCreateBillReportPdfEmpty() {
		String path = new java.util.Date().toString();
		BackofficeTableModel model = ApplicationManager.getInstance().getModelForTableName("Ausgangsrechnungen");
		
	    /** Clear the model */
        model.getDataObjectCollection().removeAll(model.getDataObjectCollection());
		
		ApplicationManager.getInstance().getPdfManager().createBillReportPDF(model, path);
		
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
	public void testCreateInOutBillReportPdfEmpty() {
		String path = new java.util.Date().toString();
		BackofficeTableModel model = ApplicationManager.getInstance().getModelForTableName("Buchungszeilen");
		
	    /** Clear the model */
        model.getDataObjectCollection().removeAll(model.getDataObjectCollection());
		
		ApplicationManager.getInstance().getPdfManager().createInOutBillReportPDF(model, path);
		
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
