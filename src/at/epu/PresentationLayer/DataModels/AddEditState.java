package at.epu.PresentationLayer.DataModels;

import java.util.ArrayList;

public class AddEditState {
	 String[] addEditColNames = null;
	 Object[][] addEditData = null;
	 ArrayList< ArrayList<String> > chosenData = new ArrayList< ArrayList<String> >();	/** ausgewählte Daten */
	 ArrayList<Integer> missingCols = new ArrayList<Integer>(); /** nicht editierbar */
	 ArrayList<Integer> chooseIndex = new ArrayList<Integer>(); /** auswählbar */
	 ArrayList<Integer> indexChoosable = new ArrayList<Integer>(); /** auswählbare spalten */
	 boolean detailTableView = false;
	 BackofficeTableModel parent = null;
	 
	public AddEditState(BackofficeTableModel parent) {
		this.parent = parent;
		
		for ( int i = 0; i < 3; ++i) {
			chosenData.add(new ArrayList<String>());
		}
	}
	
	public int getMaxChosenDataSize() {
		return chosenData.size();
	}
	
	public ArrayList< ArrayList<String> > getAllChosenData() {
		return chosenData;
	}
	
	public ArrayList<Integer> getIndexChoosable() {
		return indexChoosable;
	}
	 
	public ArrayList<Integer> getMissingCols() {
		return missingCols;
	}

	public void setMissingCols(ArrayList<Integer> missingCols) {
		this.missingCols = missingCols;
	}
	
	public ArrayList<String> getChoosenData(int index) {
		return this.chosenData.get(index);
	}

	public void resetChoosenData(int index) {
		this.chosenData.set(index, new ArrayList<String>());
	}

	public void addChoosenData(int index, String data_) {
		this.chosenData.get(index).add(data_);
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

	public void removeChoosenData(int index, String data_) {
		int idx = this.chosenData.get(index).indexOf(data_);
		
		this.chosenData.remove(idx);
	}

	public void setChoosenData(int index, String data_) {
		this.chosenData.set(index, new ArrayList<String>());
		this.chosenData.get(index).add(data_);
	}

	public void deleteChoosenData(int index) {
		this.chosenData.set(index, new ArrayList<String>());
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
