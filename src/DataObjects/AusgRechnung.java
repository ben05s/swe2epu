package DataObjects;

import java.util.ArrayList;

public class AusgRechnung {
	int id_;
	ArrayList<Rechnungszeile> rechnungszeile_liste_ = new ArrayList<Rechnungszeile>();
	ArrayList<Buchungszeile> buchungszeile_liste_ = new ArrayList<Buchungszeile>();
	Kunde kunde_;
	
	public int getID() {
		return id_;
	}
	
	public void setID(int id) {
		id_ = id;
	}
	
	public ArrayList<Rechnungszeile> getRechnungszeilen() {
		if(rechnungszeile_liste_.size() == 0){
			return null;
		}
		else {
			return rechnungszeile_liste_;
		}
	}
	
	public void setRechnungszeilen(Rechnungszeile rechnungszeile) {
		rechnungszeile_liste_.add(rechnungszeile);
	}
	
	public ArrayList<Buchungszeile> getBuchungszeilen() {
		if(buchungszeile_liste_.size() == 0){
			return null;
		}
		else {
			return buchungszeile_liste_;
		}
	}
	
	public void setBuchungszeilen(Buchungszeile buchungszeile) {
		buchungszeile_liste_.add(buchungszeile);
	}
	
	public Kunde getKunde() {
		return kunde_;
	}
	
	public void setKunde(Kunde kunde) {
		kunde_ = kunde;
	}
}
