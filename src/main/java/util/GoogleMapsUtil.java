package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
	// TODO Logic already completed. Need to test the module.
	private static final String URL = "https://maps.googleapis.com/maps/api/directions/json?";
	private static String apiKey = "";
	
	static {
		try {
			File file = new File("C:\\Users\\Jesus\\Desktop\\API_KEY.txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
			apiKey = br.readLine();
			br.close();
		} catch (IOException e) {
			System.out.println("No se encontró el archivo de la API KEY");
			e.printStackTrace();
		}
	}
	
	public static List<String> getTimeDistSteps(String origen, String destino) {
		List<String> lista = new ArrayList<String>();
		// Crea la URI para pedir el recurso a Google Maps con base en el origen y el destino
		String url_final = URL + "origin=" + origen + "&destination" + destino + "&key=" + apiKey;
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
            
            String time = (String) jsonTime.get("value");
            String distance = (String) jsonTime.get("value");
            String timeTxt = (String) jsonTime.get("text");
            String distanceTxt = (String) jsonDist.get("text");
            String steps = jsonStepsArr.toString();
            
            // Agrega la información que nos importa a la lista
            lista.add(time);
            lista.add(distance);
            lista.add(timeTxt);
            lista.add(distanceTxt);
            lista.add(steps);
            
		} catch (Exception e) {
			e.printStackTrace();
		}
		
        // Regresa la lista
		return lista;
	}
}
