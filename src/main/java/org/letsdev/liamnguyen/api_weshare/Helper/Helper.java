package org.letsdev.liamnguyen.api_weshare.Helper;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.letsdev.liamnguyen.api_weshare.DatabaseManager.Database;
import org.letsdev.liamnguyen.api_weshare.Dto.Users;
import org.letsdev.liamnguyen.api_weshare.Interfaces.DtoObjectsInterface;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Helper {
	private static ObjectMapper mapper = new ObjectMapper();
	private static HashMap<String, Users> users = Database.getUsers();

	public static String jacksonConvertObjToJSon(DtoObjectsInterface obj) {
		String json = "";
		try {
			json = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return json;
	}
	
	public static DtoObjectsInterface jacksonConvertJsonToObj(String json) {
		DtoObjectsInterface obj = null;
		try {
			obj = mapper.readValue(json, DtoObjectsInterface.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return obj;
	}
	
	public static Users findUserFromToken(String sessionToken) {
		Users returnUser = null;
		List<Users> allUser = new ArrayList<Users>(users.values());
		for (Users user: allUser) {
			if (user.getSessionToken().equals(sessionToken)) {
				returnUser = user;
			}
		}
		return returnUser;
	}
	
	public static Users findUserFromUserLoginId(String userLoginId) {
		Users returnUser = null;
		List<Users> allUser = new ArrayList<Users>(users.values());
		for (Users user: allUser) {
			if (user.getUserLoginId().equals(userLoginId)) {
				returnUser = user;
			}
		}
		return returnUser;
	}
	
	public static Date convertStrToDate(String dateStr) {
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss"); 
	    Date convertedDate = null;
	    try {
	    	convertedDate = df.parse(dateStr);
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
	    return convertedDate;
	}
	
	public static String getCurrentDate() {
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss"); 
	    String returnDate = null;
    	returnDate = df.format(new Date());
	    return returnDate;
	}
	
	public static void print(String msg) {
		System.out.println(msg);
	}
}
