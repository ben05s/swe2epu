package at.epu.DataAccessLayer.DataObjects;

import java.util.Date;
/*
 * | ID(PK) | EingRechnung | AusgRechnung | Betrag 
 * | Umsatzsteuer | Buchungsdatum | Kategorie
 */
public class BankAccountDataObject extends DataObject {
	int eingangsrechnung_id;
	int ausgangsrechnung_id;
	double betrag;
	double umsatzsteuer;
	Date buchungsdatum;
	int kat_mapping_id;
	
	public int getEingangsrechnung_id() {
		return eingangsrechnung_id;
	}
	public void setEingangsrechnung_id(int eingangsrechnung_id) {
		this.eingangsrechnung_id = eingangsrechnung_id;
	}
	public int getAusgangsrechnung_id() {
		return ausgangsrechnung_id;
	}
	public void setAusgangsrechnung_id(int ausgangsrechnung_id) {
		this.ausgangsrechnung_id = ausgangsrechnung_id;
	}
	public double getBetrag() {
		return betrag;
	}
	public void setBetrag(double betrag) {
		this.betrag = betrag;
	}
	public double getUmsatzsteuer() {
		return umsatzsteuer;
	}
	public void setUmsatzsteuer(double umsatzsteuer) {
		this.umsatzsteuer = umsatzsteuer;
	}
	public Date getBuchungsdatum() {
		return buchungsdatum;
	}
	public void setBuchungsdatum(Date buchungsdatum) {
		this.buchungsdatum = buchungsdatum;
	}
	public int getKat_mapping_id() {
		return kat_mapping_id;
	}
	public void setKat_mapping_id(int kat_mapping_id) {
		this.kat_mapping_id = kat_mapping_id;
	}	
}
