package at.epu.DataAccessLayer.DataObjects;

public class CustomerDataObject extends DataObject {
	String vorname;
	String nachname;
	String adresse;
	String unternehmen;
	String email;
	String telefon;
	int angebot_mapping_id;
	
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
	public int getAngebot_mapping_id() {
		return angebot_mapping_id;
	}
	public void setAngebot_mapping_id(int angebot_mapping_id) {
		this.angebot_mapping_id = angebot_mapping_id;
	}
}
