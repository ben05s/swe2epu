package at.epu.DataAccessLayer.DataModels;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

public abstract class BackofficeTableModel extends DefaultTableModel implements FilterableDataModel {
	private static final long serialVersionUID = 2110831280426094363L;

	protected String[] columnNames 		= null;
	protected String[] addEditColNames  = null;
	protected Object[][] data 			= null;
	protected Object[][] presented_data = null;
	protected ArrayList<String> choosenData = new ArrayList<String>();
	protected ArrayList<Integer> chooseIndex = new ArrayList<Integer>();
	
	protected Statement stm;
	protected Statement sub_stm;
	protected ResultSet rs;
	protected ResultSet sub_rs;
	protected String sql;
	protected String sql_count;
	protected Connection dbHandle = null;
	protected ArrayList<Integer> missingCols = new ArrayList<Integer>();
	
	public Connection getDbHandle() {
		return dbHandle;
	}

	public void setDbHandle(Connection dbHandle) {
		this.dbHandle = dbHandle;
	}

	public void closeConnection(Connection databaseHandle) {
		if ( databaseHandle != null )
		{
			try {
				databaseHandle.close();
			} catch ( SQLException f ) {
				f.printStackTrace();
			}
		}
		System.exit(0);
	}
	
	public String[] getAddEditColNames() {
		return addEditColNames;
	}

	public void setAddEditColNames(String[] addEditColNames) {
		this.addEditColNames = addEditColNames;
	}
	
	@Override
	public String getColumnName(int column) {
		return getColumnNames()[column];
	}
	
	@Override
	public int getColumnCount() {
		return getColumnNames().length;
	}
	
