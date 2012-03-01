package epu.at.dal;

import java.util.ArrayList;
import java.util.Date;
import DataObjects.*;

public class DALFake implements DALInterface{
	
	ArrayList<Angebot> angebot_liste = new ArrayList<Angebot>();
	ArrayList<Kunde> kunde_liste = new ArrayList<Kunde>();
	ArrayList<AusgRechnung> ausgrechnung_liste = new ArrayList<AusgRechnung>();
	ArrayList<Rechnungszeile> rechnungszeile_liste = new ArrayList<Rechnungszeile>();
	
	public DALFake() {
		saveKunde(getNewKunde(1, "Benny", "Steindl", "Möbel AG", "b.s80@gmx.at", "06604850184", getAngebote()));
		saveKunde(getNewKunde(2, "Patrick", "Schwab", "Kleider AG", "patrick.schwab@gmx.at", "0664436001", getAngebote()));
		saveKunde(getNewKunde(3, "Johannes", "Steindl", "Auto AG", "johannes@gmx.at", "06600352033", getAngebote()));
		saveAngebot(getNewAngebot(1,getKunden().get(0),200.50,180,new Date(10102012),0.65));
		saveAngebot(getNewAngebot(2,getKunden().get(1),2100,100,new Date(10102012),0.80));
		saveAngebot(getNewAngebot(3,getKunden().get(2),500.00,180,new Date(10102012),0.55));
		
	}

	public Angebot getNewAngebot(int id, Kunde kunde, double summe, int dauer, Date datum, double chance) {
		Angebot angebot = new Angebot();
		angebot.setID(id);
		angebot.setKunde(kunde);
		angebot.setSumme(summe);
		angebot.setDauer(dauer);
		angebot.setDatum(datum);
		angebot.setUChance(chance);
		return angebot;
	}

	@Override
	public Angebot getAngebot(int id) {
		for(int i=0;i<angebot_liste.size();i++) {
			if(angebot_liste.get(i).getID()==id) {
				return angebot_liste.get(i);
			}
		}
		return null;
	}
	
	@Override
	public void saveAngebot(Angebot angebot) {
		angebot_liste.add(angebot);
	}

	@Override
	public void deleteAngebot(Angebot angebot) {
		for(int i=0;i<angebot_liste.size();i++) {
			if(angebot_liste.get(i)==angebot) {
				angebot_liste.remove(i);
			}
		}
	}
	
	@Override
	public ArrayList<Angebot> getAngebote() {
		return angebot_liste;
	}

	public Kunde getNewKunde(int id, String vorname, String nachname, 
			String unternehmen, String email, String telefon, ArrayList<Angebot> angebot_liste) {
		Kunde kunde = new Kunde();
		kunde.setID(id);
		kunde.setVorname(vorname);
		kunde.setNachname(nachname);
		kunde.setUnternehmen(unternehmen);
		kunde.setEmail(email);
		kunde.setTelefon(telefon);
		kunde.setAngebot(angebot_liste);
		return kunde;
	}
	
	@Override
	public ArrayList<Kunde> getKunden() {
		return kunde_liste;
	}

	@Override
	public Kunde getKunde(int id) {
		for(int i=0;i<kunde_liste.size();i++) {
			if(kunde_liste.get(i).getID()==id) {
				return kunde_liste.get(i);
			}
		}
		return null;
	}

	@Override
	public void saveKunde(Kunde kunde) {
		kunde_liste.add(kunde);
	}

	@Override
	public void deleteKunde(Kunde kunde) {
		for(int i=0;i<kunde_liste.size();i++) {
			if(kunde_liste.get(i)==kunde) {
				kunde_liste.remove(i);
			}
		}
	}
	
	@Override
	public ArrayList<Rechnungszeile> getRechnungszeilen() {
		return rechnungszeile_liste;
	}

	@Override
	public ArrayList<AusgRechnung> getAusgRechnungen() {
		return ausgrechnung_liste;
	}

	@Override
	public ArrayList<EingRechnung> getEingRechnungen() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Bankkonto> getBankkontos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Buchungszeile> getBuchungszeilen() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Kategorie> getKategorien() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rechnungszeile getRechnungszeile(int id) {
		for(int i=0;i<rechnungszeile_liste.size();i++) {
			if(rechnungszeile_liste.get(i).getID()==id) {
				return rechnungszeile_liste.get(i);
			}
		}
		return null;
		
	}

	@Override
	public void saveRechnungszeile(Rechnungszeile rzeile) {
		rechnungszeile_liste.add(rzeile);
	}

	@Override
	public void deleteRechnungszeile(Rechnungszeile rzeile) {
		for(int i=0;i<rechnungszeile_liste.size();i++) {
			if(rechnungszeile_liste.get(i)==rzeile) {
				rechnungszeile_liste.remove(i);
			}
		}
	}

	@Override
	public AusgRechnung getAusgRechnung(int id) {
		for(int i=0;i<ausgrechnung_liste.size();i++) {
			if(ausgrechnung_liste.get(i).getID()==id) {
				return ausgrechnung_liste.get(i);
			}
		}
		return null;
	}

	@Override
	public void saveAusgRechnung(AusgRechnung arechnung) {
		ausgrechnung_liste.add(arechnung);
	}

	@Override
	public void deleteAusgrechnung(AusgRechnung arechnung) {
		for(int i=0;i<ausgrechnung_liste.size();i++) {
			if(ausgrechnung_liste.get(i)==arechnung) {
				ausgrechnung_liste.remove(i);
			}
		}
	}

	@Override
	public EingRechnung getEingRechnung(int id) {
		return null;
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveEingRechnung(EingRechnung erechnung) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteEingrechnung(EingRechnung erechnung) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Bankkonto getBankkonto(int id) {
		return null;
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveBankkonto(Bankkonto bkonto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteBankkonto(Bankkonto bkonto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Buchungszeile getBuchungszeile(int id) {
		return null;
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveBuchungszeile(Buchungszeile bzeile) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteBuchungszeile(Buchungszeile bzeile) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Kategorie getKategorie(int id) {
		return null;
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveKategorie(Kategorie kat) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteKategorie(Kategorie kat) {
		// TODO Auto-generated method stub
		
	}
}
