package at.epu.DataAccessLayer.DataObjects;

public class BillRowDataObject extends DataObject {
	int ausgangsrechnung_id;
	int angebot_id;
	String kommentar;
	double steuern;
	double betrag;
	
	public int getAusgangsrechnung_id() {
		return ausgangsrechnung_id;
	}
	public void setAusgangsrechnung_id(int ausgangsrechnung_id) {
		this.ausgangsrechnung_id = ausgangsrechnung_id;
	}
	public int getAngebot_id() {
		return angebot_id;
	}
	public void setAngebot_id(int angebot_id) {
		this.angebot_id = angebot_id;
	}
	public String getKommentar() {
		return kommentar;
	}
	public void setKommentar(String kommentar) {
		this.kommentar = kommentar;
	}
	public double getSteuern() {
		return steuern;
	}
	public void setSteuern(double steuern) {
		this.steuern = steuern;
	}
	public double getBetrag() {
		return betrag;
	}
	public void setBetrag(double betrag) {
		this.betrag = betrag;
	}
}
 