package at.epu.DataAccessLayer.DataObjects;

public class OutBillDataObject extends DataObject {
	String rechnungskuerzel;
	int kunde_id;
	int rzeile_mapping_id;
	int bzeile_mapping_id;
	String status;
	
	public String getRechnungskuerzel() {
		return rechnungskuerzel;
	}
	public void setRechnungskuerzel(String rechnungskuerzel) {
		this.rechnungskuerzel = rechnungskuerzel;
	}
	public int getKunde_id() {
		return kunde_id;
	}
	public void setKunde_id(int kunde_id) {
		this.kunde_id = kunde_id;
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
