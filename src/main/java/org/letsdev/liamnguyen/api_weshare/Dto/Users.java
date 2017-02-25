package org.letsdev.liamnguyen.api_weshare.Dto;

import java.util.Date;

import org.letsdev.liamnguyen.api_weshare.Interfaces.DtoObjectsInterface;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Users implements DtoObjectsInterface {
	private String userId;
	@JsonIgnore private String userLoginId;
	@JsonIgnore private String userPassword;
	private String sessionToken;
	@JsonIgnore private Date createdAt;
	@JsonIgnore private Date updatedAt;
	
	public Users() {
		
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserLoginId() {
		return userLoginId;
	}
	public void setUserLoginId(String userLoginId) {
		this.userLoginId = userLoginId;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getSessionToken() {
		return sessionToken;
	}
	public void setSessionToken(String sessionToken) {
		this.sessionToken = sessionToken;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
}
