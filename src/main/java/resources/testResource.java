package resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

@Path("test")
public class testResource {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String testUrbanJourney(String body) {
		
		
		
		return "Si le pego a este recurso";
        
	}
}
