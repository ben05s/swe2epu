package epu.at.dal;

import java.util.Date;

public class Angebot {
	int id_;
	int kunde_id_;
	double summe_;
	int dauer_; // in Tagen
	Date datum_;
	double u_chance_; // in %
	
	public Angebot() {
		
	}
	
	public void setID(int id) {
		id_ = id;
	}
	
	public int getID() {
		return id_;
	}
	
	public void setKunde(int id) {
		kunde_id_ = id;
	}
	
	public int getKunde() {
		return kunde_id_;
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
