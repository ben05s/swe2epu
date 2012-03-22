package at.epu.DataAccessLayer;

//import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import at.epu.DataAccessLayer.DataModels.BackofficeTableModel;

//import at.epu.DataAccessLayer.DataObjects.DataObject;

public class SQLQueryProvider {

	Connection databaseHandle = null;
	Statement stm = null;
	ResultSet rs = null;
	ResultSet sub_rs = null;
	String sql = new String();
	String sql_count = new String();

	protected ArrayList<String> choosenData = new ArrayList<String>();	//will be removed after implementation of saveData() (to disable errors)
	
	
	public SQLQueryProvider(Connection dbHandle) {
		databaseHandle = dbHandle;
	}

	public Object[][] selectAll(BackofficeTableModel model) {
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
			if (model.getForeignColumns().length == 1
					&& model.getForeignColumns()[0].equals("-")) {

			} else {
				fkindex = new int[model.getForeignColumns().length];

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
					if (model.getForeignColumns().length == 1
							&& model.getForeignColumns()[0].equals("-")) {
						data_[x][i] = rs.getString(i + 1);
					} else {
						for (int c = 0; c < model.getForeignColumns().length; c++) {
							if (i == fkindex[c]) {
								data_[x][i] = getDataFromForeignKey(
										rs.getInt(1),
										model.getTableName(),
										model.getMappingTableName()[c],
										model.getForeignTableName()[c],
										model.getForeignColumns()[c],
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
	 * this implementation currently only works for kontakte - tab
	 */
	public void saveData(BackofficeTableModel model, Object[] data_)
			throws SQLException {
		int next_id = getNextIdForTable(model.getTableName());

		String valueString = new String();
		for (int i = 0; i < data_.length; i++) {
			valueString += "\'" + data_[i].toString() + "\'";
			if (i != data_.length - 1) {
				valueString += ",";
			}
		}

		String sql = "INSERT INTO " + model.getTableName() + " VALUES ("
				+ next_id + "," + valueString + ")";

		try {
			stm = databaseHandle.createStatement();
			stm.executeUpdate(sql);
		} catch (SQLException e1) {
			System.err.println("Error when executing the Save Query");
			e1.printStackTrace();
		}
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
		ArrayList<Object> data = new ArrayList<Object>();

		try {
			if (mappingTableName.equals("-")) {
				sql = "SELECT b." + desiredColFromForeignKey + " FROM "
						+ mainTableName + " a, " + foreignTableName + " b "
						+ "WHERE a." + foreignKeyColumn + " = b.id "
						+ "AND a.id = " + id;
			} else {
				sql = "SELECT c." + desiredColFromForeignKey + " FROM "
						+ mainTableName + " a, " + mappingTableName + " b, "
						+ foreignTableName + " c " + "WHERE a."
						+ foreignKeyColumn + " = b.id " + "AND b."
						+ foreignKeyFromMappingCol + " = c.id " + "AND a.id = "
						+ id;
			}

			sub_rs = stm.executeQuery(sql);

			while (sub_rs.next()) {
				data.add(sub_rs.getObject(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return data;
	}

	public int getNextIdForTable(String tableName) {
		int next_id = 0;
		sql = "SELECT MAX(id) FROM " + tableName;

		try {
			stm = databaseHandle.createStatement();
			rs = stm.executeQuery(sql);
			rs.next();
			next_id = rs.getInt(1) + 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return next_id;
	}

	public void insertDataIntoMappingTable(ArrayList<Integer> ids_,
			String table_, int id_) {
		for (int i = 0; i < this.choosenData.size(); i++) {
			sql = "INSERT INTO " + table_ + " VALUES(" + id_ + ", "
					+ ids_.get(i) + ")";

			try {
				stm.executeUpdate(sql);
			} catch (SQLException e1) {
				e1.printStackTrace();
				System.exit(1);
			}
		}
	}

	public void insertDataIntoKontakte(Object[] data_, String title) {
		int id_ = 0;
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
		// getNextIdForTable(table);

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

	public boolean checkIdValidation(String table, int id_) {
		sql = "SELECT COUNT(*) FROM " + table + " WHERE id = " + id_;

		try {
			rs = stm.executeQuery(sql);
		
			rs.next();
			if (rs.getInt(1) == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return false;
	}

	public int getIdForKunde(String table, String kunde) {
		int id_ = 0;
		String sub_sql = "SELECT id FROM " + table + " "
				+ "WHERE nachname = \'" + kunde + "\'";

		try {
			stm = databaseHandle.createStatement();
		
			sub_rs = stm.executeQuery(sub_sql);

			while (sub_rs.next()) {
				id_ = sub_rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		return id_;
	}

	public void insertDataIntoAngebote(Object[] data_, String table) {
		int id = 0;
		// getNextIdForTable(table);

		// enter this if container only if some mapping or id validation has to
		// be done
		if (this.choosenData.size() != 0) {
			String table_ = "KUNDEN";
			int kunde_id = getIdForKunde(table_, this.choosenData.get(0));

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
	}

	/*
	 * the titles for desired ids must already be in the choosenData List
	 */
	public ArrayList<Integer> getIdsForMapping(String table) {
		ArrayList<Integer> ids = new ArrayList<Integer>();
		try {
			stm = databaseHandle.createStatement();
		
			for(int i=0;i<this.choosenData.size();i++) {
				String sub_sql = "SELECT id FROM "+table+" " +
						"WHERE titel = \'"+this.choosenData.get(i)+"\'";
		
				sub_rs = stm.executeQuery(sub_sql);
		
				while(sub_rs.next()) {
					ids.add(sub_rs.getInt(1));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		return ids;
	}
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