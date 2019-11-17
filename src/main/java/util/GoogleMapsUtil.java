package util;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class GoogleMapsUtil {
	// Already completed
	public static final String URL = "https://maps.googleapis.com/maps/api/directions/json?";
	public static final String API_KEY = "AIzaSyAomYdf0G4S_QZ6TE6puT5x8eRcRaHXKo0";
	
	public static List<String> getTimeDistSteps(String origen, String destino) {
		List<String> lista = new ArrayList<String>();
		// Crea la URI para pedir el recurso a Google Maps con base en el origen y el destino
		String url_final = URL + "origin=" + origen + "&destination" + destino + "&key=" + API_KEY;
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(url_final);
		Invocation.Builder request = target.request(MediaType.APPLICATION_JSON);
		Response response = request.get();
		// Obtiene la respuesta como un JSON
		String jsonResponse = response.readEntity(String.class);
		
		// Obtiene la información que queremos del JSON
		JSONParser parser = new JSONParser();
        try {
        	Object obj = parser.parse(jsonResponse);
            JSONObject jb = (JSONObject) obj;

            //Read the JSON to get the info that we want
            JSONArray jsonObject1 = (JSONArray) jb.get("routes");
            JSONObject jsonObject2 = (JSONObject)jsonObject1.get(0);
            JSONArray jsonObject3 = (JSONArray)jsonObject2.get("legs");
            JSONObject jsonFinal = (JSONObject) jsonObject3.get(0);
            JSONObject jsonTime = (JSONObject) jsonFinal.get("duration");
            JSONObject jsonDist = (JSONObject) jsonFinal.get("distance");
            JSONArray jsonStepsArr = (JSONArray) jsonFinal.get("steps");
            
            String time = (String) jsonTime.get("text");
            String distance = (String) jsonDist.get("text");
            String steps = jsonStepsArr.toString();
            
            // Agrega la información que nos importa a la lista
            lista.add(time);
            lista.add(distance);
            lista.add(steps);
            
		} catch (Exception e) {
			e.printStackTrace();
		}
		
        // Regresa la lista
		return lista;
	}
}
