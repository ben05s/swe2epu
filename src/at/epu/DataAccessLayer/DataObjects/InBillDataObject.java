package at.epu.DataAccessLayer.DataObjects;

/*
 * ID | Kontakt | Buchungszeile
 */
public class InBillDataObject extends DataObject {	
	String rechnungskürzel;
	int kontakt_id;
	int rzeile_mapping_id;
	int bzeile_mapping_id;
	String status;
	
	public String getRechnungskürzel() {
		return rechnungskürzel;
	}
	public void setRechnungskürzel(String rechnungskürzel) {
		this.rechnungskürzel = rechnungskürzel;
	}
	public int getKontakt_id() {
		return kontakt_id;
	}
	public void setKontakt_id(int kontakt_id) {
		this.kontakt_id = kontakt_id;
	}
	public int getRzeile_mapping_id() {
		return rzeile_mapping_id;
	}
	public void setRzeile_mapping_id(int rzeile_mapping_id) {
		this.rzeile_mapping_id = rzeile_mapping_id;
	}
	public int getBzeile_mapping_id() {
		return bzeile_mapping_id;
	}
	public void setBzeile_mapping_id(int bzeile_mapping_id) {
		this.bzeile_mapping_id = bzeile_mapping_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}

