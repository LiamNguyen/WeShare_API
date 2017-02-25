package org.letsdev.liamnguyen.api_weshare.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.letsdev.liamnguyen.api_weshare.DatabaseManager.DBConnection;
import org.letsdev.liamnguyen.api_weshare.DatabaseManager.Database;
import org.letsdev.liamnguyen.api_weshare.Dto.Users;

public class UsersDAO {
	private HashMap<String, Users> users = Database.getUsers();
	
	DBConnection connection = new DBConnection();
	
	public void selectAllUsers() {
		connection.cnOpen();
		String sql = "Select UserId, UserLoginId, UserPassword, SessionToken from wesharedb.tbl_users";
		ResultSet rs = connection.getSqlData(sql);
		parseDataToDto(rs);
	}
	
	public void selectUser(String userLoginId) {
		connection.cnOpen();
		String sql = "Select UserId, UserLoginId, UserPassword, SessionToken from wesharedb.tbl_users";
		ResultSet rs = connection.getSqlData(sql);
		parseDataToDto(rs);
	}
	
	public void deleteUser(String userLoginId) {
		connection.cnOpen();
//		String sql = "Update wesharedb.tbl_users Set Active = 0 Where UserLoginId = " + userLoginId;
		
	}
	
	private void parseDataToDto(ResultSet rs) {
		try {
			while (rs.next()) {
				Users user = new Users();
				
				user.setUserId(rs.getString(1));
				user.setUserLoginId(rs.getString(2));
				user.setUserPassword(rs.getString(3));
				user.setSessionToken(rs.getString(4));

				this.users.put(user.getUserLoginId(), user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
