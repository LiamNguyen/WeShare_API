package org.letsdev.liamnguyen.api_weshare.Services;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.letsdev.liamnguyen.api_weshare.Dao.ProfilesDAO;
import org.letsdev.liamnguyen.api_weshare.Dao.UsersDAO;
import org.letsdev.liamnguyen.api_weshare.DatabaseManager.Database;
import org.letsdev.liamnguyen.api_weshare.Dto.Users;
import org.letsdev.liamnguyen.api_weshare.Helper.Helper;

public class UserService {
	private HashMap<String, Users> users = Database.getUsers();

	private UsersDAO userDao = new UsersDAO();
	private ProfilesDAO profileDao = new ProfilesDAO();
	
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
		Users insertedUser = Helper.findUserFromUserLoginId(credential.getUserLoginId());
		createProfile(insertedUser);
		
		return credential;
	}
	
	public Users login(Users credential) {
		String userLoginId = credential.getUserLoginId();
		String userPassword = credential.getUserPassword();
		
		if (userIsValid(userLoginId, userPassword)) {
			this.userDao.updateToken(userLoginId, tokenGenerator());
			this.profileDao.selectProfile(userLoginId);
			return this.users.get(userLoginId);
		} else {
			return null;
		}
	}
	
	public void deleteUser(String userLoginId) {
		this.userDao.deleteUser(userLoginId);
		this.users.remove(userLoginId);
	}
	
	private void createCredential(Users user) {
		user.setSessionToken(tokenGenerator());
		this.userDao.insertUser(user);
	}
	
	private void createProfile(Users user) { 
		this.profileDao.insertProfile(user);
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
		this.userDao.checkUserExistence(userLoginId);
		if (this.users.get(userLoginId) != null) {
			this.users.remove(userLoginId);
			return true;
		}
		return false;
	}
	
	private String tokenGenerator() {
		SecureRandom random = new SecureRandom();
		return new BigInteger(130, random).toString(32);
	}
}
