package at.epu.DataAccessLayer.DataModels.SQLModels;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import at.epu.DataAccessLayer.DataModels.BankAccountDataModel;

public class SQLBankAccountDataModel extends BankAccountDataModel {
	private static final long serialVersionUID = -8371682062900518962L;
	
	public SQLBankAccountDataModel(Connection databaseHandle) throws Exception {
		try {
			stm = databaseHandle.createStatement();
		} catch (SQLException e) {
			System.err.println("Could not create Statement");
			closeConnection(databaseHandle);
		}
		
		setDbHandle(databaseHandle);
		
		sql = "SELECT * FROM BUCHUNGSZEILEN";
		sql_count = "SELECT COUNT(*) FROM BUCHUNGSZEILEN";
		try {
			rs = stm.executeQuery(sql_count);
		} catch (SQLException e) {
			System.err.println("Error when executing the Query");
			closeConnection(databaseHandle);
		}
		
		int x = 0;
		int rowCount = 0;
		try {
			while(rs.next()) {
				rowCount = rs.getInt(1);
			}
		} catch (SQLException e1) {
			System.err.println("Error when fetching the rowcount");
			closeConnection(databaseHandle);
		}
		
		try {
			rs = stm.executeQuery(sql);
		} catch (SQLException e) {
			System.err.println("Error when executing the Query");
			closeConnection(databaseHandle);
		}
		
	
		Object[][] data_ = new Object[rowCount][getColumnCount()];
		try {
			while(rs.next()) {
				for(int i=0;i<getColumnCount();i++) {
					if(i == 6) {
						data_[x][i] = getKategorieForBuchungszeilen(rs.getInt(1));
					} else {
						try {
							data_[x][i] = rs.getString(i+1);
						} catch (SQLException e) {			
							System.err.println("Error when fetching data from resultSet");
							closeConnection(databaseHandle);
						}
					}
				}
				x++;
			}
		} catch (SQLException e) {
			System.err.println("Error on next fkt from resultSet");
			closeConnection(databaseHandle);
		}

		setData(data_);
	}
	
	public ArrayList<String> getKategorieForBuchungszeilen(int id) {
		ArrayList<String> kategorie = new ArrayList<String>();
		Statement sub_stm = null;
		ResultSet sub_rs = null;

		try {
			sub_stm = dbHandle.createStatement();
		} catch (SQLException e1) {
			System.err.println("Error on creating sub statement");
			e1.printStackTrace();
		}
		
		String sub_sql = "SELECT c.name FROM BUCHUNGSZEILEN a, KAT_MAPPING b, KATEGORIEN c " +
				"WHERE a.kat_mapping_id = b.id " +
				"AND b.kategorie_id = c.id "+
				"AND a.id = "+id;
		
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
					kategorie.add(sub_rs.getString(1));
				} catch (SQLException e) {			
					System.err.println("Error when fetching data from Angebot title resultSet");
					closeConnection(dbHandle);
				}
			}
		} catch (SQLException e) {
			System.err.println("Error on next fkt from Angebot resultSet");
			closeConnection(dbHandle);
		}
		
		return kategorie;
	}
}
