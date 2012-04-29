package at.epu.BusinessLayer;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.*;
import at.epu.DataAccessLayer.DataObjects.InBillDataObject;
import at.epu.DataAccessLayer.DataObjects.OutBillDataObject;

public class PDFManager {
	public void createOutBillPDF(OutBillDataObject outbill, String filepath) {
		PDDocument doc = newDocument();
		PDPage page = newPage();
		
		doc.addPage(page);
		
		saveDocument(doc, filepath);
	}
	
	public void createOutBillReportPDF(ArrayList<OutBillDataObject> outbillObjects, String filepath) {
		PDDocument doc = newDocument();
		PDPage page = newPage();
		
		doc.addPage(page);
		
		saveDocument(doc, filepath);
	}
	
	public void createInOutBillReportPDF(ArrayList<InBillDataObject> inbillModel, ArrayList<OutBillDataObject> outbillModel, String filepath) {
		PDDocument doc = newDocument();
		PDPage page = newPage();
		
		doc.addPage(page);
		
		saveDocument(doc, filepath);
	}
	
	private PDDocument newDocument() {
		PDDocument doc = null;
		
		try {
			doc = new PDDocument();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return doc;
	}
	
	private PDPage newPage() {
		PDPage page = null;
		
		page = new PDPage();
		
		return page;
	}
	
	private void saveDocument(PDDocument document, String filepath) {
		try {
			document.save(filepath);
		} catch (COSVisitorException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
