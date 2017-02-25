package org.letsdev.liamnguyen.api_weshare.DatabaseManager;

import java.util.HashMap;

import org.letsdev.liamnguyen.api_weshare.Dto.Profiles;
import org.letsdev.liamnguyen.api_weshare.Dto.Users;

public class Database {
	private static HashMap<String, Profiles> profile = new HashMap<>();
	private static HashMap<String, Users> users = new HashMap<>();
	
	public Database() {

	}
	
	public static HashMap<String, Profiles> getProfile() {
		return profile;
	}
	
	public static HashMap<String, Users> getUsers() {
		return users;
	}
}
