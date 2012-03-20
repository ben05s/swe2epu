package at.epu.DataAccessLayer.DataModels.SQLModels;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import at.epu.DataAccessLayer.SQLQueryProvider;
import at.epu.DataAccessLayer.DataModels.ProjectDataModel;

public class SQLProjectDataModel extends ProjectDataModel {
	private static final long serialVersionUID = -6826087926269956822L;
	
	public SQLProjectDataModel(Connection databaseHandle) throws Exception {
		SQLQueryProvider sqlProvider = new SQLQueryProvider();
		Object[][] data_ = sqlProvider.selectAll(this, databaseHandle);
		
		setData(data_);
		
		/*try {
			stm = databaseHandle.createStatement();
		} catch (SQLException e) {
			System.err.println("Could not create Statement");
			closeConnection(databaseHandle);
		}
		
		setDbHandle(databaseHandle);
		
		sql = "SELECT * FROM PROJEKTE";
		sql_count = "SELECT COUNT(*) FROM PROJEKTE";
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
						data_[x][i] = getAngebotForProjekt(rs.getInt(1));
					} else if(i == 3) {
						data_[x][i] = getAusgangsrechnungenForProjekt(rs.getInt(1));
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
	
	public String getAngebotForProjekt(int id) {
		String angebot = "";
		Statement sub_stm = null;
		ResultSet sub_rs = null;

		try {
			sub_stm = dbHandle.createStatement();
		} catch (SQLException e1) {
			System.err.println("Error on creating sub statement");
			e1.printStackTrace();
		}
		
		String sub_sql = "SELECT b.titel FROM PROJEKTE a, ANGEBOTE b " +
				"WHERE a.angebot_id = b.id " +
				"AND a.id = "+id;
		
		try {
			sub_rs = sub_stm.executeQuery(sub_sql);
		} catch(SQLException e) {
			System.err.println("Error when executing Angebot Query");
			e.printStackTrace();
		}
		
		try {
			while(sub_rs.next()) {
				try {
					angebot = sub_rs.getString(1);
				} catch (SQLException e) {			
					System.err.println("Error when fetching data from Angebot title resultSet");
				}
			}
		} catch (SQLException e) {
			System.err.println("Error on next fkt from Angebot resultSet");
		}
		
		return angebot;
	}
	
	public ArrayList<String> getAusgangsrechnungenForProjekt(int id) {
		ArrayList<String> angebot = new ArrayList<String>();
		Statement sub_stm = null;
		ResultSet sub_rs = null;

		try {
			sub_stm = dbHandle.createStatement();
		} catch (SQLException e1) {
			System.err.println("Error on creating sub statement");
			e1.printStackTrace();
		}
		
		String sub_sql = "SELECT c.id FROM projekte a, ausgangsrechnungen_mapping b, ausgangsrechnungen c "+
						 "WHERE a.ausgr_mapping_id = b.id "+
						 "AND b.ausgangsrechnung_id = c.id "+
						 "AND a.id = "+id;
		
		try {
			sub_rs = sub_stm.executeQuery(sub_sql);
		} catch(SQLException e) {
			System.err.println("Error when executing Angebot Query");
			e.printStackTrace();
		}
		
		try {
			while(sub_rs.next()) {
				try {
					angebot.add(sub_rs.getString(1));
				} catch (SQLException e) {			
					System.err.println("Error when fetching data from Angebot title resultSet");
				}
			}
		} catch (SQLException e) {
			System.err.println("Error on next fkt from Angebot resultSet");
		}
		
		return angebot;
	}
}
