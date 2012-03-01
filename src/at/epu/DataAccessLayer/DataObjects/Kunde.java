package at.epu.DataAccessLayer.DataObjects;

import java.util.ArrayList;

public class Kunde {
	int id_;
	String vorname_;
	String nachname_;
	String unternehmen_;
	String email_;
	String telefon_;
	ArrayList<Angebot> angebot_liste_ = new ArrayList<Angebot>();
	
	public int getID() {
		return id_;
	}
	
	public void setID(int id) {
		id_ = id;
	}
	
	public String getVorname() {
		return vorname_;
	}
	
	public void setVorname(String vorname) {
		vorname_ = vorname;
	}
	
	public String getNachname() {
		return nachname_;
	}
	
	public void setNachname(String nachname) {
		nachname_ = nachname;
	}
	
	public String getUnternehmen() {
		return unternehmen_;
	}
	
	public void setUnternehmen(String unternehmen) {
		unternehmen_ = unternehmen;
	}
	
	public String getEmail() {
		return email_;
	}
	
	public void setEmail(String email) {
		email_ = email;
	}
	
	public String getTelefon() {
		return telefon_;
	}
	
	public void setTelefon(String telefon) {
		telefon_ = telefon;
	}
	
	public ArrayList<Angebot> getAngebot() {
		if(angebot_liste_.size() == 0){
			return null;
		}
		else {
			return angebot_liste_;
		}
	}
	
	public void setAngebot(ArrayList<Angebot> angebot_liste) {
		angebot_liste_ = angebot_liste;
	}
	
	public void addAngebot(Angebot angebot) {
		angebot_liste_.add(angebot);
	}
}
