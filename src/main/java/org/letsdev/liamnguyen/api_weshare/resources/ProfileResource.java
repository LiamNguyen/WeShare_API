package org.letsdev.liamnguyen.api_weshare.resources;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.letsdev.liamnguyen.api_weshare.Dto.Profiles;
import org.letsdev.liamnguyen.api_weshare.Services.ProfileService;

@Path("user/profile")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProfileResource {

	ProfileService profileService = new ProfileService();
	
	@GET
	public Profiles getUserProfile(@HeaderParam("Authorization") String sessionToken) {
		return profileService.getUserProfile(sessionToken);
	}
	
	@PUT
	public Profiles updateUserProfile(@HeaderParam("Authorization") String sessionToken, Profiles profile) {
		return profileService.updateUserProfile(sessionToken, profile);
	}
}
