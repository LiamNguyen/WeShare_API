package org.letsdev.liamnguyen.api_weshare.DatabaseManager;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {
	
	private String DB_URL_CONNECTION = "jdbc:mysql://localhost:3306/wesharedb";
	private String DB_DRIVER = "com.mysql.jdbc.Driver";
	private String DB_USERNAME = "phucng";
	private String DB_PASSWORD = "ThanhTruc1208";
	public static Connection connection;
	
	public void cnOpen() {
		try {
			if (DBConnection.connection == null) {
	            Driver driver = (Driver) Class.forName(DB_DRIVER).newInstance();
	            DriverManager.registerDriver(driver);
	            
				DBConnection.connection = DriverManager.getConnection(DB_URL_CONNECTION, DB_USERNAME, DB_PASSWORD);
			}
		} catch(Exception exc) {
			exc.printStackTrace();
		}
	}
	
	public ResultSet getSqlData(String sql) {
		ResultSet returnResultSet = null;
		try {
			PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
			returnResultSet = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return returnResultSet;
	}
	
	public void executeQuery(String sql) {
		
		try {
			PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
