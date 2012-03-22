package at.epu.DataAccessLayer;

import java.sql.SQLException;

import at.epu.DataAccessLayer.DataModels.BackofficeTableModel;

public class MockDataProvider {

	public void saveData(BackofficeTableModel model, Object[] data_){
		int col = model.getColumnNames().length;
		int row = model.getData().length;
		int id = 0;
		Object[] missingData = new Object[model.getMissingCols().size()];
		System.out.println(model.getTableName());
		missingData[0] = id;
		if(model.getTableName().equals("Rechnungszeilen")) { missingData[1] = 0; }
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
	
	public void updateData(BackofficeTableModel model, Object[] data_, int rowindex) {	
		int col = model.getColumnCount();
		int insertCol = 0;
		
		//in mock models only insert fake missing data (in most cases the id of the row)
		Object[] missingData = new Object[model.getMissingCols().size()];
		missingData[0] = 0;
		if(model.getTableName().equals("Rechnungszeilen")) { missingData[1] = 0; }
		
		for(int i=0;i<col;i++) {
			model.getData()[rowindex][i] = data_[i-insertCol];
			for(int z=0;z<model.getMissingCols().size();z++) {
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
	/*
	public void deleteData(int rowindex, String title) {
		int row = this.data.length;
		int col = this.columnNames.length;
		int counter = 0;
		if(dbHandle != null) {
			try {
				stm = dbHandle.createStatement();
			} catch (SQLException e) {
				System.err.println("Could not create Delete Statement");
			}
			rowindex++;
			sql = "DELETE FROM "+title+" WHERE ROWNUM = "+rowindex;
			rowindex--;
			try {
				stm.executeUpdate(sql);
			} catch (SQLException e) {
				System.err.println("Error when executing the Delete Query");
				e.printStackTrace();
			}
		}
		
		Object[][] newData = new Object[row-1][col];

		for(int i=0;i<row;i++) {
			for(int x=0;x<col;x++) {
				if(i != rowindex) {
					newData[counter][x] = this.data[i][x];
				}
			}
			if(i != rowindex) { counter++; }
		}
		setData(newData);
		fireTableDataChanged();
	}*/
}
