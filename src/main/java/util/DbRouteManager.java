package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Place;
import models.Route;
import models.Trajectory;

public class DbRouteManager {
	private Connection conn;
	
	public DbRouteManager(Connection conn) {
		this.conn = conn;
	}
	
	//TODO this class does the CRUD operations to have persistence in the DB
	
	public int insertRoute(Route route) {
		// TODO arreglar lo de la conversion de tiempo
		int result = -1;
		
		try {
			String query_ruta = "INSERT INTO ruta(id_usuario, nombre, num_lugares, duracion, distancia, status) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement stmnt_ruta = conn.prepareStatement(query_ruta);
			
			// Obtiene los valores de una ruta y los pone en un stmnt para mandarlos en un query de sql
			stmnt_ruta.setInt(1, route.getId_usuario());
			stmnt_ruta.setString(2, route.getNombre());
			stmnt_ruta.setInt(3, route.getNum_lugares());
			//stmnt_ruta.setDate(4,(Date)route.getDuracion()); //Arreglar esto
			stmnt_ruta.setFloat(5, route.getDistancia());
			stmnt_ruta.setString(6, route.getStatus());
			
			stmnt_ruta.executeUpdate(); // Used to perform INSERT, UPDATE or DELETE
			
			for(Trajectory t: route.getTrayectorias()) {
				String query_tr = "INSERT INTO trayectoria(id_ruta, id_origin, id_destino, duracion, distancia, direcciones) VALUES (?, ?, ?, ?, ?, ?)";
				PreparedStatement stmnt_tr = conn.prepareStatement(query_tr);
				
				stmnt_tr.setInt(1, t.getId_ruta());
				stmnt_tr.setInt(2, t.getId_origen());
				stmnt_tr.setInt(3, t.getId_destino());
				//stmnt_tr.setDate(4, t.getTiempo()); // Arreglar esto
				stmnt_tr.setFloat(5, t.getDistancia());
				stmnt_tr.setString(6, t.getDirecciones());
				
				stmnt_tr.executeUpdate(); // Used to perform INSERT, UPDATE or DELETE
			}
			
			for(Place p: route.getLugares()) {
				String query_pl = "INSERT INTO lugar(id_ruta, nombre, direccion, tiempo_usuario, urlfoto, calificacion, costo, tipo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement stmnt_pl = conn.prepareStatement(query_pl);
				
				stmnt_pl.setInt(1, p.getId_ruta());
				stmnt_pl.setString(2, p.getNombre());
				stmnt_pl.setString(3, p.getDireccion());
				//stmnt_pl.setDate(4, p.getTiempo_usuario()); //Arreglar esto
				stmnt_pl.setString(5, p.getUrlFoto());
				stmnt_pl.setFloat(6, p.getCalificacion());
				stmnt_pl.setString(7, p.getCosto());
				stmnt_pl.setString(8, p.getTipo());
				
				stmnt_pl.executeUpdate(); // Used to perform INSERT, UPDATE or DELETE
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public Route getRoute(int id_usuario, int id_ruta) {
		// TODO implementar funcion
		Route route = new Route();
		
		
		return route;
	}
	
	public List<Route> getRoutes(int id_usuario){
		List<Route> routes = new ArrayList<Route>();
		//TODO implement function
		
		
		return routes;
	}
	
	public int modifyRoute(Route route) {
		//TODO implement function
		return -1;
	}
	
}
