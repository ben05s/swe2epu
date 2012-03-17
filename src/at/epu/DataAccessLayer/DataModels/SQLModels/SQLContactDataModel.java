package at.epu.DataAccessLayer.DataModels.SQLModels;

import java.sql.Connection;
import java.sql.SQLException;

import at.epu.DataAccessLayer.DataModels.ContactDataModel;

public class SQLContactDataModel extends ContactDataModel {
	private static final long serialVersionUID = -4462317932452701225L;
	
	public SQLContactDataModel(Connection databaseHandle) {
		try {
			stm = databaseHandle.createStatement();
		} catch (SQLException e) {
			System.err.println("Could not create Statement");
			closeConnection(databaseHandle);
		}
		
		setDbHandle(databaseHandle);
		
		sql = "SELECT * FROM KONTAKTE";
		sql_count = "SELECT COUNT(*) FROM KONTAKTE";
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
					try {
						data_[x][i] = rs.getString(i+1);
					} catch (SQLException e) {			
						System.err.println("Error when fetching data from resultSet");
						closeConnection(databaseHandle);
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
}
