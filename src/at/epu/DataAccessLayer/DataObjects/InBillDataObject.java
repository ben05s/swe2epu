package at.epu.DataAccessLayer.DataObjects;

import java.util.ArrayList;
/*
 * ID | Kontakt | Buchungszeile
 */
public class InBillDataObject {
	int id_;
	ContactDataObject kontakt_;
	ArrayList<BookRowDataObject> bzeile_liste_;
	
	public int getId_() {
		return id_;
	}
	public void setId_(int id_) {
		this.id_ = id_;
	}
	public ContactDataObject getKontakt_() {
		return kontakt_;
	}
	public void setKontakt_(ContactDataObject kontakt_) {
		this.kontakt_ = kontakt_;
	}
	public ArrayList<BookRowDataObject> getBzeile_liste_() {
		return bzeile_liste_;
	}
	public void setBzeile_liste_(ArrayList<BookRowDataObject> bzeile_liste_) {
		this.bzeile_liste_ = bzeile_liste_;
	}
	
	
}
