package at.epu.DataAccessLayer.DataObjects;

/*
 * ID(PK) | Angebot_ID(FK) | AusgRechnung_Liste(FK/PK)
 */
public class ProjectDataObject extends DataObject {
	String titel;
	int angebot_id;
	int ausgr_mapping_id;
	double zeit;
	
	public String getTitel() {
		return titel;
	}
	public void setTitel(String titel) {
		this.titel = titel;
	}
	public int getAngebot_id() {
		return angebot_id;
	}
	public void setAngebot_id(int angebot_id) {
		this.angebot_id = angebot_id;
	}
	public int getAusgr_mapping_id() {
		return ausgr_mapping_id;
	}
	public void setAusgr_mapping_id(int ausgr_mapping_id) {
		this.ausgr_mapping_id = ausgr_mapping_id;
	}
	public double getZeit() {
		return zeit;
	}
	public void setZeit(double zeit) {
		this.zeit = zeit;
	}
}
