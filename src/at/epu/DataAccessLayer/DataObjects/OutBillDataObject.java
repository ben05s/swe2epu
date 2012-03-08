package at.epu.DataAccessLayer.DataObjects;

import java.util.ArrayList;

public class OutBillDataObject {
	int id_;
	ArrayList<BillRowDataObject> rechnungszeile_liste_ = new ArrayList<BillRowDataObject>();
	ArrayList<BookRowDataObject> buchungszeile_liste_ = new ArrayList<BookRowDataObject>();
	CustomerDataObject kunde_;
	
	public int getID() {
		return id_;
	}
	
	public void setID(int id) {
		id_ = id;
	}
	
	public ArrayList<BillRowDataObject> getRechnungszeilen() {
		if(rechnungszeile_liste_.size() == 0){
			return null;
		}
		else {
			return rechnungszeile_liste_;
		}
	}
	
	public void setRechnungszeilen(ArrayList<BillRowDataObject> rechnungszeile) {
		rechnungszeile_liste_.addAll(rechnungszeile);
	}
	
	public ArrayList<BookRowDataObject> getBuchungszeilen() {
		if(buchungszeile_liste_.size() == 0){
			return null;
		}
		else {
			return buchungszeile_liste_;
		}
	}
	
	public void setBuchungszeilen(ArrayList<BookRowDataObject> buchungszeile) {
		buchungszeile_liste_.addAll(buchungszeile);
	}
	
	public CustomerDataObject getKunde() {
		return kunde_;
	}
	
	public void setKunde(CustomerDataObject kunde) {
		kunde_ = kunde;
	}
}