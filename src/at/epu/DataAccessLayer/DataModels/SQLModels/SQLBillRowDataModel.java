package at.epu.DataAccessLayer.DataModels.SQLModels;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import at.epu.DataAccessLayer.SQLQueryProvider;
import at.epu.DataAccessLayer.DataModels.BillRowDataModel;

public class SQLBillRowDataModel extends BillRowDataModel {
	private static final long serialVersionUID = 1259019063593769044L;
	
	public SQLBillRowDataModel(Connection databaseHandle) throws Exception {
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
		
		sql = "SELECT * FROM RECHNUNGSZEILEN";
		sql_count = "SELECT COUNT(*) FROM RECHNUNGSZEILEN";
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
						data_[x][i] = getAngebotForRechnungszeilen(rs.getInt(1));
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
	
	public String getAngebotForRechnungszeilen(int id) {
		String angebot = "";
		Statement sub_stm = null;
		ResultSet sub_rs = null;

		try {
			sub_stm = dbHandle.createStatement();
		} catch (SQLException e1) {
			System.err.println("Error on creating sub statement");
			e1.printStackTrace();
		}
		
		String sub_sql = "SELECT b.titel FROM RECHNUNGSZEILEN a, ANGEBOTE b " +
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
}
