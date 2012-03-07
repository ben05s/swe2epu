package at.epu.DataAccessLayer.DataObjects;

public class BankAccountDataObject {
	int id_;
	String kontonummer_;
	String vorname_;
	String nachname_;
	String bank_;
	int blz_;
	
	public int getId_() {
		return id_;
	}
	public void setId_(int id_) {
		this.id_ = id_;
	}
	public String getKontonummer_() {
		return kontonummer_;
	}
	public void setKontonummer_(String kontonummer_) {
		this.kontonummer_ = kontonummer_;
	}
	public String getVorname_() {
		return vorname_;
	}
	public void setVorname_(String vorname_) {
		this.vorname_ = vorname_;
	}
	public String getNachname_() {
		return nachname_;
	}
	public void setNachname_(String nachname_) {
		this.nachname_ = nachname_;
	}
	public String getBank_() {
		return bank_;
	}
	public void setBank_(String bank_) {
		this.bank_ = bank_;
	}
	public int getBlz_() {
		return blz_;
	}
	public void setBlz_(int blz_) {
		this.blz_ = blz_;
	}
}
