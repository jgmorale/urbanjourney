package resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import models.Route;
import util.DatabaseUtil;
import util.DbRouteManager;

@Path("calcroute")
public class CalculateRouteResource {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String calculateRoute(String body) {
		//Parse the input response
		
        
        return "Si le pego a este recurso";
        
	}
}
