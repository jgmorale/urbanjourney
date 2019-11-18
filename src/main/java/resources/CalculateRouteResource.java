package resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import models.Route;
import util.DatabaseUtil;
import util.DbRouteManager;
import util.GoogleMapsUtil;

@Path("calcroute")
public class CalculateRouteResource {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String calculateRoute(String body) {
		//Parse the input response
		
		// Aqui va a ir la logica de qué debe de pasar cuando se añade un nuevo lugar a la ruta o cuando se elimina uno
		
        return "Si le pego a este recurso";
        
	}
}
