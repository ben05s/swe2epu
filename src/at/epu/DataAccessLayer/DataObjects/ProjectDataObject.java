package at.epu.DataAccessLayer.DataObjects;

import java.util.ArrayList;

/*
 * ID(PK) | Angebot_ID(FK) | AusgRechnung_Liste(FK/PK)
 */
public class ProjectDataObject {
	int id_;
	String titel;
	int angebot_id_;
	ArrayList<OutBillDataObject> ausgrechnung_liste_ = new ArrayList<OutBillDataObject>();
	
	public int getId_() {
		return id_;
	}
	public void setId_(int id_) {
		this.id_ = id_;
	}
	public int getAngebot_id_() {
		return angebot_id_;
	}
	public void setAngebot_id_(int angebot_id_) {
		this.angebot_id_ = angebot_id_;
	}
	public ArrayList<OutBillDataObject> getAusgrechnung_liste_() {
		return ausgrechnung_liste_;
	}
	public String getTitel() {
		return titel;
	}
	public void setTitel(String titel) {
		this.titel = titel;
	}
	public void setAusgrechnung_liste_(ArrayList<OutBillDataObject> ausgrechnung_liste_) {
		this.ausgrechnung_liste_.addAll(ausgrechnung_liste_);
	}
	public void addAusgrechnung_item_(OutBillDataObject item) {
		this.ausgrechnung_liste_.add(item);
	}
	public void deleteAusgrechnung_item_(OutBillDataObject item) {
		for(int i=0;i<this.ausgrechnung_liste_.size();i++) {
			if(this.ausgrechnung_liste_.get(i) == item) {
				this.ausgrechnung_liste_.remove(i);
			}
		}
	}
	
}
