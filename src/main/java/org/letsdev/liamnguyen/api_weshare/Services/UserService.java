package org.letsdev.liamnguyen.api_weshare.Services;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.ForbiddenException;
import javax.ws.rs.NotAllowedException;
import javax.ws.rs.NotFoundException;

import org.letsdev.liamnguyen.api_weshare.Dao.ProfilesDAO;
import org.letsdev.liamnguyen.api_weshare.Dao.UsersDAO;
import org.letsdev.liamnguyen.api_weshare.DatabaseManager.Database;
import org.letsdev.liamnguyen.api_weshare.Dto.Users;
import org.letsdev.liamnguyen.api_weshare.Helper.Helper;
import org.letsdev.liamnguyen.api_weshare.Locale.ErrorMessageManager;
import org.letsdev.liamnguyen.api_weshare.Locale.ErrorMessages;
import org.letsdev.liamnguyen.api_weshare.ResponseBuilder.ResponseBuilder;

public class UserService {
	private HashMap<String, Users> users = Database.getUsers();

	private UsersDAO userDao = new UsersDAO();
	private ProfilesDAO profileDao = new ProfilesDAO();
	
	public UserService() {
	
	}
	
	public List<Users> getAllUsers() {
		this.userDao.selectAllUsers();
		List<Users> allUser = new ArrayList<Users>(this.users.values());
		
		if (allUser.size() == 0) {
			ErrorMessageManager errorMessage = new ErrorMessageManager(ErrorMessages.NO_USER_YET, 404);
			throw new NotFoundException(ResponseBuilder.build(errorMessage, 404));
		}
		return allUser;
	}
	
	public Users getUser(String userLoginId) {
		this.userDao.selectUser(userLoginId);
		if (this.users.get(userLoginId) == null) {
			ErrorMessageManager errorMessage = new ErrorMessageManager(ErrorMessages.USER_NOT_FOUND, 404);
			throw new NotFoundException(ResponseBuilder.build(errorMessage, 404));
		}
		return this.users.get(userLoginId);
	}
	
	public Users register(Users credential) {
		if (userHasExisted(credential.getUserLoginId())) {
			ErrorMessageManager errorMessage = new ErrorMessageManager(ErrorMessages.USER_EXISTED, 405);
			throw new NotAllowedException(ResponseBuilder.build(errorMessage, 405));
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
		Users user = Helper.findUserFromUserLoginId(userLoginId);
		if (user == null) {
			ErrorMessageManager errorMessage = new ErrorMessageManager(ErrorMessages.USER_NOT_FOUND, 404);
			throw new NotFoundException(ResponseBuilder.build(errorMessage, 404));
		}
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
			ErrorMessageManager errorMessage = new ErrorMessageManager(ErrorMessages.USER_NOT_YET_REGISTERED, 404, ErrorMessages.REGISTER_DOCUMENTATION);
			throw new NotFoundException(ResponseBuilder.build(errorMessage, 404));				
		}
		
		if (this.users.get(userLoginId).getUserPassword().equals(userPassword)) {
			return true;
		}
		
		ErrorMessageManager errorMessage = new ErrorMessageManager(ErrorMessages.INVALID_CREDENTIAL, 403);
		throw new ForbiddenException(ResponseBuilder.build(errorMessage, 403));		
	}
	
	private Boolean userHasExisted(String userLoginId) {
		this.userDao.selectUser(userLoginId);
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
