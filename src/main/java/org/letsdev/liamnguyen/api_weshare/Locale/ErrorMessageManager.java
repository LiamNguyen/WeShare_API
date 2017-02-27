package org.letsdev.liamnguyen.api_weshare.Locale;

import javax.xml.bind.annotation.XmlRootElement;

import org.letsdev.liamnguyen.api_weshare.Interfaces.ResponseEntity;

@XmlRootElement
public class ErrorMessageManager implements ResponseEntity {
	private String message;
	private int statusCode;
	private String documentation;
	
	public ErrorMessageManager() {
	}
	
	public ErrorMessageManager(String message, int statusCode) {
		this.message = message;
		this.statusCode = statusCode;
		this.documentation = "http://weshare-api.documentation.com";
	}
	
	public ErrorMessageManager(String message, int statusCode, String documentation) {
		this(message, statusCode);
		this.documentation = documentation;
	}
	
	public String getmessage() {
		return message;
	}
	public void setmessage(String message) {
		this.message = message;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getDocumentation() {
		return documentation;
	}
	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}
	
	
}
