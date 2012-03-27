package at.epu.DataAccessLayer;


import at.epu.DataAccessLayer.DataModels.BackofficeTableModel;

public class MockDataProvider {

	public void saveData(BackofficeTableModel model, Object[] data_){
		int col = model.getColumnNames().length;
		int row = model.getData().length;
		int id = 0;
		
		//insert dummy data into non editable data 
		Object[] missingData = new Object[model.getMissingCols().size()];
		missingData[0] = id;
		if(model.getTableName().equals("Rechnungszeilen")) { missingData[1] = 0; }
		
		int insertCol = 0;
		row++;	//array should now hold one additional data row
		Object[][] newData = new Object[row][col];
		
		for(int i=0;i<row;i++) {
			for(int x=0;x<col;x++) {
				if(i < row-1) {
					newData[i][x] = model.getData()[i][x]; 
				}
				//insert the new row
				if(i == row-1) {
					//insert data into new row from the auswählen function 
					//up to this point there was a placeholder in the data_[] array. now fill it with the choosen data!
					for(int z=0;z<model.getChooseIndex().size();z++) {	
						data_[model.getChooseIndex().get(z)] = model.getChoosenData();
					}
					
					newData[i][x] = data_[x-insertCol];
					
					//insert dummy data into array where the add/edit function does not allow you to put data(id for example)
					for(int z=0;z<model.getMissingCols().size();z++) {
						
						//check if current col is a missing col in add/edit fkt
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
	
	public void updateData(BackofficeTableModel model, Object[] data_, int rowindex) {	
		int col = model.getColumnCount();
		int insertCol = 0;
		
		//in mock models only insert fake missing data (in most cases the id of the row)
		Object[] missingData = new Object[model.getMissingCols().size()];
		missingData[0] = 0;
		if(model.getTableName().equals("Rechnungszeilen")) { missingData[1] = 0; }
		
		for(int i=0;i<col;i++) {
			
			model.getData()[rowindex][i] = data_[i-insertCol];
			
			//insert dummy data into array where the add/edit function does not allow you to put data(id for example)
			for(int z=0;z<model.getMissingCols().size();z++) {
				
				//check if current col is a missing col in add/edit fkt
				if(i == model.getMissingCols().get(z)) {
					model.getData()[rowindex][i] = missingData[z];
					insertCol++;
					break;
				}
			}
		}
		
		model.updateTableData();
		model.resetChoosenData();
	}
	
	public void deleteData(BackofficeTableModel model, int rowindex) {
		int row = model.getData().length;
		int col = model.getColumnCount();
		int counter = 0;
		
		Object[][] newData = new Object[row-1][col];

		for(int i=0;i<row;i++) {
			for(int x=0;x<col;x++) {
				//dont fill the new data array with the selected row(rowindex) which should be deleted 
				if(i != rowindex) {
					newData[counter][x] = model.getData()[i][x];
				}
			}
		
			if(i != rowindex) { counter++; }
		}
		
		model.setData(newData);
		model.updateTableData();
	}
}
