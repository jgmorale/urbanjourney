package resources;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import models.Route;
import models.User;
import util.DatabaseUtil;
import util.DbRouteManager;
import util.RouteUtil;
import util.UserManager;

@Path("saveroute")
public class SaveRouteResource {
	// TODO agregar la condicion de que se verifique el token
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getRoutes(){
		String token = "";
		int idUsuario = 0;
		if(idUsuario == -1) {
			return new Gson().toJson("Necesita una cuenta para poder hacer esta acción");
		} else {
			// Se crea un nuevo modelo de usuario para comprobar si el token actual es valido
			User usuario = new User();
			// Se crea una conexion con la base de datos
	        Connection conn = DatabaseUtil.getConnection();
	        UserManager usrManager = new UserManager(conn);
			usuario = usrManager.getUser(idUsuario);
			if (token.equals(usuario.getToken())) {
				// Se crea una lista de rutas para almacenar las rutas del usuario
				List<Route> routes = new ArrayList<Route>();
				// Se crea un dbRouteManager para poder persistir a la BD
		        DbRouteManager dbRouteManager = new DbRouteManager(conn);
		        // Se obtienen las rutas de la BD con base en el id del usuario
		        routes = dbRouteManager.getRoutes(idUsuario);
		        
		        if(routes.size() == 0) {
		        	// Si el tamaño de la lista que tiene rutas es cero, entonces quiere decir que
		        	// no hay bases de datos guardadas. Regresa el mensaje "No hay rutas guardadas"
		        	return new Gson().toJson("No hay rutas guardadas");
		        } else {
		        	// Convierte las rutas al formato JSON que se acordó con el front end
		        	String urbnJSON = RouteUtil.urbanJourneyResp(routes);
			        // Regresa el JSON con toda la informacion para el front end
			        return urbnJSON;
		        }
			} else {
				return new Gson().toJson("Por favor vuelva a iniciar sesión");
			} 
		}
    }
	
	@GET
	@Path("/getroute")
	@Consumes(MediaType.APPLICATION_JSON)
	public String getRoute() {
		int idUsuario = 0;
		int idRuta = 0;
		String token = "";
		if(idUsuario == -1) {
			return new Gson().toJson("Necesita una cuenta para poder hacer esta acción");
		} else {
			// Se crea un nuevo modelo de usuario para comprobar si el token actual es valido
			User usuario = new User();
			// Obtiene una conexion para la BD
			Connection conn = DatabaseUtil.getConnection();
			// Se crea un usrManager para poder persistir a la tabla del usuario en la BD
			UserManager usrManager = new UserManager(conn);
			// Se obtiene el usuario por id
			usuario = usrManager.getUser(idUsuario);
			// Se valida que el token que se recibió sea igual al token que generó el sistema
			if (token.equals(usuario.getToken())) {
				if(idRuta > 0) {
					// Crea un dbRouteManager para poder persistir la BD
					DbRouteManager dbRouteManager = new DbRouteManager(conn);
					// Crea un nuevo modelo para poder guardar los datos de la BD
					Route route = new Route();
					// Obtiene la ruta deseada con base en el id de usuario y el id de la ruta
					route = dbRouteManager.getRoute(idRuta);
					// Convierte la ruta obtenida al formato JSON que se acordó en el front end
					String urbnJSON = RouteUtil.urbanJourneyResp(route);
					// Regresa la respuesta al front end
					return urbnJSON;
				} else {
					return new Gson().toJson("La ruta que eligió no es válida");
				}
			} else {
				return new Gson().toJson("Por favor vuelva a iniciar sesión");
			}
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String saveRoute(String body, @FormParam("idUsuario") int idUsuario, @FormParam("token") String token) {
		if(idUsuario == -1) {
			return new Gson().toJson("Necesita una cuenta para poder hacer esta acción");
		} else {
			User usuario = new User();
			Connection conn = DatabaseUtil.getConnection();
			UserManager usrManager = new UserManager(conn);
			usuario = usrManager.getUser(idUsuario);
			if (token.equals(usuario.getToken())) {
				// Crea un nuevo modelo de ruta para poder guardar los datos
		        Route route = new Route();
		        // Crea un dbRouteManager para poder persistir a la BD
		        DbRouteManager dbRouteManager = new DbRouteManager(conn);
		        // Convierte el JSON del request a nuestro modelo Route
		        route = RouteUtil.urbanJourneyReq(body);
		        // Inserta la ruta a la base de datos
		        dbRouteManager.insertRoute(route);
		        return new Gson().toJson("Operación realizada exitosamente");
			} else {
				return new Gson().toJson("Por favor vuelva a iniciar sesión");
			}
		}
	}
}
