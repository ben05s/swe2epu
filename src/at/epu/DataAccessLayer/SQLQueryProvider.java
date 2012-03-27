package at.epu.DataAccessLayer;

//import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import at.epu.DataAccessLayer.DataModels.BackofficeTableModel;

//import at.epu.DataAccessLayer.DataObjects.DataObject;

public class SQLQueryProvider {

	Connection databaseHandle = null;

	PreparedStatement insertMainTable = null;
	PreparedStatement insertMappingTable = null;
	String sql = new String();
	String sql_count = new String();

	protected ArrayList<String> choosenData = new ArrayList<String>();	//will be removed after implementation of saveData() (to disable errors)
	
	
	public SQLQueryProvider(Connection dbHandle) {
		databaseHandle = dbHandle;
	}

	public Object[][] selectAll(BackofficeTableModel model) {
		Statement stm = null;
		ResultSet rs = null;
		
		Object[][] data_ = null;

		try {
			stm = databaseHandle.createStatement();
			sql = "SELECT * FROM " + model.getTableName();
			sql_count = "SELECT COUNT(*) FROM " + model.getTableName();

			rs = stm.executeQuery(sql_count);

			int x = 0;
			int rowCount = 0;
			int colCount = model.getColumnCount();
			while (rs.next()) {
				rowCount = rs.getInt(1);
			}

			rs = stm.executeQuery(sql);

			int counter = 0;
			int[] fkindex = null;

			// determin if there are foreign keys in the table to be replaced
			// with real data
			if (model.getForeignKeyColumns().length == 1
					&& model.getForeignKeyColumns()[0].equals("-")) {

			} else {
				fkindex = new int[model.getForeignKeyColumns().length];

				for (int z = 0; z < model.getColumnCount(); z++) {
					if (model.getForeignTableColumns().length == counter) {
						break;
					}
					if (model.getColumnNames()[z].equals(model
							.getForeignTableColumns()[counter])) {
						fkindex[counter] = z;
						counter++;
					}
				}

			}

			data_ = new Object[rowCount][colCount];

			// fill the data array
			while (rs.next()) {
				for (int i = 0; i < colCount; i++) {
					if (model.getForeignKeyColumns().length == 1
							&& model.getForeignKeyColumns()[0].equals("-")) {
						data_[x][i] = rs.getString(i + 1);
					} else {
						for (int c = 0; c < model.getForeignKeyColumns().length; c++) {
							if (i == fkindex[c]) {
								data_[x][i] = getDataFromForeignKey(
										rs.getInt(1),
										model.getTableName(),
										model.getMappingTableName()[c],
										model.getForeignTableName()[c],
										model.getForeignKeyColumns()[c],
										model.getForeignKeyNameFromMappingCol()[c],
										model.getDesiredColFromForeignKey()[c]);
								break;
							} else {
								data_[x][i] = rs.getString(i + 1);
							}
						}
					}
				}
				x++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(1);
		}

		return data_;
	}

	/*
	 * works for kontakte/angebote
	 * TODO: multiple choose selection
	 * TODO: more then one choosable data array
	 */
	public Object[] setupArrayForInsert(BackofficeTableModel model, Object[] data_) {
		int col = model.getColumnCount();
		int insertCol = 0;
		Object[] newData = new Object[col];
		
		for(int x=0;x<col;x++) {
			//insert data into new row from the auswählen function 
			//up to this point there was a placeholder in the data_[] array. now fill it with the choosen data!
			for(int c = 0; c < model.getChoosenData().size(); c++) {
				//check if current label is from "auswählen" function (= here is a foreign key in the table)
				if(x == model.getChooseIndex().get(c)) {
					data_[x] = getIdForForeignKey(model.getChoosenData().get(0).toString(),
												  model.getForeignTableName()[c], 
												  model.getDesiredColFromForeignKey()[c]);
				}
			}
				
			newData[x] = data_[x-insertCol];
				
			//insert data into array where the add/edit function does not allow you to put data(id for example)
			for(int z=0;z<model.getMissingCols().size();z++) {
				//check if current col is a missing col in add/edit fkt
				if(x == model.getMissingCols().get(z)) {
					if(x == 0) {
						newData[x] = getNextIdForTable(model.getTableName());
					} else {
						/*if(model.getTableName().equals("Ausgangsrechnungen")) {
							newData[x] = null;
						}
						if(model.getTableName().equals("Rechnungszeilen")) {
							newData[x] = null;
						}*/
					}
					insertCol++;
					break;
				}
			}
			
		}
		return newData;
	}
	
	/*
	 * this implementation currently only works for kontakte - tab
	 */
	public void saveData(BackofficeTableModel model, Object[] data_)
			throws SQLException {
		int col = model.getColumnCount();
		ArrayList<String> metaData = new ArrayList<String>();
		Object[] newData = new Object[col];
		
		//get MetaData of Current Table
		metaData = getMetaData(model.getTableName());
		
		String valueString = new String();	

		newData = setupArrayForInsert(model, data_);
			
		for(int i = 0; i < col; i++) {
			valueString += "? ";
			if (i != col - 1) {
				valueString += ",";
			}
		}

		String sql = "INSERT INTO " + model.getTableName() + " VALUES ("
				+ valueString + ")";
		
		insertMainTable = databaseHandle.prepareStatement(sql);
		
		for(int y = 0; y < col; y++) {
			if(! newData[y].toString().isEmpty()) {
				if(metaData.get(y).equals("String")) {
					insertMainTable.setString(y+1, newData[y].toString());
				}
				if(metaData.get(y).equals("int")) {
					insertMainTable.setInt(y+1, Integer.parseInt(newData[y].toString()));
				}	
				if(metaData.get(y).equals("double")) {
					insertMainTable.setDouble(y+1, Double.parseDouble(newData[y].toString()));
				}	
			} else {
				try {
					throw new Exception("Empty Values are not accepted");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				/*
				if(metaData.get(y).equals("String")) {
					insertMainTable.setNull(y+1, java.sql.Types.VARCHAR);
				}
				if(metaData.get(y).equals("int")) {
					insertMainTable.setNull(y+1, java.sql.Types.INTEGER);	
				}	
				if(metaData.get(y).equals("double")) {
					insertMainTable.setNull(y+1, java.sql.Types.DECIMAL);
				}*/
			}
		}
		
		insertMainTable.executeUpdate();
	}
	
	public void updateData(BackofficeTableModel model, Object[] data_,
			int rowindex) {
		/*
		 * if(dbHandle != null) { try { stm = dbHandle.createStatement(); }
		 * catch (SQLException e) {
		 * System.err.println("Could not create Update Statement"); }
		 * rowindex++; sql =
		 * "UPDATE "+title+" SET ID="+data_[0].toString()+",VORNAME=\'"
		 * +data_[1].toString()+"\',NACHNAME=\'"+
		 * data_[2].toString()+"\',ADRESSE=\'"
		 * +data_[3].toString()+"\',EMAIL=\'"+
		 * data_[4].toString()+"\',TELEFON=\'"+
		 * data_[5].toString()+"\' WHERE ROWNUM="+rowindex; rowindex--; try {
		 * stm.executeUpdate(sql); } catch (SQLException e) {
		 * System.err.println("Error when executing the Update Query");
		 * e.printStackTrace(); } }
		 */
	}
	
	public void deleteData(BackofficeTableModel model, Object[] data_,
			int rowindex) {
		
	}
	
	public ArrayList<Object> getDataFromForeignKey(int id,
			String mainTableName, String mappingTableName,
			String foreignTableName, String foreignKeyColumn,
			String foreignKeyFromMappingCol, String desiredColFromForeignKey) {
		
		Statement stm_ = null;
		ResultSet rs_ = null;
		String sql_ = null;
	
		ArrayList<Object> data = new ArrayList<Object>();

		try {
			if (mappingTableName.equals("-")) {
				sql_ = "SELECT b." + desiredColFromForeignKey + " FROM "
						+ mainTableName + " a, " + foreignTableName + " b "
						+ "WHERE a." + foreignKeyColumn + " = b.id "
						+ "AND a.id = " + id;
			} else {
				sql_ = "SELECT c." + desiredColFromForeignKey + " FROM "
						+ mainTableName + " a, " + mappingTableName + " b, "
						+ foreignTableName + " c " + "WHERE a."
						+ foreignKeyColumn + " = b.id " + "AND b."
						+ foreignKeyFromMappingCol + " = c.id " + "AND a.id = "
						+ id;
			}
			
			stm_ = databaseHandle.createStatement();
			rs_ = stm_.executeQuery(sql_);

			while (rs_.next()) {
				data.add(rs_.getObject(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return data;
	}

	public ArrayList<String> getMetaData(String tableName) {
		Statement stm = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		int col = 0;
		ArrayList<String> metaData = new ArrayList<String>();
		
		try {
			stm = databaseHandle.createStatement();
			rs = stm.executeQuery("SELECT * FROM " + tableName);
			rsmd = rs.getMetaData();
			col = rsmd.getColumnCount();
			for(int i = 0; i < col; i++) {
				metaData.add(rsmd.getColumnTypeName(i+1));
				if(metaData.get(i).equals("VARCHAR")) { 
					metaData.set(i, "String");
				}
				if(metaData.get(i).equals("INTEGER")) { 
					metaData.set(i, "int");
				}
				if(metaData.get(i).equals("DECIMAL")) { 
					metaData.set(i, "double");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		return metaData;
	}
	
	public int getNextIdForTable(String tableName) {
		Statement stm_ = null;
		ResultSet rs_ = null;
		String sql_ = null;
		int next_id = 0;
		
		sql_ = "SELECT MAX(id) FROM " + tableName;

		try {
			stm_ = databaseHandle.createStatement();
			rs_ = stm_.executeQuery(sql_);
			rs_.next();
			next_id = rs_.getInt(1) + 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return next_id;
	}
	
	public int getIdForForeignKey(String data, String foreignTableName, String desiredColName) {
		Statement stm_ = null;
		ResultSet rs_ = null;
		String sql_ = null; 
		int id_ = 0;
		
		sql_ = "SELECT id FROM " + foreignTableName + " "
				+ "WHERE "+ desiredColName + " = \'" + data.toString() + "\'";

		try {
			stm_ = databaseHandle.createStatement();
			rs_ = stm_.executeQuery(sql_);
		
			rs_.next();
			
			id_ = rs_.getInt(1);
		
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		return id_;
	}
	
	/*
	 * the titles for desired ids must already be in the choosenData List
	 */
	public ArrayList<Integer> getIdsForMapping(String table) {
		Statement stm = null;
		ResultSet rs = null;
		ArrayList<Integer> ids = new ArrayList<Integer>();
		try {
			stm = databaseHandle.createStatement();
		
			for(int i=0;i<this.choosenData.size();i++) {
				String sub_sql = "SELECT id FROM "+table+" " +
						"WHERE titel = \'"+this.choosenData.get(i)+"\'";
		
				rs = stm.executeQuery(sub_sql);
		
				while(rs.next()) {
					ids.add(rs.getInt(1));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		return ids;
	}
	
	public boolean checkIdValidation(String tableName_, int id_) {
		Statement stm_ = null;
		ResultSet rs_ = null;
		String sql_ = null;
	
		sql_ = "SELECT COUNT(*) FROM " + tableName_ + " WHERE id = " + id_;

		try {
			stm_ = databaseHandle.createStatement();
			rs_ = stm_.executeQuery(sql_);
		
			rs_.next();
			if (rs_.getInt(1) == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return false;
	}

	public void insertDataIntoMappingTable(ArrayList<Integer> ids_,
			String tableName_, int next_id_) {
		Statement stm_ = null;
		String sql_ = null;
		for (int i = 0; i < this.choosenData.size(); i++) {
			sql_ = "INSERT INTO " + tableName_ + " VALUES(" + next_id_ + ", "
					+ ids_.get(i) + ")";

			try {
				stm_ = databaseHandle.createStatement();
				stm_.executeUpdate(sql_);
			} catch (SQLException e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
	}

	public void insertDataIntoKontakte(Object[] data_, String title) {
		int id_ = 0;
		Statement stm = null;
		try {
			stm = databaseHandle.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// getNextIdForTable(title);

		sql = "INSERT INTO " + title + " VALUES(" + id_ + ",\'"
				+ data_[0].toString() + "\',\'" + data_[1].toString() + "\',\'"
				+ data_[2].toString() + "\',\'" + data_[3].toString() + "\',\'"
				+ data_[4].toString() + "\')";

		try {
			stm.executeUpdate(sql);
		} catch (SQLException e1) {
			e1.printStackTrace();
			System.exit(1);
		}
	}

	public void insertDataIntoKunden(Object[] data_, String table) {
		int id = 0;
		Statement stm = null;
		// getNextIdForTable(table);
		try {
			stm = databaseHandle.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// enter this if container only if some mapping has to be done
		if (this.choosenData.size() != 0) {
			String mapping_table = "ANGEBOTE_MAPPING";
			int mapping_id = 0;
			// getNextIdForTable(mapping_table);

			insertDataIntoMappingTable(getIdsForMapping("ANGEBOTE"),
					mapping_table, mapping_id);

			sql = "INSERT INTO KUNDEN VALUES(" + id + ",\'"
					+ data_[0].toString() + "\',\'" + data_[1].toString()
					+ "\',\'" + data_[2].toString() + "\',\'"
					+ data_[3].toString() + "\',\'" + data_[4].toString()
					+ "\',\'" + data_[5].toString() + "\'," + mapping_id + ")";
		} else {
			sql = "INSERT INTO KUNDEN VALUES(" + id + ",\'"
					+ data_[0].toString() + "\',\'" + data_[1].toString()
					+ "\',\'" + data_[2].toString() + "\',\'"
					+ data_[3].toString() + "\',\'" + data_[4].toString()
					+ "\',\'" + data_[5].toString() + "\'," + null + ")";
		}

		try {
			stm.executeUpdate(sql);
		} catch (SQLException e1) {
			e1.printStackTrace();
			System.exit(1);
		}
	}
/*
	public void insertDataIntoAngebote(Object[] data_, String table) {
		Statement stm = null;
		ResultSet rs = null;
		int id = 0;
		// getNextIdForTable(table);

		// enter this if container only if some mapping or id validation has to
		// be done
		if (this.choosenData.size() != 0) {
			String table_ = "KUNDEN";
			//int kunde_id = getIdForKunde(table_, this.choosenData.get(0));

			if (!checkIdValidation(table_, kunde_id)) {
				System.err
						.println("There is no Primary Key to reference for Selected Data");
			}
			System.out.println(data_[0].toString() + ", " + data_[1].toString()
					+ ", " + data_[2].toString() + ", " + data_[3].toString()
					+ ", " + data_[4].toString() + ", " + data_[5].toString());
			sql = "INSERT INTO ANGEBOTE VALUES(" + id + ",\'"
					+ data_[0].toString() + "\'," + kunde_id + ",\'"
					+ Integer.parseInt(data_[2].toString()) + "\',\'"
					+ Integer.parseInt(data_[3].toString()) + "\',\'"
					+ Integer.parseInt(data_[4].toString()) + "\',\'"
					+ Integer.parseInt(data_[5].toString()) + "\')";
		} else {

			System.out.println(data_[0].toString() + ", " + data_[1].toString()
					+ ", " + data_[2].toString() + ", " + data_[3].toString()
					+ ", " + data_[4].toString() + ", " + data_[5].toString());
			sql = "INSERT INTO ANGEBOTE VALUES(" + id + ",\'"
					+ data_[0].toString() + "\'," + null + "," + data_[2] + ","
					+ data_[3] + ",\'" + data_[4].toString() + "\'," + data_[5]
					+ ")";
		}

		try {
			stm = databaseHandle.createStatement();
		
		} catch (SQLException e1) {
			e1.printStackTrace();
			System.exit(1);
		}
	}*/
}

/*
 * Class<? extends DataObject> objClass = obj.getClass();
 * 
 * Field[] fields = objClass.getFields();
 * 
 * String valueString = new String();
 * 
 * for(int i = 0; i < fields.length; i++) { Object property = null;
 * 
 * try { property = fields[i].get(obj); } catch (IllegalArgumentException e) {
 * e.printStackTrace(); throw new SQLException("Reflection failure."); } catch
 * (IllegalAccessException e) { e.printStackTrace(); throw new
 * SQLException("Reflection failure."); }
 * 
 * if(property.getClass() == String.class) { valueString += "\'" +
 * property.toString() + "\'";
 * 
 * if(i != fields.length - 1) { valueString += ","; } } }
 */