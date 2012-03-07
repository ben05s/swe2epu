package at.epu.DataAccessLayer.DataObjects;

import java.util.ArrayList;
import java.util.Date;
/*
 * Index(PK) | ID(PK) | EingRechnung | AusgRechnung | Betrag 
 * | Umsatzsteuer | Buchungsdatum | Kategorie
 */
public class Buchungszeile {
	int id_;
	ArrayList<EingRechnung> eingrechnung_liste_;
	ArrayList<AusgRechnung> ausgrechnung_liste_;
	double betrag_;
	double umsatzsteuer_;
	Date buchungsdatum_;
	ArrayList<Kategorie> kategorie_;
	
	public ArrayList<Kategorie> getKategorie_() {
		return kategorie_;
	}
	public void setKategorie_(ArrayList<Kategorie> kategorie_) {
		this.kategorie_ = kategorie_;
	}
	public double getUmsatzsteuer_() {
		return umsatzsteuer_;
	}
	public void setUmsatzsteuer_(double umsatzsteuer_) {
		this.umsatzsteuer_ = umsatzsteuer_;
	}
	
	public int getId_() {
		return id_;
	}
	public void setId_(int id_) {
		this.id_ = id_;
	}
	public ArrayList<EingRechnung> getEingrechnung_liste_() {
		return eingrechnung_liste_;
	}
	public void setEingrechnung_liste_(ArrayList<EingRechnung> eingrechnung_liste_) {
		this.eingrechnung_liste_ = eingrechnung_liste_;
	}
	public ArrayList<AusgRechnung> getAusgrechnung_liste_() {
		return ausgrechnung_liste_;
	}
	public void setAusgrechnung_liste_(ArrayList<AusgRechnung> ausgrechnung_liste_) {
		this.ausgrechnung_liste_ = ausgrechnung_liste_;
	}
	public double getBetrag_() {
		return betrag_;
	}
	public void setBetrag_(double betrag_) {
		this.betrag_ = betrag_;
	}
	public Date getBuchungsdatum_() {
		return buchungsdatum_;
	}
	public void setBuchungsdatum_(Date buchungsdatum_) {
		this.buchungsdatum_ = buchungsdatum_;
	}
}
