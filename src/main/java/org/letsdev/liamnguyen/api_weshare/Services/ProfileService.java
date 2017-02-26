package org.letsdev.liamnguyen.api_weshare.Services;

import java.util.Date;
import java.util.HashMap;

import javax.ws.rs.NotAuthorizedException;

import org.letsdev.liamnguyen.api_weshare.Dao.ProfilesDAO;
import org.letsdev.liamnguyen.api_weshare.DatabaseManager.Database;
import org.letsdev.liamnguyen.api_weshare.Dto.Profiles;
import org.letsdev.liamnguyen.api_weshare.Dto.Users;
import org.letsdev.liamnguyen.api_weshare.Helper.Helper;
import org.letsdev.liamnguyen.api_weshare.Locale.ErrorMessageManager;
import org.letsdev.liamnguyen.api_weshare.Locale.ErrorMessages;
import org.letsdev.liamnguyen.api_weshare.ResponseBuilder.ResponseBuilder;

public class ProfileService {
	
	private HashMap<String, Profiles> profiles = Database.getProfile();
	
	private ProfilesDAO profilesDao = new ProfilesDAO();
	
	public ProfileService() {

	}
	
	public Profiles updateUserProfile(String sessionToken, Profiles profile) {
		Users user = Helper.findUserFromToken(sessionToken);
		
		if (user == null) {			
			ErrorMessageManager errorMessage = new ErrorMessageManager(ErrorMessages.UNAUTHORIZE, 401, ErrorMessages.LOGIN_DOCUMENTATION);
			throw new NotAuthorizedException(ResponseBuilder.build(errorMessage, 401));
		}
		
		Profiles updatedProfile = restrictIneditableFields(user, profile);
		
		this.profilesDao.updateProfile(updatedProfile, user);
		
		return this.profiles.get(user.getUserLoginId());
	}
	
	public Profiles getUserProfile(String sessionToken) {
		Users returnUser = Helper.findUserFromToken(sessionToken);
		
		if (returnUser == null) {			
			ErrorMessageManager errorMessage = new ErrorMessageManager(ErrorMessages.UNAUTHORIZE, 401, ErrorMessages.LOGIN_DOCUMENTATION);
			throw new NotAuthorizedException(ResponseBuilder.build(errorMessage, 401));
		}
		
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
