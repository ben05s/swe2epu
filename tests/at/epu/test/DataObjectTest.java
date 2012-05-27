package at.epu.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import at.epu.DataAccessLayer.DataObjects.ContactDataObject;

public class DataObjectTest {

	@Test
	public void testGetFieldNames() {
		ContactDataObject contact = new ContactDataObject();
		
		ArrayList<String> fieldNames = contact.getFieldNames();
		
		assertEquals("id", fieldNames.get(0));
		assertEquals("vorname", fieldNames.get(1));
		assertEquals("nachname", fieldNames.get(2));
		assertEquals("adresse", fieldNames.get(3));
		assertEquals("email", fieldNames.get(4));
		assertEquals("telefon", fieldNames.get(5));
	}

	@Test
	public void testGetFieldValues() {
		ContactDataObject contact = new ContactDataObject();
		
		contact.setId(0);
		contact.setVorname("Herbert");
		contact.setNachname("Foobar");
		contact.setEmail("herbi@foobi.at");
		contact.setAdresse("herbifoobigasse 7/123");
		contact.setTelefon("066666666");
		
		ArrayList<Object> fieldValues = contact.getFieldValues();
		
		assertEquals("0", fieldValues.get(0).toString());
		assertEquals("Herbert", fieldValues.get(1).toString());
		assertEquals("Foobar", fieldValues.get(2).toString());
		assertEquals("herbifoobigasse 7/123", fieldValues.get(3).toString());
		assertEquals("herbi@foobi.at", fieldValues.get(4).toString());
		assertEquals("066666666", fieldValues.get(5).toString());
	}
}
