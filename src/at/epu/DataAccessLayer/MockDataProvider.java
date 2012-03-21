package at.epu.DataAccessLayer;

import at.epu.DataAccessLayer.DataModels.BackofficeTableModel;

public class MockDataProvider {

	public void saveData(BackofficeTableModel model, Object[] data_){
		int col = model.getColumnNames().length;
		int row = model.getData().length;
		int id = 0;
		Object[] missingData = new Object[model.getMissingCols().size()];
		
		missingData[0] = id;
		int insertCol = 0;
		row++;

		Object[][] newData = new Object[row][col];
		for(int i=0;i<row;i++) {
			for(int x=0;x<col;x++) {
				if(i < row-1) {
					newData[i][x] = model.getData()[i][x]; 
				}
				//insert the new row
				if(i == row-1) {
					for(int z=0;z<model.getChooseIndex().size();z++) {	
						data_[model.getChooseIndex().get(z)] = model.getChoosenData();
					}
					newData[i][x] = data_[x-insertCol];
					for(int z=0;z<model.getMissingCols().size();z++) {
						if(x == model.getMissingCols().get(z)) {
							newData[i][x] = missingData[z];
							insertCol++;
							break;
						}
					}
				}
			}
		}
		model.setData(newData);
		model.updateTableData();
		model.resetChoosenData();
	}
}
