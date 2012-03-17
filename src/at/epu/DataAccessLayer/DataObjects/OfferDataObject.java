package at.epu.DataAccessLayer.DataObjects;

import java.util.Date;

public class OfferDataObject {
	int id_;
	String titel;
	CustomerDataObject kunde_;
	double summe_;
	int dauer_; // in Tagen
	Date datum_;
	double u_chance_; // in %
	
	public OfferDataObject() {
		
	}
	
	public void setID(int id) {
		id_ = id;
	}
	
	public int getID() {
		return id_;
	}
	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}
	
	public void setKunde(CustomerDataObject kunde) {
		kunde_ = kunde;
	}
	
	public CustomerDataObject getKunde() {
		return kunde_;
	}
	
	public void setSumme(double summe) {
		summe_ = summe;
	}
	
	public double getSumme() {
		return summe_;
	}
	
	public void setDauer(int dauer) {
		dauer_ = dauer;
	}
	
	public int getDauer() {
		return dauer_;
	}
	
	public void setDatum(Date datum) {
		datum_ = datum;
	}
	
	public Date getDatum() {
		return datum_;
	}
	
	public void setUChance(double chance) {
		u_chance_ = chance;
	}
	
	public double getUchance() {
		return u_chance_;
	}
}
