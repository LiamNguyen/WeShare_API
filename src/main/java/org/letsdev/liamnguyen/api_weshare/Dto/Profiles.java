package org.letsdev.liamnguyen.api_weshare.Dto;

import java.util.Date;

import org.letsdev.liamnguyen.api_weshare.Interfaces.DtoObjectsInterface;

public class Profiles implements DtoObjectsInterface {
	private String id;
	private String userID;
	private String userFirstName;
	private String userLastName;
	private String userEmail;
	private String userTwitter;
	private String userPhone;
	private Date createdAt;
	private Date updatedAt;
	
	public Profiles() {
		this.createdAt = new Date();
		this.userEmail = "";
		this.userTwitter = "";
		this.userFirstName = "";
		this.userLastName = "";
		this.userPhone = "";
	}
 	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserFirstName() {
		return userFirstName;
	}
	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}
	public String getUserLastName() {
		return userLastName;
	}
	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserTwitter() {
		return userTwitter;
	}
	public void setUserTwitter(String userTwitter) {
		this.userTwitter = userTwitter;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
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
