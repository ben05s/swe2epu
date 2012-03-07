package at.epu.DataAccessLayer.DataObjects;

import java.util.ArrayList;
/*
 * ID | Kontakt | Buchungszeile
 */
public class EingRechnung {
	int id_;
	Kontakt kontakt_;
	ArrayList<Buchungszeile> bzeile_liste_;
	
	public int getId_() {
		return id_;
	}
	public void setId_(int id_) {
		this.id_ = id_;
	}
	public Kontakt getKontakt_() {
		return kontakt_;
	}
	public void setKontakt_(Kontakt kontakt_) {
		this.kontakt_ = kontakt_;
	}
	public ArrayList<Buchungszeile> getBzeile_liste_() {
		return bzeile_liste_;
	}
	public void setBzeile_liste_(ArrayList<Buchungszeile> bzeile_liste_) {
		this.bzeile_liste_ = bzeile_liste_;
	}
	
	
}
