package epu.at.dal;

import java.util.ArrayList;
import java.util.Date;

public class DALFake implements DALInterface{
	
	ArrayList<Angebot> angebot_list = new ArrayList<Angebot>();
	Angebot angebot = new Angebot();
	
	@Override
	public void init() {
		angebot.setID(1);
		angebot.setKunde(1);
		angebot.setSumme(1000.00);
		angebot.setDauer(365);
		angebot.setDatum(new Date(29022012));
		angebot.setUChance(0.75);
		
		saveAngebot(angebot);
	
		angebot.setID(2);
		angebot.setKunde(2);
		angebot.setSumme(500.00);
		angebot.setDauer(180);
		angebot.setDatum(new Date(29022012));
		angebot.setUChance(0.65);
		
		saveAngebot(angebot);
	}

	@Override
	public Angebot getAngebot(int id) {
		for(int i=0;i<angebot_list.size();i++) {
			if(angebot_list.get(i).getID()==id) {
				return angebot_list.get(i);
			}
		}
		return null;
	}

	@Override
	public void saveAngebot(Angebot angebot) {
		angebot_list.add(angebot);
	}

	@Override
	public void deleteAngebot(Angebot angebot) {
		for(int i=0;i<angebot_list.size();i++) {
			if(angebot_list.get(i)==angebot) {
				angebot_list.remove(i);
			}
		}
	}
	
	@Override
	public ArrayList<Angebot> getAngebote() {
		return angebot_list;
	}

	@Override
	public ArrayList<Rechnungszeile> getRechnungszeilen() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<AusgRechnung> getAusgRechnungen() {
		// TODO Auto-generated method stub
		return null;
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
		return null;
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveRechnungszeile(Rechnungszeile rzeile) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteRechnungszeile(Rechnungszeile rzeile) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AusgRechnung getAusgRechnung(int id) {
		return null;
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveAusgRechnung(AusgRechnung arechnung) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAusgrechnung(AusgRechnung arechnung) {
		// TODO Auto-generated method stub
		
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
