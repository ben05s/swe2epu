package at.epu.DataAccessLayer.DataObjects;

import java.util.Date;

public class OfferDataObject extends DataObject {
	String titel;
	int kunde_id;
	double summe;
	int dauer; // in Tagen
	Date datum;
	double chance; // in %
	
	public String getTitel() {
		return titel;
	}
	public void setTitel(String titel) {
		this.titel = titel;
	}
	public int getKunde_id() {
		return kunde_id;
	}
	public void setKunde_id(int kunde_id) {
		this.kunde_id = kunde_id;
	}
	public double getSumme() {
		return summe;
	}
	public void setSumme(double summe) {
		this.summe = summe;
	}
	public int getDauer() {
		return dauer;
	}
	public void setDauer(int dauer) {
		this.dauer = dauer;
	}
	public Date getDatum() {
		return datum;
	}
	public void setDatum(Date datum) {
		this.datum = datum;
	}
	public double getChance() {
		return chance;
	}
	public void setChance(double chance) {
		this.chance = chance;
	}
}
