package util;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import models.Route;

public class RouteUtil {
	public RouteUtil() {}
	
	public static String RouteToJSON(Route route) {
		return "";
	}
	
	public static Route JSONToRoute(String string) {
		Route route = new Route();
		
		return route;
	}
	
	public static Route urbanJourneyReq(String req) {
		// TODO implementar funcion
		Route route = new Route();
		
		return route;
	}
	
	public static String urbanJourneyResp(Route route) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("nombre", route.getNombre());
		
		return jsonObject.toString();
	}
	
	public static String urbanJourneyResp(List<Route> routes) {
		// Crea un arreglo de Strings para convertir las rutas a JSON
    	List<String> routesJSON = new ArrayList<String>();
        
        // Convierte cada una de las rutas a formato JSON
        for(Route r: routes) {
        	//routesJSON.add(RouteUtil.urbanJourneyResp(r));
        }
        
        //Convierte la lista de rutasJSON a un solo JSON
        String jsonString = new Gson().toJson(routesJSON);
        // Falta darle el formato que queremos para lo que vamos a regresar al front end
		return "";
	}
	
	
}
