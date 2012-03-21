package at.epu.DataAccessLayer.DataModels.SQLModels;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import at.epu.DataAccessLayer.SQLQueryProvider;
import at.epu.DataAccessLayer.DataModels.OfferDataModel;

public class SQLOfferDataModel extends OfferDataModel {
	private static final long serialVersionUID = 3836902474382893474L;
	
	public SQLOfferDataModel(Connection databaseHandle) throws Exception {
		sqlProvider = new SQLQueryProvider(databaseHandle);
		Object[][] data_ = sqlProvider.selectAll(this);
		
		setData(data_);
		
		/*try {
			stm = databaseHandle.createStatement();
		} catch (SQLException e) {
			System.err.println("Could not create Statement");
			closeConnection(databaseHandle);
		}
		
		setDbHandle(databaseHandle);
		
		sql = "SELECT * FROM ANGEBOTE";
		sql_count = "SELECT COUNT(*) FROM ANGEBOTE";
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
					if(i == 2) {
						data_[x][i] = getKundeForAngebot(rs.getInt(1));
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

		setData(data_);*/
	}
	
	public String getKundeForAngebot(int id) {
		String kunde = "";
		Statement sub_stm = null;
		ResultSet sub_rs = null;

		try {
			sub_stm = dbHandle.createStatement();
		} catch (SQLException e1) {
			System.err.println("Error on creating sub statement");
			e1.printStackTrace();
		}
		
		String sub_sql = "SELECT nachname FROM KUNDEN a, ANGEBOTE b " +
				"WHERE b.kunde_id = a.id " +
				"AND b.id = "+id;
		
		try {
			sub_rs = sub_stm.executeQuery(sub_sql);
		} catch(SQLException e) {
			System.err.println("Error when executing Angebot Query");
			e.printStackTrace();
		}
		
		try {
			while(sub_rs.next()) {
				try {
					kunde = sub_rs.getString(1);
				} catch (SQLException e) {			
					System.err.println("Error when fetching data from Angebot title resultSet");
				}
			}
		} catch (SQLException e) {
			System.err.println("Error on next fkt from Angebot resultSet");
		}
		
		return kunde;
	}
}
