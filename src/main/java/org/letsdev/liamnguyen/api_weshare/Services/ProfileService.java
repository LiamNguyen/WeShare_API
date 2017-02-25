package org.letsdev.liamnguyen.api_weshare.Services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.letsdev.liamnguyen.api_weshare.Dao.ProfilesDAO;
import org.letsdev.liamnguyen.api_weshare.DatabaseManager.Database;
import org.letsdev.liamnguyen.api_weshare.Dto.Profiles;
import org.letsdev.liamnguyen.api_weshare.Dto.Users;
import org.letsdev.liamnguyen.api_weshare.Helper.Helper;

public class ProfileService {
	
	private HashMap<String, Profiles> profiles = Database.getProfile();
	
	private ProfilesDAO profilesDao = new ProfilesDAO();
	
	public ProfileService() {

	}
	
	public List<Profiles> getAllUsers() {
		return new ArrayList<Profiles>(this.profiles.values());
	}
	
	public Profiles updateUserProfile(String sessionToken, Profiles profile) {
		Users user = Helper.findUserFromToken(sessionToken);
		Profiles updatedProfile = restrictIneditableFields(user, profile);
		
		this.profilesDao.updateProfile(updatedProfile, user);
		
		return this.profiles.get(user.getUserLoginId());
	}
	
	public Profiles getUserProfile(String sessionToken) {
		Users returnUser = Helper.findUserFromToken(sessionToken);
		return this.profiles.get(returnUser.getUserLoginId());
	}
	
	private Profiles restrictIneditableFields(Users user, Profiles profile) {
		Profiles defaultInfo = this.profiles.get(user.getUserLoginId());
		
		profile.setCreatedAt(defaultInfo.getCreatedAt());
		profile.setUserID(defaultInfo.getUserID());
		profile.setUpdatedAt(new Date());
		
		return profile;
	}
}
