package at.epu.DataAccessLayer.DataObjects;

import java.util.ArrayList;

public class CustomerDataObject extends DataObject {
	String vorname;
	String nachname;
	String adresse;
	String unternehmen;
	String email;
	String telefon;
	String angebote;
	
	public String getVorname() {
		return vorname;
	}
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	public String getNachname() {
		return nachname;
	}
	public void setNachname(String nachname) {
		this.nachname = nachname;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getUnternehmen() {
		return unternehmen;
	}
	public void setUnternehmen(String unternehmen) {
		this.unternehmen = unternehmen;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefon() {
		return telefon;
	}
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	public String getAngebote() {
		return angebote;
	}
	public void setAngebote(String angebote) {
		this.angebote = angebote;
	}
}
