package org.letsdev.liamnguyen.api_weshare.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.letsdev.liamnguyen.api_weshare.DatabaseManager.DBConnection;
import org.letsdev.liamnguyen.api_weshare.DatabaseManager.Database;
import org.letsdev.liamnguyen.api_weshare.Dto.Profiles;
import org.letsdev.liamnguyen.api_weshare.Dto.Users;
import org.letsdev.liamnguyen.api_weshare.Helper.Helper;

public class ProfilesDAO {
	private HashMap<String, Profiles> profiles = Database.getProfile();
	
	DBConnection connection = new DBConnection();
	
	public void selectProfile(String userLoginId) {
		connection.cnOpen();
		String sql = "Select p.UserId, UserFirstName, UserLastName, UserEmail, UserTwitter, UserPhone "
				+ "From wesharedb.tbl_profiles p "
				+ "Inner join wesharedb.tbl_users u "
				+ "On p.UserId = u.UserId "
				+ "Where u.UserLoginId = '" + userLoginId + "'";
		ResultSet rs = connection.getSqlData(sql);
		parseDataToDto(rs, userLoginId);
	}
	
	public void insertProfile(Users user) {
		connection.cnOpen();
		String sql = "Insert into wesharedb.tbl_profiles(UserId) "
				+ "Values('" + user.getUserId() + "')";
		connection.executeQuery(sql);
		selectProfile(user.getUserLoginId());
	}
	
	public void updateProfile(Profiles profile, Users user) {
		connection.cnOpen();
		String sql = "Update wesharedb.tbl_profiles "
				+ "Set UserFirstName = '" + profile.getUserFirstName() + "', "
					+ "UserLastName = '" + profile.getUserLastName() +"', "
					+ "UserEmail = '" + profile.getUserEmail() + "', "
					+ "UserTwitter = '" + profile.getUserTwitter() + "', "
					+ "UserPhone '" + profile.getUserPhone() + "' "
					+ "UpdatedAt '" + Helper.getCurrentDate() + "' "
				+ "Where UserId = '" + user.getUserId() + "'";
		connection.executeQuery(sql);
		selectProfile(user.getUserLoginId());
	}
	
	private void parseDataToDto(ResultSet rs, String userLoginId) {
		try {
			while (rs.next()) {
				Profiles profile = new Profiles();
				profile.setUserID(rs.getString(1));
				if (rs.getString(2) == null) {
					profile.setUserFirstName("");
				} else {
					profile.setUserFirstName(rs.getString(2));
				}
				if (rs.getString(3) == null) {
					profile.setUserLastName("");
				} else {
					profile.setUserLastName(rs.getString(3));
				}
				if (rs.getString(4) == null) {
					profile.setUserEmail("");
				} else {
					profile.setUserEmail(rs.getString(4));
				}
				if (rs.getString(5) == null) {
					profile.setUserTwitter("");
				} else {
					profile.setUserTwitter(rs.getString(5));
				}
				if (rs.getString(6) == null) {
					profile.setUserPhone("");
				} else {
					profile.setUserPhone(rs.getString(6));
				}

				this.profiles.put(userLoginId, profile);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
