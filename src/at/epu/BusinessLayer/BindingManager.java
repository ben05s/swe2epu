package at.epu.BusinessLayer;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class BindingManager {
	
	private ArrayList<String> illegalInput = null;
	
	BindingManager() {
		illegalInput = new ArrayList<String>();
		
		illegalInput.add("drop table");
		illegalInput.add("delete");
		illegalInput.add("update");
		illegalInput.add("alter table");
		illegalInput.add("<");
		illegalInput.add("+");
		illegalInput.add("?");
		illegalInput.add("kontake");
		illegalInput.add("kunden");
		illegalInput.add("angebote");
		illegalInput.add("projekte");
		illegalInput.add("ausgangsrechnungen");
		illegalInput.add("eingangsrechnungen");
		illegalInput.add("rechnungszeilen");
		illegalInput.add("buchungszeilen");
	}
	
	public boolean checkInput(Object[] data, ArrayList<String> choosenData) throws NullPointerException {
		
		if(data.length == 0) {
			JOptionPane.showMessageDialog(null, "Sie haben noch nichts ausgefüllt!", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		else {
			for(int i=0;i<data.length;i++) {
				
				if(data[i].toString().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Füllen sie bitte alle Felder aus!", "Error", JOptionPane.ERROR_MESSAGE);
					return false;
				} 
				else {
					for(int x=0;x<illegalInput.size();x++) {
						if(data[i].toString().contains(illegalInput.get(x))) {
							JOptionPane.showMessageDialog(null, "Die Eingabe: \'" + data[i] + "\' ist nicht erlaubt!", "Error", JOptionPane.ERROR_MESSAGE);
							return false;
						}
					}	
				}
				
				if(choosenData.size() == 0) {
					JOptionPane.showMessageDialog(null, "Wählen sie von allen Auswählen-Buttons etwas aus bevor sie fortfahren!", "Error", JOptionPane.ERROR_MESSAGE);
					return false;
				}
			}
		}
		
		return true;
	}
}
