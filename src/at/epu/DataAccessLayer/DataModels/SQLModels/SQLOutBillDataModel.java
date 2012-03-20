package at.epu.DataAccessLayer.DataModels.SQLModels;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import at.epu.DataAccessLayer.SQLQueryProvider;
import at.epu.DataAccessLayer.DataModels.OutBillDataModel;

public class SQLOutBillDataModel extends OutBillDataModel {
	private static final long serialVersionUID = -2612819716366533584L;
	
	public SQLOutBillDataModel(Connection databaseHandle) throws Exception {
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
		
		sql = "SELECT * FROM AUSGANGSRECHNUNGEN";
		sql_count = "SELECT COUNT(*) FROM AUSGANGSRECHNUNGEN";
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
					if(i == 1) {
						data_[x][i] = getKundeForAusgangsrechnung(rs.getInt(1));
					} else if(i == 2) {
						data_[x][i] = getRzeileCountForAusgangsrechnung(rs.getInt(1));
					} else if(i == 3) {
						data_[x][i] = getBzeileCountForAusgangsrechnung(rs.getInt(1));
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
	
	public String getKundeForAusgangsrechnung(int id) {
		String kunde = "";
		Statement sub_stm = null;
		ResultSet sub_rs = null;

		try {
			sub_stm = dbHandle.createStatement();
		} catch (SQLException e1) {
			System.err.println("Error on creating sub statement");
			e1.printStackTrace();
		}
		
		String sub_sql = "SELECT b.nachname FROM AUSGANGSRECHNUNGEN a, KUNDEN b " +
				"WHERE a.kunde_id = b.id " +
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
					kunde = sub_rs.getString(1);
				} catch (SQLException e) {			
					System.err.println("Error when fetching data from Angebot title resultSet");
					closeConnection(dbHandle);
				}
			}
		} catch (SQLException e) {
			System.err.println("Error on next fkt from Angebot resultSet");
			closeConnection(dbHandle);
		}
		
		return kunde;*/
	}
	
	public int getRzeileCountForAusgangsrechnung(int id) {
		int count = 0;
		Statement sub_stm = null;
		ResultSet sub_rs = null;
		
		try {
			sub_stm = dbHandle.createStatement();
		} catch (SQLException e1) {
			System.err.println("Error on creating sub statement");
			e1.printStackTrace();
		}
		
		String sub_sql = "SELECT COUNT(*) FROM ausgangsrechnungen a, rzeilen_mapping b, rechnungszeilen c "+
						 "WHERE a.rzeile_mapping_id = b.id "+
						 "AND b.rechnungszeile_id = c.id "+
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
					count = sub_rs.getInt(1);
				} catch (SQLException e) {			
					System.err.println("Error when fetching data from Angebot title resultSet");
				}
			}
		} catch (SQLException e) {
			System.err.println("Error on next fkt from Angebot resultSet");
		}
		
		return count;
	}
	
	public int getBzeileCountForAusgangsrechnung(int id) {
		int count = 0;
		Statement sub_stm = null;
		ResultSet sub_rs = null;
		
		try {
			sub_stm = dbHandle.createStatement();
		} catch (SQLException e1) {
			System.err.println("Error on creating sub statement");
			e1.printStackTrace();
		}
		
		String sub_sql = "SELECT COUNT(*) FROM ausgangsrechnungen a, bzeilen_mapping b, buchungszeilen c "+
						 "WHERE a.bzeile_mapping_id = b.id "+
						 "AND b.buchungszeile_id = c.id "+
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
					count = sub_rs.getInt(1);
				} catch (SQLException e) {			
					System.err.println("Error when fetching data from Angebot title resultSet");
				}
			}
		} catch (SQLException e) {
			System.err.println("Error on next fkt from Angebot resultSet");
		}
		
		return count;
	}
}
