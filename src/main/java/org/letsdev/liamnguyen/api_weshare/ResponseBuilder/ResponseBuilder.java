package org.letsdev.liamnguyen.api_weshare.ResponseBuilder;

import javax.ws.rs.core.Response;

import org.letsdev.liamnguyen.api_weshare.Interfaces.ResponseEntity;

public class ResponseBuilder {
	
	public static Response build(ResponseEntity entity, int statusCode) {
		return Response.status(statusCode)
					.entity(entity)
					.build();
	}
}
