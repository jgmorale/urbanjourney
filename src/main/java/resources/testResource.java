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
		
		
		JSONParser parser = new JSONParser();
        try {
        	Object obj = parser.parse(body);
            JSONObject jb = (JSONObject) obj;
            JSONArray jsonObject1 = (JSONArray) jb.get("routes");
            JSONObject jsonObject2 = (JSONObject)jsonObject1.get(0);
            JSONArray jsonObject3 = (JSONArray)jsonObject2.get("legs");
            JSONObject jsonFinal = (JSONObject) jsonObject3.get(0);
            JSONObject jsonTime = (JSONObject) jsonFinal.get("duration");
            JSONObject jsonDist = (JSONObject) jsonFinal.get("distance");
            JSONArray jsonStepsArr = (JSONArray) jsonFinal.get("steps");
            String time = jsonTime.get("value").toString();
            String distance = jsonDist.get("value").toString();
            
            System.out.println(time);
            System.out.println(distance);
            System.out.println(jsonStepsArr);
            
            
            
		} catch (Exception e) {
			e.printStackTrace();
		}
		
        // Regresa la lista
		return "Si le pego a este recurso";
        
	}
}
