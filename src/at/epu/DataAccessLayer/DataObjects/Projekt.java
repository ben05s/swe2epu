package at.epu.DataAccessLayer.DataObjects;

import java.util.ArrayList;

/*
 * ID(PK) | Angebot_ID(FK) | AusgRechnung_Liste(FK/PK)
 */
public class Projekt {
	int id_;
	int angebot_id_;
	ArrayList<AusgRechnung> ausgrechnung_liste_ = new ArrayList<AusgRechnung>();
	
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
	public ArrayList<AusgRechnung> getAusgrechnung_liste_() {
		return ausgrechnung_liste_;
	}
	public void setAusgrechnung_liste_(ArrayList<AusgRechnung> ausgrechnung_liste_) {
		this.ausgrechnung_liste_.addAll(ausgrechnung_liste_);
	}
	public void addAusgrechnung_item_(AusgRechnung item) {
		this.ausgrechnung_liste_.add(item);
	}
	public void deleteAusgrechnung_item_(AusgRechnung item) {
		for(int i=0;i<this.ausgrechnung_liste_.size();i++) {
			if(this.ausgrechnung_liste_.get(i) == item) {
				this.ausgrechnung_liste_.remove(i);
			}
		}
	}
	
}