	@Override
	public int getRowCount() {
		if(presented_data != null)
		{
			return presented_data.length;
		}
		else
		{
			return 0;
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return presented_data[rowIndex][columnIndex];
	}
	
	public void filterDataModel(String filterString) {
		if(filterString.isEmpty()) {
			presented_data = data;
		} else {
			presented_data = DataFilterProvider.filterDataModel(filterString, data);
		}
		
		fireTableDataChanged();
	}

	public String[] getColumnNames() {
		return columnNames;
	}

	public void setColumnNames(String[] columnNames) {
		this.columnNames = columnNames;
	}

	public Object[][] getData() {
		return data;
	}

	public void setData(Object[][] data) {
		if(presented_data == null) {
			presented_data = data;
		}
		
		this.data = data;
	}
	
	public Object[][] getAddEditData() {		
		int z=0;
		Object[][] addEditData = new Object[this.data.length][this.addEditColNames.length];
		for(int i=0;i<this.data.length;i++) {
			for(int x=0;x<this.addEditColNames.length;x++) {
				while(! this.addEditColNames[x].equals(this.columnNames[z])) {	
					if(i == 0) {
						missingCols.add(z);
					}
					z++;
				}
				addEditData[i][x] = this.data[i][z];
				z++;
			}
			z=0;
		}
		
		return addEditData;
	}
	
	public void addStringArrayListToData(ArrayList<String> list, int row, int col) {
		this.data[row][col] = list;
	}
	
	public void addIntegerArrayListToData(ArrayList<Integer> list, int row, int col) {
		this.data[row][col] = list;
	}
	
	public void deleteData(int rowindex, String title) {
		int row = this.data.length;
		int col = this.columnNames.length;
		int counter = 0;
		if(dbHandle != null) {
			try {
				stm = dbHandle.createStatement();
			} catch (SQLException e) {
				System.err.println("Could not create Delete Statement");
				closeConnection(dbHandle);
			}
			rowindex++;
			sql = "DELETE FROM "+title+" WHERE ROWNUM = "+rowindex;
			rowindex--;
			try {
				stm.executeUpdate(sql);
			} catch (SQLException e) {
				System.err.println("Error when executing the Delete Query");
				e.printStackTrace();
				closeConnection(dbHandle);
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
	
	public void removeChoosenData(String data_) {
		for(int i=0;i<this.choosenData.size();i++) {
			if(this.choosenData.get(i).equals(data_)) {
				this.choosenData.remove(i);
			}
		}
	}
	public int getNextIdForTable(String title) {
		int next_id = 0;
		sql = "SELECT MAX(id) FROM "+title;
		
		try {
			rs = stm.executeQuery(sql);
		} catch (SQLException e1) {
			System.err.println("Error when executing the Save Query");
			e1.printStackTrace();
			closeConnection(dbHandle);
		}
		
		try {
			rs.next();
			next_id = rs.getInt(1) + 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return next_id;
	}
	
	public void insertDataIntoMappingTable(ArrayList<Integer> ids_, String table_, int id_) {
		for(int i=0;i<this.choosenData.size();i++) {
			sql = "INSERT INTO "+table_+" VALUES("+id_+", "+ids_.get(i)+")";
			
			try {
				stm.executeUpdate(sql);
			} catch (SQLException e1) {
				System.err.println("Error when executing the Save Query");
				e1.printStackTrace();
				closeConnection(dbHandle);
			}
		}
	}
	
	public void insertDataIntoKontakte(Object[] data_, String title) {
		int id_ = getNextIdForTable(title);
		
		sql = "INSERT INTO "+title+" VALUES("+id_+",\'"+data_[0].toString()+"\',\'"+
				data_[1].toString()+"\',\'"+data_[2].toString()+"\',\'"+data_[3].toString()+"\',\'"+data_[4].toString()+"\')";
		
		try {
			stm.executeUpdate(sql);
		} catch (SQLException e1) {
			System.err.println("Error when executing the Save Query");
			e1.printStackTrace();
			closeConnection(dbHandle);
		}
	}
	
	public void insertDataIntoKunden(Object[] data_, String table) {
		int id = getNextIdForTable(table);
		
		//enter this if container only if some mapping has to be done
		if(this.choosenData.size() != 0) { 
			String mapping_table ="ANGEBOTE_MAPPING";
			int mapping_id = getNextIdForTable(mapping_table);
			
			insertDataIntoMappingTable(getIdsForMapping("ANGEBOTE"), mapping_table, mapping_id);
			
			sql = "INSERT INTO KUNDEN VALUES("+id+",\'"+data_[0].toString()+"\',\'"+data_[1].toString()+"\',\'"+
					data_[2].toString()+"\',\'"+data_[3].toString()+"\',\'"+data_[4].toString()+"\',\'"+data_[5].toString()+"\',"+mapping_id+")";
		} else {
			sql = "INSERT INTO KUNDEN VALUES("+id+",\'"+data_[0].toString()+"\',\'"+data_[1].toString()+"\',\'"+
					data_[2].toString()+"\',\'"+data_[3].toString()+"\',\'"+data_[4].toString()+"\',\'"+data_[5].toString()+"\',"+null+")";
		}
		
		try {
			stm.executeUpdate(sql);
		} catch (SQLException e1) {
			System.err.println("Error when executing the Save Query");
			e1.printStackTrace();
			closeConnection(dbHandle);
		}
	}
	
	public boolean checkIdValidation(String table, int id_) {
		sql = "SELECT COUNT(*) FROM "+table+" WHERE id = "+id_;
		
		try {
			rs = stm.executeQuery(sql);
		} catch (SQLException e1) {
			System.err.println("Error when executing the Save Query");
			e1.printStackTrace();
			closeConnection(dbHandle);
		}
		
		try {
			rs.next();
			if(rs.getInt(1) == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public int getIdForKunde(String table, String kunde) {
		int id_ = 0;
		String sub_sql = "SELECT id FROM "+table+" " +
				"WHERE nachname = \'"+kunde+"\'";
		
		try {
			sub_stm = dbHandle.createStatement();
		} catch (SQLException e1) {
			System.err.println("Error on creating sub statement");
			e1.printStackTrace();
		}
			try {
				sub_rs = sub_stm.executeQuery(sub_sql);
			} catch(SQLException e) {
				System.err.println("Error when executing Angebot Query");
				e.printStackTrace();
				closeConnection(dbHandle);
			}
			
			try {
				while(sub_rs.next()) {
					try {
						id_ = sub_rs.getInt(1);
					} catch (SQLException e) {			
						System.err.println("Error when fetching data from Angebot title resultSet");
						closeConnection(dbHandle);
					}
				}
			} catch (SQLException e) {
				System.err.println("Error on next fkt from Angebot resultSet");
				closeConnection(dbHandle);
			}
			System.out.println(id_);
		return id_;
	}
	
	public void insertDataIntoAngebote(Object[] data_, String table) {
		int id = getNextIdForTable(table);
		
		//enter this if container only if some mapping or id validation has to be done
		if(this.choosenData.size() != 0) { 
			String table_ ="KUNDEN";
			int kunde_id = getIdForKunde(table_, this.choosenData.get(0));
			
			if(! checkIdValidation(table_, kunde_id)) {
				System.err.println("There is no Primary Key to reference for Selected Data");
			}
			System.out.println(data_[0].toString()+", "+data_[1].toString()+", "+data_[2].toString()+", "+data_[3].toString()+", "+data_[4].toString()+", "+data_[5].toString());
			sql = "INSERT INTO ANGEBOTE VALUES("+id+",\'"+data_[0].toString()+"\',"+kunde_id+",\'"+
					Integer.parseInt(data_[2].toString())+"\',\'"+Integer.parseInt(data_[3].toString())+"\',\'"+
					Integer.parseInt(data_[4].toString())+"\',\'"+Integer.parseInt(data_[5].toString())+"\')";
		} else {

			System.out.println(data_[0].toString()+", "+data_[1].toString()+", "+data_[2].toString()+", "+data_[3].toString()+", "+data_[4].toString()+", "+data_[5].toString());
			sql = "INSERT INTO ANGEBOTE VALUES("+id+",\'"+data_[0].toString()+"\',"+null+","+
					data_[2]+","+data_[3]+",\'"+data_[4].toString()+"\',"+data_[5]+")";
		}
		
		try {
			stm = dbHandle.createStatement();
		} catch (SQLException e1) {
			System.err.println("Error on creating sub statement");
			e1.printStackTrace();
		}
		
		try {
			stm.executeUpdate(sql);
		} catch (SQLException e1) {
			System.err.println("Error when executing the Save Query");
			e1.printStackTrace();
			closeConnection(dbHandle);
		}
	}
	
	public void saveData(Object[] data_, String title) {
		int col = this.columnNames.length;
		int row = this.data.length;
		int id = 0;
		Object[] missingData = new Object[missingCols.size()];
		
		if(dbHandle != null) {
			try {
				stm = dbHandle.createStatement();
			} catch (SQLException e) {
				System.err.println("Could not create Save Statement");
				closeConnection(dbHandle);
			}
			
			if(title.equals("Kontakte")) {
				missingData[0] = getNextIdForTable(title);
				insertDataIntoKontakte(data_, title);
			}
			if(title.equals("Kunden")) {
				missingData[0] = getNextIdForTable(title);
				insertDataIntoKunden(data_, title);
			}
			/*
			if(title.equals("Angebote")) {
				missingData[0] = getNextIdForTable(title);
				insertDataIntoAngebote(data_, title);
			}
			*/
		} else {
			missingData[0] = id;
		}
		
		int insertCol = 0;
		row++;
		//fill last element with arrayList of CheckBoxes
		
		Object[][] newData = new Object[row][col];
		for(int i=0;i<row;i++) {
			for(int x=0;x<col;x++) {
				if(i < row-1) {
					newData[i][x] = this.data[i][x]; 
				}
				//insert the new row
				if(i == row-1) {
					for(int z=0;z<this.chooseIndex.size();z++) {	
						data_[this.chooseIndex.get(z)] = getChoosenData();
					}
					newData[i][x] = data_[x-insertCol];
					for(int z=0;z<this.missingCols.size();z++) {
						if(x == this.missingCols.get(z)) {
							newData[i][x] = missingData[z];
							insertCol++;
							break;
						}
					}
				}
			}
		}
		setData(newData);
		fireTableDataChanged();
		resetChoosenData();
	}
	/*
	 * the titles for desired ids must already be in the choosenData List
	 */
	public ArrayList<Integer> getIdsForMapping(String table) {
		ArrayList<Integer> ids = new ArrayList<Integer>();
		try {
			sub_stm = dbHandle.createStatement();
		} catch (SQLException e1) {
			System.err.println("Error on creating sub statement");
			e1.printStackTrace();
		}
		for(int i=0;i<this.choosenData.size();i++) {
			String sub_sql = "SELECT id FROM "+table+" " +
				"WHERE titel = \'"+this.choosenData.get(i)+"\'";
		
			try {
				sub_rs = sub_stm.executeQuery(sub_sql);
			} catch(SQLException e) {
				System.err.println("Error when executing Angebot Query");
				e.printStackTrace();
				closeConnection(dbHandle);
			}
			
			try {
				while(sub_rs.next()) {
					try {
						ids.add(sub_rs.getInt(1));
					} catch (SQLException e) {			
						System.err.println("Error when fetching data from Angebot title resultSet");
						closeConnection(dbHandle);
					}
				}
			} catch (SQLException e) {
				System.err.println("Error on next fkt from Angebot resultSet");
				closeConnection(dbHandle);
			}
		}
		
		return ids;
	}
	
	public void updateData(Object[] data_, int rowindex, String title) {
		if(dbHandle != null) {
			try {
				stm = dbHandle.createStatement();
			} catch (SQLException e) {
				System.err.println("Could not create Update Statement");
				closeConnection(dbHandle);
			}
			rowindex++;
			sql = "UPDATE "+title+" SET ID="+data_[0].toString()+",VORNAME=\'"+data_[1].toString()+"\',NACHNAME=\'"+
					data_[2].toString()+"\',ADRESSE=\'"+data_[3].toString()+"\',EMAIL=\'"+data_[4].toString()+"\',TELEFON=\'"+
					data_[5].toString()+"\' WHERE ROWNUM="+rowindex;
			rowindex--;
			try {
				stm.executeUpdate(sql);
			} catch (SQLException e) {
				System.err.println("Error when executing the Update Query");
				e.printStackTrace();
				closeConnection(dbHandle);
			}
		}
			
		for(int i=0;i<this.addEditColNames.length;i++) {
			this.data[rowindex][i] = data_[i];
		}
		fireTableDataChanged();
	}
}
