package at.epu.DataAccessLayer;

import java.util.ArrayList;
import java.util.Date;

import at.epu.DataAccessLayer.DataObjects.*;

public interface DALInterface {
	public ArrayList<Angebot> getAngebote();
	public ArrayList<Kunde> getKunden();
	public ArrayList<Rechnungszeile> getRechnungszeilen();
	public ArrayList<AusgRechnung> getAusgRechnungen();
	public ArrayList<EingRechnung> getEingRechnungen();
	public ArrayList<Bankkonto> getBankkontos();
	public ArrayList<Buchungszeile> getBuchungszeilen();
	public ArrayList<Kategorie> getKategorien();
	
	public Angebot getAngebot(int id);
	public void saveAngebot(Angebot angebot);
	public void deleteAngebot(Angebot angebot);
	public Kunde getKunde(int id);
	public void saveKunde(Kunde kunde);
	public void deleteKunde(Kunde kunde);
	public Rechnungszeile getRechnungszeile(int id);
	public void saveRechnungszeile(Rechnungszeile rzeile);
	public void deleteRechnungszeile(Rechnungszeile rzeile);
	public AusgRechnung getAusgRechnung(int id);
	public void saveAusgRechnung(AusgRechnung arechnung);
	public void deleteAusgrechnung(AusgRechnung arechnung);
	public EingRechnung getEingRechnung(int id);
	public void saveEingRechnung(EingRechnung erechnung);
	public void deleteEingrechnung(EingRechnung erechnung);
	public Bankkonto getBankkonto(int id);
	public void saveBankkonto(Bankkonto bkonto);
	public void deleteBankkonto(Bankkonto bkonto);
	public Buchungszeile getBuchungszeile(int id);
	public void saveBuchungszeile(Buchungszeile bzeile);
	public void deleteBuchungszeile(Buchungszeile bzeile);
	public Kategorie getKategorie(int id);
	public void saveKategorie(Kategorie kat);
	public void deleteKategorie(Kategorie kat);
}