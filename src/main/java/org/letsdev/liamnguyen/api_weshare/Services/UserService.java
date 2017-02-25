package org.letsdev.liamnguyen.api_weshare.Services;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.letsdev.liamnguyen.api_weshare.Dao.UsersDAO;
import org.letsdev.liamnguyen.api_weshare.DatabaseManager.Database;
import org.letsdev.liamnguyen.api_weshare.Dto.Profiles;
import org.letsdev.liamnguyen.api_weshare.Dto.Users;

public class UserService {
	private HashMap<String, Users> users = Database.getUsers();
	private HashMap<String, Profiles> profile = Database.getProfile();

	private UsersDAO userDao = new UsersDAO();
	public UserService() {
	
	}
	
	public List<Users> getAllUsers() {
		this.userDao.selectAllUsers();
		return new ArrayList<Users>(this.users.values());
	}
	
	public Users getUser(String userLoginId) {
		this.userDao.selectUser(userLoginId);
		return this.users.get(userLoginId);
	}
	
	public Users register(Users credential) {
		if (userHasExisted(credential.getUserLoginId())) {
			return null;
		}
		createCredential(credential);
		createProfile(credential);
		
		return credential;
	}
	
	public Users login(Users credential) {
		String loginId = credential.getUserLoginId();
		String password = credential.getUserPassword();
		
		if (userIsValid(loginId, password)) {
			credential.setSessionToken(tokenGenerator());
			return this.users.get(loginId);
		} else {
			return null;
		}
	}
	
	public void deleteUser(String userLoginId) {
		Users user = this.users.get(userLoginId);
		this.users.put(userLoginId, user);
	}
	
	private void createCredential(Users credential) {
		String id = Integer.toString(this.users.size() + 1);
		credential.setUserId(id);
		credential.setSessionToken(tokenGenerator());
		this.users.put(credential.getUserLoginId(), credential);
	}
	
	private void createProfile(Users credential) {
		Profiles newUserProfile = new Profiles();
		newUserProfile.setUserID(credential.getUserId());
		this.profile.put(credential.getUserLoginId(), newUserProfile);
	}
	
	private Boolean userIsValid(String userLoginId, String userPassword) {
		this.userDao.selectUser(userLoginId);
		if (!this.users.containsKey(userLoginId)) {
			return false;
		}
		if (this.users.get(userLoginId).getUserPassword().equals(userPassword)) {
			return true;
		}
		return false;
	}
	
	private Boolean userHasExisted(String userLoginId) {
		this.userDao.selectUser(userLoginId);
		if (this.users.get(userLoginId) != null) {
			return true;
		}
		return false;
	}
	
	private String tokenGenerator() {
		SecureRandom random = new SecureRandom();
		return new BigInteger(130, random).toString(32);
	}
}
