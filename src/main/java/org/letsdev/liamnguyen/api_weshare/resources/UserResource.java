package org.letsdev.liamnguyen.api_weshare.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.letsdev.liamnguyen.api_weshare.Dto.Users;
import org.letsdev.liamnguyen.api_weshare.Helper.Helper;
import org.letsdev.liamnguyen.api_weshare.service.UserService;

@Path("user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

	UserService authenticationService = new UserService();
	
	@GET
	public List<Users> getAllUser() {
		return this.authenticationService.getAllUsers(); 
	}
	
	@GET 
	@Path("/{userLoginId}")
	public Users getUser(@PathParam("userLoginId") String userLoginId) {
		return this.authenticationService.getUser(userLoginId);
	}
	
	@POST
	@Path("/register")
	public Users register(Users credential) {
		return this.authenticationService.register(credential);
	}
	
	@POST
	@Path("/login")
	public String login(Users credential) {
		Users user = this.authenticationService.login(credential);
		return Helper.jacksonConvertObjToJSon(user);
	}
	
	@DELETE
	@Path("/{userLoginId}")
	public void deleteUser(@PathParam("userLoginId") String userLoginId){
		this.authenticationService.deleteUser(userLoginId);
	}
}
