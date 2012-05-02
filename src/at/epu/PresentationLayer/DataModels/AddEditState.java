package at.epu.PresentationLayer.DataModels;

import java.util.ArrayList;

public class AddEditState {
	 String[] addEditColNames = null;
	 Object[][] addEditData = null;
	 ArrayList<String> choosenData = new ArrayList<String>();	/** ausgewählte Datei */
	 ArrayList<Integer> missingCols = new ArrayList<Integer>(); /** nicht editierbar */
	 ArrayList<Integer> chooseIndex = new ArrayList<Integer>(); /** auswählbar */
	 boolean detailTableView = false;
	 BackofficeTableModel parent = null;
	 
	public AddEditState(BackofficeTableModel parent) {
		this.parent = parent;
	}
	 
	public ArrayList<Integer> getMissingCols() {
		return missingCols;
	}

	public void setMissingCols(ArrayList<Integer> missingCols) {
		this.missingCols = missingCols;
	}
	
	public ArrayList<String> getChoosenData() {
		return this.choosenData;
	}

	public void resetChoosenData() {
		this.choosenData = new ArrayList<String>();
	}

	public void addChoosenData(String data_) {
		this.choosenData.add(data_);
	}

	public void setChooseIndex(int index) {
		this.chooseIndex.add(index);
	}

	public ArrayList<Integer> getChooseIndex() {
		return this.chooseIndex;
	}

	public void deleteChooseIndex() {
		this.chooseIndex = new ArrayList<Integer>();
	}

	public void removeChoosenData(String data_) {
		for(int i=0;i<this.choosenData.size();i++) {
			if(this.choosenData.get(i).equals(data_)) {
				this.choosenData.remove(i);
			}
		}
	}

	public void setChoosenData(String data_) {
		this.choosenData = new ArrayList<String>();
		this.choosenData.add(data_);
	}

	public void deleteChoosenData() {
		this.choosenData = new ArrayList<String>();
	}
	
	/*
	 * Creates the array to be presented when editing or adding some data.
	 * must be different because some specified columns (missingCols) should not be in the add/Edit screen
	 */
	public Object[][] getAddEditData() {
		Object[][] data = parent.getDataObjectCollection().toDataArray();
		
		int z=-1;
		Object[][] addEditData = new Object[data.length][this.addEditColNames.length];
		for(int i=0;i<data.length;i++) {
			for(int x=0;x<this.addEditColNames.length;x++) {
				z++;
				while(! this.addEditColNames[x].equals(parent.getColumnNames()[z])) {	
					if(i == 0) {
						missingCols.add(z);
					}
					z++;
				}
				addEditData[i][x] = data[i][z];

			}
			z=0;
		}
		return addEditData;
	}
	
	public boolean isDetailTableView() {
		return detailTableView;
	}

	public void setDetailTableView() {
		this.detailTableView = true;
	}

	public void unsetDetailTableView() {
		this.detailTableView = false;
	}

	public void setDetailTableView(boolean detailTableView) {
		this.detailTableView = detailTableView;
	}

	public void setAddEditColNames(String[] addEditColNames) {
		this.addEditColNames = addEditColNames;
	}
	
	public String[] getAddEditColNames() {
		return addEditColNames;
	}
}
